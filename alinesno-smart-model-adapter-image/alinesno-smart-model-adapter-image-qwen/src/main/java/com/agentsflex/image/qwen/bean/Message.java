package com.agentsflex.image.qwen.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 消息类
@Data
@NoArgsConstructor
public class Message {

    private String role;
    private List<Content> content;

    public Message(String role, List<Content> content) {
        this.role = role;
        this.content = content;
    }

}
