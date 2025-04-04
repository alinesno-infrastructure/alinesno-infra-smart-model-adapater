/*
 *  Copyright (c) 2023-2025, Agents-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agentsflex.core.llm;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 每个大模型都可以有自己的实现类
 */
@Setter
@Getter
public class ChatOptions {

    public static final ChatOptions DEFAULT = new ChatOptions() {
        @Override
        public void setTemperature(Float temperature) {
            throw new IllegalStateException("Can not set temperature to the default instance.");
        }

        @Override
        public void setMaxTokens(Integer maxTokens) {
            throw new IllegalStateException("Can not set maxTokens to the default instance.");
        }
    };


    private String seed;
    private Float temperature = 0.8f;
    private Float topP;
    private Integer topK;
    private Integer maxTokens;
    private List<String> stop;

}
