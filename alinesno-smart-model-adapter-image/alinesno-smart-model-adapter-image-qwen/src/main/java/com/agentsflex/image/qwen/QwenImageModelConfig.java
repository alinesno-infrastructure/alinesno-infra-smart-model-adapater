package com.agentsflex.image.qwen;

import lombok.Data;

import java.io.Serializable;

@Data
public class QwenImageModelConfig implements Serializable {
    private String endpoint = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text2image/image-synthesis";
    private String taskEndpoint = "https://dashscope.aliyuncs.com/api/v1/tasks/";
    private String model = QwenImageModels.wanx2_0_t2i_turbo;
    private String apiKey;
}
