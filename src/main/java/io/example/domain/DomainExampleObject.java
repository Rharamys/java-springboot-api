package io.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DomainExampleObject {
    @Id
    @Column(name="anyAttribute")
    @JsonProperty("anyAttribute")
    public String anyAttribute;

}