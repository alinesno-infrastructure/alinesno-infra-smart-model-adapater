package com.agentsflex.reranker.siliconflow;

import com.agentsflex.core.reranker.ReRankerConfig;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SiliconFlowRerankerConfig extends ReRankerConfig {

    private static final String DEFAULT_MODEL = "BAAI/bge-reranker-v2-m3";
    private static final String DEFAULT_ENDPOINT = "https://api.siliconflow.cn/v1/rerank";

    private int maxChunksPerDoc = 1024;
    private int overlapTokens = 80;

    public SiliconFlowRerankerConfig() {
        setEndpoint(DEFAULT_ENDPOINT);
        setModel(DEFAULT_MODEL);
    }

}
