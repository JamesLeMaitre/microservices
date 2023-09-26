package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.BorderauRemiseCheque;

import java.util.List;

public interface BorderauRemiseChequeService {

    List<BorderauRemiseCheque> getBorderauRemiseCheque();
    BorderauRemiseCheque ajouterBorderauRemiseCheque(BorderauRemiseCheque borderauRemiseCheque);
    BorderauRemiseCheque getBorderauRemiseChequebyId(Long id);
    void deleteBorderauRemiseCheque(Long id);
}
