package com.agentsflex.llm.doubao.test;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.store.VectorData;
import com.agentsflex.llm.doubao.DoubaoLlm;
import com.agentsflex.llm.doubao.DoubaoLlmConfig;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.FunctionPrompt;
import org.junit.Test;

/**
 * 豆包模型接入方式：https://www.volcengine.com/docs/82379/1399008#b00dee71
 */
public class DoubaoTest {

    public static void main(String[] args) throws InterruptedException {
        DoubaoLlmConfig config = new DoubaoLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DOUBAO_LLM_API_KEY"));
        config.setApiSecret(System.getenv("ALINESNO_DOUBAO_API_SECRET"));
        config.setModel("ep-20250423153825-m748g");

        Llm llm = new DoubaoLlm(config);

        System.out.println("思考中......");

        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
            AiMessage message = response.getMessage();

            System.out.println(">>>> 推理内容: " + message.getFullReasoningContent());
            System.out.println(">>>> 结果内容: " + message.getFullContent());

        });

        Thread.sleep(10 * 1000);
    }

    @Test
    public void testChat() throws InterruptedException {

        DoubaoLlmConfig config = new DoubaoLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_DOUBAO_API_KEY"));
        config.setModel("ep-20250210101146-9ds66");

        System.out.println("思考中......");

        Llm llm = new DoubaoLlm(config);
        String output = llm.chat("你好.");
        System.out.println(output);

    }


    @Test
    public void testFunctionCalling() throws InterruptedException {
        DoubaoLlmConfig config = new DoubaoLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DOUBAO_API_KEY"));

        // config.setModel("qwen-turbo");

        Llm llm = new DoubaoLlm(config);

        FunctionPrompt prompt = new FunctionPrompt("今天北京的天气怎么样", WeatherFunctions.class);
        AiMessageResponse response = llm.chat(prompt);

        System.out.println(response.callFunctions());
        // "Today it will be dull and overcast in 北京"
    }


    @Test
    public void testEmbedding() throws InterruptedException {
        DoubaoLlmConfig config = new DoubaoLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DOUBAO_API_KEY"));
        config.setDefaultEmbeddingModel("ep-20250210102654-7x4q6");
        config.setDebug(true);

        Llm llm = new DoubaoLlm(config);
        VectorData vectorData = llm.embed(Document.of("花椰菜又称菜花、花菜，是一种常见的蔬菜。"));


        System.out.println(vectorData);
    }
}
