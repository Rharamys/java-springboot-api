package io.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PublicApiObject {
    @JsonProperty("count")
    public Number count;
    @JsonProperty("name")
    public String name;
    @JsonProperty("age")
    public Number age;
}

