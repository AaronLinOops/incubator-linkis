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

package org.apache.linkis.basedatamanager.server.service.impl;

import org.apache.linkis.basedatamanager.server.dao.DatasourceEnvMapper;
import org.apache.linkis.basedatamanager.server.domain.DatasourceEnvEntity;
import org.apache.linkis.basedatamanager.server.service.DatasourceEnvService;

import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @description Database operation Service implementation for the table
 *     [linKIS_PS_DM_DATASource_env]
 * @createDate 2022-08-13 15:15:25
 */
@Service
public class DatasourceEnvServiceImpl extends ServiceImpl<DatasourceEnvMapper, DatasourceEnvEntity>
    implements DatasourceEnvService {
  @Override
  public PageInfo getListByPage(String searchName, Integer currentPage, Integer pageSize) {
    PageHelper.startPage(currentPage, pageSize);
    List<DatasourceEnvEntity> listByPage = this.getBaseMapper().getListByPage(searchName);
    PageInfo pageInfo = new PageInfo(listByPage);
    return pageInfo;
  }
}
