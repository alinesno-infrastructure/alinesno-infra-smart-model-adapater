package com.agentsflex.core.speech;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 该类表示文本转语音合成请求，继承自 BaseSpeechRequest 类。
 * 它包含了文本转语音所需的各种参数，用于配置语音合成的具体要求。
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SynthesizeSpeechRequest extends BaseSpeechRequest {

    /**
     * 需要转换为语音的文本内容。
     * 该文本将作为语音合成的输入，最终会被转换为对应的语音。
     */
    private String text;

    /**
     * 指定语音的性别，可取值为 "male" 或 "female"。
     * 用于选择合成语音时使用男性或女性的声音。
     */
    private String voice ;

    /**
     * 合成语音的输出格式，默认值为 "mp3"。
     * 支持的格式有 "mp3"、"opus"、"wav"、"pcm" 等。
     */
    private String responseFormat = "mp3";

    /**
     * 合成语音的采样率。
     * 不同的输出格式支持不同的采样率，具体取值范围根据输出格式而定。
     */
    private String sampleRate;

    /**
     * 是否采用流式传输方式，默认值为 true。
     * 若设置为 true，则以流式方式返回合成的语音数据；若为 false，则一次性返回完整的语音数据。
     */
    private boolean stream = true;

    /**
     * 合成语音的播放速度，默认值为 1。
     * 取值范围在 0.25 到 4.0 之间，1.0 表示正常速度。
     */
    private int speed = 1;

    /**
     * 合成语音的增益值，默认值为 0。
     * 取值范围在 -10 到 10 之间，用于调整语音的音量大小。
     */
    private int gain = 0;
}
