package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsInput {

    @Column(nullable = true)
    private String code;

    @Column(nullable = true)
    private String label;

    @Column(nullable = true, length = 2000)
    private String value;

    @Column(nullable = true)
    private String type;
}