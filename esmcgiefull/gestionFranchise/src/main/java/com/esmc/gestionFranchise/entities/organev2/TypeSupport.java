package com.esmc.gestionFranchise.entities.organev2;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(indexes = @Index(name = "fn_idx_",columnList = "libelle"))
public class TypeSupport implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle",nullable = false)
    private String libelle;

    private String code;

   @OneToMany(mappedBy = "typeSupport")
   @JsonIgnore
    private List<PosteTypeSupport> typeSupports= new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


}
