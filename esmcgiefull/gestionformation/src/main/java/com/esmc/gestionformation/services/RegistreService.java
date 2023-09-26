package com.esmc.gestionformation.services;

import com.esmc.gestionformation.entities.Code;
import com.esmc.gestionformation.entities.Registre;
import com.esmc.gestionformation.feign.AgrRestClient;
import com.esmc.gestionformation.feign.FranchiseRestClient;
import com.esmc.gestionformation.feign.KsuRestClient;
import com.esmc.gestionformation.inputs.CodeInput;
import com.esmc.gestionformation.inputs.ObjectInput;
import com.esmc.gestionformation.repositories.CodeRepository;
import com.esmc.gestionformation.repositories.RegistreRepository;
import com.esmc.gestionformation.serviceinterfaces.RegistreServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import com.esmc.models.Poste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RegistreService implements RegistreServiceInterface {

    @Autowired
    private RegistreRepository registreRepository;

    @Autowired
    private CodeService codeService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Date date = new Date();
    @Autowired
    private FranchiseRestClient franchiseRestClient;

    @Autowired
    private AgrRestClient agrRestClient;

    @Autowired
    private KsuRestClient ksuRestClient;
    @Autowired
    private CodeRepository codeRepository;

    @Override
    public Registre initRegistre(String data) {
        Registre registre = new Registre();
        Code code = codeService.getCodeByCode(data);
        if(code == null){
            return null;
        }

        registre.setCode(code);
        registre.setIdPoste(code.getAffectationSalle().getIdPoste());
        registre.setIdDetailsAgr(code.getIdAgr());
        registre.setDateEntree(formatter.format(date));
        registre.setSalleFormation(code.getAffectationSalle().getSalleFormation());

        Formatter<Poste> posteFormatter = franchiseRestClient.getById(registre.getIdPoste());
        registre.setLibellePoste(posteFormatter.getData().getLibelle());

        DetailsAgr det = agrRestClient.findDetailAgrById(code.getIdAgr());
        Ksu ksuClient = ksuRestClient.getById(det.getKsu());
        registre.setIdKsu(ksuClient.getId());
        registre.setNomksu(ksuClient.getNom());
       return registreRepository.save(registre);
    }

    @Override
    public Registre updateCheckOut(ObjectInput data) {
        Registre registre = registreRepository.checkOut(data.getIdKSU());
        System.out.println(registre);
        if(registre == null){
            return null;
        }
        if(data.getState() == 2){
            registre.setDateSortie(formatter.format(date));
            registre.setDailyCheck(true);
            registreRepository.save(registre);
        }
        return registre;
    }

    @Override
    public Registre checkExist(CodeInput data) {
        Code code = codeRepository.getCodeByCode(data.getCode());
        Registre registre = registreRepository.checkExist(data.getCode(),code.getAffectationSalle().getId(), data.getIdKSU());
        if(registre == null) {
            return null;
        }
        System.out.println("==========");
        System.out.println(registre);
        return registre;
    }

    @Override
    public Registre updateRegistre(String data) {
        Registre registre = registreRepository.getRegistresByCode(data);
        registre.setDateSortie(formatter.format(date));
        registre.setDailyCheck(true);
        return registreRepository.save(registre);
    }

    @Override
    public List<Registre> allRegistreFull(Long formation) {
        return registreRepository.getHistorique(formation);
    }

    @Override
    public List<Registre> allRegistreFullFranchise(Long idDetAgrFranchiser) {
        return null;
    }
}
