package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.Intrant;

import java.util.List;

public interface IntrantServiceInterface {
    public Intrant addIntrant(Long id, Intrant Intrant);
    public Intrant updateIntrant(Long id, Intrant intrant);

    public void deleteIntrant(Long id);
    public List<Intrant> getAllIntrant();

    public List<Intrant> recuperationIntrantAvr(Long id);

    public List<Intrant> IntrantByKsu(Long id);

    List<Intrant> getByIdAvr(Long id);

    List<Intrant> getByListPosteReceveur(Long id);

    List<Intrant> getByIdDetailAgr(Long id);

    String getByPosteReceveur(Long id);

    Intrant getExtrantById(Long id);
    List<Intrant> getIntrantByDetailsSupport(Long id);

    List<Intrant> getAllSupportEtablie(Long id);

    Intrant archivage(Long id);

    List<Intrant> listArchivage(Long id);

    List<Intrant> getIntrantByDetailAgrFalse(Long id);
    List<Intrant> getIntrantByDetailAgrTrue(Long id);

    List<Intrant> getIntrantByDetailTypeSupport(Long id);

    Intrant archivageByIdDetailAgr(Long id);
}
