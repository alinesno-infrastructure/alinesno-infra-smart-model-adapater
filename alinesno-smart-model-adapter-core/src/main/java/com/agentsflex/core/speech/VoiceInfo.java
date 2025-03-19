package com.agentsflex.core.speech;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 定义 VoiceInfo 类，用于存储音色相关信息
@NoArgsConstructor
@Getter
public class VoiceInfo {
    private String voice;
    private String description;
    private String applicableScenarios;
    private String language;
    private int defaultSampleRate;
    private String defaultAudioFormat;

public VoiceInfo(String voice, String description, String applicableSc , int defaultSampleRate , String format) {
    this.voice = voice;
    this.description = description;
    this.applicableScenarios = applicableSc;
    this.defaultSampleRate = defaultSampleRate;
    this.defaultAudioFormat = format;
}
}
