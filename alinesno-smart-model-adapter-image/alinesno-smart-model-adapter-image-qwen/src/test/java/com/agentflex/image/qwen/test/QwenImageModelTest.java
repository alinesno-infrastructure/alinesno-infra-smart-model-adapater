package com.agentflex.image.qwen.test;

import com.agentsflex.core.image.*;
import com.agentsflex.image.qwen.QwenImageModel;
import com.agentsflex.image.qwen.QwenImageModelConfig;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

public class QwenImageModelTest {

    @Test
    public void testGenImage(){
        QwenImageModelConfig config = new QwenImageModelConfig();
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;

        QwenImageModel openAiImageModel = new QwenImageModel(config);

        GenerateImageRequest request = new GenerateImageRequest();
        request.setPrompt("一间有着精致窗户的花店，漂亮的木质门，摆放着花朵");
        request.setNegativePrompt("人物");

        request.setN(3);

        request.setSize(512,512);
        ImageResponse generate = openAiImageModel.generate(request);
        if (generate != null && generate.getImages() != null){
            int index = 0;
            for (Image image : generate.getImages()) {
                image.writeToFile(new File("/Users/luodong/Desktop/demo-images/image_" + UUID.randomUUID() + "_" +(index++)+".jpg"));
            }
        }

        System.out.println(generate);
    }

//    @Test
//    public void testUnderstand(){
//        QwenImageModelConfig config = new QwenImageModelConfig();
//        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;
//        config.setModel("qwen-vl-plus");
//
//        QwenImageModel openAiImageModel = new QwenImageModel(config);
//
//        UnderstandImageRequest request = new UnderstandImageRequest();
//        request.setImageUrl("http://data.linesno.com/demo.png") ;
//
//        UnderstandImageResponse understand = openAiImageModel.understand(request);
//        System.out.println(understand.getResponse());
//    }

}
