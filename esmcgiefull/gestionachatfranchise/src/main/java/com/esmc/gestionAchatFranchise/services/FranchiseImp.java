package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.Franchise;
import com.esmc.gestionAchatFranchise.repositories.CentreFranchiseRepository;
import com.esmc.gestionAchatFranchise.repositories.FranchiseRepository;
import com.esmc.gestionAchatFranchise.repositories.TypeFranchiseRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.FranchiseInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class FranchiseImp implements FranchiseInt {
    @Autowired
    private FranchiseRepository franchiseRep;

    private TypeFranchiseRepository typeFranchiseRepository;
    //    private DetailAgrRepository detailAgrRepository;
    private CentreFranchiseRepository centreFranchiseRepositoryRepository;

    @Override
    public List<Franchise> getFranchise() {
        return franchiseRep.findAll();
    }

    @Override
    public Franchise getFranchiseById(Long id) {
        return franchiseRep.findById(id).get();    }

    @Override
    @Transactional
    public Franchise addFranchise(Franchise franchise) {
        franchise.setDateCreate(new Date());
        franchise.setDateUpdate(new Date());
        return   franchiseRep.save(franchise);
    }



    //Update 2
    @Override
    public Franchise updateFranchise(Franchise fr) {
        return franchiseRep.save(fr);
    }



    @Override
    @Transactional
    public void deleteFranchise( Long franchiseId) {
        franchiseRep.deleteById(franchiseId);
    }

    @Override
    public List<Franchise> listCentreFranchise(Long id) {
        return franchiseRep.findFranchiseByCentre(id);
    }

    @Override
    public List<Franchise> listTypeFranchise(Long id) {
        return franchiseRep.findFranchiseByType(id);
    }

    @Override
    public List<Franchise> listDetailAgr(Long id) {
        return franchiseRep.findFranchiseByDetailAgr(id);
    }

    @Override
    public List<Franchise> listTypeFranchiseAndAndDetailAgr(Long id, Long id2) {
        return franchiseRep.findFranchiseByTypeAndDetailAgr(id, id2);
    }

    @Override
    public List<Franchise> listTypeFranchiseAndAndCentre(Long id, Long id2) {
        return franchiseRep.findFranchiseByTypeAndCentre(id, id2);
    }

    @Override
    public List<Franchise> listTypeFranchiseAndCentreFranchiseAndDetailAgr(Long id, Long id2, Long id3) {
        return franchiseRep.findFranchiseByTypeAndCentreAndDetailAgr(id, id2, id3);
    }

    @Override
    public List<Franchise> listTypeFranchiseAndKsuAndDetailAgr(Long id, Long id2, Long id3) {
        return franchiseRep.findFranchiseByTypeAndKsuAndDetailAgr(id,id2,id3);
    }

    @Transactional
    @Override
    public void addTypeFranchiseAndDetailAgr(Long id, Franchise franchise) throws ExceptionsHandler {

        Franchise franchise1 = getById (franchise.getId());
        franchise1.setTypeFranchise(franchise.getTypeFranchise());
        franchise1.setDetailAgr(franchise.getDetailAgr());
        franchiseRep.save(franchise1);

    }

    // THIS METHOD'S GOAL IS TO CHECK IF THE IDENTIFIER IS ENTERED


    // THIS METHOD'S GOAL IS TO VERIFY THE A LAPS TIME BETWEEN THE CREATED DATE AND CURRENT DATE TO MAKE THE PURCHASE

    public String[] achatFranchiseGestionDelai (Long id)  throws ExceptionsHandler {
        Franchise franchise1= getById (id) ;
        Date daupdate = franchise1.getDateCreate();
        Date dateVerification= new Date() ;
        final  long MILLIS_PER_DAY = 24 * 3600 * 1000; // days in milliseconds

        long diff = dateVerification.getTime() - daupdate.getTime();
        long daysdiff= Math.round(diff/((double)MILLIS_PER_DAY));
        //System.out.println(daysdiff);
        String m;
        if(daysdiff<20){

            m= "les 22 jours ne sont pas encore passé";
            //System.out.println(" les 22 jours ne sont pas encore passé ");
        }else if(daysdiff==20){
            m="22 jour écoulés aujourd'hui envoyer message de notification a la personne";
            // System.out.println(" 22 jour écoulés aujourd'hui envoyer message de notification a la personne ");
        }
        else  {
            m=" cette procédure d'attente depasse 22 jour";
            //System.out.println(" cette procédure d'attente depasse 22 jour ");
        }
        String[] ma={String.valueOf(daysdiff),m};


        return  ma;

    }


    @Transactional
    @Override
    public String achatFranchiseByCode(String code) {

        String str = code;

        int index = str.indexOf("BAN");

        if(index == - 1) {
            System.out.println("BCI");
            Franchise franchise = new Franchise();
            code=franchise.getCodeBci();


        } else {
            System.out.println("Ban");
        }
        return str;
    }

    @Override
    public Franchise getById(Long id) throws ExceptionsHandler {
        if (id==null)
            System.out.println("identifiant inexistant");
        Optional<Franchise> op=franchiseRep.findById(id);
        return op.get();
    }


}
