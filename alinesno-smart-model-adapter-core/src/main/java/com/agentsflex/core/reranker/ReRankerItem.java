package com.agentsflex.core.reranker;

import lombok.Data;

/**
 * ReRankerItem 类用于表示重排序器中的一个条目。
 * 该类包含了文档内容、文档索引以及相关性得分等信息，
 * 可用于在重排序过程中对文档进行管理和评估。
 */
@Data
public class ReRankerItem {

    /**
     * 文档的具体内容。
     * 该字段存储了与重排序相关的文档文本信息，
     * 可以是一段文章、一个段落或者其他形式的文本数据。
     */
    private Document document;

    /**
     * 文档在某个集合中的索引位置。
     * 该字段表示文档在原始文档集合中的顺序编号，
     * 用于唯一标识该文档在集合中的位置，方便后续的查找和引用。
     */
    private int index;

    /**
     * 文档与查询的相关性得分。
     * 该字段表示文档与查询之间的相关程度，得分越高表示相关性越强。
     * 该得分是通过重排序算法计算得出的，用于对文档进行排序和筛选。
     */
    private float relevance_score;

    @Data
    public static class Document {
        private String text;
    }
}
