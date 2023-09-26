package com.esmc.gestionformation.entities;

import com.esmc.gestionformation.enums.SalleEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleFormation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;

    @ManyToOne
    @JoinColumn(name = "id_type_salle")
    private TypeSalles typeSalles;
    @CreationTimestamp
    private Date createCreate;
    @UpdateTimestamp
    private Date createUpdate;
}
