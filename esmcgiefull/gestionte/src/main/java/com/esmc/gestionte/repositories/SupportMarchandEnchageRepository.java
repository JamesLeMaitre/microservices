package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.SupportMarchandEnchage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

@Repository
public interface  SupportMarchandEnchageRepository extends JpaRepository<SupportMarchandEnchage,Long> {

    @Query("select s from SupportMarchandEnchage s where   s.id < 61 and s.id NOT IN (1,2,5,6) ")
    public List<SupportMarchandEnchage> getAllWithoutBPSD();

    @Query("select s.id from SupportMarchandEnchage s where trim(lower(s.libelle)) = :x")
    public Long findSMbyId(@Param("x") String libelle);

    @Query("select s from SupportMarchandEnchage s where s.id not in (1,2,4,5,6) and s.id<= 60")
    List<SupportMarchandEnchage> listWithout();

    public SupportMarchandEnchage findSupportMarchandEnchageByLibelle(String libelle);

    @Query("select s.libelle from SupportMarchandEnchage s where  s.id =:y ")
    public String libelleSMEMByTeAndSMDE(@Param("y") Long idSM);

    @Query("select s from SupportMarchandEnchage s where s.id = 4 or s.id = 10 or s.id=15 ")
    public List<SupportMarchandEnchage> listCircuitBan();

    @Query("select s from SupportMarchandEnchage s where  s.libelle = :x")
    public SupportMarchandEnchage sourceMEV(@Param("x" ) String source);

    @Query("select s from SupportMarchandEnchage s where s.id =4 or s.id=10 or s.id=15")
    public List<SupportMarchandEnchage> listCircuitBanOpi();


    @Query("select s from SupportMarchandEnchage s where s.id =11 or s.id=12 or s.id=13 or s.id=14 ")
    public List<SupportMarchandEnchage> listCircuitBCr();

    @Query("select s from SupportMarchandEnchage s where s.id =16 or s.id=17 or s.id=18 or s.id=19 or s.id=20")
    public List<SupportMarchandEnchage> listCircuitBCnr();

    @Query("select s from SupportMarchandEnchage s where  s.id =8")
    public List<SupportMarchandEnchage> circuitMPRgBAn();

    @Query("select s from SupportMarchandEnchage s where s.id=6 or s.id=7 or s.id = 57")
    public List<SupportMarchandEnchage> listCircuitBLGCp();

    @Query("select s from SupportMarchandEnchage s where  s.id=21")
    public List<SupportMarchandEnchage> listCircuitBLGCsc();

    @Query("select s from SupportMarchandEnchage s where s.id = 22 or s.id = 29 or s.id = 36 or s.id = 46")
    public List<SupportMarchandEnchage> listCircuitSMCIPN();

    @Query("select s from SupportMarchandEnchage s where s.id=23 or s.id = 24")
    public List<SupportMarchandEnchage> listCircuitTSMCIPNBudget();

    @Query("select s from SupportMarchandEnchage s where s.id=30 or s.id = 31")
    public List<SupportMarchandEnchage> listCircuitTSMCIPNBudgetAnticipe();

    @Query("select s from SupportMarchandEnchage s where s.id=37 or s.id = 38 or s.id = 39")
    public List<SupportMarchandEnchage> listCircuitTSMCIPNServiceInterim();

    @Query("select s from SupportMarchandEnchage s where s.id=47 or s.id = 48 or s.id = 49")
    public List<SupportMarchandEnchage> listCircuitTSMCIPNAutre();

    @Query("select s from SupportMarchandEnchage s where s.id=3 or s.id=5 or s.id = 21 or s.id = 58")
    public List<SupportMarchandEnchage> listCircuitOpi();

    // Source MABAn se mute en BAn ||  id = idBAn
    @Query("select s from SupportMarchandEnchage s where  s.id =2")
    public List<SupportMarchandEnchage> sourceMABAn();

    // Source MABAn
    @Query("select s from SupportMarchandEnchage s where  s.id =10 or s.id=4 or s.id=15")
    public List<SupportMarchandEnchage> sourceBAn();

    @Query("select s from SupportMarchandEnchage s where  s.id =3")
    public List<SupportMarchandEnchage> sourceBAnOpi();

    @Query("select s from SupportMarchandEnchage s where  s.id =59")
    public List<SupportMarchandEnchage> sourceBLGCp();

    @Query("select s from SupportMarchandEnchage s where  s.id =60")
    public List<SupportMarchandEnchage> sourceBLGCsc();

    @Query("select s from SupportMarchandEnchage s where  s.id = 17 or s.id = 57")
    public List<SupportMarchandEnchage> listSmFranchise();

    /*** Source Mutation
     *  Source
     */

    @Query("select s from SupportMarchandEnchage s where  s.id = 27")
    List<SupportMarchandEnchage> sourceBAi_SMCI_Budget();

    @Query("select s from SupportMarchandEnchage s where  s.id = 28")
    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Budget();

    @Query("select s from SupportMarchandEnchage s where  s.id = 34")
    List<SupportMarchandEnchage> sourceBAi_SMCI_Budget_Anticipe();

    @Query("select s from SupportMarchandEnchage s where  s.id = 35")
    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Budget_Anticipe();

    @Query("select s from SupportMarchandEnchage s where  s.id = 43")
    List<SupportMarchandEnchage> sourceBAi_SMCI_Service_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id = 44")
    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Service_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id = 45")
    List<SupportMarchandEnchage> sourceBAi_SMCI_PN_Service_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id = 53")
    List<SupportMarchandEnchage> sourceBAi_SMCI_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id = 54")
    List<SupportMarchandEnchage> sourceBAi_SMC_PN_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id = 55")
    List<SupportMarchandEnchage> sourceBAi_SMCI_PN_Autres();


    /***** LIST MUTATION
     *  MUTATION LISTE
     */

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_Budget();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMC_PN_Budget();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_Budget_Anticipe();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMC_PN_Budget_Anticipe();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_Service_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMC_PN_Service_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_PN_Service_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMC_PN_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_PN_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_Autres();

    /**** BAI OPI
     *
     *  BAI OPI
     */

    @Query("select s from SupportMarchandEnchage s where  s.id = 56")
    List<SupportMarchandEnchage> sourceBAiOPi();

    @Query("select s from SupportMarchandEnchage s where  s.id = 59 or s.id=60 or s.id = 21")
    List<SupportMarchandEnchage> listBCiBAiOPI();

    @Query("select s from SupportMarchandEnchage s where  s.id = 6 or s.id=7 or s.id=57")
    List<SupportMarchandEnchage> listBLGCp();

    @Query("select s from SupportMarchandEnchage s where  s.id = 21")
    List<SupportMarchandEnchage> sourceMutationBLGCsc();

    @Query("select s from SupportMarchandEnchage s where  s.id = 5 or s.id = 21 or s.id=58 or s.id=3")
    List<SupportMarchandEnchage> listOPI();

    @Query("select s from SupportMarchandEnchage s where  s.id=74 or s.id=2 or s.id=67")
    List<SupportMarchandEnchage> listMPRg_BPSDEVpPN_BAn();

    @Query("select s from SupportMarchandEnchage s where s.id=74" )
    List<SupportMarchandEnchage> sourceMutationBPSDEVpPN_BPSDEVpIPN();

    @Query("select s from SupportMarchandEnchage s where s.id=40" )
    List<SupportMarchandEnchage> sourceMutationSMCI_Services_Interim();


    ///=================================PARFAIT==========================

    @Query("select s from SupportMarchandEnchage s where  s.id=76 or s.id=79")
    List<SupportMarchandEnchage> listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN();

    @Query("select s from SupportMarchandEnchage s where s.id=78" )
    List<SupportMarchandEnchage> sourceMutationBPSDEpIPN_BPSDEpPN();

    @Query("select s from SupportMarchandEnchage s where s.id=81" )
    List<SupportMarchandEnchage> sourceMutationBPSDVpIPN_BPSDVpPN();

    @Query("select s from SupportMarchandEnchage s where  s.id=73 or s.id=2")
    List<SupportMarchandEnchage> listMPRg_BPSDEVpI_BAn();

    @Query("select s from SupportMarchandEnchage s where s.id=75 or s.id=78" )
    List<SupportMarchandEnchage> sourceMutationBPSDEVpI_BPSDEVpIPN();

    @Query("select s from SupportMarchandEnchage s where s.id=77" )
    List<SupportMarchandEnchage> sourceMutationBPSDEpIPN_BPSDEpI();

    @Query("select s from SupportMarchandEnchage s where s.id=80" )
    List<SupportMarchandEnchage> sourceMutationBPSDVpIPN_BPSDVpI();

    @Query("select s from SupportMarchandEnchage s where  s.id=84 or s.id=2")
    List<SupportMarchandEnchage> listMPRg_BPSDEVCPN_BAn();

    @Query("select s from SupportMarchandEnchage s where s.id=69" )
    List<SupportMarchandEnchage> sourceMutationBPSDEVCPN_BPSDVpI();

    @Query("select s from SupportMarchandEnchage s where  s.id=84 or s.id=81")
    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN();

    @Query("select s from SupportMarchandEnchage s where s.id=67" )
    List<SupportMarchandEnchage> sourceMutationBPSDVCPN();

    @Query("select s from SupportMarchandEnchage s where s.id=67" )
    List<SupportMarchandEnchage> sourceMutationBP();

    @Query("select s from SupportMarchandEnchage s where s.id=68" )
    List<SupportMarchandEnchage> sourceMutationBPSDVCIPN_BPSDVCPN();

    @Query("select s from SupportMarchandEnchage s where  s.id=84 or s.id=2")
    List<SupportMarchandEnchage> listMPRg_BPSDEVCI_BAn();

    @Query("select s from SupportMarchandEnchage s where s.id=69" )
    List<SupportMarchandEnchage> sourceMutationBPSDEVCI_BPSDEVCIPN();

    @Query("select s from SupportMarchandEnchage s where s.id=65" )
    List<SupportMarchandEnchage> sourceMutationBPSDECIPN_BPSDECI();

    @Query("select s from SupportMarchandEnchage s where s.id=66" )
    List<SupportMarchandEnchage> sourceMutationBPSDVCIPN_BPSDVCIPN();

    @Query("select s from SupportMarchandEnchage s where s.id<0" )
    List<SupportMarchandEnchage> vide();

    /****
     *
     */

    @Query("select s from SupportMarchandEnchage s where  s.id=64 or s.id=66")
    List<SupportMarchandEnchage> listBPSDECIPN_BPSDECI();


    @Query("select s from SupportMarchandEnchage s where  s.id=65")
    List<SupportMarchandEnchage> listBPSDVCIPN_BPSDVCI();

    @Query("select s from SupportMarchandEnchage s where  s.id=67")
    List<SupportMarchandEnchage> listBPSDVCIPN_BPSDVCPN();


    @Query("select s from SupportMarchandEnchage s where  s.id=83")
    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDEVCI();

    @Query("select s from SupportMarchandEnchage s where  s.id=82")
    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDEVCPN();

    
    @Query("select s from SupportMarchandEnchage s where  s.id=81 or s.id=84")
    List<SupportMarchandEnchage> listBPSDEVCIPN_BPSDVCIPN();

    @Query("select s from SupportMarchandEnchage s where  s.id=84 ")
    List<SupportMarchandEnchage> listBPSDVCIPN_BPSDECIPN();


    @Query("select s from SupportMarchandEnchage s where  s.id=82")
    List<SupportMarchandEnchage> listMPRg_BPSDEVCPN();

    @Query("select s from SupportMarchandEnchage s where  s.id=83")
    List<SupportMarchandEnchage> listMPRg_BPSDEVCI();

    @Query("select s from SupportMarchandEnchage s where  s.id=68")
    List<SupportMarchandEnchage> listBPSDEVCPN_BPSDEVCIPN();

    @Query("select s from SupportMarchandEnchage s where  s.id=68")
    List<SupportMarchandEnchage> listBPSDEVCI_BPSDEVCIPN();


    // =============================================================NEW===============================================
    /***
     *
     * NEW BCR_JOUR
     */

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21 or s.id=8")
    List<SupportMarchandEnchage> listCircuitBCr_jour();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id= 60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBCr_limite_11_2();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBCriRMSBCr22_4();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBCr_illimite_22_4();


    /***
     *
     * NEW BCnR
     */

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBCnr5_6_PRK_6_5();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBCnrPRK_6_5_8();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCnrIMM_PRK8_PRK_9();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCnrPREIMM11_2_PRK_22_4();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCnrPRE_9_22_4();

    /*** SM PN BUDGET
     *
     */

    @Query("select s from SupportMarchandEnchage s where  s.id=25")
    List<SupportMarchandEnchage> sourceSMCI_Budget();


    @Query("select s from SupportMarchandEnchage s where  s.id=26")
    List<SupportMarchandEnchage> sourceSMC_PN_Budget();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> sourceBCi_SMCPN_Budget();

    @Query("select s from SupportMarchandEnchage s where  s.id=32")
    List<SupportMarchandEnchage> sourceSMCI_Budget_Anticipe();

    @Query("select s from SupportMarchandEnchage s where  s.id=33")
    List<SupportMarchandEnchage> sourceSMC_PN_Budget_Anticipe();
    

    @Query("select s from SupportMarchandEnchage s where  s.id=41")
    List<SupportMarchandEnchage> sourceSMC_PN_Services_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id=42")
    List<SupportMarchandEnchage> sourceSMCIPN_Services_Interim();

    @Query("select s from SupportMarchandEnchage s where  s.id=42")
    List<SupportMarchandEnchage> sourceBAi_SMCI_Services_Interim();



    @Query("select s from SupportMarchandEnchage s where  s.id=50")
    List<SupportMarchandEnchage> sourceSMCI_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id=51")
    List<SupportMarchandEnchage> sourceSMC_PN_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listBCi_SMCI_Services_Interim();


    @Query("select s from SupportMarchandEnchage s where  s.id=52")
    List<SupportMarchandEnchage> sourceSMCIPN_Autres();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBC();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage> listCircuitBCi_BLGCp();

    @Query("select s from SupportMarchandEnchage s where  s.id=63")
    List<SupportMarchandEnchage>  sourceBPSDE();

    @Query("select s from SupportMarchandEnchage s where  s.id=63")
    List<SupportMarchandEnchage>  sourceBPSDV();


    @Query("select s from SupportMarchandEnchage s where  s.id=1")
    List<SupportMarchandEnchage>  sourceBPSDEV();

    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage>  sourceBCi_SMCPN_Budget_Anticipe();


    @Query("select s from SupportMarchandEnchage s where  s.id=59 or s.id=60 or s.id=21")
    List<SupportMarchandEnchage>  listCircuitMPRgBAn_OPI_non_echus();

    @Query("select s from SupportMarchandEnchage s where  s.id>=68 or s.id=4 or s.id=6 ")
    List<SupportMarchandEnchage>  listCircuitDecaissment();

    @Query("select s from SupportMarchandEnchage s where s.id=5 or s.id=6 or s.id = 1 or s.id = 2  or s.id = 73 or s.id = 74 or s.id = 75 or s.id  = 78 or s.id = 77 or s.id = 80 ")
    List<SupportMarchandEnchage> listFondsPropreOT();

    @Query("select s from SupportMarchandEnchage s where s.id=5 or s.id=6 or s.id = 1 or s.id = 2 or s.id = 82 or s.id = 68 or s.id = 84 or s.id  = 81 or s.id = 66 or s.id = 67 ")
    List<SupportMarchandEnchage> listFondsCommunOT();

    @Query("select s from SupportMarchandEnchage s where s.id=5 or s.id=6 or  s.id = 1 or s.id = 2  or s.id = 72 or s.id = 74 or s.id = 75 or s.id  = 78 or s.id = 76 or s.id = 79 ")
    List<SupportMarchandEnchage> listFondsPropreAutres();

    @Query("select s from SupportMarchandEnchage s where s.id=5 or s.id=6 or s.id = 1 or s.id = 2 or s.id = 83 or s.id = 68 or s.id = 84 or s.id  = 81 or s.id = 64 or s.id = 65 ")
    List<SupportMarchandEnchage> listFondsCommunAutres();


    /******* Decaissement By ID
     *  OT / Autres
     *  Valeur Propre / Valeur Commun
     */

        /*/******** VALEUR PROPRE *************/
    // MPRG BAn OPI échus / MPRG OPI
    @Query("select s from SupportMarchandEnchage s where  s.id = 1 or s.id= 2 or s.id=73")
    List<SupportMarchandEnchage> listCircuitMPRG_OT();

    @Query("select s from SupportMarchandEnchage s where  s.id = 1 or s.id= 2 or s.id=72")
    List<SupportMarchandEnchage> listCircuitMPRG_AUTRES();

    // BPSDEVpPN / BPSDEVpI
    @Query("select s from SupportMarchandEnchage s where  s.id = 74 ")
    List<SupportMarchandEnchage> listCircuitBPSDEVpPN_OT();

    @Query("select s from SupportMarchandEnchage s where s.id=74")
    List<SupportMarchandEnchage> listCircuitBPSDEVpI_AUTRES();

    // BPSDEVpIPN / BPSDEVpIPN
    @Query("select s from SupportMarchandEnchage s where  s.id = 75 or s.id=78")
    List<SupportMarchandEnchage> listCircuitBPSDEVpIPN_OT();

    @Query("select s from SupportMarchandEnchage s where s.id=75 or s.id=78")
    List<SupportMarchandEnchage> listCircuitBPSDEVpIPN_AUTRES();

    // BPSDEpIPN
    @Query("select s from SupportMarchandEnchage s where s.id=77")
    List<SupportMarchandEnchage> listCircuitBPSDEpIPN_OT();

    @Query("select s from SupportMarchandEnchage s where s.id=76")
    List<SupportMarchandEnchage> listCircuitBPSDVpIPN_OT();

    // BPSDVpIPN
    @Query("select s from SupportMarchandEnchage s where s.id=80")
    List<SupportMarchandEnchage> listCircuitBPSDVpIPN_AUTRE();

    @Query("select s from SupportMarchandEnchage s where s.id=79")
    List<SupportMarchandEnchage> listCircuitBPSDVpIPN_AUTRES();


    /*/******** VALEUR COMMUN *************/
    // MPRG BAn OPI échus / MPRG OPI
    @Query("select s from SupportMarchandEnchage s where  s.id = 1 or s.id= 2 or s.id=82")
    List<SupportMarchandEnchage> listCircuit_C_MPRG_OT();

    @Query("select s from SupportMarchandEnchage s where  s.id = 1 or s.id= 2 or s.id=83")
    List<SupportMarchandEnchage> listCircuit_C_MPRG_AUTRES();

    // BPSDEVCPN  = _82 / BPSDEVCI = 83
    @Query("select s from SupportMarchandEnchage s where  s.id = 68")
    List<SupportMarchandEnchage> listCircuit_C_BPSDEVCPN_OT();

    @Query("select s from SupportMarchandEnchage s where  s.id =68")
    List<SupportMarchandEnchage> listCircuit_C_BPSDEVCI_AUTRES();

    // BPSDEVCIPN  = _68 / BPSDEVCIPN = 68
    @Query("select s from SupportMarchandEnchage s where  s.id = 81 or s.id= 84")
    List<SupportMarchandEnchage> listCircuit_C_BPSDEVCIPN_OT();

    @Query("select s from SupportMarchandEnchage s where  s.id =81 or s.id=84")
    List<SupportMarchandEnchage> listCircuit_C_BPSDEVCIPN_AUTRES();

    // BPSDECIPN  = 84
    @Query("select s from SupportMarchandEnchage s where  s.id = 66")
    List<SupportMarchandEnchage> listCircuit_C_BPSDECIPN_OT();

    @Query("select s from SupportMarchandEnchage s where s.id=64")
    List<SupportMarchandEnchage> listCircuit_C_BPSDVCIPN_OT();

    //  BPSDVCIPN = 81
    @Query("select s from SupportMarchandEnchage s where  s.id = 67")
    List<SupportMarchandEnchage> listCircuit_C_BPSDECIPN_AUTRES();

    @Query("select s from SupportMarchandEnchage s where s.id=65")
    List<SupportMarchandEnchage> listCircuit_C_BPSDVCIPN_AUTRES();


    /* Décaissment path : 08/11/2022

     */
    @Query("select s from SupportMarchandEnchage s where s.id=76")
    SupportMarchandEnchage  decaissementSMCircuitBPSDEpIPN_EpI();

    @Query("select s from SupportMarchandEnchage s where s.id=77")
    SupportMarchandEnchage  decaissementSMCircuitBPSDEpIPN_EpPN();

    @Query("select s from SupportMarchandEnchage s where s.id=79")
    SupportMarchandEnchage  decaissementSMCircuitBPSDVpIPN_VpI();

    @Query("select s from SupportMarchandEnchage s where s.id=80")
    SupportMarchandEnchage  decaissementSMCircuitBPSDVpIPN_VpPN();

    @Query("select s from SupportMarchandEnchage s where s.id=64")
    SupportMarchandEnchage  decaissementSMCircuitBPSDECIPN_ECI();

    @Query("select s from SupportMarchandEnchage s where s.id=66")
    SupportMarchandEnchage  decaissementSMCircuitBPSDECIPN_ECPN();

    @Query("select s from SupportMarchandEnchage s where s.id=65")
    SupportMarchandEnchage  decaissementSMCircuitBPSDVCIPN_VCI();

    @Query("select s from SupportMarchandEnchage s where s.id=67")
    SupportMarchandEnchage  decaissementSMCircuitBPSDVCIPN_VCPN();

    /****** NEW MPRG No Mutation **************/

    @Query("select s from SupportMarchandEnchage s where s.id <0")
    List<SupportMarchandEnchage>  listMPRGBAn_No_Mutation();

    @Query("select s from SupportMarchandEnchage s where s.id <0")
    List<SupportMarchandEnchage>  listMPRGOPI_No_Mutation();

    @Query("select s from SupportMarchandEnchage s where s.id =8")
    List<SupportMarchandEnchage>  listMPRGBAn_OPI_non_echus();


    /****** NEW  No Mutation **************/

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSEDCI();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSDVCI();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSDVCPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_EV_p();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_E_p();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_V_p();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_EV_pI();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_EV_pPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_E_pI();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_E_pPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_V_pIPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_V_pI();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_V_pPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_V_CIPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_EV_CPN();

    @Query("select s from SupportMarchandEnchage s where s.id < 0")
    List<SupportMarchandEnchage>  listNo_Mutation_BPSD_EV_CI();

    // =======================NEW 11/10/2022 ==================
    @Query("select s from SupportMarchandEnchage s where s.id =63")
    List<SupportMarchandEnchage>  listBPSDE();

    @Query("select s from SupportMarchandEnchage s where s.id =61 or s.id=62")
    List<SupportMarchandEnchage>  listBPSDE_Mutation();

    @Query("select s from SupportMarchandEnchage s where s.id =70 or s.id=71")
    List<SupportMarchandEnchage>  listBPSDEVp_Mutation();

    @Query("select s from SupportMarchandEnchage s where s.id =5 or s.id=6 ")
    List<SupportMarchandEnchage> listMPRg();

    @Query("select s from SupportMarchandEnchage s where  s.id=63")
    List<SupportMarchandEnchage> sourceEqualBPSDEV();

    @Query("select s from SupportMarchandEnchage s where  s.id=1 or s.id=85")
    List<SupportMarchandEnchage> listMaBAnAndMaBAnSpecial();

    @Query("select s from SupportMarchandEnchage s where  s.id=1")
    List<SupportMarchandEnchage> listMaBAn();

    @Query("select s from SupportMarchandEnchage s where  s.id=2")
    List<SupportMarchandEnchage> listBAn();

}
