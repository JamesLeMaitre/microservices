package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.TraitementDemandeDebitInputService;
import com.esmc.gestionPayement.entities.TraitementDemandeDebitInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traitementDemandeDebitInput")
public class TraitementDemandeDebitInputController {

    private TraitementDemandeDebitInputService traitementDemandeDebitInputService;

    @GetMapping("ListAll")
    public List<TraitementDemandeDebitInput> getTraitementDemandeDebitInput(){
        return traitementDemandeDebitInputService.getTraitementDemandeDebitInput();
    }
    @PostMapping("/callback")
    public TraitementDemandeDebitInput saveTraitementDemandeDebitInput(@RequestBody TraitementDemandeDebitInput traitementDemandeDebitInput){
        return traitementDemandeDebitInputService.saveTraitementDemandeDebitInput(traitementDemandeDebitInput);
    }
    @GetMapping("/get/id")
    public TraitementDemandeDebitInput TraitementDemandeDebitInput(@PathVariable Long id){
        return traitementDemandeDebitInputService.TraitementDemandeDebitInput(id);
    }
    @PutMapping("/update/{id}")
    public TraitementDemandeDebitInput updateTraitementDemandeDebitInput(@PathVariable("id") Long id , @RequestBody TraitementDemandeDebitInput traitementDemandeDebitInput ){
        traitementDemandeDebitInput.setId(id);
        return traitementDemandeDebitInputService.saveTraitementDemandeDebitInput(traitementDemandeDebitInput);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteTraitementDemandeDebitInput(@PathVariable Long id ){
        traitementDemandeDebitInputService.deleteTraitementDemandeDebitInput(id);
    }
}
