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

package org.apache.linkis.metadata.query.service;

import org.apache.linkis.datasourcemanager.common.util.json.Json;
import org.apache.linkis.metadata.query.common.domain.MetaColumnInfo;
import org.apache.linkis.metadata.query.common.service.AbstractDbMetaService;
import org.apache.linkis.metadata.query.common.service.MetadataConnection;
import org.apache.linkis.metadata.query.service.conf.SqlParamsMapper;
import org.apache.linkis.metadata.query.service.greenplum.SqlConnection;

import org.apache.logging.log4j.util.Strings;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreenplumMetaService extends AbstractDbMetaService<SqlConnection> {
  @Override
  public MetadataConnection<SqlConnection> getConnection(
      String operator, Map<String, Object> params) throws Exception {
    String host =
        String.valueOf(params.getOrDefault(SqlParamsMapper.PARAM_SQL_HOST.getValue(), ""));
    // After deserialize, Integer will be Double, Why?
    Integer port =
        (Double.valueOf(
                String.valueOf(params.getOrDefault(SqlParamsMapper.PARAM_SQL_PORT.getValue(), 0))))
            .intValue();
    String username =
        String.valueOf(params.getOrDefault(SqlParamsMapper.PARAM_SQL_USERNAME.getValue(), ""));
    String password =
        String.valueOf(params.getOrDefault(SqlParamsMapper.PARAM_SQL_PASSWORD.getValue(), ""));
    // In greenplum, each database under the same instance is completely independent, and the
    // table is stored under the catalog with the same library name.
    // \c (\connect) Behind the dbname command is to close the current connection and create a
    // new connection to achieve database switching
    // Cannot directly switch to another database under the current database connection, and
    // cannot show tables from xxxx, select * from database.table like MySQL
    String database =
        String.valueOf(params.getOrDefault(SqlParamsMapper.PARAM_SQL_DATABASE.getValue(), ""));
    Map<String, Object> extraParams = new HashMap<>();
    Object sqlParamObj = params.get(SqlParamsMapper.PARAM_SQL_EXTRA_PARAMS.getValue());
    if (null != sqlParamObj) {
      if (!(sqlParamObj instanceof Map)) {
        extraParams =
            Json.fromJson(String.valueOf(sqlParamObj), Map.class, String.class, Object.class);
      } else {
        extraParams = (Map<String, Object>) sqlParamObj;
      }
    }
    assert extraParams != null;
    if (Strings.isBlank(database)) {
      database = "";
    }
    return new MetadataConnection<>(
        new SqlConnection(host, port, username, password, database, extraParams));
  }

  @Override
  public List<String> queryTables(SqlConnection connection, String schemaname) {
    try {
      return connection.getAllTables(schemaname);
    } catch (SQLException e) {
      throw new RuntimeException("Fail to get Sql tables(获取表列表失败)", e);
    }
  }

  @Override
  public List<String> queryDatabases(SqlConnection connection) {
    try {
      return connection.getAllDatabases();
    } catch (SQLException e) {
      throw new RuntimeException("Fail to get Sql databases(获取数据库列表失败)", e);
    }
  }

  @Override
  public List<MetaColumnInfo> queryColumns(SqlConnection connection, String schema, String table) {
    try {
      return connection.getColumns(schema, table);
    } catch (SQLException | ClassNotFoundException e) {
      throw new RuntimeException("Fail to get Sql columns(获取字段列表失败)", e);
    }
  }
}
