package com.agentflex.ocr.aip;

import com.agentflex.ocr.aliyun.AliyunOcrModel;
import com.agentflex.ocr.aliyun.AliyunOcrModelConfig;
import com.agentsflex.core.ocr.OcrRequest;
import com.agentsflex.core.ocr.OcrResponse;
import org.junit.Test;

import java.io.File;

public class AipOcrModelTest {

    @Test
    public void testRecognize() {
        AliyunOcrModelConfig config = new AliyunOcrModelConfig();

        AliyunOcrModel ocrModel = new AliyunOcrModel(config);

        OcrRequest ocrRequest = new OcrRequest();
        ocrRequest.setImage(new File("C:\\Users\\luoandon\\Pictures\\微信图片_20240415154642.png"));

        OcrResponse response = ocrModel.recognize(ocrRequest);

        System.out.println(response.getResults());
        System.out.println(response.getUsageTime());
    }

}
