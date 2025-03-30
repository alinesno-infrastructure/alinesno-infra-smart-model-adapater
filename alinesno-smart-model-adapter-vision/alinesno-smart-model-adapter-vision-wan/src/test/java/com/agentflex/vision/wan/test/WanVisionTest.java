package com.agentflex.vision.wan.test;

import com.agentflex.vision.wan.WanVision;
import com.agentflex.vision.wan.WanVisionConfig;
import com.agentflex.vision.wan.WanVisionModelEnums;
import com.agentsflex.core.vision.BaseVision;
import com.agentsflex.core.vision.GenVideoResponse;
import org.junit.Test;

import java.io.IOException;

public class WanVisionTest {

    @Test
    public void testTextToVideo() throws IOException {

        WanVisionConfig config = new WanVisionConfig();
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;
        config.setModel(WanVisionModelEnums.WANX2_1_T2V_TURBO.getModelName());

        BaseVision<WanVisionConfig> vision = new WanVision(config);

        String textPrompt = "猫咪看向镜头，双手挥舞释放魔法，身上的衣服随风飞舞，围绕主体运镜";
        GenVideoResponse genVideoResponse = vision.textToVideo(textPrompt , null) ;
        System.out.println(genVideoResponse);

        System.in.read();
    }

    @Test
    public void testImageToVideo() throws IOException {

        WanVisionConfig config = new WanVisionConfig();
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;

        config.setModel(WanVisionModelEnums.WANX2_1_I2V_TURBO.getModelName());
        BaseVision<WanVisionConfig> vision = new WanVision(config);

        String textPrompt = "蒙娜丽莎在哈哈大笑";
        String imageUrl = "https://s2-111386.kwimgs.com/bs2/mmu-aiplatform-temp/kling/20240620/1.jpeg";
        GenVideoResponse genVideoResponse = vision.imageToVideo(textPrompt, imageUrl , null) ;

        System.out.println(genVideoResponse);
        System.in.read();
    }

}
