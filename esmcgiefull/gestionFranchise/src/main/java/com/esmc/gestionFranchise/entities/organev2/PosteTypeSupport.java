package com.esmc.gestionFranchise.entities.organev2;

import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PosteTypeSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderposte_id")
    @JsonIgnore
    private Poste senderPoste;

    @ManyToOne
    @JoinColumn(name = "receiverposte_id")
    @JsonIgnore
    private Poste receiverPoste;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_support_id")
    @JsonIgnore
    private TypeSupport typeSupport;

/*    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poste_id")
    private Poste poste;*/

    @ManyToOne
    @JoinColumn(name = "table_description_ep_id")
    @JsonIgnore
    private TableDescriptionEp tableDescriptionEp;

    private boolean status;
}
