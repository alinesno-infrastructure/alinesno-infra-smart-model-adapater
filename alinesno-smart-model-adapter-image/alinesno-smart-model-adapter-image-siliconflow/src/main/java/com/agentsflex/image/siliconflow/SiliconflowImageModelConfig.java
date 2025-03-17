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
package com.agentsflex.image.siliconflow;


import com.agentsflex.core.image.ImageConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SiliconflowImageModelConfig extends ImageConfig {

    private Integer numInferenceSteps = 20;
    private Integer guidanceScale = 7;
    private String imageSize = "1024x1024";

    public SiliconflowImageModelConfig() {
        super();

        String endpoint = "https://api.siliconflow.cn";
        String model = SiliconflowImageModels.flux_1_schnell;

        this.setEndpoint(endpoint);
        this.setModel(model);
    }

}
