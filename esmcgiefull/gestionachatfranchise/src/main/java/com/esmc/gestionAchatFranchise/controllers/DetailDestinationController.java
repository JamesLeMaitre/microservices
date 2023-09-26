package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.DetailDestination;
import com.esmc.gestionAchatFranchise.services.DetailDestinationImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/detailDestination")
public class DetailDestinationController extends DataFormatter<DetailDestination> {
    @Autowired
    private DetailDestinationImp detailDestinationImp;

    @PostMapping("/save")
    public Object  addDetailDestination(@RequestBody DetailDestination detailDestination) {
        return renderData(true, detailDestinationImp.addDetailDestination(detailDestination),"add");
    }

    @GetMapping("/getAll")
    public Formatter<List<DetailDestination>> getAllDetailDestination(){
        return renderDataArray(true,detailDestinationImp.getDetailDestination(), "HttpStatus.OK");    }

    @GetMapping("/get/{detailDestinationId}")
    public Object getDetailDestination(@PathVariable("detailDestinationId") Long detailDestinationId) {
        return renderData(true,detailDestinationImp.getDetailDestinationById(detailDestinationId), "get detailDestination");
    }


    @PutMapping("/update/{detailDestinationId}")

    public Object  updateDetailDestination(@PathVariable("detailDestinationId") Long detailDestinationId, @RequestBody DetailDestination detailDestination) {
        detailDestination.setId(detailDestinationId);
        return renderData(true,detailDestinationImp.updateDetailDestination(detailDestination), "HttpStatus.OK");
    }

    @DeleteMapping("/delete/{detailDestinationId}")
    public Formatter<String> deleteDetailDestination(@PathVariable Long detailDestinationId) {
        try{
            detailDestinationImp.deleteDetailDestination(detailDestinationId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }


    }

    @GetMapping("/list/{id}/destination")
    public Formatter<List<DetailDestination>> listDetailDestinationParDestination(@PathVariable Long id) {
        return renderDataArray(true,detailDestinationImp.listDestination(id),"destination");
    }

}
