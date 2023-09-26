package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MABanOI implements Serializable {

    private Long id;

    private String referenceMABanOksuOI;

    private Date dateTransaction;

    private Date dateCreate;

    private Date dateUpdate;

    private MaBanKsu maBanKsu;

}
