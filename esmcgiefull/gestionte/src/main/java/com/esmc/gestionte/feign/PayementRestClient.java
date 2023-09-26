package com.esmc.gestionte.feign;


import com.esmc.feign.FeignClientCronConfiguration;
import com.esmc.models.Formatter;
import com.esmc.models.Settings;
import com.esmc.models.TransactionAdmins;
import com.esmc.models.Transactions;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYEMENT-SERVICE", configuration = FeignClientCronConfiguration.class)
public interface PayementRestClient {

    @GetMapping("api/out/transaction/by/ksu/{idKsu}/te/{idTe}")
    public Transactions getTransactionsByKsuAndTe(@PathVariable("idKsu") Long idKsu, @PathVariable("idTe") Long idTe);

    @GetMapping("/api/out/transaction/{id}")
    public Transactions getTransactionsById(@PathVariable("id") Long id);

    @GetMapping("api/settings/request/by/id/{id}")
    public Settings getSettingById(@PathVariable("id") Long id);

    @GetMapping("/api/transaction/action/transactions_decaissement/{id}")
    public TransactionAdmins getTransactionById(@PathVariable Long id);

    @GetMapping("api/settings/request/by/code/{code}")
    public Formatter<Settings> getSettingUsedByTe(@PathVariable("code") String code);
}
