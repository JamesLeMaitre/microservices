package com.esmc.gestionKsu.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkActivationInput {
    String codeMembre;
    String secretCode;
    String tempFingerPrintKey;
}
