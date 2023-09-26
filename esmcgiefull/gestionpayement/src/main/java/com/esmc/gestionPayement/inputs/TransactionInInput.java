package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInInput {
    private TransactionIn[] data;
    private String code;
    private Double  amount;
    private String lot;
    //private String transactionId;
    private String [] listOfDetailsmEnchangeLinked;
}
