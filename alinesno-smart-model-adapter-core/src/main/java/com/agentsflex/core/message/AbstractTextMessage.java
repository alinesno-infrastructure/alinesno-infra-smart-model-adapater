package com.agentsflex.core.message;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AbstractTextMessage extends Message{

    protected String reasoningContent ; // 推理原因
    protected String content;

    @Override
    public Object getMessageContent() {
        return getContent();
    }
}
