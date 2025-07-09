package com.agentsflex.spring.boot.llm.deepseek;

import com.agentsflex.llm.deepseek.DeepseekLlm;
import com.agentsflex.llm.deepseek.DeepseekLlmConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * alinesno-smart-model-adapter 大语言模型自动配置。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@ConditionalOnClass(DeepseekLlm.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(DeepseekProperties.class)
public class DeepseekAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DeepseekLlm deepseekLlm(DeepseekProperties properties) {
        DeepseekLlmConfig config = new DeepseekLlmConfig();
        config.setApiKey(properties.getApiKey());
        config.setApiSecret(properties.getApiSecret());
        config.setEndpoint(properties.getEndpoint());
        config.setModel(properties.getModel());
        return new DeepseekLlm(config);
    }

}
