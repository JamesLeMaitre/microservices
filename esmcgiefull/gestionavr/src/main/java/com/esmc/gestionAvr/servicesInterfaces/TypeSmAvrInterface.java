package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.TypeSmAvr;

import java.util.List;

public interface TypeSmAvrInterface {

    public TypeSmAvr saveTypeSmavr(TypeSmAvr t);

    public TypeSmAvr updateTypeSmAvr( TypeSmAvr t);

    public void deleteTypeSmAvr(Long id);

    public List<TypeSmAvr> listTypeSmAvr();

    public TypeSmAvr DetailTypeBon (String code);

    /*public List<TypeSmAvr> listTypeSmAvrBySMAvr(Long id);*/
}
