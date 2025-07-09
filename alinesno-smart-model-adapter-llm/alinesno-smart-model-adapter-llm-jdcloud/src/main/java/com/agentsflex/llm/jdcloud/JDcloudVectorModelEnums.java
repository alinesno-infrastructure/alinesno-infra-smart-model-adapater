package com.agentsflex.llm.jdcloud;

import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 向量模型
 *
 * @author luoxiaodong
 */
@Getter
public enum JDcloudVectorModelEnums {

    TEXT_EMBEDDING_V3("text-embedding-v3", "文本向量化多语种", "1,024（默认）、768或512", false),
    TEXT_EMBEDDING_V2("text-embedding-v2", "文本向量化多语种", "1,536", false),
    TEXT_EMBEDDING_V1("text-embedding-v1", "文本向量化多语", "未提及", false),

    TEXT_EMBEDDING_ASYNC_V2("text-embedding-async-v2", "文本向量化多语种", "未提及（最大行数100,000）", false),
    TEXT_EMBEDDING_ASYNC_V1("text-embedding-async-v1", "文本向量化多语", "未提及", false),

    MULTIMODAL_EMBEDDING_V1("multimodal-embedding-v1", "多模态分类检索", "1,024", false);

    private final String modelName;
    private final String usageScenario;
    private final String vectorDimension;
    private final boolean isOpenSource;

    JDcloudVectorModelEnums(String modelName, String usageScenario, String vectorDimension, boolean isOpenSource) {
        this.modelName = modelName;
        this.usageScenario = usageScenario;
        this.vectorDimension = vectorDimension;
        this.isOpenSource = isOpenSource;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {
        JDcloudVectorModelEnums[] values = JDcloudVectorModelEnums.values();
        List<Map<String, String>> list = new java.util.ArrayList<>();
        for (JDcloudVectorModelEnums value : values) {
            Map<String, String> map = new java.util.HashMap<>();
            map.put("modelName", value.getModelName());
            map.put("usageScenario", value.getUsageScenario());
            map.put("vectorDimension", value.getVectorDimension());
            map.put("isOpenSource", String.valueOf(value.isOpenSource));
            list.add(map);
        }
        return list;
    }

}
