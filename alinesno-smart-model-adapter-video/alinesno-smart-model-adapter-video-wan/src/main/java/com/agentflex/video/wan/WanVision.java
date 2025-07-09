package com.agentflex.video.wan;

import com.agentsflex.core.llm.ChatOptions;
import com.agentsflex.core.llm.client.HttpClient;
import com.agentsflex.core.parser.AiMessageParser;
import com.agentsflex.core.vision.BaseVision;
import com.agentsflex.core.vision.GenVideoResponse;
import com.alibaba.dashscope.aigc.videosynthesis.VideoSynthesis;
import com.alibaba.dashscope.aigc.videosynthesis.VideoSynthesisParam;
import com.alibaba.dashscope.aigc.videosynthesis.VideoSynthesisResult;
import com.alibaba.dashscope.utils.JsonUtils;
import lombok.SneakyThrows;

public class WanVision extends BaseVision<WanVisionConfig> {

    private final WanVisionConfig config  ;

    HttpClient httpClient = new HttpClient();

    public AiMessageParser aiMessageParser = WanVisionUtil.getAiMessageParser(false);
    public AiMessageParser streamMessageParser = WanVisionUtil.getAiMessageParser(true);

    public WanVision(WanVisionConfig config) {
        super(config);
        this.config = config;
    }

    /**
     * 生成视频
     * @param prompt
     * @param options
     * @return
     */
    @SneakyThrows
    @Override
    public GenVideoResponse textToVideo(String prompt, ChatOptions options) {
        GenVideoResponse genVideoResponse = new GenVideoResponse();
        long startTime = System.currentTimeMillis();

        VideoSynthesis vs = new VideoSynthesis();
        VideoSynthesisParam param =
            VideoSynthesisParam.builder()
                .apiKey(config.getApiKey())
                .model(config.getModel())
                .prompt(prompt)
                .size("1280*720")
                .build();
        System.out.println("please wait...");
        VideoSynthesisResult result = vs.call(param);

        long endTime = System.currentTimeMillis();
        long usageTime = (endTime - startTime) / 1000;

        genVideoResponse.setStartTime(startTime);
        genVideoResponse.setEndTime(endTime);
        genVideoResponse.setDuration(usageTime);

        System.out.println(JsonUtils.toJson(result));

        return genVideoResponse;
    }

    @SneakyThrows
    @Override
    public GenVideoResponse imageToVideo(String prompt, String imageUrl ,  ChatOptions options) {
        GenVideoResponse genVideoResponse = new GenVideoResponse();
        long startTime = System.currentTimeMillis();

        VideoSynthesis vs = new VideoSynthesis();
        VideoSynthesisParam param =
            VideoSynthesisParam.builder()
                .apiKey(config.getApiKey())
                .model(config.getModel())
                .prompt(prompt)
                .imgUrl(imageUrl)
                .build();
        System.out.println("please wait...");
        VideoSynthesisResult result = vs.call(param);

        long endTime = System.currentTimeMillis();
        long usageTime = (endTime - startTime) / 1000;

        genVideoResponse.setStartTime(startTime);
        genVideoResponse.setEndTime(endTime);
        genVideoResponse.setDuration(usageTime);

        System.out.println(JsonUtils.toJson(result));

        return genVideoResponse;
    }
}
