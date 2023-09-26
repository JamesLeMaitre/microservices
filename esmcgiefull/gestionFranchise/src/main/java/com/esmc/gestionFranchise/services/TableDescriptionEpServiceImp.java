package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.TableDescriptionEpRepo;
import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.esmc.gestionFranchise.servicesInterface.TableDescriptionEpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TableDescriptionEpServiceImp implements TableDescriptionEpService {

    @Autowired
    private TableDescriptionEpRepo tableDescriptionEpRepo;
    @Override
    public List<TableDescriptionEp> getTableDescriptionEp() {
        return tableDescriptionEpRepo.findAll();
    }

    @Override
    public List<TableDescriptionEp> getTableDescriptionEpbyManuel(Long id) {
        return tableDescriptionEpRepo.getTableaubyManuel(id);
    }

    @Override
    public TableDescriptionEp ajouterTableDescriptionEp(TableDescriptionEp tableDescriptionEp) {
        tableDescriptionEp.setDateCreate(new Date());
        tableDescriptionEp.setDateUpdate(new Date());
        return tableDescriptionEpRepo.save(tableDescriptionEp);
    }

    @Override
    public TableDescriptionEp getTableDescriptionEpbyId(Long id) {
        return tableDescriptionEpRepo.findById(id).get();
    }

    @Override
    public void deleteTableDescriptionEp(Long id) {
        tableDescriptionEpRepo.deleteById(id);
    }

    @Override
    public List<TableDescriptionEp> listTableDescriptionEp(Long id) {
        return tableDescriptionEpRepo.findTableDescriptionEpBymanuelProcedure(id);
    }
}
