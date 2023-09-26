package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Vague;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FifoRepository extends JpaRepository<Fifo, Long> {
    @Query("select f from Fifo f where f.ksu.id =?1 and f.isTreated = false")
    Fifo findByCodeKsu(Long code);
//    Fifo findFifoByKsuIdAndTreatedFalse(Long ksu_id);

    @Query("select f from Fifo f where f.id =?1 and f.isTreated =false")
    Optional<Fifo> findFifoByFifo(Long id);

    List<Fifo> findAllByVagueAndKsuType(Vague vague, KsuType ksuType);

    List<Fifo> findAllByKsuType(KsuType ksuType);

    Fifo findFifoById(Long id);

    @Query("select f from Fifo f where f.ksuType=?1 and f.isTreated=?2 ")
    List<Fifo> findAllByKsuTypeAndTreated(KsuType ksuType, boolean treated);

    Fifo findFifoByVague(Vague vague);

    @Query("select f from Fifo f where f.isTreated=false and f.ksu.id=?1 ")
    Fifo getGIE(Long idKSU);

    @Query("select f from Fifo f where f.isTreated=true and f.processingDate is not null")
    List<Fifo> getAllTreated();

    @Query("select f from Fifo f where f.isTreated=false and f.processingDate is null")
    List<Fifo> getAllNoTreatedInBasket();

    @Query("select count(f) from Fifo f where f.ksuType=0 and f.isTreated=false and f.panierTour=false")
    public int dernierFifoVente();

    @Query("select count(f) from Fifo f where f.ksuType=1 and f.isTreated=false and f.panierTour=false")
    public int dernierFifoAchat();


    @Query("select f from Fifo f where f.isTreated=false and f.processingDate is null and f.ksuType=?1")
    List<Fifo>  getAllFifoByNoTreated(KsuType ksuType);

    List<Fifo> getLastFifoByKsuTypeAndPanierTourIsFalse(KsuType ksuType);


//    @Query("select f from Fifo f where f.isTreated=true and f.processingDate is not null and f.ksuType=?1 and f.panierTour = false and f.ksu.id=?2 and order by f.id desc")
//    Fifo  getAllFifoTreated(KsuType ksuType,Long idKsu);

    @Query("select f from Fifo f where f.isTreated=true and f.processingDate is not null and f.ksuType=?1 and f.panierTour = false and f.ksu.id=?2 order by f.id desc ")
    Fifo getAllFifoTreated(KsuType ksuType, Long idKsu, PageRequest of);

}
