package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.DetailSupport;
import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.Intrant;
import com.esmc.gestionAvr.inputs.ExtrantInput;
import com.esmc.gestionAvr.inputs.ExtrantInputv2;

import java.util.List;

public interface ExtrantServiceInterface {

    public Extrant addExtrant(Long id, Extrant extrant);

    public Extrant updateExtrant(Long id, Extrant extrant);

    Extrant updateExtrantV2(Long id, Long idDetail, Extrant extrant, String data);

    public void deleteExtrant(Long id);

    public List<Extrant> getAllExtrant();
    public List<Extrant> recuperatiionExtrantAvr(Long id);

    public List<Extrant> ExtrantByKsu(Long id);

    List<Extrant> getByIdAvr(Long id);

    List<Extrant> getByIdDetailAgr(Long id);


    String getByIdPosteEmetteur(Long id);

    List<Extrant> getByListPosteEmetteur(Long id);

    List<Extrant> getListByPosteEmetteurEtablie(Long id);

    Extrant getExtrantById(Long id);

    Extrant getExtrantByArchive(Long id);

    String getLibelle(Long id);

    Extrant addExtrantSupport(ExtrantInput a);

    DetailSupport updateExtrantSupport(Long id, ExtrantInput a);

    void addExtrantSupportv2(ExtrantInputv2 a);

    List<Extrant> getExtrantByDetailsSupport(Long id);

    Extrant archivage(Long id);

    List<Extrant> listArchivage(Long id);


    Extrant getByID(Long id);

    Extrant viderExtrant(Long id);

    Extrant getNoAchive(Long id);

    List<Extrant> getExtrantByDetailAgrFalse(Long id);

    List<Extrant> getExtrantByDetailAgrTrue(Long id);

    Extrant archivageByIdDetailAgr(Long id);

    int countBy(Long id);
    public void deleteDev(Long id);

    void addExtrantSupportv3(ExtrantInputv2 a);
}
