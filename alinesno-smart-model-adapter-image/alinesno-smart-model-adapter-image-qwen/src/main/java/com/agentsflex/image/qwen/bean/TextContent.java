package com.agentsflex.image.qwen.bean;

// 文本类型的内容类
public class TextContent extends Content {
    private String text;

    public TextContent(String text) {
        this.type = "text";
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
