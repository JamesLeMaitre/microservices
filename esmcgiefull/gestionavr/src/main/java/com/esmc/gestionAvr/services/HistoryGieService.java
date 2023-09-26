package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.repositories.HistoryGieAchatRepository;
import com.esmc.gestionAvr.servicesInterfaces.HistoryGieInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HistoryGieService implements HistoryGieInterface {
    @Autowired
    private HistoryGieAchatRepository historyGieAchatRepository;


    @Override
    public Double getAmount() {
        return historyGieAchatRepository.getCumule();
    }
}
