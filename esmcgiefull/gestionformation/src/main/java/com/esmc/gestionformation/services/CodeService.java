package com.esmc.gestionformation.services;

import com.esmc.gestionformation.entities.AffectationSalle;
import com.esmc.gestionformation.entities.Code;
import com.esmc.gestionformation.feign.AgrRestClient;
import com.esmc.gestionformation.feign.FranchiseRestClient;
import com.esmc.gestionformation.feign.KsuRestClient;
import com.esmc.gestionformation.inputs.CodeInput;
import com.esmc.gestionformation.inputs.CodeView;
import com.esmc.gestionformation.repositories.AffectationSalleRepository;
import com.esmc.gestionformation.repositories.CodeRepository;
import com.esmc.gestionformation.serviceinterfaces.CodeServiceInterface;
import com.esmc.gestionformation.serviceinterfaces.RegistreServiceInterface;
import com.esmc.gestionformation.serviceinterfaces.SalleServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import com.esmc.models.Poste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CodeService implements CodeServiceInterface {

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private SalleServiceInterface salleServiceInterface;

    @Autowired
    private AffectationSalleRepository affectationSalleRepository;
    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private AgrRestClient agrRestClient;

    @Autowired
    FranchiseRestClient franchiseRestClient;

    @Autowired
    RegistreServiceInterface registreServiceInterface;



    @Override
    public Code addCode(Code c) {

        AffectationSalle affectionSalle = affectationSalleRepository.findById(c.getAffectationSalle().getId()).orElse(null);
        c.setAffectationSalle(affectionSalle);

        //Code co = codeRepository.save(c);

        Long id = c.getAffectationSalle().getId();
        AffectationSalle s = salleServiceInterface.getSalleById(id);

        if (s == null) {
            return null;
        }

        String initial = s.getSalleFormation().getLibelle();

      //  String result = initial.substring(0,3);

        /*String cod = GetNextCode(result.toUpperCase()+"-");*/
        String lib = c.getAffectationSalle().getFormation().substring(0,3).toUpperCase()+"-";

        String cod = lib+System.currentTimeMillis();
        c.setCode(cod);
        c.setIdAgr(s.getIdAgr());

        return codeRepository.save(c);
    }

    @Override
    public Code addCodev2(Code c) {
        AffectationSalle affectionSalle = affectationSalleRepository.findById(c.getAffectationSalle().getId()).orElse(null);
        if(c.getAffectationSalle().getId() == null){
            return codeRepository.save(c);
        }
       return null;
    }

    @Override
    public Code updateCode(Long id, Code c) {

        Code code = codeRepository.findById(id).orElse(null);

        code.setCode(c.getCode());
        code.setDateUpdate(new Date());

        return codeRepository.save(code);
    }

    @Override
    public void deleteCode(Long id) {
        codeRepository.deleteById(id);
    }

    @Override
    public List<Code> listCode() {
        this.generateCode();
        return codeRepository.findAll();
    }

    @Override
    public List<Code> listCodeByType(Long idType) {
        return codeRepository.listCodeByType(idType);
    }

    @Override
    public List<Code> listCodeBySalle(Long idSalle) {
        return codeRepository.listCodeBySalle(idSalle);
    }

    @Override
    public List<Code> listCodeByTypeAndSalle(Long idType, Long idSalle) {
        return codeRepository.listCodeByTypeAndSalle(idType, idSalle);
    }


    @Override
    public Code getCodeByCode(String code) {
        Code c = codeRepository.getCodeByCode(code);

        if(c == null) {
            return null;
        }
        c.setEtat(true);
        codeRepository.save(c);
        return c;
    }

    @Override
    public Code updateCodeListAdmin(Long idCode) {
        Code c = codeRepository.findById(idCode).orElse(null);
        if(c == null) {
            return null;
        }
        c.setViewAdmin(true);
        codeRepository.save(c);
        return c;
    }

    @Override
    public String getCodeByIdAgrIdDetAgr(Long idPoste,Long idAgr,Long idAgrFran) {
        AffectationSalle affectionSalle = affectationSalleRepository.getSalleByIdPosteAndAndIdDetailAgrFranchiser(idPoste,idAgr,idAgrFran);

        if(affectionSalle == null){
            return null;
        } else {
            String code = codeRepository.getCodeByIdAgr(idAgr,idAgrFran, affectionSalle.getId());
            return code;
        }
    }

    @Override
    public List<CodeView> getAll(Long id) {

        List<Code> codeList1 = codeRepository.getCodeByFranchise(id);
        List<CodeView> codeList12 = new ArrayList<>();


        for (Code code : codeList1){
            CodeView codeView = new CodeView();
            DetailsAgr d = agrRestClient.findDetailAgrById(code.getIdAgr());
            DetailsAgr dFranchise = agrRestClient.findDetailAgrById(code.getIdAgrFranchiser());
            Ksu ksuClient = ksuRestClient.getById(d.getKsu());
            Ksu ksuFranchise = ksuRestClient.getById(dFranchise.getKsu());

            Formatter<Poste> posteFormatter = franchiseRestClient.getById(code.getAffectationSalle().getIdPoste());
                codeView.setAffectionSalle(code.getAffectationSalle());
                codeView.setId(code.getId());
                codeView.setCode(code.getCode());

                codeView.setKsu(ksuClient);

                codeView.setRaisonSociale(ksuFranchise.getRaisonSocial());
                codeView.setPoste(posteFormatter.getData().getLibelle());
                codeList12.add(codeView);
        }
         return codeList12;
    }

    @Override
    public Code checkExist(Code code) {
        return null;
    }



    public String generateCode() {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        // Exemple d'une chaine aléatoire de 20 caractères
        for (int i = 0; i < 9; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output;
        return output = sb.toString();
    }



    static final String AB = "SCV00" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public String GetNextCode(String init) {
        Integer codeNext = codeRepository.getLastIn(init) + 1;
        DecimalFormat formatterC = new DecimalFormat(init+"0000000");
        String c=formatterC.format(codeNext);

        return c;
    }

    /* Registre path*/
    /*
    * Task :  Check don't duplicate Registre input */
    @Override
    public Code checkInit(CodeInput code) {
        Code  code1 = codeRepository.getCodeByCodeCheckInit(code.getCode(),code.getAffectationSalle().getId());
        if(code1 == null){
            return null;
        }
       if(code.getState() == 1){
           code1.setEtat(true);
           codeRepository.save(code1);
           registreServiceInterface.initRegistre(code1.getCode());
           return code1;
       } else if(code.getState() == 2 ){
          // && registreServiceInterface.checkExist(code) != null/**/
          // if(registreServiceInterface.checkExist(code) != null){
               registreServiceInterface.updateRegistre(code1.getCode());
               return code1;
          // }

       }
       return null;
    }



}
