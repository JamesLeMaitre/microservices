package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinetPayResponse {
        private String code;
        private String message;
        private CinetPayItemResponse [] data;
}
