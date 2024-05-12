package com.roy.langchainjavachat.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@AiService
public interface Assistant {

    @SystemMessage("You are a professional translator into {{language}}")
    @UserMessage("Translate the following text: {{text}}")
    String translate(@V("text") String text, @V("language") String language);

    ResponseBodyEmitter chat(String message);

    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

}