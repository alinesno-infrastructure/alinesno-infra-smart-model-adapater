package com.agentsflex.core.speech;


import com.agentsflex.core.image.Image;
import com.agentsflex.core.util.Metadata;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 模型模型返回
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpeechResponse extends Metadata {

    private Speech speechMp3;
    private String errorMessage;
    private boolean error;

    public void addSpeech(byte[] bytes) {
        if (this.speechMp3 == null) {
            this.speechMp3 = new Speech() ;
        }

        this.speechMp3 = Speech.ofBytes(bytes);
    }

}
