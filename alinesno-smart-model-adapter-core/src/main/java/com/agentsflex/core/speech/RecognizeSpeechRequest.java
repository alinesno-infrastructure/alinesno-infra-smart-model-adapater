package com.agentsflex.core.speech;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 语音识别接口请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecognizeSpeechRequest extends BaseSpeechRequest {
    private List<String> audioList;
}
