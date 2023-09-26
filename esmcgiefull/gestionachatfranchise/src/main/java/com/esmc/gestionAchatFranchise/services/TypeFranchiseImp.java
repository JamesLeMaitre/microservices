package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.TypeFranchise;
import com.esmc.gestionAchatFranchise.repositories.TypeFranchiseRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.TypeFranchiseInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypeFranchiseImp implements TypeFranchiseInt {
    @Autowired
    private TypeFranchiseRepository typeFranchiseRep;

    @Override
    public List<TypeFranchise> getTypeFranchise() {
        return   typeFranchiseRep.findAll();
    }

    @Override
    public TypeFranchise getTypeFranchiseById(Long id) {
        return typeFranchiseRep.findById(id).get();
    }

    @Override
    public TypeFranchise addTypeFranchise(TypeFranchise typeFranchise) {
        typeFranchise.setDateCreate(new Date());
        typeFranchise.setDateUpdate(new Date());
        return typeFranchiseRep.save(typeFranchise);
    }


    @Override
    public TypeFranchise updateTypeFranchise( TypeFranchise typeFranchise) {
        return typeFranchiseRep.save(typeFranchise);
    }

    @Override
    public void deleteTypeFranchise(@PathVariable Long typeFranchiseId) {
        typeFranchiseRep.deleteById(typeFranchiseId);

    }

    @Override
    public String achatFranchiseGestionDelai(Long id) throws ExceptionsHandler {
        TypeFranchise typeFranchise1= getById (id) ;
        Date daupdate = typeFranchise1.getDateCreate();
        Date dateVerification= new Date() ;
        final  long MILLIS_PER_DAY = 24 * 3600 * 1000; // days in milliseconds

        long diff = dateVerification.getTime() - daupdate.getTime();
        long daysdiff= Math.round(diff/((double)MILLIS_PER_DAY));
        System.out.println(daysdiff);
        if(daysdiff<22){
            throw new ExceptionsHandler(ExceptionsHandler.alertGeneralException("les 22 jours ne sont pas encore passé"));
            //System.out.println(" les 22 jours ne sont pas encore passé ");
        }else if(daysdiff==22){
            throw new ExceptionsHandler(ExceptionsHandler.alertGeneralException("22 jour écoulés aujourd'hui envoyer message de notification a la personne"));
            // System.out.println(" 22 jour écoulés aujourd'hui envoyer message de notification a la personne ");

        }
        else if (daysdiff>22){
            throw new ExceptionsHandler(ExceptionsHandler.alertGeneralException(" cette procédure d'attente depasse 22 jour"));
            //System.out.println(" cette procédure d'attente depasse 22 jour ");
        }
        else  {
            throw new ExceptionsHandler(ExceptionsHandler.alertGeneralException("cas non resolu"));
            //System.out.println("cas non resolu ");

        }

    }

    @Override
    public TypeFranchise getById(Long id) throws ExceptionsHandler {
        if (id==null)
            System.out.println("identifiant inexistant");
        Optional<TypeFranchise> op=typeFranchiseRep.findById(id);
        return op.get();
    }

}
