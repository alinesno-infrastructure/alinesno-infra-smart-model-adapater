package com.agentsflex.llm.jdcloud;

import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.message.FunctionCall;
import com.agentsflex.core.message.MessageStatus;
import com.agentsflex.core.parser.AiMessageParser;
import com.agentsflex.core.parser.JSONObjectParser;
import com.agentsflex.core.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Getter
public class JDcloudAiMessageParser implements AiMessageParser {

    private String contentPath;
    private String reasoningContentPath;
    private String indexPath;
    private String totalTokensPath;
    private String promptTokensPath;
    private String completionTokensPath;
    private JSONObjectParser<MessageStatus> statusParser;
    private JSONObjectParser<List<FunctionCall>> callsParser;

    public void setReasoningContentPath(String reasoningContentPath) {
        this.reasoningContentPath = reasoningContentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public void setTotalTokensPath(String totalTokensPath) {
        this.totalTokensPath = totalTokensPath;
    }

    public void setPromptTokensPath(String promptTokensPath) {
        this.promptTokensPath = promptTokensPath;
    }

    public void setCompletionTokensPath(String completionTokensPath) {
        this.completionTokensPath = completionTokensPath;
    }

    public void setStatusParser(JSONObjectParser<MessageStatus> statusParser) {
        this.statusParser = statusParser;
    }

    public void setCallsParser(JSONObjectParser<List<FunctionCall>> callsParser) {
        this.callsParser = callsParser;
    }

    @Override
    public AiMessage parse(JSONObject rootJson) {
        AiMessage aiMessage = new AiMessage();

        if (StringUtil.hasText(this.contentPath)) {
            Object content = JSONPath.eval(rootJson, this.contentPath) ;
            aiMessage.setContent(content == null ? "": content.toString());
        }

        if (StringUtil.hasText(this.reasoningContentPath)) {
            Object reasoningContent = JSONPath.eval(rootJson, this.reasoningContentPath) ;
            aiMessage.setReasoningContent(reasoningContent == null? "" : reasoningContent.toString()) ;
        }

        if (StringUtil.hasText(this.indexPath)) {
            aiMessage.setIndex((Integer) JSONPath.eval(rootJson, this.indexPath));
        }


        if (StringUtil.hasText(promptTokensPath)) {
            aiMessage.setPromptTokens((Integer) JSONPath.eval(rootJson, this.promptTokensPath));
        }

        if (StringUtil.hasText(completionTokensPath)) {
            aiMessage.setCompletionTokens((Integer) JSONPath.eval(rootJson, this.completionTokensPath));
        }

        if (StringUtil.hasText(this.totalTokensPath)) {
            aiMessage.setTotalTokens((Integer) JSONPath.eval(rootJson, this.totalTokensPath));
        }
        //some LLMs like Ollama not response the total tokens
        else if (aiMessage.getPromptTokens() != null && aiMessage.getCompletionTokens() != null) {
            aiMessage.setTotalTokens(aiMessage.getPromptTokens() + aiMessage.getCompletionTokens());
        }

        if (this.statusParser != null) {
            aiMessage.setStatus(this.statusParser.parse(rootJson));
        }

        if (callsParser != null) {
            aiMessage.setCalls(callsParser.parse(rootJson));
        }

        return aiMessage;
    }


    public static JDcloudAiMessageParser getChatGPTMessageParser(boolean isStream) {
        JDcloudAiMessageParser aiMessageParser = new JDcloudAiMessageParser();
        if (isStream) {
            aiMessageParser.setContentPath("$.choices[0].delta.content");
            aiMessageParser.setReasoningContentPath("$.choices[0].delta.reasoning_content");
        } else {
            aiMessageParser.setContentPath("$.choices[0].message.content");
            aiMessageParser.setReasoningContentPath("$.choices[0].message.reasoning_content");
        }

        aiMessageParser.setIndexPath("$.choices[0].index");
        aiMessageParser.setTotalTokensPath("$.usage.total_tokens");
        aiMessageParser.setPromptTokensPath("$.usage.prompt_tokens");
        aiMessageParser.setCompletionTokensPath("$.usage.completion_tokens");

        aiMessageParser.setStatusParser(content -> {
            Object finishReason = JSONPath.eval(content, "$.choices[0].finish_reason");
            if (finishReason != null && StringUtil.hasText(finishReason+"") && finishReason.equals("stop")) {
                return MessageStatus.END;
            }
            return MessageStatus.MIDDLE;
        });

        aiMessageParser.setCallsParser(content -> {
            JSONArray toolCalls = (JSONArray) JSONPath.eval(content, "$.choices[0].message.tool_calls");
            if (toolCalls == null || toolCalls.isEmpty()) {
                return Collections.emptyList();
            }
            List<FunctionCall> functionCalls = new ArrayList<>();
            for (int i = 0; i < toolCalls.size(); i++) {
                JSONObject jsonObject = toolCalls.getJSONObject(i);
                JSONObject functionObject = jsonObject.getJSONObject("function");
                if (functionObject != null) {
                    FunctionCall functionCall = new FunctionCall();
                    functionCall.setName(functionObject.getString("name"));
                    Object arguments = functionObject.get("arguments");
                    if (arguments instanceof Map) {
                        //noinspection unchecked
                        functionCall.setArgs((Map<String, Object>) arguments);
                    } else if (arguments instanceof String) {
                        //noinspection unchecked
                        functionCall.setArgs(JSON.parseObject(arguments.toString(), Map.class));
                    }
                    functionCalls.add(functionCall);
                }
            }
            return functionCalls;
        });

        return aiMessageParser;
    }
}
