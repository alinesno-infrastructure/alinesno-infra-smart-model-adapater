package com.agentsflex.image.doubao;

import com.agentsflex.core.image.*;
import com.alibaba.fastjson.JSONObject;
import com.volcengine.ark.runtime.model.images.generation.GenerateImagesRequest;
import com.volcengine.ark.runtime.model.images.generation.ImagesResponse;
import com.volcengine.ark.runtime.service.ArkService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DoubaoImageModel implements ImageModel {

    private final DoubaoImageModelConfig config;

    public DoubaoImageModel(DoubaoImageModelConfig config) {
        this.config = config;
    }

    @Override
    public ImageResponse generate(GenerateImageRequest request) {

        // 初始化视觉服务并设置密钥
//        IVisualService visualService = VisualServiceImpl.getInstance();
//        visualService.setAccessKey(config.getAccessKey());
//        visualService.setSecretKey(config.getSecretKey());

//        // 获取请求 JSON 对象
//        JSONObject jsonObject = getJsonObject(request);

        try {
            String apiKey = config.getApiKey();
            ConnectionPool connectionPool = new ConnectionPool(5, 1, TimeUnit.SECONDS);
            Dispatcher dispatcher = new Dispatcher();
            ArkService service = ArkService.builder().dispatcher(dispatcher).connectionPool(connectionPool).apiKey(apiKey).build();

            GenerateImagesRequest generateRequest = GenerateImagesRequest.builder()
                .model(config.getModel())
                .prompt(request.getPrompt())
                .build();

            ImagesResponse imagesResponse = service.generateImages(generateRequest);
            System.out.println(imagesResponse.getData().get(0).getUrl());

//            String responseJson = JSONObject.toJSONString(response);
//            log.info("responseJson: {}", responseJson);

//            // 解析 responseJson 为 JSONObject
//            ResponseWrapper responseWrapper = JSON.parseObject(responseJson, ResponseWrapper.class);
            ImagesResponse.Error error = imagesResponse.getError() ;

            if (error == null) {
                // 通知回调
                log.info("图片已经生成 :-)");

                // 获取图片链接
                List<ImagesResponse.Image> imageUrls = imagesResponse.getData() ;
                if (imageUrls != null) {
                    ImageResponse imageResponse = new ImageResponse();
                    for (int i = 0; i < imageUrls.size() ; i++) {
                        ImagesResponse.Image img = imageUrls.get(i) ;
                        // 下载图片到本地临时文件
                        File tempFile = new File("/tmp/image_" + i + ".png");
                        byte[] imageBytes = downloadFileToBytes(img.getUrl());
                        if (imageBytes != null) {
                            imageResponse.addImage(imageBytes);
                        }
                    }
                    return imageResponse;
                }
            } else {
                log.error("请求失败或没有返回有效的图片链接");
            }
        } catch (Exception e) {
            log.error("图片生成过程中出现异常", e);
        }

        return ImageResponse.error("图片生成失败");
    }

    // 新增下载图片到字节数组的方法
    private byte[] downloadFileToBytes(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request httpRequest = new Request.Builder()
            .url(url)
            .build();

        try (Response httpResponse = client.newCall(httpRequest).execute()) {
            if (httpResponse.isSuccessful()) {
                return httpResponse.body().bytes();
            } else {
                log.error("下载图片失败，响应码: {}", httpResponse.code());
                return null;
            }
        }
    }

    private JSONObject getJsonObject(GenerateImageRequest request) {
        JSONObject logoInfo = new JSONObject();
        logoInfo.put("add_logo", true);
        logoInfo.put("position", 0);
        logoInfo.put("language", 0);
        logoInfo.put("opacity", 0.3);
        logoInfo.put("logo_text_content", "AIP智能体平台");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("req_key", "high_aes_general_v21_L");
        jsonObject.put("prompt", request.getPrompt());
        jsonObject.put("width", request.getWidth());
        jsonObject.put("height", request.getHeight());
        jsonObject.put("return_url", true);
        jsonObject.put("logo_info", logoInfo);

        return jsonObject;
    }

    private void downloadFile(String url, File destination) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request httpRequest = new Request.Builder()
            .url(url)
            .build();

        try (Response httpResponse = client.newCall(httpRequest).execute()) {
            if (httpResponse.isSuccessful()) {
                InputStream inputStream = httpResponse.body().byteStream();
                Files.copy(inputStream, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } else {
                log.error("下载图片失败，响应码: {}", httpResponse.code());
            }
        }
    }

    @Override
    public ImageResponse edit(EditImageRequest request) {
        return ImageResponse.error("DoubaoImageModel does not support edit image.");
    }

    @Override
    public ImageResponse vary(VaryImageRequest request) {
        return ImageResponse.error("DoubaoImageModel does not support vary image.");
    }

}
