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
package org.apache.linkis.basedatamanager.server.dao;

import org.apache.linkis.basedatamanager.server.domain.DatasourceAccessEntity;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author jack
 * @description 针对表【linkis_ps_datasource_access】的数据库操作Mapper
 * @createDate 2022-08-13 15:17:35 @Entity
 *     org.apache.linkis.basedatamanager.server.domain.LinkisPsDatasourceAccess
 */
public interface DatasourceAccessMapper extends BaseMapper<DatasourceAccessEntity> {
  /**
   * 获取列表（分页）
   *
   * @param searchName
   * @return
   */
  List<DatasourceAccessEntity> getListByPage(String searchName);
}
