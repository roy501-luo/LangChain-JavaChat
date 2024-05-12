package com.roy.langchainjavachat.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RagService {

    public ContentRetriever createContentRetriever(List<Document> documents) {

        // 在这里，我们为文档及其嵌入创建并清空内存存储。
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        // 在这里，我们将文档提取到存储中。
        // 在幕后，发生了很多“魔法”，但我们现在可以忽略它。
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);

        // 最后，让我们从嵌入存储创建一个内容检索器。
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
}
