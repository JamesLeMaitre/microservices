package com.esmc.gestionPayement.Controllers;

import com.esmc.gestionPayement.ServicesInterface.TransactionInRequestServiceInterface;
import com.esmc.gestionPayement.ServicesInterface.TransactionInServiceInterface;
import com.esmc.gestionPayement.entities.TransactionInRequest;
import com.esmc.gestionPayement.entities.TransactionIns;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.inputs.TmoneyInput;
import com.esmc.gestionPayement.inputs.TransactionInInput;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/in/transaction/history")
public class TransactionInController  extends DataFormatter<TransactionIns> {

    @Autowired
    private TransactionInServiceInterface transactionInServiceInterface;

    @GetMapping("by/te/{idTe}")
    public Object getTransactionsUsedByTe(@PathVariable("idTe") Long idTe){
        List<TransactionIns> tran = transactionInServiceInterface.getTransactionInByIdTe(idTe);
        return  renderDataArray(true,tran,"list of transaction done succesffully by idte ");
    }

    @PostMapping("add/queue/te/{idTe}/ksu/{idKsu}/data/")
    public Object createTeTransactions(@RequestBody() TransactionInInput data, @PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu){
        try{
            return  renderData(true, transactionInServiceInterface.createTeTransactionIn(data,idTe,idKsu),"Create te transaction");
        }catch(Exception e){

        }
        return  renderStringData(false,"Error while procssing" ,"Reference already used");

    }

    @PostMapping("decaissement/te/{idTe}/ksu/{idKsu}/data/")
    public Object decaissement(@RequestBody() TmoneyInput data, @PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu){

        try{
            Object transactionIns = transactionInServiceInterface.decaissement(data,idTe,idKsu);
            if(transactionIns == null){
                return  renderStringData(false,"Error while procssing" ,"transaction refused");
            }
            return  renderStringData(true,"Transaction donne successfuly" ,"Done");
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ,"Reference error ");
        }

    }
}
