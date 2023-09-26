package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelephoneOffreurOt {
     private String number;
    private String internationalNumber;
    private String nationalNumber;
    private String e164Number;
    private String countryCode;
    private String dialCode;
}
