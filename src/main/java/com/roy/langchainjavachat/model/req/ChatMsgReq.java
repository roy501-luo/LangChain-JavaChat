package com.roy.langchainjavachat.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "对话内容")
public class ChatMsgReq implements Serializable {
    /**
     * 知识库ID
     */
    @ApiModelProperty(value = "知识库ID", required = true)
    private String knowledgeBaseId;
    /**
     * 用户问题
     */
    @ApiModelProperty(value = "用户问题", required = true)
    private String question;
    /**
     * 历史对话
     */
    @ApiModelProperty(value = "历史对话", required = true)
    private List<HistoryDTO> history;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    private String userid;

    /**
     * HistoryDTO
     */
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class HistoryDTO {
        /**
         * role
         */
        private String role;
        /**
         * content
         */
        private String content;
    }

}
