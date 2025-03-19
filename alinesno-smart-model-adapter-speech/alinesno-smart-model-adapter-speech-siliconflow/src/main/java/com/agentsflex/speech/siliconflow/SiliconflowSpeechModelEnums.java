package com.agentsflex.speech.siliconflow;

import com.agentsflex.core.speech.VoiceInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luoxiaodong
 */
@Getter
@AllArgsConstructor
public enum SiliconflowSpeechModelEnums {

    FUNAUDIOLLM_SENSEVOICESMALL("FunAudioLLM/CosyVoice2-0.5B", "适用于将语音转换为文本的任务，可用于语音识别、语音记录转文字等场景" , buildSiliconflowVoiceInfo().get("FunAudioLLM/CosyVoice2-0.5B") , false);

    private final String modelName;
    private final String usageScenario;
    private final List<VoiceInfo> voiceInfos;
    private final boolean isOpenSource;

    /**
     * 返回所有枚举对象的Map，包含modelName/descript/isOpenSource
     */
    public static List<Map<String, Object>> getAllModels() {

        List<Map<String, Object> > models = new ArrayList<>();

        for (SiliconflowSpeechModelEnums model : SiliconflowSpeechModelEnums.values()) {

            Map<String, Object> modelMap = new HashMap<>();
            modelMap.put("modelName", model.getModelName());
            modelMap.put("usageScenario", model.getUsageScenario());
            modelMap.put("isOpenSource", model.isOpenSource());

            List<Map<String, Object>> voiceInfoList = getMaps(model);
            modelMap.put("voiceInfos", voiceInfoList);

            models.add(modelMap);
        }

        return models;
    }

    @NotNull
    private static List<Map<String, Object>> getMaps(SiliconflowSpeechModelEnums model) {
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

    // 构建 SILICONFLOW 语音模型的音色信息
    private static Map<String, List<VoiceInfo>> buildSiliconflowVoiceInfo() {
        Map<String, List<VoiceInfo>> voiceInfoMap = new HashMap<>();
        List<VoiceInfo> voices = new ArrayList<>();

        // 男生音色
        voices.add(new VoiceInfo("alex", "沉稳男声", "通用场景", 22050, "mp3"));
        voices.add(new VoiceInfo("benjamin", "低沉男声", "通用场景", 22050, "mp3"));
        voices.add(new VoiceInfo("charles", "磁性男声", "通用场景", 22050, "mp3"));
        voices.add(new VoiceInfo("david", "欢快男声", "通用场景", 22050, "mp3"));

        // 女生音色
        voices.add(new VoiceInfo("anna", "沉稳女声", "通用场景", 22050, "mp3"));
        voices.add(new VoiceInfo("bella", "激情女声", "通用场景", 22050, "mp3"));
        voices.add(new VoiceInfo("claire", "温柔女声", "通用场景", 22050, "mp3"));
        voices.add(new VoiceInfo("diana", "欢快女声", "通用场景", 22050, "mp3"));

        voiceInfoMap.put("FunAudioLLM/CosyVoice2-0.5B", voices);
        return voiceInfoMap;
    }

}
