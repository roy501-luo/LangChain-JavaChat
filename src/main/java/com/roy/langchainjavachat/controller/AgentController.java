package com.roy.langchainjavachat.controller;

import com.roy.langchainjavachat.annotation.ReWriteBody;
import com.roy.langchainjavachat.service.Assistant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * 工具管理
 *
 * @author roy
 */
@Slf4j
@ReWriteBody
@RestController
@Tag(name = "工具管理")
@RequestMapping("/v1/tool/")
public class AgentController {

    @Resource
    Assistant assistant;

    /**
     * 翻译工具
     * @param text
     * @param language
     * @return 翻译后的结果
     */
    @GetMapping("translate")
    public String translate(@Parameter(description = "问句", required = true, example = "hello") @RequestParam String text,
                            @Parameter(description = "目标语言", required = true, example = "chinese") @RequestParam String language) {
        return assistant.translate(text, language);
    }

}
