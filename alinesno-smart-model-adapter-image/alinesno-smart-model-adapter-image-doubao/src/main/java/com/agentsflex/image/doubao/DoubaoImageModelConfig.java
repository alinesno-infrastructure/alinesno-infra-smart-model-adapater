package com.agentsflex.image.doubao;

import com.agentsflex.core.image.ImageConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DoubaoImageModelConfig extends ImageConfig {

    public DoubaoImageModelConfig() {
        String endpoint = "https://visual.volcengineapi.com";
        String model = DoubaoImageModelEnums.GENERAL_SEEDREAM_3_0_T2I_250415.getModelName();

        setEndpoint(endpoint);
        setModel(model);
    }
}
