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
package com.alipay.disruptor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @description: [test for FixedSequenceGroup]
 * @email: <a href="guolei.sgl@antfin.com"></a>
 * @author: guolei.sgl
 * @date: 18/8/1
 */
public class FixedSequenceGroupTest {
    @Test
    public void shouldReturnMinimumOf2Sequences() throws Exception {
        Sequence sequence1 = new Sequence(34);
        Sequence sequnece2 = new Sequence(47);
        Sequence group = new FixedSequenceGroup(new Sequence[]{sequence1, sequnece2});

        assertThat(group.get(), is(34L));
        sequence1.set(35);
        assertThat(group.get(), is(35L));
        sequence1.set(48);
        assertThat(group.get(), is(47L));
    }

}