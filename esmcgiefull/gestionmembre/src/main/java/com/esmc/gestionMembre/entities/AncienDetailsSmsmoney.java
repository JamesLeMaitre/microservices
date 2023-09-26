package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Table(name = "ancien_details_smsmoney")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AncienDetailsSmsmoney implements Serializable {

    @Id
    @Column(name = "id_detail_smsmoney")
    private  long idDetailSmsmoney;

    @Column(name = "num_bon")
    private  long numBon;

    @Column(name = "code_membre")
    private  String codeMembre;

    @Column(name = "mont_sms")
    private  Double montSms;

    @Column(name = "date_allocation")
    private  Date dateAllocation;

    @Column(name = "id_utilisateur")
    private  long idUtilisateur;

    @Column(name = "code_membre_dist")
    private  String  codeMembreDist;

    @Column(name = "creditcode")
    private  String creditcode;

    @Column(name = "mont_vendu")
    private Double montVendu;

    @Column(name = "solde_sms")
    private  Double soldeSms;

    @Column(name = "origine_sms")
    private  String origineSms;

    @Column(name = "mont_regle")
    private  Double monRegle;

    @Column(name = "type_sms")
    private  Integer typeSms;



    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


}
