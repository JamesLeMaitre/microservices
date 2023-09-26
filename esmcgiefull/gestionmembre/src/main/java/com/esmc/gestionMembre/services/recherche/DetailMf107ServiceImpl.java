package com.esmc.gestionMembre.services.recherche;

import com.esmc.gestionMembre.entities.DetailMf107;
import com.esmc.gestionMembre.repositories.DetailMf107Repository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.DetailMf107ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Amemorte99
 */
@Transactional
@Service
public class DetailMf107ServiceImpl implements DetailMf107ServiceInterface {

    @Autowired
    private DetailMf107Repository detailMf107Repository;




    @Override
    public List<DetailMf107> getDetailMf107ByCode(String nomOrCode) {
        return detailMf107Repository.findDetailMf107ByCode(nomOrCode);
    }


}
