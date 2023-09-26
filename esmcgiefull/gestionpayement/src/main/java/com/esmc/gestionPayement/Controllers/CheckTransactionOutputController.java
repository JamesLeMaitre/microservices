package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.CheckTransactionOutputService;
import com.esmc.gestionPayement.entities.CheckTransactionOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkTransactionOuput")
public class CheckTransactionOutputController {

    private CheckTransactionOutputService checkTransactionOutputService;

    @GetMapping("/listAll")
    public List<CheckTransactionOutput> getCheckTransactionOutput(){
        return checkTransactionOutputService.getCheckTransactionOutput();
    }
    @PostMapping("/bill-pay/v1.0/check-transaction")
    public ResponseEntity<CheckTransactionOutput> saveCheckingTransactionStatusInput(@RequestBody CheckTransactionOutput checkTransactionOutput){
        return new ResponseEntity<CheckTransactionOutput>( checkTransactionOutputService.saveCheckTransactionOutput(checkTransactionOutput), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/id")
    public CheckTransactionOutput CheckTransactionOutput(@PathVariable Long id){
        return checkTransactionOutputService.CheckTransactionOutput(id);
    }
    @PutMapping("/update/{id}")
    public CheckTransactionOutput updateCheckTransactionOutput(@PathVariable("id") Long id , @RequestBody CheckTransactionOutput checkTransactionOutput ){
        checkTransactionOutput.setId(id);
        return  checkTransactionOutputService.saveCheckTransactionOutput(checkTransactionOutput);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCheckTransactionOutput(@PathVariable Long id ){
        checkTransactionOutputService.deleteCheckTransactionOutput(id);
    }
}
