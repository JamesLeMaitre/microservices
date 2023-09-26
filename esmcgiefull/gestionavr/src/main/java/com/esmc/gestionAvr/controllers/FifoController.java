package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.ResponseMessage.ResponseMessage;
import com.esmc.gestionAvr.dao.VendeursEnAttenteDeQuota;
import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.services.FifoService;
import com.esmc.gestionAvr.servicesInterfaces.FifoInterface;
import com.esmc.gestionAvr.utils.enums.KsuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;


@RestController
@RequestMapping(value = "api/Fifo/")
public class FifoController extends DataFormatter<Fifo> {

    @Autowired
    private FifoService fifoService;

    @Autowired
    private FifoInterface fifoInterface;

    /**
     * Map to save an AvrType
     * @param fifo
     * @return
     */
    @PostMapping(value = "addFifo/{id}/detailAgr")
    public void addFifo(@RequestBody Fifo fifo, @PathVariable Long id) throws Exception {
          fifoInterface.addFifo(fifo, id);
    }

    /**
     * Map to update an Fifo
     *
     * @param id
     * @return
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<Fifo> getFifoById(@PathVariable Long id){
        return new ResponseEntity<Fifo>( fifoInterface.getFifoById(id),HttpStatus.OK);
    }

    /**
     * Map to delete an Fifo
     * @param id
     */
    @DeleteMapping(value = "delete/{id}")
    public void deleteFifo(@PathVariable Long id){
        fifoInterface.deleteFifo(id);
    }

    /**
     * Map to see the list of Fifo
     * @return
     */
    @GetMapping(value = "get/all")
    public ResponseMessage listFifo(){
        return new ResponseMessage(true,fifoInterface.listFifo());
    }

    /**
     * Map to see the list of Fifo by Fifo
     * @return
    @GetMapping(value = "get/{id}/detailAGR")
    public List<Fifo> ListFifoByDetailAgrId(@PathVariable Long id){
        return fifoInterface.ListFifoByDetailAgrId(id);
    }

    *//**
     * Map to see the list of Fifo by ficheODD
     * @return
     *//*
    @GetMapping(value = "get/{id}/ficheODD")
    public List<Fifo> ListFifoByFicheODDId(@PathVariable Long id){
        return fifoInterface.ListFifoByFicheODDId(id);
    }

    *//**
     * Map to see the list of Fifo by ficheODD
     * @return
     *//*
    @GetMapping(value = "get/{id}/detailAGR/{id2}/ficheODD")
    public List<Fifo> ListFifoByByDetailAgrIdAndFicheODDId(@PathVariable Long id,@PathVariable Long id2){
        return fifoInterface.ListFifoByByDetailAgrIdAndFicheODDId(id,id2);
    }*/

    @GetMapping(value = "get/{libelle}/typeAvr")
    public List<Fifo> ListFifoByTypeAvr(@PathVariable String libelle){
        return fifoInterface.ListFifoByTypeAvr(libelle="vente");
    }

    @GetMapping(value = "fifo")
    public void  istBesoinsAvr(){

        fifoService.changement();
    }

    @GetMapping(value = "fifo/actif")
    public List<Fifo> ListFifoActif(){

        return fifoInterface.ListFifoActif();
    }

    @GetMapping(value = "fifo/actif/vente")
    public List<Fifo> ListFifoVenteActif() {
        return fifoInterface.ListFifoVenteActif();
    }

    @GetMapping(value = "fifo/actif/achat")
    public List<Fifo> ListFifoAchatActif() {
        return fifoInterface.ListFifoAchatActif();
    }

    @GetMapping(value = "fifo/inactif")
    public List<Fifo> ListFifoInactif() {
        return fifoInterface.ListFifoInactif();
    }

    @GetMapping(value = "fifo/enAttente")
    public List<VendeursEnAttenteDeQuota> vendeursEnAttenteDeQuotas() {
        return fifoInterface.vendeursEnAttenteDeQuotas();
    }

    @GetMapping(value = "fifo/inactif/vente")
    public List<Fifo> ListFifoVenteInactif() {
        return fifoInterface.ListFifoVenteInactif();
    }


    @GetMapping(value = "fifo/inactif/achat")
    public List<Fifo> ListFifoAchatInactif() {
        return fifoInterface.ListFifoAchatInactif();
    }


    @PostMapping("/")
    public Object saveFifo (@RequestBody Fifo fifo) {
        System.out.println("Inside saveFifo method of Fifo");
        return this.renderData(true,fifoService.saveFifo(fifo),"Fifo created successfully");
    }

    @GetMapping("fifo/getByDetailAgr/{idDetailsAgr}")
    public Object List (@PathVariable Long idDetailsAgr) {

        return this.renderDataArray(true,fifoService.getFifiByIdDetailsAgr(idDetailsAgr),"Fifo created successfully");
    }

    @GetMapping("getNumOrder/idKsu/{idKsu}/idType/{idType}")
    public Object saveFifo (@PathVariable("idKsu")Long id,@PathVariable("idType")Long idType) {
       try {
           Fifo fifo = fifoService.getNumOrdre(id,idType);
           if(fifo == null){
               return renderStringData(false,"","No NumOrder");
           }
           return renderData(true,fifo,"Num Ordre");
       }catch (Exception e){
           return renderStringData(false,"","Error");
       }
    }

    @GetMapping("checkIfExistInTour/idKsu/{idKsu}/ksuType/{ksuType}")
    public Object checkIfExist (@PathVariable("idKsu")Long id, @PathVariable Long ksuType) {
        try {
            boolean fifo = fifoService.checkIfExistInTour(id, ksuType);
            if(fifo){
                return renderStringData(true,"true","Ok, I'm in");
            }
            return renderStringData(false,"false","Not In");
        }catch (Exception e){
            return renderStringData(false,"","Error");
        }
    }


}
