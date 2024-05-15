package com.roy.langchainjavachat.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "对话内容")
public class ChatMsgReq implements Serializable {
    /**
     * 知识库ID
     */
    @Schema(description = "知识库ID")
    private String knowledgeBaseId;
    /**
     * 用户问题
     */
    @Schema(description = "用户问题")
    private String question;
    /**
     * 历史对话
     */
    @Schema(description = "历史对话")
    private List<HistoryDTO> history;
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
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
