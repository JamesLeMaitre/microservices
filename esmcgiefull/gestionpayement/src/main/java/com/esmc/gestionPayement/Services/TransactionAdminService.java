package com.esmc.gestionPayement.Services;

import com.esmc.gestionPayement.Repositories.TransactionAdminRepo;
import com.esmc.gestionPayement.Repositories.TransactionRepo;
import com.esmc.gestionPayement.ServicesInterface.TransactionAdminInterface;
import com.esmc.gestionPayement.ServicesInterface.TransactionInRequestServiceInterface;
import com.esmc.gestionPayement.ServicesInterface.TransactionInServiceInterface;
import com.esmc.gestionPayement.entities.TransactionAdmins;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.feign.KsuRestClient;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.inputs.TmoneyInput;
import com.esmc.gestionPayement.inputs.TransactionActionInput;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionAdminService implements TransactionAdminInterface {
    @Autowired
    TeRestClient teRestClient;

    @Autowired
    private TransactionAdminRepo transactionAdminRepo;

    @Autowired
    private TransactionInServiceInterface transactionInServiceInterface;

    @Autowired
    private TransactionInRequestServiceInterface transactionInRequestServiceInterface;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public TransactionAdmins createTeTransactionAdmins(TransactionActionInput data, Long idTe, Long idKsu) {
        TransactionAdmins transactionAdmins = new TransactionAdmins();
        transactionAdmins.setMontant(data.getMontant());
        transactionAdmins.setNumero(data.getNumero());
        transactionAdmins.setIdTe(idTe);
        transactionAdmins.setPrefix(data.getPrefix());
        transactionAdmins.setIdKsu(idKsu);
        transactionAdmins.setBon(data.getBon());
        transactionAdmins.setIdDetailAgr(data.getIdDetailAgr());
        transactionAdmins.setTypeFonds(data.getTypeFonds());
        return transactionAdminRepo.save(transactionAdmins);
    }

    @Override
    public TransactionAdmins createTeTransactionAdminsV2(TransactionActionInput data, Long idTe, Long idKsu, Long transactionType) {

        TransactionAdmins transactionAdmins = new TransactionAdmins();
        transactionAdmins.setMontant(data.getMontant());
        transactionAdmins.setNumero(data.getNumero());
        transactionAdmins.setIdTe(idTe);
        transactionAdmins.setPrefix(data.getPrefix());
        transactionAdmins.setIdKsu(idKsu);
        transactionAdmins.setBon(data.getBon());
        transactionAdmins.setIdDetailAgr(data.getIdDetailAgr());
        transactionAdmins.setTypeFonds(data.getTypeFonds());
        TransactionAdmins transactions = transactionAdminRepo.save(transactionAdmins);

        TransactionAdmins transaction =  transactionAdminRepo.findById(transactions.getId()).orElse(null);

        TransactionAdmins trans =  this.activate(transaction.getId(), transactionType);

        return trans;
    }

    @Override
    public List<TransactionAdmins> getAll() {
        return transactionAdminRepo.findAllByStatus();
    }


    @Override
    public TransactionAdmins activate(Long id, Long transactionType) {
        TransactionAdmins transactionAdmins =  transactionAdminRepo.findById(id).orElse(null);

        if(transactionType == 1){
            //Tmoney

            TmoneyInput tmoneyInput = new TmoneyInput();
            tmoneyInput.setAmount(transactionAdmins.getMontant());
            tmoneyInput.setPhone(transactionAdmins.getNumero());
            transactionInServiceInterface.decaissement(tmoneyInput,transactionAdmins.getIdTe(),transactionAdmins.getIdKsu());
            transactionAdmins.setStatus(2);
            transactionAdmins.setSource("TMoney");
            transactionAdminRepo.save(transactionAdmins);
        } else if(transactionType == 2){
            //cinetpay
            Ksu k = ksuRestClient.getKsuById(transactionAdmins.getIdKsu());
            System.out.println("Montant : "+transactionAdmins.getMontant()+" Numero : "+transactionAdmins.getNumero()+" Prefix : "+ transactionAdmins.getPrefix()+" Nom : "+k.getNom()+" Prenom : "+k.getPrenom()+" Email : "+k.getEmail() );
            transactionInRequestServiceInterface.createDecaissement(transactionAdmins.getMontant(), transactionAdmins.getNumero(), transactionAdmins.getPrefix(), k.getNom(), k.getPrenom(), k.getEmail(), transactionAdmins);

        }

        TransactionAdmins trans = transactionAdminRepo.findById(id).orElse(null);

        return trans;
    }

    @Override
    public List<TransactionAdmins> listTransactionAdminByTeCreate(Long idTe) {
        return transactionAdminRepo.findAllByStatusFalse(idTe);
    }

    @Override
    public List<TransactionAdmins> listTransactionAdminByTe(Long idTe) {
        return transactionAdminRepo.findAllTrasactionByStatus(idTe);
    }

    @Override
    public Transactions getCurrentTransactions(Long idKsu, Long idTe) {
        return transactionRepo.getTransactionByIdKsuAndIdTe(idKsu, idTe);
    }

    @Override
    public TransactionAdmins getTransactionById(Long id) {
        return transactionAdminRepo.getTransactiontByIdTransaction(id);
    }
}
