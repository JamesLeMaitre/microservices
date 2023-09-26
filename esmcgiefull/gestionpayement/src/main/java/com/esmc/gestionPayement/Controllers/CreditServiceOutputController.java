package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.CreditServiceOutputService;
import com.esmc.gestionPayement.entities.CreditServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creditServiseOutput")
public class CreditServiceOutputController {

    @Autowired
    private CreditServiceOutputService creditServiceOutputService;

    @GetMapping("/listAll")
    public List<CreditServiceOutput> getCreditServiceInput(){
        return creditServiceOutputService.getCreditServiceOutput();
    }
    @PostMapping("/tpcredit")
    public CreditServiceOutput saveCreditServiceOutput(@RequestBody CreditServiceOutput creditServiceOutput){
        return creditServiceOutputService.saveCreditServiceOutput(creditServiceOutput);
    }
    @GetMapping("/get/id")
    public CreditServiceOutput CreditServiceOutput(@PathVariable Long id){
        return creditServiceOutputService.CreditServiceOutput(id);
    }
    @PutMapping("/update/{id}")
    public CreditServiceOutput updateCreditServiceOutput(@PathVariable("id") Long id , @RequestBody CreditServiceOutput creditServiceOutput ){
        creditServiceOutput.setId(id);
        return creditServiceOutputService.saveCreditServiceOutput(creditServiceOutput);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteCreditServiceOutput(@PathVariable Long id ){
        creditServiceOutputService.deleteCreditServiceOutput(id);
    }
}
