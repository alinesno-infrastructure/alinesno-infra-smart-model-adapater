package com.agentsflex.core.speech;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SynthesizeSpeechRequest extends BaseSpeechRequest {

    private String text ;
    private String voice ; // male female

}
