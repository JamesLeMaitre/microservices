package com.esmc.demandeAchatBanKsu.servicesInterface;

import com.esmc.demandeAchatBanKsu.entities.MABanOI;

import java.util.List;

public interface MABanOIService {

    /*
        List All MABanOI
     */
    public List<MABanOI> getMABanOI();
    /*
        Find MABanOI By Id
     */
    public MABanOI MABanOI(Long id);
    /*
        save MABanOI
     */
    public MABanOI saveMABanOI(MABanOI maBanOI);
    /*
        delete MABanOI By Id
     */
    public void deletemaBanOI(Long id);
    /*
        update MABanOI
     */
    public MABanOI updatemaBanOI(MABanOI maBanOI);
}
