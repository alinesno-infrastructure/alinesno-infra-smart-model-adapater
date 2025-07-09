package com.agentsflex.image.qwen;

import com.agentsflex.core.image.ImageConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QwenImageModelConfig extends ImageConfig {

    private String taskEndpoint = "https://dashscope.aliyuncs.com/api/v1/tasks/";

    public QwenImageModelConfig() {

        String endpoint = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text2image/image-synthesis";
        String model = "wanx2.0-t2i-turbo" ; // QwenImageModels.wanx2_0_t2i_turbo;

        setEndpoint(endpoint);
        setModel(model);

    }
}
