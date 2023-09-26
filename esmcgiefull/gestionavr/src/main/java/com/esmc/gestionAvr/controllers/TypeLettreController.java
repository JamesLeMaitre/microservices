package com.esmc.gestionAvr.controllers;


import com.esmc.gestionAvr.ResponseMessage.ResponseMessage;
import com.esmc.gestionAvr.entities.TypeLettre;
import com.esmc.gestionAvr.servicesInterfaces.TypeLettreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/typeLettre/")
public class TypeLettreController {
    @Autowired
    private TypeLettreInterface typeLettreInterface;

    /**
     * Map to save an TypeLettre
     *
     * @param typeLettre
     * @return
     */
    @PostMapping(value = "save")
    public ResponseEntity<TypeLettre> addTypeLettre(@RequestBody TypeLettre typeLettre) {
        return new ResponseEntity<TypeLettre>( typeLettreInterface.addTypeLettre(typeLettre), HttpStatus.CREATED);
    }

    /**
     * Map to update an TypeLettre
     * @param id
     * @return
     */
    @GetMapping(value = "get/{id}")
    public ResponseEntity<TypeLettre> getById(@PathVariable Long id){
        return new ResponseEntity<TypeLettre>( typeLettreInterface.getTypeLetterById(id),HttpStatus.OK);
    }

    /**
     * Map to delete an TypeLettre
     * @param id
     */
    @DeleteMapping(value = "delete/{id}")
    public void deleteTypeLettre(@PathVariable Long id){
        typeLettreInterface.deleteTypeLettre(id);
    }

    /**
     * Map to see the list of Typelettre
     * @return
     */
    @GetMapping(value = "get/All")
    public ResponseMessage listTypelettre(){
        return new ResponseMessage(true,typeLettreInterface.listTypeLettre());
    }

    @PutMapping(value ="update/{id}")
    public ResponseEntity<TypeLettre> updateTypelettre(@RequestBody long id , @RequestBody TypeLettre typeLettre ){
        typeLettre.setId(id);
        return new ResponseEntity<TypeLettre>( typeLettreInterface.updateTypeLettre(id, typeLettre), HttpStatus.OK);
    }

}
