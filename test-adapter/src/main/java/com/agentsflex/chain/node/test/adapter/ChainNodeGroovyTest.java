package com.agentsflex.chain.node.test.adapter;

import com.agentsflex.chain.node.GroovyExecNode;
import com.agentsflex.core.chain.impl.SequentialChain;
import com.agentsflex.core.chain.node.EndNode;
import com.agentsflex.core.chain.node.LlmStreamNode;
import com.agentsflex.core.chain.node.StartNode;
import com.agentsflex.core.llm.ChatContext;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.core.llm.StreamResponseListener;
import com.agentsflex.core.llm.response.AiMessageResponse;
import com.agentsflex.llm.siliconflow.SiliconflowLlm;
import com.agentsflex.llm.siliconflow.SiliconflowLlmConfig;

import java.util.HashMap;
import java.util.Map;

public class ChainNodeGroovyTest {

    public static void main(String[] args) throws InterruptedException {

        // 创建执行链
        SequentialChain chain = new SequentialChain();

        // 创建开始节点
        StartNode startNode = new StartNode();
        startNode.setId("1");
        chain.addNode(startNode);

        // 创建中间节点（动态代码节点）
        GroovyExecNode groovyExecNode = new GroovyExecNode();
        groovyExecNode.setId("2");
        groovyExecNode.setCode("System.out.println('groovy hello world')");
        chain.addNode(groovyExecNode);

        LlmStreamNode llmStreamNode = new LlmStreamNode() ;
        llmStreamNode.setId("4");
        llmStreamNode.setUserPrompt("请给我一个5个字");
        llmStreamNode.setListener(new StreamResponseListener() {

            @Override
            public void onStart(ChatContext context) {
                StreamResponseListener.super.onStart(context);
            }

            @Override
            public void onMessage(ChatContext context, AiMessageResponse response) {
                System.out.println("推理=" + response.getMessage().getFullReasoningContent());
                System.out.println("消息=" + response.getMessage().getFullContent());
            }

            @Override
            public void onStop(ChatContext context) {
                StreamResponseListener.super.onStop(context);
            }

            @Override
            public void onFailure(ChatContext context, Throwable throwable) {
                StreamResponseListener.super.onFailure(context, throwable);
            }
        });

        SiliconflowLlmConfig config = new SiliconflowLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;
        config.setModel("Qwen/QwQ-32B");

        config.setDebug(false);

        Llm llm = new SiliconflowLlm(config);

        llmStreamNode.setLlm(llm);

        chain.addNode(llmStreamNode);

        // 创建结束节点
        EndNode endNode = new EndNode();
        endNode.setId("3");
        endNode.setMessage("success");
        chain.addNode(endNode);

        // 执行执行链

       Map<String, Object> result = new HashMap<>() ;
       chain.executeForResult(result) ;
       System.out.println(result);
       System.out.println(chain.getMemory().getAll());

       Thread.sleep(60*1000);
    }

}
