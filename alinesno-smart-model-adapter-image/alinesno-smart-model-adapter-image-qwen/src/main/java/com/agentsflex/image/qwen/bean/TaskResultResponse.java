package com.agentsflex.image.qwen.bean;

import lombok.Data;

import java.util.List;

/**
 * 任务结果响应类，用于解析任务完成后的详细结果信息。
 */
@Data
public class TaskResultResponse {
    /**
     * 请求的唯一标识，可用于请求明细溯源和问题排查。
     */
    private String request_id;
    /**
     * 任务的输出信息，包含任务状态、执行时间、结果列表等。
     */
    private Output output;
    /**
     * 输出信息统计，如生成图片的数量。
     */
    private Usage usage;

    /**
     * 任务输出信息的内部类，包含任务的详细执行信息和结果。
     */
    @Data
    public static class Output {
        /**
         * 任务的唯一标识，与初始请求中的任务 ID 一致。
         */
        private String task_id;
        /**
         * 任务的最终状态，如 SUCCEEDED、FAILED 等。
         */
        private String task_status;
        /**
         * 任务的提交时间。
         */
        private String submit_time;
        /**
         * 任务开始执行的时间。
         */
        private String scheduled_time;
        /**
         * 任务完成的时间。
         */
        private String end_time;
        /**
         * 任务结果列表，包含每个结果的详细信息。
         */
        private List<Result> results;
        /**
         * 任务结果统计信息，如成功和失败的任务数量。
         */
        private TaskMetrics task_metrics;
    }

    /**
     * 任务结果的内部类，包含每个结果的详细信息，如原始提示、实际提示、图片 URL 等。
     */
    @Data
    public static class Result {
        /**
         * 原始的输入提示信息。
         */
        private String orig_prompt;
        /**
         * 开启 prompt 智能改写后实际使用的提示信息。
         */
        private String actual_prompt;
        /**
         * 模型生成图片的 URL 地址。
         */
        private String url;
        /**
         * 图像错误码，部分任务执行失败时会返回该字段。
         */
        private String code;
        /**
         * 图像错误信息，部分任务执行失败时会返回该字段。
         */
        private String message;
    }

    /**
     * 任务结果统计信息的内部类，包含总的任务数、成功的任务数和失败的任务数。
     */
    @Data
    public static class TaskMetrics {
        /**
         * 总的任务数。
         */
        private int TOTAL;
        /**
         * 任务状态为成功的任务数。
         */
        private int SUCCEEDED;
        /**
         * 任务状态为失败的任务数。
         */
        private int FAILED;
    }

    /**
     * 输出信息统计的内部类，包含模型生成图片的数量。
     */
    @Data
    public static class Usage {
        /**
         * 模型生成图片的数量。
         */
        private int image_count;
    }
}
