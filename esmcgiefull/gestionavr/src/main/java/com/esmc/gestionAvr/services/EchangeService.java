package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Echange;
import com.esmc.gestionAvr.repositories.EchangeRepository;
import com.esmc.gestionAvr.repositories.TypeEchangeRepository;
import com.esmc.gestionAvr.servicesInterfaces.EchangeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EchangeService implements EchangeInterface {

    @Autowired
    private EchangeRepository echangeRepository;

    private TypeEchangeRepository typeEchangeRepository;
    /**
     * @param echange
     * @return
     */
    @Override
    public Echange addEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public void deleteEchange(Long id) {
        echangeRepository.deleteById(id);
    }

    /**
     * @param echange
     * @return
     */
    @Override
    public Echange updateEchange(Echange echange) {
        return echangeRepository.save(echange);
    }

    /**
     * @return
     */
    @Override
    public List<Echange> listEchange() {
        return echangeRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Echange getEchange(Long id) {
        return echangeRepository.findById(id).orElse(null);
    }

    /**
     /** @param id
     * @return
     *//*
    @Override
    public List<Echange> getEchangeByTypeEchange(Long id) {
        return echangeRepository.getEchangeByTypeEchange(id);
    }*/


    /**
     *
    public void Commande(Echange echange, SMAvr smAvr, Avr avr) {
        if (typeEchange.getId(id1)){

        }
    }

    *//**
     *
     *//*
    @Override
    public void ConfirmationCommande() {

    }

    *//**
     *
     *//*
    @Override
    public void Réclammation() {

    }*/
}
