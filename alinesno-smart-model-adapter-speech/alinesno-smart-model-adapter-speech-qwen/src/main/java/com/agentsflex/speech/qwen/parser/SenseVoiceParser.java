package com.agentsflex.speech.qwen.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SenseVoiceParser {

    private static final List<String> EMOTION_LIST = Arrays.asList("NEUTRAL", "HAPPY", "ANGRY", "SAD");
    private static final List<String> EVENT_LIST = Arrays.asList("Speech", "Applause", "BGM", "Laughter");
    private static final List<String> ALL_TAGS = Arrays.asList(
            "Speech", "Applause", "BGM", "Laughter", "NEUTRAL", "HAPPY", "ANGRY", "SAD", "SPECIAL_TOKEN_1");

    /**
     * 本工具用于解析 sensevoice 识别结果
     * @param data json格式的sensevoice转写结果
     * @param keepTrans 是否保留转写文本
     * @param keepEmotions 是否保留情感标签
     * @param keepEvents 是否保留事件标签
     * @return
     */
    public static JsonObject parseSenseVoiceResult(JsonObject data, boolean keepTrans, boolean keepEmotions, boolean keepEvents) {

        List<String> tagsToCleanup = ALL_TAGS.stream()
                .flatMap(tag -> Stream.of("<|" + tag + "|> ", "<|/" + tag + "|>", "<|" + tag + "|>"))
                .collect(Collectors.toList());

        JsonArray transcripts = data.getAsJsonArray("transcripts");

        for (JsonElement transcriptElement : transcripts) {
            JsonObject transcript = transcriptElement.getAsJsonObject();
            JsonArray sentences = transcript.getAsJsonArray("sentences");

            for (JsonElement sentenceElement : sentences) {
                JsonObject sentence = sentenceElement.getAsJsonObject();
                String text = sentence.get("text").getAsString();

                if (keepEmotions) {
                    extractTags(sentence, text, EMOTION_LIST, "emotion");
                }

                if (keepEvents) {
                    extractTags(sentence, text, EVENT_LIST, "event");
                }

                if (keepTrans) {
                    String cleanText = getCleanText(text, tagsToCleanup);
                    sentence.addProperty("text", cleanText);
                } else {
                    sentence.remove("text");
                }
            }

            if (keepTrans) {
                transcript.addProperty("text", getCleanText(transcript.get("text").getAsString(), tagsToCleanup));
            } else {
                transcript.remove("text");
            }

            JsonArray filteredSentences = new JsonArray();
            for (JsonElement sentenceElement : sentences) {
                JsonObject sentence = sentenceElement.getAsJsonObject();
                if (sentence.has("text") || sentence.has("emotion") || sentence.has("event")) {
                    filteredSentences.add(sentence);
                }
            }
            transcript.add("sentences", filteredSentences);
        }
        return data;
    }

    private static void extractTags(JsonObject sentence, String text, List<String> tagList, String key) {
        String pattern = "<\\|(" + String.join("|", tagList) + ")\\|>";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);
        Set<String> tags = new HashSet<>();

        while (matcher.find()) {
            tags.add(matcher.group(1));
        }

        if (!tags.isEmpty()) {
            JsonArray tagArray = new JsonArray();
            tags.forEach(tagArray::add);
            sentence.add(key, tagArray);
        } else {
            sentence.remove(key);
        }
    }

    private static String getCleanText(String text, List<String> tagsToCleanup) {
        for (String tag : tagsToCleanup) {
            text = text.replace(tag, "");
        }
        return text.replaceAll("\\s{2,}", " ").trim();
    }
}

