package com.esmc.gestionte.controllers;


import com.esmc.gestionte.entities.Promotion;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.services.PromotionImpl;
import com.esmc.gestionte.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/promotion/")
public class PromotionController {


    @Autowired
    private PromotionImpl promotionImpl;

    @GetMapping("getAll")
    public List<Promotion> getOrdre(){
        return  promotionImpl.getPromotion();
    }

    @PostMapping("save")
    public ResponseEntity<?> addNewOrdre(@RequestBody Promotion promotion){
        try {
            promotionImpl.addNewPromotion(promotion);
            return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateOrdre(@PathVariable("id") Long id, @RequestBody Promotion promotion ){
        try {
            promotionImpl.updatePromotion(id,promotion);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteOrdre(@PathVariable ("id") Long id){
        try {
            promotionImpl.deletePromotion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            promotionImpl.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("date_debut/{debut}/date_fin/{defin}/montan_bci/{montantBci}")
    public Double montantPromotion(@PathVariable("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date debut, @PathVariable("defin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date defin, @PathVariable ("montantBci") double montantBci){
        return promotionImpl.montantPromotion(debut, defin, montantBci);
    }

    @GetMapping("bci-ban/{montantBci}")
    public Double montantPromotionBciBanCurrent( @PathVariable ("montantBci") double montantBci){
        Promotion currentPromotion =  promotionImpl.getPromotionTrue();
        return promotionImpl.montantPromotion(currentPromotion.getDateDebut(), currentPromotion.getDateFin(), montantBci);
    }

    @GetMapping("ban-bci/{montantBan}")
    public Double montantPromotionBanBciCurrent( @PathVariable ("montantBan") double montantBan){
        Promotion currentPromotion =  promotionImpl.getPromotionTrue();
        return promotionImpl.montantPromotionBCI(currentPromotion.getDateDebut(), currentPromotion.getDateFin(), montantBan);
    }


    @GetMapping("datedebut/{debut}/datefin/{defin}")
    public Promotion getPromotionByDateDebutAndDateFin(@PathVariable("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut, @PathVariable("defin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {
        return promotionImpl.getPromotionByDateDebutAndDateFin(dateDebut, dateFin);
    }


    @GetMapping("current_promotion")
    public Promotion getPromotionTrue() {
        return promotionImpl.getPromotionTrue();
    }

    //  BAn En BCi

    @GetMapping("date_debut/{debut}/date_fin/{defin}/montan_ban/{montantBan}")
    @ResponseBody
    public Double montantPromotionBCI(@PathVariable("debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date debut, @PathVariable("defin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date defin, @PathVariable ("montantBan") double montantBan){
        return promotionImpl.montantPromotionBCI(debut, defin, montantBan);
    }
}
