package com.agentsflex.reranker.siliconflow;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 重排序模型枚举类
 */
@Getter
@AllArgsConstructor
public enum SiliconflowRerankerModels {

    NETEASE_YOUDAO_BCE_RERANKER_BASE_V1("netease-youdao/bce-reranker-base_v1", "适用于对文本检索结果进行重排序，以提高检索结果的相关性和准确性", false),
    BAAI_BGE_RERANKER_V2_M3("BAAI/bge-reranker-v2-m3", "用于对检索到的文本进行重新排序，优化排序结果，提升信息检索效果", false);

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, String>> getAllModels() {

        List<Map<String, String> > models = new ArrayList<>();

        for (SiliconflowRerankerModels model : SiliconflowRerankerModels.values()) {
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
