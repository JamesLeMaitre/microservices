package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abonnement implements Serializable {

    private Long id;

    private String libelle;

    private Date dateCreate;

    private Date dateUpdate;


}
