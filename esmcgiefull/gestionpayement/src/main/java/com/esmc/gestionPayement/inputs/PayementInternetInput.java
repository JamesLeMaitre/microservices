package com.esmc.gestionPayement.inputs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayementInternetInput {
   private String countryCode;
   private String phone;
   private String codePv;
   private String withdrawalCode;
   private String amount;
   private String isMontant;

}
