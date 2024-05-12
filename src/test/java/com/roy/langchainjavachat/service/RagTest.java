package com.roy.langchainjavachat.service;

import cn.hutool.core.io.resource.ClassPathResource;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.bge.small.en.v15.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RagTest {

    @Test
    public void Advanced_RAG_with_Metadata_Example() {

//       1、 load——文档加载
        Document document = FileSystemDocumentLoader.loadDocument(new ClassPathResource("/documents/test.txt").getAbsolutePath(), new TextDocumentParser());

//       2、 splitter——文段拆分
        DocumentSplitter recursive = DocumentSplitters.recursive(100, 10);
        List<TextSegment> segments = recursive.split(document);


//       3、 embedding-向量化并存储
        EmbeddingModel embeddingModel = new BgeSmallEnV15QuantizedEmbeddingModel();
        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        embeddingStore.addAll(embeddings, segments);

//       4、 retrieve 召回
        String question = "什么是Prompt工程";
        Embedding query = embeddingModel.embed(question).content();
        List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(query, 1);

        StringBuilder context = new StringBuilder();
        for (EmbeddingMatch<TextSegment> item : relevant) {
            context.append(item.embedded().text());
        }

        EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);
        System.out.println(embeddingMatch.score());
        System.out.println(embeddingMatch.embedded().text());

//       5、 prompt
        PromptTemplate from = PromptTemplate.from("<指令>根据已知信息，简洁和专业的来回答问题。如果无法从中得到答案，请说 “根据已知信息无法回答该问题”，答案请使用中文。 </指令>" +
                "<已知信息>{{context}}</已知信息>" +
                "<问题>{{question}}</问题>");

        Map<String, Object> promptInputs = new HashMap<>();
        promptInputs.put("question", question);
        promptInputs.put("context", context);

        Prompt apply = from.apply(promptInputs);

        System.out.println("----------------------------------");
        System.out.println("User：" + apply.text());
//       chat 输入大模型
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .logRequests(true)
                .logResponses(true)
                .build();

        Response<AiMessage> generate = model.generate(apply.toUserMessage());
        System.out.println("AI：" + generate.content().text());

    }
}
