package com.agentsflex.speech.qwen.test;

import com.agentsflex.core.speech.RecognizeSpeechRequest;
import com.agentsflex.core.speech.RecognizeSpeechResponse;
import com.agentsflex.core.speech.SpeechResponse;
import com.agentsflex.core.speech.SynthesizeSpeechRequest;
import com.agentsflex.speech.qwen.QwenSpeechModel;
import com.agentsflex.speech.qwen.QwenSpeechModelConfig;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class QwenSpeechModelTest {

    @Test
    public void testGenSpeech(){
        QwenSpeechModelConfig config = new QwenSpeechModelConfig();
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;

        QwenSpeechModel openAiSpeechModel = new QwenSpeechModel(config);

        SynthesizeSpeechRequest request = new SynthesizeSpeechRequest();
        request.setText("一间有着精致窗户的花店，漂亮的木质门，摆放着花朵");
        request.setVoice("longxiaochun");

        SpeechResponse generate = openAiSpeechModel.synthesize(request);
        if (generate != null && generate.getSpeechMp3() != null){
            generate.getSpeechMp3().writeToFile(new File("/Users/luodong/Desktop/demo-images/" + UUID.randomUUID() + ".mp3"));
        }

        System.out.println(generate);
    }

    @Test
    public void testRecognize(){
        QwenSpeechModelConfig config = new QwenSpeechModelConfig();
        config.setApiKey(System.getenv("ALINESNO_QIWEN_API_KEY")) ;
        config.setModel("paraformer-v1");

        QwenSpeechModel openAiSpeechModel = new QwenSpeechModel(config);

        RecognizeSpeechRequest request = new RecognizeSpeechRequest();
        request.setAudioList(List.of("http://data.linesno.com/demo_2.wav" , "http://data.linesno.com/demo_2.wav")) ;

        List<RecognizeSpeechResponse> generate = openAiSpeechModel.recognize(request);
        for(RecognizeSpeechResponse speechResponse : generate){
            System.out.println(speechResponse.getTranscripts().get(0).getText());
        }
    }

}
