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
public class SpaceTypeRequest implements Serializable {
    private String wording;
}
