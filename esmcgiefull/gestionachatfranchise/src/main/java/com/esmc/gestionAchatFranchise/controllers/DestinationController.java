package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Destination;
import com.esmc.gestionAchatFranchise.services.DestinationImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/destination")
public class DestinationController extends DataFormatter<Destination> {

    @Autowired
    private DestinationImp destinationImp;

    @PostMapping("/save")
    public Object addDestination(@RequestBody Destination destination) {


        try{
            Destination destination1= destinationImp.addDestination(destination);
            return  renderData(true,destination1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<Destination>> getAllDestination(){
        return renderDataArray(true,destinationImp.getDestination(),"liste destination");
    }

    @GetMapping("/get/{destinationId}")
    public Object getDestination(@PathVariable("destinationId") Long destinationId) {
        Destination destination1=destinationImp.getDestinationById(destinationId);
        return renderStringData(true, String.valueOf(destination1), "getId");
    }


    @PutMapping("/update/{destinationId}")

    public Object updateDestination(@PathVariable("destinationId") Long destinationId, @RequestBody Destination destination) {


        try{
            Destination destination1 = destinationImp.getDestinationById(destinationId);
            if(destination1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            destination.setId(destinationId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,destinationImp.updateDestination(destination1), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{destinationId}")
    public Formatter<String> deleteDestination(@PathVariable Long destinationId) {


        try{   destinationImp.deleteDestination(destinationId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }


}
