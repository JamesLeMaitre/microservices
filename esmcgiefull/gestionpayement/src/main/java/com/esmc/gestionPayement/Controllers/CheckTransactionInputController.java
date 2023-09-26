package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.CheckTransactionInputService;
import com.esmc.gestionPayement.entities.CheckTransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkTransactionInput")
public class CheckTransactionInputController {

    @Autowired
    private CheckTransactionInputService checkTransactionInputService;

    @GetMapping("/listAll")
    public List<CheckTransactionInput> getCheckTransactionInput(){
        return checkTransactionInputService.getCheckTransactionInput();
    }
    @PostMapping("/bill-pay/v1.0/check-transaction")
    public CheckTransactionInput saveCheckTransactionInput(@RequestBody CheckTransactionInput checkTransactionInput){
        return checkTransactionInputService.saveCheckTransactionInput(checkTransactionInput);
    }
    @GetMapping("/get/{id}")
    public CheckTransactionInput checkTransactionInput(@PathVariable Long id){
        return checkTransactionInputService.CheckTransactionInput(id);
    }
      @PutMapping("/update/{id}")
    public CheckTransactionInput updateCheckTransactionInput(@PathVariable("id") Long id ,@RequestBody CheckTransactionInput checkTransactionInput ){
        checkTransactionInput.setId(id);
        return checkTransactionInputService.saveCheckTransactionInput(checkTransactionInput);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCheckTransactionInput(@PathVariable Long id ){
        checkTransactionInputService.deleteCheckTransactionInput(id);
    }
}
