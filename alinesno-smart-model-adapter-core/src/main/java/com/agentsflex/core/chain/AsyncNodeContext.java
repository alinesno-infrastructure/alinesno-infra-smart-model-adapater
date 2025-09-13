package com.agentsflex.core.chain;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

// AsyncNodeContext.java
public class AsyncNodeContext {
    final Chain chain;
    final ChainNode node;
    final Runnable completionCallback;
    ScheduledFuture<?> timeoutFuture;

    public AsyncNodeContext(Chain chain, ChainNode node, Runnable completionCallback) {
        this.chain = chain;
        this.node = node;
        this.completionCallback = completionCallback;
    }

    public void complete(Map<String, Object> result) {
        try {
            chain.getMemory().putAll(result);
            completionCallback.run();
            chain.continueFromNode(node);
        } finally {
            if (timeoutFuture != null) {
                timeoutFuture.cancel(false);
            }
        }
    }

    public void fail(Throwable e) {
        try {
            chain.notifyNodeError(e, node, null);
        } finally {
            if (timeoutFuture != null) {
                timeoutFuture.cancel(false);
            }
        }
    }
}
