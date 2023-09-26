package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.TableDescriptionEp;

import java.util.List;

public interface TableDescriptionEpService {

    List<TableDescriptionEp> getTableDescriptionEp();
    List<TableDescriptionEp> getTableDescriptionEpbyManuel(Long id);
    TableDescriptionEp ajouterTableDescriptionEp(TableDescriptionEp tableDescriptionEp);
    TableDescriptionEp getTableDescriptionEpbyId(Long id);
    void deleteTableDescriptionEp(Long id);
    List<TableDescriptionEp> listTableDescriptionEp(Long id);
}
