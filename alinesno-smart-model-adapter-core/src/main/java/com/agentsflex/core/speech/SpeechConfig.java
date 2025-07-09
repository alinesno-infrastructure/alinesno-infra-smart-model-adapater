package com.agentsflex.core.speech;

import lombok.Data;

import java.io.Serializable;

/**
 * 语音模型配置类，用于配置语音识别或合成服务的相关参数
 */
@Data
public class SpeechConfig implements Serializable {

    // 服务接入点，指定与语音服务通信的服务器地址
    private String endpoint;

    // API密钥，用于身份验证以访问语音服务
    private String apiKey;

    // 语音模型标识，指定用于识别或合成的特定语音模型
    private String model;
}
