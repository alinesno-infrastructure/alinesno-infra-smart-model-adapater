package com.agentsflex.core.image;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片理解
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnderstandImageRequest extends BaseImageRequest{
    private String text = "请描述这张图片，并使用中文回复.";
    private String imageUrl;
}
