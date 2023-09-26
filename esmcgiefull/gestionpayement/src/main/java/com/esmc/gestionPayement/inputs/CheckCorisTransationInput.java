package com.esmc.gestionPayement.inputs;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckCorisTransationInput implements Serializable {
    private String codeOperation;
}
