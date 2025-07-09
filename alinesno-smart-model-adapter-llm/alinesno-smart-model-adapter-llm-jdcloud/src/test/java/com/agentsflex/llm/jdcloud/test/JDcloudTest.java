package com.agentsflex.llm.jdcloud.test;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.store.VectorData;
import com.agentsflex.llm.jdcloud.JDcloudLlm;
import com.agentsflex.llm.jdcloud.JDcloudLlmConfig;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.FunctionPrompt;
import org.junit.Test;

public class JDcloudTest {

    public static void main(String[] args) throws InterruptedException {
        JDcloudLlmConfig config = new JDcloudLlmConfig();

        config.setEndpoint(System.getenv("ALINESNO_JDCLOUD_END_POINT"));
        config.setApiKey(System.getenv("ALINESNO_JDCLOUD_API_KEY")) ;
        config.setModel("DeepsSeek-R1");

        config.setDebug(true);

        Llm llm = new JDcloudLlm(config);
        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
            AiMessage message = response.getMessage();
            System.out.println(message.getStatus() + "推理>>>> " + message.getFullReasoningContent());
            System.out.println(message.getStatus() +  "内容>>>> " + message.getFullContent());
        });

        Thread.sleep(60*1000);
    }


    @Test
    public void testFunctionCalling() throws InterruptedException {
        JDcloudLlmConfig config = new JDcloudLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_JDCLOUD_API_KEY")) ;
        config.setModel("qwen-turbo");

        Llm llm = new JDcloudLlm(config);

        FunctionPrompt prompt = new FunctionPrompt("今天北京的天气怎么样", WeatherFunctions.class);
        AiMessageResponse response = llm.chat(prompt);

        System.out.println(response.callFunctions());
        // "Today it will be dull and overcast in 北京"
    }


    @Test
    public void testEmbedding() throws InterruptedException {
        JDcloudLlmConfig config = new JDcloudLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_JDCLOUD_API_KEY")) ;
        config.setModel("qwen-turbo");

        Llm llm = new JDcloudLlm(config);
        VectorData vectorData = llm.embed(Document.of("test"));


        System.out.println(vectorData);
    }
}
