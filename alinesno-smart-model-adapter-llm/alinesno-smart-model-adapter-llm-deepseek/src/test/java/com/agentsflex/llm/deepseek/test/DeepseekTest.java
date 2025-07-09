package com.agentsflex.llm.deepseek.test;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.store.VectorData;
import com.agentsflex.llm.deepseek.DeepseekLlm;
import com.agentsflex.llm.deepseek.DeepseekLlmConfig;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.FunctionPrompt;
import org.junit.Test;

public class DeepseekTest {

    public static void main(String[] args) throws InterruptedException {
        DeepseekLlmConfig config = new DeepseekLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DEEPSEEK_API_KEY"));
        config.setModel("deepseek-chat");

        Llm llm = new DeepseekLlm(config);

        System.out.println("思考中......");

        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
            AiMessage message = response.getMessage();

            System.out.println(">>>> 推理内容: " + message.getFullReasoningContent());
            System.out.println(">>>> 结果内容: " + message.getFullContent());

        });

        Thread.sleep(10 * 1000);
    }


    @Test
    public void testFunctionCalling() throws InterruptedException {
        DeepseekLlmConfig config = new DeepseekLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DEEPSEEK_API_KEY"));

        // config.setModel("qwen-turbo");

        Llm llm = new DeepseekLlm(config);

        FunctionPrompt prompt = new FunctionPrompt("今天北京的天气怎么样", WeatherFunctions.class);
        AiMessageResponse response = llm.chat(prompt);

        System.out.println(response.callFunctions());
        // "Today it will be dull and overcast in 北京"
    }


    @Test
    public void testEmbedding() throws InterruptedException {
        DeepseekLlmConfig config = new DeepseekLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DEEPSEEK_API_KEY"));

        // config.setModel("qwen-turbo");

        Llm llm = new DeepseekLlm(config);
        VectorData vectorData = llm.embed(Document.of("test"));


        System.out.println(vectorData);
    }
}
