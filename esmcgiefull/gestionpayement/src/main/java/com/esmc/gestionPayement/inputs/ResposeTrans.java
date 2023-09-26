package com.esmc.gestionPayement.inputs;

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
public class ResposeTrans implements Serializable {
    private String code;
    private String message;
    private String transactionId;
    @JsonProperty("montant")
    private String amount;
}