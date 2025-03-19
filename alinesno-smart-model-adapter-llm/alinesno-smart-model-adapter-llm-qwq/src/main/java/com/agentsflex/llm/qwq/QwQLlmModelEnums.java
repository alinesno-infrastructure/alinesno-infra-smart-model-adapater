package com.agentsflex.llm.qwq;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 推理模型
 */
public class QwQLlmModelEnums {

    /**
     * 枚举类表示不同的 QwQ 模型信息
     */
    @Getter
    @AllArgsConstructor
    public enum QwQModel {

        QWQ_PLUS("qwq-plus", "Qwen2.5 强化推理", false),
        QWQ_PLUS_LATEST("qwq-plus-latest", "Qwen2.5 最新推理", false),
        QWQ_PLUS_2025_03_05("qwq-plus-2025-03-05", "Qwen2.5 快照推理", false);

        private final String modelName;
        private final String usageScenario;
        private final boolean isOpenSource;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();
        for (QwQModel model : QwQModel.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }
}
