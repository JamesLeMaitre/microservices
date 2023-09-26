package com.esmc.gestionPayement.ServicesInterface;

import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.inputs.*;
import org.springframework.http.ResponseEntity;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface TransactionServiceInterface {

    Transactions createTransaction(TransactionInput data);

    public Transactions createTeTransaction(TransactionInput data, Long idTe, Long idKsu);
    List<Transactions> getAll();

    Transactions getTransactionsByReference(String reference);

    Transactions getTransactionsById(Long id);

    List<Transactions> getTransactionByIdTe(Long idTe, boolean status);

    Transactions getTransactionByIdKsuAndIdTe(Long idKsu, Long idTe);

    Transactions createTansApro(Double montant, Long idKsu, Long idTe);

    Double sommeMev(Long idKsu, Long idTe);

    Object sendOTPCode(DemandeOtpInput data) throws Exception;
    Object makePaymentForGoods(PayementBienInput data) throws Exception;

    String makeInternetPayement(PayementInternetInput data) throws Exception;

//   String recoverClientInformation(ClientInfosInput data) throws Exception;
    Object recoverClientInformation(String countryCode, String phone) throws Exception;

    Object checkTransactionStatus(String codeOperation) throws Exception;

    public Transactions createTransactionCoris(Transactions data);
//
//    void sendCodeOTP(DemandeOtpInput data) throws Exception;
//
//    Object payedGoods(PayementBienInput data) throws Exception;
//
//    Object internetPayement(PayementInternetInput data) throws Exception;
//
//    Object getClientInfos(ClientInfosInput data) throws Exception;

    TmoneyDebitResponse tmoneyDebit(String phone, Double amount);

    TmoneyDebitResponse getTmoneyApiCheckTransaction(String phone, Double amount);

//    Object getcli(String countryCode, String phone) throws Exception;
}
