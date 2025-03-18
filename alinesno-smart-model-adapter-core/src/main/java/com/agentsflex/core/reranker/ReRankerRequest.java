package com.agentsflex.core.reranker;

import lombok.Data;

import java.util.List;

/**
 * ReRankerRequest 类用于封装重排序器请求所需的信息。
 * 该类包含了控制重排序过程的参数，如返回结果数量、是否返回文档内容等，
 * 以及重排序所需的查询语句和文档列表。
 */
@Data
public class ReRankerRequest {

    /**
     * 期望返回的重排序后文档的最大数量。
     * 该参数用于限制重排序结果的数量，重排序器将根据相关性得分等规则，
     * 选取前 top_n 个文档作为最终结果返回。
     */
    private int top_n;

    /**
     * 指示是否在重排序结果中返回文档内容。
     * 如果设置为 true，重排序结果中将包含文档的具体内容；
     * 如果设置为 false，则只返回与文档相关的其他信息，如索引、得分等。
     */
    private boolean return_documents;

    /**
     * 用于重排序的查询语句。
     * 重排序器将根据该查询语句与文档的相关性，对文档列表进行重排序。
     * 该查询语句可以是用户输入的问题、关键词等。
     */
    private String query;

    /**
     * 待重排序的文档列表。
     * 列表中的每个元素代表一个文档，重排序器将根据查询语句与这些文档的相关性，
     * 对它们进行重新排序，以确定每个文档与查询的匹配程度。
     */
    private List<String> documents;
}
