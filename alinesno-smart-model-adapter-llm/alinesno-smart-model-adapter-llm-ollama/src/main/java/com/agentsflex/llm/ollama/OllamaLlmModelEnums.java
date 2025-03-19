package com.agentsflex.llm.ollama;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ollama 大语言模型枚举类
 */
@Getter
@AllArgsConstructor
public enum OllamaLlmModelEnums {

    DEEPSEEK_R1_1_5B("deepseek-r1:1.5b", "小规推理生成任务", false),
    DEEPSEEK_R1_7B("deepseek-r1:7b", "中等规模语言任务", false),
    DEEPSEEK_R1_8B("deepseek-r1:8b", "中等规模语言任务", false),
    DEEPSEEK_R1_14B("deepseek-r1:14b", "较高要求语言任务", false),
    DEEPSEEK_R1_32B("deepseek-r1:32b", "高要求语言任务", false),

    LLAMA2("llama2", "文本生成对话任务", false),
    LLAMA2_13B("llama2:13b", "文本生成对话任务", false),
    LLAMA2_70B("llama2:70b", "文本生成对话任务", false),
    LLAMA2_CHINESE_13B("llama2-chinese:13b", "中文对话处理任务", false),
    LLAMA3_8B("llama3:8b", "多类语言处理任务", false),
    LLAMA3_70B("llama3:70b", "多类语言处理任务", false),

    QWEN_0_5B("qwen:0.5b", "小规语言处理任务", false),
    QWEN_1_8B("qwen:1.8b", "多类语言处理任务", false),
    QWEN_4B("qwen:4b", "多类语言处理任务", false),
    QWEN_7B("qwen:7b", "多类语言处理任务", false),
    QWEN_14B("qwen:14b", "高要求语言任务", false),
    QWEN_32B("qwen:32b", "高要求语言任务", false),
    QWEN_72B("qwen:72b", "高要求语言任务", false),
    QWEN_110B("qwen:110b", "极高要求语言任务", false),

    QWEN2_72B_INSTRUCT("qwen2:72b-instruct", "多类指令处理任务", false),
    QWEN2_57B_A14B_INSTRUCT("qwen2:57b-a14b-instruct", "多类指令处理任务", false),
    QWEN2_7B_INSTRUCT("qwen2:7b-instruct", "多类指令处理任务", false),
    QWEN2_5_72B_INSTRUCT("qwen2.5:72b-instruct", "多类指令处理任务", false),
    QWEN2_5_32B_INSTRUCT("qwen2.5:32b-instruct", "多类指令处理任务", false),
    QWEN2_5_14B_INSTRUCT("qwen2.5:14b-instruct", "多类指令处理任务", false),
    QWEN2_5_7B_INSTRUCT("qwen2.5:7b-instruct", "多类指令处理任务", false),
    QWEN2_5_1_5B_INSTRUCT("qwen2.5:1.5b-instruct", "多类指令处理任务", false),
    QWEN2_5_0_5B_INSTRUCT("qwen2.5:0.5b-instruct", "多类指令处理任务", false),
    QWEN2_5_3B_INSTRUCT("qwen2.5:3b-instruct", "多类指令处理任务", false),

    PHI3("phi3", "轻量语言处理任务", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();
        for (OllamaLlmModelEnums model : OllamaLlmModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }
}
