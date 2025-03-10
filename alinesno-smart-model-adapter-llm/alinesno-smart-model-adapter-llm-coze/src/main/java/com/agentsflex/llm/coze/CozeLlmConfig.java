/*
 *  Copyright (c) 2023-2025, alinesno-smart-model-adapter (fuhai999@gmail.com).
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
package com.agentsflex.llm.coze;

import com.agentsflex.core.llm.LlmConfig;

/**
 * @author yulsh
 */
public class CozeLlmConfig extends LlmConfig {

    private final String DEFAULT_CHAT_API = "/v3/chat";
    private final String DEFAULT_ENDPOINT = "https://api.coze.cn";
    private String chatApi;


    private String defaultBotId;

    private String defaultConversationId;

    private String defaultUserId;

    private boolean stream;

    public CozeLlmConfig() {
        this.setChatApi(DEFAULT_CHAT_API);
        this.setEndpoint(DEFAULT_ENDPOINT);
    }

    public void setChatApi(String chatApi) {
        this.chatApi = chatApi;
    }

    public String getChatApi() {
        return chatApi;
    }

    public String getDefaultBotId() {
        return defaultBotId;
    }

    public void setDefaultBotId(String defaultBotId) {
        this.defaultBotId = defaultBotId;
    }

    public String getDefaultConversationId() {
        return defaultConversationId;
    }

    public void setDefaultConversationId(String defaultConversationId) {
        this.defaultConversationId = defaultConversationId;
    }

    public String getDefaultUserId() {
        return defaultUserId;
    }

    public void setDefaultUserId(String defaultUserId) {
        this.defaultUserId = defaultUserId;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }
}
