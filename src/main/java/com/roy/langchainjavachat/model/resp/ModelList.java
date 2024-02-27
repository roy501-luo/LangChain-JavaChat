package com.roy.langchainjavachat.model.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ModelList {

    @JsonProperty("object")
    private String object;
    @JsonProperty("data")
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("object")
        private String object;
        @JsonProperty("created")
        private Integer created;
        @JsonProperty("owned_by")
        private String ownedBy;
        @JsonProperty("root")
        private String root;
        @JsonProperty("parent")
        private Object parent;
        @JsonProperty("permission")
        private List<PermissionDTO> permission;

        @NoArgsConstructor
        @Data
        public static class PermissionDTO {
            @JsonProperty("id")
            private String id;
            @JsonProperty("object")
            private String object;
            @JsonProperty("created")
            private Integer created;
            @JsonProperty("allow_create_engine")
            private Boolean allowCreateEngine;
            @JsonProperty("allow_sampling")
            private Boolean allowSampling;
            @JsonProperty("allow_logprobs")
            private Boolean allowLogprobs;
            @JsonProperty("allow_search_indices")
            private Boolean allowSearchIndices;
            @JsonProperty("allow_view")
            private Boolean allowView;
            @JsonProperty("allow_fine_tuning")
            private Boolean allowFineTuning;
            @JsonProperty("organization")
            private String organization;
            @JsonProperty("group")
            private Object group;
            @JsonProperty("is_blocking")
            private Boolean isBlocking;
        }
    }
}
