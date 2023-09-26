package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.DebitServiceOutputService;
import com.esmc.gestionPayement.entities.DebitServiceOutput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/debitServiceOutput")
public class DebitServiceOutputController {

    private DebitServiceOutputService debitServiceOutputService;

    @GetMapping("/listAll")
    public List<DebitServiceOutput> getCreditServiceInput(){
        return debitServiceOutputService.getDebitServiceOutput();
    }
    @PostMapping("/tmoney-middleware/debit")
    public DebitServiceOutput saveDebitServiceOutput(@RequestBody DebitServiceOutput debitServiceOutput){
        return debitServiceOutputService.saveDebitServiceOutput(debitServiceOutput);
    }
    @GetMapping("/get/id")
    public DebitServiceOutput debitDebitServiceOutput(@PathVariable Long id){
        return debitServiceOutputService.DebitServiceOutput(id);
    }
    @PutMapping("/update/{id}")
    public DebitServiceOutput updateDebitServiceOutput(@PathVariable("id") Long id , @RequestBody DebitServiceOutput debitServiceOutput ){
        debitServiceOutput.setId(id);
        return  debitServiceOutputService.saveDebitServiceOutput(debitServiceOutput);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteDebitServiceInput(@PathVariable Long id ){
        debitServiceOutputService.deleteDebitServiceOutput(id);
    }
}
