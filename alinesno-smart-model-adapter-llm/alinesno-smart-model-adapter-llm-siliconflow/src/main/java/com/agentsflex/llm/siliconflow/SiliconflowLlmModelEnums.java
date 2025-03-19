package com.agentsflex.llm.siliconflow;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 向量模型
 */
@Getter
@AllArgsConstructor
public enum SiliconflowLlmModelEnums {

    DEEPSEEK_AI_DEEPSEEK_R1_DISTILL_LLAMA_8B("deepseek-ai/DeepSeek-R1-Distill-Llama-8B", "中规语言处理任务", false),
    DEEPSEEK_AI_DEEPSEEK_R1_DISTILL_QWEN_7B("deepseek-ai/DeepSeek-R1-Distill-Qwen-7B", "中能语言处理任务", false),
    DEEPSEEK_AI_DEEPSEEK_R1_DISTILL_QWEN_1_5B("deepseek-ai/DeepSeek-R1-Distill-Qwen-1.5B", "适中规模语言任务", false),
    QWEN_QWEN2_5_7B_INSTRUCT("Qwen/Qwen2.5-7B-Instruct", "多类指令处理任务", false),
    QWEN_QWEN2_5_CODER_7B_INSTRUCT("Qwen/Qwen2.5-Coder-7B-Instruct", "代码相关语言任务", false),
    INTERNLM_INTERNLM2_5_7B_CHAT("internlm/internlm2_5-7b-chat", "聊天对话语言任务", false),
    QWEN_QWEN2_1_5B_INSTRUCT("Qwen/Qwen2-1.5B-Instruct", "小规指令处理任务", false),
    THUDM_GLM_4_9B_CHAT("THUDM/glm-4-9b-chat", "复杂聊天对话任务", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();
        for (SiliconflowLlmModelEnums model : SiliconflowLlmModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }
}
