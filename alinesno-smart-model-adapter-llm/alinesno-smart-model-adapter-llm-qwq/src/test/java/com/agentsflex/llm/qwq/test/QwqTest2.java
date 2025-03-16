package com.agentsflex.llm.qwq.test;

import com.agentsflex.core.llm.Llm;
import com.agentsflex.llm.qwq.QwqLlm;
import com.agentsflex.llm.qwq.QwqLlmConfig;

public class QwqTest2 {

    public static void main(String[] args) throws InterruptedException {
        QwqLlmConfig config = new QwqLlmConfig();

        // 阿里百炼
        config.setEndpoint("https://dashscope.aliyuncs.com/compatible-mode/v1");
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY"));
        config.setModel("deepseek-r1");

        Llm llm = new QwqLlm(config);

        System.out.println("思考中......");

        String response = llm.chat("你是谁?");
        System.out.println(response);

//        AiMessageResponse output = llm.chat("你是谁.") ;
//        System.out.println("output = " + output);

//        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
//            AiMessage message = response.getMessage();
//
//            if(StringUtil.hasText(message.getReasoningContent())){
//                System.out.println(">>>> 推理内容: " + message.getReasoningContent());
//            }
//            if(StringUtil.hasText(message.getContent())){
//                System.out.println(">>>> 结果内容: " + message.getContent());
//            }
//
//        });

    }

}
