package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfosInput implements Serializable {
    private String countryCode;
    private String phone;

}
