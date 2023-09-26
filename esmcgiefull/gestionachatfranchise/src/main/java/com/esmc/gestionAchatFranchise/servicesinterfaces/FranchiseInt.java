package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.Franchise;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface FranchiseInt {
    List<Franchise> getFranchise();

    Franchise getFranchiseById(Long id);

    Franchise addFranchise(Franchise franchise);

//    Franchise  updateFranchise( Long id, FranchiseRequest request) throws Exception;

    Franchise updateFranchise(Franchise fr) ;

    void deleteFranchise(Long franchiseId);

    public  List<Franchise> listCentreFranchise(Long id);

    public  List<Franchise> listTypeFranchise(Long id);

    public  List<Franchise> listDetailAgr(Long id);

    public  List<Franchise> listTypeFranchiseAndAndDetailAgr(Long id, Long id2);

    public  List<Franchise> listTypeFranchiseAndAndCentre(Long id, Long id2);


    public  List<Franchise> listTypeFranchiseAndCentreFranchiseAndDetailAgr(Long id, Long id2, Long id3);

    public  List<Franchise> listTypeFranchiseAndKsuAndDetailAgr(Long id, Long id2, Long id3);

    // METHOD TO INSERT IN  FRANCHISE BY FILLING FRANCHISE ID  TYPEFRANCHISE AND  DETAILAGR ID
    void addTypeFranchiseAndDetailAgr(Long id, Franchise franchise) throws ExceptionsHandler;

    // METHOD TO INSERT IN  FRANCHISE BY FILLING FRANCHISE ID  TYPEFRANCHISE AND  DETAILAGR ID
    public String[]  achatFranchiseGestionDelai(Long id)  throws ExceptionsHandler; //

    // METHOD TO HANDLE THE BCI AND BAN CODE'S CONTROL
    public String achatFranchiseByCode(String code);

    public Franchise getById(Long id) throws  ExceptionsHandler;





}
