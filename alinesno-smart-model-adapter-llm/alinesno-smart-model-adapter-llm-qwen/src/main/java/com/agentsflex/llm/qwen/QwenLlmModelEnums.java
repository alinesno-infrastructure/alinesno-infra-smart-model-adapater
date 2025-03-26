package com.agentsflex.llm.qwen;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 千问的模型列表
 * @author luoxiaodong
 */
@Getter
public enum QwenLlmModelEnums {

    // qwen-max系列
    QWEN_MAX("qwen-max", "适合复杂任务处理", false),
    QWEN_MAX_LATEST("qwen-max-latest", "适合复杂任务处理", false),
    QWEN_MAX_2025_01_25("qwen-max-2025-01-25", "通义千问最佳模型", false),
    QWEN_MAX_2024_09_19("qwen-max-2024-09-19", "适合复杂任务处理", false),
    QWEN_MAX_2024_04_28("qwen-max-2024-04-28", "适合复杂任务处理", false),
    QWEN_MAX_2024_04_03("qwen-max-2024-04-03", "适合复杂任务处理", false),
    QWEN_MAX_2024_01_07("qwen-max-2024-01-07", "适合复杂任务处理", false),

    // qwen-plus系列
    QWEN_PLUS("qwen-plus", "适合中等复杂任务", false),
    QWEN_PLUS_LATEST("qwen-plus-latest", "适合中等复杂任务", false),
    QWEN_PLUS_2025_01_25("qwen-plus-2025-01-25", "适合中等复杂任务", false),
    QWEN_PLUS_2025_01_12("qwen-plus-2025-01-12", "适合中等复杂任务", false),
    QWEN_PLUS_2024_12_20("qwen-plus-2024-12-20", "适合中等复杂任务", false),
    QWEN_PLUS_2024_11_27("qwen-plus-2024-11-27", "适合中等复杂任务", false),
    QWEN_PLUS_2024_11_25("qwen-plus-2024-11-25", "适合中等复杂任务", false),
    QWEN_PLUS_2024_09_19("qwen-plus-2024-09-19", "适合中等复杂任务", false),
    QWEN_PLUS_2024_08_06("qwen-plus-2024-08-06", "适合中等复杂任务", false),
    QWEN_PLUS_2024_07_23("qwen-plus-2024-07-23", "适合中等复杂任务", false),
    QWEN_PLUS_2024_06_24("qwen-plus-2024-06-24", "适合中等复杂任务", false),
    QWEN_PLUS_2024_02_06("qwen-plus-2024-02-06", "适合中等复杂任务", false),

    // qwen-turbo系列
    QWEN_TURBO("qwen-turbo", "适合简单任务处理", false),
    QWEN_TURBO_LATEST("qwen-turbo-latest", "适合简单任务处理", false),
    QWEN_TURBO_2025_02_11("qwen-turbo-2025-02-11", "适合简单任务处理", false),
    QWEN_TURBO_2024_11_01("qwen-turbo-2024-11-01", "适合简单任务处理", false),
    QWEN_TURBO_2024_09_19("qwen-turbo-2024-09-19", "适合简单任务处理", false),
    QWEN_TURBO_2024_06_24("qwen-turbo-2024-06-24", "适合简单任务处理", false),
    QWEN_TURBO_2024_02_06("qwen-turbo-2024-02-06", "适合简单任务处理", false),

    // qwen-long
    QWEN_LONG("qwen-long", "适合长文本分析任务", false),

    // qwen-omni-turbo系列
    QWEN_OMNI_TURBO("qwen-omni-turbo", "多模态理解生成模型", false),
    QWEN_OMNI_TURBO_LATEST("qwen-omni-turbo-latest", "多模态理解生成模型", false),
    QWEN_OMNI_TURBO_2025_01_19("qwen-omni-turbo-2025-01-19", "多模态理解生成模型", false),

    // qwen-audio-turbo系列
    QWEN_AUDIO_TURBO("qwen-audio-turbo", "音频理解转录模型", false),
    QWEN_AUDIO_TURBO_LATEST("qwen-audio-turbo-latest", "音频理解转录模型", false),
    QWEN_AUDIO_TURBO_2024_12_04("qwen-audio-turbo-2024-12-04", "提升语音识别能力", false),
    QWEN_AUDIO_TURBO_2024_08_07("qwen-audio-turbo-2024-08-07", "音频理解转录模型", false),

    // qwen-audio-asr系列
    QWEN_AUDIO_ASR("qwen-audio-asr", "专用于语音识别模型", false),
    QWEN_AUDIO_ASR_LATEST("qwen-audio-asr-latest", "专用于语音识别模型", false),
    QWEN_AUDIO_ASR_2024_12_04("qwen-audio-asr-2024-12-04", "专用于语音识别模型", false),

    // qwen-math-plus系列
    QWEN_MATH_PLUS("qwen-math-plus", "数学解题语言模型", false),
    QWEN_MATH_PLUS_LATEST("qwen-math-plus-latest", "数学解题语言模型", false),
    QWEN_MATH_PLUS_2024_09_19("qwen-math-plus-2024-09-19", "数学解题语言模型", false),
    QWEN_MATH_PLUS_2024_08_16("qwen-math-plus-2024-08_16", "数学解题语言模型", false),

    // qwen-math-turbo系列
    QWEN_MATH_TURBO("qwen-math-turbo", "数学解题语言模型", false),
    QWEN_MATH_TURBO_LATEST("qwen-math-turbo-latest", "数学解题语言模型", false),
    QWEN_MATH_TURBO_2024_09_19("qwen-math-turbo-2024-09_19", "数学解题语言模型", false),

    // qwen-coder-plus系列
    QWEN_CODER_PLUS("qwen-coder-plus", "适用于代码相关任务", false),
    QWEN_CODER_PLUS_LATEST("qwen-coder-plus-latest", "适用于代码相关任务", false),
    QWEN_CODER_PLUS_2024_11_06("qwen-coder-plus-2024-11-06", "适用于代码相关任务", false),

    // qwen-coder-turbo系列
    QWEN_CODER_TURBO("qwen-coder-turbo", "适用于代码相关任务", false),
    QWEN_CODER_TURBO_LATEST("qwen-coder-turbo-latest", "适用于代码相关任务", false),
    QWEN_CODER_TURBO_2024_09_19("qwen-coder-turbo-2024-09_19", "适用于代码相关任务", false),

    // Qwen2.5系列
    QWEN2_5_14B_INSTRUCT_1M("qwen2.5-14b-instruct-1m", "适用于多种NLP任务", true),
    QWEN2_5_7B_INSTRUCT_1M("qwen2.5-7b-instruct-1m", "适用于多种NLP任务", true),
    QWEN2_5_72B_INSTRUCT("qwen2.5-72b-instruct", "适用于多种NLP任务", true),
    QWEN2_5_32B_INSTRUCT("qwen2.5-32b-instruct", "适用于多种NLP任务", true),
    QWEN2_5_14B_INSTRUCT("qwen2.5-14b-instruct", "适用于多种NLP任务", true),
    QWEN2_5_7B_INSTRUCT("qwen2.5-7b-instruct", "适用于多种NLP任务", true),
    QWEN2_5_3B_INSTRUCT("qwen2.5-3b-instruct", "适用于多种NLP任务", true),
    QWEN2_5_1_5B_INSTRUCT("qwen2.5-1.5b-instruct", "适用于多种NLP任务", true),
    QWEN2_5_0_5B_INSTRUCT("qwen2.5-0.5b-instruct", "适用于多种NLP任务", true),

    // Qwen2系列
    QWEN2_72B_INSTRUCT("qwen2-72b-instruct", "适用于NLP相关任务", true),
    QWEN2_57B_A14B_INSTRUCT("qwen2-57b-a14b-instruct", "适用于NLP相关任务", true),
    QWEN2_7B_INSTRUCT("qwen2-7b-instruct", "适用于NLP相关任务", true),
    QWEN2_1_5B_INSTRUCT("qwen2-1.5b-instruct", "适用于NLP相关任务", true),
    QWEN2_0_5B_INSTRUCT("qwen2-0.5b-instruct", "适用于NLP相关任务", true),

    // Qwen1.5系列
    QWEN1_5_110B_CHAT("qwen1.5-110b-chat", "适用于聊天相关任务", true),
    QWEN1_5_72B_CHAT("qwen1.5-72b-chat", "适用于聊天相关任务", true),
    QWEN1_5_32B_CHAT("qwen1.5-32b-chat", "适用于聊天相关任务", true),
    QWEN1_5_14B_CHAT("qwen1.5-14b-chat", "适用于聊天相关任务", true),
    QWEN1_5_7B_CHAT("qwen1.5-7b-chat", "适用于聊天相关任务", true),
    QWEN1_5_1_8B_CHAT("qwen1.5-1.8b-chat", "适用于聊天相关任务", true),
    QWEN1_5_0_5B_CHAT("qwen1.5-0.5b-chat", "适用于聊天相关任务", true),

    // Qwen系列
    QWEN_72B_CHAT("qwen-72b-chat", "适用于聊天相关任务", true),
    QWEN_14B_CHAT("qwen-14b-chat", "适用于聊天相关任务", true),
    QWEN_7B_CHAT("qwen-7b-chat", "适用于聊天相关任务", true),
    QWEN_1_8B_CHAT("qwen-1.8b-chat", "适用于聊天相关任务", true),
    QWEN_1_8B_LONGCONTEXT_CHAT("qwen-1.8b-longcontext-chat", "适用于聊天相关任务", true),

    // Qwen-Math系列
    QWEN2_5_MATH_72B_INSTRUCT("qwen2.5-math-72b-instruct", "数学解题模型", true),
    QWEN2_5_MATH_7B_INSTRUCT("qwen2.5-math-7b-instruct", "数学解题模型", true),
    QWEN2_5_MATH_1_5B_INSTRUCT("qwen2.5-math-1.5b-instruct", "数学解题模型", true),
    QWEN2_MATH_72B_INSTRUCT("qwen2-math-72b-instruct", "数学解题模型", true),
    QWEN2_MATH_7B_INSTRUCT("qwen2-math-7b-instruct", "数学解题模型", true),
    QWEN2_MATH_1_5B_INSTRUCT("qwen2-math-1.5b-instruct", "数学解题模型", true),

    // Qwen-Coder系列
    QWEN2_5_CODER_32B_INSTRUCT("qwen2.5-coder-32b-instruct", "适合代码任务", true),
    QWEN2_5_CODER_14B_INSTRUCT("qwen2.5-coder-14b-instruct", "适合代码任务", true),
    QWEN2_5_CODER_7B_INSTRUCT("qwen2.5-coder-7b-instruct", "适合代码任务", true),
    QWEN2_5_CODER_3B_INSTRUCT("qwen2.5-coder-3b-instruct", "适合代码任务", true),
    QWEN2_5_CODER_1_5B_INSTRUCT("qwen2.5-coder-1.5b-instruct", "适合代码任务", true),
    QWEN2_5_CODER_0_5B_INSTRUCT("qwen2.5-coder-0.5b-instruct", "适合代码任务", true),

    // DeepSeek系列
    DEEPSEEK_R1("deepseek-r1", "多任务表现优", false),
    DEEPSEEK_V3("deepseek-v3", "多能力表现佳", false),
    DEEPSEEK_R1_DISTILL_QWEN_1_5B("deepseek-r1-distill-qwen-1.5b", "适用推理任务", false),
    DEEPSEEK_R1_DISTILL_QWEN_7B("deepseek-r1-distill-qwen-7b", "适用推理任务", false),
    DEEPSEEK_R1_DISTILL_QWEN_14B("deepseek-r1-distill-qwen-14b", "适用推理任务", false),
    DEEPSEEK_R1_DISTILL_QWEN_32B("deepseek-r1-distill-qwen-32b", "适用推理任务", false),
    DEEPSEEK_R1_DISTILL_LLAMA_8B("deepseek-r1-distill-llama-8b", "适用推理任务", false),
    DEEPSEEK_R1_DISTILL_LLAMA_70B("deepseek-r1-distill-llama-70b", "适用推理任务", false) ;

    private final String modelName;
    private final String description;
    private final boolean isOpenSource;

    QwenLlmModelEnums(String modelName, String description, boolean isOpenSource) {
        this.modelName = modelName;
        this.description = description;
        this.isOpenSource = isOpenSource;
    }

    public boolean isOpenSource() {
        return isOpenSource;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();
        for (QwenLlmModelEnums model : QwenLlmModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("description", model.getDescription());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }


}
