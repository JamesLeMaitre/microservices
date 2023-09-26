package com.esmc.gestionPayement.entities.coris;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CorisOutput implements Serializable {

    String code, message , transactionId,montant;

}
