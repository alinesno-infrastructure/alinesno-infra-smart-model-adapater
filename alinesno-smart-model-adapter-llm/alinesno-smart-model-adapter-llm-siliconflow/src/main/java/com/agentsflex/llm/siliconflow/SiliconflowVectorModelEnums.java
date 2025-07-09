package com.agentsflex.llm.siliconflow;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 嵌入模型枚举类
 */
@Getter
@AllArgsConstructor
public enum SiliconflowVectorModelEnums {

    NETEASE_YOUDAO_BCE_EMBEDDING_BASE_V1("netease-youdao/bce-embedding-base_v1", "文本向量化，信息检索", false),
    BAAI_BGE_M3("BAAI/bge-m3", "文本向量化，语义匹配", false),
    BAAI_BGE_LARGE_EN_V1_5("BAAI/bge-large-en-v1.5", "英文文本向量化分析", false),
    BAAI_BGE_LARGE_ZH_V1_5("BAAI/bge-large-zh-v1.5", "中文文本向量化匹配", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        List<Map<String, String>> models = new ArrayList<>();
        for (SiliconflowVectorModelEnums model : SiliconflowVectorModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }
}
