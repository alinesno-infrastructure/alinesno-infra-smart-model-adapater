package com.agentsflex.core.reranker;

public interface ReRanker {

    /**
     * 执行重排序操作的抽象方法。
     * 该方法接收一个 ReRankerRequest 对象作为输入，该对象包含了重排序所需的查询信息、文档列表等。
     * 具体的重排序器子类需要实现该方法，根据自身的算法和逻辑对输入的请求进行处理，
     * 并返回一个 ReRankerResponse 对象，该对象包含了重排序后的结果、任务标识以及令牌计数等信息。
     *
     * @param request 包含重排序所需信息的请求对象
     * @return 重排序操作的响应对象，包含重排序结果和相关统计信息
     */
    ReRankerResponse reranker(ReRankerRequest request);

}
