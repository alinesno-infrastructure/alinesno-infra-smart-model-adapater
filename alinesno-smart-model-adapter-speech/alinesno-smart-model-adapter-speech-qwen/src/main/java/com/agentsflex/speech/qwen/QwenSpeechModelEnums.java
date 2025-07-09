package com.agentsflex.speech.qwen;

import com.agentsflex.core.speech.VoiceInfo;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 语音模型
 */
@Getter
public enum QwenSpeechModelEnums {

    COSYVOICE_V1("cosyvoice-v1", "多场景语音应用", buildQwenVoiceInfo().get("cosyvoice-v1"), false),
    COSYVOICE_V2("cosyvoice-v2", "常见语音场景应用", buildQwenVoiceInfo().get("cosyvoice-v2"), false);

    private final String modelName;
    private final String usageScenario;
    private final List<VoiceInfo> voiceInfos;
    private final boolean isOpenSource;

    QwenSpeechModelEnums(String modelName, String usageScenario, List<VoiceInfo> voiceInfos, boolean isOpenSource) {
        this.modelName = modelName;
        this.usageScenario = usageScenario;
        this.voiceInfos = voiceInfos;
        this.isOpenSource = isOpenSource;
    }

    // 构建 QWEN 语音模型的音色信息
    private static Map<String, List<VoiceInfo>> buildQwenVoiceInfo() {
        Map<String, List<VoiceInfo>> voiceInfoMap = new HashMap<>();

        // cosyvoice-v1 模型的音色信息
        List<VoiceInfo> cosyvoiceV1Voices = new ArrayList<>();
        cosyvoiceV1Voices.add(new VoiceInfo("longwan", "龙婉", "语音导航等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longcheng", "龙橙", "语音导航等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longhua", "龙华", "语音导航等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longxiaochun", "龙小淳", "中英语音交互场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longxiaoxia", "龙小夏", "中文语音聊天场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longxiaocheng", "龙小诚", "中英语音交互场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longxiaobai", "龙小白", "聊天有声书场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longlaotie", "龙老铁", "新闻播报等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longshu", "龙书", "有声书等多场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longshuo", "龙硕", "语音播报催收场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longjing", "龙婧", "语音播报催收场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longmiao", "龙妙", "客服催收等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longyue", "龙悦", "语音朗诵播报场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longyuan", "龙媛", "有声书聊天场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longfei", "龙飞", "会议新闻播报场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longjielidou", "龙杰力豆", "新闻聊天等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longtong", "龙彤", "有声书导航场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("longxiang", "龙祥", "新闻播报等场景", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("loongstella", "Stella", "语音多场景应用", 22050, "mp3"));
        cosyvoiceV1Voices.add(new VoiceInfo("loongbella", "Bella", "语音播报等场景", 22050, "mp3"));
        voiceInfoMap.put("cosyvoice-v1", cosyvoiceV1Voices);

        // cosyvoice-v2 模型的音色信息
        List<VoiceInfo> cosyvoiceV2Voices = new ArrayList<>();
        cosyvoiceV2Voices.add(new VoiceInfo("longcheng_v2", "龙橙V2", "语音助手等场景", 22050, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("longhua_v2", "龙华V2", "语音助手等场景", 22050, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("longshu_v2", "龙书V2", "有声书多场景应用", 22050, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("loongbella", "Bella", "语音播报等场景", 22050, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("loongbella_v2", "BellaV2", "待补充", 0, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("longwan_v2", "龙婉V2", "语音助手等场景", 22050, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("longxiaochun_v2", "龙小淳V2", "中英语音助手场景", 22050, "mp3"));
        cosyvoiceV2Voices.add(new VoiceInfo("longxiaoxia_v2", "龙小夏V2", "中文语音聊天场景", 22050, "mp3"));
        voiceInfoMap.put("cosyvoice-v2", cosyvoiceV2Voices);

        return voiceInfoMap;
    }

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource以及voiceInfos
     */
    public static List<Map<String, Object>> getAllModels() {
        List<Map<String, Object>> models = new ArrayList<>();

        for (QwenSpeechModelEnums model : QwenSpeechModelEnums.values()) {
            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", model.isOpenSource);

            List<Map<String, Object>> voiceInfoList = getMaps(model);
            modelMap.put("voiceInfos", voiceInfoList);

            models.add(modelMap);
        }
        return models;
    }

    @NotNull
    private static List<Map<String, Object>> getMaps(QwenSpeechModelEnums model) {
        List<Map<String, Object>> voiceInfoList = new ArrayList<>();
        for (VoiceInfo voiceInfo : model.getVoiceInfos()) {
            Map<String, Object> voiceMap = new HashMap<>();
            voiceMap.put("voice", voiceInfo.getVoice());
            voiceMap.put("description", voiceInfo.getDescription());
            voiceMap.put("applicableScenarios", voiceInfo.getApplicableScenarios());
            voiceMap.put("language", voiceInfo.getLanguage());
            voiceMap.put("defaultSampleRate", voiceInfo.getDefaultSampleRate());
            voiceMap.put("defaultAudioFormat", voiceInfo.getDefaultAudioFormat());
            voiceInfoList.add(voiceMap);
        }
        return voiceInfoList;
    }
}
