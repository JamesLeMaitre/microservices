package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.AffectationSMAvr;
import com.esmc.gestionAvr.services.AffectationSMAvrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/AffecationSMAvr/")
public class AffectationSMAvrController {

    @Autowired
    private AffectationSMAvrService affectationSMAvrService;

    @PostMapping(value = "save")
    public AffectationSMAvr addFifo(@RequestBody AffectationSMAvr a) {
        return  affectationSMAvrService.addAffectation(a);
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteFifo(@PathVariable Long id){
        affectationSMAvrService.deleteAffectationSMAvr(id);
    }

    @PutMapping(value = "edit/{id}")
    public AffectationSMAvr modifier(@PathVariable Long id, @RequestBody AffectationSMAvr a) {
        a.setId(id);
        return affectationSMAvrService.updateAffectationSMAvr(a);
    }

    @GetMapping(value = "list")
    public List<AffectationSMAvr> listAffectationSMAvr() {
        return affectationSMAvrService.listAffectationSMAvr();
    }

    @GetMapping(value = "list/{id}/echange")
    public List<AffectationSMAvr> AffSMAvrByEchange(@PathVariable Long id) {
        return affectationSMAvrService.AffSMAvrByEchange(id);
    }


    @GetMapping(value = "list/{id}/typeavr")
    public List<AffectationSMAvr> AffSMAvrByTypeAvr(@PathVariable Long id) {
        return affectationSMAvrService.AffSMAvrByTypeAvr(id);
    }

    @GetMapping(value = "list/{id}/typesmavr")
    public List<AffectationSMAvr> AffSMAvrByTypeSAvr(@PathVariable Long id) {
        return affectationSMAvrService.AffSMAvrByTypeSmavr(id);
    }

    @GetMapping(value = "list/{id1}/typeavr/{id2}/echange")
    public List<AffectationSMAvr> AffSMAvrByTypeAvrEchangeSMAVR(@PathVariable Long id1, @PathVariable Long id2) {
        return affectationSMAvrService.AffSMAvrByTypeAvrEchangeSMAVR(id1, id2);
    }

//    @GetMapping(value = "list/{echange}/echange/{typeAvr}/typeAvr")
//    public List<AffectationSMAvr> getSmavr(@PathVariable String echange,@PathVariable String typeAvr) {
//        return affectationSMAvrService.getSmavr(echange, typeAvr);
//    }

}
