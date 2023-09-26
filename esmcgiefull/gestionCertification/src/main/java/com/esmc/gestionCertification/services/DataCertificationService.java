package com.esmc.gestionCertification.services;

import com.esmc.gestionCertification.entities.DataCertification;

public interface DataCertificationService {
    DataCertification addDataCertification(DataCertification data);
    DataCertification getDataCertification(Long id);
}
