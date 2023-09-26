package com.esmc.gestionPayement.Controllers;

import com.esmc.gestionPayement.Repositories.TransactionRepo;
import com.esmc.gestionPayement.ServicesInterface.TransactionAdminInterface;
import com.esmc.gestionPayement.entities.TransactionAdmins;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.feign.SecurityRestClient;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.inputs.TransactionActionInput;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction/action/")
public class TransactionActionController  extends DataFormatter<TransactionAdmins> {

    @Autowired
    private TransactionAdminInterface transactionAdminInterface;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private SecurityRestClient securityRestClient;

    @Autowired
    private TeRestClient teRestClient;



    @PostMapping("add/queue/te/{idTe}/ksu/{idKsu}/data")
    public Object createTeTransactions(@RequestBody() TransactionActionInput data, @PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu){
//        try{
            TransactionAdmins item = transactionAdminInterface.createTeTransactionAdmins(data,idTe,idKsu);
            return  renderData(true, item,"Create te transaction");
//        }catch(Exception e){
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  renderStringData(false,exceptionAsString ," error ");
//        }
    }

    @PostMapping("add/queue/te/{idTe}/ksu/{idKsu}/type_transanction_id/{transactionType}/data")
    public Object createTeTransactionsV2(@RequestBody() TransactionActionInput data, @PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu, @PathVariable("transactionType") Long transactionType){
        try{
        TransactionAdmins item = transactionAdminInterface.createTeTransactionAdminsV2(data,idTe,idKsu, transactionType);
        return  renderData(true, item,"Create te transaction");
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ," error ");
        }
    }

    @GetMapping("activate/{idTransaction}/transaction/{type}")
    public Object activate(@PathVariable("idTransaction") Long idTransaction, @PathVariable("type") Long type) {
        try {
            return renderData(true, transactionAdminInterface.activate(idTransaction, type), " activate successfull ");

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, exceptionAsString, " error ");
        }
    }

    @GetMapping("getAll")
    public Object getAll(){
        try{
            return  renderDataArray(true, transactionAdminInterface.getAll(),"Create te transaction");
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ," error ");
        }
    }

    @GetMapping("activate/list/encours/te/{idTe}")
   public Object  lisTransactionEncours(@PathVariable Long idTe) {

        try {
            return renderDataArray(true, transactionAdminInterface.listTransactionAdminByTeCreate(idTe), "transaction creer");
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ," error ");
        }
   }

    @GetMapping("{idType}/activate/{idTransaction}")
    public Object  decaissement(@PathVariable Long idType, @PathVariable Long idTransaction) {
            TransactionAdmins item = transactionAdminInterface.activate(idTransaction, idType);
             teRestClient.mutationMPRgOPIBpsdv(item.getId());

            if (item == null) {
                return renderStringData(false, "", "Not Found");
            }
            return renderData(true, item, "Opperation Successiful");
    }

    @GetMapping("activate/list/decaissement/te/{idTe}")
    public Object lisTransactionValider(@PathVariable Long idTe) {
        try {
            return renderDataArray(true, transactionAdminInterface.listTransactionAdminByTe(idTe), "transaction creer");
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ," error ");
        }
    }

    @GetMapping("encaissementcheck/ksu/{idKsu}/te/{idTe}")
    public Object getCurrentTranssaction(Long idKsu, Long idTe) {

        Transactions transactions = transactionAdminInterface.getCurrentTransactions(idKsu, idTe);
        Formatter<Transactions> transactionsFormatter = new Formatter<>();
        transactionsFormatter.setStatus(true);
        transactionsFormatter.setData(transactions);
        transactionsFormatter.setMessage("Oppérations réussi");

        return transactionsFormatter;
    }

    @GetMapping("transactions_decaissement/{id}")
    public TransactionAdmins getTransactionById(@PathVariable Long id) {

        TransactionAdmins item = transactionAdminInterface.getTransactionById(id);

        if (item == null) {
            return null;
        }
        return item;
    }

}
