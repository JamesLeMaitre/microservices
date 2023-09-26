package com.esmc.gestionte.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectPassifInput {
    @Column(nullable = false)
    private String qr;

    @Column(nullable = false)
    private String code;
}

