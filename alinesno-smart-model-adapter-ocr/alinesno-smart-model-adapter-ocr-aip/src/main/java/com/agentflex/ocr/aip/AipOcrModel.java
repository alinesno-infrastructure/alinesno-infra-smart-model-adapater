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

    private final AipOcrModelConfig config ;

    public AipOcrModel(AipOcrModelConfig config) {
        this.config = config ;
    }

    @Override
    public OcrResponse recognize(OcrRequest ocrRequest) {

        File file = ocrRequest.getImage() ;

        String data = "" ;
        long startTime = System.currentTimeMillis() ;

        // 临时处理成OCR识别服务
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file",file.getAbsolutePath() ,
                RequestBody.create(MediaType.parse("application/octet-stream"), file))
            .build();
        Request request = new Request.Builder()
            .url(config.getEndpoint())
            .method("POST", body)
            .addHeader("Content-Type", "multipart/form-data")
            .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;
                String result = response.body().string();
                JSONObject jsonObject = JSONObject.parseObject(result);
                if (jsonObject.get("code").equals(200)) {
                    data = jsonObject.getString("data");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long usageTime = System.currentTimeMillis() - startTime ;
        return new OcrResponse(data , usageTime) ;
    }
}
