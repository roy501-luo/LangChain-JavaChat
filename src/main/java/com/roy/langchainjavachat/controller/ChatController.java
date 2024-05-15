package com.roy.langchainjavachat.controller;

import com.roy.langchainjavachat.annotation.ReWriteBody;
import com.roy.langchainjavachat.model.req.ChatMsgReq;
import com.roy.langchainjavachat.service.Assistant;
import com.roy.langchainjavachat.service.RagService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 对话管理
 *
 * @author roy
 */
@Slf4j
@ReWriteBody
@RestController
@Tag(name = "对话管理")
@RequestMapping("/v1/")
public class ChatController {

    @Resource
    Assistant assistant;

    @Resource
    ChatLanguageModel chatLanguageModel;

    @Resource
    RagService ragService;

    @GetMapping("chat")
    @Operation(summary = "与大模型对话(后台控制多轮问答)")
    public String llmQA(@Parameter(description = "问句", required = true) @RequestParam String question,
                        @Parameter(description = "会话ID", required = true) @RequestParam String sessionId) {
        return assistant.chat(sessionId, question);
    }

    @GetMapping("qa")
    @Operation(summary = "单轮问答")
    public String llmQA(@Parameter(description = "问句", required = true) @RequestParam String question) {
        return chatLanguageModel.generate(question);
    }

    @Operation(summary = "与大模型对话(前台控制多轮问答)")
    @PostMapping("chat")
    public String llm(@Parameter(description = "内容对象", required = true) @RequestBody ChatMsgReq req) {
        List<ChatMessage> messageList = new ArrayList<>();
        for (ChatMsgReq.HistoryDTO dto : req.getHistory()) {
            if (dto.getRole().equals("user")) {
                messageList.add(UserMessage.userMessage(dto.getContent()));
            } else {
                messageList.add(AiMessage.aiMessage(dto.getContent()));
            }
        }
        messageList.add(UserMessage.userMessage(req.getQuestion()));
        Response<AiMessage> generate = chatLanguageModel.generate(messageList);
        return generate.content().text();
    }

    @GetMapping("knowledge_base_chat")
    @Operation(summary = "与知识库对话")
    public String knowledgeBaseChat(@Parameter(description = "问句", required = true) @RequestParam String question) {

        // First, let's load documents that we want to use for RAG

        List<Document> documents = FileSystemDocumentLoader.loadDocuments("documents/test.txt");

        // Second, let's create an assistant that will have access to our documents
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey("demo")) // it should use OpenAI LLM
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10)) // it should remember 10 latest messages
                .contentRetriever(ragService.createContentRetriever(documents)) // it should have access to our documents
                .build();

        String chat = assistant.chat("501", question);
        return chatLanguageModel.generate(question);
    }

}
