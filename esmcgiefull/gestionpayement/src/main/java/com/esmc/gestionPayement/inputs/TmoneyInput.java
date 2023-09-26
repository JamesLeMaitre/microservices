package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TmoneyInput {
    private String phone;
    private Double  amount;

    private String [] listOfDetailsmEnchangeLinked;
}
