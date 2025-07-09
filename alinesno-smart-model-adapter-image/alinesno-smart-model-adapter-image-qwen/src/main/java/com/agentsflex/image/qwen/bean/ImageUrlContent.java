package com.agentsflex.image.qwen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

// 图片URL 类型的内容类
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageUrlContent extends Content {
    private ImageUrl image_url;

    public ImageUrlContent(String url) {
        this.type = "image_url";
        this.image_url = new ImageUrl(url);
    }
}
