package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.TypeEchange;

import java.util.List;

public interface TypeEchangeServiceInterface {
    public TypeEchange addTypeAchat(TypeEchange typeEchange);
    public TypeEchange updateTypeAchat(Long id, TypeEchange typeEchange);

    public TypeEchange getTypeEchange(Long id);

    public void deleteTypeAchat(Long id);
    public List<TypeEchange> getAllTypeAchat();
}
