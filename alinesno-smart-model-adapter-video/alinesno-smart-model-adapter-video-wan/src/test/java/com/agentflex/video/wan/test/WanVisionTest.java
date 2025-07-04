package com.agentflex.video.wan.test;

import com.agentflex.video.wan.WanVision;
import com.agentflex.video.wan.WanVisionConfig;
import com.agentflex.video.wan.WanVisionModelEnums;
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

        textPrompt = "摩托车第一人称视角（FPV）快速在赛道上奔驰，尘土飞扬，路途颠簸，高速运动带来的模糊效果增强了速度感，让观众仿佛亲身参与这场冒险，动态模糊，速度感，呼吸感" ;
        textPrompt = "远景拍摄，一个太空中的中国女人孤独地坐着，双手慢慢抱着自己的膝盖，镜头切换拍摄女人的面部特写，透过头盔，她脸上孤独悲伤的表情，真实摄影风格。";
        textPrompt = "一个穿着光滑的黑色和红色发光的Tron套装的人骑着一辆红黑色的光轮摩托车，车轮飞速旋转，骑手做出一个急转弯，凭借精准和技巧繁忙的街道上穿行，镜头环绕到摩托车身后，留下火光和光的痕迹，镜头捕捉到动态的运动和能量，赛博朋克城市、高速追逐、发光的轮毂、动感模糊、荧光灯、动态的镜头角度、紧张的气氛" ;

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
