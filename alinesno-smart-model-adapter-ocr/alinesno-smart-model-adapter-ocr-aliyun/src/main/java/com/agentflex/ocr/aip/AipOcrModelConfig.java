package com.agentflex.ocr.aip;

import com.agentsflex.core.ocr.OcrConfig;

/**
 * AIP的图像识别能力
 */
public class AipOcrModelConfig extends OcrConfig {

    private static final String endpoint = "http://alinesno-infra-smart-ocr-boot.beta.base.infra.linesno.com/api/infra/smart/ocr/generalText";

    public AipOcrModelConfig() {
        setEndpoint(endpoint);
    }

}
