package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinetPayItemResponse {
    private String transaction_id;

    private String client_transaction_id;

    private String lot;

    private String amount;

    private String receiver;

    private String receiver_e164;

    private String operator;

    private String sending_status;

    private String transfer_valid;

    private String treatment_status;

    private String comment;

    private String validated_at;

}
