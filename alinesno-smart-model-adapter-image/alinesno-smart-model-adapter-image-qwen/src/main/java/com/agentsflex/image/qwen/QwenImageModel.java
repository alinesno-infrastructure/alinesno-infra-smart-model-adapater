package com.agentsflex.image.qwen;

import com.agentsflex.core.image.*;
import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.image.qwen.bean.*;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class QwenImageModel implements ImageModel {

    private final QwenImageModelConfig config;
    private final HttpClient httpClient = new HttpClient();
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public QwenImageModel(QwenImageModelConfig config) {
        this.config = config;
    }

    @Override
    public ImageResponse generate(GenerateImageRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + config.getApiKey());
        headers.put("X-DashScope-Async", "enable");

        String payload = getString(request);

        String url = config.getEndpoint();
        try {
            // 发送初始请求
            String initialResponseStr = httpClient.post(url, headers, payload);
            InitialResponse initialResult = JSON.parseObject(initialResponseStr, InitialResponse.class);
            String taskId = initialResult.getOutput().getTask_id();

            // 轮询任务结果
            TaskResultResponse taskResult = pollTaskResult(taskId, headers);

            // 这里可以根据任务结果进一步处理，例如下载图片等
            if ("SUCCEEDED".equals(taskResult.getOutput().getTask_status())) {
                ImageResponse response = new ImageResponse();
                List<TaskResultResponse.Result> results = taskResult.getOutput().getResults();
                for (TaskResultResponse.Result result : results) {
                    String imageUrl = result.getUrl();
                    try {
                        // 下载图片字节数据
                        byte[] imageBytes = downloadImage(imageUrl);
                        response.addImage(imageBytes);
                    } catch (IOException e) {
                        log.error("Failed to download image from URL: {}", imageUrl, e);
                        // 可以选择继续下载其他图片，或者将错误信息添加到响应中
                        response.setError(true);
                        response.setErrorMessage("Failed to download some images. Check logs for details.");
                    }
                }
                return response;
            } else {
                return ImageResponse.error("Task failed with status: " + taskResult.getOutput().getTask_status());
            }
        } catch (IOException | InterruptedException e) {
            log.error("HTTP request error", e);
            return ImageResponse.error("HTTP request error: " + e.getMessage());
        }
    }

    @NotNull
    private String getString(GenerateImageRequest request) {
        Map<String, Object> input = new HashMap<>();
        input.put("prompt", request.getPrompt());

        if(request.getNegativePrompt() != null){
            input.put("negative_prompt", request.getNegativePrompt());
        }

        Map<String, Object> parameters = new HashMap<>();
        if (request.getWidth() != null && request.getHeight() != null) {
            parameters.put("size", request.getWidth() + "*" + request.getHeight());
        }
        parameters.put("n", request.getN());

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("model", config.getModel());
        payloadMap.put("input", input);
        payloadMap.put("parameters", parameters);

        return convertToJson(payloadMap);
    }

    private String convertToJson(Map<String, Object> map) {
        return JSON.toJSONString(map);
    }

    private TaskResultResponse pollTaskResult(String taskId, Map<String, String> headers) throws IOException, InterruptedException {
        String taskStatusUrl = config.getTaskEndpoint() + taskId ;
        while (true) {
            String taskStatusResponseStr = httpClient.get(taskStatusUrl, headers);
            TaskResultResponse taskResult = JSON.parseObject(taskStatusResponseStr, TaskResultResponse.class);
            String taskStatus = taskResult.getOutput().getTask_status();
            if ("SUCCEEDED".equals(taskStatus) || "FAILED".equals(taskStatus)) {
                return taskResult;
            }
            Thread.sleep(2000); // 每 2 秒检查一次
        }
    }

    private byte[] downloadImage(String imageUrl) throws IOException {
        Request request = new Request.Builder()
            .url(imageUrl)
            .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            assert response.body() != null;
            return response.body().bytes();
        }
    }

    @Override
    public ImageResponse edit(EditImageRequest request) {
        throw new IllegalStateException("QwenImageModel Can not support edit image.");
    }

    @Override
    public ImageResponse vary(VaryImageRequest request) {
        throw new IllegalStateException("QwenImageModel Can not support vary image.");
    }

    @Override
    public ImageResponse understand(UnderstandImageRequest request) {
        try {
            // 构建JSON结构
            Map<String, Object> requestBody = getStringObjectMap(request);

            // 转换为JSON字符串
            String json = JSON.toJSONString(requestBody);

            // 构建HTTP请求
            RequestBody body = RequestBody.create(
                MediaType.get("application/json; charset=utf-8"),
                json
            );

            Request requestHttp = new Request.Builder()
                .url(config.getEndpoint())
                .post(body)
                .addHeader("Authorization", "Bearer " + config.getApiKey())
                .build();

            try (Response response = okHttpClient.newCall(requestHttp).execute()) {
                if (response.isSuccessful()) {
                    ImageResponse imageResponse = new ImageResponse();
                    // 处理响应数据
                    return imageResponse;
                }
            }
        } catch (IOException e) {
            log.error("请求处理失败: {}", e.getMessage());
        }
        return null;
    }

    @NotNull
    private static Map<String, Object> getStringObjectMap(UnderstandImageRequest request) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "qwen-vl-plus");

        List<Map<String, Object>> messages = new ArrayList<>();
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");

        List<Map<String, Object>> contentList = new ArrayList<>();
        // 添加文本内容
        Map<String, Object> textContent = new HashMap<>();
        textContent.put("type", "text");
        textContent.put("text", request.getText());
        contentList.add(textContent);

        // 添加图片URL内容
        Map<String, Object> imageContent = new HashMap<>();
        imageContent.put("type", "image_url");
        Map<String, String> imageUrl = new HashMap<>();
        imageUrl.put("url", request.getImageUrl());
        imageContent.put("image_url", imageUrl);
        contentList.add(imageContent);

        message.put("content", contentList);
        messages.add(message);
        requestBody.put("messages", messages);

        return requestBody;
    }

}
