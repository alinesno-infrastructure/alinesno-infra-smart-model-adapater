package com.agentsflex.llm.deepseek.test;

import lombok.SneakyThrows;
import okhttp3.*;

public class TestMain {
    @SneakyThrows
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"model\": \"DeepSeek-R1\",\r\n    \"messages\": [\r\n        {\r\n            \"role\": \"user\",\r\n            \"content\": \"你好\"\r\n        }\r\n    ],\r\n    \"stream\": true\r\n}");
        Request request = new Request.Builder()
            .url("http://apigw.chogori.wzhlwq.jdcloud.com/v1/chat/completions")
            .method("POST", body)
            .addHeader("Authorization", "Bearer KpGMw5K0IyM-WaKPMcYNHDiBJABbYj8fi6WKAOuXPME=")
            .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "*/*")
            .addHeader("Host", "apigw.chogori.wzhlwq.jdcloud.com")
            .addHeader("Connection", "keep-alive")
            .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().toString());
    }
}
