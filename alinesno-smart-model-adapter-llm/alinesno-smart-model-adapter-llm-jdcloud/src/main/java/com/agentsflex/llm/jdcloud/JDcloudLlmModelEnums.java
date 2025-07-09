package com.agentsflex.llm.jdcloud;

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
public enum JDcloudLlmModelEnums {

    // DeepSeek系列
    DEEPSEEK_R1("deepseek-r1", "多任务表现优", false),
    DEEPSEEK_V3("deepseek-v3", "多能力表现佳", false) ;

    private final String modelName;
    private final String description;
    private final boolean isOpenSource;

    JDcloudLlmModelEnums(String modelName, String description, boolean isOpenSource) {
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
        for (JDcloudLlmModelEnums model : JDcloudLlmModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("description", model.getDescription());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }


}
