package com.agentflex.ocr.aip;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 语音模型
 */
@Getter
public enum AipOcrModelEnums {

    COSYVOICE_V1("paddle-ocr", "百度PaddleOCR模型", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    AipOcrModelEnums(String modelName, String usageScenario, boolean isOpenSource) {
        this.modelName = modelName;
        this.usageScenario = usageScenario;
        this.isOpenSource = isOpenSource;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource以及voiceInfos
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();

        for (AipOcrModelEnums model : AipOcrModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource));
            models.add(modelMap);
        }
        return models;
    }

}
