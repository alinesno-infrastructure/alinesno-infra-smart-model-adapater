package com.agentsflex.core.image;

import lombok.Data;

import java.io.Serializable;

/**
 * 图像配置类，用于存储图像处理服务的相关配置信息
 * 实现了Serializable接口，以支持对象的序列化和反序列化
 */
@Data
public class ImageConfig implements Serializable {

    // 图像处理服务的访问地址
    private String endpoint ;

    // 图像处理模型的名称
    private String model ;

    // 图像处理服务的API密钥
    private String apiKey ;

    // 访问图像处理服务的密钥
    private String accessKey ;

    // 图像处理服务的密钥，用于验证身份
    private String secretKey ;

}
