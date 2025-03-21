package com.agentsflex.core.speech;

import java.util.List;

/**
 * 语音模型，包括语音合成、语音识别两个接口
 */
public interface SpeechModel {

    /**
     * 语音合成
     * @param request
     * @return
     */
    SpeechResponse synthesize(SynthesizeSpeechRequest request);

    /**
     * 语音识别
     * @param request
     * @return
     */
    List<RecognizeSpeechResponse> recognize(RecognizeSpeechRequest request);
}
