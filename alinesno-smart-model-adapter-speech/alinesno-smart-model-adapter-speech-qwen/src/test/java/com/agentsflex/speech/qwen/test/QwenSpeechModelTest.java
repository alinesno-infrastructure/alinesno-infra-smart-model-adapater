package com.agentsflex.speech.qwen.test;

import com.agentsflex.core.speech.RecognizeSpeechRequest;
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
        config.setModel("sensevoice-v1");

        QwenSpeechModel openAiSpeechModel = new QwenSpeechModel(config);

        RecognizeSpeechRequest request = new RecognizeSpeechRequest();
        request.setAudioList(List.of("http://data.linesno.com/ddaiai_whisper.wav" , "https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/sensevoice/rich_text_example_1.wav")) ;

        SpeechResponse generate = openAiSpeechModel.recognize(request);
        if (generate != null && generate.getSpeechMp3() != null){
            generate.getSpeechMp3().writeToFile(new File("E:\\tmp\\mp3\\Speech_" + UUID.randomUUID() + ".mp3"));
        }

        System.out.println(generate);
    }

}
