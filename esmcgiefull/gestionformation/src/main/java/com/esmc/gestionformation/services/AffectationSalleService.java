package com.esmc.gestionformation.services;


import com.esmc.gestionformation.entities.*;
import com.esmc.gestionformation.feign.AgrRestClient;
import com.esmc.gestionformation.feign.FranchiseRestClient;
import com.esmc.gestionformation.feign.KsuRestClient;
import com.esmc.gestionformation.repositories.*;
import com.esmc.gestionformation.serviceinterfaces.SalleServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import com.esmc.models.Poste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AffectationSalleService implements SalleServiceInterface {

    @Autowired
    private AffectationSalleRepository salleRepository;

    @Autowired
    private TypeSaleRepository typeSaleRepository;

    @Autowired
    private SalleFormationRepository salleFormationRepository;

    @Autowired
    private AgrRestClient agrRestClient;
    @Autowired
    private FranchiseRestClient franchiseRestClient;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private TypeCodeRepository typeCodeRepository;

    @Autowired
    private CodeRepository codeRepository;

    @Autowired
    private CodeService codeService;

    @Override
    public AffectationSalle addSalle(AffectationSalle s) {

        Formatter<Poste> posteFormatter = franchiseRestClient.getById(s.getIdPoste());
        if(posteFormatter.getData().getId() != null){
            TypeSalles typeSalles = typeSaleRepository.findById(s.getTypeSalle().getId()).orElse(null);
            s.setTypeSalle(typeSalles);
            SalleFormation salleFormation = salleFormationRepository.findById(s.getSalleFormation().getId()).orElse(null);
            if(typeSalles == null) {
                return null;
            }
            if(salleFormation == null) {
                return null;
            }
            s.setFormation(posteFormatter.getData().getLibelle());
            s.setSalleFormation(salleFormation);
            DetailsAgr det = agrRestClient.findDetailAgrById(s.getIdAgr());
            Ksu ksuClient = ksuRestClient.getById(det.getKsu());

            s.setIdksu(ksuClient.getId());
            AffectationSalle salleSave = salleRepository.save(s);



            Code code = new Code();
            code.setAffectationSalle(salleSave);
            code.setIdAgrFranchiser(s.getIdDetailAgrFranchiser());


            code.setIdAgr(s.getIdAgr());
            code.setTypeCode(typeCodeRepository.getTypeCodesById());
            code.setEtat(true);
            codeService.addCode(code);

            return salleSave;
        }  else {
            return null;
        }

//        Salle salle = salleRepository.save(s);
//
//        Salle sa = salleRepository.findById(s.getId()).orElse(null);

//        DetailsAgr ds = agrRestClient.findDetailAgrById(s.getId());
//
//        Ksu k = ksuRestClient.getById(ds.getKsu());
//
//        sa.setRaisonSocial(k.getRaisonSocial());


    }

    @Override
    public AffectationSalle updateSalle(Long id, AffectationSalle s) {

        AffectationSalle salle = salleRepository.findById(id).orElse(null);

        assert salle != null :"ID null";
        s.setId(salle.getId());
        s.setIdAgr(salle.getIdAgr());
        s.setTypeSalle(salle.getTypeSalle());
        s.setIdAgr(salle.getIdAgr());
        s.setTypeSalle(salle.getTypeSalle());

        return salleRepository.save(s);
    }

    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }

    @Override
    public List<AffectationSalle> listSalle() {
        return salleRepository.findAll();
    }

    @Override
    public List<AffectationSalle> listSalleByType(Long idType) {
        return salleRepository.listSalleByType(idType);
    }


    @Override
    public List<AffectationSalle> listByDetailsAgrFranchise(Long idDetailsAgrFranchise) {

        return salleRepository.listFormationByDetailsAgrFranchiser(idDetailsAgrFranchise);
    }
    @Override
    public AffectationSalle getSalleByRaisonSociale(Long idSalleFormation) {
        AffectationSalle s = salleRepository.getSalleByRaisonSociale(idSalleFormation);

        if(s == null) {
            return null;
        }
        return s;
    }

    @Override
    public AffectationSalle getSalleById(Long id) {

        return  salleRepository.findById(id).orElse(null);

    }

    @Override
    public List<AffectationSalle> getByIdPoste(Long idPoste) {
        return salleRepository.getSalleByIdPoste(idPoste);
    }

    /* Registre Path */
    @Override
    public List<AffectationSalle> listFormationByKsu(Long idKSU) {
        return salleRepository.getListFormationByKSU(idKSU);
    }
}
