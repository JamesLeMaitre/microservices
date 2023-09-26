package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.DataCertification;
import com.esmc.gestionCertification.repository.DataCertificationRepository;
import com.esmc.gestionCertification.services.DataCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DataCertificationServiceImpl implements DataCertificationService {
    @Autowired
    private DataCertificationRepository dataCertificationRepository;

    @Override
    public DataCertification addDataCertification(DataCertification data) {
        return dataCertificationRepository.save(data);
    }

    @Override
    public DataCertification getDataCertification(Long id) {
        return dataCertificationRepository.getDataCertification(id);
    }
}
