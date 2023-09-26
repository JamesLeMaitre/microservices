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
public class BanKsu implements Serializable {

    private Long id;

    private String codeBanKsu;

    private int quantite;

    private int total;

    private Date dateAchat;

    private Date dateCreate;

    private Date dateUpdate;

    private MaBanKsu maBanKsu;

    private Long supportMarchandEnchage;

}
