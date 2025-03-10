package com.agentsflex.speech.qwen;

import com.agentsflex.core.speech.RecognizeSpeechRequest;
import com.agentsflex.core.speech.SpeechModel;
import com.agentsflex.core.speech.SpeechResponse;
import com.agentsflex.core.speech.SynthesizeSpeechRequest;
import com.agentsflex.speech.qwen.parser.SenseVoiceParser;
import com.alibaba.dashscope.audio.asr.transcription.*;
import com.alibaba.dashscope.audio.ttsv2.SpeechSynthesisParam;
import com.alibaba.dashscope.audio.ttsv2.SpeechSynthesizer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Qwen语音识别接口
 */
@Slf4j
public class QwenSpeechModel implements SpeechModel {

    private final QwenSpeechModelConfig config;

    public QwenSpeechModel(QwenSpeechModelConfig config) {
        this.config = config;
    }

    @Override
    public SpeechResponse synthesize(SynthesizeSpeechRequest request) {
        SpeechSynthesisParam param =
            SpeechSynthesisParam.builder()
                .apiKey(config.getApiKey())
                .model(config.getModel())
                .voice(request.getVoice())
                .build();
        SpeechSynthesizer synthesizer = new SpeechSynthesizer(param, null);
        ByteBuffer audio = synthesizer.call(request.getText());

        log.debug("requestId: " + synthesizer.getLastRequestId());

        // 创建 SpeechResponse 对象
        SpeechResponse response = new SpeechResponse();

        // 将音频数据添加到 SpeechResponse 中
        response.addSpeech(audio.array());

        return response;
    }

    @Override
    public SpeechResponse recognize(RecognizeSpeechRequest request) {
        // 创建转写请求参数，需要用真实apikey替换your-dashscope-api-key
        TranscriptionParam param =
            TranscriptionParam.builder()
                .apiKey(config.getApiKey())
                .model(config.getModel())
                .fileUrls(request.getAudioList())
                .parameter("language_hints", new String[] {"en" , "zh"})
                .build();
        try {
            Transcription transcription = new Transcription();

            // 提交转写请求
            TranscriptionResult result = transcription.asyncCall(param);
            log.debug("requestId: " + result.getRequestId());

            // 等待转写完成
            result = transcription.wait(TranscriptionQueryParam.FromTranscriptionParam(param, result.getTaskId()));

            // 获取转写结果
            List<TranscriptionTaskResult> taskResultList = result.getResults();

            if (taskResultList != null && !taskResultList.isEmpty()) {
                for (TranscriptionTaskResult taskResult : taskResultList) {
                    String transcriptionUrl = taskResult.getTranscriptionUrl();
                    HttpURLConnection connection = (HttpURLConnection) new URL(transcriptionUrl).openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonElement jsonResult = gson.fromJson(reader, JsonObject.class);

                    System.out.println(gson.toJson(jsonResult));
                    System.out.println(gson.toJson(SenseVoiceParser.parseSenseVoiceResult(jsonResult.getAsJsonObject(), true, true, true)));
                }
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return null ;
    }
}
