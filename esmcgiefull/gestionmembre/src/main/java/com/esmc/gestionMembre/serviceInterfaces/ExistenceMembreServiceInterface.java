package com.esmc.gestionMembre.serviceInterfaces;//package com.esmc.gestionMembre.serviceInterfaces;

import com.esmc.gestionMembre.entities.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExistenceMembreServiceInterface {

    Physique RedemarrepersonnePhysique(String code);

    List<Physique> listPhysique(String searchWords);

    Page<Physique> pageListPhysique(int offset, int pagesize);

    Morale RedemarrepersonneMorale(String code);

    List<Morale> listMorale(String searchWords);

    Page<Morale> pageListMorale(int offset, int pagesize);

    Page<Membre> pageListMembre(int offset, int pagesize);

    AncienMembre MCNPancienMembre(String code);

    List<AncienMembre> listAncienMembrePP(String searchWords);

    Page<AncienMembre> pageListAncienMembrePP(int offset, int pageSize);

    List<AncienMembre> listAncienMembrePM(String searchWord);

    Page<AncienMembre> pageListAncienMembrePm(int offset, int pageSize);

    Membre ESMCSARLUmembreFondateurMembre (String code);


    List<Membre> listMembre(String searchWords);

    MembreMorale ESMCSARLUmembreFondateurMembreMorale(String code);

    List<MembreMorale> listMembreMorale(String searchWords);

    Page<MembreMorale> pageListMembreMorale(int offset, int pageSize);


    StringBuilder formatNumber(String words);



}
