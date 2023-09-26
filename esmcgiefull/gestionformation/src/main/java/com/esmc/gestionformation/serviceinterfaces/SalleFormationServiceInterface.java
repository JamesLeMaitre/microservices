package com.esmc.gestionformation.serviceinterfaces;


import com.esmc.gestionformation.entities.SalleFormation;

import java.util.List;

public interface SalleFormationServiceInterface {

    public SalleFormation create(SalleFormation data);
    public SalleFormation update(Long id, SalleFormation s);
    public void delete(Long id);
    public List<SalleFormation> getAll();

    SalleFormation getById(Long id);

    List<SalleFormation> getByIdSalle(Long id);

    List<SalleFormation> getByIdSalle();
}
