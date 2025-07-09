此框架基于[AgentFlex](https://github.com/agents-flex/agents-flex)二次开发

 
<p align="center">
    <img src="./docs/assets/images/banner.png"/>
</p>


# alinesno-smart-model-adapter is a LLM Application Framework like LangChain base on Java.

---

## Features

- LLM Visit
- Prompt、Prompt Template
- Function Calling Definer, Invoker、Running
- Memory
- Embedding
- Vector Store
- Resource Loaders
- Document
  - Splitter
  - Loader
  - Parser
    - PoiParser
    - PdfBoxParser
- Agent
  - LLM Agent
- Chain
  - SequentialChain
  - ParallelChain
  - LoopChain
  - ChainNode
    - AgentNode
    - EndNode
    - RouterNode
      - GroovyRouterNode
      - QLExpressRouterNode
      - LLMRouterNode

## Simple Chat

use OpenAi LLM:

```java
 @Test
public void testChat() {
    OpenAiLlmConfig config = new OpenAiLlmConfig();
    config.setApiKey("sk-rts5NF6n*******");

    Llm llm = new OpenAiLlm(config);
    String response = llm.chat("what is your name?");

    System.out.println(response);
}
```


use Qwen LLM:

```java
 @Test
public void testChat() {
    QwenLlmConfig config = new QwenLlmConfig();
    config.setApiKey("sk-28a6be3236****");
    config.setModel("qwen-turbo");

    Llm llm = new QwenLlm(config);
    String response = llm.chat("what is your name?");

    System.out.println(response);
}
```


use SparkAi LLM:

```java
 @Test
public void testChat() {
    SparkLlmConfig config = new SparkLlmConfig();
    config.setAppId("****");
    config.setApiKey("****");
    config.setApiSecret("****");

    Llm llm = new SparkLlm(config);
    String response = llm.chat("what is your name?");

    System.out.println(response);
}
```

## Chat With Histories


```java
public static void main(String[] args) {
    SparkLlmConfig config = new SparkLlmConfig();
    config.setAppId("****");
    config.setApiKey("****");
    config.setApiSecret("****");

    Llm llm = new SparkLlm(config);

    HistoriesPrompt prompt = new HistoriesPrompt();

    System.out.println("ask for something...");
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();

    while (userInput != null) {

        prompt.addMessage(new HumanMessage(userInput));

        llm.chatStream(prompt, (context, response) -> {
            System.out.println(">>>> " + response.getMessage().getContent());
        });

        userInput = scanner.nextLine();
    }
}
```

## Function Calling

- step 1: define the function native

```java
public class WeatherUtil {

    @FunctionDef(name = "get_the_weather_info", description = "get the weather info")
    public static String getWeatherInfo(
        @FunctionParam(name = "city", description = "the city name") String name
    ) {
        //we should invoke the third part api for weather info here
        return "Today it will be dull and overcast in " + name;
    }
}

```

- step 2: invoke the function from LLM

```java
 public static void main(String[] args) {
    OpenAiLlmConfig config = new OpenAiLlmConfig();
    config.setApiKey("sk-rts5NF6n*******");

    OpenAiLlm llm = new OpenAiLlm(config);

    FunctionPrompt prompt = new FunctionPrompt("How is the weather in Beijing today?", WeatherUtil.class);
    FunctionResultResponse response = llm.chat(prompt);

    Object result = response.getFunctionResult();

    System.out.println(result);
    //Today it will be dull and overcast in Beijing
}
```


## Communication

- Twitter: https://twitter.com/yangfuhai

## Modules

![](./docs/assets/images/modules.jpg)
