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

package org.apache.linkis.rpc.errorcode;

public enum LinkisRpcErrorCodeSummary {
  METHON_CALL_FAILED(10000, "method call failed:(方法调用失败：)"),
  TRANSMITTED_BEAN_IS_NULL(10001, "The transmitted bean is Null.(传输的bean为Null."),
  TIMEOUT_PERIOD(10002, "The timeout period is not set!(超时时间未设置！)"),
  CORRESPONDING_NOT_FOUND(
      10003, "The corresponding anti-sequence class was not found:{0}(找不到对应的反序列类:{0})"),
  CORRESPONDING_TO_INITIALIZE(
      10004, "The corresponding anti-sequence class:{0} failed to initialize(对应的反序列类:{0} 初始化失败)"),
  APPLICATION_IS_NOT_EXISTS(
      10051, "The instance:{0} of application {1} is not exists(应用程序:{0} 的实例:{1} 不存在).");

  /** 错误码 */
  private int errorCode;
  /** 错误描述 */
  private String errorDesc;

  LinkisRpcErrorCodeSummary(int errorCode, String errorDesc) {
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorDesc() {
    return errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }

  @Override
  public String toString() {
    return "errorCode: " + this.errorCode + ", errorDesc:" + this.errorDesc;
  }
}
