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
package com.agentsflex.llm.gitee;

import com.agentsflex.core.llm.ChatOptions;
import com.agentsflex.core.message.HumanMessage;
import com.agentsflex.core.message.Message;
import com.agentsflex.core.parser.AiMessageParser;
import com.agentsflex.core.parser.impl.DefaultAiMessageParser;
import com.agentsflex.core.prompt.DefaultPromptFormat;
import com.agentsflex.core.prompt.Prompt;
import com.agentsflex.core.prompt.PromptFormat;
import com.agentsflex.core.util.CollectionUtil;
import com.agentsflex.core.util.Maps;

import java.util.List;

public class GiteeAiLLmUtil {

    private static final PromptFormat promptFormat = new DefaultPromptFormat();

    public static AiMessageParser getAiMessageParser(boolean isStream) {
        return DefaultAiMessageParser.getChatGPTMessageParser(isStream);
    }

    public static String promptToPayload(Prompt prompt, GiteeAiLlmConfig config, ChatOptions options, boolean withStream) {
        List<Message> messages = prompt.toMessages();
        HumanMessage humanMessage = (HumanMessage) CollectionUtil.lastItem(messages);
        return Maps.of()
            .set("messages", promptFormat.toMessagesJsonObject(messages))
            .set("model", config.getModel())
            .setIf(withStream, "stream", withStream)
            .setIfNotNull("max_tokens", options.getMaxTokens())
            .setIfNotNull("temperature", options.getTemperature())
            .setIfNotEmpty("tools", promptFormat.toFunctionsJsonObject(humanMessage))
            .setIfContainsKey("tools", "tool_choice", humanMessage.getToolChoice())
            .setIfNotNull("top_p", options.getTopP())
            .setIfNotNull("top_k", options.getTopK())
            .setIfNotEmpty("stop", options.getStop())
            .setIf(map -> !map.containsKey("tools") && options.getTemperature() > 0, "temperature", options.getTemperature())
            .setIf(map -> !map.containsKey("tools") && options.getMaxTokens() != null, "max_tokens", options.getMaxTokens())
            .toJSON();
    }

}
