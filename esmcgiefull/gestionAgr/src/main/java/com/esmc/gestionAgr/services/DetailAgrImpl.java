package com.esmc.gestionAgr.services;

import com.esmc.gestionAgr.entities.DetailsAgr;
import com.esmc.gestionAgr.entities.TypeMaBanKsuAgr;
import com.esmc.gestionAgr.exceptions.Exceptions;
import com.esmc.gestionAgr.feign.KsuRestClient;
import com.esmc.gestionAgr.feign.TerminalEnchangeRestClient;
import com.esmc.gestionAgr.feign.TypeMaBanKsuRestClient;
import com.esmc.gestionAgr.repositories.AgrRepository;
import com.esmc.gestionAgr.repositories.DetailsAgrRepository;

import com.esmc.gestionAgr.repositories.TypeMaBanKsuAgrRepository;
import com.esmc.gestionAgr.serviceinterfaces.DetailAgrServiceInterface;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DetailAgrImpl implements DetailAgrServiceInterface {

    @Autowired
    private  DetailsAgrRepository detailsAgrRepository;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private TypeMaBanKsuAgrRepository typeMaBanKsuAgrRepository;

    @Autowired
    private TypeMaBanKsuRestClient typeMaBanKsuRestClient;

    @Autowired
    private TerminalEnchangeRestClient terminalEnchangeRestClient;

    private final AgrRepository agrRepository;

    public DetailAgrImpl(DetailsAgrRepository detailsAgrRepository, AgrRepository agrRepository) {
        this.detailsAgrRepository = detailsAgrRepository;
        this.agrRepository = agrRepository;
    }


    @Override
    public List<DetailsAgr> getAgr() {
        return detailsAgrRepository.findAll();
    }


    @Override
    public void deleteAgr(Long id) throws Exceptions {
        detailsAgrRepository.deleteById(id);
    }

    @Override
    public void updateAgr(Long id, DetailsAgr detailAgr) throws Exceptions {
//        DetailsAgr detailAgr1 = getById(detailAgr.getId());
//        detailAgr1.setEtat(detailAgr.isEtat());
//        detailAgr1.getDateUpdate();
//        detailAgr1.setAgr(detailAgr.getAgr());
//        detailAgr1.setKsu(detailAgr.getKsu());
//        detailsAgrRepository.save(detailAgr1);
    }

    @Override
    public DetailsAgr getById(Long id) {
        Optional<DetailsAgr> det = detailsAgrRepository.findById(id);
        return det.get();
    }

    @Override
    public void affectationAgr(int val2, Long idKsu) {

    }

    @Override
    public List<DetailsAgr> findDetailAgrByIdkSU(Long idKSU) {

        return detailsAgrRepository.findDetailAgrByIdkSU(idKSU);
    }

    /**
     * @param idKSU
     * @return
     */
    @Override
    public DetailsAgr getDetailAgrByIdkSU(Long idKSU) {
        return detailsAgrRepository.findById(idKSU).orElse(null);
    }

    // Affectation des AGRs au Nouveau KSU
    @Override
    public DetailsAgr AffectationAgr(Long idType, Long idKsu) {

      List<TypeMaBanKsuAgr> lstTA = typeMaBanKsuAgrRepository
              .listTypeMaBanKsuAgrByTypeMaBanKsu(idType);

//        Ksu k = ksuRestClient. getById(idKsu);
//
//        System.out.println("===============================02======================================");
//        System.out.println(k);
//        System.out.println("=======================================================================");
        DetailsAgr detailsAgr = null;

        List<DetailsAgr> listD = detailsAgrRepository.findDetailAgrByIdkSU(idKsu);

        if (listD.size() > 0) {
            detailsAgrRepository.findDetailAgrByIdkSU(idKsu);
        } else {
            if(lstTA.size() > 0) {
                for (TypeMaBanKsuAgr t: lstTA) {
                    DetailsAgr da = new DetailsAgr(t, idKsu);
                    detailsAgr = detailsAgrRepository.save(da);
                }
            }
        }
        return detailsAgr;
    }

    @Override
    public DetailsAgr getDetailAgrById(Long id) {
        return detailsAgrRepository.findById(id).orElse(null);
    }

    @Override
    public DetailsAgr activateFRanchiseMode(Long id) {
        DetailsAgr d = detailsAgrRepository.findById(id).orElse(null);
        if(d == null){
            return null;
        }
        d.setFranchise(true);
        detailsAgrRepository.save(d);
        return d;
    }
}
