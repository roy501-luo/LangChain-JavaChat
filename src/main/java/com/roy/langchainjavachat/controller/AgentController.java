package com.roy.langchainjavachat.controller;

import com.roy.langchainjavachat.annotation.ReWriteBody;
import com.roy.langchainjavachat.service.Assistant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 工具管理
 *
 * @author roy
 */
@Slf4j
@ReWriteBody
@RestController
@Api(tags = "工具管理")
@RequestMapping("/v1/tool/")
public class AgentController {

    @Resource
    Assistant assistant;

    @GetMapping("translate")
    @ApiOperation(value = "翻译工具")
    public String translate(@ApiParam(value = "问句", required = true, defaultValue = "hello") @RequestParam String text,
                            @ApiParam(value = "目标语言", required = true, defaultValue = "chinese") @RequestParam String language) {
        return assistant.translate(text, language);
    }

}
