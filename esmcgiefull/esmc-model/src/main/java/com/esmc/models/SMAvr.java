package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMAvr implements Serializable {

    private Long id;

    private String codeSMAvr;

    private String libelle;

    private String lieu;

    private String ente;

    private byte[] signature;

    private String piedPage;

    private byte[] caher;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private SupportMarchandEnchage supportMarchandEnchage;
}
