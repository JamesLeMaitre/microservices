package com.esmc.gestionAgr.serviceinterfaces;

import com.esmc.gestionAgr.entities.TypeMaBanKsuAgr;

import java.util.List;

public interface TypeMaBanKsuAgrServiceInterface {

    public TypeMaBanKsuAgr addTypeMaBanKsuAgr(TypeMaBanKsuAgr t);
    public TypeMaBanKsuAgr updateTypeMaBanKsuAgr(Long id);
    public void deleteTypeMaBanKsuAgr(Long id);
    public List<TypeMaBanKsuAgr> listTypeMaBanKsuAgr();

    public List<TypeMaBanKsuAgr> listTypeMaBanKsuAgrByTypeMaBanKsu(Long id);

}
