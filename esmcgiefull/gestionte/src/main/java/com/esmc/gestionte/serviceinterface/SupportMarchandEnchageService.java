package com.esmc.gestionte.serviceinterface;

import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.exceptions.Exceptions;

import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

public interface SupportMarchandEnchageService {
    /**
     * Liste des agr
     *
     * @return
     */
    List<SupportMarchandEnchage> getSupportMarchandEnchage();

    /**
     * creation d'un detailSMEnchange
     *
     * @param supportMarchandEnchage
     */
    public SupportMarchandEnchage addNewSupportMarchandEnchage(SupportMarchandEnchage supportMarchandEnchage);

    /**
     * supprimer un detailSMEnchange
     *
     * @param id
     */
    public void deleteSupportMarchandEnchage(Long id);

    /**
     * modifiacation d'un supportMarchandEnchage
     *
     * @param id
     * @param supportMarchandEnchage
     * @throws Exceptions
     */

    public void updateSupportMarchandEnchage(Long id, SupportMarchandEnchage supportMarchandEnchage) throws Exceptions;

    /**
     * recherche par id
     *
     * @param id
     * @return
     * @throws Exceptions
     */

    public SupportMarchandEnchage getById(Long id) throws Exceptions;

    public SupportMarchandEnchage getSupportMarchandById(Long id);

    public SupportMarchandEnchage getSupportMarchandEchangeByLibelle(String libelle);

    List<SupportMarchandEnchage> listCircuitBan();

    List<SupportMarchandEnchage> listCircuitBCr();

    List<SupportMarchandEnchage> listCircuitBCnr();

    List<SupportMarchandEnchage> sourceMABAn();

    List<SupportMarchandEnchage> sourceBAn();

    List<SupportMarchandEnchage> sourceBAnOpi();

    List<SupportMarchandEnchage> sourceBLGCp();

    List<SupportMarchandEnchage> circuitMPRgBAn();

    List<SupportMarchandEnchage> listCircuitBLGCp();

    List<SupportMarchandEnchage> listCircuitBLGCsc();

    List<SupportMarchandEnchage> listCircuitSMCIPN();

    List<SupportMarchandEnchage> listCircuitTSMCIPNBudget();

    List<SupportMarchandEnchage> listCircuitTSMCIPNBudgetAnticipe();

    List<SupportMarchandEnchage> listCircuitTSMCIPNServiceInterim();

    List<SupportMarchandEnchage> listCircuitTSMCIPNAutre();

    List<SupportMarchandEnchage> listCircuitBanOpi();

    List<SupportMarchandEnchage> listCircuitOpi();

    List<SupportMarchandEnchage> sourceBLGCsc();

    List<SupportMarchandEnchage> listSmFranchise();

    /****** JAMES LE MAITRE
     *  31/10/2022
     *
     */

    List<SupportMarchandEnchage> sourceBAi_SMCI_Budget();

    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Budget();

    List<SupportMarchandEnchage> sourceBAi_SMCI_Budget_Anticipe();

    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Budget_Anticipe();

    List<SupportMarchandEnchage> sourceBAi_SMCI_Service_Interim();

    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Service_Interim();

    List<SupportMarchandEnchage> sourceBAi_SMCI_PN_Service_Interim();

    List<SupportMarchandEnchage> sourceBAi_SMCI_Autres();

    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Autres();

    List<SupportMarchandEnchage> sourceBAi_SMCI_PN_Autres();

    /****
     *
     *  List
     */

    List<SupportMarchandEnchage> listBCi_SMCI_Budget();

    List<SupportMarchandEnchage> listBCi_SMC_PN_Budget();

    List<SupportMarchandEnchage> listBCi_SMCI_Budget_Anticipe();

    List<SupportMarchandEnchage> listBCi_SMC_PN_Budget_Anticipe();

    List<SupportMarchandEnchage> listBCi_SMCI_Service_Interim();

    List<SupportMarchandEnchage> listBCi_SMC_PN_Service_Interim();

    List<SupportMarchandEnchage> listBCi_SMCI_PN_Service_Interim();

    List<SupportMarchandEnchage> listBCi_SMCI_Autres();

    List<SupportMarchandEnchage> listBCi_SMC_PN_Autres();

    List<SupportMarchandEnchage> listBCi_SMCI_PN_Autres();

    /******* BAi OPI
     *
     */
    List<SupportMarchandEnchage> sourceBAiOpi();

    List<SupportMarchandEnchage> listBCiBAiOPI();

    List<SupportMarchandEnchage> listBLGCp();

    List<SupportMarchandEnchage> sourceMutationBLGCsc();

    List<SupportMarchandEnchage> listOPI();

    List<SupportMarchandEnchage> listMPRg_BPSDEVpPN_BAn();

    List<SupportMarchandEnchage> sourceMutationBPSDEVpPN_BPSDEVpIPN();

    List<SupportMarchandEnchage> listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN();

    List<SupportMarchandEnchage> sourceMutationBPSDEpIPN_BPSDEpPN();

    List<SupportMarchandEnchage> sourceMutationBPSDVpIPN_BPSDVpPN();

    List<SupportMarchandEnchage> listMPRg_BPSDEVpI_BAn();

    List<SupportMarchandEnchage> sourceMutationBPSDEVpI_BPSDEVpIPN();

    List<SupportMarchandEnchage> sourceMutationBPSDEpIPN_BPSDEpI();

    List<SupportMarchandEnchage> sourceMutationBPSDVpIPN_BPSDVpI();

    List<SupportMarchandEnchage> listMPRg_BPSDEVCPN_BAn();

    List<SupportMarchandEnchage> sourceMutationBPSDEVCPN_BPSDVpI();

    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN();

    List<SupportMarchandEnchage> sourceMutationBPSDVCIPN_BPSDVCPN();

    List<SupportMarchandEnchage> listMPRg_BPSDEVCI_BAn();

    List<SupportMarchandEnchage> sourceMutationBPSDEVCI_BPSDEVCIPN();

    List<SupportMarchandEnchage> sourceMutationBPSDECIPN_BPSDECI();

    List<SupportMarchandEnchage> sourceMutationBPSDVCIPN_BPSDVCIPN();

    List<SupportMarchandEnchage> listCircuitBCr_jour();

    List<SupportMarchandEnchage> listCircuitBCr_limite_11_2();

    List<SupportMarchandEnchage> listCircuitBCriRMSBCr22_4();

    List<SupportMarchandEnchage> listCircuitBCr_illimite_22_4();

    List<SupportMarchandEnchage> listCircuitBCnr5_6_PRK_6_5();

    List<SupportMarchandEnchage> listCircuitBCnrPRK_6_5_8();

    List<SupportMarchandEnchage> listBCnrIMM_PRK8_PRK_9();

    List<SupportMarchandEnchage> listBCnrPREIMM11_2_PRK_22_4();

    List<SupportMarchandEnchage> listBCnrPRE_9_22_4();

    List<SupportMarchandEnchage> sourceSMCI_Budget();

    List<SupportMarchandEnchage> sourceSMC_PN_Budget();

    List<SupportMarchandEnchage> sourceBCi_SMCPN_Budget();

    List<SupportMarchandEnchage> sourceSMCI_Budget_Anticipe();

    List<SupportMarchandEnchage> sourceSMC_PN_Budget_Anticipe();


    List<SupportMarchandEnchage> listBPSDVCIPN_BPSDVCI();

    List<SupportMarchandEnchage> listBPSDECIPN_BPSDECI();

    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDEVCI();

    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDEVCPN();

    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDVCIPN();

    List<SupportMarchandEnchage> listBPSDVCIPN_BPSDECIPN();

    List<SupportMarchandEnchage> listBPSDVCIPN_BPSDVCPN();

    List<SupportMarchandEnchage> listBPSDEVCPN_BPSDEVCIPN();

    List<SupportMarchandEnchage> listBPSDEVCI_BPSDEVCIPN();

    List<SupportMarchandEnchage> listMPRg_BPSDEVCPN();

    List<SupportMarchandEnchage> listMPRg_BPSDEVCI();

    List<SupportMarchandEnchage> sourceSMC_PN_Services_Interim();

    List<SupportMarchandEnchage> sourceSMCIPN_Services_Interim();

    List<SupportMarchandEnchage> sourceBAi_SMCI_Services_Interim();

    List<SupportMarchandEnchage> sourceSMCI_Autres();

    List<SupportMarchandEnchage> sourceSMC_PN_Autres();

    List<SupportMarchandEnchage> listBCi_SMCI_Services_Interim();

    List<SupportMarchandEnchage> sourceSMCIPN_Autres();

    List<SupportMarchandEnchage> listCircuitBC();

    List<SupportMarchandEnchage> listCircuitBCi_BLGCp();

    List<SupportMarchandEnchage> sourceBPSDE();

    List<SupportMarchandEnchage> sourceBPSDV();

    List<SupportMarchandEnchage> sourceBPSDEV();

    List<SupportMarchandEnchage> sourceBCi_SMCPN_Budget_Anticipe();

    List<SupportMarchandEnchage> listCircuitMPRgBAn_OPI_non_echus();

    List<SupportMarchandEnchage> listCircuitDecaissment();

    List<SupportMarchandEnchage> listCircuitDecaissementByID(Long id);

    List<SupportMarchandEnchage> muttationDecaissementList(Long idAgr, Long idSm, Long type);

    List<SupportMarchandEnchage> listCircuitDecaissementValeurPropre(Long idAgr, Long idSM);

    List<SupportMarchandEnchage> listCircuitDecaissementValeurCommun(Long idAgr, Long idSM);

    List<SupportMarchandEnchage> listDecaissementFondPropre(Long idAgr);

    List<SupportMarchandEnchage> listDecaissementFondCommun(Long idAgr);

    SupportMarchandEnchage decaissementSMEFp(Long idSM, Long idAgr);

    SupportMarchandEnchage decaissementSMVFp(Long idSM, Long idAgr);

    SupportMarchandEnchage decaissementSMEFc(Long idSM, Long idAgr);

    SupportMarchandEnchage decaissementSMVFc(Long idSM, Long idAgr);

    SupportMarchandEnchage decaissementSM(Long idSM, Long idAgr, Long type);

    List<SupportMarchandEnchage> listMPRGBAn_No_Mutation();

    List<SupportMarchandEnchage> listMPRGOPI_No_Mutation();

    List<SupportMarchandEnchage> listMPRGBAn_OPI_non_echus();

    List<SupportMarchandEnchage> listNo_Mutation_BPSEDCI();


    List<SupportMarchandEnchage> sourceMutationSMCI_Services_Interim();

    List<SupportMarchandEnchage> listBPSDE();

    List<SupportMarchandEnchage> listMPRg();

    List<SupportMarchandEnchage> sourceEqualBPSDEV();

    List<SupportMarchandEnchage> checkFatherChild(Long id);

    List<SupportMarchandEnchage> listMaBanAppro(Long idTe, Long idKsu);

    List<SupportMarchandEnchage> listMaBAnAndMaBAnSpecial();

    List<SupportMarchandEnchage> listMaBAn();
}