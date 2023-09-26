package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.FifoHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FifoHistoryInterface {
    List<FifoHistory> getAll();

    FifoHistory getById(Long id);

    List<FifoHistory> getListByType(Boolean isBuy);

    List<FifoHistory> getListByIdDetailAgr(Long id);
}
