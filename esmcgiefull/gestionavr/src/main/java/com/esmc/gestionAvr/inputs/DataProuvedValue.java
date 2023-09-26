package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DataProuvedValue {
    private  String libelle;
    private  String idPost;
    private  Boolean aprouve;
}
