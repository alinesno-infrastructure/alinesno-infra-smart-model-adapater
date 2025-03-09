package com.agentsflex.spring.boot.llm.deepseek;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author luoxiaodong
 * @version 1.0.0
 */
@Getter
@ConfigurationProperties(prefix = "alinesno-smart-model-adapter.llm.deepseek")
public class DeepseekProperties {

    private String model = "deepseek-chat";
    private String endpoint = "https://api.deepseek.com";
    private String apiKey;
    private String apiSecret;

    public void setModel(String model) {
        this.model = model;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

}
