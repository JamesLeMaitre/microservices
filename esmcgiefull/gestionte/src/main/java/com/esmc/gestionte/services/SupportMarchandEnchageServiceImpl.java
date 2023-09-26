package com.esmc.gestionte.services;

import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.feign.DetailAgrRestClient;
import com.esmc.gestionte.feign.KsuRestClient;
import com.esmc.gestionte.repositories.SupportMarchandEnchageRepository;
import com.esmc.gestionte.serviceinterface.SupportMarchandEnchageService;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import constants.AccountConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 * @author Amemorte
 * @since 05/05/2022
 */


@Service
public class SupportMarchandEnchageServiceImpl implements SupportMarchandEnchageService {

    @Autowired
    private SupportMarchandEnchageRepository supportMarchandEnchageRepository;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Override
    public List<SupportMarchandEnchage> getSupportMarchandEnchage() {
        return  supportMarchandEnchageRepository.listWithout();
    }

    @Override
    @Transactional
    public SupportMarchandEnchage addNewSupportMarchandEnchage(SupportMarchandEnchage supportMarchandEnchage) {
         return supportMarchandEnchageRepository.save(supportMarchandEnchage);
    }

    @Override
    @Transactional
    public void deleteSupportMarchandEnchage(Long id) {

        supportMarchandEnchageRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateSupportMarchandEnchage(Long id, SupportMarchandEnchage supportMarchandEnchage) throws Exceptions {

        if(!isPresent(supportMarchandEnchage.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        SupportMarchandEnchage supportMarchandEnchage1=getById(supportMarchandEnchage.getId());

        supportMarchandEnchage1.setLibelle(supportMarchandEnchage.getLibelle());
        supportMarchandEnchage1.setEtat(supportMarchandEnchage.isEtat());
        supportMarchandEnchage1.setDateUpdate(new Date());

        //update supportMarchandEnchage
        supportMarchandEnchage=supportMarchandEnchageRepository.save(supportMarchandEnchage1);

        if (supportMarchandEnchage==null){
            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
        }
    }

    @Override
    public SupportMarchandEnchage getById(Long id) throws Exceptions {
        if(!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<SupportMarchandEnchage> opad=supportMarchandEnchageRepository.findById(id);
        return  opad.get();
    }

    @Override
    public SupportMarchandEnchage getSupportMarchandById(Long id) {
        return supportMarchandEnchageRepository.findById(id).orElse(null);
    }

    @Override
    public SupportMarchandEnchage getSupportMarchandEchangeByLibelle(String libelle) {
        return supportMarchandEnchageRepository.findSupportMarchandEnchageByLibelle(libelle);
    }

    public boolean isPresent(Long id){
        Optional<SupportMarchandEnchage> opab=supportMarchandEnchageRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBan() {
        return supportMarchandEnchageRepository.listCircuitBan();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCr() {
        return supportMarchandEnchageRepository.listCircuitBCr();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCnr() {
        return supportMarchandEnchageRepository.listCircuitBCnr();
    }

    @Override
    public List<SupportMarchandEnchage> circuitMPRgBAn() {
        return supportMarchandEnchageRepository.circuitMPRgBAn();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBLGCp() {
        return supportMarchandEnchageRepository.listCircuitBLGCp();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBLGCsc() {
        return supportMarchandEnchageRepository.listCircuitBLGCsc();
    }
    @Override
    public List<SupportMarchandEnchage> listCircuitSMCIPN() {
        return supportMarchandEnchageRepository.listCircuitSMCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitTSMCIPNBudget() {
        return supportMarchandEnchageRepository.listCircuitTSMCIPNBudget();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitTSMCIPNBudgetAnticipe() {
        return supportMarchandEnchageRepository.listCircuitTSMCIPNBudgetAnticipe();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitTSMCIPNServiceInterim() {
        return supportMarchandEnchageRepository.listCircuitTSMCIPNServiceInterim();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitTSMCIPNAutre() {
        return supportMarchandEnchageRepository.listCircuitTSMCIPNAutre();
    }
    @Override
    public List<SupportMarchandEnchage> listCircuitBanOpi() {
        return supportMarchandEnchageRepository.listCircuitBanOpi();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitOpi() {
        return supportMarchandEnchageRepository.listCircuitOpi();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMABAn() {
        return supportMarchandEnchageRepository.sourceMABAn();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAn() {
        return supportMarchandEnchageRepository.sourceBAn();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAnOpi() {
        return supportMarchandEnchageRepository.sourceBAnOpi();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBLGCp() {
        return supportMarchandEnchageRepository.sourceBLGCp();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBLGCsc() {
        return supportMarchandEnchageRepository.sourceBLGCsc();
    }

    @Override
    public List<SupportMarchandEnchage> listSmFranchise() {
        return supportMarchandEnchageRepository.listSmFranchise();
    }

    /******* JAMES LE MAITRE
     * 31/10/2022
     *
     */
    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_Budget() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_Budget();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMC_PN_Budget() {
        return supportMarchandEnchageRepository.sourceBAi_SMC_PN_Budget();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_Budget_Anticipe() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_Budget_Anticipe();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMC_PN_Budget_Anticipe() {
        return supportMarchandEnchageRepository.sourceBAi_SMC_PN_Budget_Anticipe();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_Service_Interim() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_Service_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMC_PN_Service_Interim() {
        return supportMarchandEnchageRepository.sourceBAi_SMC_PN_Service_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_PN_Service_Interim() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_PN_Service_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_Autres() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_Autres();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMC_PN_Autres() {
        return supportMarchandEnchageRepository.sourceBAi_SMC_PN_Autres();
    }



    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_PN_Autres() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_PN_Autres();
    }

    /*********
     ********************************
     */

    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_Budget() {
        return supportMarchandEnchageRepository.listBCi_SMCI_Budget();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMC_PN_Budget() {
        return supportMarchandEnchageRepository.listBCi_SMC_PN_Budget();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_Budget_Anticipe() {
        return supportMarchandEnchageRepository.listBCi_SMCI_Budget_Anticipe();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMC_PN_Budget_Anticipe() {
        return supportMarchandEnchageRepository.listBCi_SMC_PN_Budget_Anticipe();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_Service_Interim() {
        return supportMarchandEnchageRepository.listBCi_SMCI_Service_Interim();
    }



    @Override
    public List<SupportMarchandEnchage> listBCi_SMC_PN_Service_Interim() {
        return supportMarchandEnchageRepository.listBCi_SMC_PN_Service_Interim();
    }



    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_PN_Service_Interim() {
        return supportMarchandEnchageRepository.listBCi_SMCI_PN_Service_Interim();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_Autres() {
        return supportMarchandEnchageRepository.listBCi_SMCI_Autres();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMC_PN_Autres() {
        return supportMarchandEnchageRepository.listBCi_SMC_PN_Autres();
    }


    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_PN_Autres() {
        return supportMarchandEnchageRepository.listBCi_SMCI_PN_Autres();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAiOpi() {
        return supportMarchandEnchageRepository.sourceBAiOPi();
    }

    @Override
    public List<SupportMarchandEnchage> listBCiBAiOPI() {
        return supportMarchandEnchageRepository.listBCiBAiOPI();
    }

    @Override
    public List<SupportMarchandEnchage> listBLGCp() {
        return supportMarchandEnchageRepository.listBLGCp();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBLGCsc() {
        return supportMarchandEnchageRepository.sourceMutationBLGCsc();
    }

    @Override
    public List<SupportMarchandEnchage> listOPI() {
        return supportMarchandEnchageRepository.listOPI();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg_BPSDEVpPN_BAn() {
        return supportMarchandEnchageRepository.listMPRg_BPSDEVpPN_BAn();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDEVpPN_BPSDEVpIPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDEVpPN_BPSDEVpIPN();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN() {
        return supportMarchandEnchageRepository.listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDEpIPN_BPSDEpPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDEpIPN_BPSDEpPN();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDVpIPN_BPSDVpPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDVpIPN_BPSDVpPN();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg_BPSDEVpI_BAn() {
        return supportMarchandEnchageRepository.listMPRg_BPSDEVpI_BAn();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDEVpI_BPSDEVpIPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDEVpI_BPSDEVpIPN();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDEpIPN_BPSDEpI() {
        return supportMarchandEnchageRepository.sourceMutationBPSDEpIPN_BPSDEpI();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDVpIPN_BPSDVpI() {
        return supportMarchandEnchageRepository.sourceMutationBPSDVpIPN_BPSDVpI();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg_BPSDEVCPN_BAn() {
        return supportMarchandEnchageRepository.listMPRg_BPSDEVCPN_BAn();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDEVCPN_BPSDVpI() {
        return supportMarchandEnchageRepository.sourceMutationBPSDEVCPN_BPSDVpI();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN() {
        return supportMarchandEnchageRepository.listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDVCIPN_BPSDVCPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDVCIPN_BPSDVCPN();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg_BPSDEVCI_BAn() {
        return supportMarchandEnchageRepository.listMPRg_BPSDEVCI_BAn();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDEVCI_BPSDEVCIPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDEVCI_BPSDEVCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDECIPN_BPSDECI() {
        return supportMarchandEnchageRepository.sourceMutationBPSDECIPN_BPSDECI();
    }

    @Override
    public List<SupportMarchandEnchage> sourceMutationBPSDVCIPN_BPSDVCIPN() {
        return supportMarchandEnchageRepository.sourceMutationBPSDVCIPN_BPSDVCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCr_jour() {
        return supportMarchandEnchageRepository.listCircuitBCr_jour();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCr_limite_11_2() {
        return supportMarchandEnchageRepository.listCircuitBCr_limite_11_2();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCriRMSBCr22_4() {
        return supportMarchandEnchageRepository.listCircuitBCriRMSBCr22_4();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCr_illimite_22_4() {
        return supportMarchandEnchageRepository.listCircuitBCr_illimite_22_4();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCnr5_6_PRK_6_5() {
        return supportMarchandEnchageRepository.listCircuitBCnr5_6_PRK_6_5();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCnrPRK_6_5_8() {
        return supportMarchandEnchageRepository.listCircuitBCnrPRK_6_5_8();
    }

    @Override
    public List<SupportMarchandEnchage> listBCnrIMM_PRK8_PRK_9() {
        return supportMarchandEnchageRepository.listBCnrIMM_PRK8_PRK_9();
    }

    @Override
    public List<SupportMarchandEnchage> listBCnrPREIMM11_2_PRK_22_4() {
        return supportMarchandEnchageRepository.listBCnrPREIMM11_2_PRK_22_4();
    }

    @Override
    public List<SupportMarchandEnchage> listBCnrPRE_9_22_4() {
        return supportMarchandEnchageRepository.listBCnrPRE_9_22_4();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMCI_Budget() {
        return supportMarchandEnchageRepository.sourceSMCI_Budget();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMC_PN_Budget() {
        return supportMarchandEnchageRepository.sourceSMC_PN_Budget();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBCi_SMCPN_Budget() {
        return supportMarchandEnchageRepository.sourceBCi_SMCPN_Budget();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMCI_Budget_Anticipe() {
        return supportMarchandEnchageRepository.sourceSMCI_Budget_Anticipe();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMC_PN_Budget_Anticipe() {
        return supportMarchandEnchageRepository.sourceSMC_PN_Budget_Anticipe();
    }


    @Override
    public List<SupportMarchandEnchage> listBPSDVCIPN_BPSDVCI() {
        return supportMarchandEnchageRepository.listBPSDVCIPN_BPSDVCI();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDECIPN_BPSDECI() {
        return supportMarchandEnchageRepository.listBPSDECIPN_BPSDECI();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDVCIPN_BPSDVCPN() {
        return supportMarchandEnchageRepository.listBPSDVCIPN_BPSDVCPN();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDEVCI() {
        return supportMarchandEnchageRepository.listBPSDEVCIPN_BPSDEVCI();
    }


    @Override
    public List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDEVCPN() {
        return supportMarchandEnchageRepository.listBPSDEVCIPN_BPSDEVCPN();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDVCIPN() {
        return supportMarchandEnchageRepository.listBPSDEVCIPN_BPSDVCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDVCIPN_BPSDECIPN() {
        return supportMarchandEnchageRepository.listBPSDVCIPN_BPSDECIPN();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg_BPSDEVCPN() {
        return supportMarchandEnchageRepository.listMPRg_BPSDEVCPN();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg_BPSDEVCI() {
        return supportMarchandEnchageRepository.listMPRg_BPSDEVCI();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDEVCPN_BPSDEVCIPN() {
        return supportMarchandEnchageRepository.listBPSDEVCPN_BPSDEVCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> listBPSDEVCI_BPSDEVCIPN() {
        return supportMarchandEnchageRepository.listBPSDEVCI_BPSDEVCIPN();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMC_PN_Services_Interim() {
        return supportMarchandEnchageRepository.sourceSMC_PN_Services_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMCIPN_Services_Interim() {
        return supportMarchandEnchageRepository.sourceSMCIPN_Services_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBAi_SMCI_Services_Interim() {
        return supportMarchandEnchageRepository.sourceBAi_SMCI_Services_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMCI_Autres() {
        return supportMarchandEnchageRepository.sourceSMCI_Autres();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMC_PN_Autres() {
        return supportMarchandEnchageRepository.sourceSMC_PN_Autres();
    }

    @Override
    public List<SupportMarchandEnchage> listBCi_SMCI_Services_Interim() {
        return supportMarchandEnchageRepository.listBCi_SMCI_Services_Interim();
    }

    @Override
    public List<SupportMarchandEnchage> sourceSMCIPN_Autres() {
        return supportMarchandEnchageRepository.sourceSMCIPN_Autres();
    }



    @Override
    public List<SupportMarchandEnchage> listCircuitBC() {
        return supportMarchandEnchageRepository.listCircuitBC();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitBCi_BLGCp() {
        return supportMarchandEnchageRepository.listCircuitBCi_BLGCp();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBPSDE() {
        return supportMarchandEnchageRepository.sourceBPSDE();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBPSDV() {
        return supportMarchandEnchageRepository.sourceBPSDV();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBPSDEV() {
        return supportMarchandEnchageRepository.sourceBPSDEV();
    }

    @Override
    public List<SupportMarchandEnchage> sourceBCi_SMCPN_Budget_Anticipe() {
        return supportMarchandEnchageRepository.sourceBCi_SMCPN_Budget_Anticipe();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitMPRgBAn_OPI_non_echus() {
        return supportMarchandEnchageRepository.listCircuitMPRgBAn_OPI_non_echus();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitDecaissment() {
        return supportMarchandEnchageRepository.listCircuitDecaissment();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitDecaissementByID(Long id) {
        SupportMarchandEnchage  marchandEnchage = supportMarchandEnchageRepository.findById(id).orElse(null);

        assert marchandEnchage != null;
        if (marchandEnchage.getId() == 5 || marchandEnchage.getId() == 6) {
            return supportMarchandEnchageRepository.listMPRg_BPSDEVpPN_BAn();
        } else if (marchandEnchage.getId() == 80) {
            return supportMarchandEnchageRepository.sourceMutationBPSDEVpPN_BPSDEVpIPN();
        } else if (marchandEnchage.getId() == 67) {
            return supportMarchandEnchageRepository.sourceMutationBPSDVCIPN_BPSDVCPN();
        } else if (marchandEnchage.getId() == 74) {
            return supportMarchandEnchageRepository.sourceMutationBPSDEVpI_BPSDEVpIPN();
        } else if (marchandEnchage.getId() == 68) {
            return supportMarchandEnchageRepository.listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN();
        } else if (marchandEnchage.getId() == 81) {
            return supportMarchandEnchageRepository.sourceMutationBP();
        } else if (marchandEnchage.getId() == 84) {
            return supportMarchandEnchageRepository.sourceMutationBPSDVCIPN_BPSDVCIPN();
        } else if (marchandEnchage.getId() == 75) {
            return supportMarchandEnchageRepository.sourceMutationBPSDEpIPN_BPSDEpI();
        } else if (marchandEnchage.getId() == 78) {
            return supportMarchandEnchageRepository.sourceMutationBPSDVpIPN_BPSDVpI();
        }

       return supportMarchandEnchageRepository.vide();
    }

    @Override
    public List<SupportMarchandEnchage> muttationDecaissementList(Long idAgr, Long idSm, Long type) {

        if (type == 0) {
            return listCircuitDecaissementValeurPropre(idAgr, idSm);
        }
        return listCircuitDecaissementValeurCommun(idAgr, idSm);
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitDecaissementValeurPropre(Long idAgr,Long idSM) {
        SupportMarchandEnchage sm  = supportMarchandEnchageRepository.findById(idSM).orElse(null);
        assert sm != null;
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idAgr);
        if (detailsAgr.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")){
            if (sm.getId() == 5 || sm.getId() == 6){
                return supportMarchandEnchageRepository.listCircuitMPRG_OT();
            } else if(sm.getId() == 73){
                return supportMarchandEnchageRepository.listCircuitBPSDEVpPN_OT();
            } else if(sm.getId() == 74){
                return supportMarchandEnchageRepository.listCircuitBPSDEVpIPN_OT();
            } else if(sm.getId()== 75){
                return supportMarchandEnchageRepository.listCircuitBPSDEpIPN_OT();
            } else if(sm.getId()== 78) {
                return supportMarchandEnchageRepository.listCircuitBPSDVpIPN_OT();
            }
        } else {
            if (sm.getId() == 5 || sm.getId()== 6){
                return supportMarchandEnchageRepository.listCircuitMPRG_AUTRES();
            } else if(sm.getId() == 72){
                return supportMarchandEnchageRepository.listCircuitBPSDEVpI_AUTRES();
            } else if(sm.getId() == 74){
                return supportMarchandEnchageRepository.listCircuitBPSDEVpIPN_AUTRES();
            } else if(sm.getId() == 75){
                return supportMarchandEnchageRepository.listCircuitBPSDVpIPN_AUTRE();
            } else if(sm.getId() == 78){
                return supportMarchandEnchageRepository.listCircuitBPSDVpIPN_AUTRES();
            }
        }
        return supportMarchandEnchageRepository.vide();
    }

    @Override
    public List<SupportMarchandEnchage> listCircuitDecaissementValeurCommun(Long idAgr, Long idSM) {
        SupportMarchandEnchage sm  = supportMarchandEnchageRepository.findById(idSM).orElse(null);
        assert sm != null;
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idAgr);
        if (detailsAgr.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")){
            if (sm.getId() == 5 || sm.getId() == 6){
                return supportMarchandEnchageRepository.listCircuit_C_MPRG_OT();
            } else if(sm.getId() == 82){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDEVCPN_OT();
            } else if(sm.getId() == 68){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDEVCIPN_OT();
            } else if(sm.getId() == 84){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDECIPN_OT();
            } else if(sm.getId() == 81) {
                return supportMarchandEnchageRepository.listCircuit_C_BPSDECIPN_AUTRES();
            }
        } else {
            if (sm.getId() == 5 || sm.getId()== 6){
                return supportMarchandEnchageRepository.listCircuit_C_MPRG_AUTRES();
            } else if(sm.getId() == 83){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDEVCI_AUTRES();
            } else if(sm.getId() == 68){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDEVCIPN_AUTRES();
            } else if(sm.getId() == 84){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDVCIPN_OT();
            } else if(sm.getId() == 81){
                return supportMarchandEnchageRepository.listCircuit_C_BPSDVCIPN_AUTRES();
            }
        }
        return supportMarchandEnchageRepository.vide();
    }

    @Override
    public List<SupportMarchandEnchage> listDecaissementFondPropre(Long idAgr) {

        DetailsAgr d = detailAgrRestClient.getDetailById(idAgr);


        if (d.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")) {
            return supportMarchandEnchageRepository.listFondsPropreOT();
        }

        return supportMarchandEnchageRepository.listFondsPropreAutres();
    }

    @Override
    public List<SupportMarchandEnchage> listDecaissementFondCommun(Long idAgr) {
        DetailsAgr d = detailAgrRestClient.getDetailById(idAgr);

        if (d.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")) {
            return supportMarchandEnchageRepository.listFondsCommunOT();
        }
        return supportMarchandEnchageRepository.listFondsCommunAutres();
    }

    @Override
    public SupportMarchandEnchage decaissementSMEFp(Long idSM, Long idAgr) {
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idAgr);
        if (idSM == 75 && detailsAgr.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")) {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDEpIPN_EpPN();

        }else {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDEpIPN_EpI();
        }

    }

    @Override
    public SupportMarchandEnchage decaissementSMVFp(Long idSM, Long idAgr) {
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idAgr);
        if (idSM == 78 && detailsAgr.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")) {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDVpIPN_VpPN();

        }else {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDVpIPN_VpI();
        }

    }

    @Override
    public SupportMarchandEnchage decaissementSMEFc(Long idSM, Long idAgr) {
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idAgr);
        if (idSM == 84 && detailsAgr.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")) {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDECIPN_ECPN();

        }else {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDECIPN_ECI();
        }

    }

    @Override
    public SupportMarchandEnchage decaissementSMVFc(Long idSM, Long idAgr) {
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idAgr);
        if (idSM == 81 && detailsAgr.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OT")) {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDVCIPN_VCPN();

        }else {
            return supportMarchandEnchageRepository.decaissementSMCircuitBPSDVCIPN_VCI();
        }

    }

    @Override
    public SupportMarchandEnchage decaissementSM(Long idSM, Long idAgr, Long type) {

        SupportMarchandEnchage sm  =  supportMarchandEnchageRepository.findById(idSM).orElse(null);

        System.out.println("=================================04===================================");
        System.out.println(sm);
        System.out.println("=====================================================================");
        if (sm == null) {
            return null;
        }

        System.out.println("=================================05===================================");
        System.out.println(sm.getType());
        System.out.println("=====================================================================");
        Long t= Long.valueOf(sm.getType());

        if(type == 0  && t==5 && sm.getId() == 75){
                return decaissementSMEFp(idSM, idAgr);
        }else if (type == 0 && t==6 && sm.getId() == 78){
                return decaissementSMVFp(idSM, idAgr);
        } else if (type == 1 && t==5 && sm.getId()==84) {
                return decaissementSMEFc(idSM, idAgr);
        } else if (type == 1 && t==6 && sm.getId()==81){
                return decaissementSMVFc(idSM, idAgr);
        } else {
            return null;
        }
    }


    /****** MPRG Mutation  && NO MUTation
     *
     */

    @Override
    public List<SupportMarchandEnchage> listMPRGBAn_No_Mutation() {
        return supportMarchandEnchageRepository.listMPRGBAn_No_Mutation();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRGOPI_No_Mutation() {
        return supportMarchandEnchageRepository.listMPRGOPI_No_Mutation();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRGBAn_OPI_non_echus() {
        return supportMarchandEnchageRepository.listMPRGBAn_OPI_non_echus();
    }

    @Override
    public List<SupportMarchandEnchage> listNo_Mutation_BPSEDCI() {
        return supportMarchandEnchageRepository.listNo_Mutation_BPSEDCI();
    }


    @Override
    public List<SupportMarchandEnchage> sourceMutationSMCI_Services_Interim() {
        return supportMarchandEnchageRepository.sourceMutationSMCI_Services_Interim();
    }

    //* NEW Mutation ***/

    @Override
    public List<SupportMarchandEnchage> listBPSDE() {
        return supportMarchandEnchageRepository.listBPSDE();
    }

    @Override
    public List<SupportMarchandEnchage> listMPRg() {
        return supportMarchandEnchageRepository.listMPRg();
    }

    @Override
    public List<SupportMarchandEnchage> sourceEqualBPSDEV() {
        return supportMarchandEnchageRepository.sourceEqualBPSDEV();
    }

    @Override
    public List<SupportMarchandEnchage> checkFatherChild(Long id) {
        SupportMarchandEnchage supportMarchandEnchage = supportMarchandEnchageRepository.findById(id).orElse(null);

        //System.out.println(supportMarchandEnchage.getId());
        if(supportMarchandEnchage.getId() == null){
            return  null;
        }
        if (supportMarchandEnchage.getId()== 63){
            return supportMarchandEnchageRepository.listBPSDE_Mutation();
        } else if(supportMarchandEnchage.getId() == 69){
            return supportMarchandEnchageRepository.listBPSDEVp_Mutation();
        } else if (supportMarchandEnchage.getId() != 63 || supportMarchandEnchage.getId() != 69) {
            return supportMarchandEnchageRepository.vide();
        }
        return null;
    }

    @Override
    public List<SupportMarchandEnchage> listMaBanAppro(Long idTe, Long idKsu) {

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        Ksu k = ksuRestClient.getById(idKsu);

        if (k == null) {
            return null;
        }

        String codeKsu = k.getCodeKsu();

        if (codeKsu.contains("M")) {
            if (idTe.equals(admin)) {
                return listMaBAnAndMaBAnSpecial();
            } else {
                return listMaBAn();
            }
        }
        else {
            return supportMarchandEnchageRepository.listBAn();
        }
    }

    @Override
    public List<SupportMarchandEnchage> listMaBAnAndMaBAnSpecial() {
        return supportMarchandEnchageRepository.listMaBAnAndMaBAnSpecial();
    }

    @Override
    public List<SupportMarchandEnchage> listMaBAn() {
        return supportMarchandEnchageRepository.listMaBAn();
    }


}
