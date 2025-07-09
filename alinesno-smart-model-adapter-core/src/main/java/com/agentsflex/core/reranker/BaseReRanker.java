package com.agentsflex.core.reranker;

import lombok.Getter;

/**
 * BaseReRanker 是一个抽象类，作为所有重排序器的基类。
 * 它使用泛型类型 T 来表示重排序器的配置，T 必须是 ReRankerConfig 或其子类。
 * 该类定义了重排序操作的基本结构，通过抽象方法 reranker 要求所有具体的重排序器实现重排序逻辑。
 *
 * @param <T> 重排序器的配置类型，必须继承自 ReRankerConfig
 */
@Getter
public abstract class BaseReRanker<T extends ReRankerConfig> implements ReRanker {

    protected T config;

    public BaseReRanker(T config) {
        this.config = config;
    }


}
