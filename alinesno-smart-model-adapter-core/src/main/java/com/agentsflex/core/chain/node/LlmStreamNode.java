package com.agentsflex.core.chain.node;

import com.agentsflex.core.chain.AsyncNodeContext;
import com.agentsflex.core.chain.Chain;
import com.agentsflex.core.chain.DataType;
import com.agentsflex.core.chain.Parameter;
import com.agentsflex.core.llm.ChatContext;
import com.agentsflex.core.llm.ChatOptions;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.StreamResponseListener;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.message.MessageStatus;
import com.agentsflex.core.prompt.TextPrompt;
import com.agentsflex.core.prompt.template.TextPromptTemplate;
import com.agentsflex.core.util.Maps;
import com.agentsflex.core.util.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class LlmStreamNode extends BaseNode {

    protected Llm llm;
    protected ChatOptions chatOptions = ChatOptions.DEFAULT;
    protected StreamResponseListener listener;
    protected String userPrompt;
    protected TextPromptTemplate userPromptTemplate;

    protected String systemPrompt;
    protected TextPromptTemplate systemPromptTemplate;
    protected String outType = "text"; //text markdown json

    public LlmStreamNode() {
        this.setAsync(true); // 强制设为异步节点
    }


    public LlmStreamNode(Llm llm, String userPrompt) {
        this.llm = llm;
        this.userPrompt = userPrompt;
        this.userPromptTemplate = StringUtil.hasText(userPrompt)
            ? TextPromptTemplate.of(userPrompt) : null;
    }


    public void setLlm(Llm llm) {
        this.llm = llm;
    }

    public void setUserPrompt(String userPrompt) {
        this.userPrompt = userPrompt;
        this.userPromptTemplate = StringUtil.hasText(userPrompt)
            ? TextPromptTemplate.of(userPrompt) : null;
    }

    public void setSystemPrompt(String systemPrompt) {
        this.systemPrompt = systemPrompt;
        this.systemPromptTemplate = StringUtil.hasText(systemPrompt)
            ? TextPromptTemplate.of(systemPrompt) : null;
    }

    public void setChatOptions(ChatOptions chatOptions) {
        if (chatOptions == null) {
            chatOptions = ChatOptions.DEFAULT;
        }
        this.chatOptions = chatOptions;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    @Override
    protected Map<String, Object> execute(Chain chain) {
//        Map<String, Object> parameterValues = chain.getParameterValues(this);
//
//        if (userPromptTemplate == null) {
//            return Collections.emptyMap();
//        }
//
//        TextPrompt userPrompt = userPromptTemplate.format(parameterValues);
//
//        if (systemPromptTemplate != null) {
//            String systemPrompt = systemPromptTemplate.formatToString(parameterValues);
//            userPrompt.setSystemMessage(SystemMessage.of(systemPrompt));
//        }
//
//        // 创建一个 CompletableFuture 用于异步通知
//        CompletableFuture<String> future = new CompletableFuture<>();
//        llm.chatStream(userPrompt, listener, chatOptions);
//        // 同步节点等待 future 完成
//        try {
//            String resultOutput = future.get();
//            System.out.println("resultOutput= " + resultOutput);
//            return Maps.of("output", resultOutput);
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }

        // 1. 准备prompt
        Map<String, Object> params = chain.getParameterValues(this);
        TextPrompt prompt = userPromptTemplate.format(params);


        // 2. 创建完成回调
        AtomicBoolean isCompleted = new AtomicBoolean(false);
        AsyncNodeContext context = new AsyncNodeContext(
            chain,
            this,
            () -> isCompleted.set(true)
        );

        // 3. 发起流式请求
        llm.chatStream(prompt, new StreamResponseListener() {
            private final StringBuilder content = new StringBuilder();

            @Override
            public void onMessage(ChatContext ctx, AiMessageResponse response) {
                AiMessage msg = response.getMessage();
                content.append(msg.getContent());

                // 实时流式输出
                if (msg.getContent() != null) {
                    chain.output(LlmStreamNode.this, msg.getContent());
                }

                if (msg.getStatus() == MessageStatus.END) {
                    context.complete(Maps.of("output", content.toString()));
                }
            }

            @Override
            public void onFailure(ChatContext ctx, Throwable e) {
                context.fail(e);
            }
        }, chatOptions);

        // 4. 返回异步等待标记
        return Chain.ASYNC_WAIT;

    }

    private static Object getOutputDefData(Parameter output, JSONObject data, boolean sub) {
        String name = output.getName();
        DataType dataType = output.getDataType();
        switch (dataType) {
            case Array, Array_Object -> {
                if (output.getChildren() == null || output.getChildren().isEmpty()) {
                    return data.get(name);
                }
                List<Object> subResultList = new ArrayList<>();
                Object dataObj = data.get(name);
                if (dataObj instanceof JSONArray contentFields) {
                    if (!contentFields.isEmpty()) {
                        contentFields.forEach(field -> {
                            if (field instanceof JSONObject) {
                                subResultList.add(getChildrenResult(output.getChildren(), (JSONObject) field, sub));
                            }
                        });
                    }
                }
                return subResultList;
            }
            case Object -> {
                return (output.getChildren() != null && !output.getChildren().isEmpty()) ? getChildrenResult(output.getChildren(), sub ? data : (JSONObject) data.get(name), sub) : data.get(name);
            }
            case String, Number, Boolean -> {
                Object obj = data.get(name);
                return (DataType.String == dataType) ? (obj instanceof String ? obj : "") : (DataType.Number == dataType) ? (obj instanceof Number ? obj : 0) : obj instanceof Boolean ? obj : false;
            }
            case Array_String, Array_Number, Array_Boolean -> {
                Object arrayObj = data.get(name);
                if (arrayObj instanceof JSONArray) {
                    ((JSONArray) arrayObj).removeIf(o -> arrayRemoveFlag(dataType, o));
                    return arrayObj;
                }
                return Collections.emptyList();
            }
            default -> {
                return ""; // FILE和其他不支持的类型，默认空字符串
            }
        }
    }

    private static boolean arrayRemoveFlag(DataType dataType, Object arrayObj) {
        boolean removeFlag = false;
        if (DataType.Array_String == dataType) {
            if (!(arrayObj instanceof String)) {
                removeFlag = true;
            }
        } else if (DataType.Array_Number == dataType) {
            if (!(arrayObj instanceof Number)) {
                removeFlag = true;
            }
        } else {
            if (!(arrayObj instanceof Boolean)) {
                removeFlag = true;
            }
        }
        return removeFlag;
    }

    private static Map<String, Object> getChildrenResult(List<Parameter> children, JSONObject data, boolean sub) {
        Map<String, Object> childrenResult = new HashMap<>();
        children.forEach(child -> {
            String childName = child.getName();
            Object subData = getOutputDefData(child, data, sub);
            if ((subData instanceof JSONObject) && (child.getChildren() != null && !child.getChildren().isEmpty())) {
                getChildrenResult(child.getChildren(), (JSONObject) subData, true);
            } else {
                childrenResult.put(childName, subData);
            }
        });
        return childrenResult;
    }


    @Override
    public String toString() {
        return "LlmNode{" +
            "llm=" + llm +
            ", chatOptions=" + chatOptions +
            ", userPrompt='" + userPrompt + '\'' +
            ", userPromptTemplate=" + userPromptTemplate +
            ", systemPrompt='" + systemPrompt + '\'' +
            ", systemPromptTemplate=" + systemPromptTemplate +
            ", outType='" + outType + '\'' +
            ", description='" + description + '\'' +
            ", parameters=" + parameters +
            ", outputDefs=" + outputDefs +
            ", id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", async=" + async +
            ", inwardEdges=" + inwardEdges +
            ", outwardEdges=" + outwardEdges +
            ", condition=" + condition +
            ", memory=" + memory +
            ", nodeStatus=" + nodeStatus +
            '}';
    }
}
