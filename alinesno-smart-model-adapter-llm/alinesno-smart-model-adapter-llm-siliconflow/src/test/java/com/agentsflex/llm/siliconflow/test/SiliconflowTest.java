package com.agentsflex.llm.siliconflow.test;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.store.VectorData;
import com.agentsflex.llm.siliconflow.SiliconflowLlm;
import com.agentsflex.llm.siliconflow.SiliconflowLlmConfig;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.FunctionPrompt;
import org.junit.Test;

public class SiliconflowTest {

    public static void main(String[] args) throws InterruptedException {
        SiliconflowLlmConfig config = new SiliconflowLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;
        config.setModel("Qwen/QwQ-32B");

        Llm llm = new SiliconflowLlm(config);
        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
            AiMessage message = response.getMessage();
            System.out.println(">>>> " + message);
        });

        Thread.sleep(10000);
    }


    @Test
    public void testFunctionCalling() throws InterruptedException {
        SiliconflowLlmConfig config = new SiliconflowLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;
        config.setModel("Qwen/QwQ-32B");

        Llm llm = new SiliconflowLlm(config);

        FunctionPrompt prompt = new FunctionPrompt("今天北京的天气怎么样", WeatherFunctions.class);
        AiMessageResponse response = llm.chat(prompt);

        System.out.println(response.callFunctions());
        // "Today it will be dull and overcast in 北京"
    }


    @Test
    public void testEmbedding() throws InterruptedException {
        SiliconflowLlmConfig config = new SiliconflowLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;
        config.setModel("Qwen/QwQ-32B");

        Llm llm = new SiliconflowLlm(config);
        VectorData vectorData = llm.embed(Document.of("test"));


        System.out.println(vectorData);
    }
}
