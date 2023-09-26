package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.StepCertification;
import com.esmc.gestionCertification.repository.StepCertificationRepository;
import com.esmc.gestionCertification.services.StepCertificationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StepCertificationImpl implements StepCertificationService {
    private final StepCertificationRepository stepCertificationRepository;

    public StepCertificationImpl(StepCertificationRepository stepCertificationRepository) {
        this.stepCertificationRepository = stepCertificationRepository;
    }

    @Override
    public List<StepCertification> getAllStep() {
        return stepCertificationRepository.findAll();
    }

    @Override
    public StepCertification save(StepCertification stepCertification) {
                return stepCertificationRepository.save(stepCertification);
    }


    @Override
    public StepCertification update(Long id, StepCertification stepCertification) {
        StepCertification stp = stepCertificationRepository.findById(id).orElse(null);
        /*stp.setId(stepCertification.getId());
        stp.setIdPoste(stepCertification.getIdPoste());
        stp.setIdDetailAgr(stepCertification.getIdDetailAgr());*/
        stp.setStep(stepCertification.getStep());
        return stepCertificationRepository.save(stp);
    }

    @Override
    public void delete(Long id) {
        stepCertificationRepository.deleteById(id);
    }

    @Override
    public StepCertification getById(Long id) {
      return   stepCertificationRepository.findById(id).orElse(null);
    }

    @Override
    public StepCertification get3Id(Long idDetailAgr, Long idDetailAgrFranchiser, Long idPoste) {
        return stepCertificationRepository.getStepCertificationByIdDetailAgrAndIdDetailAgrFranchiserAndIdPoste(idDetailAgr,idDetailAgrFranchiser,idPoste);
    }

}
