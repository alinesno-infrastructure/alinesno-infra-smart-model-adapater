package com.agentsflex.core.ocr;

/**
 * OCR图像识别接口，实现OCR识别能力
 *
 */
public interface OcrModel {

    /**
     * 图片识别接口
     */
    OcrResponse recognize(OcrRequest request);

}
