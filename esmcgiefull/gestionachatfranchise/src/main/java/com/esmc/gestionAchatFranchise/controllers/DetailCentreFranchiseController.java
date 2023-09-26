package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.DetailCentreFranchise;
import com.esmc.gestionAchatFranchise.services.DetailCentreFranchiseImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/detailCentreFranchise")
public class DetailCentreFranchiseController extends DataFormatter {
  /*  @Autowired
    private DetailCentreFranchiseImp detailCentreFranchiseImp;

    @PostMapping("/save")
    public Object addDetailCentreFranchise(@RequestBody DetailCentreFranchise detailCentreFranchise) {
        return renderData(true,detailCentreFranchiseImp.addDetailCentreFranchise(detailCentreFranchise),"add");
    }

    @GetMapping("/getAll")
    public Formatter<List<DetailCentreFranchise>> getAllDetailCentreFranchise(){
        return renderDataArray(true,detailCentreFranchiseImp.getDetailCentreFranchise(), "get All");
    }

    @GetMapping("/get/{detailCentreFranchiseId}")
    public Object getDetailCentreFranchise(@PathVariable("detailCentreFranchiseId") Long detailCentreFranchiseId) {
        return renderData(true, detailCentreFranchiseImp.getDetailCentreFranchiseById(detailCentreFranchiseId), "object");
    }


    @PutMapping("/update/{detailCentreFranchiseId}")
    public Object updateDetailCentreFranchise(@PathVariable("detailCentreFranchiseId") Long detailCentreFranchiseId, @RequestBody DetailCentreFranchise detailCentreFranchise) {
        detailCentreFranchise.setId(detailCentreFranchiseId);
        return renderData(true,detailCentreFranchiseImp.updateDetailCentreFranchise(detailCentreFranchise), "update");
    }

    @DeleteMapping({"/delete/{detailCentreFranchiseId}"})
    public Object  deleteTypeFranchise(@PathVariable Long detailCentreFranchiseId) {


        try{
            detailCentreFranchiseImp.deleteDetailCentreFranchise(detailCentreFranchiseId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/list/{id}/centreFranchise")
    public Formatter<List<DetailCentreFranchise>> listDetailCentreFranchiseParCentreFranchise(@PathVariable Long id) {
        return renderDataArray(true,detailCentreFranchiseImp.listCentreFranchise(id),"list");
    }

    @GetMapping("/list/{id}/canton")
    public Formatter<List<DetailCentreFranchise>> listDetailCentreParCanton(@PathVariable Long id) {
        return renderDataArray(true,detailCentreFranchiseImp.listCanton(id),"list");
    }

    @GetMapping("/list/{id}/{id2}")
    public Formatter<List<DetailCentreFranchise>> listDetailCentreFranchiseParCentreFranchiseParCanton(@PathVariable Long id, @PathVariable Long id2) {
        return renderDataArray(true,detailCentreFranchiseImp.listCentreFranchiseAndCanton(id, id2),"list");
    }*/
}
