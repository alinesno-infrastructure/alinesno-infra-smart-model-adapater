package com.agentflex.vision.qwen.test;

import com.agentflex.vision.qwen.QwenVision;
import com.agentflex.vision.qwen.QwenVisionConfig;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.ImagePrompt;

public class QwenVisionTest {

    public static void main(String[] args) throws InterruptedException {
        QwenVisionConfig config = new QwenVisionConfig();

        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;
        Llm llm = new QwenVision(config);

        ImagePrompt imagePrompt = new ImagePrompt("这是什么" , "https://dashscope.oss-cn-beijing.aliyuncs.com/images/dog_and_girl.jpeg");

        llm.chatStream(imagePrompt, (context, response) -> {
            AiMessage message = response.getMessage();
            System.out.println(">>>> " + message.getFullContent());
        });

        Thread.sleep(30000);
    }

}
