package com.esmc.gestionAgr.fifo.services.impls;


import com.esmc.gestionAgr.fifo.entities.*;
import com.esmc.gestionAgr.fifo.models.DataSupportInput;
import com.esmc.gestionAgr.fifo.models.PaiementInfo;
import com.esmc.gestionAgr.fifo.repositories.*;
import com.esmc.gestionAgr.fifo.services.TypeSmAvrService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BonSmAvrImpl {
    private final TypeSmAvrService typeSmAvrService;
    private final KsuRepository ksuRepository;
    private final SMAvrRepository smAvrRepository;
    private final ExtrantRepository extrantRepository;
    private final DetailSMAvrRepository detailSMAvrRepository;
    private final DataSupportRepository dataSupportRepository;
    private final TypeSmAvrRepository typeSmAvrRepository;

    private final DetailTypeSupportRepository detailTypeSupportRepository;
    private final MiddleWareExtrantRepository middleWareExtrantRepository;

    public Extrant creationBon(String bon, Fifo recepteur, int quantite, Fifo envoyeur, Double MontantEnvoye, Double MontantRecu, Avr avr, Long refer) throws JsonProcessingException {

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.getById(bon);
        Extrant ex = new Extrant();

        Ksu ksuEnvoyeur = ksuRepository.findKsuById(envoyeur.getKsu().getId());
        Ksu ksuRecepteur = ksuRepository.findKsuById(recepteur.getKsu().getId());

        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.getById(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);

        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(MontantEnvoye);
        detailSMAvr.setMontantFirst(MontantRecu);
        detailSMAvr.setMontantSecond(MontantRecu);
        detailSMAvr.setQuantite(quantite);

        ex.setAvr(avr);

        ex.setMontant(MontantRecu);
        ex.setKsuEmetteur(ksuEnvoyeur.getId());

        ex.setKsuRecepteur(ksuRecepteur.getId());

        ex.setRefer(refer);
        ex.setArchive(false);
        ex.setDataInfo(ksuEnvoyeur.getCodeBanKsu());

        ex.setTypeSmAvr(t);

        extrantRepository.save(ex);

        PaiementInfo paiementInfo = new PaiementInfo();
        paiementInfo.setTransactionId(String.valueOf(System.currentTimeMillis()));
        paiementInfo.setMontant(MontantEnvoye);
        DataSupportInput dataSupportInput = new DataSupportInput();
        dataSupportInput.setDatePaiement(paiementInfo);
        dataSupportInput.setInfoKsuEmetteur(ksuEnvoyeur);
        dataSupportInput.setInfoKsuRecepteur(ksuRecepteur);
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(dataSupportInput);

        MiddleWareExtrant mxExtrant = new MiddleWareExtrant();
        mxExtrant.setIdExtrant(ex.getId());

        TypeSmAvr typeSmAvr = typeSmAvrService.getById(bon);

        System.out.println("==================================TYPE SM AVR=========================");
        System.out.println(typeSmAvr);
        System.out.println("==================================TYPE SM AVR=========================");
        DetailTypeSupport detailTypeSupport = new DetailTypeSupport();
        assert typeSmAvr != null;
        detailTypeSupport.setIdTSupport(typeSmAvr.getId());
        detailTypeSupport.setLibelleTypeSupport(typeSmAvr.getLibelle());
        detailTypeSupport.setCode(typeSmAvr.getCode());

        DetailTypeSupport detailTypeSupport1 = detailTypeSupportRepository.save(detailTypeSupport);
        DataSupport datas = new DataSupport();
        datas.setDetailTypeSupport(detailTypeSupport1);
        datas.setData(data);
        dataSupportRepository.save(datas);
        mxExtrant.setIdDataSupport(datas.getId());
        mxExtrant.setIntervenant(ksuRecepteur.getId());
        mxExtrant.setPosteEmetteur(ksuEnvoyeur.getId());
        middleWareExtrantRepository.save(mxExtrant);

        ex.setDetailTypeSupport(detailTypeSupport1);
        ex.setTypeSmAvr(typeSmAvr);

        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);
        extrantRepository.save(ex);
        return ex;

    }
}
