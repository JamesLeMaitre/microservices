package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.CreditServiceInputService;
import com.esmc.gestionPayement.entities.CreditServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditServiceInput")
public class CreditServiceInputController {

    @Autowired
    private CreditServiceInputService creditServiceInputService;

    @GetMapping("/listAll")
    public List<CreditServiceInput> getCreditServiceInput(){
        return  creditServiceInputService.getCreditServiceInput();
    }
    @PostMapping("/tpcredit")
    public CreditServiceInput saveCreditServiceInput(@RequestBody CreditServiceInput creditServiceInput){
        return creditServiceInputService.saveCreditServiceInput(creditServiceInput);
    }
    @GetMapping("/get/id")
    public CreditServiceInput creditServiceInput(@PathVariable Long id){
        return  creditServiceInputService.CreditServiceInput(id);
    }
    @PutMapping("/update/{id}")
    public CreditServiceInput updateCreditServiceInput(@PathVariable("id") Long id , @RequestBody CreditServiceInput creditServiceInput ){
        creditServiceInput.setId(id);
        return creditServiceInputService.saveCreditServiceInput(creditServiceInput);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCreditServiceInput(@PathVariable Long id ){
        creditServiceInputService.deleteCreditServiceInput(id);
    }
}
