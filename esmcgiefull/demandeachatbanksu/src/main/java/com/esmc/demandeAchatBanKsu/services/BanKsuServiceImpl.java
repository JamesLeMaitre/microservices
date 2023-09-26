package com.esmc.demandeAchatBanKsu.services;


import com.esmc.demandeAchatBanKsu.entities.BanKsu;
import com.esmc.demandeAchatBanKsu.repositories.BanKsuRepository;
import com.esmc.demandeAchatBanKsu.servicesInterface.BanKsuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Random;


@Service
@Transactional
public class BanKsuServiceImpl implements BanKsuService {
    @Autowired
    private BanKsuRepository banKsuRepository;


    @Override
    public List<BanKsu> getBanKsu() {
        return banKsuRepository.findAll();
    }

    @Override
    public BanKsu BanKsu(Long id) {
        return banKsuRepository.findById(id).get();
    }


    @Override
    public BanKsu savebanKsu(BanKsu banKsu) {
        return banKsuRepository.save(banKsu);
    }

    @Override
    public void deletebanksu(@PathVariable  Long id) {
        banKsuRepository.deleteById(id);
    }

    @Override
    public void updatebanKsu(Long id, BanKsu banKsu) {
        banKsuRepository.save(banKsu);
    }

    @Override
    public String typeKSU(String codeBanKsu){
        return banKsuRepository.typeKsu(codeBanKsu);
    }

    @Override
    public BanKsu listeRecup(String codeBanKsu) {
        return null;
    }


   public  String getAlphaNumeriqueString(){
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i< 9; i++){
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output;
        System.out.println("codeBan : "  + sb.toString());
        return output = sb.toString();

    }

    @Override
    public boolean getExitingBanKsu(String nom, String prenom, String email) {
         BanKsu b = banKsuRepository.getExitingBanKsu(nom, prenom, email);

         if (b != null) {
             return true;
         }
        return false;
    }

    @Override
    public boolean getExitingBanKsuAndIdMaBanksuAndCodeBan(Long idMabanksu, String codeban) {

        BanKsu b = banKsuRepository.getExitingBanKsuByCodeBanAndIdMaBanKsu(idMabanksu, codeban);

        return b != null;
    }

    @Override
    public BanKsu getBanKsuByIdMaBanKsu(Long id) {

        return banKsuRepository.getBanKsuByIdMaBanKsu(id);
    }

    @Override
    public BanKsu getBanKsuByCodeBan(String code) {

        return banKsuRepository.getBanKsuByCodeBan(code);
    }

}
