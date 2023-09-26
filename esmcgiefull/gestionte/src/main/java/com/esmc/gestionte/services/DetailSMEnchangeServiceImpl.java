package com.esmc.gestionte.services;

import com.esmc.gestionte.constant.DetailTotal;
import com.esmc.gestionte.entities.DetailSMEnchange;
import com.esmc.gestionte.entities.Ordre;
import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.entities.TerminalEchange;
import com.esmc.gestionte.entities.TypeOrdre;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.feign.*;
import com.esmc.gestionte.inputs.PayerVendeurKsu;
import com.esmc.gestionte.repositories.*;
import com.esmc.gestionte.serviceinterface.DetailSMEnchangeService;
import com.esmc.gestionte.serviceinterface.PassifPresentielleInterface;
import com.esmc.gestionte.serviceinterface.SupportMarchandEnchageService;
import com.esmc.gestionte.serviceinterface.TerminalEchangeService;
import com.esmc.models.*;
import com.esmc.models.Formatter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import constants.AccountConstant;
import constants.EtiquetteConstant;
import constants.SettingsConstant;
import constants.SupportMarchandConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utiles.Messagerie;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

@Service
public class DetailSMEnchangeServiceImpl extends Messagerie implements DetailSMEnchangeService {

    @Autowired
    private DetailSMEnchangeRepository detailSMEnchangeRepository;

    @Autowired
    private SupportMarchandEnchageRepository supportMarchandEnchageRepository;

    @Autowired
    private TerminalEchangeRepository terminalEchangeRepository;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;

    @Autowired
    private TerminalEchangeService terminalEchangeService;

    @Autowired
    public TerminalEchangeService terminalEchangeImpl;

    @Autowired
    private PayementRestClient payementRestClient;

    @Autowired
    private PassifPresentielleInterface passifPresentielleInterface;

    @Autowired
    private TypeOrdreRepository typeOrdreRepository;

    @Autowired
    private OrdreRepository ordreRepository;

    @Autowired
    private SupportMarchandEnchageService supportMarchandEnchageService;

    @Autowired
    private AvrRestClient avrRestClient;

    @Autowired
    private AchatKsuClientRest achatKsuClientRest;

    @Override
    public void changeSelectDetailSMEnchange(Long selectElementId) {
        DetailSMEnchange ds = detailSMEnchangeRepository.findById(selectElementId).orElse(null);
        ds.setStatus(false);
        detailSMEnchangeRepository.save(ds);
    }

    @Override
    public void changeStatuSelectDetailSMEnchange(Long selectElementId, Boolean status) {
        System.out.println("DSMG:" + status);
        DetailSMEnchange ds = detailSMEnchangeRepository.findById(selectElementId).orElse(null);
        if (status) {
            ds.setStatus(false);
            ds.setUsed(true);
        } else {
            ds.setStatus(true);
            ds.setUsed(false);
        }
        detailSMEnchangeRepository.save(ds);
    }

    /**
     * @param id
     * @return Fonction pour afficher la liste des support marcahnd d'echange par
     *         Terminal d'echange
     */
    @Override
    public List<DetailSMEnchange> listDetailSmeByTe(Long id) {

        DetailsAgr d = detailAgrRestClient.getDetailById(id);
        System.out.println("id : " + d.getId());

        TerminalEchange te = terminalEchangeImpl.teByDetailAgr(d.getId());

        System.out.println("id : " + te.getId());

        List<DetailSMEnchange> listDe = detailSMEnchangeRepository.listDeatailSme(id);

        System.out.println("List Detail Sm : " + listDe.size());

        return listDe;
    }

    @Override
    public List<DetailSMEnchange> getDeatilSMEnchange() {

        return detailSMEnchangeRepository.findAll();
    }

    // Fonction pour Supprimer les details support marchand d'echange
    @Override
    @Transactional
    public void deleteDetailSMEnchange(Long id) throws Exceptions {
        boolean exists = detailSMEnchangeRepository.existsById(id);
        if (!exists)
            throw new Exceptions(
                    Exceptions.alertGeneralException("DetailSMEnchange dont l id " + id + "n'existe pas "));

        detailSMEnchangeRepository.deleteById(id);
    }

    // Fonction pour Modifier un detail support marchand d'echange
    @Override
    @Transactional
    public void updateDetailSMEnchange(Long id, DetailSMEnchange detailSMEnchange) throws Exceptions {
        if (!isPresent(detailSMEnchange.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'existe pas"));
        DetailSMEnchange detailSMEnchange1 = getById(detailSMEnchange.getId());

        detailSMEnchange1.setCodeSM(detailSMEnchange.getCodeSM());
        detailSMEnchange1.setCoutUnitaire(detailSMEnchange.getCoutUnitaire());
        detailSMEnchange1.setQuantite(detailSMEnchange.getQuantite());
        detailSMEnchange1.setTotal(detailSMEnchange.getTotal());
        detailSMEnchange1.setCodeBar(detailSMEnchange.getCodeBar());
        detailSMEnchange1.setDateUpdate(new Date());
        detailSMEnchange1.setSupportMarchandEnchage(detailSMEnchange.getSupportMarchandEnchage());
        // update detailSMEnchange
        detailSMEnchange = detailSMEnchangeRepository.save(detailSMEnchange1);

        if (detailSMEnchange == null) {
            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
        }

    }

    // Fonction pour recupperer un support marchand d'echange
    @Override
    public DetailSMEnchange getById(Long id) throws Exceptions {
        if (!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<DetailSMEnchange> opad = detailSMEnchangeRepository.findById(id);
        return opad.get();
    }

    // Fonction pour faire l'encaissement
    @Transactional
    @Override
    public void encaissement(Double montant, String typeBAn, Long te) throws Exceptions, IOException, WriterException {
        DetailSMEnchange detailSMEnchange = new DetailSMEnchange();

        Long val = supportMarchandEnchageRepository.findSMbyId(typeBAn);

        String a = getAlphaNumericString();

        byte[] res = toByteArray(montant);
        while (isPresent(detailSMEnchange.getCodeSM())) {
            String ab = getAlphaNumericString();

            detailSMEnchange.setCodeSM("SM" + ab);
            detailSMEnchange.setCodeBar(String.valueOf(res));
            detailSMEnchange.setCoutUnitaire(montant);
            detailSMEnchange.setQuantite(1);
            detailSMEnchange.setTotal(montant);
            detailSMEnchange.setDateAchat(new Date());
            detailSMEnchange.setDateCreate(new Date());
            detailSMEnchange.setDateUpdate(new Date());
            detailSMEnchange.setTerminalEchange(new TerminalEchange(te));
            detailSMEnchange.setSupportMarchandEnchage(new SupportMarchandEnchage(val));
            DetailSMEnchange detailSMEnchange1 = detailSMEnchangeRepository.save(detailSMEnchange);
            if (detailSMEnchange1 == null) {
                throw new Exceptions(Exceptions.alertGeneralException("erreur d'ajout"));
            }

        }

        detailSMEnchange.setCodeSM("SM" + a);
        detailSMEnchange.setCodeBar(String.valueOf(res));
        detailSMEnchange.setCoutUnitaire(montant);
        detailSMEnchange.setQuantite(1);
        detailSMEnchange.setTotal(montant);
        detailSMEnchange.setDateAchat(new Date());
        detailSMEnchange.setDateCreate(new Date());
        detailSMEnchange.setDateUpdate(new Date());
        detailSMEnchange.setTerminalEchange(new TerminalEchange(te));
        detailSMEnchange.setSupportMarchandEnchage(new SupportMarchandEnchage(val));
        DetailSMEnchange detailSMEnchange1 = detailSMEnchangeRepository.save(detailSMEnchange);
        if (detailSMEnchange1 == null) {
            throw new Exceptions(Exceptions.alertGeneralException("erreur d'ajout"));
        }

    }

    /// Fonction pour faire le decaissement

    /***
     * UPGRADE BY Prince Patrice (DKP) 10/09/2022
     * Changes : Sustract value from selected details_sms_echanage corresponding to
     * te and type supportMarchandEnchage
     *
     *
     *
     * @return
     */
    @Override
    @Transactional
    public boolean decaissement(Double montant, String supportMarchandEnchage, Long te)
            throws Exceptions, IOException, WriterException {

        Long supportMarchandEnchageId = supportMarchandEnchageRepository.findSMbyId(supportMarchandEnchage);
        List<DetailSMEnchange> list_details_echange_by_ksu = detailSMEnchangeRepository
                .listDecaissemntByKsu(supportMarchandEnchageId, te);
        Double cumule_details_echange_by_ksu = detailSMEnchangeRepository.cumuleAmountByKsu(supportMarchandEnchageId,
                te);

        // Double mnte = 0.0;
        System.out.println("list of corresponding details_sms_echange");
        System.out.println(list_details_echange_by_ksu);
        if (cumule_details_echange_by_ksu > montant) {
            while (montant > 0) {
                System.out.println("operation action with amount " + montant);
                for (DetailSMEnchange b : list_details_echange_by_ksu) {
                    DetailSMEnchange detailSMEnchange1 = getById(b.getId());
                    if (b.getTotal() > 0) {
                        if (b.getTotal() >= montant) {
                            detailSMEnchange1.setTotal(b.getTotal() - montant);
                            detailSMEnchangeRepository.save(detailSMEnchange1);
                            montant = 0.0;
                            break;

                        } else {
                            montant = montant - b.getTotal();
                            detailSMEnchange1.setTotal(0.0);
                            detailSMEnchangeRepository.save(detailSMEnchange1);
                            break;
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public Double cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(String supportMarchandEnchage, Long te)
            throws Exceptions, IOException, WriterException {
        Long supportMarchandEnchageId = supportMarchandEnchageRepository.findSMbyId(supportMarchandEnchage);
        return detailSMEnchangeRepository.cumuleAmountByKsu(supportMarchandEnchageId, te);
    }

    // Fonction pour faire l'encaissement vide

    @Override
    public void encaissementVide(Double montant, String typeBAn, Long teEn)
            throws Exceptions, IOException, WriterException {

        String codeSM = getAlphaNumericString();
        while (isPresent(codeSM)) {
            codeSM = getAlphaNumericString();
        }
        byte[] res = toByteArray(montant);
        // Long val = supportMarchandEnchageRepository.findSMbyId(typeBAn);
        DetailSMEnchange detailSMEnchange = new DetailSMEnchange();
        detailSMEnchange.setCodeSM("SM" + codeSM);
        detailSMEnchange.setCodeBar(String.valueOf(res));
        detailSMEnchange.setCoutUnitaire(montant);
        detailSMEnchange.setQuantite(1);
        detailSMEnchange.setTotal(montant);
        detailSMEnchange.setDateAchat(new Date());
        detailSMEnchange.setDateCreate(new Date());
        detailSMEnchange.setDateUpdate(new Date());
        detailSMEnchange.setTerminalEchange(new TerminalEchange(teEn));
        detailSMEnchange.setSupportMarchandEnchage(
                supportMarchandEnchageRepository.findSupportMarchandEnchageByLibelle(typeBAn));
        DetailSMEnchange detailSMEnchange1 = detailSMEnchangeRepository.save(detailSMEnchange);
        if (detailSMEnchange1 == null) {
            throw new Exceptions(Exceptions.alertGeneralException("erreur d'ajout"));
        }

    }

    // Fonction pour faire l'echange

    /***
     * UPGRADE BY Prince Patrice (DKP) 10/09/2022
     *
     * @return
     */
    @Override
    @Transactional
    public void enchange(Double montant, String typeBAn, Long teEn, Long teDe)
            throws Exceptions, IOException, WriterException {
        if (decaissement(montant, typeBAn, teDe)) {
            encaissement(montant, typeBAn, teEn);
        }

    }

    static String getAlphaNumericString() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        // Exemple d'une chaine aléatoire de 20 caractères
        for (int i = 0; i < 9; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        // String output;
        // return output =
        return sb.toString();

    }

    public boolean isPresent(String codeSM) {
        Optional<DetailSMEnchange> opab = detailSMEnchangeRepository.findDetailSMEnchangeByCodeSM(codeSM);
        if (opab.isPresent())
            return true;
        return false;
    }

    public boolean isPresent(Long id) {
        Optional<DetailSMEnchange> opab = detailSMEnchangeRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }

    @Override
    public List<DetailSMEnchange> listBciReinitialiser(Long id) {
        return detailSMEnchangeRepository.listBciReinitialiser(id);
    }

    @Override
    public DetailSMEnchange approvisionnement(Double montant, Long te) throws Exceptions, IOException, WriterException {

        String codeSM = getAlphaNumericString();
        while (isPresent(codeSM)) {
            codeSM = getAlphaNumericString();
        }
        byte[] res = toByteArray(montant);
        Long val = supportMarchandEnchageRepository.findSMbyId(SupportMarchandConstant.supportMarchandBan);
        DetailSMEnchange detailSMEnchange = new DetailSMEnchange();
        detailSMEnchange.setCodeSM("SM" + codeSM);
        detailSMEnchange.setCodeBar(String.valueOf(res));
        detailSMEnchange.setCoutUnitaire(montant);
        detailSMEnchange.setQuantite(1);
        detailSMEnchange.setTotal(montant);
        detailSMEnchange.setDateAchat(new Date());
        detailSMEnchange.setDateCreate(new Date());
        detailSMEnchange.setDateUpdate(new Date());
        detailSMEnchange.setTerminalEchange(new TerminalEchange(te));
        detailSMEnchange.setSupportMarchandEnchage(new SupportMarchandEnchage(val));
        DetailSMEnchange detailSMEnchange1 = detailSMEnchangeRepository.save(detailSMEnchange);
        if (detailSMEnchange1 == null) {
            throw new Exceptions(Exceptions.alertGeneralException("erreur d'ajout"));
        }
        TerminalEchange terminalEchange = terminalEchangeRepository.findById(te).orElse(null);

        DetailsAgr d = detailAgrRestClient.getDetailById(terminalEchange.getDetailAgr());

        Ksu k = ksuRestClient.getById(d.getKsu());

        String message = "\n" + k.getNom() + " " + k.getPrenom() + " \n Votre code BAn est: "
                + detailSMEnchange.getCodeSM();

//        sendMail(k.getEmail(), "Code BAn", message);
        sendSms(k.getTelephone(), message);
        return detailSMEnchange;
    }

    // @Override
    // public DetailSMEnchange mutationGenerale(String typeSM, String typeDAte,
    // Double montant, Long idTe) throws Exceptions, IOException, WriterException {
    //
    // DetailSMEnchange detailSMEnchange=new DetailSMEnchange();
    // Double montantMuter=0.0;
    // if(typeSM.equals("BCr"))
    // montantMuter= mutationBCr(typeDAte,montant,idTe);
    // else if (typeSM.equals("BCnr")){
    // montantMuter =mutationBCnr(typeDAte,montant,idTe);
    // }
    // detailSMEnchange.setTotal(montantMuter);
    // return detailSMEnchange;
    // }

    @Override
    public List<DetailSMEnchange> listDSMEByIdTeAndSM(Long idTe, Long supportME) throws Exceptions {
        return detailSMEnchangeRepository.listDSMEByIdTeAndSM(idTe, supportME);
    }

    @Override
    public DetailTotal[] sommeTotalByIdTe(Long idTe) throws Exceptions {

        Long[] idlistsm = {
                SupportMarchandConstant.QsupportMarchandBan,
                SupportMarchandConstant.supportMarchandSMCIPN,
                SupportMarchandConstant.supportMarchandBLGCp,
                SupportMarchandConstant.supportMarchandBCi_BLGCp,
                SupportMarchandConstant.supportMarchandBLGCsc,

        };
        DetailTotal[] arr = new DetailTotal[idlistsm.length];
        int cpt = 0;
        for (Long idSm : idlistsm) {
            Double som = detailSMEnchangeRepository.sommeTotalDSMByTeAndSMDE(idTe, idSm);
            String libelle = supportMarchandEnchageRepository.libelleSMEMByTeAndSMDE(idSm);

            DetailTotal d1 = new DetailTotal();
            d1.setLibelle(libelle);
            d1.setTotal(som);
            d1.setIdSM(idSm);

            arr[cpt] = d1;
            cpt++;
        }

        return arr;
    }

    @Override
    public Double MutationMPRgBAnEntrerM(Double MPRgBAn, Long idTe) throws Exceptions, IOException, WriterException {

        // Double montantMPRg=terminalEchangeService.MPRgBAnEntrerM(MPRgBAn);

        decaissement(MPRgBAn, SupportMarchandConstant.supportMarchandBan, idTe);
        encaissementVide(MPRgBAn, SupportMarchandConstant.supportMarchandMPRg, idTe);

        return MPRgBAn;
    }

    public DetailSMEnchange create(Long idTe, Double montant, Long idSm, Long refer)
            throws Exceptions, IOException, WriterException {

        try {
            String codeSMMaBan = getAlphaNumericString();
            while (isPresent(codeSMMaBan)) {
                codeSMMaBan = getAlphaNumericString();
            }
            byte[] res = toByteArray(montant);
            TerminalEchange te = terminalEchangeRepository.findById(idTe).orElse(null);

            System.out.println("TES : " + te.getId());

            if (te == null) {
                return null;
            }

            SupportMarchandEnchage supportMarchandEnchage = supportMarchandEnchageRepository.findById(idSm)
                    .get();

            System.out.println("SM : " + supportMarchandEnchage.getId());

            if (supportMarchandEnchage == null) {
                return null;
            }

            DetailSMEnchange detailSMEnchange = new DetailSMEnchange();
            detailSMEnchange.setTerminalEchange(te);
            detailSMEnchange.setCoutUnitaire(montant);
            detailSMEnchange.setSourceEnree(montant);
            detailSMEnchange.setSourcceSortie(-montant);
            detailSMEnchange.setFondsEntre(montant);
            detailSMEnchange.setFondsFondDisponible(montant);
            detailSMEnchange.setQuantite(1);
            detailSMEnchange.setRefer(refer);
            detailSMEnchange.setSupportMarchandEnchage(supportMarchandEnchage);
            detailSMEnchange.setCodeSM(codeSMMaBan);
            detailSMEnchange.setCodeBar(String.valueOf(res));
            detailSMEnchange.setTotal(montant);
            return detailSMEnchangeRepository.save(detailSMEnchange);
        } catch (Exception e) {
            System.out.println("Somethings is wrong");
            return null;
        }

    }

    @Override
    public List<DetailSMEnchange> listDetailSMEnchangeByTeAndSupportExchange(Long idTe) {
        return detailSMEnchangeRepository.listDetailSMEnchangeByTeAndSupportExchange(idTe);
    }

    /*
     * /**
     *
     * @param idTe
     * 
     * @param montant
     *
     *
     * Processus MEV -- BAN
     *
     */
    @Override
    public DetailSMEnchange mutationProcessusMevBan(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.supportMarchandMABAn).orElse(null);

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        DetailSMEnchange detailSMEnchangeBan = null;

        boolean check = this.checkSmTotal(admin, sm.getId(), montant);

        if (check == true) {

            DetailSMEnchange ds = mutationFondDisponible(sm.getId(), admin, montant);
            // Creation du MaBan
            DetailSMEnchange detailSMEnchangeMaBan = this.create(admin, montant,
                    SupportMarchandConstant.supportMarchandMABAn, ds.getId());

            // Emission du MaBan Fonds de Sortie
            DetailSMEnchange dMABAnFonds = this.createFondSortie(admin, detailSMEnchangeMaBan.getId(), montant);
            // Creation du Ban
            detailSMEnchangeBan = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBan,
                    dMABAnFonds.getId());

        } else {
            return null;
        }
        return detailSMEnchangeBan;
    }

    /*
     * /**
     *
     * @param idTe
     * 
     * @param montant
     *
     *
     * Processus MEV -- BLGCsc
     *
     */

    public Ordre createOPi(Long idSm, Long idTe, Long idDetailSM, Double montant) throws IOException, WriterException {

        String codeSMMaBan = getAlphaNumericString();
        while (isPresent(codeSMMaBan)) {
            codeSMMaBan = getAlphaNumericString();
        }

        byte[] res = toByteArray(montant);

        TerminalEchange t = terminalEchangeRepository.findById(idTe).orElse(null);

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSm).orElse(null);

        System.out.println("Id SM : " + sm.getId());

        System.out.println("Libelle SM : " + sm.getLibelle());

        TypeOrdre typeOrdre = typeOrdreRepository.getTypeOrdreByLibelle(sm.getLibelle());

        System.out.println("Id Type ordre : " + typeOrdre.getId());

        if (typeOrdre == null) {
            return null;
        }

        // DecimalFormat formatterP = new DecimalFormat("0000000000");

        Ordre o = new Ordre();

        o.setCodeOPI(codeSMMaBan);
        o.setMontantOPI(montant);
        o.setDateEmission(new Date());
        o.setDatePrelevementOPI(new Date());
        o.setCodeBar(String.valueOf(res));
        o.setTerminalEchange(t);
        o.setTypeOrdre(typeOrdre);
        o.setSourceEnree(montant);
        o.setSourcceSortie(-montant);
        o.setFondsEntre(montant);
        o.setFondsSortie(-montant);
        o.setFondsFondDisponible(montant);
        o.setRefer(idDetailSM);
        o.setSupportMarchandEnchage(sm);



        Ordre or = ordreRepository.save(o);
        String code = o.getId() + "";
        int numero = 10 - code.length();

        for (int i = 0; i < numero; i++) {
            code = "0" + code;
        }

        o.setNumeroOPI(code);

        Ordre ord = ordreRepository.save(o);

        return ord;
    }

    public DetailSMEnchange createFondSortie(Long idTe, Long idDetailSmEchange, Double montant) throws IOException, WriterException {

        DetailSMEnchange detailSMEnchange = null;

        String codeSMMaBan = getAlphaNumericString();
        while (isPresent(codeSMMaBan)) {
            codeSMMaBan = getAlphaNumericString();
        }

        byte[] res = toByteArray(montant);
        DetailSMEnchange d = detailSMEnchangeRepository.detailSMEchangeByIdTe(idDetailSmEchange, idTe);

        if (d == null) {
            return null;
        }

        // if (montant <= d.getFondsFondDisponible()) {
        //
        // d.setFondsFondDisponible(d.getFondsFondDisponible() - montant);
        //
        // detailSMEnchangeRepository.save(d);

        detailSMEnchange = new DetailSMEnchange();
        detailSMEnchange.setTerminalEchange(d.getTerminalEchange());
        detailSMEnchange.setCoutUnitaire(montant);
        detailSMEnchange.setSourceEnree(montant);
        detailSMEnchange.setSourcceSortie(-montant);
        detailSMEnchange.setFondsEntre(montant);
        detailSMEnchange.setFondsFondDisponible(0.0);
        detailSMEnchange.setFondsSortie(-montant);
        detailSMEnchange.setQuantite(1);
        detailSMEnchange.setRefer(d.getId());
        detailSMEnchange.setSupportMarchandEnchage(d.getSupportMarchandEnchage());
        detailSMEnchange.setCodeSM(codeSMMaBan);
        detailSMEnchange.setCodeBar(String.valueOf(res));

        detailSMEnchange.setTotal(montant);

        //
        // }

        return detailSMEnchangeRepository.save(detailSMEnchange);
    }

    @Override
    public DetailSMEnchange mutationProcessusMevMABAn(Long id, Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // DetailSMEnchange sm = detailSMEnchangeRepository.findById(id).orElse(null);

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(id).orElse(null);

        Settings st = payementRestClient.getSettingById(SettingsConstant.PARAM_MABAn_Zero);

        DetailSMEnchange detailSMEnchangeMABAn = null;

        Double montantMABAn = 0.0;

        if (st == null) {
            return null;
        }

        if (sm == null) {
            return null;
        }

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (smTotal > 0) {
            boolean result = this.checkSmTotal(idTe, sm.getId(), smTotal);

            if (result == true) {

                DetailSMEnchange dst = mutationFondDisponible(sm.getId(), idTe, montant);

                if (dst != null) {

                    DetailSMEnchange ds = this.createFondSortie(idTe, dst.getId(), montant);

                    montantMABAn = Double.parseDouble(st.getValue()) * montant;

                    detailSMEnchangeMABAn = this.create(idTe, montantMABAn,
                            SupportMarchandConstant.supportMarchandMABAn, ds.getId());
                }

            } else {
                return null;
            }
        }

        return detailSMEnchangeMABAn;
    }

    @Override
    public DetailSMEnchange mutationProcessusMevMABAnNormal(Long id, Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        DetailSMEnchange sm = detailSMEnchangeRepository.findById(id).orElse(null);

        Settings stnormal = payementRestClient.getSettingById(SettingsConstant.PARAM_MABAn);

        DetailSMEnchange detailSMEnchangeMABAn = null;

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        Double montantMABAn = 0.0;

        // Creation de Fonds de Sortie de BPSD EV

        DetailSMEnchange d = this.createFondSortie(sm.getTerminalEchange().getId(), sm.getId(), montant);

        montantMABAn = Double.parseDouble(stnormal.getValue()) * montant;

        // Creation du MABAn
        detailSMEnchangeMABAn = this.create(idTe, montantMABAn, SupportMarchandConstant.supportMarchandMABAn,
                d.getId());

        return detailSMEnchangeMABAn;
    }

    /*
     * /**
     *
     * @param idTe
     * 
     * @param montant
     *
     *
     * Processus de Mutation de MEV
     *
     */

    @Override
    public DetailSMEnchange gennerateMev(Long idTransaction) throws Exceptions, IOException, WriterException {

        Transactions t = payementRestClient.getTransactionsById(idTransaction);

        if (t == null) {
            return null;
        }

        SupportMarchandEnchage s = supportMarchandEnchageRepository.sourceMEV(t.getSource());

        if (s == null) {
            return null;
        }

        // Creation du MEV
        DetailSMEnchange detailSMEnchangeMEV = this.create(t.getIdTe(), t.getMontant(), s.getId(), 0L);

        DetailSMEnchange det = detailSMEnchangeRepository.findById(detailSMEnchangeMEV.getId()).orElse(null);
        det.setOrgine(t.getOrigin());
        DetailSMEnchange deta = detailSMEnchangeRepository.save(det);

        // DetailSMEnchange detailSMEnchangeMEVAdmin = this.create(admin,
        // t.getMontant(), s.getId(), 0L);

        return deta;
    }

    /*
     * /**
     *
     * @param idTe
     * 
     * @param montant
     *
     *
     * Processus MPRg OPI -- BPSDV
     *
     */

    @Override
    public DetailSMEnchange mutationMPRgOPIBpsdv(Long idTransDacaissement)
            throws Exceptions, IOException, WriterException {

        TransactionAdmins t = payementRestClient.getTransactionById(idTransDacaissement);

        if (t == null) {
            return null;
        }

        SupportMarchandEnchage s = supportMarchandEnchageRepository.findById(t.getBon()).orElse(null);

        if (s == null) {
            return null;
        }

        SupportMarchandEnchage SmMutter = supportMarchandEnchageService.decaissementSM(s.getId(), t.getIdDetailAgr(),
                t.getTypeFonds());

        if (SmMutter == null) {
            return null;
        }

        // DetailSMEnchange muttationDecaisse = this.mutationSMProcessus(t.getIdTe(),
        // s.getId(), SmMutter.getId(), t.getMontant());
        DetailSMEnchange muttationDecaisse = this.create(t.getIdTe(), t.getMontant(), s.getId(), null);

        if (muttationDecaisse == null) {
            return null;
        }
        DetailSMEnchange mutsave = detailSMEnchangeRepository.findById(muttationDecaisse.getId()).orElse(null);
        mutsave.setOrgine(t.getSource());
        detailSMEnchangeRepository.save(mutsave);

        return muttationDecaisse;
    }

    /*
     * /**
     *
     * @param idTe
     * 
     * @param montant
     *
     *
     * Processus MEV -- BLGCsc
     *
     */

    @Override
    public void generatedCircuitMevBGCs(Long idTe, Double montant) throws Exceptions, IOException, WriterException {

        // Creatiob de Mev
        DetailSMEnchange dMev = this.create(idTe, montant, SupportMarchandConstant.supportMarchandMV, 0L);

        // Creation de Fonds de sortie de Mev
        DetailSMEnchange dMEvFondSortie = this.createFondSortie(dMev.getTerminalEchange().getId(), dMev.getId(),
                dMev.getFondsEntre());

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.supportMarchandMABAn).orElse(null);

        DetailSMEnchange dsmBlgcp = null;

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        DetailSMEnchange dsm = null;

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        boolean result = this.checkSmTotal(idTe, sm.getId(), smTotal);

        if (result == true) {

            List<DetailSMEnchange> listSm = this.listdSMFondsDisponible(sm.getId(), admin);

            if (listSm.size() > 0) {
                for (DetailSMEnchange d : listSm) {
                    DetailSMEnchange dt = detailSMEnchangeRepository.findById(d.getId()).orElse(null);
                    dt.setFondsSortie(-dt.getFondsEntre());
                    dt.setFondsFondDisponible(dt.getFondsFondDisponible() - d.getFondsFondDisponible());
                    detailSMEnchangeRepository.save(dt);
                }

            }

            dsm = this.create(idTe, smTotal, SupportMarchandConstant.supportMarchandMABAn, 0L);

            // DetailSMEnchange dse =
            // this.createFondSortie(dsm.getTerminalEchange().getId(), dsm.getId(),
            // montant);

            DetailSMEnchange ds = detailSMEnchangeRepository.findById(dsm.getId()).orElse(null);

            ds.setFondsSortie(-ds.getFondsEntre());
            ds.setFondsFondDisponible(ds.getFondsFondDisponible() - montant);

            detailSMEnchangeRepository.save(ds);

            // dsmBlgcp = this.create(admin, ds.getFondsEntre(),
            // SupportMarchandConstant.QsupportMarchandBan, ds.getId());

        }

        // Creation de BAn
        DetailSMEnchange dBAn = this.create(dMEvFondSortie.getTerminalEchange().getId(), dMEvFondSortie.getFondsEntre(),
                SupportMarchandConstant.QsupportMarchandBan, dMEvFondSortie.getId());

        // Creation de Fonds de sortie de BAn
        DetailSMEnchange dBAnFondSortie = this.createFondSortie(dBAn.getTerminalEchange().getId(), dBAn.getId(),
                dBAn.getFondsEntre());

        // Creation de BCnr
        DetailSMEnchange dBCnr = this.create(dBAnFondSortie.getTerminalEchange().getId(),
                dBAnFondSortie.getFondsEntre(), SupportMarchandConstant.QsupportMarchandBCnr, dBAnFondSortie.getId());

        // Creation de Fonds de sortie de BCnr
        DetailSMEnchange dBCnrFond = this.createFondSortie(dBCnr.getTerminalEchange().getId(), dBCnr.getId(),
                dBCnr.getFondsEntre());

        // Montant BCnrPRK

        Double montantPRM = terminalEchangeImpl.bcnrPrk(dBCnrFond.getFondsEntre());

        DetailSMEnchange dBCnrPRk = this.create(dBCnrFond.getTerminalEchange().getId(), montantPRM,
                SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, dBCnrFond.getId());

        // Creation de Fonds de sortie de BCnr PRK
        DetailSMEnchange dBCnrPRKFonds = this.createFondSortie(dBCnrPRk.getTerminalEchange().getId(), dBCnrPRk.getId(),
                dBCnrPRk.getFondsEntre());

        // Creation de BLGCsc
        DetailSMEnchange dBLGCsc = this.create(admin, dBCnrPRKFonds.getFondsEntre(),
                SupportMarchandConstant.supportMarchandBLGCsc, dBCnrPRKFonds.getId());
    }

    /**
     * @param idTe
     * @param montant Processus SMCIPNBcismci -- BciSMCI ()
     */
    @Override
    public DetailSMEnchange mutationProcessusSMCIPNBcismciBciSMCI(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        try {
            // Creation du SMCIPNBcismci
            DetailSMEnchange detailSMEnchangeSMCIPNBcismci = this.create(idTe, montant,
                    SupportMarchandConstant.supportMarchandSMCIPN_Services_Interim, 0L);
            detailSMEnchangeSMCIPNBcismci.setFondsFondDisponible(0.0);
            detailSMEnchangeRepository.save(detailSMEnchangeSMCIPNBcismci);

            // Emission du Fonds de Sortie de SMCIPNBcismci
            DetailSMEnchange dSMCIPNFondsSortie = this.createFondSortie(idTe, detailSMEnchangeSMCIPNBcismci.getId(),
                    montant);

            // Creation du SMCIINTERIEM
            DetailSMEnchange dSMCI = this.create(idTe, montant,
                    SupportMarchandConstant.supportMarchandSMCI_Services_Interim, dSMCIPNFondsSortie.getId());
            dSMCI.setFondsFondDisponible(0.0);
            detailSMEnchangeRepository.save(dSMCI);

            // Emission du Fonds de Sortie de SMCIPNBcismci
            DetailSMEnchange deSMCIFondSortie = this.createFondSortie(idTe, dSMCI.getId(), montant);

            // Creation du BAIsmci
            DetailSMEnchange dBAIsmci = this.create(idTe, montant,
                    SupportMarchandConstant.supportMarchandBAi_SMCI_Services_Interim, deSMCIFondSortie.getId());
            dBAIsmci.setFondsFondDisponible(0.0);
            detailSMEnchangeRepository.save(dBAIsmci);

            // Emission du Fonds de Sortie de SMCIPNBcismci
            DetailSMEnchange dBAIsmciFondsSortie = this.createFondSortie(idTe, dBAIsmci.getId(), montant);

            // Creation du BCIsmci
            DetailSMEnchange dBCIsmci = this.create(idTe, montant,
                    SupportMarchandConstant.supportMarchandBCi_SMCI_Services_Interim, dBAIsmciFondsSortie.getId());


            return dBCIsmci;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return null;
        }

    }

    /**
     * @param idTe
     * @param montant Processus SMCIPNBcismci -- BciSMCPN ()
     */
    @Override
    public DetailSMEnchange mutationProcessusSMCIPNBcismciBciSMCPN(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation du SMCIPNBcismci
        DetailSMEnchange dSMCIPNBcismci = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCIPN_Services_Interim, 0L);
        dSMCIPNBcismci.setFondsFondDisponible(0.0);
        detailSMEnchangeRepository.save(dSMCIPNBcismci);

        // Emission du Fonds de Sortie de SMCIPNBcismci
        DetailSMEnchange dSMCIPNBcismciFondSortie = this.createFondSortie(idTe, dSMCIPNBcismci.getId(), montant);

        // Creation du SMCPNINTERIEM
        DetailSMEnchange dSMCPNINTERIEM = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMC_PN_Services_Interim, dSMCIPNBcismciFondSortie.getId());
        dSMCPNINTERIEM.setFondsFondDisponible(0.0);
        detailSMEnchangeRepository.save(dSMCPNINTERIEM);

        // Emission du Fonds de Sortie de SMCPNINTERIEM
        DetailSMEnchange dSMCPNINTERIEMFondSortie = this.createFondSortie(idTe, dSMCPNINTERIEM.getId(), montant);

        // Creation du BAIsmcpn
        DetailSMEnchange dBAIsmcpn = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMC_PN_Services_Interim, dSMCPNINTERIEMFondSortie.getId());
        dBAIsmcpn.setFondsFondDisponible(0.0);
        detailSMEnchangeRepository.save(dBAIsmcpn);

        // Emission du Fonds de Sortie de BAIsmcpn
        DetailSMEnchange dBAIsmcpnFondSortie = this.createFondSortie(idTe, dBAIsmcpn.getId(), montant);

        // Creation du BCIsmcpn
        DetailSMEnchange dBCIsmcpn = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCi_SMC_PN_Services_Interim, dBAIsmcpnFondSortie.getId());

        return dBCIsmcpn;
    }

    /**
     * @param idTeDestination
     * @param idTeSource
     * @param idTeSource      Bcismcpn - Blgcp
     */
    @Override
    public DetailSMEnchange mutationProcessusBcismcpnBlgcp(Long idTeSource, Long idTeDestination, Long idSmE)
            throws Exceptions, IOException, WriterException {
        // SMCIPNBcismci Actuelle
        DetailSMEnchange detailSMEnchangeBcismcpn = this.detailSMEnchangeRepository.findById(idSmE).get();
        Double montant = detailSMEnchangeBcismcpn.getTotal();

        DetailSMEnchange detailSMEnchangeBlgcp = null;
        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(detailSMEnchangeBcismcpn.getSupportMarchandEnchage().getId()).orElse(null);

        Double montantTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTeSource);

        if (montant <= montantTotal) {
            Double montantRenitialiserBCI = 0.0;
            montantRenitialiserBCI = (montant / 2187.5) * 70000;

            DetailSMEnchange dst = this.mutationFondDisponible(sm.getId(), idTeSource, montant);

            if (dst != null) {
                // Creation de Fonds de Sortie du BCiSMCPN
                DetailSMEnchange dBCiSMCPN = this.createFondSortie(idTeSource, dst.getId(), montant);

                // Creation du Blgcp
                detailSMEnchangeBlgcp = this.create(idTeDestination, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBLGCp, dBCiSMCPN.getId());
            } else {
                return null;
            }

        }

        return detailSMEnchangeBlgcp;
    }

    /**
     * @param idTeDestination
     * @param idTeSource
     * @param idTeSource      Bcismci - Blgcp
     */
    @Override
    public DetailSMEnchange mutationProcessusBcismciBlgcp(Long idTeSource, Long idTeDestination, Long idSmE)
            throws Exceptions, IOException, WriterException {
        // SMCIPNBcismci Actuelle
        DetailSMEnchange detailSMEnchangeBcismci = this.detailSMEnchangeRepository.findById(idSmE).get();
        Double montant = detailSMEnchangeBcismci.getTotal();

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(detailSMEnchangeBcismci.getSupportMarchandEnchage().getId()).orElse(null);

        Double montantTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTeSource);
        DetailSMEnchange detailSMEnchangeBlgcp = null;

        if (montant <= montantTotal) {
            Double montantRenitialiserBCI = 0.0;
            montantRenitialiserBCI = (montant / 2187.5) * 70000;

            DetailSMEnchange dst = this.mutationFondDisponible(sm.getId(), idTeSource, montant);

            if (dst != null) {
                // Creation de Fonds de Sortie de BCISMCI
                DetailSMEnchange dBCISMCIFonds = this.createFondSortie(idTeSource, dst.getId(), montant);

                // Creation du Blgcp
                detailSMEnchangeBlgcp = this.create(idTeDestination, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBLGCp, dBCISMCIFonds.getId());
            } else {
                return null;
            }

        }

        return detailSMEnchangeBlgcp;
    }

    @Override
    public DetailSMEnchange mutationProcessusBlgcpBciReinitialise(Long idTe, Long idSmE)
            throws Exceptions, IOException, WriterException {
        return null;
    }

    /**
     * @param idTe Processus Blgcp -- BciReinitialise ()
     */
    @Override
    public DetailSMEnchange mutationProcessusBlgcpBciReinitialise(Long idTe, Long idSmE, Double montantRenitialiserBCI)
            throws Exceptions, IOException, WriterException {


        DetailSMEnchange detailSMEnchangeBciReinitialise = null;
        DetailSMEnchange detailSMEnchangeBlgcp = this.detailSMEnchangeRepository.findById(idSmE).get();
        Double montant = detailSMEnchangeBlgcp.getTotal();

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(detailSMEnchangeBlgcp.getSupportMarchandEnchage().getId()).get();

        Double montantTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);


        if ( montantRenitialiserBCI <= montantTotal) {

            DetailSMEnchange dst = this.mutationFondDisponible(sm.getId(), idTe, montantRenitialiserBCI);

            if (dst != null) {
                // Creation de Fonds de Sortie du BLGCp
                DetailSMEnchange dBLGCpFondSortie = this.createFondSortie(idTe, dst.getId(), montant);

                // Emission du Blgcp
                detailSMEnchangeBciReinitialise = this.create(idTe, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBCi_BLGCp, dBLGCpFondSortie.getId());
            } else {
                return null;
            }

        }

        return detailSMEnchangeBciReinitialise;
    }

    @Override
    public DetailSMEnchange renitialisationPassif(Double montantBAn, Long idTe)
            throws Exceptions, IOException, WriterException {

        System.out.println("id TE Ksu : " + idTe);

        TerminalEchange terminalEchange = this.terminalEchangeRepository.findById(idTe).get();

        Long admin;
        DetailSMEnchange detailSMEnchangeBlgcp;

        // to revise depend on passifpresentiel type

        admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        DetailSMEnchange detailSMEnchangeBciSMCI = mutationProcessusSMCIPNBcismciBciSMCI(admin, montantBAn);
        detailSMEnchangeBlgcp = mutationProcessusBcismciBlgcp(admin, idTe, detailSMEnchangeBciSMCI.getId());

        DetailSMEnchange detailSMEnchangeBciReinitialiser = mutationProcessusBlgcpBciReinitialise(idTe,
                detailSMEnchangeBlgcp.getId(), detailSMEnchangeBlgcp.getTotal());

        return detailSMEnchangeBciReinitialiser;
    }

    @Override
    public DetailSMEnchange renitialisationBCI(Double montantBAn, Long idTe)
            throws Exceptions, IOException, WriterException {

        DetailsAgr agr = detailAgrRestClient.getDetailById(idTe);

        Ksu k = ksuRestClient.getById(agr.getKsu());

        String nom = k.getNom();
        String prenom = k.getPrenom();
        String raisonSocial = k.getRaisonSocial();

        PassifPresentielle p = null;

        if (k.getCodeKsu().contains("P")) {
            p = passifPresentielleInterface.getPassifPresentielleByNomAndPrenom(nom, prenom);
        } else {
            p = passifPresentielleInterface.getPassifPresentielleByNomAndPrenom(raisonSocial, raisonSocial);
        }
        if (p == null) {
            return null;
        }

        int typeContrat = Integer.parseInt(p.getTypeContrat());

        TerminalEchange terminalEchange = this.terminalEchangeRepository.findById(idTe).get();
        // List<TypeMaBanKsuAgr> typeMaBanKsuAgrList =
        // this.terminalEchangeRepository.findByTypeMaBanKsuAgr(terminalEchange.getId());
        // en supposant qu on retrouve le typemabanksu
        // TypeMaBanKsuAgr typeMaBanKsuAgr = typeMaBanKsuAgrList.get(0);
        Long admin;
        DetailSMEnchange detailSMEnchangeBlgcp;

        DetailSMEnchange detailSMEnchangeBciReinitialiser;

        // to revise depend on passifpresentiel type

        if (typeContrat == 2) {
            admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
            DetailSMEnchange detailSMEnchangeBciSMCI = mutationProcessusSMCIPNBcismciBciSMCI(admin, montantBAn);
            detailSMEnchangeBlgcp = mutationProcessusBcismciBlgcp(admin, idTe, detailSMEnchangeBciSMCI.getId());
            detailSMEnchangeBciReinitialiser = mutationProcessusBlgcpBciReinitialise(idTe,
                    detailSMEnchangeBlgcp.getId(), detailSMEnchangeBlgcp.getTotal());

        } else if (typeContrat == 1) {
            admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
            DetailSMEnchange detailSMEnchangeBciSMCPN = mutationProcessusSMCIPNBcismciBciSMCPN(admin, montantBAn);
            detailSMEnchangeBlgcp = mutationProcessusBcismcpnBlgcp(admin, idTe, detailSMEnchangeBciSMCPN.getId());
            detailSMEnchangeBciReinitialiser = mutationProcessusBlgcpBciReinitialise(idTe,
                    detailSMEnchangeBlgcp.getId(), detailSMEnchangeBlgcp.getTotal());
        } else {
            return null;
        }

        return detailSMEnchangeBciReinitialiser;
    }

    // LES PROCESSUS DE MUTATION

    /**
     * @param idTe
     * @param montant Processus BAn en B
     */
    @Override
    public void mutationProcessusBAnBcr(Long idTe, Double montant) throws Exceptions, IOException, WriterException {

        // Creation de Ban
        DetailSMEnchange detailSMEnchangeBAn = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBan,
                null);
        // Emission du Ban Negative
        DetailSMEnchange detailSMEnchangeBAnNeg = this.create(idTe, -montant,
                SupportMarchandConstant.QsupportMarchandBan, detailSMEnchangeBAn.getId());
        // Creation du BCr
        DetailSMEnchange detailSMEnchangeBcr = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBCr,
                detailSMEnchangeBAn.getId());

    }

    @Override
    public void mutationProcessusBCrBcrParjour(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        Double montantBCrjour = terminalEchangeImpl.jour(montant);

        // Creation de BCr
        DetailSMEnchange detailSMEnchangeBCr = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBCr,
                null);
        // Emission du BCr Negative
        DetailSMEnchange detailSMEnchangeBCrNeg = this.create(idTe, -montant,
                SupportMarchandConstant.QsupportMarchandBCr, detailSMEnchangeBCr.getId());
        // Creation du BCr Par jour
        DetailSMEnchange detailSMEnchangeBcrParjour = this.create(idTe, montantBCrjour,
                SupportMarchandConstant.QsupportMarchandBCr, detailSMEnchangeBCr.getId());

    }

    @Override
    public void mutationProcessusBCrParJourBcrLimite_11_2(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        Double montantBCrLimite_11_2 = terminalEchangeImpl.limitee_11_2(montant);

        // Creation de BCr Par Jour
        DetailSMEnchange detailSMEnchangeBCrParjour = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCr_jour, null);
        // Emission du BCr Par Jour Negative
        DetailSMEnchange detailSMEnchangeBCrParjourNeg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCr_jour, detailSMEnchangeBCrParjour.getId());
        // Creation du BCr Limite 11,2
        DetailSMEnchange detailSMEnchangeBcrLimite_11_2 = this.create(idTe, montantBCrLimite_11_2,
                SupportMarchandConstant.supportMarchandBCrl11_2, detailSMEnchangeBCrParjour.getId());

    }

    @Override
    public void mutationProcessusBAnMPRgBan(Long idTe, Double montant) throws Exceptions, IOException, WriterException {

        // creation du ban
        DetailSMEnchange detailSMEnchangeBAn = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBan,
                null);
        // Emission du Ban Negative
        DetailSMEnchange detailSMEnchangeBAnNeg = this.create(idTe, -montant,
                SupportMarchandConstant.QsupportMarchandBan, detailSMEnchangeBAn.getId());
        // Creation du MPRgBan
        DetailSMEnchange detailSMEnchangeMPRgBAn = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandMPRg_BAn, detailSMEnchangeBAn.getId());

    }

    @Override
    public void mutationProcessusBAnBCnr(Long idTe, Double montant) throws Exceptions, IOException, WriterException {

        // Creation de Ban
        DetailSMEnchange detailSMEnchangeBAn = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBan,
                null);
        // Emission du Ban Negative
        DetailSMEnchange detailSMEnchangeBAnNeg = this.create(idTe, -montant,
                SupportMarchandConstant.QsupportMarchandBan, detailSMEnchangeBAn.getId());
        // Creation du BCnr
        DetailSMEnchange detailSMEnchangeBCnr = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBCnr,
                detailSMEnchangeBAn.getId());

    }

    @Override
    public void mutationProcessusBCrParjourBLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation de BCr
        DetailSMEnchange detailSMEnchangeBCr = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCr_jour, null);
        // Emission du BCr Negative
        DetailSMEnchange detailSMEnchangeBCrNeg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCr_jour, detailSMEnchangeBCr.getId());
        // Creation du BLGPCpParJour
        DetailSMEnchange detailSMEnchangeBLGPCpParJour = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCr.getId());

    }

    @Override
    public void mutationProcessusBCrLimite_11_2BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {
        // Creation de BCr
        DetailSMEnchange detailSMEnchangeBCr = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCrl11_2, null);
        // Emission du BCr Negative
        DetailSMEnchange detailSMEnchangeBCrNeg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCrl11_2, detailSMEnchangeBCr.getId());
        // Creation du BLGPCpLimite_11_2
        DetailSMEnchange detailSMEnchangeBLGPCLimite_11_2 = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCr.getId());

    }

    @Override
    public void mutationProcessusBCrLimite_22_4BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation de BCr
        DetailSMEnchange detailSMEnchangeBCr = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCrl22_4, null);
        // Emission du BCr Negative
        DetailSMEnchange detailSMEnchangeBCrNeg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCrl22_4, detailSMEnchangeBCr.getId());
        // Creation du BLGPCpLimite_22_4
        DetailSMEnchange detailSMEnchangeBLGPC = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCr.getId());

    }

    @Override
    public void mutationProcessusBCrIllimite_22_2BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation de BCr
        DetailSMEnchange detailSMEnchangeBCr = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCrl22_4, null);
        // Emission du BCr Negative
        DetailSMEnchange detailSMEnchangeBCrNeg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCrl22_4, detailSMEnchangeBCr.getId());
        // Creation du BLGPCpLimite_22_4
        DetailSMEnchange detailSMEnchangeBLGPC = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCr.getId());

    }

    @Override
    public void mutationProcessusOPI_nonEchusBLGCP(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation de OPI_nonEchus
        DetailSMEnchange detailSMEnchangeOPI_nonEchus = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandOPI_non_echus, null);
        // Emission du OPI_nonEchus Negative
        DetailSMEnchange detailSMEnchangeOPI_nonEchusNeg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandOPI_non_echus, detailSMEnchangeOPI_nonEchus.getId());
        // Creation du BLGPCp
        DetailSMEnchange detailSMEnchangeBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeOPI_nonEchus.getId());

    }

    @Override
    public void mutationProcessusBCnr5_6_PRK_6_5BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation de BCnr5_6_PRK_6_5
        DetailSMEnchange detailSMEnchangeBCnr5_6_PRK_6_5 = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, null);
        // Emission du BCnr5_6_PRK_6_5
        DetailSMEnchange detailSMEnchangeBCnr5_6_PRK_6_5Neg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, detailSMEnchangeBCnr5_6_PRK_6_5.getId());
        // Creation du BLGPC
        DetailSMEnchange detailSMEnchangeBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCnr5_6_PRK_6_5.getId());

    }

    @Override
    public void mutationProcessusBCnrPRK_6_5_8BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {
        // Creation de BCnrPRK_6_5_8
        DetailSMEnchange detailSMEnchangeBCnrPRK_6_5_8 = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCnrPRK_6_5_8, null);
        // Emission du BCnrPRK_6_5_8
        DetailSMEnchange detailSMEnchangeBCnrPRK_6_5_8Neg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCnrPRK_6_5_8, detailSMEnchangeBCnrPRK_6_5_8.getId());
        // Creation du BLGPC
        DetailSMEnchange detailSMEnchangeBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCnrPRK_6_5_8.getId());

    }

    @Override
    public void mutationProcessusBCnrIMMPRK8_PRK9BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {

        // Creation de BCnrIMM_PRK8_PRK_9
        DetailSMEnchange detailSMEnchangeBCnrIMMPRK8_PRK9 = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCnrIMM_PRK8_PRK_9, null);
        // Emission du BCnrIMM_PRK8_PRK_9
        DetailSMEnchange detailSMEnchangeBCnrIMMPRK8_PRK9Neg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCnrIMM_PRK8_PRK_9, detailSMEnchangeBCnrIMMPRK8_PRK9.getId());
        // Creation du BLGPC
        DetailSMEnchange detailSMEnchangeBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCnrIMMPRK8_PRK9.getId());

    }

    @Override
    public void mutationProcessusBCnrPRE_9_22_4BLGCp(Long idTe, Double montant)
            throws Exceptions, IOException, WriterException {
        // Creation de BCnrIMM_PRE_9_22_4
        DetailSMEnchange detailSMEnchangeBCnrPRE_9_22_4 = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCnrPRE_9_22_4, null);
        // Emission du BCnrIMM_PRE_9_22_4
        DetailSMEnchange detailSMEnchangeBCnrPRE_9_22_4Neg = this.create(idTe, -montant,
                SupportMarchandConstant.supportMarchandBCnrPRE_9_22_4, detailSMEnchangeBCnrPRE_9_22_4.getId());
        // Creation du BLGPC
        DetailSMEnchange detailSMEnchangeBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, detailSMEnchangeBCnrPRE_9_22_4.getId());

    }

    @Override
    public void mutationProcessusBCnrPREIMM_11_2_PRK_22_4BLGCp(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCnrPREIMM_11_2_PRK_22_4
        DetailSMEnchange dBCnrPREIMM_11_2_PRK_22_4 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BLGPC
        DetailSMEnchange detailSMEnchangeBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCp, dBCnrPREIMM_11_2_PRK_22_4.getId());

    }

    @Override
    public void mutationProcessusBCrParjourBLGsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCrParjour
        DetailSMEnchange dBCrParjour = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BCr
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCrParjour.getId());

    }

    @Override
    public void mutationProcessusBCrLimite11_2BLGsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCr/Limité 11,2 jour en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCr/Limité 11,2 jour
        DetailSMEnchange dBCrLimite11_2 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCrLimite11_2.getId());

    }

    @Override
    public void mutationProcessusBCrLimite22_4BLGsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCr/Limité 22,4 jour en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCr/Limité 22,4 jour
        DetailSMEnchange dBCrLimite22_4 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCrLimite22_4.getId());

    }

    @Override
    public void mutationProcessusBCrIllimite22_4BLGsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCr/Illimitée 22,4 en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCr/Illimitée 22,4
        DetailSMEnchange dBCrIllimite22_4 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCrIllimite22_4.getId());

    }

    @Override
    public void mutationProcessusOPI_non_chusBLGCsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation OPI non échus en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du OPI non échus
        DetailSMEnchange dOPI_non_echus = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dOPI_non_echus.getId());

    }

    @Override
    public void mutationProcessusBCnr5_6_PRK_6_5BLGCsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCnr5,6≤PRK<6,5 en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCnr5,6≤PRK<6,5
        DetailSMEnchange dBCnr5_6_PRK_6_5 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCnr5_6_PRK_6_5.getId());

    }

    @Override
    public void mutationProcessusBCnrPRK_6_5_8BLGCsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCnrPRK≥6.5à8 en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCnrPRK≥6.5à8
        DetailSMEnchange dBCnrPRK_6_5_8 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCnrPRK_6_5_8.getId());

    }

    @Override
    public void mutationProcessusBCnrIMM_PRK8_PRK_9BLGCsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCnrIMM PRK8≤PRK≤9 en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCnrIMM PRK8≤PRK≤9
        DetailSMEnchange dBCnrIMM_PRK8_PRK_9 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCnrIMM_PRK8_PRK_9.getId());

    }

    @Override
    public void mutationProcessusBCnrPRE_9_22_4BLGCsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCnrPRE≥9à22.4 en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCnrPRE≥9à22.4
        DetailSMEnchange dBCnrPRE_9_22_4 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCnrPRE_9_22_4.getId());

    }

    @Override
    public void mutationProcessusBCnrPREIMM11_2_PRK_22_4BLGCsc(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BCnrPREIMM11.2≤PRK≤22.4 en BLGCsc
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BCnrPREIMM11.2≤PRK≤22.4
        DetailSMEnchange dBCnrPREIMM11_2_PRK_22_4 = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BLGsc
        DetailSMEnchange detailSMEnchangeBLGsc = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBLGCsc, dBCnrPREIMM11_2_PRK_22_4.getId());

    }

    @Override
    public void mutationProcessusBLGCpBCiBLGCp(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BLGCp en BC
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BLGCp
        DetailSMEnchange dBLGCp = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BCi BLGCp
        DetailSMEnchange detailSMEnchangeBCiBLGCp = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBCi_BLGCp, dBLGCp.getId());

    }

    @Override
    public void mutationProcessusBLGCpMPRg_BAn_OPI_echus(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BLGCp en MPRg BAn/OPI échus
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BLGCp
        DetailSMEnchange dBLGCp = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du MPRg_BAn_OPI_echus
        DetailSMEnchange detailSMEnchangeMPRg_BAn_OPI_echus = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandMPRg_BAn_OPI_echus, dBLGCp.getId());

    }

    @Override
    public Object mutationProcessusBLGCpOPI(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BLGCp en OPI

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BLGCp
        DetailSMEnchange dBLGCp = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // System.out.println(dBLGCp.getId());

        // Creation du OPI
        // DetailSMEnchange detailSMEnchangeOPI =
        // this.create(idTe,montant,SupportMarchandConstant.supportMarchandOPI,dBLGCp.getId());

        Ordre ord = this.createOPi(SupportMarchandConstant.supportMarchandOPI, idTe, dBLGCp.getId(), montant);

        return ord;

    }

    @Override
    public void mutationProcessusOPIMPRg_OPI(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation OPI en MPRg OPI

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du OPI
        DetailSMEnchange dOPI = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du MPRg OPI
        DetailSMEnchange detailSMEnchangeMPRg_OPI = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandMPRg_OPI, dOPI.getId());

    }

    @Override
    public void mutationProcessusBLGCscSMCIPN(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation BLGCsc en SMCIPN

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BLGCsc
        DetailSMEnchange dBLGCsc = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMCIPN
        DetailSMEnchange detailSMEnchangeSMCIPN = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCIPN, dBLGCsc.getId());

    }

    @Override
    public void mutationProcessusSMCIPNTSMCIPN_Budget(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN en TSMCIPN Budget
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du BLGCsc
        DetailSMEnchange dSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du TSMCIPN Budget
        DetailSMEnchange detailSMEnchangeTSMCIPN_Budget = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandTSMCIPN_Budget, dSMCIPN.getId());

    }

    @Override
    public void mutationProcessusSMCIPNTSMCIPN_Budget_Anticipe(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN en TSMCIPN Budget Anticipé
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCIPN
        DetailSMEnchange dSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du TSMCIPN Budget Anticipé
        DetailSMEnchange detailSMEnchangeTSMCIPN_Budget_Anticipe = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandTSMCIPN_Budget_Anticipe, dSMCIPN.getId());

    }

    @Override
    public void mutationProcessusSMCIPNTSMCIPN_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN en TSMCIPN Services Intérim

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCIPN
        DetailSMEnchange dSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du TSMCIPN Services Intérim
        DetailSMEnchange detailSMEnchangeTSMCIPN_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandTSMCIPN_Services_Interim, dSMCIPN.getId());

    }

    @Override
    public void mutationProcessusSMCIPNTSMCIPN_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN en TSMCIPN Autres

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCIPN
        DetailSMEnchange dSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du TSMCIPN Autres
        DetailSMEnchange detailSMEnchangeTSMCIPN_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandTSMCIPN_Autres, dSMCIPN.getId());

    }

    @Override
    public void mutationProcessusTSMCIPNSMCI_Budget(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Budget en SMCI Budget

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN
        DetailSMEnchange dTSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMCI_Budget
        DetailSMEnchange detailSMEnchangeSMCI_Budget = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCI_Budget, dTSMCIPN.getId());

    }

    @Override
    public void mutationProcessusTSMCIPNSMC_PN_Budget(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Budget en SMC PN Budget
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN
        DetailSMEnchange dTSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMC_PN_Budget
        DetailSMEnchange detailSMEnchangeSMC_PN_Budget = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMC_PN_Budget, dTSMCIPN.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_Budget_AnticipeSMCI_Budget_Anticipe(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Budget Anticipé en SMCI Budget Anticipé
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN
        DetailSMEnchange dTSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMCI Budget Anticipé
        DetailSMEnchange detailSMEnchangeSMCI_Budget_Anticipe = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCI_Budget_Anticipe, dTSMCIPN.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_Budget_AnticipeSMC_PN_Budget_Anticipe(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Budget Anticipé en SMC PN Budget Anticipé
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN
        DetailSMEnchange dTSMCIPN = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMC PN Budget Anticipé
        DetailSMEnchange detailSMEnchangeSMC_PN_Budget_Anticipe = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMC_PN_Budget_Anticipe, dTSMCIPN.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_Services_InterimSMCI_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Services Intérim en SMCI Services Intérim
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN Services Intérim
        DetailSMEnchange dTSMCIPN_Services_Interim = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du SMCI Services Intérim
        DetailSMEnchange detailSMEnchangeSMCI_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCI_Services_Interim, dTSMCIPN_Services_Interim.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_Services_InterimSMC_PN_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Services Intérim en SMC PN Services Intérim
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN Services Intérim
        DetailSMEnchange dTSMCIPN_Services_Interim = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du SMC PN Services Intérim
        DetailSMEnchange detailSMEnchangeSMC_PN_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMC_PN_Services_Interim, dTSMCIPN_Services_Interim.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_Services_InterimSMCIPN_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Services Intérim en SMCIPN Services Intérim
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN Services Intérim
        DetailSMEnchange dTSMCIPN_Services_Interim = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du SMCIPN Services Intérim
        DetailSMEnchange detailSMEnchangeSMCIPN_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCIPN_Services_Interim, dTSMCIPN_Services_Interim.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_AutresSMCI_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Autres en SMCI Autres
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN Autres
        DetailSMEnchange dTSMCIPN_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMCI Autres
        DetailSMEnchange detailSMEnchangeSMCI_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCI_Autres, dTSMCIPN_Autres.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_AutresSMC_PN_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Autres en SMC PN Autres

        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN Autres
        DetailSMEnchange dTSMCIPN_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMC PN Autres
        DetailSMEnchange detailSMEnchangeSMC_PN_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMC_PN_Autres, dTSMCIPN_Autres.getId());

    }

    @Override
    public void mutationProcessusTSMCIPN_AutresSMCIPN_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation TSMCIPN Autres en SMCIPN Autres
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du TSMCIPN Autres
        DetailSMEnchange dTSMCIPN_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du SMCIPN Autres
        DetailSMEnchange detailSMEnchangeSMCIPN_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandSMCIPN_Autres, dTSMCIPN_Autres.getId());

    }

    @Override
    public void mutationProcessusSMCI_BudgetBAi_SMCI_Budget(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCI Budget en BAi SMCI Budget
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCI Budget
        DetailSMEnchange dSMCI_Budget = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BAi SMCI Budget
        DetailSMEnchange detailSMEnchangeBAi_SMCI_Budget = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCI_Budget, dSMCI_Budget.getId());

    }

    @Override
    public void mutationProcessusSMC_PN_AutresBAi_SMC_PN_Budget(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMC PN Autres en BAi SMC PN Budget
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMC PN Autres
        DetailSMEnchange dSMC_PN_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BAi SMC PN Budget
        DetailSMEnchange detailSMEnchangeBAi_SMC_PN_Budget = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMC_PN_Budget, dSMC_PN_Autres.getId());

    }

    @Override
    public void mutationProcessusSMCI_Budget_AnticipeBAi_SMCI_Budget_Anticipe(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCI Budget Anticipé en BAi SMCI Budget Anticipé
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCI Budget Anticipé
        DetailSMEnchange dSMCI_Budget_Anticipe = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BAi SMCI Budget Anticipé
        DetailSMEnchange detailSMEnchangeBAi_SMCI_Budget_Anticipe = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCI_Budget_Anticipe, dSMCI_Budget_Anticipe.getId());

    }

    @Override
    public void mutationProcessusSMC_PN_Budget_AnticipeBAi_SMC_PN_Budget_Anticipe(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMC PN Budget Anticipé en BAi SMC PN Budget Anticipé
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMC PN Budget Anticipé
        DetailSMEnchange dSMC_PN_Budget_Anticipe = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du SMC PN Budget Anticipé
        DetailSMEnchange detailSMEnchangeBAi_SMC_PN_Budget_Anticipe = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMC_PN_Budget_Anticipe, dSMC_PN_Budget_Anticipe.getId());

    }

    @Override
    public void mutationProcessusSMCI_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCI Services Intérim en BAi SMCI Services Intérim
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCI Services Intérim
        DetailSMEnchange dSMCI_Services_Interim = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BAi SMCI Services Intérim
        DetailSMEnchange detailSMEnchangeBAi_SMCI_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCI_Services_Interim, dSMCI_Services_Interim.getId());

    }

    @Override
    public void mutationProcessusSMC_PN_Services_InterimBAi_SMC_PN_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMC PN Services Intérim en BAi SMC PN Services Intérim
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMC PN Services Intérim
        DetailSMEnchange dSMC_PN_Services_Interim = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BAi SMC PN Services Intérim
        DetailSMEnchange detailSMEnchangeBAi_SMC_PN_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMC_PN_Services_Interim, dSMC_PN_Services_Interim.getId());

    }

    @Override
    public void mutationProcessusSMCIPN_Services_InterimBAi_SMCIPN_Services_Interim(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN Services Intérim en BAi SMCIPN Services Intérim
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCIPN Services Intérim
        DetailSMEnchange dSMCIPN_Services_Interim = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(),
                montant);

        // Creation du BAi SMCIPN Services Intérim
        DetailSMEnchange detailSMEnchangeBAi_SMCIPN_Services_Interim = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCIPN_Services_Interim, dSMCIPN_Services_Interim.getId());

    }

    @Override
    public void mutationProcessusSMCI_AutresBAi_SMCI_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCI Autres en BAi SMCI Autres
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCI Autres
        DetailSMEnchange dSMCI_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BAi SMCI Autres
        DetailSMEnchange detailSMEnchangeBAi_SMCI_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCI_Autres, dSMCI_Autres.getId());

    }

    @Override
    public void mutationProcessusSMC_PN_AutresBAi_SMCI_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMC PN Autres en BAi SMC PN Autres
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMC PN Autres
        DetailSMEnchange dSMC_PN_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BAi SMCI Autres
        DetailSMEnchange detailSMEnchangeBAi_SMCI_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCI_Autres, dSMC_PN_Autres.getId());

    }

    @Override
    public void mutationProcessusSMCIPN_AutresBAi_SMCIPN_Autres(Long idTe, Long id, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN Autres en BAi SMCIPN Autres
        DetailSMEnchange d = detailSMEnchangeRepository.findById(id).orElse(null);

        // Creation de Fonds de Sortie du SMCIPN Autres
        DetailSMEnchange dSMCIPN_Autres = this.createFondSortie(d.getTerminalEchange().getId(), d.getId(), montant);

        // Creation du BAi SMCIPN Autres
        DetailSMEnchange detailSMEnchangeBAi_SMCIPN_Autres = this.create(idTe, montant,
                SupportMarchandConstant.supportMarchandBAi_SMCIPN_Autres, dSMCIPN_Autres.getId());

    }

    private byte[] toByteArray(Double contents) throws IOException, WriterException {
        BarcodeFormat format = BarcodeFormat.CODE_39;

        int width = 180;
        int height = 40;

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            Code39Writer writer = new Code39Writer();
            BitMatrix bitMatrix = writer.encode(String.valueOf(contents), format, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, "png", output);
            return output.toByteArray();
        }
    }

    /*
     * /**
     *
     * @param idTe
     * 
     * @param montant
     *
     *
     * Processus d'Achat de Franchise ou Certification
     *
     */

    @Override
    public boolean checkBCiBlGpFondsEntre(Long idTe, Double montant) {

        boolean result = this.checkSmTotal(idTe, SupportMarchandConstant.supportMarchandBCi_BLGCp, montant);

        return result;
    }

    @Override
    public boolean checkSmFondsEntre(Long idTe, Double montant) {

        boolean result = this.checkSmTotal(idTe, SupportMarchandConstant.supportMarchandBCi_BLGCp, montant);

        return result;
    }

    @Override
    public List<DetailSMEnchange> listdSMFondsDisponible(Long idSm, Long idTe) {
        return detailSMEnchangeRepository.listdSMFondsDisponible(idSm, idTe);
    }

    @Override
    public DetailSMEnchange achatDesendettement(Long idTe) throws Exceptions, IOException, WriterException {

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.supportMarchandBCi_BLGCp).orElse(null);

        if (sm == null) {
            return null;
        }

        DetailSMEnchange dsmBlgcp = null;

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        DetailSMEnchange dsm = null;

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (smTotal > 0) {
            boolean result = this.checkSmTotal(idTe, sm.getId(), smTotal);

            if (result == true) {

                DetailSMEnchange dst = mutationFondDisponible(sm.getId(), idTe, smTotal);

                if (dst != null) {

                    DetailSMEnchange ds = this.createFondSortie(idTe, dst.getId(), smTotal);

                    dsmBlgcp = this.create(admin, ds.getFondsEntre(), SupportMarchandConstant.supportMarchandBLGCsc,
                            ds.getId());
                }

            } else {
                return null;
            }
        }

        return dsmBlgcp;
    }

    @Override
    public DetailSMEnchange achatFranchiseNormalByBciBlgp(Long idTe, Long idSm, String code)
            throws Exceptions, IOException, WriterException {

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSm).orElse(null);

        Formatter<Settings> settings = payementRestClient.getSettingUsedByTe(code);

        Settings montantFranchise = settings.getData();

        Double montant = Double.parseDouble(montantFranchise.getValue());

        if (sm == null) {
            return null;
        }

        DetailSMEnchange dsmBlgcp = null;

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        DetailSMEnchange dsm = null;

        DetailSMEnchange dsmf = null;

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (smTotal > 0 && montant <= smTotal) {

            DetailSMEnchange dst = mutationFondDisponible(sm.getId(), idTe, montant);

            if (dst != null) {

                dsmf = this.createFondSortie(idTe, dst.getId(), montant);

                dsmBlgcp = this.create(admin, montant, SupportMarchandConstant.supportMarchandBLGCsc, dsmf.getId());
            }

        } else {
            return null;
        }

        return dsmBlgcp;
    }

    @Override
    public DetailSMEnchange mutationProcessusBCnrPrk7(Long idTe, Long idSm, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN Services Intérim en BAi SMCIPN Services Intérim

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSm).orElse(null);

        if (sm == null) {
            return null;
        }

        DetailSMEnchange dst = null;

        DetailSMEnchange dsCreateBCnrPrk = null;

        DetailSMEnchange dtBCnrPrk = null;

        DetailSMEnchange dsBc = null;

        DetailSMEnchange dsBan = null;

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (smTotal > 0 && montant <= smTotal) {

            dst = mutationFondDisponible(sm.getId(), idTe, montant);

            if (dst != null) {

                dtBCnrPrk = this.createFondSortie(idTe, dst.getId(), montant);

                Double montantBcnr = terminalEchangeImpl.bcnrPrk7(montant);

                dsCreateBCnrPrk = this.create(idTe, montantBcnr, SupportMarchandConstant.supportMarchandBCnrPRK_6_5_8,
                        dtBCnrPrk.getId());
            }

        }

        return dsCreateBCnrPrk;

    }

    @Override
    public DetailSMEnchange mutationProcessusBan(Long idTe, Long idSm, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN Services Intérim en BAi SMCIPN Services Intérim

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSm).orElse(null);

        if (sm == null) {
            return null;
        }

        DetailSMEnchange dst = null;

        DetailSMEnchange dsCreateBCnrPrk = null;

        DetailSMEnchange dtBCnrPrk = null;

        DetailSMEnchange dsBc = null;

        DetailSMEnchange dsBan = null;

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (smTotal > 0 && montant <= smTotal) {

            dst = mutationFondDisponible(sm.getId(), idTe, montant);

            if (dst != null) {

                dsCreateBCnrPrk = this.create(idTe, montant, sm.getId(), dst.getId());

                DetailSMEnchange ds = detailSMEnchangeRepository.findById(dsCreateBCnrPrk.getId()).orElse(null);
                ds.setFondsSortie(-montant);
                ds.setFondsFondDisponible(ds.getFondsFondDisponible() - montant);
                dtBCnrPrk = detailSMEnchangeRepository.save(ds);

                dsBan = this.create(idTe, montant, SupportMarchandConstant.QsupportMarchandBan, dtBCnrPrk.getId());

            }

        }

        return dsBan;

    }

    @Override
    public DetailSMEnchange mutationSMProcessus(Long idTe, Long idSmMuttan, Long idSmMutter, Double montant)
            throws Exceptions, IOException, WriterException {
        // Processus Mutation SMCIPN Services Intérim en BAi SMCIPN Services Intérim

        // Valeur Felm
        Formatter<Settings> stMabanfz = payementRestClient.getSettingUsedByTe("PARAM_MABAn_Zero");
        Settings settingstMabanfz = stMabanfz.getData();

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSmMuttan).orElse(null);

        if (sm == null) {
            return null;
        }

        DetailSMEnchange dsmMuttan = null;
        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (smTotal > 0 && montant <= smTotal) {

            DetailSMEnchange dst = mutationFondDisponible(idSmMuttan, idTe, montant);

            if(dst != null) {
              if (Objects.equals(idSmMutter, SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5)) {

                    DetailSMEnchange dsl5_6_PRK_6_5 = this.createFondSortie(idTe, dst.getId(), montant);

                    Double montantBcnrl_5_6_PRK_6_5 = terminalEchangeImpl.bcnrPrk(montant);

                    return dsmMuttan = this.create(idTe, montantBcnrl_5_6_PRK_6_5, idSmMutter, dsl5_6_PRK_6_5.getId());

                } else if (Objects.equals(idSmMutter, SupportMarchandConstant.supportMarchandBCnrPRK_6_5_8)) {
                    DetailSMEnchange dslPRK_6_5_8 = this.createFondSortie(idTe, dst.getId(), montant);

                    Double montantBcnrl_PRK_6_5_8 = terminalEchangeImpl.bcnrPrk7(montant);

                    return dsmMuttan = this.create(idTe, montantBcnrl_PRK_6_5_8, idSmMutter, dslPRK_6_5_8.getId());

             } else if (Objects.equals(idSmMutter, SupportMarchandConstant.supportMarchandMABAn_FZ)) {
                  Double smTotalMa = detailSMEnchangeRepository.sommeTotalSm(SupportMarchandConstant.supportMarchandMABAn_FZ, idTe);
                  DetailSMEnchange maban_fz = this.createFondSortie(idTe, dst.getId(), montant);
                  Double montantParam = Double.parseDouble(settingstMabanfz.getValue());
                  Double montantMaban = montant * montantParam;
                  Double verify = smTotalMa + montantMaban;
                  Double quota = Double.parseDouble("1100000000000");
                  if (verify <= quota) {
                      return this.create(idTe, montantMaban, SupportMarchandConstant.supportMarchandMABAn_FZ, maban_fz.getId());
                  } else {
                      return null;
                  }
              } else  {
                  DetailSMEnchange ds = null;
                      ds = this.createFondSortie(idTe, dst.getId(), montant);
                  return   this.create(idTe, montant, idSmMutter, ds.getId());
              }
            } else {
                return null;
            }

        }
        return dsmMuttan;

    }

    @Override
    public Double amountBciTe(Long idTe) {

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.supportMarchandBCi_BLGCp).orElse(null);

        if (sm == null) {
            return 0.0;
        }

        Double smTotal = detailSMEnchangeRepository.sommeTotalSmBci(sm.getId(), idTe);
        List<DetailSMEnchange> detailSMEnchange = detailSMEnchangeRepository.listDetailSmByIdSmAndIdTe(sm.getId(), idTe);

        if (detailSMEnchange.size() > 0) {
            for (DetailSMEnchange d : detailSMEnchange) {
                d.setUsed(true);
                detailSMEnchangeRepository.save(d);
            }
        }

        return smTotal;
    }

    @Override
    public Double checkSmTotalCurrent(Long idSm, Long idTe) {

        Double sommeTotal = detailSMEnchangeRepository.sommeTotalSmBan(idSm, idTe);

        return sommeTotal;

    }

    @Override
    public boolean checkSmTotal(Long idTe, Long idSm, Double montant) {

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSm).orElse(null);

        if (sm == null) {
            return false;
        }

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTe);

        if (montant <= smTotal) {
            return true;
        }
        return false;
    }

    @Override
    public DetailSMEnchange mutationPourAchatFifoAdmin(Long idTeAcheteur, Long idTeVendeur, Double montant)
            throws Exceptions, IOException, WriterException {

        DetailSMEnchange dsme = null;

        DetailSMEnchange dt = null;

        DetailSMEnchange dBLGBpV = null;

        // Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.QsupportMarchandBan).orElse(null);

        if (sm == null) {
            return null;
        }

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTeAcheteur);

        // boolean resultMontantSm = this.checkSmTotal(idTeAcheteur, sm.getId(),
        // montant);

        if (smTotal > 0 && montant <= smTotal) {

            DetailSMEnchange ds = mutationFondDisponible(sm.getId(), idTeAcheteur, montant);

            // dsme = this.create(idTeAcheteur, montant, sm.getId(), ds.getId());
            //
            // DetailSMEnchange detailSMEnchange =
            // detailSMEnchangeRepository.findById(dsme.getId()).orElse(null);

            if (ds != null) {
                dt = this.createFondSortie(idTeAcheteur, ds.getId(), montant);

                // Creation de BCnr
                DetailSMEnchange dBCnr = this.create(idTeAcheteur, dt.getFondsEntre(),
                        SupportMarchandConstant.QsupportMarchandBCnr, dt.getId());

                // Creation de Fonds de sortie de BCnr
                DetailSMEnchange dBCnrFond = this.createFondSortie(idTeAcheteur, dBCnr.getId(), dBCnr.getFondsEntre());

                // Montant BCnrPRK

                Double montantPRM = terminalEchangeImpl.bcnrPrk(dBCnrFond.getFondsEntre());

                Double montantRenitialiserBCI = 0.0;
                montantRenitialiserBCI = (montantPRM / 2187.5) * 70000;

                DetailSMEnchange dBCnrPRk = this.create(idTeAcheteur, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, dBCnrFond.getId());

                // Creation de Fonds de sortie de BCnr PRK
                DetailSMEnchange dBCnrPRKFonds = this.createFondSortie(idTeAcheteur, dBCnrPRk.getId(),
                        dBCnrPRk.getFondsEntre());

                // Creation de BLGCp Vendeur
                dBLGBpV = this.create(idTeVendeur, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBLGCsc, dBCnrFond.getId());

            }

        } else {
            return null;
        }
        return dBLGBpV;
    }

    @Override
    public DetailSMEnchange mutationPourAchatFifo(Long idTeAcheteur, Long idTeVendeur, Double montant)
            throws Exceptions, IOException, WriterException {

        DetailSMEnchange dsme = null;

        DetailSMEnchange dt = null;

        DetailSMEnchange dBLGBpV = null;

        DetailSMEnchange deMPRgOPI = null;

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.QsupportMarchandBan).orElse(null);

        if (sm == null) {
            return null;
        }

        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTeAcheteur);

        // boolean resultMontantSm = this.checkSmTotal(idTeAcheteur, sm.getId(),
        // montant);

        if (smTotal > 0 && montant <= smTotal) {


            DetailSMEnchange ds = mutationFondDisponible(sm.getId(), idTeAcheteur, montant);


            // dsme = this.create(idTeAcheteur, montant, sm.getId(), ds.getId());
            //
            // DetailSMEnchange detailSMEnchange =
            // detailSMEnchangeRepository.findById(dsme.getId()).orElse(null);

            if (ds != null) {
                dt = this.createFondSortie(idTeAcheteur, ds.getId(), montant);

                // Creation de BCnr
                DetailSMEnchange dBCnr = this.create(idTeAcheteur, montant,
                        SupportMarchandConstant.QsupportMarchandBCnr, dt.getId());
                dBCnr.setFondsFondDisponible(0.0);
                detailSMEnchangeRepository.save(dBCnr);

                // Creation de Fonds de sortie de BCnr
                DetailSMEnchange dBCnrFond = this.createFondSortie(idTeAcheteur, dBCnr.getId(), montant);

                // Montant BCnrPRK

                Double montantPRM = terminalEchangeImpl.bcnrPrk(montant);

                Double montantRenitialiserBCI = 0.0;
                montantRenitialiserBCI = (montantPRM / 2187.5) * 70000;

                DetailSMEnchange dBCnrPRk = this.create(idTeAcheteur, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5, dBCnrFond.getId());
                dBCnrPRk.setFondsFondDisponible(0.0);
                detailSMEnchangeRepository.save(dBCnrPRk);

                // Creation de Fonds de sortie de BCnr PRK
                DetailSMEnchange dBCnrPRKFonds = this.createFondSortie(idTeAcheteur, dBCnrPRk.getId(),
                        montantRenitialiserBCI);

                // Creation de BLGCp Vendeur
                dBLGBpV = this.create(idTeVendeur, montantRenitialiserBCI, SupportMarchandConstant.supportMarchandBLGCp,
                        dBCnrFond.getId());
                dBLGBpV.setFondsFondDisponible(0.0);
                detailSMEnchangeRepository.save(dBLGBpV);

                DetailSMEnchange dBLGCpSortie = this.createFondSortie(idTeVendeur, dBLGBpV.getId(),
                        montantRenitialiserBCI);

                Ordre ordre = this.createOPi(SupportMarchandConstant.supportMarchandOPI, idTeVendeur,
                        dBLGCpSortie.getId(), montantRenitialiserBCI);
                ordre.setFondsFondDisponible(0.0);
                ordreRepository.save(ordre);

                deMPRgOPI = this.create(idTeVendeur, montantRenitialiserBCI,
                        SupportMarchandConstant.supportMarchandMPRg_OPI, ordre.getId());
            }

        } else {
            return null;
        }
        return deMPRgOPI;
    }

    public DetailSMEnchange mutationFondDisponible(Long idSm, Long idTe, Double montant) {

        DetailSMEnchange ds = null;

        Double montantGlobal = montant;

        List<DetailSMEnchange> listD = detailSMEnchangeRepository.listdSMFondsDisponible(idSm, idTe);

        if (listD.size() > 0) {

            for (DetailSMEnchange d : listD) {
                if (d.getFondsFondDisponible() <= montantGlobal) {
                    montantGlobal = montantGlobal - d.getFondsFondDisponible();
                    d.setFondsSortie(-d.getFondsEntre());
                    d.setFondsFondDisponible(0.0);
                    ds = detailSMEnchangeRepository.save(d);
                } else {
                    Double result = d.getFondsFondDisponible() - montantGlobal;
                    d.setFondsSortie(d.getFondsSortie() - montantGlobal);
                    d.setFondsFondDisponible(result);
                    ds = detailSMEnchangeRepository.save(d);
                    break;
                }
            }

        }
        return ds;
    }

    @Override
    public DetailSMEnchange mutationFondDisponibleUsed(Long idTe, Double montant) {
        DetailSMEnchange ds = null;

        Double montantGlobal = montant;

        SupportMarchandEnchage sm = supportMarchandEnchageRepository
                .findById(SupportMarchandConstant.supportMarchandBCi_BLGCp).orElse(null);

        List<DetailSMEnchange> listD = detailSMEnchangeRepository.listdSMFondsDisponibleUsed(sm.getId(), idTe);

        if (listD.size() > 0) {

            for (DetailSMEnchange d : listD) {
                if (d.getFondsFondDisponible() <= montantGlobal) {
                    montantGlobal = montantGlobal - d.getFondsFondDisponible();
                    d.setFondsSortie(-d.getFondsEntre());
                    d.setFondsFondDisponible(0.0);
                    ds = detailSMEnchangeRepository.save(d);
                } else {
                    Double result = d.getFondsFondDisponible() - montantGlobal;
                    montantGlobal = 0.0;
                    d.setFondsSortie(-d.getFondsEntre());
                    d.setFondsFondDisponible(result);
                    ds = detailSMEnchangeRepository.save(d);
                    break;
                }
            }

        }
        return ds;
    }

    @Override
    public Double sommeSm(Long idTe) {
        return detailSMEnchangeRepository.sommeTotalSmProduit(SupportMarchandConstant.supportMarchandBCi_BLGCp, idTe);
    }

    @Override
    public List<DetailSMEnchange> listDetailSm(Long idTe) {
        return detailSMEnchangeRepository.listBPSD(idTe);
    }

    @Override
    public List<DetailSMEnchange> listSm(Long idSm, Long idTe) {
        return detailSMEnchangeRepository.listdSM(idSm, idTe);
    }

    @Override
    public DetailSMEnchange depotAInterim(Long idIdSm, Long idTeEmeteur, Long idteDestinateur, Double montant)
            throws Exceptions, IOException, WriterException {

        DetailSMEnchange dsme = null;
        DetailSMEnchange dt = null;
        DetailSMEnchange dSm = null;
        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idIdSm).orElse(null);
        if (sm == null) {
            return null;
        }
        Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(), idTeEmeteur);
        if (smTotal > 0) {
            DetailSMEnchange ds = mutationFondDisponible(sm.getId(), idTeEmeteur, montant);
            if (ds != null) {
                dsme = this.createFondSortie(idTeEmeteur, ds.getId(), montant);
                // Creation de SM au niveau de l'iterim
                dSm = this.create(idteDestinateur, montant, idIdSm, dsme.getId());
            }
        } else {
            return null;
        }
        return dSm;
    }

    @Override
    public DetailSMEnchange depotAInterimAchatKsu(Long idIdSm, Long idteDestinateur, Double montant, Long refer)
            throws Exceptions, IOException, WriterException {

        DetailSMEnchange dsme = null;
        DetailSMEnchange dt = null;
        DetailSMEnchange dSm = null;
        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idIdSm).orElse(null);
        if (sm == null) {
            return null;
        }
        // Double smTotal = detailSMEnchangeRepository.sommeTotalSm(sm.getId(),
        // idTeEmeteur);
        // if (smTotal > 0) {
        // DetailSMEnchange ds = mutationFondDisponible(sm.getId(), idTeEmeteur,
        // montant);
        // if (ds != null) {
        // dsme = this.createFondSortie(idTeEmeteur, ds.getId(), montant);
        // Creation de SM au niveau de l'iterim
        dSm = this.create(idteDestinateur, montant, idIdSm, refer);
        // }
        // } else {
        // return null;
        // }
        return dSm;
    }

    @Override
    public Double sommeTotalSm(Long idSm, Long idTe) {
        return detailSMEnchangeRepository.sommeTotalSmProduit(idSm, idTe);
    }

    /// listSmMuter
    @Override
    public List<DetailSMEnchange> listSmMuter(Long idTE, Long idSM) {
        return detailSMEnchangeRepository.listSmMuter(idTE, idSM);
    }

    // ***** TE - Acheteur || TE - Vendeurbv
    // create by James

    @Override
    public DetailSMEnchange acheteurVendeur(Long idTEAcheteur, Long idSMAcheteur,
            Long idTEVendeur, double montant) throws IOException, WriterException, Exceptions {
        DetailSMEnchange dsme = null;
        DetailSMEnchange dt = null;
        DetailSMEnchange dSm = null;
        DetailSMEnchange dSmVendeur = null;
        // Get SM Acheteur
        SupportMarchandEnchage smAcheteur = supportMarchandEnchageRepository.findById(idSMAcheteur).orElse(null);
        // Get somme total acheteur
        Double smTotalAcheteur = detailSMEnchangeRepository.sommeTotalSm(smAcheteur.getId(), idTEAcheteur);

        if (smTotalAcheteur > 0) {
            DetailSMEnchange ds = mutationFondDisponible(smAcheteur.getId(), idTEAcheteur, montant);
            if (ds != null) {
                dsme = this.createFondSortie(idTEAcheteur, ds.getId(), montant);
                // Creation de SM au niveau du vendeur
                dSm = this.create(idTEVendeur, montant, idSMAcheteur, dsme.getId());
            }
        } else {
            return null;
        }
        return dSm;
    }

    @Override
    public DetailSMEnchange vendeurAcheteur(Long idTEAcheteur, Long idSMVendeur,
            Long idTEVendeur, double montant) throws IOException, WriterException, Exceptions {
        DetailSMEnchange dsme = null;
        DetailSMEnchange dt = null;
        DetailSMEnchange dSm = null;
        DetailSMEnchange dSmVendeur = null;
        // Get SM Acheteur
        SupportMarchandEnchage smVendeur = supportMarchandEnchageRepository.findById(idSMVendeur).orElse(null);
        // Get somme total acheteur
        Double smTotalVendeur = detailSMEnchangeRepository.sommeTotalSm(smVendeur.getId(), idTEVendeur);

        if (smTotalVendeur > 0) {
            DetailSMEnchange ds = mutationFondDisponible(smVendeur.getId(), idTEVendeur, montant);
            if (ds != null) {
                dsme = this.createFondSortie(idTEVendeur, ds.getId(), montant);
                // Creation de SM au niveau du vendeur
                dSm = this.create(idTEAcheteur, montant, idSMVendeur, dsme.getId());
            }
        } else {
            return null;
        }
        return dSm;
    }

    @Override
    public boolean supply(Long idTEAcheteur, Long idSMVendeur, Long idTEVendeur,
            Long idSMAcheteur, double montant)
            throws IOException, WriterException, Exceptions {

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSMAcheteur).orElse(null);
        DetailsAgr detailsAgr = detailAgrRestClient.getDetailById(idTEVendeur);

        DetailSMEnchange dSMV = null;
        DetailSMEnchange dSMA = null;
    //    DetailSMEnchange detailSMEnchangeMABAn = null;
        Double montantMABAn = terminalEchangeImpl.margeMABAn(montant);

        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

            if (detailsAgr.isFranchise() && detailsAgr.getId() != admin) {
                dSMV = this.acheteurVendeur(idTEAcheteur, idSMAcheteur, idTEVendeur, montant);
                dSMA = this.vendeurAcheteur(idTEAcheteur, idSMVendeur, admin, montantMABAn);
                dSMA.setIdEtiquette(Long.parseLong(EtiquetteConstant.etiquetteAppro.replace("L", "")));
                if (dSMA.getSupportMarchandEnchage().equals(SupportMarchandConstant.supportMarchandMABAn)) {
                    detailSMEnchangeRepository.save(dSMA);
                    avrRestClient.createSupportMarchandAvr(idTEVendeur, 1, idTEAcheteur, montant, sm.getLibelle());
                }
            } else {

                DetailSMEnchange detailBPSDVendeur = null;

                Double somTotale = this.sommeTotalSm(idSMAcheteur, idTEAcheteur);
                if ((somTotale > 0 && montant <= somTotale)) {
                    DetailSMEnchange dst = this.mutationFondDisponible(idSMAcheteur, idTEAcheteur, montant);

                    if (dst != null) {
                        // Creation de la sortie chez l'acheteur
                        DetailSMEnchange detailSMEnchange = this.createFondSortie(idTEAcheteur, dst.getId(),  montant);

                        // Creation de BPSD chez le franchisé Zéro
                        detailBPSDVendeur = this.create(admin, montant, idSMAcheteur, detailSMEnchange.getId());
                        detailBPSDVendeur.setFondsSortie(-montant);
                        detailBPSDVendeur.setFondsFondDisponible(0.0);
                        detailSMEnchangeRepository.save(detailBPSDVendeur);

                        // Creation de BPSD Fond Sortie chez le franchisé Zéro
                        DetailSMEnchange detailBPSDVendeurFondS = this.createFondSortie(admin, detailBPSDVendeur.getId(), montant);

                        // Creation de MABAn chez le franchisé Zéro
                        DetailSMEnchange MABAn = this.create(admin,  montant, SupportMarchandConstant.supportMarchandMABAn, detailBPSDVendeurFondS.getId());
                        MABAn.setFondsSortie(-montant);
                        MABAn.setFondsFondDisponible(0.0);
                        detailSMEnchangeRepository.save(MABAn);

                        // Creation de MABAn Fond Sortie chez le franchisé Zéro
                        DetailSMEnchange MABAnFondSortie = this.createFondSortie(admin, MABAn.getId(), montant);


                        if (Objects.equals(idSMVendeur, SupportMarchandConstant.supportMarchandMABAn)) {
                            // Creation de MABAn chez le franchisé Acheteur
                           Double montantMarge = terminalEchangeImpl.margeMABAn(montant) + montant;
                            DetailSMEnchange MABAnF = this.create(idTEAcheteur,  montantMarge, SupportMarchandConstant.supportMarchandMABAn, MABAnFondSortie.getId());
                            Long etiq = Long.valueOf(EtiquetteConstant.etiquetteAppro.replace("L", ""));
                            MABAnF.setIdEtiquette(etiq);
                            detailSMEnchangeRepository.save(MABAnF);
                            return true;
                        }
                        // Creation de BAn chez l'acheteur
                        DetailSMEnchange MABAnF = this.create(idTEAcheteur,  montant, SupportMarchandConstant.QsupportMarchandBan, MABAnFondSortie.getId());

                    }

                }

            }
            return true;
    }


    @Override
    public DetailSMEnchange debitSm(Long idSm, Long idTeAcheteur, Long idTeVendeur, Double montant)
            throws IOException, WriterException, Exceptions {
        DetailSMEnchange dsme = null;
        DetailSMEnchange dt = null;
        DetailSMEnchange dSm = null;
        DetailSMEnchange dSmVendeur = null;
        // Get SM Acheteur
        SupportMarchandEnchage smVendeur = supportMarchandEnchageRepository.findById(idSm).orElse(null);
        // Get somme total acheteur
        Double smTotalVendeur = detailSMEnchangeRepository.sommeTotalSm(smVendeur.getId(), idTeAcheteur);

        if (smTotalVendeur > 0) {
            DetailSMEnchange ds = mutationFondDisponible(smVendeur.getId(), idTeAcheteur, montant);
            if (ds != null) {
                dsme = this.createFondSortie(idTeAcheteur, ds.getId(), montant);
                // Creation de SM au niveau du vendeur
                dSm = this.create(idTeVendeur, montant, idSm, dsme.getId());
            }
        } else {
            return null;
        }
        return dSm;
    }

    @Override
    public DetailSMEnchange achatCertifSm(Long idSm, Long idTeAcheteur, Long idTeVendeur, Double montant)
            throws Exceptions, IOException, WriterException {
        DetailSMEnchange detailSMEnchange = null;

        SupportMarchandEnchage sm = supportMarchandEnchageRepository.findById(idSm).orElse(null);
        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        depotAInterim(idSm, idTeAcheteur, admin, montant);
        // avrRestClient.createSupportMarchandAvr(idTeVendeur, 1, idTeAcheteur,
        // montant,sm.getLibelle());
        detailSMEnchange = depotAInterim(idSm, admin, idTeVendeur, montant);
        return detailSMEnchange;
    }

    @Override
    public DetailSMEnchange achatCertifSmAchatKsu(Long idSm, Double montant, Long refer)
            throws Exceptions, IOException, WriterException {

        DetailSMEnchange detailSMEnchange = null;

        // SupportMarchandEnchage sm =
        // supportMarchandEnchageRepository.findById(idSm).orElse(null);
        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        detailSMEnchange = depotAInterimAchatKsu(idSm, admin, montant, refer);

        return detailSMEnchange;
    }

    @Override
    public DetailSMEnchange payementVandeurKsu(PayerVendeurKsu data) throws IOException, WriterException, Exceptions {

        BanKsu b = achatKsuClientRest.getBanKsuByCodeBan(data.getCodeBanKsu());

        if (b == null) {
            return null;
        }

        DetailSMEnchange detagcp = null;
        DetailSMEnchange det = null;
        DetailSMEnchange detailSMEnchange = detailSMEnchangeRepository.getSmFromInterim(b.getId());
        Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

        if (data.getIdDetailAgrFranchise() == null) {
            DetailSMEnchange fondSortie = this.createFondSortie(admin, detailSMEnchange.getId(),
                    detailSMEnchange.getTotal());
            det = this.create(admin, fondSortie.getTotal(), SupportMarchandConstant.QsupportMarchandBCnr,
                    fondSortie.getId());
            DetailSMEnchange fondSortieBcnr = this.createFondSortie(admin, det.getId(), detailSMEnchange.getTotal());
            Double montant = terminalEchangeImpl.bcnrPrk(fondSortieBcnr.getTotal());
            DetailSMEnchange deta = this.create(admin, montant, SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5,
                    fondSortieBcnr.getId());
            DetailSMEnchange fondSortieBcnr5 = this.createFondSortie(admin, deta.getId(), deta.getTotal());
            detagcp = this.create(admin, montant, SupportMarchandConstant.supportMarchandBLGCp,
                    fondSortieBcnr5.getId());

        } else {
            DetailSMEnchange fondSortie = this.createFondSortie(admin, detailSMEnchange.getId(),
                    detailSMEnchange.getTotal());
            det = this.create(admin, fondSortie.getTotal(), SupportMarchandConstant.QsupportMarchandBCnr,
                    fondSortie.getId());
            DetailSMEnchange fondSortieBcnr = this.createFondSortie(admin, det.getId(), detailSMEnchange.getTotal());
            Double montant = terminalEchangeImpl.bcnrPrk(fondSortieBcnr.getTotal());
            DetailSMEnchange deta = this.create(admin, montant, SupportMarchandConstant.supportMarchandBCnr5_6_PRK_6_5,
                    fondSortieBcnr.getId());
            DetailSMEnchange fondSortieBcnr5 = this.createFondSortie(admin, deta.getId(), deta.getTotal());
            detagcp = this.create(admin, montant, SupportMarchandConstant.supportMarchandBLGCp,
                    fondSortieBcnr5.getId());

        }
        return detagcp;
    }

    @Override
    public DetailSMEnchange approBAnByPassif(Long idTe, int type) throws Exceptions, IOException, WriterException {

        // Valeur Felm
        Formatter<Settings> stFelm = payementRestClient.getSettingUsedByTe("felmBan");
        Settings settingsFelm = stFelm.getData();
        Double valeurApproFelm = terminalEchangeImpl.margeMABAn(Double.parseDouble(settingsFelm.getValue())) + Double.parseDouble(settingsFelm.getValue()) ;

        // Valeur Flm
        Formatter<Settings> stFlb = payementRestClient.getSettingUsedByTe("flbBan");
        Settings settingFlb = stFlb.getData();
        Double valeurApproFlb = terminalEchangeImpl.margeMABAn(Double.parseDouble(settingFlb.getValue())) + Double.parseDouble(settingFlb.getValue());

        // Valeur Fil
        Formatter<Settings> stFil = payementRestClient.getSettingUsedByTe("filBan");
        Settings settingFil = stFil.getData();
        Double valeurApproFil = terminalEchangeImpl.margeMABAn(Double.parseDouble(settingFil.getValue())) + Double.parseDouble(settingFil.getValue());

        DetailSMEnchange detailSMEnchange = null;

        switch (type) {
            case 3:
                // Appro MABan Felm
                detailSMEnchange = this.create(idTe, valeurApproFelm,
                        SupportMarchandConstant.supportMarchandMABAn, null);
                detailSMEnchange.setIdEtiquette(1L);
                detailSMEnchangeRepository.save(detailSMEnchange);
                break;
            case 4:
                // Appro MABan Flb
                detailSMEnchange = this.create(idTe, valeurApproFlb,
                        SupportMarchandConstant.supportMarchandMABAn, null);
                detailSMEnchange.setIdEtiquette(1L);
                detailSMEnchangeRepository.save(detailSMEnchange);
                break;
            case 5:
                // Appro MABan Fil
                detailSMEnchange = this.create(idTe, valeurApproFil,
                        SupportMarchandConstant.supportMarchandMABAn, null);
                detailSMEnchange.setIdEtiquette(1L);
                detailSMEnchangeRepository.save(detailSMEnchange);
                break;
            default:
                System.out.println("Error");
                break;
        }

        return detailSMEnchange;
    }

}
