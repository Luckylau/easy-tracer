/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.tracer.plugin.flexible;

import com.alipay.common.tracer.core.appender.builder.JsonStringBuilder;
import com.alipay.common.tracer.core.appender.builder.XStringBuilder;
import com.alipay.common.tracer.core.span.CommonSpanTags;
import com.alipay.common.tracer.core.span.SofaTracerSpan;
import com.alipay.common.tracer.core.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: guolei.sgl (guolei.sgl@antfin.com) 2019/9/1 7:23 PM
 * @since:
 **/
public class FlexibleDigestEncoder extends FlexibleDigestJsonEncoder {

    @Override
    protected void appendComponentSlot(XStringBuilder xsb, JsonStringBuilder jsb, SofaTracerSpan span) {
        Map<String, String> strTags = span.getTagsWithStr();
        Map<String, Number> numTags = span.getTagsWithNumber();
        Map<String, Boolean> boolTags = span.getTagsWithBool();

        xsb.append(strTags.get(CommonSpanTags.METHOD));
        Set<String> strKeys = strTags.keySet();
        Map<String, String> flexibleTags = new HashMap<>();
        strKeys.forEach(key -> {
            if (!isFlexible(key)) {
                flexibleTags.put(key, strTags.get(key));
            }
        });

        Set<String> numKeys = numTags.keySet();
        numKeys.forEach(key -> {
            if (!isFlexible(key)) {
                flexibleTags.put(key, String.valueOf(numTags.get(key)));
            }
        });

        Set<String> boolKeys = boolTags.keySet();
        boolKeys.forEach(key -> {
            if (!isFlexible(key)) {
                flexibleTags.put(key, String.valueOf(boolTags.get(key)));
            }
        });

        String flexibleTagsData = StringUtils.mapToString(flexibleTags);
        xsb.append(flexibleTagsData);
    }
}
