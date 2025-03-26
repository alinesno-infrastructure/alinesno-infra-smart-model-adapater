package com.agentflex.vision.qwen.test;

import com.agentflex.vision.qwen.QwenVision;
import com.agentflex.vision.qwen.QwenVisionConfig;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.message.AiMessage;

public class QwenTest {

    public static void main(String[] args) throws InterruptedException {
        QwenVisionConfig config = new QwenVisionConfig();

        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;
        config.setModel("qwen-plus");

        Llm llm = new QwenVision(config);
        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
            AiMessage message = response.getMessage();
            System.out.println(">>>> " + message);
        });

        Thread.sleep(10000);
    }

}
