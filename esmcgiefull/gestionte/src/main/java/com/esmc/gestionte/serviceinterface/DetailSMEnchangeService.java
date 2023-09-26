package com.esmc.gestionte.serviceinterface;


import com.esmc.gestionte.constant.DetailTotal;
import com.esmc.gestionte.entities.DetailSMEnchange;
import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.inputs.PayerVendeurKsu;
import com.google.zxing.WriterException;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author Amemorte  (manes)
 */

public interface DetailSMEnchangeService {

    void changeSelectDetailSMEnchange(Long selectElementId);

    void changeStatuSelectDetailSMEnchange(Long selectElementId, Boolean status);

    List<DetailSMEnchange> listDetailSmeByTe(Long id);

    List<DetailSMEnchange> getDeatilSMEnchange();

    // Fonction pour Supprimer les details support marchand d'echange
    @Transactional
    void deleteDetailSMEnchange(Long id) throws Exceptions;

    // Fonction pour Modifier un detail support marchand d'echange
    @Transactional
    void updateDetailSMEnchange(Long id, DetailSMEnchange detailSMEnchange) throws Exceptions;

    // Fonction pour recupperer un support marchand d'echange
    DetailSMEnchange getById(Long id) throws Exceptions;

    // Fonction pour faire l'encaissement
    @Transactional
    void encaissement(Double montant, String typeBAn, Long te) throws Exceptions, IOException, WriterException;

    @Transactional
    boolean decaissement(Double montant, String supportMarchandEnchage, Long te) throws Exceptions, IOException, WriterException;

    @Transactional
    Double cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(String supportMarchandEnchage, Long te) throws Exceptions, IOException, WriterException;

    void encaissementVide(Double montant, String typeBAn, Long teEn) throws Exceptions, IOException, WriterException;

    @Transactional
    void enchange(Double montant, String typeBAn, Long teEn, Long teDe) throws Exceptions, IOException, WriterException;

    List<DetailSMEnchange> listBciReinitialiser(Long id);

    DetailSMEnchange approvisionnement(Double montant, Long te) throws Exceptions, IOException, WriterException;

    List<DetailSMEnchange> listDSMEByIdTeAndSM(Long idTe, Long supportME) throws Exceptions;

    DetailTotal [] sommeTotalByIdTe(Long idTe) throws Exceptions;

    Double MutationMPRgBAnEntrerM(Double MPRgBAn, Long idTe) throws Exceptions, IOException, WriterException;

    List<DetailSMEnchange> listDetailSMEnchangeByTeAndSupportExchange(Long idTe);

    /*
        /**
         *
         * @param idTe
         * @param montant
         *
         *
         *     Processus MEV -- BAN
         *
         */
    DetailSMEnchange mutationProcessusMevBan(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusMevMABAn(Long id, Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusMevMABAnNormal(Long id, Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange gennerateMev(Long idTransaction) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationMPRgOPIBpsdv(Long idTransDacaissement) throws Exceptions, IOException, WriterException;

    void generatedCircuitMevBGCs(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    Double checkSmTotalCurrent(Long idSm, Long idTe);

    boolean checkSmTotal(Long idTe, Long idSm, Double montant);

    DetailSMEnchange mutationPourAchatFifoAdmin(Long idTeAcheteur, Long idTeVendeur, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationPourAchatFifo(Long idTeAcheteur, Long idTeVendeur, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusSMCIPNBcismciBciSMCI(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusSMCIPNBcismciBciSMCPN(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusBcismcpnBlgcp(Long idTeSource, Long idTeDestination, Long idSmE) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusBcismciBlgcp(Long idTeSource, Long idTeDestination, Long idSmE) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusBlgcpBciReinitialise(Long idTe, Long idSmE) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusBlgcpBciReinitialise(Long idTe, Long idSmE, Double montantRenitialiserBCI) throws Exceptions, IOException, WriterException;

    DetailSMEnchange renitialisationPassif(Double montantBAn, Long idTe) throws Exceptions, IOException, WriterException;

    DetailSMEnchange renitialisationBCI(Double montantBAn, Long idTe) throws Exceptions, IOException, WriterException;

    void mutationProcessusBAnBcr(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrBcrParjour(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrParJourBcrLimite_11_2(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBAnMPRgBan(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBAnBCnr(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrParjourBLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrLimite_11_2BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrLimite_22_4BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrIllimite_22_2BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusOPI_nonEchusBLGCP(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnr5_6_PRK_6_5BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrPRK_6_5_8BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrIMMPRK8_PRK9BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrPRE_9_22_4BLGCp(Long idTe, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrPREIMM_11_2_PRK_22_4BLGCp(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrParjourBLGsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrLimite11_2BLGsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrLimite22_4BLGsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCrIllimite22_4BLGsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusOPI_non_chusBLGCsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnr5_6_PRK_6_5BLGCsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrPRK_6_5_8BLGCsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrIMM_PRK8_PRK_9BLGCsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrPRE_9_22_4BLGCsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBCnrPREIMM11_2_PRK_22_4BLGCsc(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBLGCpBCiBLGCp(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBLGCpMPRg_BAn_OPI_echus(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    Object mutationProcessusBLGCpOPI(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusOPIMPRg_OPI(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusBLGCscSMCIPN(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCIPNTSMCIPN_Budget(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCIPNTSMCIPN_Budget_Anticipe(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCIPNTSMCIPN_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCIPNTSMCIPN_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPNSMCI_Budget(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPNSMC_PN_Budget(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_Budget_AnticipeSMCI_Budget_Anticipe(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_Budget_AnticipeSMC_PN_Budget_Anticipe(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_Services_InterimSMCI_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_Services_InterimSMC_PN_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_Services_InterimSMCIPN_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_AutresSMCI_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_AutresSMC_PN_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusTSMCIPN_AutresSMCIPN_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCI_BudgetBAi_SMCI_Budget(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMC_PN_AutresBAi_SMC_PN_Budget(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCI_Budget_AnticipeBAi_SMCI_Budget_Anticipe(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMC_PN_Budget_AnticipeBAi_SMC_PN_Budget_Anticipe(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCI_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMC_PN_Services_InterimBAi_SMC_PN_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCIPN_Services_InterimBAi_SMCIPN_Services_Interim(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCI_AutresBAi_SMCI_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMC_PN_AutresBAi_SMCI_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    void mutationProcessusSMCIPN_AutresBAi_SMCIPN_Autres(Long idTe, Long id, Double montant) throws Exceptions, IOException, WriterException;

    boolean checkBCiBlGpFondsEntre(Long idTe, Double montant);

    boolean checkSmFondsEntre(Long idTe, Double montant);

    List<DetailSMEnchange> listdSMFondsDisponible(Long idSm, Long idTe);

    DetailSMEnchange achatDesendettement(Long idTe) throws Exceptions, IOException, WriterException;

    DetailSMEnchange achatFranchiseNormalByBciBlgp(Long idTe, Long idSm, String code) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationProcessusBan(Long idTe, Long idSm, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange mutationSMProcessus(Long idTe, Long idSmMuttan, Long idSmMutter, Double montant) throws Exceptions, IOException, WriterException;

    Double amountBciTe(Long idTe);

    DetailSMEnchange mutationFondDisponibleUsed(Long idTe, Double montant);

    Double sommeSm(Long idTe);

    DetailSMEnchange mutationProcessusBCnrPrk7(Long idTe, Long idSm, Double montant) throws Exceptions, IOException, WriterException;

    List<DetailSMEnchange> listDetailSm(Long idTe);

    List<DetailSMEnchange> listSm(Long idSm, Long idTe);

    DetailSMEnchange depotAInterim(Long idIdSm, Long idTeEmeteur, Long idteDestinateur, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange depotAInterimAchatKsu(Long idIdSm, Long idteDestinateur, Double montant, Long refer)
            throws Exceptions, IOException, WriterException;

    Double sommeTotalSm(Long idSm, Long idTe);

    ///listSmMuter
    List<DetailSMEnchange> listSmMuter(Long idTE, Long idSM);

    DetailSMEnchange acheteurVendeur(Long idTEAcheteur, Long idSMAcheteur,
                                     Long idTEVendeur, double montant) throws IOException, WriterException, Exceptions;

    DetailSMEnchange vendeurAcheteur(Long idTEAcheteur, Long idSMVendeur,
                                     Long idTEVendeur, double montant) throws IOException, WriterException, Exceptions;

    boolean supply(Long idTEAcheteur, Long idSMVendeur, Long idTEVendeur,
                            Long idSMAcheteur, double montant)
                                             throws IOException, WriterException, Exceptions;

    DetailSMEnchange debitSm(Long idSm, Long idTeAcheteur, Long idTeVendeur, Double montant) throws IOException, WriterException, Exceptions;

    DetailSMEnchange achatCertifSm(Long idSm, Long idTeAcheteur, Long idTeVendeur, Double montant) throws Exceptions, IOException, WriterException;

    DetailSMEnchange achatCertifSmAchatKsu(Long idSm, Double montant, Long refer) throws Exceptions, IOException, WriterException;

    DetailSMEnchange payementVandeurKsu(PayerVendeurKsu data) throws IOException, WriterException, Exceptions;

    DetailSMEnchange approBAnByPassif(Long idTe, int type) throws Exceptions, IOException, WriterException;


    ///listSmMuter

}
