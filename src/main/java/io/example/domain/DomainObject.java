package io.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DomainObject {
    @Id
    @Column(name="anyAttribute")
    @JsonProperty("anyAttribute")
    public String anyAttribute;

}