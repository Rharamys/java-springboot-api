package io.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PublicApiObject {
    public static class Status {
        public Status (Boolean verified, String feedback, Number sentCount) {
            this.verified = verified;
            this.feedback = feedback;
            this.sentCount = sentCount;
        }
        @JsonProperty("verified")
        public Boolean verified;
        @JsonProperty("feedback")
        public String feedback;
        @JsonProperty("sentCount")
        public Number sentCount;

    }
    @JsonProperty("_id")
    public String _id;
    @JsonProperty("_v")
    public Number _v;
    @JsonProperty("user")
    public String user;
    @JsonProperty("text")
    public String text;
    @JsonProperty("updatedAt")
    public String updatedAt;
    @JsonProperty("sendDate")
    public String sendDate;
    @JsonProperty("deleted")
    public Boolean deleted;
    @JsonProperty("source")
    public String source;
    @JsonProperty("status")
    public Status status;

}

