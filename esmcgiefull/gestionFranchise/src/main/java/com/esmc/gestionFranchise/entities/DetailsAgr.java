package com.esmc.gestionFranchise.entities;

import com.esmc.models.Agr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetailsAgr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean etat;

    private Date dateCreate;

    private Date dateUpdate;
    private Long agr;

    private Long ksu;

}
