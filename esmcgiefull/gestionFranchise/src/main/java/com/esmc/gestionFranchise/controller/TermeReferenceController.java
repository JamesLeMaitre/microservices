package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.TermeReference;
import com.esmc.gestionFranchise.servicesInterface.TermeReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/TermeReference")
public class TermeReferenceController {

    @Autowired
    private TermeReferenceService termeReferenceService;
    @GetMapping(value = "/ListAll")
    public ResponseEntity<List<TermeReference>>  getTermeReference(){
        return new ResponseEntity<List<TermeReference>>(termeReferenceService.getTermeReference(), HttpStatus.OK);
    }
    @PostMapping(value = "/save")
    public ResponseEntity<TermeReference> ajouterTermeReference(@RequestBody TermeReference termeReference){
        return new ResponseEntity<TermeReference>(termeReferenceService.ajouterTermeReference(termeReference),HttpStatus.CREATED);
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<TermeReference> getTermeReferencebyId(@PathVariable Long id){
        return new ResponseEntity<>(termeReferenceService.getTermeReferencebyId(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<TermeReference> deleteTermeReference(@PathVariable Long id){
        termeReferenceService.deleteTermeReference(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getTermeReference/{id}")
    public List<TermeReference> listTermeReference(@PathVariable Long id) {
        return termeReferenceService.listTermeReference(id);
    }


}
