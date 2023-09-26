package com.esmc.demandeAchatBanKsu.servicesInterface;

import com.esmc.demandeAchatBanKsu.entities.TypeMABanKSU;

import java.util.List;

public interface TypeMABanKSUService {
    /*
       List All TypeMABanKSU
    */
    public List<TypeMABanKSU> getTypeMABanKSU();
    /*
       Find TypeMABanKSU By Id
    */
    public TypeMABanKSU findTypeMABanKSUById(Long id);
    /*
     save TypeMABanKSU
  */
    public TypeMABanKSU savetypeMABanKSU(TypeMABanKSU typeMABanKSU);
    /*
       delete TypeMABanKSU By Id
    */
    public void deletetypeMABanKSU(Long id);
    /*
        update TypeMABanKSU
     */
    public TypeMABanKSU updatetypeMABanKSU(TypeMABanKSU typeMABanKSU);
}
