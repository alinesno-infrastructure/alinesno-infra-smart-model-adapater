package com.agentsflex.llm.gitee;

import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.prompt.FunctionPrompt;
import lombok.SneakyThrows;
import org.junit.Test;

public class GiteeAITest {

    @SneakyThrows
    public static void main(String[] args) {
        GiteeAiLlmConfig config = new GiteeAiLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_GITEE_API_KEY"));
        config.setModel("DeepSeek-R1");  // TODO fix:处理和优化推理模型适配

        GiteeAiLlm llm = new GiteeAiLlm(config);
//        String result = llm.chat("你好");
//        System.out.println(result);

        llm.chatStream("9.10和9.9哪个更大", (context, response) -> {
            AiMessage message = response.getMessage();

            System.out.println(">>>> 推理内容: " + message.getReasoningContent());
            System.out.println(">>>> 结果内容: " + message.getFullContent());

        });

        Thread.sleep(50 * 1000);
    }

    @Test
    public void testFunctionCalling() {
        GiteeAiLlmConfig config = new GiteeAiLlmConfig();
        config.setApiKey(System.getenv("ALINESNO_GITEE_API_KEY"));
        config.setModel("Qwen2.5-72B-Instruct");

        GiteeAiLlm llm = new GiteeAiLlm(config);


        FunctionPrompt prompt = new FunctionPrompt("What's the weather like in Beijing?", WeatherFunctions.class);
        AiMessageResponse response = llm.chat(prompt);

        System.out.println(response.callFunctions());
    }
}
