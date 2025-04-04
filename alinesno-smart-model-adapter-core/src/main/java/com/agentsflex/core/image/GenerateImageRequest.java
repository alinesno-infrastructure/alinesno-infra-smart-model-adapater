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
package com.agentsflex.core.image;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 继承自 BaseImageRequest 类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GenerateImageRequest extends BaseImageRequest {

    // 用于描述生成图像的正向提示词
    private String prompt;

    // 用于描述不希望在生成图像中出现的元素的负向提示词
    private String negativePrompt;

    // 图像生成的质量级别
    private String quality;

    // 图像生成的风格
    private String style;

}
