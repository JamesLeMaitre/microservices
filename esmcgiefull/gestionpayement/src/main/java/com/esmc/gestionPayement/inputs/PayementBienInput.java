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
public class PayementBienInput implements Serializable {
    private String countryCode;
    private String phone;
    private String codePv;
    private String amount;
    private String codeOTP;

    private Long idKsu=0l;
    private Long idTe=0l;

}
