package com.agentsflex.reranker.siliconflow.test;

import com.agentsflex.core.reranker.ReRanker;
import com.agentsflex.core.reranker.ReRankerRequest;
import com.agentsflex.core.reranker.ReRankerResponse;
import com.agentsflex.reranker.siliconflow.SiliconFlowReRanker;
import com.agentsflex.reranker.siliconflow.SiliconFlowRerankerConfig;
import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

public class QwenReRankerTest {

    public static void main(String[] args) throws InterruptedException {

        // 创建 QwenRerankerConfig 实例并设置必要的配置
        SiliconFlowRerankerConfig config = new SiliconFlowRerankerConfig();
        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;

        // 创建 QwenReRanker 实例
        ReRanker qwenReRanker = new SiliconFlowReRanker(config);

        // 创建测试用的 ReRankerRequest 实例
        ReRankerRequest request = new ReRankerRequest();
        request.setTop_n(2);
        request.setReturn_documents(true);
        request.setQuery("什么是文本排序模型");
        List<String> documents = Arrays.asList(
            "文本排序模型广泛用于搜索引擎和推荐系统中，它们根据文本相关性对候选文本进行排序",
            "量子计算是计算科学的一个前沿领域",
            "预训练语言模型的发展给文本排序模型带来了新的进展"
        );
        request.setDocuments(documents);

        // 调用 reranker 方法获取响应
        ReRankerResponse response = qwenReRanker.reranker(request);

        // 使用 Fastjson 格式化打印响应
        String formattedJson = JSON.toJSONString(response, true);
        System.out.println(formattedJson);

    }

}
