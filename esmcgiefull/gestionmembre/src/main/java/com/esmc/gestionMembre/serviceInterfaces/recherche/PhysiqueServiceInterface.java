package com.esmc.gestionMembre.serviceInterfaces.recherche;


import com.esmc.gestionMembre.entities.Physique;
import org.springframework.data.domain.Page;

public interface PhysiqueServiceInterface {


    Page<Physique> pageListSearchPhysiqueByDateLieu(String search,int offset, int pageSize);

    Physique  getReDeMaRePhysiqueByNom(String nom,String prenom);

    Physique  getReDeMaRePhysiqueByCode(String code);
}
