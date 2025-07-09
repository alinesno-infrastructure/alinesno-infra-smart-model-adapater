package com.agentsflex.image.doubao;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 图像模型相关枚举类
 */
@Getter
@AllArgsConstructor
public enum DoubaoImageModelEnums {

    GENERAL_V2_0("general_v2.0", "使用场景可根据具体情况确定，假设用于一般图像相关任务", false),
    GENERAL_CONTROLNET_V2_0("general_controlnet_v2.0", "使用场景可根据具体情况确定，假设用于一般图像相关任务", false),
    GENERAL_V1_4("general_v1.4", "使用场景可根据具体情况确定，假设用于一般图像相关任务", false),
    GENERAL_V2_0_L("general_v2.0_L", "使用场景可根据具体情况确定，假设用于一般图像相关任务", false),
    GENERAL_V2_1_L("general_v2.1_L", "使用场景可根据具体情况确定，假设用于一般图像相关任务", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new java.util.ArrayList<>();

        for (DoubaoImageModelEnums model : DoubaoImageModelEnums.values()) {
            Map<String, String> modelMap = Map.of(
                    "modelName", model.getModelName(),
                    "usageScenario", model.getUsageScenario(),
                    "isOpenSource", String.valueOf(model.isOpenSource())
            );
            models.add(modelMap);
        }

        return models;
    }
}
