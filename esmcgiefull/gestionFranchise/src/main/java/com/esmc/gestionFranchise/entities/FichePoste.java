package com.esmc.gestionFranchise.entities;

import com.esmc.gestionFranchise.entities.organev2.Poste;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FichePoste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String libelle;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

/*    @ManyToOne
    @JoinColumn(name = "id_agent_recruteur")
    private  AgentRecruteur agentRecruteur;*/

    @ManyToOne
    @JoinColumn(name = "poste_id")
    private Poste poste;

}
