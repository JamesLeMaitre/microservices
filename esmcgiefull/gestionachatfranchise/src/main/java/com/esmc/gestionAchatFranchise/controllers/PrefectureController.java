package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Prefecture;
import com.esmc.gestionAchatFranchise.services.PrefectureImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/prefecture")
public class PrefectureController extends DataFormatter<Prefecture> {
    @Autowired
    private PrefectureImp prefectureImp;

    @PostMapping("/save")
    public Object addPrefecture(@RequestBody Prefecture prefecture) {
        try{
            Prefecture prefecture1= prefectureImp.addPrefecture(prefecture);
            return  renderData(true,prefecture1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @GetMapping("/getAll")
    public Formatter<List<Prefecture>> getAllPrefecture(){
        return renderDataArray(true,prefectureImp.getPrefecture(),"list");
    }

    @GetMapping("/get/{prefectureId}")
    public Object getPrefecture(@PathVariable("prefectureId")  Long prefectureId) {
        return renderData(true,prefectureImp.getPrefectureById(prefectureId), "get by id");
    }

    @PutMapping("/update/{prefectureId}")

    public Object updatePrefecture(@PathVariable("prefectureId") Long prefectureId, @RequestBody Prefecture prefecture) {

        try{
            Prefecture prefecture1 = prefectureImp.getPrefectureById(prefectureId);
            if(prefecture1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            prefecture.setId(prefectureId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,prefectureImp.updatePrefecture(prefecture1), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @DeleteMapping("/delete/{prefectureId}")
    public Formatter<String> deletePrefecture(@PathVariable Long prefectureId) {

        try{
            prefectureImp.deletePrefecture(prefectureId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }
    @GetMapping("/list/{id}/region")
    public Formatter<List<Prefecture>> listPrefectureParRegion(@PathVariable Long id) {
        return renderDataArray(true,prefectureImp.listRegion(id),"list region by id");
    }

}
