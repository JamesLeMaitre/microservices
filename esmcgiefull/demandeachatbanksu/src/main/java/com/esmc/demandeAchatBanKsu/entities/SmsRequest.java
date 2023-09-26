package com.esmc.demandeAchatBanKsu.entities;

import lombok.Data;

@Data
public class SmsRequest {
    private  String telephone;
    private  String message;
}
