package com.esmc.demandeAchatBanKsu.controllers;


import com.esmc.demandeAchatBanKsu.entities.BanKsu;
import com.esmc.demandeAchatBanKsu.entities.Errors;
import com.esmc.demandeAchatBanKsu.entities.MaBanKsu;
import com.esmc.demandeAchatBanKsu.feign.SupportMarchandEnchageRestClient;
import com.esmc.input.MaBanKsuInput;
import com.esmc.demandeAchatBanKsu.repositories.ErrorRepository;
import com.esmc.demandeAchatBanKsu.servicesInterface.BanKsuService;
import com.esmc.demandeAchatBanKsu.servicesInterface.MaBanKsuService;
import com.esmc.models.SupportMarchandEnchage;
import constants.SupportMarchandConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.Messagerie;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;


@RestController
@RequestMapping("/api/BanKsu")
public class BanKsuControlller extends Messagerie  {

    @Autowired
    private BanKsuService banKsuService;

    @Autowired
    private SupportMarchandEnchageRestClient supportMarchandEnchageRestClient;


    @Autowired
    public MaBanKsuService maBanKsuService;

    @Autowired
    private ErrorRepository errorRepository;

    @PostMapping("/save")
    public Object savebanKsu(@RequestBody MaBanKsuInput banKsuInput){
        try {
            BanKsu bk =new BanKsu();
            MaBanKsu maBanKsu = maBanKsuService.MaBanKsu(banKsuInput.getMabanksu());
        //MaBanKsu maBanKsu = maBanKsuService.getCurrentMaBanKsu();
        bk.setTotal(banKsuInput.getTotal());
        bk.setMaBanKsu(maBanKsu);

       // System.out.println("id MaBAnKsu : "+m.getId());
        int q = 1;
        SupportMarchandEnchage sme = supportMarchandEnchageRestClient.supportMarchandEnchageParLibelle(SupportMarchandConstant.supportMarchandBan);
     //   System.out.println("SM : "+ sme.getId());

        bk.setSupportMarchandEnchage(sme.getId());

        String a = banKsuService.getAlphaNumeriqueString();
        bk.setCodeBanKsu(""+a);
        //bk.setMaBanKsu(m);
        bk.setQuantite(q);
        bk.setDateAchat(new Date());

        /*if (maBanKsu.getTypeMABanKSU().getId() == 1) {
            String Code = "\n" + bk.getMaBanKsu().getNom()+" "+bk.getMaBanKsu().getPrenom()+ " Votre code BAn pour l'achat de votre KSU est : "+bk.getCodeBanKsu();
            sendMail(bk.getMaBanKsu().getEmail(),"Code BAn",Code);
            sendSms(maBanKsu.getTelephone(),Code);
        } else if (maBanKsu.getTypeMABanKSU().getId() == 2 || maBanKsu.getTypeMABanKSU().getId() == 3) {

        }*/

            String Code = "Votre code BAn pour l'achat de votre KSU est : "+bk.getCodeBanKsu();
            if(maBanKsu != null){
               /* if(maBanKsu.getEmail() != null){
                   // sendMail(maBanKsu.getEmail(),"Code BAn",Code);
                }*/
                if(maBanKsu.getTelephone() != null){
                    sendSms(maBanKsu.getTelephone(),Code);
                }
            }
            BanKsu bank = banKsuService.savebanKsu(bk);

            Double montant = (double) bank.getTotal();

            supportMarchandEnchageRestClient.debitSMPourAchatKsu(bank.getSupportMarchandEnchage(), montant, bank.getId());

       return  bank;

        } catch (Exception handle){
            HttpStatus request = HttpStatus.BAD_REQUEST;
            StringWriter sw = new StringWriter();
            handle.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(handle);
            Errors er = new Errors();
            er.setMessage(handle.getMessage());
            er.setHttpStatus(request);
            er.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
            er.setThrowable(exceptionAsString);
            errorRepository.save(er);
            return null;
        }
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<BanKsu>>getBanKsu(){
        return new ResponseEntity<List<BanKsu>>(banKsuService.getBanKsu(),HttpStatus.OK);
    }

   /* @GetMapping("/getAll")
    public ResponseMessage getBan(){
        return new ResponseMessage(true,banKsuService.getBanKsu());
    }*/

    @GetMapping("/api/BanKsu")
    public ResponseEntity<BanKsu> banKsu(@PathVariable("id") Long id){
        return new ResponseEntity<BanKsu>( banKsuService.BanKsu(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BanKsu> updatebanKsu(@PathVariable("id") Long id, @RequestBody BanKsu banKsu ){
       banKsu.setId(id);
        return new ResponseEntity<>( banKsuService.savebanKsu(banKsu),HttpStatus.OK);
    }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<BanKsu> deletebanKsu(@PathVariable Long id ){
       banKsuService.deletebanksu(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

   @GetMapping("/api/BanKsu/update/{id}")
   public String recup(@PathVariable("codeBanKsu") String BanKsu){
        return banKsuService.typeKSU(BanKsu);

   }
    @GetMapping("/listeRecup/{codeBanKsu}")
   public BanKsu listeRecup(@PathVariable String codeBanKsu){
        return  banKsuService.listeRecup(codeBanKsu);
   }

    @GetMapping("/code_ban/{codeBanKsu}")
    public BanKsu getBanKsuByCodeBan(@PathVariable("codeBanKsu") String codeBanKsu){
        return  banKsuService.getBanKsuByCodeBan(codeBanKsu);
    }

    @GetMapping("/codeBanKsu")
   public String codeBan() {
        return banKsuService.getAlphaNumeriqueString();
   }

    @GetMapping("/exist/{nom}/{prenom}/{email}")
    public boolean getExitingBanKsu(@PathVariable("nom") String nom, @PathVariable("prenom") String prenom, @PathVariable("email") String email) {

        try {
            return banKsuService.getExitingBanKsu(nom, prenom, email);
        } catch (Exception handle){
            HttpStatus request = HttpStatus.BAD_REQUEST;
            StringWriter sw = new StringWriter();
            handle.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(handle);
            Errors er = new Errors();
            er.setMessage(handle.getMessage());
            er.setHttpStatus(request);
            er.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
            er.setThrowable(exceptionAsString);
            errorRepository.save(er);
            return false;
        }
    }

    @GetMapping("/exist_codde/mabanksu/{idMabanksu}/codeban/{codeban}")
    public boolean getExitingBanKsuAndIdMaBanksuAndCodeBan(@PathVariable Long idMabanksu, @PathVariable("codeban") String codeban) {

        try {
            return banKsuService.getExitingBanKsuAndIdMaBanksuAndCodeBan(idMabanksu, codeban);

        } catch (Exception handle){
            HttpStatus request = HttpStatus.BAD_REQUEST;
            StringWriter sw = new StringWriter();
            handle.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            System.out.println(handle);
            Errors er = new Errors();
            er.setMessage(handle.getMessage());
            er.setHttpStatus(request);
            er.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
            er.setThrowable(exceptionAsString);
            errorRepository.save(er);
            return false;
        }
    }

    @GetMapping("mabanksu/{idMabanksu}")
    public BanKsu getBanKsuByIdMaBanKsu(@PathVariable Long id) {
       return banKsuService.getBanKsuByIdMaBanKsu(id);

    }
}
