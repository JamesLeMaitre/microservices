package com.esmc.gestionMembre.services;


import com.esmc.gestionMembre.entities.Credit;
import com.esmc.gestionMembre.repositories.CreditReppository;
import com.esmc.gestionMembre.serviceInterfaces.CreditServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreditServiceImpl implements CreditServiceInterface {


    @Autowired
    private CreditReppository creditReppository;




    @Override
    public List<Credit> getCreditByCodeMemebre(String codeMemebre) {
        return creditReppository.passifByCodeMemebre(codeMemebre);
    }
}
