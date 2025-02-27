/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.linkis.cli.application.interactor.job;

import org.apache.linkis.cli.application.operator.once.LinkisNodeStatus;
import org.apache.linkis.cli.common.entity.job.JobStatus;

import org.apache.commons.lang3.StringUtils;

public enum LinkisJobStatus implements JobStatus {
  UNSUBMITTED("Unsubmitted", 0),
  SUBMITTING("Submitting", 1),
  INITED("Inited", 2),
  WAIT_FOR_RETRY("WaitForRetry", 3),
  SCHEDULED("Scheduled", 4),
  RUNNING("Running", 5),
  SUCCEED("Succeed", 6),
  FAILED("Failed", 7),
  CANCELLED("Cancelled", 8),
  TIMEOUT("Timeout", 9),
  UNKNOWN("Unknown", 10),
  SHUTTINGDOWN("Shuttingdown", 11);

  private String name;
  private int id;

  LinkisJobStatus(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public static LinkisJobStatus convertFromJobStatusString(String status) {
    if (StringUtils.isNotBlank(status)) {
      if (LinkisJobStatus.INITED.name().equalsIgnoreCase(status)) return LinkisJobStatus.INITED;
      else if (LinkisJobStatus.WAIT_FOR_RETRY.name().equalsIgnoreCase(status))
        return LinkisJobStatus.WAIT_FOR_RETRY;
      else if (LinkisJobStatus.SCHEDULED.name().equalsIgnoreCase(status))
        return LinkisJobStatus.SCHEDULED;
      else if (LinkisJobStatus.RUNNING.name().equalsIgnoreCase(status))
        return LinkisJobStatus.RUNNING;
      else if (LinkisJobStatus.SUCCEED.name().equalsIgnoreCase(status))
        return LinkisJobStatus.SUCCEED;
      else if (LinkisJobStatus.FAILED.name().equalsIgnoreCase(status))
        return LinkisJobStatus.FAILED;
      else if (LinkisJobStatus.CANCELLED.name().equalsIgnoreCase(status))
        return LinkisJobStatus.CANCELLED;
      else if (LinkisJobStatus.TIMEOUT.name().equalsIgnoreCase(status))
        return LinkisJobStatus.TIMEOUT;
      else return LinkisJobStatus.UNKNOWN;
    } else {
      return LinkisJobStatus.UNKNOWN;
    }
  }

  public static LinkisJobStatus convertFromNodeStatusString(String status) {
    if (StringUtils.isNotBlank(status)) {
      if (LinkisNodeStatus.Idle.name().equalsIgnoreCase(status)) return LinkisJobStatus.RUNNING;
      else if (LinkisNodeStatus.Starting.name().equalsIgnoreCase(status))
        return LinkisJobStatus.SCHEDULED;
      else if (LinkisNodeStatus.Unlock.name().equalsIgnoreCase(status))
        return LinkisJobStatus.RUNNING;
      else if (LinkisNodeStatus.Locked.name().equalsIgnoreCase(status))
        return LinkisJobStatus.RUNNING;
      else if (LinkisNodeStatus.Running.name().equalsIgnoreCase(status))
        return LinkisJobStatus.RUNNING;
      else if (LinkisNodeStatus.Busy.name().equalsIgnoreCase(status))
        return LinkisJobStatus.RUNNING;
      else if (LinkisNodeStatus.Success.name().equalsIgnoreCase(status))
        return LinkisJobStatus.SUCCEED;
      else if (LinkisNodeStatus.Failed.name().equalsIgnoreCase(status))
        return LinkisJobStatus.FAILED;
      else if (LinkisNodeStatus.ShuttingDown.name().equalsIgnoreCase(status))
        return LinkisJobStatus.SHUTTINGDOWN;
      else return LinkisJobStatus.UNKNOWN;
    } else {
      return LinkisJobStatus.UNKNOWN;
    }
  }

  @Override
  public final boolean isJobSubmitted() {
    return !(this == UNSUBMITTED || this == SUBMITTING || this == UNKNOWN);
  }

  @Override
  public final boolean isJobFinishedState() {
    return this.isJobSuccess()
        || this.isJobFailure()
        || this.isJobCancelled()
        || this.isJobTimeout()
        || this.isJobAbnormalStatus()
        || this == SHUTTINGDOWN;
  }

  @Override
  public final boolean isJobSuccess() {
    return this == SUCCEED;
  }

  @Override
  public final boolean isJobFailure() {
    return this == FAILED;
  }

  @Override
  public final boolean isJobCancelled() {
    return this == CANCELLED;
  }

  @Override
  public final boolean isJobTimeout() {
    return this == TIMEOUT;
  }

  @Override
  public final boolean isJobAbnormalStatus() {
    return this == UNKNOWN;
  }
}
