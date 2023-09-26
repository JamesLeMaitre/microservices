package com.esmc.gestionContrat.entities.annexes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Honoraire {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHonoraire;
    private String montant_mensuel;
    private String periode_tache;
    private String montant_global;
    private Date date_signature;
    private String lieu_signature;
    private Long idDetailsAgr;
}
