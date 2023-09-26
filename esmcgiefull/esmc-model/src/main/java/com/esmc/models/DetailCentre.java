package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailCentre {

    private long id;

    private Date dateCreate;

    private Date dateUpdate;

    private Centre centre;

    private AgenceOdd agenceOdd;

}
