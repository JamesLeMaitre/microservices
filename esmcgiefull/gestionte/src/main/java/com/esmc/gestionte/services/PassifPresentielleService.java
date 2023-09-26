package com.esmc.gestionte.services;

import com.esmc.gestionte.entities.*;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.feign.KsuRestClient;
import com.esmc.gestionte.feign.PayementRestClient;
import com.esmc.gestionte.inputs.SelectPassifInput;
import com.esmc.gestionte.repositories.*;
import com.esmc.gestionte.serviceinterface.PassifPresentielleInterface;
import com.esmc.input.KsuCheckInput;
import com.esmc.input.PassifCheckInput;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import com.esmc.models.Settings;
import com.google.zxing.WriterException;
import constants.AccountConstant;
import constants.SupportMarchandConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.Messagerie;
import utiles.UseFullFunctions;

import java.io.IOException;
import java.util.List;
@Service
public class PassifPresentielleService implements PassifPresentielleInterface {
    @Autowired
    private PassifPresentielleRepository repository;
    @Autowired
    private PassifPresentielleTreatRepository passifPresentielleTreatRepository;

    @Autowired
    private PassifPresentielleHistoryRepository passifPresentielleHistoryRepository;

    @Autowired
    private PassifPresentielleUseRepository passifPresentielleUseRepository;

    @Autowired
    private PassifPresentielleSelectionRepository passifPresentielleSelectionRepository;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private DetailSMEnchangeServiceImpl detailSMEnchangeService;

    @Autowired
    private SupportMarchandEnchageRepository supportMarchandEnchageRepository;

    @Autowired
    private PayementRestClient payementRestClient;

    private  Messagerie messagerie = new Messagerie();


    private UseFullFunctions useFullFunctions = new UseFullFunctions();


    @Override
    public List<PassifPresentielle> getAll() {
        return repository.findAll();
    }

    @Override
    public PassifPresentielle getById(Long id) {
        return repository.getPassifPresentielleById(id);
    }


    @Override
    public PassifPresentielle create(PassifPresentielle data) {
        PassifPresentielle element = new PassifPresentielle();

        element.setStatus(true);
        if(data.getNoRef() != null){
            element.setNoRef(data.getNoRef());
        }

        if(data.getIdNumSerie() != null){
            element.setIdNumSerie(data.getIdNumSerie());
        }

        if(data.getNumPrinte() != null){
            element.setNumPrinte(data.getNumPrinte());
        }

        if(data.getIdChiffrementQrcode() != null){
            element.setIdChiffrementQrcode(data.getIdChiffrementQrcode());
        }

        if(data.getNumContratAchat() != null){
            element.setNumContratAchat(data.getNumContratAchat());
        }
        if(data.getNumBonLivraison() != null){
            element.setNumBonLivraison(data.getNumBonLivraison());
        }

        if(data.getNumBonCommande() != null){
            element.setNumBonCommande(data.getNumBonCommande());
        }

        if(data.getNomVendeur() != null){
            element.setNomVendeur(data.getNomVendeur());
        }

        if(data.getPrenomsvendeur() != null){
            element.setPrenomsvendeur(data.getPrenomsvendeur());
        }
        if(data.getValeurInitialXOF() != null){
            element.setValeurInitialXOF(data.getValeurInitialXOF());
        }

        if(data.getValReinitBCi() != null){
            element.setValReinitBCi(data.getValReinitBCi());
        }

        if(data.getTypeContrat() != null){
            element.setTypeContrat(data.getTypeContrat());
        }

        return repository.save(element);
    }

    @Override
    public PassifPresentielle update(Long id, PassifPresentielle data) {
        PassifPresentielle element = repository.getPassifPresentielleById(id);

        if(data.getNoRef() != null){
            element.setNoRef(data.getNoRef());
        }

        if(data.getIdNumSerie() != null){
            element.setIdNumSerie(data.getIdNumSerie());
        }

        if(data.getNumPrinte() != null){
            element.setNumPrinte(data.getNumPrinte());
        }

        if(data.getIdChiffrementQrcode() != null){
            element.setIdChiffrementQrcode(data.getIdChiffrementQrcode());
        }

        if(data.getNumContratAchat() != null){
            element.setNumContratAchat(data.getNumContratAchat());
        }

        if(data.getNumBonLivraison() != null){
            element.setNumBonLivraison(data.getNumBonLivraison());
        }

        if(data.getNumBonCommande() != null){
            element.setNumBonCommande(data.getNumBonCommande());
        }

        if(data.getNomVendeur() != null){
            element.setNomVendeur(data.getNomVendeur());
        }

        if(data.getPrenomsvendeur() != null){
            element.setPrenomsvendeur(data.getPrenomsvendeur());
        }
        if(data.getValeurInitialXOF() != null){
            element.setValeurInitialXOF(data.getValeurInitialXOF());
        }

        if(data.getValReinitBCi() != null){
            element.setValReinitBCi(data.getValReinitBCi());
        }

        if(data.getTypeContrat() != null){
            element.setTypeContrat(data.getTypeContrat());
        }

        return repository.save(element);
    }

    @Override
    public PassifPresentielle enable(Long id) {
        PassifPresentielle element = repository.getPassifPresentielleById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public PassifPresentielle disable(Long id) {
        PassifPresentielle element = repository.getPassifPresentielleById(id);
        element.setStatus(false);
        return repository.save(element);
    }

    @Override
    public int getCountAll() {
        return (int) this.repository.count();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PassifPresentielle getPassifPresentiellebyIdEmpreinte(String id)  {
        return  repository.findPassifPresentiellebyIdEmpreinte(id);
    }
    @Override
    public PassifPresentielle getPassifPresentiellebyNumeroRef(String numRef)  {
        return  repository.findPassifPresentiellebyNumRef(numRef);
    }

    @Override
    public PassifPresentielle getPassifPrentielleByCodeQrOrNumRef(String code) {
        return repository.findPassifPresentiellByQrCodeOrNUmRef(code);
    }

    @Override
    public String checkPassifPrentielleByCodeQrOrNumRef(String numRef, String numero) {
        PassifPresentielle passifPresentielle = repository.findPassifPresentiellByQrCodeOrNUmRef(numRef);
        String lastName = passifPresentielle.getPrenomsvendeur();
        String firstName = passifPresentielle.getNomVendeur();
        KsuCheckInput ksuCheckInput = new KsuCheckInput();
        ksuCheckInput.setNumero(numero);
        ksuCheckInput.setLastName(lastName);
        ksuCheckInput.setFirstName(firstName);
        ksuCheckInput.setRaisonSocial(firstName);
        int min=100000,max=999999;
        int numericCode = (int)(Math.random()*(max-min+1)+min);

        String activationCode =""+numericCode;
        Formatter<Ksu> ksu = ksuRestClient.getBySpecificInfo(ksuCheckInput);
        Ksu k = ksu.getData();
        Long idKsu = k.getId();
        PassifPresentielleTreat passifPresentielleTreat = passifPresentielleTreatRepository.getPassifPresentielleTreatByKsuId(idKsu);
        if(passifPresentielleTreat == null){
            passifPresentielleTreat = new PassifPresentielleTreat();
        }else{
            if(passifPresentielleTreat.getIdPassifPresentielle() == passifPresentielle.getId()){
                activationCode=passifPresentielleTreat.getNewCode();
            }
        }
        passifPresentielleTreat.setKsuId(idKsu);
        passifPresentielleTreat.setEnableUseStatus(true);
        passifPresentielleTreat.setDateActivated(System.currentTimeMillis());

        passifPresentielleTreat.setNewCode(activationCode);
        passifPresentielleTreat.setIdPassifPresentielle(passifPresentielle.getId());
        passifPresentielleTreatRepository.save(passifPresentielleTreat);
        repository.save(passifPresentielle);

        return "true";
    }

    @Override
    public String checkPassifPrentielleTreatByKsu(Long idKsu) {
        PassifPresentielleTreat passifPresentielleTreat = passifPresentielleTreatRepository.getPassifPresentielleTreatByKsuId(idKsu);
        if(passifPresentielleTreat == null){
            return null;
        }
        PassifPresentielle passifPresentielle = repository.findById(passifPresentielleTreat.getIdPassifPresentielle()).orElse(null);
        if(passifPresentielle.getStatus() == false){
            return null;
        }
        return passifPresentielleTreat.getNewCode();
    }

    @Override
    public void disablePassifPresentielle(Long idKsu) {

    }

    @Override
    public String checkView(Long idBon) {
        return repository.returnNumBonComm(idBon);
    }


    @Override
    public PassifPresentielle checkValidePassifGenerateActivateCode(Long idKsu, String code) {
        PassifPresentielleTreat passifPresentielleTreat = passifPresentielleTreatRepository.findPassifPresentiellTreatByKsuAndCode(idKsu,code);
        if(passifPresentielleTreat == null){
            return null;
        }
        System.out.println("element found");
        System.out.println(System.currentTimeMillis());
        Long currentTimeStamp = System.currentTimeMillis();
        Long wainTime = 60*5000l;//3 minutes
       /* if(passifPresentielleTreat.getDateActivated() + wainTime < currentTimeStamp) {
            return null;
        }*/
        System.out.println("time fit");
        PassifPresentielle passifPresentielle = repository.getPassifPresentielleById(passifPresentielleTreat.getIdPassifPresentielle());

        return passifPresentielle;
    }

    @Override
    public PassifPresentielle getPassifPresentielleByvalReinitBCi(Double valBciReinit) {
        return repository.getPassifPresentielleByvalReinitBCi(valBciReinit);

    }

    @Override
    public PassifPresentielle getPassifPresentielleByNomAndPrenom(String nom, String prenom) {
        return repository.getPassifPresentielleByNomAndPrenom(nom, prenom);

    }

    @Override
    public PassifPresentielleHistory actionRequest(Long id, int stage,String numero) throws IOException, WriterException, Exceptions {

        // Valeur Felm
        Formatter<Settings> stFelm = payementRestClient.getSettingUsedByTe("felmBan");
        Settings settingsFelm = stFelm.getData();
        String valeurApproFelm = settingsFelm.getValue();

        // Valeur Flm
        Formatter<Settings> stFlb = payementRestClient.getSettingUsedByTe("flbBan");
        Settings settingFlb = stFlb.getData();
        String valeurApproFlb = settingFlb.getValue();

        // Valeur Fil
        Formatter<Settings> stFil = payementRestClient.getSettingUsedByTe("filBan");
        Settings settingFil = stFil.getData();
        String valeurApproFil = settingFil.getValue();

        PassifPresentielle passifPresentielle = repository.findById(id).get();
        String description = "";
        int min=100000,max=999999;
        int numericCode = (int)(Math.random()*(max-min+1)+min);


        String activationCode ="";
        Double montantReleve =0.0;
        switch (stage){
            case 1:
                description = "Achat de ksu";
                montantReleve = 70000.0;
                activationCode ="AK-"+numericCode;
                break;
            case 2:
                description = "Achat de carte";
                montantReleve = 10000.0;
                activationCode ="AC-"+numericCode;
                break;
            case 3:
                description = "Achat de Franchise FELM";
                montantReleve = Double.parseDouble(valeurApproFelm);
                activationCode ="AF-"+numericCode;
                break;
            case 4:
                description = "Achat de Franchise FLB";
                montantReleve = Double.parseDouble(valeurApproFlb);
                activationCode ="AF-"+numericCode;
                break;
            case 5:
                description = "Achat de Franchise FIL";
                montantReleve = Double.parseDouble(valeurApproFil);
                activationCode ="AF-"+numericCode;
                break;
            default:
                montantReleve = 0.0;
                description = "Unknow Action";
                break;
        }
        Double currentAmount = passifPresentielle.getValeurInitialXOF();
        if(currentAmount < montantReleve){
            return null;
        }
        Double value = currentAmount - montantReleve;
        passifPresentielle.setValeurInitialXOF(value);
        if(value == 0){
            passifPresentielle.setStatus(false);
        }
        repository.save(passifPresentielle);
        PassifPresentielleHistory passifPresentielleHistory = new PassifPresentielleHistory();
        passifPresentielleHistory.setIdPassifPresentielle(passifPresentielle.getId());
        passifPresentielleHistory.setMontant(montantReleve);
        passifPresentielleHistory.setNumero(numero);
        passifPresentielleHistory.setIdentifiant(activationCode);
        passifPresentielleHistory.setDescription(description);
        passifPresentielleHistoryRepository.save(passifPresentielleHistory);
        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(SupportMarchandConstant.supportMarchandMABAn_FZ).orElse(null);
        Double somTotale = detailSMEnchangeService.sommeTotalSm(sm.getId(), admin);
        if ((somTotale > 0 && montantReleve <= somTotale)) {

            DetailSMEnchange dst = detailSMEnchangeService.mutationFondDisponible(sm.getId() , admin, montantReleve);
            if(dst != null) {
                DetailSMEnchange smp = detailSMEnchangeService.create(admin, montantReleve, SupportMarchandConstant.supportMarchandBPSD_V_p, null);
                DetailSMEnchange detailSMEnchange = detailSMEnchangeService.createFondSortie(admin, dst.getId(), montantReleve);
            }
        }
        String message = "Votre payement par support marchand de "+montantReleve+" Fcfa a été effectuer, code de reference : "+ activationCode;
        messagerie.sendSms(passifPresentielleHistory.getNumero(), message);
        return passifPresentielleHistory;
    }

    @Override
    public String checkPassifPrentielleTreatByInfo(KsuCheckInput ksuCheckInput) {
        Formatter<Ksu> ksu = ksuRestClient.getBySpecificInfo(ksuCheckInput);
        if(ksu.getData() == null ){
            return null;
        }
        Long idKsu = ksu.getData().getId();
        //System.out.println(idKsu);
        PassifPresentielleTreat passifPresentielleTreat = passifPresentielleTreatRepository.getPassifPresentielleTreatByKsuId(idKsu);
        if(passifPresentielleTreat == null){
            return null;
        }
        //System.out.println(passifPresentielleTreat);
        PassifPresentielle passifPresentielle = repository.findById(passifPresentielleTreat.getIdPassifPresentielle()).get();
        if(passifPresentielle == null || passifPresentielle.getStatus() == false){
            return null;
        }
        return passifPresentielleTreat.getNewCode();
    }
    @Override
    public String checkPassifPrentielleUseByCodeQrOrNumRef(String codeQrOrNumRef, String numero) {
        PassifPresentielle passifPresentielle = repository.findPassifPresentiellByQrCodeOrNUmRef(codeQrOrNumRef);
        if (passifPresentielle == null) {
            return null;
        }
        String lastName = passifPresentielle.getPrenomsvendeur();
        String firstName = passifPresentielle.getNomVendeur();
        PassifPresentielleUse passifPresentielleUse = passifPresentielleUseRepository.findPassifPresentiellUseByInfo(numero,passifPresentielle.getNumContratAchat(),passifPresentielle.getNumBonCommande());
        if(passifPresentielleUse == null){
            passifPresentielleUse = new PassifPresentielleUse();
        }
        passifPresentielleUse.setFirstname(firstName);
        passifPresentielleUse.setLastname(lastName);
        passifPresentielleUse.setNumero(numero);
        passifPresentielleUse.setNumBonCommande(passifPresentielle.getNumBonCommande());
        passifPresentielleUse.setNumContratAchat(passifPresentielle.getNumContratAchat());
        passifPresentielleUse.setIdPassifPresentielle(passifPresentielle.getId());
        passifPresentielleUse.setStatus(true);
        passifPresentielleUseRepository.save(passifPresentielleUse);
        repository.save(passifPresentielle);
        return "true";
    }

    @Override
    public PassifPresentielle checkExistPassifPresentielleByInfo(PassifCheckInput passifCheckInput) {
        PassifPresentielleUse passifPresentielleUse = passifPresentielleUseRepository.findPassifPresentiellUseByInfo(passifCheckInput.getNumero(),passifCheckInput.getNumContratAchat(),passifCheckInput.getNumBonCommande());
        if(passifPresentielleUse == null || passifPresentielleUse.getStatus() == false){
            return null;
        }
        passifPresentielleUse.setStatus(false);
        passifPresentielleUseRepository.save(passifPresentielleUse);
        PassifPresentielle passifPresentielle = repository.findById(passifPresentielleUse.getIdPassifPresentielle()).orElse(null);
        return passifPresentielle;
    }

    @Override
    public PassifPresentielle checkExistPassifPresentielleByInfoOk(PassifCheckInput passifCheckInput) {
        PassifPresentielleUse passifPresentielleUses = passifPresentielleUseRepository.findPassifPresentiellUseByInfo(passifCheckInput.getNumero(),passifCheckInput.getNumContratAchat(),passifCheckInput.getNumBonCommande());
        if(passifPresentielleUses == null){
            return null;
        }
        passifPresentielleUseRepository.save(passifPresentielleUses);
        PassifPresentielle passifPresentielles = repository.findById(passifPresentielleUses.getIdPassifPresentielle()).get();
        return passifPresentielles;
    }

    @Override
    public PassifPresentielle selectPassifPresentielle(SelectPassifInput selectPassifInput) {
        /**
         * Check is passif exist
         * Check there is an instance of it selecction present if not , create it
         */
        PassifPresentielle passifPresentielle = repository.findPassifPresentiellByQrCodeOrNUmRef(selectPassifInput.getQr());
        if(passifPresentielle == null){
            return null;
        }
        System.out.println(passifPresentielle);

        PassifPresentielleSelection passifPresentielleSelection = passifPresentielleSelectionRepository.findPassifPresentiellSelectionidPassifPresentielle(passifPresentielle.getId());
        if(passifPresentielleSelection == null){
            passifPresentielleSelection = new PassifPresentielleSelection();
            passifPresentielleSelection.setIdPassifPresentielle(passifPresentielle.getId());
        }
        passifPresentielleSelection.setNewCode(selectPassifInput.getCode());
        passifPresentielleSelection.setDateActivated(System.currentTimeMillis());
        passifPresentielleSelectionRepository.save(passifPresentielleSelection);
        return passifPresentielle;
    }

    @Override
    public PassifPresentielle getSelectedPassifPresentielle(String code) {
        Long currentTimeStamp = System.currentTimeMillis();
        Long wainTime = 60*5000l;//3 minutes
        Long timeToBeLessThanActivateTime = currentTimeStamp-wainTime;
        PassifPresentielleSelection passifPresentielleSelection = passifPresentielleSelectionRepository.findPassifPresentiellSelectionidPassifPresentielleByCodeTime(code, timeToBeLessThanActivateTime);
        if(passifPresentielleSelection == null){
            return null;
        }
        PassifPresentielle passifPresentielle =  repository.findById(passifPresentielleSelection.getIdPassifPresentielle()).orElse(null);
        return passifPresentielle;
    }

}
