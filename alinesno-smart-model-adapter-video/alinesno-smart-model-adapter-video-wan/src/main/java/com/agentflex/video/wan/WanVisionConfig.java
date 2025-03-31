package com.agentflex.video.wan;

import com.agentsflex.core.llm.LlmConfig;
import lombok.Getter;

@Getter
public class WanVisionConfig extends LlmConfig {

    private static final String DEFAULT_MODEL = WanVisionModelEnums.WANX2_1_T2V_TURBO.getModelName(); ;
    private static final String DEFAULT_ENDPOINT = "https://dashscope.aliyuncs.com";

    public WanVisionConfig() {
        setEndpoint(DEFAULT_ENDPOINT);
        setModel(DEFAULT_MODEL);
    }

}
