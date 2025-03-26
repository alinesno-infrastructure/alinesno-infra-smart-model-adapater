package com.agentflex.vision.qwen;

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
public enum QwenVisionModelEnums {

    // qwen-omni-turbo系列
    QWEN_OMNI_TURBO("qwen-omni-turbo", "多模态理解生成模型", false),
    QWEN_OMNI_TURBO_LATEST("qwen-omni-turbo-latest", "多模态理解生成模型", false),
    QWEN_OMNI_TURBO_2025_01_19("qwen-omni-turbo-2025-01-19", "多模态理解生成模型", false),

    // qwen-vl-max系列
    QWEN_VL_MAX("qwen-vl-max", "图像理解文本生成", false),
    QWEN_VL_MAX_LATEST("qwen-vl-max-latest", "图像理解文本生成", false),
    QWEN_VL_MAX_2025_01_25("qwen-vl-max-2025-01-25", "增强图像视频理解", false),
    QWEN_VL_MAX_2024_12_30("qwen-vl-max-2024-12-30", "图像理解文本生成", false),
    QWEN_VL_MAX_2024_11_19("qwen-vl-max-2024-11-19", "图像理解文本生成", false),
    QWEN_VL_MAX_2024_10_30("qwen-vl-max-2024-10-30", "图像理解文本生成", false),
    QWEN_VL_MAX_2024_08_09("qwen-vl-max-2024-08-09", "增强图像理解能力", false),
    QWEN_VL_MAX_2024_02_01("qwen-vl-max-2024-02-01", "图像理解文本生成", false),

    // qwen-vl-plus系列
    QWEN_VL_PLUS("qwen-vl-plus", "视觉任务卓越性能", false),
    QWEN_VL_PLUS_LATEST("qwen-vl-plus-latest", "视觉任务卓越性能", false),
    QWEN_VL_PLUS_2025_01_25("qwen-vl-plus-2025-01-25", "增强图像视频理解", false),
    QWEN_VL_PLUS_2025_01_02("qwen-vl-plus-2025-01-02", "视觉任务卓越性能", false),
    QWEN_VL_PLUS_2024_08_09("qwen-vl-plus-2024-08-09", "视觉任务卓越性能", false),
    QWEN_VL_PLUS_2023_12_01("qwen-vl-plus-2023-12-01", "视觉任务卓越性能", false),

    // qwen-vl-ocr系列
    QWEN_VL_OCR("qwen-vl-ocr", "专注文字提取模型", false),
    QWEN_VL_OCR_LATEST("qwen-vl-ocr-latest", "专注文字提取模型", false),
    QWEN_VL_OCR_2024_10_28("qwen-vl-ocr-2024-10-28", "专注文字提取模型", false),

    // qwen-audio-turbo系列
    QWEN_AUDIO_TURBO("qwen-audio-turbo", "音频理解转录模型", false),
    QWEN_AUDIO_TURBO_LATEST("qwen-audio-turbo-latest", "音频理解转录模型", false),
    QWEN_AUDIO_TURBO_2024_12_04("qwen-audio-turbo-2024-12-04", "提升语音识别能力", false),
    QWEN_AUDIO_TURBO_2024_08_07("qwen-audio-turbo-2024-08-07", "音频理解转录模型", false),

    // qwen-audio-asr系列
    QWEN_AUDIO_ASR("qwen-audio-asr", "专用于语音识别模型", false),
    QWEN_AUDIO_ASR_LATEST("qwen-audio-asr-latest", "专用于语音识别模型", false),
    QWEN_AUDIO_ASR_2024_12_04("qwen-audio-asr-2024-12-04", "专用于语音识别模型", false),

    // Qwen-VL系列
    QWEN2_5_VL_72B_INSTRUCT("qwen2.5-vl-72b-instruct", "图像NLP相关任务", true),
    QWEN2_5_VL_7B_INSTRUCT("qwen2.5-vl-7b-instruct", "图像NLP相关任务", true),
    QWEN2_5_VL_3B_INSTRUCT("qwen2.5-vl-3b-instruct", "图像NLP相关任务", true),
    QWEN2_VL_72B_INSTRUCT("qwen2-vl-72b-instruct", "图像NLP相关任务", true),
    QWEN2_VL_7B_INSTRUCT("qwen2-vl-7b-instruct", "图像NLP相关任务", true),
    QWEN2_VL_2B_INSTRUCT("qwen2-vl-2b-instruct", "图像NLP相关任务", true),
    QWEN_VL_V1("qwen-vl-v1", "图像NLP相关任务", true),
    QWEN_VL_CHAT_V1("qwen-vl-chat-v1", "图像NLP相关任务", true),

    // Qwen-Audio系列
    QWEN2_AUDIO_INSTRUCT("qwen2-audio-instruct", "音频理解输出文本", true),
    QWEN_AUDIO_CHAT("qwen-audio-chat", "音频理解输出文本", true) ;

    private final String modelName;
    private final String description;
    private final boolean isOpenSource;

    QwenVisionModelEnums(String modelName, String description, boolean isOpenSource) {
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
        for (QwenVisionModelEnums model : QwenVisionModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("description", model.getDescription());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }


}
