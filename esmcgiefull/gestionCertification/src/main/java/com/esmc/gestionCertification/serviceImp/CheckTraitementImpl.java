package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.CheckTraitement;
import com.esmc.gestionCertification.enums.CheckTraitementEnum;
import com.esmc.gestionCertification.repository.CheckTraitementRepository;
import com.esmc.gestionCertification.services.CheckTraitementService;
import com.esmc.input.CheckTraitementInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CheckTraitementImpl implements CheckTraitementService {
    @Autowired
    private CheckTraitementRepository checkTraitementRepository;
    @Override
    public CheckTraitement saveStatus(CheckTraitementInput data) {
        CheckTraitement checkTraitement = new CheckTraitement();
        checkTraitement.setIdPoste(data.getIdPoste());
        checkTraitement.setStatus(CheckTraitementEnum.PENDING.ordinal());
        checkTraitement.setIdDetailAgr(data.getIdDetailAgr());
        checkTraitement.setIdDetailAgrFranchiser(data.getIdDetailAgrFranchiser());
        checkTraitement.setCandidature(data.getCandidature());


        return checkTraitementRepository.save(checkTraitement);
    }

    @Override
    public CheckTraitement update(Long idx,Long idy,Long idz,Long idt) {
        CheckTraitement checkTraitement = checkTraitementRepository.findCheckTraitementByCandidature(idx,idy,idz,idt);
        if(checkTraitement != null){

            checkTraitement.setStatus(CheckTraitementEnum.VALIDATE.ordinal());
            checkTraitementRepository.save(checkTraitement);
            System.out.println("========================TR");
            System.out.println(checkTraitement);
            System.out.println("========================");
        }
        return checkTraitement;
    }

    @Override
    public CheckTraitement updateError(Long idx, Long idy, Long idz, Long idt) {
        CheckTraitement checkTraitement = getCurrent(idx,idy,idz,idt);
        if(checkTraitement !=null){
            checkTraitement.setStatus(CheckTraitementEnum.ERROR.ordinal());
            checkTraitementRepository.save(checkTraitement);
            return checkTraitement;
        } else{
          return null;
        }
    }

    @Override
    public CheckTraitement getCurrent(Long idx, Long idy, Long idz, Long idt) {
        return checkTraitementRepository.findCheckTraitementByCandidature(idx,idy,idz,idt);
    }
}
