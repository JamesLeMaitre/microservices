package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.BorderauRemiseChequeRepo;
import com.esmc.gestionFranchise.entities.BorderauRemiseCheque;
import com.esmc.gestionFranchise.servicesInterface.BorderauRemiseChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BorderauRemiseChequeServiceImp implements BorderauRemiseChequeService {
    @Autowired
    private BorderauRemiseChequeRepo borderauRemiseChequeRepo;

    @Override
    public List<BorderauRemiseCheque> getBorderauRemiseCheque() {
        return borderauRemiseChequeRepo.findAll();
    }

    @Override
    public BorderauRemiseCheque ajouterBorderauRemiseCheque(BorderauRemiseCheque borderauRemiseCheque) {
        borderauRemiseChequeRepo.save(borderauRemiseCheque);
        return borderauRemiseCheque;
    }

    @Override
    public BorderauRemiseCheque getBorderauRemiseChequebyId(Long id) {
        return borderauRemiseChequeRepo.findById(id).get();
    }

    @Override
    public void deleteBorderauRemiseCheque(Long id) {
        borderauRemiseChequeRepo.deleteById(id);
    }
}
