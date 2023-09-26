package com.esmc.gestionFranchise.entities.organev2;

import com.esmc.gestionFranchise.entities.AgentRecruteur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgentPoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgentPoste;

    private Long idFranchise;

    private Long idKsu;

    private Long idDetailAgr;

    @Column(name = "id_detail_agr_franchise")
    private Long idDetailAgrFranchise;

    @ManyToOne
    @JoinColumn(name = "id_poste")
    private Poste poste;

    @ManyToOne
    @JoinColumn(name = "id_agent_recruteur")
    private AgentRecruteur agentRecruteur;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
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
