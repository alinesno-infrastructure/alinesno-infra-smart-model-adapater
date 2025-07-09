package com.agentsflex.image.qwen.bean;

import lombok.Data;

/**
 * 初始请求响应类，用于解析初始图像生成请求的响应结果。
 */
@Data
public class InitialResponse {
    /**
     * 初始请求的输出信息，包含任务状态和任务 ID。
     */
    private Output output;
    /**
     * 请求的唯一标识，可用于请求明细溯源和问题排查。
     */
    private String request_id;

    /**
     * 初始请求输出信息的内部类，包含任务状态和任务 ID。
     */
    @Data
    public static class Output {
        /**
         * 任务的当前状态，如 PENDING、RUNNING 等。
         */
        private String task_status;
        /**
         * 任务的唯一标识，用于后续查询任务结果。
         */
        private String task_id;
    }
}
