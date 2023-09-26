package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.EtapeFranchise;
import com.esmc.gestionAchatFranchise.entities.TypeFranchise;
import com.esmc.gestionAchatFranchise.repositories.EtapeFranchiseRepository;
import com.esmc.gestionAchatFranchise.repositories.TypeFranchiseRepository;
import com.esmc.gestionAchatFranchise.request.EtapeFranchiseRequest;
import com.esmc.gestionAchatFranchise.servicesinterfaces.EtapeFranchiseInt;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EtapeFranchiseImp implements EtapeFranchiseInt {

    private EtapeFranchiseRepository etapeFranchiseRep;

    private TypeFranchiseRepository franchiseRepository;

    @Override
    public List<EtapeFranchise> getEtapeFranchise() {
        return etapeFranchiseRep.findAll();
    }

    @Override
    public EtapeFranchise getEtapeFranchiseById(Long id) {
        return etapeFranchiseRep.findById(id).get();
    }

    @Override
    public EtapeFranchise getEtapeFranchiseByIdKsu(Long id) {
        return etapeFranchiseRep.findByIdKSU(id).get();
    }

    @Override
    @Transactional
    public EtapeFranchise addEtapeFranchise(EtapeFranchise etapeFranchise) {
        return etapeFranchiseRep.save(etapeFranchise);
    }

    @Override
    public EtapeFranchise addEtapeFranchisev2(EtapeFranchiseRequest etapeFranchise) {
        EtapeFranchise franchise = etapeFranchiseRep.getByIdKsu(etapeFranchise.getIdKSU(), etapeFranchise.getTypeFranchise().getId());
        if(franchise == null){
            EtapeFranchise etapeFranchise1 = new EtapeFranchise();
            etapeFranchise1.setEtape(etapeFranchise.getEtape());
            etapeFranchise1.setTypeFranchise(etapeFranchise.getTypeFranchise());
            etapeFranchise1.setIdKSU(etapeFranchise.getIdKSU());
            //System.out.println("===SAVE2===");
            return etapeFranchiseRep.save(etapeFranchise1);
        } else {
            franchise.setId(franchise.getId());
            franchise.setIdKSU(etapeFranchise.getIdKSU());
            franchise.setTypeFranchise(etapeFranchise.getTypeFranchise());
            franchise.setEtape(etapeFranchise.getEtape());
            return etapeFranchiseRep.save(franchise);
        }

    }

    @Override
    public EtapeFranchise updateEtapeFranchise( EtapeFranchise etapeFranchise) {
        return etapeFranchiseRep.save(etapeFranchise);

    }

    @Override
    public EtapeFranchise updateEtapeFranchiseByKsu(Long id, EtapeFranchiseRequest request) throws Exception {
        EtapeFranchise etapeFranchise = etapeFranchiseRep.findByIdKSU(id)
                .orElseThrow(() -> new Exception());
        TypeFranchise typeFranchise = franchiseRepository.findById(request.getTypeFranchise().getId())
                .orElseThrow(() -> new Exception());
        BeanUtils.copyProperties(request, etapeFranchise);
        etapeFranchise.setTypeFranchise(typeFranchise);
        return etapeFranchiseRep.save(etapeFranchise);
    }

    @Override
    public EtapeFranchise supreme(Long id, EtapeFranchiseRequest request) {
        EtapeFranchise etapeFranchise = etapeFranchiseRep.getByIdKsu(id,request.getTypeFranchise().getId());

        if(etapeFranchise != null){
            etapeFranchise.setId(etapeFranchise.getId());
            etapeFranchise.setEtape(request.getEtape());
            etapeFranchise.setIdKSU(request.getIdKSU());
            etapeFranchise.setTypeFranchise(request.getTypeFranchise());
            System.out.println("===UPDATE2===");
            return etapeFranchiseRep.save(etapeFranchise);
        }
        return null;
    }

    @Override
    public Boolean checkExist(Long id, EtapeFranchiseRequest request) {
        EtapeFranchise etapeFranchise =  etapeFranchiseRep.getByIdKsu(id,request.getTypeFranchise().getId());
        if(etapeFranchise == null){
            return false;
        }
        return true;
    }



    @Override
    public void deleteEtapeFranchise(Long etapeFranchiseId) {
        etapeFranchiseRep.deleteById(etapeFranchiseId);
    }
    @Override
    public EtapeFranchise getById(Long id) throws ExceptionsHandler {
        if (id==null)
            System.out.println("identifiant inexistant");
        Optional<EtapeFranchise> op=etapeFranchiseRep.findById(id);
        return op.get();
    }


}