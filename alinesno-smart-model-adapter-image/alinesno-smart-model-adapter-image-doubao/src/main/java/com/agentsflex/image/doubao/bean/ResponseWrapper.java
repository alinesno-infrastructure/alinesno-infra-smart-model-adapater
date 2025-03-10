package com.agentsflex.image.doubao.bean;

import lombok.Data;

// 根响应对象
@Data
public class ResponseWrapper {
    private int code;
    private ResponseData data;
    private String time_elapsed;
    private String message;
    private String request_id;
    private int status;
}



