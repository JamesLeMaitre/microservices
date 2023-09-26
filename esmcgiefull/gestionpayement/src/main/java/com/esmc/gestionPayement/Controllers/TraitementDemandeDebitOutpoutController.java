package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.TraitementDemandeDebitOutpoutService;
import com.esmc.gestionPayement.entities.TraitementDemandeDebitOutpout;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traitementDemandeDebitOutput")
public class TraitementDemandeDebitOutpoutController {

    private TraitementDemandeDebitOutpoutService traitementDemandeDebitOutpoutService;


    @GetMapping("/listAll")
    public List<TraitementDemandeDebitOutpout> getCreditServiceInput(){
        return traitementDemandeDebitOutpoutService.getTraitementDemandeDebitOutpout();
    }
    @PostMapping("/callback")
    public TraitementDemandeDebitOutpout saveTraitementDemandeDebitOutpout(@RequestBody TraitementDemandeDebitOutpout traitementDemandeDebitOutpout){
        return traitementDemandeDebitOutpoutService.saveTraitementDemandeDebitOutpout(traitementDemandeDebitOutpout);
    }
    @GetMapping("/get/id")
    public TraitementDemandeDebitOutpout TraitementDemandeDebitOutpout(@PathVariable Long id){
        return traitementDemandeDebitOutpoutService.TraitementDemandeDebitOutpout(id);
    }
    @PutMapping("/update/{id}")
    public TraitementDemandeDebitOutpout updateTraitementDemandeDebitOutpout(@PathVariable("id") Long id , @RequestBody TraitementDemandeDebitOutpout traitementDemandeDebitOutpout ){
        traitementDemandeDebitOutpout.setId(id);
        return  traitementDemandeDebitOutpoutService.saveTraitementDemandeDebitOutpout(traitementDemandeDebitOutpout);
    }
    @DeleteMapping("/delete/{id}")
    public void deletedebitServiceInput(@PathVariable Long id ){
        traitementDemandeDebitOutpoutService.deleteTraitementDemandeDebitOutpout(id);
    }
}
