package com.esmc.gestionMembre.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataInput {
    private String searchWords;
    private String systeme;
    private String id;
}
