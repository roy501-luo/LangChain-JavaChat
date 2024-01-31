package com.roy.langchainjavachat.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ChatMsgReq {

    @JsonProperty("role")
    private String role;

    @JsonProperty("content")
    private String content;
}
