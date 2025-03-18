package com.agentsflex.core.reranker;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Consumer;

/**
 * reranker配置类
 */
@Data
public class ReRankerConfig implements Serializable {

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
