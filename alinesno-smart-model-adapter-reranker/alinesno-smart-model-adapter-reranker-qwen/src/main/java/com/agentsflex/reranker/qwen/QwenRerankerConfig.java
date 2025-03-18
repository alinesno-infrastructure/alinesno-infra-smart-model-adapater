package com.agentsflex.reranker.qwen;

import com.agentsflex.core.reranker.ReRankerConfig;
import lombok.Getter;

@Getter
public class QwenRerankerConfig extends ReRankerConfig {

    private static final String DEFAULT_MODEL = "gte-rerank";
    private static final String DEFAULT_ENDPOINT = "https://dashscope.aliyuncs.com/api/v1/services/rerank/text-rerank/text-rerank";

    public QwenRerankerConfig() {
        setEndpoint(DEFAULT_ENDPOINT);
        setModel(DEFAULT_MODEL);
    }

}
