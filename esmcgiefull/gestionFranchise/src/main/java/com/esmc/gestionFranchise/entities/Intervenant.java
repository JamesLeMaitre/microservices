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
public class Intervenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private  String libelle;

    @Column(nullable = false)
    private  boolean etat;

    @ManyToOne
    @JoinColumn(name = "table_description_ep_id")
    private TableDescriptionEp tableDescriptionEp;

/*    @ManyToOne
    @JoinColumn(name = "id_agent_recruteur")
    private AgentRecruteur agentRecruteur;*/

    @ManyToOne
    @JoinColumn(name = "poste_id")
    private Poste poste;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}
