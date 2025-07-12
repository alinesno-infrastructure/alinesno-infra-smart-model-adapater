package com.agentflex.ocr.aliyun;

import com.agentsflex.core.ocr.OcrConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AIP的图像识别能力
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AliyunOcrModelConfig extends OcrConfig {

    private static final String endpoint = "ocr-api.cn-hangzhou.aliyuncs.com";
    private String secretKey ;

    public AliyunOcrModelConfig() {
        setEndpoint(endpoint);
    }

}
