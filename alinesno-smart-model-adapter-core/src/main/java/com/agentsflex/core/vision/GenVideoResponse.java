package com.agentsflex.core.vision;

import lombok.Data;

/**
 * 视频生成响应类
 * 用于封装视频生成结果的相关信息
 * @author luoxiaodong
 */
@Data
public class GenVideoResponse {

    /**
     * 视频URL
     * 用于存储生成的视频的网络地址
     */
    private String videoUrl;

    /**
     * 视频类型
     * 用于描述生成的视频的格式，如MP4，AVI等
     */
    private String videoType ;

    /**
     * 开始时间
     * 记录视频生成开始的时间戳
     */
    private long startTime;

    /**
     * 结束时间
     * 记录视频生成结束的时间戳
     */
    private long endTime;

    /**
     * 用时(秒)
     * 记录视频生成所花费的时间
     */
    private long duration;

}
