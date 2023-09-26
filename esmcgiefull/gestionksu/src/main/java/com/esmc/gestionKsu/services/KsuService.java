package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.*;
import com.esmc.gestionKsu.feign.AgrRestClient;
import com.esmc.gestionKsu.feign.MaBanKsuRestClient;
import com.esmc.gestionKsu.feign.TeRestClient;
import com.esmc.gestionKsu.inputs.NetworkActivationInput;
import com.esmc.gestionKsu.repositories.KsuRepository;
import com.esmc.gestionKsu.serviceinterfaces.ConventionServiceInterface;
import com.esmc.gestionKsu.serviceinterfaces.KsuServiceInterface;
import com.esmc.models.MaBanKsu;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import utiles.Messagerie;
import utiles.UseFullFunctions;

import java.text.DecimalFormat;
import java.util.*;

import static constants.AccountConstant.TypeKsuPP;
import static constants.AccountConstant.TypeKsuPPM;

/**
 * {@link Ksu} service
 *
 * @author katoh <katohdavid@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class KsuService extends Messagerie implements KsuServiceInterface {

    String ipadresse = "http://localhost:8888/SECURITY-SERVICE/";
    @Autowired
    private KsuRepository ksuRepository;
    @Autowired
    private PieceIdentiteService pieceIdentiteService;
    @Autowired
    private AbonnementService abonnementService;
    @Autowired
    private SecteurService secteurService;
    @Autowired
    private BanqueService banqueService;
    @Autowired
    private TypePMService typePMService;
    @Autowired
    private TypeOperateurService typeOperateurService;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private MaBanKsuRestClient maBanKsuRestClient;
    @Autowired
    private TeRestClient teRestClient;
    @Autowired
    private AgrRestClient agrRestClient;
    @Autowired
    private ConventionServiceInterface conventionServiceInterface;
    private UseFullFunctions useFullFunctions = new UseFullFunctions();

    @Override
    public void save(Ksu ksu) {
        //Code
        Long random1 = new Random().nextLong();

        //format
        DecimalFormat formatter = new DecimalFormat("00000000000000000000000K");
        /*String code= formatter.format(Long.valueOf(random1))+"0";
        while (random1<0||codeKsuIsPresent(code)) {
            random1 = new Random().nextLong();
            code= formatter.format(Long.valueOf(random1));
        }*/

        //setting data
        ksu.setDateCreate(new Date());
        //ksu=refactorKsuObject(ksu);
        ksu.setCodeBanKsu("jgkfgjkg");

        //save
        Ksu ksu1 = ksuRepository.save(ksu);

        String codeksu = ksu1.getCodeKsu();
        ksu1.setCodeKsu(formatter.format(ksu1.getId()));
        System.out.println(ksu1.getCodeKsu());

    }

    @Override
    public void update(Ksu ksu) {


        Ksu ks1 = getById(ksu.getId());

        //setting data
        ksu = refactorKsuObject(ksu);
        ksu.setCodeKsu(ks1.getCodeKsu());
        ksu.setDateUpdate(new Date());

        //update
        Ksu ksu1 = ksuRepository.save(ksu);

    }

    /**
     * Setting data
     *
     * @param ksu
     * @return
     */
    private Ksu refactorKsuObject(Ksu ksu) {

        //getting data
        Secteur secteur = secteurService.getById(ksu.getSecteur().getId());
        Abonnement abonnement = abonnementService.getById(ksu.getAbonnement().getId());
        PieceIdentite pieceIdentite = pieceIdentiteService.getById(ksu.getPieceIdentite().getId());
        TypePM typePM = typePMService.getById(ksu.getTypePM().getId());
        TypeOperateur typeOperateur = typeOperateurService.getById(ksu.getTypeOperateur().getId());
        Banque banque = banqueService.getBanqueById(ksu.getBanque().getId());
        // BanKsu banKsu=(BanKsu) banKsuClient.getById(ksu.getBanKsu().getId()).getBody();

        //setting data
        ksu.setAbonnement(abonnement);
        ksu.setBanque(banque);
        ksu.setPieceIdentite(pieceIdentite);
        ksu.setTypePM(typePM);
        ksu.setTypeOperateur(typeOperateur);
        ksu.setSecteur(secteur);

        return ksu;
    }

    @Override
    public Ksu getById(Long id) {
        return ksuRepository.findById(id).orElse(null);
    }

/*    @Override
    public Ksu ksuParMaBanKsu(Long id) {

        Ksu k = ksuRepository.findById(id).orElse(null);

        return ksuRepository.findKsuByMaBanKsu(id);
    }*/

    @Override
    public PagedModel<EntityModel<Ksu>> getAll(Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findAll(page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    /**
     * @return
     */
    @Override
    public Ksu currentKsu() {
        return ksuRepository.findKsuByIdMax();
    }

    /**
     * @param k
     * @return
     */
    @Override
    public Ksu addKsu(Long idtype, Long idMaBanKsu, Ksu k) {

        Ksu ks = ksuRepository.ksuByCodeBAn(k.getCodeBanKsu());


        Long idCentreFranchiseZero = 142L;

        Ksu ksu = null;

        if (ks == null) {
            //format
            DecimalFormat formatterP = new DecimalFormat("0000000000000000000000P");
            DecimalFormat formatterM = new DecimalFormat("0000000000000000000000M");

            Integer codeKsuPP = ksuRepository.codeKsuPP() + 1;
            Integer codeKsuPM = ksuRepository.codeKsuPM() + 1;

            MaBanKsu ma = maBanKsuRestClient.mabanKsu(idMaBanKsu);

            Ksu kma = ksuRepository.ksuByMaBanKsu(ma.getId());

            Ksu ksus = this.ksuParCodeKsu(ma.getCodeKsuRepresentant());

            if (kma != null) {
                return null;
            }

            String message = "Votre Kit de Solvabilité Universel(KSU) a été acheté avec succès. \n Veuillez vous présenter dans une Agence la plus proche pour établir votre carte d'accès au KSU  ";

            String imageFile = k.getPhotoPieceId();
            if (imageFile != null) {
                k.setPhotoPieceId(useFullFunctions.getUploadImageByBase64(k.getPhotoPieceId()));
            }

            if (Objects.equals(idtype, TypeKsuPP) || Objects.equals(idtype, TypeKsuPPM)) {
                k.setIdmaBanKsu(ma.getId());
                k.setNom(ma.getNom());
                k.setPrenom(ma.getPrenom());
                k.setAdresse(ma.getAdresse());
                k.setDenomination(ma.getDenomination());
                k.setBoitePostale(ma.getBoitePostale());
                k.setCodeKsuRepresentant(ma.getCodeKsuRepresentant());
                k.setEmail(ma.getEmail());
                k.setTelephone(ma.getTelephone());
                k.setStatusJuridique(ma.getStatusJuridique());
                k.setCodeKsu(formatterP.format(codeKsuPP));
                if (k.getIdCanton() == null) {
                    k.setIdCanton(idCentreFranchiseZero);
                } else {
                    k.setIdCanton(k.getIdCanton());
                }
                ksu = ksuRepository.save(k);
                agrRestClient.affectationAgrAuKsu(idtype, ksu.getId());
                teRestClient.affectationTeParAgr(ksu.getId());
                conventionServiceInterface.saveConvention(ksu.getId());
                sendSms(ma.getTelephone(), message);
                //sendMail(k.getEmail(), "ESMC GIE", message);
            } else {
                k.setIdmaBanKsu(ma.getId());
                k.setNom(ksus.getNom());
                k.setPrenom(ksus.getPrenom());
                k.setAdresse(ma.getAdresse());
                k.setRaisonSocial(ma.getRaisonSocial());
                k.setBoitePostale(ma.getBoitePostale());
                k.setCodeKsuRepresentant(ma.getCodeKsuRepresentant());
                k.setEmail(ma.getEmail());
                k.setTelephone(ma.getTelephone());
                k.setStatusJuridique(ma.getStatusJuridique());
                k.setCodeKsu(formatterM.format(codeKsuPM));
                if (k.getIdCanton() == null) {
                    k.setIdCanton(idCentreFranchiseZero);
                } else {
                    k.setIdCanton(k.getIdCanton());
                }
                ksu = ksuRepository.save(k);
                agrRestClient.affectationAgrAuKsu(idtype, ksu.getId());
                teRestClient.affectationTeParAgr(ksu.getId());
                conventionServiceInterface.saveConvention(ksu.getId());
                sendSms(ma.getTelephone(), message);
                //sendMail(k.getEmail(), "ESMC GIE", message);
            }
        } else {
            return null;
        }
        return ksu;
    }

    /**
     * @param code
     * @return Ksu en fonction de CodeKsu
     */
    @Override
    public Ksu ksuParCodeKsu(String code) {
        return ksuRepository.ksuByCodeKsu(code);
    }

    /**
     * @param
     * @return
     */
    @Override
    public Ksu ksuByMaBanKsu(Long id) {
        return ksuRepository.ksuByMaBanKsu(id);
    }

    @Override
    public Ksu ksuByInfo(String firstName, String lastName, String numero) {
        return ksuRepository.ksuByInfo(firstName, lastName, numero);
    }

    @Override
    public Ksu ksuByInfoPM(String raisonSocial, String numero) {
        return ksuRepository.ksuByInfoPM(raisonSocial, numero);
    }

    @Override
    public PagedModel<EntityModel<Ksu>> getByPieceIdentite(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findByPieceIdentite(id, page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    @Override
    public PagedModel<EntityModel<Ksu>> getByAbonnement(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findByAbonnement(id, page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    @Override
    public PagedModel<EntityModel<Ksu>> getByBanque(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findByBanque(id, page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    @Override
    public PagedModel<EntityModel<Ksu>> getBySecteur(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findBySecteur(id, page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    @Override
    public PagedModel<EntityModel<Ksu>> getByTypeOperateur(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findByTypeOperateur(id, page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    @Override
    public PagedModel<EntityModel<Ksu>> getByTypePM(Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        Page<Ksu> ksus = ksuRepository.findByTypePM(id, page);
        return pagedResourcesAssembler.toModel(ksus);
    }

    //ckeck if ksu exist codeKsu
    @Override
    public boolean isPresent(Long id) {
        Optional<Ksu> optionalKsu = ksuRepository.findById(id);
        if (optionalKsu.isPresent())
            return true;
        return false;
    }

    @Override
    public boolean codeKsuIsPresent(String code) {
        Optional<Ksu> optionalKsu = ksuRepository.findByCodeKsu(code);
        if (optionalKsu.isPresent())
            return true;
        return false;
    }

    @Override
    public Ksu getKsuByActivationCode(String code) {
        Ksu ksu = ksuRepository.getKsuByActivationCode(code);

        if (ksu == null) {
            return null;
        }
        Long currentTimeStamp = System.currentTimeMillis();
        Long wainTime = 60 * 3000l;//3 minutes
        if (ksu.getDateAccessConexionTimeStamp() + wainTime < currentTimeStamp) {
            return null;
        }
        ksu.setEnableConnexionStatus(false);
        ksu.setCodeConnexion(null);
        ksuRepository.save(ksu);
        return ksu;
    }

    @Override
    public Ksu clearTempFingerPrintKey(Long id) {
        Ksu ksu = ksuRepository.findById(id).get();
        ksu.setTempFingerPrintKey(null);
        ksuRepository.save(ksu);
        return ksu;
    }

    @Override
    public Ksu activateNetwork(NetworkActivationInput networkActivationInput) {
        String codeMembre = networkActivationInput.getCodeMembre();
        Ksu ksu = ksuRepository.findFirstByCodeKsu(codeMembre, PageRequest.of(0, 1));
        if (ksu == null) {
            return null;
        }
        //int min=100000,max=999999;
        //int numericCode = (int)(Math.random()*(max-min+1)+min);
        //String activationCode =""+numericCode;
        String activationCode = networkActivationInput.getSecretCode();
        ksu.setEnableConnexionStatus(true);
        ksu.setCodeConnexion(activationCode);
        ksu.setTempFingerPrintKey(networkActivationInput.getTempFingerPrintKey());
        ksu.setDateAccessConexionTimeStamp(System.currentTimeMillis());
        ksuRepository.save(ksu);
        return ksu;
    }

    @Override
    public Ksu getKsuByCodeRepresentant(String code) {

        Ksu k = ksuRepository.ksuByCodeKsuRepresentant(code);

        if (k == null) {
            return null;
        }

        return k;
    }

    @Override
    public Object createUser(Long idKsu) {

        Ksu k = ksuRepository.findById(idKsu).orElse(null);

        String url = ipadresse + "/users";

        String token = this.getToken();

        String username = k.getNom() + k.getId();

        JSONObject obj = new JSONObject();

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        obj.put("username", username);
        obj.put("password", k.getCodeKsu());
        obj.put("actif", true);

        JSONArray ja = new JSONArray();
        ja.appendElement(obj);

        map.add("data", ja.toString());


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplateBuilder.build().postForObject(url, request, Object.class);
    }

    public String getToken() {

        RestTemplate restTemplate = new RestTemplate();

        // Login et recuperation du token

        JSONObject obj = new JSONObject();
        String token = null;
        obj.put("username", "user1");
        obj.put("password", "1234");
        String urlLogin = ipadresse + "login";
        System.out.println(urlLogin);

//        input.LoginCinetPayRequest result = restTemplateBuilder.build()
//                .postForObject(urlLogin, obj, LoginCinetPayRequest.class);
//        token = result.getData().getToken();

        Object result = restTemplateBuilder.build().postForObject(urlLogin, obj, Response.class);
        if (result != null) {
            if (result instanceof Response) {
                token = ((Response) result).getData().getToken();
            }
        }

        return token;

    }


    @Override
    public Ksu updateKSU(Long id, Ksu ksu) {
        Ksu ksu1 = ksuRepository.findById(id).orElse(null);
        assert ksu1 != null : " ID null";
        ksu1.setId(ksu.getId());
        ksu1.setReference(ksu.getReference());
        return ksuRepository.save(ksu1);
    }

    @Override
    public List<Ksu> listKsuById(Long idKsu) {
        return ksuRepository.listKsuById(idKsu);
    }

    public class Credentials {
        public static final String nomUtilisateur = "user1";
        public static final String motDePasse = "1234";
    }

    public class Response {
        private Statut statut;
        private Data data;

        public Statut getStatut() {
            return statut;
        }

        public void setStatut(Statut statut) {
            this.statut = statut;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

    }

    public class Statut {
        private String code;
        private String message;
        private String description;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public class Data {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


}
