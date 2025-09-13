package com.agentsflex.chain.node.test.adapter;

import com.agentsflex.chain.node.GroovyExecNode;
import com.agentsflex.core.chain.Parameter;
import com.agentsflex.core.chain.impl.SequentialChain;
import com.agentsflex.core.chain.node.EndNode;
import com.agentsflex.core.chain.node.LlmStreamNode;
import com.agentsflex.core.chain.node.StartNode;
import com.agentsflex.core.llm.Llm;
import com.agentsflex.llm.siliconflow.SiliconflowLlm;
import com.agentsflex.llm.siliconflow.SiliconflowLlmConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChainNodeGroovy {

//    String groovyCode = """
//            import com.alinesno.infra.smart.assistant.api.ModelNodeDto
//
//            import com.agentsflex.core.llm.Llm
//            import com.agentsflex.core.message.AiMessage
//            import com.agentsflex.llm.deepseek.DeepseekLlm
//            import com.agentsflex.llm.deepseek.DeepseekLlmConfig
//
//            // 创建对象
//            def modelNode = new ModelNodeDto(UUID.randomUUID().toString() , false)
//            modelAdapterLLM.setNode(modelNode)
//
//            def config = new DeepseekLlmConfig(
//                endpoint: "https://dashscope.aliyuncs.com/compatible-mode/v1",
//                apiKey: secretKey?.qwen_key,
//                model: "qwen3-8b"
//            )
//
//            Llm llm = new DeepseekLlm(config)
//
//            def prompt = expertService.clearMessage(datasetKnowledgeDocument + taskInfo.getText())
//            def output = modelAdapterLLM.processStreamSingle(llm , role , prompt , taskInfo)
//
//            taskInfo.setFullContent(output)
//
//            return "操作成功"
//            """ ;

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
        groovyExecNode.setCode("System.out.println('hello world');") ;
        chain.addNode(groovyExecNode);

        LlmStreamNode llmStreamNode = new LlmStreamNode() ;
        llmStreamNode.setId("4");
        llmStreamNode.setUserPrompt("写一份10个字的小说");

        SiliconflowLlmConfig config = new SiliconflowLlmConfig();

        config.setApiKey(System.getenv("ALINESNO_SILICONFLOW_API_KEY")) ;
        config.setModel("Qwen/QwQ-32B");

        config.setDebug(false);

        Llm llm = new SiliconflowLlm(config);

        llmStreamNode.setLlm(llm);

        chain.addNode(llmStreamNode);

        // 创建结束节点
        EndNode endNode = new EndNode();
        List<Parameter> parameters = new ArrayList<>();

        Parameter parameter = new Parameter();
        parameter.setName("result1");
        parameter.setRef("5.output"); // 假设 LlmStreamNode 存入的键名是 "5.output"
        parameters.add(parameter);

        parameter = new Parameter();
        parameter.setName("result2");
        parameter.setRef("4.output"); // 假设 LlmStreamNode 存入的键名是 "5.output"
        parameters.add(parameter);

        endNode.setOutputDefs(parameters);

        endNode.setId("3");
        endNode.setMessage("success");
        chain.addNode(endNode);

        // 执行执行链
        Map<String, Object> result = chain.executeForResult(new HashMap<>());
        System.out.println("Result from EndNode: " + result);

        // 打印内存中的所有内容
        Map<String, Object> memory = chain.getMemory().getAll();
        System.out.println("Memory contents: " + memory);

        Thread.sleep(20 * 1000);
    }

}
