package com.esmc.gestionPayement.inputs;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DemandeOtpInput implements Serializable {
//    private String codePays;
//    private String telephone;
private String countryCode;
    private String phone;
}
