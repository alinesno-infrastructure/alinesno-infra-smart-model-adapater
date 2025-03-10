package com.agentsflex.image.doubao.bean;

import lombok.Data;

import java.util.List;

// 响应数据对象
@Data
public class ResponseData {
    private String pe_result;
    private String vlm_result;
    private String llm_result;
    private List<String> image_urls;
    private AlgorithmBaseResp algorithm_base_resp;
    private String rephraser_result;
    private String request_id;
    private List<String> binary_data_base64;
    private String predict_tags_result;
}
