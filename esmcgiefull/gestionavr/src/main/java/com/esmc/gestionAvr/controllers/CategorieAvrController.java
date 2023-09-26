package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.ResponseMessage.ResponseMessage;
import com.esmc.gestionAvr.entities.CategorieAvr;
import com.esmc.gestionAvr.servicesInterfaces.CategorieAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping(value = "api/CategorieAvr/")
public class CategorieAvrController  extends DataFormatter<CategorieAvr>{

    @Autowired
    private CategorieAvrInterface categorieAvrInterface;


    /**
     * Map to save an AvrType
     * @param categorieAvr
     * @return
     */
    @PostMapping(value = "save")
    public ResponseEntity<CategorieAvr> addCategorieAvr(@RequestBody CategorieAvr categorieAvr) {
        return new ResponseEntity<CategorieAvr>( categorieAvrInterface.addCategorieAvr(categorieAvr),HttpStatus.CREATED);
    }

    /**
     * Map to update an CategorieAvr
     *
     * @param id
     * @return
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<CategorieAvr> getById(@PathVariable Long id){
        return new ResponseEntity<CategorieAvr>( categorieAvrInterface.getCategorieAvrById(id),HttpStatus.OK);
    }



    /**
     * Map to delete an CategorieAvr
     * @param id
     */
    @DeleteMapping(value = "delete/{id}")
    public void deleteTypeAvr(@PathVariable Long id){
        categorieAvrInterface.deleteCategorieAvr(id);
    }


    /**
     * Map to see the list of CategorieAvr
     * @return
     */
    @GetMapping(value = "list")
    public ResponseMessage listCategorieAvr(){
        return new ResponseMessage(true,categorieAvrInterface.listCategorieAvr());
    }


    @PutMapping(value ="update/{id}")
    public ResponseEntity<CategorieAvr> updateCategorieAvr(@PathVariable Long id , @RequestBody CategorieAvr categorieAvr ){
        categorieAvr.setId(id);
        return new ResponseEntity<CategorieAvr>( categorieAvrInterface.updateCategorieAvr(id, categorieAvr), HttpStatus.OK);
    }


    @GetMapping("listAll")
    public Object getDataByDetailTypeSupportV2() {
        try {
            List<CategorieAvr> oklm = categorieAvrInterface.listCategorieAvr();
            if(oklm.isEmpty()){
                return  renderStringData(false, "","Data not found");
            }
            return  renderDataArray(true, oklm,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

}
