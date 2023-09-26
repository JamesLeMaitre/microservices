package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.DetailSupport;
import com.esmc.gestionAvr.entities.Intrant;
import com.esmc.gestionAvr.feign.TypeSupportClient;
import com.esmc.gestionAvr.repositories.DetailSupportRepository;
import com.esmc.gestionAvr.repositories.IntrantRepository;
import com.esmc.gestionAvr.servicesInterfaces.IntrantServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IntrantServices implements IntrantServiceInterface {
    @Autowired
    private  IntrantRepository intrantRepository;

    @Autowired
    private DetailSupportRepository detailSupportRepository;

    @Autowired
    private TypeSupportClient typeSupportClient;

    @Override
    public Intrant addIntrant(Long id, Intrant Intrant) {
        return intrantRepository.save(Intrant);
    }

    @Override
    public Intrant updateIntrant(Long id, Intrant intrant) {
        Intrant intrant1 = intrantRepository.findById(id).orElse(null);
        assert intrant1 != null : "ID null";
        intrant1.setSupportEtablie(intrant.isSupportEtablie());
        intrant1.setMontant(intrant.getMontant());
        intrant1.setRefer(intrant.getRefer());
        intrant1.setAvr(intrant.getAvr());
        intrant1.setArchive(intrant.isArchive());
        intrant1.setKsu(intrant.getKsu());
        intrant1.setKsuEmetteur(intrant.getKsuEmetteur());
        intrant1.setKsuRecepteur(intrant.getKsuRecepteur());
        intrant1.setDetailAgrEmetteur(intrant.getDetailAgrEmetteur());
        intrant1.setDetailAgrRecepteur(intrant.getDetailAgrRecepteur());
        intrant1.setPosteEmetteur(intrant.getPosteEmetteur());
        intrant1.setPosteReceveur(intrant.getPosteReceveur());
        intrant1.setTypeSmAvr(intrant.getTypeSmAvr());
        intrant1.setDetailSupport(intrant.getDetailSupport());
        intrant1.setDataInfo(intrant.getDataInfo());
        intrant1.setViewDev(intrant.isViewDev());
        return intrantRepository.save(intrant);
    }

    @Override
    public void deleteIntrant(Long id) {
        intrantRepository.deleteById(id);
    }

    @Override
    public List<Intrant> getAllIntrant() {
        return intrantRepository.findAll();
    }

    @Override
    public List<Intrant> recuperationIntrantAvr(Long id) {
        return intrantRepository.recuperationIntrantAvr(id);
    }

    @Override
    public List<Intrant> IntrantByKsu(Long id) {
        return intrantRepository.IntrantByKsu(id);
    }

    @Override
    public List<Intrant> getByIdAvr(Long id) {
        return intrantRepository.getByIdAvr(id);
    }

    @Override
    public List<Intrant> getByListPosteReceveur(Long id) {
        List<Intrant> intrants = intrantRepository.getListByPosteReceveur(id);
        return intrants;
    }

    @Override
    public List<Intrant> getByIdDetailAgr(Long id) {
        return intrantRepository.getByIdDetailAgr(id);
    }

    @Override
    public String getByPosteReceveur(Long id) {
        Intrant intrant = intrantRepository.getByPosteReceveur(id);

        DetailSupport detailSupport = detailSupportRepository.findById(intrant.getDetailSupport().getId()).orElse(null);

        assert detailSupport != null : "ID null";

        return typeSupportClient.getLibelleById(detailSupport.getIdTypeSupport());
    }

    @Override
    public Intrant getExtrantById(Long id) {
        return intrantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Intrant> getIntrantByDetailsSupport(Long id) {
        return intrantRepository.getIntrantByDetailsSupport(id);
    }

    @Override
    public List<Intrant> getAllSupportEtablie(Long id) {
        return intrantRepository.getSupportEtablie(id);
    }

    @Override
    public Intrant archivage(Long id) {
        Intrant intrant = intrantRepository.findById(id).orElse(null);
        assert intrant != null :"Id null";
        intrant.setArchive(true);
        return intrantRepository.save(intrant);
    }

    @Override
    public List<Intrant> listArchivage(Long id) {
        return intrantRepository.listArchiver(id);
    }

    @Override
    public List<Intrant> getIntrantByDetailAgrFalse(Long id) {
        return intrantRepository.getIntrantByDetailAgrFalse(id);
    }

    @Override
    public List<Intrant> getIntrantByDetailAgrTrue(Long id) {
        return intrantRepository.getIntrantByDetailAgrTrue(id);
    }

    @Override
    public List<Intrant> getIntrantByDetailTypeSupport(Long id) {
        return intrantRepository.getIntrantByDetailTypeSupport(id);
    }

    @Override
    public Intrant archivageByIdDetailAgr(Long id) {
        Intrant intrant = intrantRepository.findById(id).orElse(null);
        assert intrant != null :"Id null";
        intrant.setArchive(true);
        return intrantRepository.save(intrant);
    }
}
