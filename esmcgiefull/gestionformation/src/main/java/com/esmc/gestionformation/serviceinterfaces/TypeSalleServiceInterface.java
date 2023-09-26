package com.esmc.gestionformation.serviceinterfaces;

import com.esmc.gestionformation.entities.TypeSalles;

import java.util.List;

public interface TypeSalleServiceInterface {

    public TypeSalles addTypeSalles(TypeSalles s);
    public TypeSalles updateTypeSalles(Long id, TypeSalles s);
    public void deleteTypeSalles(Long id);
    public List<TypeSalles> listTypeSalles();
    
}
