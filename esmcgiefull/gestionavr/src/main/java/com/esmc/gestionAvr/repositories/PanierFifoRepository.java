package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Panier;
import com.esmc.gestionAvr.entities.PanierFifo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PanierFifoRepository extends JpaRepository<PanierFifo, Long> {
    @Query("select f from PanierFifo f where f.isBuy = ?1 and f.avr.id = ?2")
    public PanierFifo getLastByTypeAndAvr(boolean isBuy, Long idAvr, PageRequest of);

    @Query("select f from PanierFifo f where f.dateCreate <= ?1")
    List<PanierFifo> getPanierFifoReadyToGoToFifoByDate(Date date);
    @Query("select f from PanierFifo f where f.detailAgr = ?1")
    List<PanierFifo> getlistByIdDetailAgr(Long id);

}
