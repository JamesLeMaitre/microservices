package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinetPayTransactionCreateResponse {
    private TransactionIn[][] data;
    private String code;
    private String  message;
}
