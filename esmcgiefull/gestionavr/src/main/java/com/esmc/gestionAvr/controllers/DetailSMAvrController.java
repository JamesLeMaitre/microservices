package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.DetailSMAvr;
import com.esmc.gestionAvr.services.DetailSMAvrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "api/detailsmavr/")
@RestController
public class DetailSMAvrController {

    @Autowired
    private DetailSMAvrService detailSMAvrService;

    @PostMapping("save")
    public DetailSMAvr addDetailSmavr(@RequestBody DetailSMAvr d) {
        return detailSMAvrService.addDetailSmavr(d) ;
    }

    @PutMapping("update/{id}")
    public DetailSMAvr updateDetailSmar(@RequestBody DetailSMAvr d, @PathVariable Long id) {
        d.setId(id);
        return detailSMAvrService.addDetailSmavr(d);
    }

    @DeleteMapping("delete/{id}")
    public void deleteDetailSMAvr(@PathVariable Long id) {
        detailSMAvrService.deleteDetailSMAvr(id);
    }

    @GetMapping("list")
    public List<DetailSMAvr> ListDetailSMAvr() {
        return detailSMAvrService.ListDetailSMAvr();
    }

    @GetMapping("list/{id}/smavr")
    public List<DetailSMAvr> ListDetailSMAvrBySMAvr(Long id) {
        return detailSMAvrService.ListDetailSMAvrBySMAvr(id);
    }

    @GetMapping("list/{id}/avr")
    public List<DetailSMAvr> ListDetailSMAvrByAvr(Long id) {
        return detailSMAvrService.ListDetailSMAvrByAvr(id);
    }
}
