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

package org.apache.linkis.ecm.errorcode;

public enum EngineconnServerErrorCodeSummary {
  EC_START_TIME_OUT(
      11100,
      "wait for engineConn initial timeout(请求引擎超时，可能是因为队列资源不足导致，请重试)",
      "wait for engineConn initial timeout(请求引擎超时，可能是因为队列资源不足导致，请重试)"),
  EC_INTERRUPT_TIME_OUT(
      11101,
      "wait for initial interrupted(请求引擎被中断，可能是因为你操作了引擎取消操作，请重试) ",
      "wait for initial interrupted(请求引擎被中断，可能是因为你操作了引擎取消操作，请重试) "),
  NOT_SUPPORTED_TYPE(
      11102,
      "Not supported BmlResource visibility type: label(不支持的 BmlResource visibility 类型：label).",
      "Not supported BmlResource visibility type: label(不支持的 BmlResource visibility 类型：label)."),
  EC_START_FAILED(11102, "", ""),
  CANNOT_FETCH_MORE_THAN(
      11110,
      "Cannot fetch more than {0} lines of logs.(无法获取超过{0}行的日志.)",
      "Cannot fetch more than {0} lines of logs.(无法获取超过{0}行的日志.)"),
  LOGFILE_IS_NOT_EXISTS(
      11110,
      "LogFile {0} is not exists or is not a file.(LogFile {0} 不存在或不是文件.)",
      "LogFile {0} is not exists or is not a file.(LogFile {0} 不存在或不是文件.)"),
  BOTH_NOT_EXISTS(
      11110,
      "the parameters of engineConnInstance and ticketId are both not exists.(engineConnInstance 和ticketId 的参数都不存在.)",
      "the parameters of engineConnInstance and ticketId are both not exists.(engineConnInstance 和ticketId 的参数都不存在.)"),
  LOG_IS_NOT_EXISTS(
      11110,
      "Log directory {0} is not exists.(日志目录 {0} 不存在.)",
      "Log directory {0} is not exists.(日志目录 {0} 不存在.)"),
  FAILED_TO_DOWNLOAD(911115, "failed to downLoad(下载失败)", "failed to downLoad(下载失败)");

  /** (errorCode)错误码 */
  private int errorCode;
  /** (errorDesc)错误描述 */
  private String errorDesc;
  /** Possible reasons for the error(错误可能出现的原因) */
  private String comment;

  EngineconnServerErrorCodeSummary(int errorCode, String errorDesc, String comment) {
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
