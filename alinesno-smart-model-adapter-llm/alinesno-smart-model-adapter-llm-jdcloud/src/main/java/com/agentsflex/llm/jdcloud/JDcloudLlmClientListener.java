/*
 *  Copyright (c) 2023-2025, Agents-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agentsflex.llm.jdcloud;

import com.agentsflex.core.llm.ChatContext;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.StreamResponseListener;
import com.agentsflex.core.llm.client.LlmClient;
import com.agentsflex.core.llm.client.LlmClientListener;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.message.AiMessage;
import com.agentsflex.core.parser.AiMessageParser;
import com.agentsflex.core.prompt.HistoriesPrompt;
import com.agentsflex.core.prompt.Prompt;
import com.agentsflex.core.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JDcloudLlmClientListener implements LlmClientListener {

    private final StreamResponseListener streamResponseListener;
    private final Prompt prompt;
    private final AiMessageParser messageParser;
    private final StringBuilder fullMessage = new StringBuilder();
    private final StringBuilder fullReasoningMessage = new StringBuilder();
    private AiMessage lastAiMessage;
    private final ChatContext context;

    private boolean isThink = false ;

    public JDcloudLlmClientListener(Llm llm
        , LlmClient client
        , StreamResponseListener streamResponseListener
        , Prompt prompt
        , AiMessageParser messageParser) {

        this.streamResponseListener = streamResponseListener;
        this.prompt = prompt;
        this.messageParser = messageParser;
        this.context = new ChatContext(llm, client);
    }


    @Override
    public void onStart(LlmClient client) {
        streamResponseListener.onStart(context);
    }

    @Override
    public void onMessage(LlmClient client, String response) {
        if (StringUtil.noText(response) || "[DONE]".equalsIgnoreCase(response.trim())) {
            return;
        }

        try {
            System.out.println(response);
            JSONObject jsonObject = JSON.parseObject(response);

            lastAiMessage = messageParser.parse(jsonObject);

            if(StringUtil.hasText(lastAiMessage.getContent()) && lastAiMessage.getContent().equals("<think>")){
                isThink = true ;
                lastAiMessage.setContent("") ;
            }

            if(StringUtil.hasText(lastAiMessage.getContent()) && lastAiMessage.getContent().equals("</think>")){
                isThink = false ;
                lastAiMessage.setContent("") ;
            }

            if(isThink){
                lastAiMessage.setReasoningContent(lastAiMessage.getContent()) ;
                lastAiMessage.setContent("");
            }else{
                lastAiMessage.setReasoningContent("");
            }

            // 推理内容
            fullMessage.append(lastAiMessage.getContent());
            fullReasoningMessage.append(lastAiMessage.getReasoningContent());

            // 聊天内容
            lastAiMessage.setFullContent(fullMessage.toString());
            lastAiMessage.setFullReasoningContent(fullReasoningMessage.toString());

            AiMessageResponse aiMessageResponse = new AiMessageResponse(prompt, response, lastAiMessage);
            streamResponseListener.onMessage(context, aiMessageResponse);
        } catch (Exception err) {
            streamResponseListener.onFailure(context, err);
        }
    }

    @Override
    public void onStop(LlmClient client) {
        if (lastAiMessage != null) {
            if (this.prompt instanceof HistoriesPrompt) {
                ((HistoriesPrompt) this.prompt).addMessage(lastAiMessage);
            }
        }

        streamResponseListener.onStop(context);
    }

    @Override
    public void onFailure(LlmClient client, Throwable throwable) {
        streamResponseListener.onFailure(context, throwable);
    }

}
