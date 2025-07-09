# Agent 智能体

`AI 智能体` 是一个抽象的概念，目前业内也有许多不同的定义和解读。

智能体笔者理解为一个 "人"，它有身份、有姓名、有记忆、能执行。在执行的过程中，可以进行规划（Planning）、调用工具（Tools）或者批判性思考（Critical Thinking）。

而让 Agent 进行规划（Planning）、调用工具（Tools）或者批判性思考（Critical Thinking）完全由开发者自己决定的，而非 alinesno-smart-model-adapter 框架。

因此，在 alinesno-smart-model-adapter 中，Agent 类定义如下：

```java
public abstract class Agent {
    private Object id;
    private String name;
    private ContextMemory memory;

    public abstract Output execute(Map<String, Object> variables, Chain chain);
}
```

- id: 定义了智能体的 id，每个智能体的 id 应该是唯一的。
- name： 智能体的姓名（或者别名）
- memory：记忆存储器
- `execute(Map<String, Object> variables, Chain chain)`：执行，由子类去实现。

## 示例代码

自定义一个 Agent，用于根据 ddl 描写信息，给出 SQL 内容：

```java
public class SampleLlmAgent extends LLMAgent {

    public SampleLlmAgent(Llm llm) {
        this.llm = llm;
        this.prompt = "您现在是一个 MySQL 数据库架构师，请根据如下的表结构信息，" +
            "帮我生成可以执行的 DDL 语句，以方便我用于创建 MySQL 的表结构。\n" +
            "注意：\n" +
            "请直接返回 DDL 内容，不需要解释，不需要以及除了 DDL 语句以外的其他内容。\n" +
            "\n以下是表信息的内容：\n\n{ddlInfo}";
    }

    @Override
    protected Output onMessage(AiMessage aiMessage) {
        String sqlContent = aiMessage.getContent()
            .replace("```sql", "")
            .replace("```", "");
        return Output.ofValue(sqlContent);
    }
}
```
接下来，我们执行这个智能体：

```java
public static void main(String[] args) {
    Llm llm = new OpenAiLlm.of("sk-rts5NF6n*******");

    SampleLlmAgent agent = new SampleLlmAgent(llm);

    String ddlInfo = "表名 student，字段 id,name";

    Map<String, Object> variables = new HashMap<>();
    variables.put("ddlInfo", ddlInfo);

    Output output = agent.execute(variables, null);
    System.out.println(output.getValue());
}
```

` System.out.println` 输出结果如下：

```sql
CREATE TABLE `student` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '学生ID',
  `name` VARCHAR(255) COMMENT '学生姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
