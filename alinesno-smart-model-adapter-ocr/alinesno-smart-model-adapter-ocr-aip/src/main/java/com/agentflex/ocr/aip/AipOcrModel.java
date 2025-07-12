package com.agentflex.ocr.aip;

import com.agentsflex.core.ocr.OcrModel;
import com.agentsflex.core.ocr.OcrRequest;
import com.agentsflex.core.ocr.OcrResponse;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * AipOcrModel
 *
 * @author luoxiaodong
 */
public class AipOcrModel implements OcrModel {

    private final AipOcrModelConfig config;
    private final OkHttpClient client; // 复用 OkHttpClient（推荐）

    public AipOcrModel(AipOcrModelConfig config) {
        this.config = config;
        this.client = new OkHttpClient.Builder().build(); // 初始化客户端
    }

    @Override
    public OcrResponse recognize(OcrRequest ocrRequest) {
        File file = ocrRequest.getImage();
        String data = "";
        long startTime = System.currentTimeMillis();

        // 构建 multipart/form-data 请求体
        RequestBody body = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                file.getName(), // 使用文件名而非绝对路径
                RequestBody.create(file, MediaType.parse("application/octet-stream"))
            )
            .build();

        // 构建请求
        Request request = new Request.Builder()
            .url(config.getEndpoint())
            .post(body)
            .addHeader("Content-Type", "multipart/form-data")
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("OCR 请求失败，状态码: " + response.code());
            }

            // 自动关闭响应体（try-with-resources）
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String result = responseBody.string();
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.getInteger("code") == 200) {
                    data = jsonObject.getString("data");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("OCR 服务调用异常: " + e.getMessage(), e);
        }

        long usageTime = System.currentTimeMillis() - startTime;
        return new OcrResponse(data, usageTime);
    }
}
