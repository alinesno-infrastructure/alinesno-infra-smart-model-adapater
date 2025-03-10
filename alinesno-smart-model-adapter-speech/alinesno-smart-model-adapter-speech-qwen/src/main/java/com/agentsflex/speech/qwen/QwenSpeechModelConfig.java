package com.agentsflex.speech.qwen;

import lombok.Data;

import java.io.Serializable;

@Data
public class QwenSpeechModelConfig  implements Serializable {

    private String model = "cosyvoice-v1";
    private String apiKey ;

}
