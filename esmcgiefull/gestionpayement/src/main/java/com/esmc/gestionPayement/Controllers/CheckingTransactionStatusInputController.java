package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.CheckingTransactionStatusInputService;
import com.esmc.gestionPayement.entities.CheckingTransactionStatusInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactionInput")
public class CheckingTransactionStatusInputController {

    @Autowired
    private CheckingTransactionStatusInputService checkingTransactionStatusInputService;

    @PostMapping("/tmoney-middleware/transactionid")
    public CheckingTransactionStatusInput saveCheckingTransactionStatusInput(@RequestBody CheckingTransactionStatusInput checkingTransactionStatusInput){
        return checkingTransactionStatusInputService.saveCheckingTransactionStatusInput(checkingTransactionStatusInput);
    }
    @GetMapping("/ListAll")
    public List<CheckingTransactionStatusInput> getCheckingTransactionStatusInput(){
        return checkingTransactionStatusInputService.getCheckingTransactionStatusInput();
    }
    @GetMapping("/get/id")
    public CheckingTransactionStatusInput checkingTransactionStatusInput(@PathVariable Long id){
        return checkingTransactionStatusInputService.CheckingTransactionStatusInput(id);
    }
    @PutMapping("/update/{id}")
    public CheckingTransactionStatusInput updateCheckingTransactionStatusInput(@PathVariable("id") Long id , @RequestBody CheckingTransactionStatusInput checkingTransactionStatusInput ){
        checkingTransactionStatusInput.setId(id);
        return  checkingTransactionStatusInputService.saveCheckingTransactionStatusInput(checkingTransactionStatusInput);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCheckingTransactionStatusInput(@PathVariable Long id ){
        checkingTransactionStatusInputService.deleteCheckingTransactionStatusInput(id);
    }

}
