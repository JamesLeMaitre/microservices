package com.esmcgie.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo implements Serializable {
    private String countryCode;
    private String phone;
    private String codePv;
    private String amount;
    private String codeOTP;
}
// le  client pay a l'entreprise