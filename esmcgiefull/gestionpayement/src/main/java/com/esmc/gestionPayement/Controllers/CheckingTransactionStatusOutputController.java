package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.CheckingTransactionStatusOutputService;
import com.esmc.gestionPayement.entities.CheckingTransactionStatusOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactionStatusOutput")
public class CheckingTransactionStatusOutputController {

    @Autowired
    private CheckingTransactionStatusOutputService checkingTransactionStatusOutputService;

    @GetMapping("/listAll")
    public List<CheckingTransactionStatusOutput> getCheckingTransactionStatusOutput(){
        return checkingTransactionStatusOutputService.getCheckingTransactionStatusOutput();
    }
    @PostMapping("/tmoney-middleware/transactionid")
    public CheckingTransactionStatusOutput saveCheckingTransactionStatusOutput(@RequestBody CheckingTransactionStatusOutput checkingTransactionStatusOutput){
        return checkingTransactionStatusOutputService.saveCheckingTransactionStatusOutput(checkingTransactionStatusOutput);
    }
    @GetMapping("/get/id")
    public CheckingTransactionStatusOutput CheckingTransactionStatusOutput(@PathVariable Long id){
        return checkingTransactionStatusOutputService.CheckingTransactionStatusOutput(id);
    }
    @PutMapping("/update/{id}")
    public CheckingTransactionStatusOutput updateCheckingTransactionStatusOutput(@PathVariable("id") Long id , @RequestBody CheckingTransactionStatusOutput checkingTransactionStatusOutput){
        checkingTransactionStatusOutput.setId(id);
        return checkingTransactionStatusOutputService.saveCheckingTransactionStatusOutput(checkingTransactionStatusOutput);
    }

    @DeleteMapping("/delete/{id}")
    public void  deleteCheckingTransactionStatusOutput(@PathVariable Long id ){
        checkingTransactionStatusOutputService.deleteCheckingTransactionStatusOutput(id);

    }
}
