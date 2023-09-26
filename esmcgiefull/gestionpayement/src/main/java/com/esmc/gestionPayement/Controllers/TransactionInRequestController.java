package com.esmc.gestionPayement.Controllers;

import com.esmc.gestionPayement.ServicesInterface.TransactionInRequestServiceInterface;
import com.esmc.gestionPayement.entities.TransactionInRequest;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.inputs.CinetPayWebhooksInput;
import com.esmc.gestionPayement.inputs.TransactionInInput;
import com.esmc.gestionPayement.inputs.TransactionInput;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/in/transaction/request")
public class TransactionInRequestController extends DataFormatter<TransactionInRequest> {

    @Autowired
    private TransactionInRequestServiceInterface transactionInRequestServiceInterface;

    @PostMapping("add/queue/te/{idTe}/ksu/{idKsu}/data/")
    public Object createTeTransactions(@RequestBody() TransactionInInput data, @PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu){
        if(transactionInRequestServiceInterface.getTransactionInRequestByReference(data.getLot())==null){
            return  renderData(true, transactionInRequestServiceInterface.createTeTransactionInRequest(data,idTe,idKsu),"Create te transaction");
        }
        return  renderStringData(false,"Error while procssing" ,"Reference already used");
    }

    @GetMapping("by/te/{idTe}")
    public Object getTransactionsUsedByTe(@PathVariable("idTe") Long idTe){
        List<TransactionInRequest> tranreq = transactionInRequestServiceInterface.getTransactionInRequestByIdTe(idTe);
        return  renderDataArray(true,tranreq,"list of transaction by idte  used");
    }

    @GetMapping(path="cinetpay/webhook/notification",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cinetPayWebhooksCheckTransaction(@RequestParam Map<String, String> body ){
        System.out.println("--------------------------- webhooks ping transaction-------------------------------");
        System.out.println(body);
        System.out.println("--------------------------- end webhooks ping transaction-------------------------------");
        return "OK";
    }

    @PostMapping(path="cinetpay/webhook/notification", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object cinetPayWebhooksVerifyTransaction(@RequestParam Map<String, String> data){
        try {
            System.out.println("---------------------------webhooks verify transaction-------------------------------");
            System.out.println(data);
            String transactionId = data.get("lot");
            System.out.println(transactionId);
            transactionInRequestServiceInterface.cinetPayWebhooksVerifyTransaction(transactionId);
            System.out.println("------------------------------------------------------------------------------------");
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ,"Reference error ");
        }

        return true;
    }


    @GetMapping(path="cinetpay/webhook/depot/notification",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cinetPayDepotWebhooksCheckTransaction(@RequestParam Map<String, String> body ){
        System.out.println("---------------------------webhooks ping depot transaction-------------------------------");
        System.out.println(body);
        System.out.println("---------------------------end webhooks ping depot transaction-------------------------------");
        return "OK";
    }

    @PostMapping(path="cinetpay/webhook/depot/notification", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object cinetPayDepotWebhooksVerifyTransaction(@RequestParam Map<String, String> data){
        try {
            System.out.println("---------------------------webhooks verify depot transaction-------------------------------");
            System.out.println(data);
            String transactionId = data.get("cpm_trans_id");
            System.out.println(transactionId);
            transactionInRequestServiceInterface.cinetPayWebhooksVerifyTransactionDepot(transactionId);
            System.out.println("------------------------------------------------------------------------------------");
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,exceptionAsString ,"Reference error ");
        }

        return true;
    }

    @GetMapping("checkTransaction")
    public Object checkTransaction(){
        return transactionInRequestServiceInterface.checkTransaction();
        //return  renderStringData(true,"","check transaction");
    }

}
