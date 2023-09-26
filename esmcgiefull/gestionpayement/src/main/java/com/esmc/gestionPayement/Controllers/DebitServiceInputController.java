package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.DebitServiceInputService;
import com.esmc.gestionPayement.entities.DebitServiceInput;
import com.esmc.gestionPayement.inputs.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/debitServiceInput")
public class DebitServiceInputController  extends DataFormatter<DebitServiceInput> {

    @Autowired
    private DebitServiceInputService debitServiceInputService;


    @GetMapping("ListAll")
    public ResponseEntity<List<DebitServiceInput>> getDebitServiceInput(){
        return new ResponseEntity<List<DebitServiceInput>>( debitServiceInputService.getDebitServiceInput(), HttpStatus.OK);
    }
    @PostMapping("https://ms-push-api-prep.togocom.tg/tmoney-middleware/debit")
    public ResponseEntity<DebitServiceInput> saveDebitServiceInput(@RequestBody DebitServiceInput debitServiceInput){
        return new ResponseEntity<DebitServiceInput>( debitServiceInputService.saveDebitServiceInput(debitServiceInput), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get/id")
    public ResponseEntity<DebitServiceInput> DebitServiceInput(@PathVariable Long id){
        return new ResponseEntity<DebitServiceInput>( debitServiceInputService.DebitServiceInput(id), HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DebitServiceInput> updateDebitServiceInput(@PathVariable("id") Long id , @RequestBody DebitServiceInput debitServiceInput ){
        debitServiceInput.setId(id);
        return  new ResponseEntity<>( debitServiceInputService.saveDebitServiceInput(debitServiceInput), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DebitServiceInput>  deleteDebitServiceInput(@PathVariable Long id ){
        debitServiceInputService.deleteDebitServiceInput(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getHash")
    public Object list_intervenant_agent(@RequestBody Input data){
        try {
            return  renderStringData(true, debitServiceInputService.hashFunc(data),"Hash Code");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
