package com.agentsflex.core.ocr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class OcrResponse extends BaseOcrResponse {

    private String results; // 识别内容
    private long usageTime ; // 识别时间

    public OcrResponse(String results, long usageTime) {
        this.results = results;
        this.usageTime = usageTime;
    }
}
