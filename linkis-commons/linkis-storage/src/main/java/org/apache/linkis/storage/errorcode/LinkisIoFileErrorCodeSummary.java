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

package org.apache.linkis.storage.errorcode;

public enum LinkisIoFileErrorCodeSummary {
  CANNOT_BE_EMPTY(
      53002,
      "The read method parameter cannot be empty(read方法参数不能为空)",
      "The read method parameter cannot be empty(read方法参数不能为空)"),
  FS_CAN_NOT_PROXY_TO(
      52002, "FS Can not proxy to:{}(FS 不能代理到：{})", "FS Can not proxy to：{}(FS 不能代理到：{})"),
  NOT_EXISTS_METHOD(
      53003, "not exists method {} in fs {}(方法不存在)", "not exists method {} in fs {}(方法不存在)"),
  PARAMETER_CALLS(
      53003, "Unsupported parameter calls(不支持的参数调用)", "Unsupported parameter calls(不支持的参数调用)");
  /** 错误码 */
  private int errorCode;
  /** 错误描述 */
  private String errorDesc;
  /** 错误可能出现的原因 */
  private String comment;

  LinkisIoFileErrorCodeSummary(int errorCode, String errorDesc, String comment) {
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
    this.comment = comment;
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

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public String toString() {
    return "errorCode: " + this.errorCode + ", errorDesc:" + this.errorDesc;
  }
}
