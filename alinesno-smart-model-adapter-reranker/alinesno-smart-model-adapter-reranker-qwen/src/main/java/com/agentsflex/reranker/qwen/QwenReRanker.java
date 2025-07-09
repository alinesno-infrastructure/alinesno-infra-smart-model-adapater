package com.agentsflex.reranker.qwen;

import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.core.reranker.BaseReRanker;
import com.agentsflex.core.reranker.ReRankerItem;
import com.agentsflex.core.reranker.ReRankerRequest;
import com.agentsflex.core.reranker.ReRankerResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// QwenReRanker 类的实现
public class QwenReRanker extends BaseReRanker<QwenRerankerConfig> {

    HttpClient httpClient = new HttpClient();

    public QwenReRanker(QwenRerankerConfig config) {
        super(config);
    }

    @Override
    public ReRankerResponse reranker(ReRankerRequest request) {
        QwenRerankerConfig config = getConfig();
        String endpoint = config.getEndpoint();
        String apiKey = config.getApiKey();

        // 构建请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + apiKey);
        headers.put("Content-Type", "application/json");

        // 构建请求体
        JSONObject inputObject = new JSONObject();
        inputObject.put("query", request.getQuery());
        JSONArray documentsArray = new JSONArray();
        documentsArray.addAll(request.getDocuments());
        inputObject.put("documents", documentsArray);

        JSONObject parametersObject = new JSONObject();
        parametersObject.put("return_documents", request.isReturn_documents());
        parametersObject.put("top_n", request.getTop_n());

        JSONObject requestBodyObject = new JSONObject();
        requestBodyObject.put("model", config.getModel());
        requestBodyObject.put("input", inputObject);
        requestBodyObject.put("parameters", parametersObject);

        String requestBodyJson = requestBodyObject.toJSONString();

        // 发送请求
        String responseJson = httpClient.post(endpoint, headers, requestBodyJson);

        // 解析响应
        JSONObject responseObject = JSON.parseObject(responseJson);

        ReRankerResponse response = new ReRankerResponse();
        response.setId(responseObject.getString("request_id"));

        JSONArray resultsArray = responseObject.getJSONObject("output").getJSONArray("results");
        List<ReRankerItem> items = resultsArray.toJavaList(ReRankerItem.class);
        response.setResult(items);

        response.setInput_tokens(responseObject.getJSONObject("usage").getLong("total_tokens"));
        response.setOutput_tokens(0); // 这里需要根据实际情况设置
        response.setTotal_tokens(responseObject.getJSONObject("usage").getLong("total_tokens"));

        return response;
    }
}
