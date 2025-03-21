package com.agentsflex.speech.siliconflow;

import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.core.speech.*;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该类实现了 SpeechModel 接口，用于与 Siliconflow 语音服务进行交互，
 * 提供了文本转语音（合成）和语音识别的功能。不过目前语音识别功能仅返回 null，未做实际处理。
 */
@Slf4j
public class SiliconflowSpeechModel implements SpeechModel {

    // 用于发送 HTTP 请求的客户端实例
    private final HttpClient httpClient = new HttpClient();
    // 存储 Siliconflow 语音模型配置信息的对象
    private final SiliconflowSpeechModelConfig config;

    /**
     * 构造函数，用于初始化 SiliconflowSpeechModel 实例。
     *
     * @param config 包含 Siliconflow 语音模型配置信息的对象，如 API 密钥、模型名称等
     */
    public SiliconflowSpeechModel(SiliconflowSpeechModelConfig config) {
        this.config = config;
    }

    /**
     * 实现文本转语音的功能，将输入的文本转换为语音数据。
     *
     * @param request 包含文本转语音所需参数的请求对象，如文本内容、语音类型等
     * @return 包含语音数据或错误信息的响应对象
     */
    @Override
    public SpeechResponse synthesize(SynthesizeSpeechRequest request) {

        request.setVoice(config.getModel() + ":" + request.getVoice());

        // 创建一个新的语音响应对象，用于存储最终的响应结果
        SpeechResponse response = new SpeechResponse();
        try {
            // 构建请求头，包含认证信息和请求体的内容类型
            Map<String, String> headers = new HashMap<>();
            // 添加认证信息，使用 Bearer 令牌的方式进行身份验证
            headers.put("Authorization", "Bearer " + config.getApiKey());
            // 指定请求体的内容类型为 JSON
            headers.put("Content-Type", "application/json");

            // 构建请求体，将请求中的参数整理成一个 Map 对象
            Map<String, Object> requestBody = getStringObjectMap(request);

            // 使用 FastJSON 将请求体的 Map 对象转换为 JSON 字符串，以便发送到服务器
            String body = JSON.toJSONString(requestBody);

            // 调用 HttpClient 的 postBytes 方法发送 POST 请求，并获取服务器返回的音频字节数据
            byte[] audioBytes = httpClient.postBytes(config.getEndpoint(), headers, body);

            // 检查是否成功获取到音频字节数据
            if (audioBytes != null) {
                // 如果获取到音频数据，将其添加到语音响应对象中
                response.addSpeech(audioBytes);
            } else {
                // 如果未获取到音频数据，标记响应为错误状态，并设置错误信息
                response.setError(true);
                response.setErrorMessage("无法从 API 获取音频数据。");
            }
        } catch (Exception e) {
            // 捕获请求过程中可能出现的异常，并记录错误日志
            log.error("文本转语音过程中出现错误", e);
            // 标记响应为错误状态，并设置包含异常信息的错误信息
            response.setError(true);
            response.setErrorMessage("文本转语音过程中发生错误: " + e.getMessage());
        }
        // 返回最终的语音响应对象
        return response;
    }

    /**
     * 构建请求体的 Map 对象，将请求中的参数整理成适合发送到服务器的格式。
     *
     * @param request 包含文本转语音所需参数的请求对象
     * @return 包含请求参数的 Map 对象
     */
    @NotNull
    private Map<String, Object> getStringObjectMap(SynthesizeSpeechRequest request) {
        // 创建一个新的 Map 对象，用于存储请求体的参数
        Map<String, Object> requestBody = new HashMap<>();
        // 添加模型名称，从配置对象中获取
        requestBody.put("model", config.getModel());
        // 添加需要转换为语音的文本内容
        requestBody.put("input", request.getText());
        // 添加语音类型，如男性或女性声音
        requestBody.put("voice", request.getVoice());
        // 添加语音输出的格式，如 mp3、wav 等
        requestBody.put("response_format", request.getResponseFormat());
        // 检查请求中是否指定了采样率，如果指定了则添加到请求体中
        if (request.getSampleRate() != null) {
            requestBody.put("sample_rate", request.getSampleRate());
        }
        // 添加是否采用流式传输的标志
        requestBody.put("stream", request.isStream());
        // 添加语音播放的速度
        requestBody.put("speed", request.getSpeed());
        // 添加语音的增益值
        requestBody.put("gain", request.getGain());
        // 返回包含所有请求参数的 Map 对象
        return requestBody;
    }

    /**
     * 实现语音识别的功能，但目前该方法仅返回 null，未做实际处理。
     *
     * @param request 包含语音识别所需参数的请求对象
     * @return 包含语音识别结果或错误信息的响应对象，目前返回 null
     */
    @Override
    public List<RecognizeSpeechResponse> recognize(RecognizeSpeechRequest request) {
        return null;
    }
}
