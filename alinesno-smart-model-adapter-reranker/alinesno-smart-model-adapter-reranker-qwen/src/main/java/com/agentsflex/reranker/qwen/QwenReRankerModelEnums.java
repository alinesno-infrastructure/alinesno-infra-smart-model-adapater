package com.agentsflex.reranker.qwen;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重排序模型
 */
@Getter
@AllArgsConstructor
public enum QwenReRankerModelEnums {

    DASH_SCOPE_RERANK("gte-rerank", "提供阿里巴巴通义实验室开发的GTE-Rerank文本排序系列模型，可用于高质量文本检索、排序，可通过LlamaIndex框架进行集成", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource以及voiceInfos
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();

        for (QwenReRankerModelEnums model : QwenReRankerModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();

            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource));

            models.add(modelMap);
        }

        return models;
    }

}
