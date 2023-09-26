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
public class jsonFlboe {
    @Column(nullable = false)
    private jsonFlbChambre[] data;
}
