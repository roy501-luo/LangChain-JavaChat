package com.roy.langchainjavachat.service;

import com.roy.langchainjavachat.model.resp.ModelList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "fast-chat-api", url = "${fast_chat.url}")
public interface FastChatService {
    @GetMapping(value = "/models")
    ModelList models();
}
