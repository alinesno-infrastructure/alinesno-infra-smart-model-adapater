package com.agentsflex.speech.siliconflow;

import lombok.Data;

import java.io.Serializable;

@Data
public class SiliconflowSpeechModelConfig implements Serializable {

    private String endpoint = "https://api.siliconflow.cn/v1/audio/speech";
    private String model = "FunAudioLLM/CosyVoice2-0.5B" ;
    private String apiKey ;

}
