package com.agentsflex.core.ocr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.File;

/**
 * 图片识别能力
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OcrRequest extends BaseOcrRequest {

    /**
     * 需要识别的文件
     */
    private File image ;

}
