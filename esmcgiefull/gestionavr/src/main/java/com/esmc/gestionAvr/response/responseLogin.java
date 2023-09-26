package com.esmc.gestionAvr.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class responseLogin {
    private String accessToken;
    private String refreshToken;
}
