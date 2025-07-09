package com.agentsflex.speech.qwen;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 语音识别模型枚举
 */
@Getter
public enum QwenSpeechRecognizeModelEnums {

    PARAFORMER_8K_V1("paraformer-8k-v1", "8kHz中文电话语音识别，支持文件转写", false),
    PARAFORMER_8K_V2("paraformer-8k-v2", "8kHz中文语音识别，模型结构升级，支持中文热词", false),
    PARAFORMER_V2("paraformer-v2", "多语种语音识别，支持任意采样率，不支持热词", false),
    PARAFORMER_V1("paraformer-v1", "16kHz及以上中英文语音识别", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    QwenSpeechRecognizeModelEnums(String modelName, String usageScenario, boolean isOpenSource) {
        this.modelName = modelName;
        this.usageScenario = usageScenario;
        this.isOpenSource = isOpenSource;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource以及voiceInfos
     */
    public static List<Map<String, String>> getAllModels() {

        List<Map<String, String>> models = new ArrayList<>();

        for (QwenSpeechRecognizeModelEnums model : QwenSpeechRecognizeModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }

        return models;
    }
}
