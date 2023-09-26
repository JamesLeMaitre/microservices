package com.esmc.demandeAchatBanKsu.servicesInterface;

import com.esmc.demandeAchatBanKsu.entities.MaBanKsu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaBanKsuService {
    /*
    List All MaBanKsy
     */
    public List<MaBanKsu> getMaBanKsu();
    /*
    /*
        save MaBanKsy
     */
    public MaBanKsu saveMabanKsu(Long id, MaBanKsu maBanKsu);
    /*
        Find MaBanKsy By Id
     */
    public MaBanKsu MaBanKsu(Long id);
    /*
        delete MaBanKsy By Id
     */
    public void deleteMabanksu(Long id);
    /*
        update MaBanKsy
     */
    public MaBanKsu updateMabanKsu(MaBanKsu maBanKsu);

    public MaBanKsu findMaBanKSUByTypeMaBanKSu(Long id);

    public MaBanKsu getCurrentMaBanKsu();

    MaBanKsu getMaBanKsuByCodeRepresentant(String code);
}
