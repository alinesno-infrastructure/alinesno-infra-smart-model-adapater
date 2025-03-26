package com.agentflex.ocr.aip;

import com.agentsflex.core.ocr.OcrRequest;
import com.agentsflex.core.ocr.OcrResponse;
import org.junit.Test;

import java.io.File;

public class AipOcrModelTest {

    @Test
    public void testRecognize() {
        AipOcrModelConfig config = new AipOcrModelConfig();

        AipOcrModel ocrModel = new AipOcrModel(config);

        OcrRequest ocrRequest = new OcrRequest();
        ocrRequest.setImage(new File("C:\\Users\\luoandon\\Pictures\\微信图片_20240415154642.png"));

        OcrResponse response = ocrModel.recognize(ocrRequest);

        System.out.println(response.getResults());
        System.out.println(response.getUsageTime());
    }

}
