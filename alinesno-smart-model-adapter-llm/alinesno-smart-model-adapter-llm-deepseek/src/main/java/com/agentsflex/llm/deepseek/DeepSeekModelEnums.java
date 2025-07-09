package com.agentsflex.llm.deepseek;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 推理模型
 */
public class DeepSeekModelEnums {

    /**
     * 枚举类表示不同的 DeepSeekModel 模型信息
     */
    @Getter
    @AllArgsConstructor
    public enum DeepSeekModel{

        DEEPSEEK_CHAT("deepseek-chat", "DEEPSEEK大模型", false),
        DEEPSEEK_REASONER("deepseek-reasoner", "DEEPSEEK推理模型", false);

        private final String modelName;
        private final String usageScenario;
        private final boolean isOpenSource;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();
        for (DeepSeekModel model : DeepSeekModel.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }
}
