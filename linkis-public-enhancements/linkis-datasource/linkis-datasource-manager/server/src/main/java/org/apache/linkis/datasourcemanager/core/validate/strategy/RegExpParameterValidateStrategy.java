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

package org.apache.linkis.datasourcemanager.core.validate.strategy;

import org.apache.linkis.datasourcemanager.common.domain.DataSourceParamKeyDefinition;
import org.apache.linkis.datasourcemanager.core.validate.ParameterValidateException;
import org.apache.linkis.datasourcemanager.core.validate.ParameterValidateStrategy;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

import static org.apache.linkis.datasourcemanager.common.errorcode.LinkisDatasourceManagerErrorCodeSummary.PARAM_VALIDATE_FAILED;

/** RegExpression validate strategy */
public class RegExpParameterValidateStrategy implements ParameterValidateStrategy {

  @Override
  public boolean accept(DataSourceParamKeyDefinition.ValueType valueType) {
    return valueType == DataSourceParamKeyDefinition.ValueType.EMAIL
        || valueType == DataSourceParamKeyDefinition.ValueType.TEXT
        || valueType == DataSourceParamKeyDefinition.ValueType.LIST
        || valueType == DataSourceParamKeyDefinition.ValueType.TEXTAREA;
  }

  @Override
  public Object validate(DataSourceParamKeyDefinition keyDefinition, Object actualValue)
      throws ParameterValidateException {
    String valueRegex = keyDefinition.getValueRegex();
    if (StringUtils.isNotBlank(valueRegex)) {
      if (actualValue instanceof List) {
        List valueList = ((List) actualValue);
        for (Object value : valueList) {
          match(keyDefinition.getKey(), keyDefinition.getName(), String.valueOf(value), valueRegex);
        }
      } else {
        match(
            keyDefinition.getKey(),
            keyDefinition.getName(),
            String.valueOf(actualValue),
            valueRegex);
      }
    }
    return actualValue;
  }

  private void match(String key, String name, String value, String valueRegex)
      throws ParameterValidateException {
    //        boolean match = String.valueOf(value).matches(valueRegex);
    boolean match = Pattern.matches(valueRegex, value);
    if (!match) {
      throw new ParameterValidateException(
          PARAM_VALIDATE_FAILED.getErrorDesc()
              + ", [the value: '"
              + String.valueOf(value)
              + "' to key: '"
              + key
              + "("
              + name
              + ")' doesn't match]");
    }
  }
}
