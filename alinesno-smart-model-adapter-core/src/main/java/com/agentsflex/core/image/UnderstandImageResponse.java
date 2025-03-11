package com.agentsflex.core.image;

import com.agentsflex.core.util.Metadata;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UnderstandImageResponse extends Metadata {
    private String response ; // 解析结果
    private boolean error;
    private String errorMessage;

    public static UnderstandImageResponse error(String error) {
        UnderstandImageResponse response = new UnderstandImageResponse();
        response.setError(true);
        response.setErrorMessage(error);
        return response;
    }
}
