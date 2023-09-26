package com.esmc.gestionAchatFranchise.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class jsonFlbOeCV {

    @Column(nullable = false)
    private String libelle;
    @Column()
    private String code;

    @Column(length = 5000)
    private String description;


    private jsonFlbOeCD [] child;

}
