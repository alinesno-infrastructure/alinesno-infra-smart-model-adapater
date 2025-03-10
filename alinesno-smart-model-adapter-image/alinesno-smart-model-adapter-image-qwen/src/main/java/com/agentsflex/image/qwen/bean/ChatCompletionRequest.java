package com.agentsflex.image.qwen.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ChatCompletionRequest {
    private String model;
    private List<Message> messages;

    public ChatCompletionRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
}
