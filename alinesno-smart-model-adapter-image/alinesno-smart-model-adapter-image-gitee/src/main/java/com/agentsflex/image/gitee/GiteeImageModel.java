/*
 *  Copyright (c) 2023-2025, alinesno-smart-model-adapter (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agentsflex.image.gitee;

import com.agentsflex.core.image.*;
import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.core.util.Maps;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiteeImageModel implements ImageModel {

    private GiteeImageModelConfig config;
    private HttpClient httpClient = new HttpClient();

    public GiteeImageModel(GiteeImageModelConfig config) {
        this.config = config;
    }

    @Override
    public ImageResponse generate(GenerateImageRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + config.getApiKey());

        String payload = Maps.of("inputs", request.getPrompt())
            .setIfNotNull("width", request.getWidth())
            .setIfNotNull("height", request.getHeight())
            .toJSON();

        String url = config.getEndpoint() + "/api/serverless/" + config.getModel() + "/text-to-image";
        byte[] imageBytes = httpClient.postBytes(url, headers, payload);
        if (imageBytes == null || imageBytes.length == 0) {
            return ImageResponse.error("can not read the image bytes.");
        }

        ImageResponse response = new ImageResponse();
        response.addImage(imageBytes);

        return response;
    }


    @Override
    public ImageResponse edit(EditImageRequest request) {
        throw new IllegalStateException("GiteeImageModel Can not support edit image.");
    }

    @Override
    public ImageResponse vary(VaryImageRequest request) {
        throw new IllegalStateException("GiteeImageModel Can not support vary image.");
    }

    @Override
    public UnderstandImageResponse understand(UnderstandImageRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + config.getApiKey());

        Map<String, Object> messagesContent = new HashMap<>();
        messagesContent.put("role", "user");
        messagesContent.put("content", List.of(
            Map.of("type", "text", "text", request.getText()),
            Map.of("type", "image_url", "image_url", Map.of("url", request.getImageUrl()))
        ));

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("model", config.getModel());
        payloadMap.put("stream", false) ;
        payloadMap.put("messages", List.of(messagesContent));

        String payload = JSON.toJSONString(payloadMap);
        String url = config.getUnderstandEndpoint();

        String responseStr = httpClient.post(url, headers, payload);
        // 解析 responseStr 中的 content 字段
        JSONObject jsonObject = JSON.parseObject(responseStr);
        List<JSONObject> choices = jsonObject.getJSONArray("choices").toList(JSONObject.class);
        if (!choices.isEmpty()) {
            JSONObject firstChoice = choices.get(0);
            JSONObject message = firstChoice.getJSONObject("message");
            String content = message.getString("content");

            UnderstandImageResponse understandResponse = new UnderstandImageResponse();
            understandResponse.setResponse(content);
            return understandResponse;
        } else {
            return UnderstandImageResponse.error("No choices found in the response.");
        }
    }

}
