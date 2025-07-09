package com.agentsflex.reranker.custom;

import com.agentsflex.core.reranker.ReRankerConfig;
import lombok.Getter;

@Getter
public class CustomRerankerConfig extends ReRankerConfig {

    private static final String DEFAULT_MODEL = "demo-rerank";
    private static final String DEFAULT_ENDPOINT = "http://example.infra.linesno.com/text-reanker" ;

    public CustomRerankerConfig() {
        setEndpoint(DEFAULT_ENDPOINT);
        setModel(DEFAULT_MODEL);
    }

}
