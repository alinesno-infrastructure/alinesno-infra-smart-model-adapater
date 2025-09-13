package com.agentsflex.image.doubao.test;

import com.agentsflex.core.image.GenerateImageRequest;
import com.agentsflex.core.image.Image;
import com.agentsflex.core.image.ImageResponse;
import com.agentsflex.image.doubao.DoubaoImageModel;
import com.agentsflex.image.doubao.DoubaoImageModelConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

@Slf4j
public class DoubaoImageModelTest {

    @Test
    public void testGenImage(){
        DoubaoImageModelConfig config = new DoubaoImageModelConfig();
        config.setApiKey(System.getenv("ALINESNO_DOUBAO_GPT_API_KEY"));

        System.out.println("config = " + config) ;

        DoubaoImageModel openAiImageModel = new DoubaoImageModel(config);

        GenerateImageRequest request = new GenerateImageRequest();
//        request.setPrompt("画面左侧是一个人皱着眉头，有些生气的表情，旁边气泡中文字：“有人说我说话直别介意怎么办？”，字体为黑色描边的白色字体。这个人站在一个普通的室内场景中，身后有简单的沙发和茶几。 画面右侧是另一个人双手抱在胸前，一脸不屑，旁边气泡中文字：“我怼人你也别介意。”，字体为红色立体的抖音风格字体。这个人站在同样的室内场景中，但位置与左侧的人稍有间隔，身后也有简单的沙发和茶几。整个场景色调较为明亮，但两个人的情绪让画面稍显紧张。") ;
        request.setPrompt("城市治理微服务架构图") ; //画面左侧是一个人皱着眉头，有些生气的表情，旁边气泡中文字：“有人说我说话直别介意怎么办？”，字体为黑色描边的白色字体。这个人站在一个普通的室内场景中，身后有简单的沙发和茶几。 画面右侧是另一个人双手抱在胸前，一脸不屑，旁边气泡中文字：“我怼人你也别介意。”，字体为红色立体的抖音风格字体。这个人站在同样的室内场景中，但位置与左侧的人稍有间隔，身后也有简单的沙发和茶几。整个场景色调较为明亮，但两个人的情绪让画面稍显紧张。") ;
        request.setNegativePrompt("人物");
        request.setN(3);

        request.setSize(512,512);
        ImageResponse generate = openAiImageModel.generate(request);
        if (generate != null && generate.getImages() != null){
            int index = 0;
            for (Image image : generate.getImages()) {
                image.writeToFile(new File("/Users/luodong/Desktop/demo/image_" + UUID.randomUUID() + "_" +(index++)+".jpg"));
            }
        }

        System.out.println(generate);
    }

}
