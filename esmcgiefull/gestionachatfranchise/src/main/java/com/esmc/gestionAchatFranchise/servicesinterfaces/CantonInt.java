package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Canton;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CantonInt {
    List<Canton> getCanton();

    Canton getCantonById(Long id);

    Canton addCanton(Canton canton);

    Canton updateCanton(Long id, Canton canton);

    void deleteCanton(Long cantonId);

    public  List<Canton> listCommune(Long id);

    int getCountAll();

    int getCountFreeCantonByCommune(Long idFranchise);

    int getCountBuyCantonByCommune(Long idFranchise);
}
