package com.agentsflex.core.reranker;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 重排序返回结果
 * 该类用于封装重排序操作完成后返回的相关信息，
 * 包含重排序任务的唯一标识、重排序后的条目列表以及令牌计数信息。
 *
 * @author luoxiaodong
 */
@ToString
@Data
public class ReRankerResponse {

    /**
     * 重排序任务的唯一标识。
     * 该字段用于唯一标识一次重排序任务，可用于追踪和管理重排序请求，
     * 在后续的日志记录、结果查询等操作中起到关键作用。
     */
    private String id;

    /**
     * 重排序后的条目列表。
     * 该字段存储了经过重排序算法处理后得到的所有重排序条目，
     * 每个条目包含文档内容、文档索引以及相关性得分等信息，
     * 列表中的条目按照相关性得分等规则进行排序。
     */
    private List<ReRankerItem> result = new ArrayList<>();

    /**
     * 输入的令牌数量。
     * 该字段记录了重排序操作中输入数据所包含的令牌数量，
     * 令牌可以是单词、子词等，用于衡量输入数据的规模。
     */
    private long input_tokens;

    /**
     * 输出的令牌数量。
     * 该字段记录了重排序操作后输出数据所包含的令牌数量，
     * 可用于评估重排序结果的规模。
     */
    private long output_tokens;

    /**
     * 总的令牌数量。
     * 该字段表示输入令牌数量和输出令牌数量的总和，
     * 可用于统计整个重排序过程中涉及的令牌总量。
     */
    private long total_tokens;
}
