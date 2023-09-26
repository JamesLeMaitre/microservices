package com.esmc.gestionAvr.servicesInterfaces;



import com.esmc.gestionAvr.entities.TypeAchatEchange;

import java.util.List;

public interface TypeAchatEchangeServiceInterface {

    public TypeAchatEchange addTypeAchatEchange(TypeAchatEchange t);

    public TypeAchatEchange updateTypeAchatEchange(TypeAchatEchange t);

    public void deleteTypeAchatEchange(Long id);

    public List<TypeAchatEchange> listTypeAchatEchange();

    public List<TypeAchatEchange> listTypeAchatEchangeByEchange(Long id);

}
