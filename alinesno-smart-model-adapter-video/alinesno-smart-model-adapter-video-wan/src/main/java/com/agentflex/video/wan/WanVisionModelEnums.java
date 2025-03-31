package com.agentflex.video.wan;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 千问的Wan模型列表
 * @author luoxiaodong
 */
@Getter
public enum WanVisionModelEnums {

    // 文生视频
    WANX2_1_T2V_TURBO("wanx2.1-t2v-turbo", "生成速度更快，表现均衡。单价0.24元/秒，免费额度各200秒，有效期：百炼开通后180天内", false),
    WANX2_1_T2V_PLUS("wanx2.1-t2v-plus", "生成细节更丰富，画面更具质感。", false),

    // 图生视频
    WANX2_1_I2V_TURBO("wanx2.1-i2v-turbo", "生成速度更快，耗时仅为plus模型的三分之一，性价比更高。单价0.24元/秒，免费额度各200秒，有效期：百炼开通后180天内", false),
    WANX2_1_I2V_PLUS("wanx2.1-i2v-plus", "生成细节更丰富，画面更具质感。", false);

    private final String modelName;
    private final String description;
    private final boolean isOpenSource;

    WanVisionModelEnums(String modelName, String description, boolean isOpenSource) {
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
        for (WanVisionModelEnums model : WanVisionModelEnums.values()) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("description", model.getDescription());
            modelMap.put("isOpenSource", String.valueOf(model.isOpenSource()));
            models.add(modelMap);
        }
        return models;
    }


}
