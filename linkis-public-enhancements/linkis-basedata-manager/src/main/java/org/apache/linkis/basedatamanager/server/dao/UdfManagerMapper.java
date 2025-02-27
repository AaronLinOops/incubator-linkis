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

import org.apache.linkis.basedatamanager.server.domain.UdfManagerEntity;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author jack
 * @description Database operation Mapper for the table [linKIS_PS_UDF_manager]
 * @createDate 2022-08-13 15:14:10 @Entity
 *     org.apache.linkis.basedatamanager.server.domain.LinkisPsUdfManager
 */
public interface UdfManagerMapper extends BaseMapper<UdfManagerEntity> {
  List<UdfManagerEntity> getListByPage(String searchName);
}
