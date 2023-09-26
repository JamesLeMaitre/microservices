package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.TypeFranchise;
import com.esmc.gestionAchatFranchise.services.TypeFranchiseImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/typeFranchise")
public class TypeFranchiseController extends DataFormatter<TypeFranchise> {
    @Autowired
    private TypeFranchiseImp typeFranchiseImp;

    @PostMapping("/save")
    public Object addTypeFranchise(@RequestBody TypeFranchise typeFranchise) {


        try{
            TypeFranchise typeFranchise1= typeFranchiseImp.addTypeFranchise(typeFranchise);
            return  renderData(true,typeFranchise1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping("/getAll")
    public Formatter<List<TypeFranchise>> getAllTypeFranchise(){
        return renderDataArray(true,typeFranchiseImp.getTypeFranchise(), "typefrancise");
    }

    @GetMapping("/get/{typeFranchiseId}")
    public Object getTypeFranchise(@PathVariable("typeFranchiseId") Long typeFranchiseId) {
        return renderData(true,typeFranchiseImp.getTypeFranchiseById(typeFranchiseId), "type of franchise");
    }

    @PutMapping("/update/{typeFranchiseId}")
    public Object updateTypeFranchise(@PathVariable("typeFranchiseId") Long typeFranchiseId, @RequestBody TypeFranchise typeFranchise) {


        try{
            TypeFranchise typeFranchise1 = typeFranchiseImp.getTypeFranchiseById(typeFranchiseId);
            if(typeFranchise1 == null){
                return renderStringData(false,"Error ","AgentOdd not found");
            }
            typeFranchise.setId(typeFranchiseId);
            //agenceOddImp.updateAgenceOdd(agenceOddId, agenceOdd);
            return renderData(true,typeFranchiseImp.updateTypeFranchise(typeFranchise1), "Updated successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @DeleteMapping("/delete/{typeFranchiseId}")
    public Formatter<String> deleteTypeFranchise(@PathVariable Long typeFranchiseId) {



        try{
            typeFranchiseImp.deleteTypeFranchise(typeFranchiseId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }
    }

    @GetMapping({"/search/checkedDate/{typeFranchiseId}"})
    public Formatter<String> checkingDateDeadlines(@PathVariable Long typeFranchiseId  ) throws ExceptionsHandler {
        return  renderStringData(true ,typeFranchiseImp.achatFranchiseGestionDelai(typeFranchiseId),"ckek");
    }


}
