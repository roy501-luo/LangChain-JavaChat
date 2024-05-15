# LangChain-JavaChat

##
基于 ChatGPT 等大语言模型与 LangChain 等应用框架实现，开源、可离线部署的检索增强生成(RAG)大模型知识库和多个Agent实现的项目。

## 快速上手

### 1、在application.yml中设置自己的openapi-key
```
src/main/resources/application.yml

langchain4j:
  open-ai:
    chat-model:
      api-key: sk-*********
```

### 2、启动
[LangChainJavaChatApplication.java](src/main/java/com/roy/langchainjavachat/LangChainJavaChatApplication.java)

### 3、访问 
http://localhost:8083/LangChainJavaChat/swagger-ui/index.html