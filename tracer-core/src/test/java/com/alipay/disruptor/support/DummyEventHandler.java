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
package com.alipay.disruptor.support;

import com.alipay.disruptor.EventHandler;
import com.alipay.disruptor.LifecycleAware;

/**
 * @description: [support for test handler]
 * @email: <a href="guolei.sgl@antfin.com"></a>
 * @author: guolei.sgl
 * @date: 18/8/1
 */
public class DummyEventHandler<T> implements EventHandler<T>, LifecycleAware {
    public int startCalls = 0;
    public int shutdownCalls = 0;
    public T lastEvent;
    public long lastSequence;

    @Override
    public void onStart() {
        startCalls++;
    }

    @Override
    public void onShutdown() {
        shutdownCalls++;
    }

    @Override
    public void onEvent(T event, long sequence, boolean endOfBatch) throws Exception {
        lastEvent = event;
        lastSequence = sequence;
    }
}
