package com.roy.langchainjavachat.controller;

import com.roy.langchainjavachat.annotation.ReWriteBody;
import com.roy.langchainjavachat.model.req.ChatMsgReq;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;

/**
 * 对话管理
 *
 * @author roy
 */
@Slf4j
@ReWriteBody
@RestController
@Api(tags = "对话管理")
@RequestMapping("/v1/chat/")
public class ChatController {

    @Value("${OPENAI_API_KEY}")
    String OPENAI_API_KEY;

    @GetMapping("qa")
    @ApiOperation(value = "与大模型对话(单轮问答)")
    public String llmQA(@ApiParam(value = "问句", required = true) @RequestParam String question) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .baseUrl("https://dgr.life/v1")
                .apiKey(OPENAI_API_KEY) // Please use your own OpenAI API key
                .modelName(GPT_3_5_TURBO)
                .build();

        return model.generate(question);
    }

    @GetMapping("chat")
    @ApiOperation(value = "与大模型对话(多轮问答)")
    public void llm(@ApiParam(value = "问句", required = true) @RequestBody List<ChatMsgReq> req) {
    }

    @GetMapping("knowledge_base_chat")
    @ApiOperation(value = "与知识库对话")
    public String knowledgeBaseChat(@ApiParam(value = "问句", required = true) @RequestParam String question) {
        ChatLanguageModel model = OpenAiChatModel.builder()
                .baseUrl("https://dgr.life/v1")
                .apiKey(System.getenv("OPENAI_API_KEY")) // Please use your own OpenAI API key
                .modelName(GPT_3_5_TURBO)
                .build();

        return model.generate(question);
    }
}
