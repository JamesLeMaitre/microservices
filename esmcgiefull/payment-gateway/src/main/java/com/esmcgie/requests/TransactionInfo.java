package com.esmcgie.requests;

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
public class TransactionInfo implements Serializable {
    private String countryCode;
    private String phone;
    private String codePv;
    private String withdrawalCode;
    private String amount;
}
// l'entreprise pay au client