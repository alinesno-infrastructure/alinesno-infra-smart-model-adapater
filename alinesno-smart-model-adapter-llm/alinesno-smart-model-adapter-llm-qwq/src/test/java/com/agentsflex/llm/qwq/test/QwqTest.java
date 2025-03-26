package com.agentsflex.llm.qwq.test;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.store.VectorData;
import com.agentsflex.llm.qwq.QwqLlm;
import com.agentsflex.llm.qwq.QwqLlmConfig;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.FunctionPrompt;
import org.junit.Test;

public class QwqTest {

    public static void main(String[] args) throws InterruptedException {
        QwqLlmConfig config = new QwqLlmConfig();

        //https://bailian.console.aliyun.com/?apiKey=1#/api-key
//        config.setApiKey(System.getenv("ALINESNO_DEEPSEEK_API_KEY"));

        // config.setModel("deepseek-chat");

        // 硅基流动
//        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY"));
//        config.setModel("deepseek-ai/DeepSeek-R1");
//        config.setModel("deepseek-ai/DeepSeek-V3");
//        config.setEndpoint("https://api.siliconflow.cn/v1/");

        // 阿里百炼
        config.setEndpoint("https://dashscope.aliyuncs.com/compatible-mode/v1");
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY"));
        config.setModel("qwq-32b");

        Llm llm = new QwqLlm(config);

        System.out.println("思考中......");

        llm.chatStream("请写一个小兔子战胜大灰狼的故事", (context, response) -> {
            AiMessage message = response.getMessage();

            System.out.println(">>>> 推理内容: " + message.getFullReasoningContent());
            System.out.println(">>>> 结果内容: " + message.getFullContent());
            System.out.println(">>>> Token数: " + message.getTotalTokens());

        });

        Thread.sleep(10 * 1000);
    }


    @Test
    public void testFunctionCalling() throws InterruptedException {
        QwqLlmConfig config = new QwqLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DEEPSEEK_API_KEY"));

        // config.setModel("qwen-turbo");

        Llm llm = new QwqLlm(config);

        FunctionPrompt prompt = new FunctionPrompt("今天北京的天气怎么样", WeatherFunctions.class);
        AiMessageResponse response = llm.chat(prompt);

        System.out.println(response.callFunctions());
        // "Today it will be dull and overcast in 北京"
    }


    @Test
    public void testEmbedding() throws InterruptedException {
        QwqLlmConfig config = new QwqLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_DEEPSEEK_API_KEY"));

        // config.setModel("qwen-turbo");

        Llm llm = new QwqLlm(config);
        VectorData vectorData = llm.embed(Document.of("test"));


        System.out.println(vectorData);
    }
}
