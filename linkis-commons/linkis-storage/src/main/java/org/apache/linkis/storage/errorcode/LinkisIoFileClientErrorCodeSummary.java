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

public enum LinkisIoFileClientErrorCodeSummary {
  NO_PROXY_USER(
      52002,
      "proxy user not set, can not get the permission information.(没有设置代理 proxy 用户，无法获取权限信息)",
      "proxy user not set, can not get the permission information.(没有设置代理 proxy 用户，无法获取权限信息)"),
  FAILED_TO_INIT_USER(
      52002,
      "Failed to init FS for user:(为用户初始化 FS 失败：)",
      "Failed to init FS for user:(为用户初始化 FS 失败：)"),
  ENGINE_CLOSED_IO_ILLEGAL(
      52002,
      "has been closed, IO operation was illegal.(已经关闭，IO操作是非法的.)",
      "has been closed, IO operation was illegal.(已经关闭，IO操作是非法的.)"),
  STORAGE_HAS_BEEN_CLOSED(
      52002, "storage has been closed.(存储已关闭.)", "storage has been closed.(存储已关闭.)");

  /** 错误码 */
  private int errorCode;
  /** 错误描述 */
  private String errorDesc;
  /** 错误可能出现的原因 */
  private String comment;

  LinkisIoFileClientErrorCodeSummary(int errorCode, String errorDesc, String comment) {
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
