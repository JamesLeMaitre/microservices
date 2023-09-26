package com.esmc.gestionAchatFranchise.controllers;


import com.esmc.gestionAchatFranchise.entities.ChaineValeur;
import com.esmc.gestionAchatFranchise.entities.EtapeFranchise;
import com.esmc.gestionAchatFranchise.request.EtapeFranchiseRequest;
import com.esmc.gestionAchatFranchise.services.EtapeFranchiseImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

/**
 * @author Merveil
 * @since 18/05/2022
 * @apiNote all operation related to franchise table  are handle here
 */


@RestController
@RequestMapping(value="/api/etapeFranchise")
public class EtapeFranchiseController extends DataFormatter<EtapeFranchise> {
    @Autowired
    private EtapeFranchiseImp etapeFranchiseImp;

    @PostMapping("/add")
    public Object addEtapeFranchise(@RequestBody EtapeFranchise etapeFranchise) {
        return renderData(true, etapeFranchiseImp.addEtapeFranchise(etapeFranchise), "add sucess");
    }

    @GetMapping("/getAll")
    public Formatter<List<EtapeFranchise>> getAllEtapeFranchise(){
        return renderDataArray(true,etapeFranchiseImp.getEtapeFranchise(), "list franchise");
    }

    @GetMapping("/{etapeFranchiseId}")
    public Object getEtapeFranchise(@PathVariable("etapeFranchiseId") Long etapeFranchiseId) {
        return renderData(true,etapeFranchiseImp.getEtapeFranchiseById(etapeFranchiseId), "object");
    }

    @GetMapping("/getByIdKsu/{ksuEtapeFranchiseId}")
    public Object getEtapeFranchiseByKsuId(@PathVariable("ksuEtapeFranchiseId") Long ksuEtapeFranchiseId) {
        return renderData(true,etapeFranchiseImp.getEtapeFranchiseByIdKsu(ksuEtapeFranchiseId), "get by ksu");
    }

    @PutMapping("/update/{etapeFranchiseId}")
    public Object updateTypeFranchise(@PathVariable("etapeFranchiseId") Long etapeFranchiseId, @RequestBody EtapeFranchise etapeFranchise) {
        etapeFranchise.setId(etapeFranchiseId);
        return renderData(true,etapeFranchiseImp.updateEtapeFranchise(etapeFranchise), "etape by ksu");
    }

    @PutMapping("/updateByKsu/{ksuId}")
    public Object updateEtapeFranchiseParKsu(@PathVariable("ksuId") Long ksuId, @RequestBody EtapeFranchiseRequest request) throws Exception {
        return renderData(true,etapeFranchiseImp.updateEtapeFranchiseByKsu(ksuId,request), "update sucess");
    }

    @PutMapping("/supreme-upgrade/{id}")
    public Object addChaineValeur(@PathVariable("id") Long idKSU,@RequestBody EtapeFranchiseRequest data) throws Exception {
        try{
                return  renderData(true,etapeFranchiseImp.addEtapeFranchisev2(data),"Agent odd add successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{etapeFranchiseId}")
    public Formatter<String> deleteEtapeFranchise(@PathVariable Long etapeFranchiseId) {


        try{
            etapeFranchiseImp.deleteEtapeFranchise(etapeFranchiseId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }
}




