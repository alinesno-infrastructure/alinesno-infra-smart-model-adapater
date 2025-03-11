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

/**
 * LlmConfig 类用于配置与大型语言模型（LLM）交互所需的参数
 * 实现了 Serializable 接口，以支持对象的序列化和反序列化，便于网络传输或存储
 */
package com.agentsflex.core.llm;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Consumer;

@Data
public class LlmConfig implements Serializable {

    // 模型名称，用于指定与之交互的具体语言模型
    private String model;

    // 服务端点，即发送请求的URL地址
    private String endpoint;

    // API密钥，用于身份验证
    private String apiKey;

    // API密钥的秘密部分，与apiKey一起用于身份验证
    private String apiSecret;

    // 调试模式，开启后可能会输出更多的调试信息
    private boolean debug;

    // 自定义请求头配置的消费者函数，用于在发送请求前动态修改请求头
    private Consumer<Map<String, String>> headersConfig;

}
