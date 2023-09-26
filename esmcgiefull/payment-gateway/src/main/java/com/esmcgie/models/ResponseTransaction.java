package com.esmcgie.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class    ResponseTransaction implements Serializable {
    private String code;
    private String message;
    private String transactionId;
    @JsonProperty("montant")
    private String amount;
}
