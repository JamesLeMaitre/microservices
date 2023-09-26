package com.esmc.gestionformation.serviceinterfaces;

import com.esmc.gestionformation.entities.TypeCodes;

import java.util.List;

public interface TypeCodeServiceInterface {
    public TypeCodes addTypeCodes(TypeCodes t);
    public TypeCodes updateTypeCodes(Long id, TypeCodes t);
    public void deleteTypeCodes(Long id);
    public List<TypeCodes> listTypeCodes();
}
