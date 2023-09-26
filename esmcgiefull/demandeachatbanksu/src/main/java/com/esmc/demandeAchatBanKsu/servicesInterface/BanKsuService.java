package com.esmc.demandeAchatBanKsu.servicesInterface;


import com.esmc.demandeAchatBanKsu.entities.BanKsu;

import java.util.List;

public interface BanKsuService {
    /*
        List All Banksu
     */
   public List<BanKsu> getBanKsu();
    /*
        Find BanKsu By Id
     */

    public BanKsu BanKsu(Long id);

    /*
        save BanKsu
     */
    public BanKsu savebanKsu(BanKsu banKsu);
    /*
        delete BanKsu By id
     */
    public void deletebanksu(Long id);
     /*
        update BanKsu
      */
     public void updatebanKsu(Long id,BanKsu banKsu);

    public String typeKSU(String codeBanKsu);


     public BanKsu listeRecup(String codeBanKsu);

 public  String getAlphaNumeriqueString();

 public boolean getExitingBanKsu(String nom, String prenom, String email);

 public boolean getExitingBanKsuAndIdMaBanksuAndCodeBan(Long idMabanksu, String codeban);


    BanKsu getBanKsuByIdMaBanKsu(Long id);

    BanKsu getBanKsuByCodeBan(String code);
}
