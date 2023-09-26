package com.esmc.gestionAvr.services.impl;

import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.inputs.AchatProduitInput;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.services.AchatProduitPOp;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.repositories.ReferenceTokenRepository;
import com.esmc.gestionAvr.tokens.services.ReferencTokenInterface;
import com.esmc.gestionAvr.utils.JavaUtils;
import com.esmc.models.DetailsAgr;
import com.esmc.models.TerminalEchange;
import constants.SupportMarchandConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AchatProduitPreOpEnLigne implements AchatProduitPOp {
    private final KsuRepository ksuRepository;
    private final CategorieAvrRepository categorieAvrRepository;
    private final VagueRepo vagueRepository;
    private final TypeAvrRepository typeAvrRepository;
    private final SettingsRepository setttingsRepository;
    private final JavaUtils utils;
    private final AvrRepository avrRepository;

    private final TeClient teRestClient;
    private final VagueServiceInterface vagueServiceInterface;
    private final ReferenceTokenRepository referenceTokenRepository;
    private final ReferencTokenInterface referencTokenInterface;
    private final DetailAgrClient detailAgrRestClient;


//    @Override
//    public ReferencToken achatProduitPreOpEnLigne(AchatProduitInput data) {
//        CategorieAvr categorieAvr = categorieAvrRepository.findById(data.getIdCategoryAvr()).orElse(null);
//        TypeAvr typeAvr = typeAvrRepository.findById(data.getIdTypeAvr()).orElse(null);
//        Vague vague = vagueRepository.findById(data.getIdVague()).orElse(null);
//        double price = data.getPrixUnitaire();
//        Optional<Double> minimal_value = utils.getValue();
//        if (price < minimal_value.get() &&vague == null) return null;
//        assert typeAvr != null;
//        boolean isSell = typeAvr.getLibelle().toLowerCase().contains("vente");
//        System.out.println("=============IS Achat==============");
//
//        TerminalEchange te = teRestClient.getTeByDetailAgr(data.getIdDetailAgr());
//
//        if(!isSell){
//            teRestClient.depotAInterim(SupportMarchandConstant.QsupportMarchandBan, te.getId(), price);
//        }
//
//        Avr a = new Avr();
//
//        a.setTypeAvr(typeAvr);
//        a.setLibelle(data.getLibelle());
//        a.setCategorieAvr(categorieAvr);
//        a.setPrixUnitaire( Double.parseDouble(vagueServiceInterface.convertBanBci(price)));
//        a.setDetailAgr(data.getIdDetailAgr());
//        Avr avr1 = avrRepository.save(a);
//
//        DetailsAgr d = detailAgrRestClient.getDetailById(data.getIdDetailAgr());
//        Ksu ksu = ksuRepository.findKsuById(d.getKsu());
//
//
//        ReferencToken referencTokenGIE = referenceTokenRepository.getReferenceTokenGIEActif(6L);
//        return referencTokenInterface.tokenGIE_Division_filsClient(referencTokenGIE.getId(),ksu.getId(),data.getPrixUnitaire(),avr1);
//
//
//    }

    @Override
    public ReferenceTokenOp achatProduitPreOpEnLigne(AchatProduitInput data) {
        Optional<CategorieAvr> categorieAvr = categorieAvrRepository.findById(data.getIdCategoryAvr());
        Optional<TypeAvr> typeAvr = typeAvrRepository.findById(data.getIdTypeAvr());
        Optional<Vague> vague = vagueRepository.findById(data.getIdVague());
        double price = vagueServiceInterface.convertBanBciVague0(data.getPrixUnitaire());
        Optional<Double> minimal_value = utils.getValue();
        if (!minimal_value.isPresent() || price < minimal_value.get() || !vague.isPresent()) return null;
        assert typeAvr.isPresent();
        boolean isSell = typeAvr.get().getLibelle().toLowerCase().contains("vente");
        System.out.println("=============IS Achat==============");

        TerminalEchange te = teRestClient.getTeByDetailAgr(data.getIdDetailAgrAcheteur());

        if (!isSell) {
            teRestClient.depotAInterim(SupportMarchandConstant.QsupportMarchandBan, te.getId(), price);
        }

        Avr a = new Avr();
        a.setTypeAvr(typeAvr.get());
        a.setLibelle(data.getLibelle());
        a.setCategorieAvr(categorieAvr.get());
        a.setPrixUnitaire(price);
        a.setDetailAgr(data.getIdDetailAgrAcheteur());
        Avr avr1 = avrRepository.save(a);

        DetailsAgr d = detailAgrRestClient.getDetailById(data.getIdDetailAgrAcheteur());
        Ksu ksu = ksuRepository.findKsuById(d.getKsu());

        ReferencToken referencTokenGIE = referenceTokenRepository.getReferenceTokenGIEActif(6L);
        return referencTokenInterface.tokenGIE_Division_filsClientAchatOP(referencTokenGIE.getId(), ksu.getId(), price, avr1);
    }
}
