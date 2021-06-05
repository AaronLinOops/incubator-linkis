/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.webank.wedatasphere.linkis.orchestrator.computation.catalyst.reheater

import com.webank.wedatasphere.linkis.common.exception.LinkisRetryException
import com.webank.wedatasphere.linkis.common.utils.{Logging, Utils}
import com.webank.wedatasphere.linkis.orchestrator.computation.conf.ComputationOrchestratorConf
import com.webank.wedatasphere.linkis.orchestrator.computation.utils.TreeNodeUtil
import com.webank.wedatasphere.linkis.orchestrator.execution.FailedTaskResponse
import com.webank.wedatasphere.linkis.orchestrator.extensions.catalyst.ReheaterTransform
import com.webank.wedatasphere.linkis.orchestrator.plans.physical.{ExecTask, PhysicalContext, PhysicalOrchestration, ReheatableExecTask, RetryExecTask}

/**
 * Transform physical tree by pruning it's nodes
 *
 */
class PruneTaskRetryTransform extends ReheaterTransform with Logging{
  override def apply(in: ExecTask, context: PhysicalContext): ExecTask = {
      val failedTasks = TreeNodeUtil.getAllFailedTaskNode(in)
      failedTasks.foreach(task => {
        info(s"task:${in.getId} has ${failedTasks.size} child tasks which execute failed, some of them may be retried")
        TreeNodeUtil.getTaskResponse(task) match {
          case response: FailedTaskResponse => {
            val exception = response.getCause
            if (exception.isInstanceOf[LinkisRetryException]) {
              val parents = task.getParents
              if (parents != null) {
                parents.foreach(parent => {
                  val otherParents = parents.filter(_ != parent)
                  val otherChildren = parent.getChildren.filter(_ != task)
                  Utils.tryCatch{
                    task match {
                      case retryExecTask: RetryExecTask => {
                        if (retryExecTask.getAge() < Integer.parseInt(ComputationOrchestratorConf.RETRYTASK_MAXIMUM_AGE.getValue)) {
                          val newTask = new RetryExecTask(task, retryExecTask.getAge() + 1)
                          newTask.initialize(retryExecTask.getPhysicalContext)
                          TreeNodeUtil.replaceNode(retryExecTask, newTask)
                          TreeNodeUtil.removeTaskResponse(retryExecTask)
                          info(s"Retry---success to rebuild task node, retry-task:${retryExecTask.getId}, current age is ${newTask.getAge()} ")
                        }else{
                          info(s"Retry task: ${retryExecTask.getId} reached maximum age:${retryExecTask.getAge()}, stop to retry it!")
                        }
                      }
                      case _ => {
                        val retryExecTask = new RetryExecTask(task)
                        retryExecTask.initialize(task.getPhysicalContext)
                        TreeNodeUtil.insertNode(parent, task, retryExecTask)
                        TreeNodeUtil.removeTaskResponse(task)
                        info(s"Retry---success to rebuild task node, retry-task:${task.getId}, current age is ${retryExecTask.getAge()} ")
                      }
                    }
                  }{
                    //restore task node when retry task construction failed
                    case e: Exception => {
                      warn(s"Retry task construction failed, start to restore task node, task node: ${task.getId}, " +
                        s"age: ${task match { case retryExecTask: RetryExecTask => retryExecTask.getAge() case _ => 0}}, reason: ${e.getMessage}")
                      parent.withNewChildren(otherChildren :+ task)
                      task.withNewParents(otherParents :+ parent)
                      info(s"restore task success! task node: ${task.getId}")
                    }
                  }
                })
              }
            }
          }
          case _ =>
        }
      })
    in
  }



  override def getName: String = {
    //Cannot ignore inner class
    getClass.getName
  }
}
