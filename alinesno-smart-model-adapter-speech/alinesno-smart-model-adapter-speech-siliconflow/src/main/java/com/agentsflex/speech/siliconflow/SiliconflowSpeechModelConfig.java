package com.agentsflex.speech.siliconflow;

import com.agentsflex.core.speech.SpeechConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SiliconflowSpeechModelConfig extends SpeechConfig {

    public SiliconflowSpeechModelConfig() {
        String endpoint = "https://api.siliconflow.cn/v1/audio/speech";
        String model = "FunAudioLLM/CosyVoice2-0.5B" ;

        setEndpoint(endpoint);
        setModel(model);
    }
}
