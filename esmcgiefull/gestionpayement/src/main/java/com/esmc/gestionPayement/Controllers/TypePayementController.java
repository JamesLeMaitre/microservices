package com.esmc.gestionPayement.Controllers;

import com.esmc.gestionPayement.ServicesInterface.TypePayementServiceInterface;
import com.esmc.gestionPayement.entities.TypePayement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/typePayement")
public class TypePayementController {
    @Autowired
    private TypePayementServiceInterface typePayementServiceInterface;

    @GetMapping("/list")
    public List<TypePayement> getTypePayement(){
        return typePayementServiceInterface.getTypePayement();
    }

    @PostMapping("/save")
    public TypePayement saveTypePayement(@RequestBody TypePayement typePayement){
        return typePayementServiceInterface.saveTypePayement(typePayement);
    }
    @GetMapping("/get/{id}")
    public TypePayement TypePayement(@PathVariable Long id){
        return typePayementServiceInterface.TypePayement(id);
    }

    @PutMapping("/update/{id}")
    public TypePayement updateTypePayement(@PathVariable("id") Long id , @RequestBody TypePayement typePayement){

        return typePayementServiceInterface.updateTypePayement(id, typePayement);
    }

    @DeleteMapping("/delete/{id}")
    public void  deleteTypePayement(@PathVariable Long id ){
        typePayementServiceInterface.deleteTypePayement(id);

    }
    
}
