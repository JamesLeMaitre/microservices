package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.DetailCentre;
import com.esmc.gestionAchatFranchise.services.DetailCentreImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/detailCentre")
public class DetailCentreController extends DataFormatter<DetailCentre> {
   /* @Autowired
    private DetailCentreImp detailCentreImp;

    @PostMapping("/save")
    public Object addDetailCentre(@RequestBody DetailCentre detailCentre) {;
        try{
            DetailCentre detailCentre1= detailCentreImp.addDetailCentre(detailCentre);
            return  renderData(true,detailCentre1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @GetMapping("/getAll")
    public Formatter<List<DetailCentre>> getAllDetailCentre(){
        return renderDataArray(true,detailCentreImp.getDetailCentre(), "list detail center");
    }

    @GetMapping("/get/{detailCentreId}")
    public Object getDetailCentre(@PathVariable("detailCentreId") Long detailCentreId) {
        DetailCentre detailCentre1=detailCentreImp.getDetailCentreById(detailCentreId);
        return renderData(true,detailCentre1, "HttpStatus.OK");
    }

    @PutMapping("/update/{detailCentreId}")
    public Object updateDetailCentre(@PathVariable("detailCentreId") Long detailCentreId, @RequestBody DetailCentre detailCentre) {


        try{
            DetailCentre detailCentre1 = detailCentreImp.getDetailCentreById(detailCentreId);
            if(detailCentre1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            detailCentre.setId(detailCentreId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,detailCentreImp.updateDetailCentre(detailCentre), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }



    }

    @DeleteMapping("/delete/{detailCentreId}")
    public Formatter<String> deleteDetailCentre(@PathVariable Long detailCentreId) {

        try{
            detailCentreImp.deleteDetailCentre(detailCentreId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }


    @GetMapping("/list/{id}/agenceOdd")
    public Formatter<String> listDetailCentreParAgenceOdd(@PathVariable Long id) {
        DetailCentre detailCentre= (DetailCentre) detailCentreImp.listAgenceOdd(id);
        return renderStringData(true, String.valueOf(detailCentre),"list by agenceOdd");
    }

    @GetMapping("/list/{id}/centre")
    public Object listDetailCentreParCentre(@PathVariable Long id) {

        return renderData(true, (DetailCentre) detailCentreImp.listCentre(id),"list");
    }

    @GetMapping("/list/{id}/{id2}")
    public Formatter<String> listDetailCentreParCentreParAgenceOdd(@PathVariable Long id, @PathVariable Long id2) {

        return renderStringData(true,detailCentreImp.listCentreAndAgenceOdd(id, id2),"list");
    }*/
}
