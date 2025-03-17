package com.agentsflex.image.doubao;

import com.agentsflex.core.image.ImageConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DoubaoImageModelConfig extends ImageConfig {

    public DoubaoImageModelConfig() {
        String endpoint = "https://visual.volcengineapi.com";
        String model = DoubaoImageModels.general_v2_1_L;

        setEndpoint(endpoint);
        setModel(model);
    }
}
