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
package com.agentsflex.core.vision;

import com.agentsflex.core.document.Document;
import com.agentsflex.core.llm.ChatOptions;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.LlmConfig;
import com.agentsflex.core.llm.StreamResponseListener;
import com.agentsflex.core.llm.embedding.EmbeddingOptions;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.core.prompt.Prompt;
import com.agentsflex.core.store.VectorData;
import lombok.Getter;

@Getter
public abstract class BaseVision<T extends LlmConfig> implements Llm {

    protected T config;

    public BaseVision(T config) {
        this.config = config;
    }

    @Override
    public VectorData embed(Document document, EmbeddingOptions options) {
        return null;
    }

    /**
     * 文生成视频
     * @param prompt
     * @param options
     * @return
     */
    public GenVideoResponse textToVideo(String prompt, ChatOptions options){
        return null ;
    }


    /**
     * 图生成视频
     * @param prompt
     * @param options
     * @return
     */
    public GenVideoResponse imageToVideo(String prompt, String imageUrl ,  ChatOptions options){
        return null ;
    }

    @Override
    public AiMessageResponse chat(Prompt prompt, ChatOptions options) {
        return null;
    }

    @Override
    public void chatStream(Prompt prompt, StreamResponseListener listener, ChatOptions options) {

    }

}
