package com.esmc.gestionAvr.putonchain.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepGoingOnChainRequest implements Serializable {
    private int step;
    private Long detailAgr;
    private boolean state ;
    private String data;
}
