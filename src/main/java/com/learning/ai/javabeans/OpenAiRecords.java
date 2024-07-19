package com.learning.ai.javabeans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenAiRecords {
    public record ModelList (String object, List<Model> data) {
        public record Model (
                String id,
                String object,
                long created,
//                @SerializedName("owned_by")
                String ownedBy){
        }
    }
}
