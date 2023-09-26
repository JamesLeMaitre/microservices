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
public class FicheStock implements Serializable {

    private Long id;

    private String designation;

    private String magasin;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;

}
