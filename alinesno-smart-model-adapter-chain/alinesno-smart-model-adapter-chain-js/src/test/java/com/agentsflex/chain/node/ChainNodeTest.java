package com.agentsflex.chain.node;

import com.agentsflex.core.chain.Chain;
import com.agentsflex.core.chain.ChainEdge;
import com.agentsflex.core.chain.node.EndNode;
import com.agentsflex.core.chain.node.StartNode;

import java.util.HashMap;
import java.util.Map;

public class ChainNodeTest {

    public static void main(String[] args) {
        // 创建执行链
        Chain chain = new Chain();

        // 创建开始节点
        StartNode startNode = new StartNode();
        startNode.setId("1");
        chain.addNode(startNode);

        // 创建中间节点（动态代码节点）
        JsExecNode jsExecNode = new JsExecNode();
        jsExecNode.setId("2");
        jsExecNode.setCode("console.log('hello world')");
        chain.addNode(jsExecNode);

        // 创建结束节点
        EndNode endNode = new EndNode();
        endNode.setId("3");
        endNode.setMessage("success");
        chain.addNode(endNode);

        // 创建 1-2 的边
        ChainEdge edge12 = new ChainEdge();
        edge12.setSource("1");
        edge12.setTarget("2");
        chain.addEdge(edge12);

        // 创建 2-3 的边
        ChainEdge edge23 = new ChainEdge();
        edge23.setSource("2");
        edge23.setTarget("3");
        chain.addEdge(edge23);

        // 执行执行链
        Map<String, Object> result = chain.executeForResult(new HashMap<>());
        System.out.println(result);
    }

}
