package com.esmc.mobileMoneyCredit.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esmc.mobileMoneyCredit.input.TmoneyTransactionInput;

@RestController
@RequestMapping("api/tmoney/transaction/")
public class TmoneyController {

    @PostMapping("recieve")
    public void recieveTransaction(@RequestBody TmoneyTransactionInput transactionData) {
        System.out.println(transactionData);

    }
}
