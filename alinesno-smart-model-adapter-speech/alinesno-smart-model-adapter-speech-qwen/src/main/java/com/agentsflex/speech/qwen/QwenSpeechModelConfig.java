package com.agentsflex.speech.qwen;

import com.agentsflex.core.speech.SpeechConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class QwenSpeechModelConfig extends SpeechConfig {

    public QwenSpeechModelConfig() {
        String model = "cosyvoice-v1";
        setModel(model);
    }
}
