package com.agentsflex.core.speech;

import com.agentsflex.core.util.Metadata;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 语音识别结果
 * @author luoxiaodong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RecognizeSpeechResponse extends Metadata {

    /**
     * 待识别语音文件的 URL 地址
     */
    private String fileUrl;

    /**
     * 语音文件的属性信息
     */
    private Properties properties;

    /**
     * 语音识别后的转录信息列表
     */
    private List<Transcript> transcripts;

    /**
     * 语音识别开始的时间戳
     */
    private long startTime;

    /**
     * 语音识别结束的时间戳
     */
    private long endTime;

    /**
     * 语音识别所花费的时间
     */
    private long usageTime;

    @Data
    public static class Properties {
        /**
         * 语音文件的声道信息列表
         */
        private List<Integer> channels;

        /**
         * 语音文件的原始采样率
         */
        private int originalSamplingRate;

        /**
         * 语音文件的原始时长，单位为毫秒
         */
        private long originalDurationInMilliseconds;
    }

    @Data
    public static class Transcript {
        /**
         * 转录信息对应的声道 ID
         */
        private int channelId;

        /**
         * 转录内容的时长，单位为毫秒
         */
        private long contentDurationInMilliseconds;

        /**
         * 转录后的文本内容
         */
        private String text;
    }
}
