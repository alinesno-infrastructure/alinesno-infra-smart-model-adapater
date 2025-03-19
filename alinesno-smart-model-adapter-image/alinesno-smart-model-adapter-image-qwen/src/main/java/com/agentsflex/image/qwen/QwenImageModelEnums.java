package com.agentsflex.image.qwen;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 千问图片模型
 */
@Getter
public enum QwenImageModelEnums {
    // 文生图V2
    WANX2_1_T2I_TURBO("wanx2.1-t2i-turbo", "根据文本描述生成粘土动画风格等图片", false),
    WANX2_1_T2I_PLUS("wanx2.1-t2i-plus", "根据文本描述生成如花店等场景的图片", false),
    WANX2_0_T2I_TURBO("wanx2.0-t2i-turbo", "根据文本描述生成特定形象的图片", false) ;

    private final String modelName;
    private final String usageScenario;
    private final boolean isOpenSource;

    QwenImageModelEnums(String modelName, String usageScenario, boolean isOpenSource) {
        this.modelName = modelName;
        this.usageScenario = usageScenario;
        this.isOpenSource = isOpenSource;
    }

    /**
     * 获取所有模型信息
     * @return
     */
    public static List<Map<String, String>> getAllModels() {
        return Arrays.stream(QwenImageModelEnums.values())
                .map(model -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("modelName", model.getModelName());
                    map.put("usageScenario", model.getUsageScenario());
                    map.put("isOpenSource", String.valueOf(model.isOpenSource()));
                    return map;
                })
                .collect(Collectors.toList());
    }
}
