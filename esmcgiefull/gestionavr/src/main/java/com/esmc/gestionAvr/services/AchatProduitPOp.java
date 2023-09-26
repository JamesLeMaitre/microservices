package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.ReferenceTokenOp;
import com.esmc.gestionAvr.inputs.AchatProduitInput;

public interface AchatProduitPOp {
    ReferenceTokenOp achatProduitPreOpEnLigne(AchatProduitInput data);
}
