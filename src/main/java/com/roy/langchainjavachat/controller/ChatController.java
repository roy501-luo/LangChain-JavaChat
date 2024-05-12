package com.roy.langchainjavachat.controller;

import com.roy.langchainjavachat.annotation.ReWriteBody;
import com.roy.langchainjavachat.model.req.ChatMsgReq;
import com.roy.langchainjavachat.service.Assistant;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(tags = "对话管理")
@RequestMapping("/v1/")
public class ChatController {

    Assistant assistant;

    @Resource
    ChatLanguageModel chatLanguageModel;


    ChatController(Assistant assistant) {
        this.assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(OpenAiChatModel.withApiKey("demo"))
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    @GetMapping("qa")
    @ApiOperation(value = "与大模型对话(后台控制多轮问答)")
    public String llmQA(@ApiParam(value = "问句", required = true) @RequestParam String question,
                        @ApiParam(value = "会话ID", required = true) @RequestParam String sessionId) {
        return assistant.chat(sessionId, question);
    }

    @ApiOperation(value = "与大模型对话(前台控制多轮问答)")
    @PostMapping("chat")
    public String llm(@ApiParam(value = "内容对象", required = true) @RequestBody ChatMsgReq req) {
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
    @ApiOperation(value = "与知识库对话")
    public String knowledgeBaseChat(@ApiParam(value = "问句", required = true) @RequestParam String question) {
        return chatLanguageModel.generate(question);
    }

    @GetMapping("translate")
    @ApiOperation(value = "翻译工具")
    public String translate(@ApiParam(value = "问句", required = true, defaultValue = "hello") @RequestParam String text,
                            @ApiParam(value = "目标语言", required = true, defaultValue = "chinese") @RequestParam String language) {
        return assistant.translate(text, language);
    }

}
