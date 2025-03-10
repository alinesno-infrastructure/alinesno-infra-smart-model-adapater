package com.agentsflex.image.doubao;

import lombok.Data;

import java.io.Serializable;

@Data
public class DoubaoImageModelConfig implements Serializable {
    private String endpoint = "https://visual.volcengineapi.com";
    private String model = DoubaoImageModels.general_v2_1_L;
    private String accessKey ;
    private String secretKey ;
}
