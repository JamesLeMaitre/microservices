package com.esmc.demandeAchatBanKsu.services;

import com.esmc.demandeAchatBanKsu.entities.MABanOI;
import com.esmc.demandeAchatBanKsu.repositories.MABanOIRepository;
import com.esmc.demandeAchatBanKsu.servicesInterface.MABanOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class MABanOIServiceImpl implements MABanOIService {

    @Autowired
    private MABanOIRepository maBanOIRepository;

    @Override
    public MABanOI saveMABanOI(MABanOI maBanOI) {
        maBanOI.setDateTransaction(new Date());
        maBanOI.setDateCreate(new Date());
        maBanOI.setDateUpdate(new Date());


        return maBanOIRepository.save(maBanOI);
    }

    @Override
    public List<MABanOI> getMABanOI() {
        return maBanOIRepository.findAll();
    }

    @Override
    public MABanOI MABanOI(Long id) {
        return maBanOIRepository.findById(id).get();
    }



    @Override
    public void deletemaBanOI(@PathVariable Long id) {
        maBanOIRepository.deleteById(id);
    }

    @Override
    public MABanOI updatemaBanOI(MABanOI maBanOI) {
        return maBanOIRepository.save(maBanOI);
    }
}
