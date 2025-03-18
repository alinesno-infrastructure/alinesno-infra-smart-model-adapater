package com.agentsflex.reranker.siliconflow;

import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.core.reranker.BaseReRanker;
import com.agentsflex.core.reranker.ReRankerItem;
import com.agentsflex.core.reranker.ReRankerRequest;
import com.agentsflex.core.reranker.ReRankerResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

// SiliconFlowReRanker 类的实现
public class SiliconFlowReRanker extends BaseReRanker<SiliconFlowRerankerConfig> {

    HttpClient httpClient = new HttpClient();

    public SiliconFlowReRanker(SiliconFlowRerankerConfig config) {
        super(config);
    }

    @Override
    public ReRankerResponse reranker(ReRankerRequest request) {
        // 构建请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + getConfig().getApiKey());
        headers.put("Content-Type", "application/json");

        // 构建请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", getConfig().getModel());
        requestBody.put("query", request.getQuery());
        requestBody.put("documents", request.getDocuments());
        requestBody.put("top_n", request.getTop_n());
        requestBody.put("return_documents", request.isReturn_documents());
        requestBody.put("max_chunks_per_doc", 1024);
        requestBody.put("overlap_tokens", 80);

        // 发送 POST 请求
        String responseStr = httpClient.post(getConfig().getEndpoint(), headers, requestBody.toJSONString());

        // 解析响应
        if (responseStr != null) {
            JSONObject responseObj = JSON.parseObject(responseStr);
            ReRankerResponse response = new ReRankerResponse();
            response.setId(responseObj.getString("id"));

            JSONArray resultsArray = responseObj.getJSONArray("results");
            if (resultsArray != null) {
                for (int i = 0; i < resultsArray.size(); i++) {
                    JSONObject resultObj = resultsArray.getJSONObject(i);
                    ReRankerItem item = new ReRankerItem();
                    ReRankerItem.Document document = new ReRankerItem.Document();
                    document.setText(resultObj.getJSONObject("document").getString("text"));
                    item.setDocument(document);
                    item.setIndex(resultObj.getIntValue("index"));
                    item.setRelevance_score(resultObj.getFloatValue("relevance_score"));
                    response.getResult().add(item);
                }
            }

            JSONObject tokensObj = responseObj.getJSONObject("tokens");
            if (tokensObj != null) {
                response.setInput_tokens(tokensObj.getLongValue("input_tokens"));
                response.setOutput_tokens(tokensObj.getLongValue("output_tokens"));
                response.setTotal_tokens(tokensObj.getLongValue("input_tokens") + tokensObj.getLongValue("output_tokens"));
            }

            return response;
        }

        return null;
    }
}
