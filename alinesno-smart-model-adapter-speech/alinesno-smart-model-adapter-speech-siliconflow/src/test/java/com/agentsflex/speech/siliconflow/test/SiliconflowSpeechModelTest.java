package com.agentsflex.speech.siliconflow.test;

import com.agentsflex.core.speech.SpeechResponse;
import com.agentsflex.core.speech.SynthesizeSpeechRequest;
import com.agentsflex.speech.siliconflow.SiliconflowSpeechModel;
import com.agentsflex.speech.siliconflow.SiliconflowSpeechModelConfig;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

public class SiliconflowSpeechModelTest {

    @Test
    public void testGenSpeech(){
        SiliconflowSpeechModelConfig config = new SiliconflowSpeechModelConfig();
        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;

        SiliconflowSpeechModel openAiSpeechModel = new SiliconflowSpeechModel(config);

        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest();
        request.setText("若要使用 FastJSON 来构建请求体，可按以下方式对代码进行修改。FastJSON 是阿里巴巴开源的一个高性能的 JSON 处理库，能方便地将 Java 对象转换为 JSON 字符串");
        request.setVoice("FunAudioLLM/CosyVoice2-0.5B:alex");

        SpeechResponse generate = openAiSpeechModel.synthesize(request);
        if (generate != null && generate.getSpeechMp3() != null){
            generate.getSpeechMp3().writeToFile(new File("E:\\tmp\\mp3\\Speech_" + UUID.randomUUID() + ".mp3"));
        }

        System.out.println(generate);
    }

}
