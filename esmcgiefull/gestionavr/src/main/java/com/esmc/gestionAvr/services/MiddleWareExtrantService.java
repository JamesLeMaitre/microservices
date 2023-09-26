package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.MiddleWareExtrant;
import com.esmc.gestionAvr.inputs.MInput;
import com.esmc.gestionAvr.repositories.MiddleWareExtrantRepository;
import com.esmc.gestionAvr.servicesInterfaces.MiddleWareExtrantInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utiles.FirstElement;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MiddleWareExtrantService implements MiddleWareExtrantInterface {
    private final MiddleWareExtrantRepository mxRep;

    private final ExtrantServices services;

    private final DataSupportService dataSupportService;

    public MiddleWareExtrantService(MiddleWareExtrantRepository mxRep, ExtrantServices services, DataSupportService dataSupportService) {
        this.mxRep = mxRep;
        this.services = services;
        this.dataSupportService = dataSupportService;
    }


    @Override
    public MiddleWareExtrant  updateMedia(MInput dt) {
        MiddleWareExtrant middleWareExtrant = mxRep.updateBy(dt.getIdExtrant(),dt.getIdIntevenant());
        assert middleWareExtrant != null : "Not Found";
        Extrant extrant = services.getExtrantById(middleWareExtrant.getIdExtrant());
        if(dt.getIdTypeSupport() ==null){
            services.updateExtrant(middleWareExtrant.getIdExtrant(),extrant);
        }else{
            services.updateExtrantV2(middleWareExtrant.getIdExtrant(), dt.getIdTypeSupport(), extrant,dt.getData());
        }

        middleWareExtrant.setStep(false);
        mxRep.save(middleWareExtrant);
        // Save New after Update
        MiddleWareExtrant mxc = new MiddleWareExtrant();
        if(extrant.isArchive() || extrant.isViewDev()){
            return null;
        }
        mxc.setIdExtrant(middleWareExtrant.getIdExtrant());
        mxc.setStep(true);
        mxc.setPosteEmetteur(middleWareExtrant.getIntervenant());
        mxc.setIntervenant(dt.getIntervenantTour());
        mxc.setIdDataSupport(middleWareExtrant.getIdDataSupport());
        if(dt.getIdTypeSupport() == null){
            dataSupportService.updateDataSupportV2(dt.getIdDataSupport(),dt.getData());
        }
        //dataSupportService.updateDataSupportV3(dt.getIdDataSupport(),dt.getData(),dt.getIdTSupport());
        return mxRep.save(mxc);
    }

    @Override
    public List<MiddleWareExtrant> listIntrant(Long idintervant) {
        return mxRep.listIntrantTour(idintervant);
    }

    @Override
    public List<MiddleWareExtrant> archivage(Long idMx) {
        List<MiddleWareExtrant> items = mxRep.listExtrantTour(idMx);
        List<MiddleWareExtrant> extrantList = new ArrayList<>();
        for(MiddleWareExtrant idExtrant: items){
           idExtrant.setStep(false);
            mxRep.save(idExtrant);
            extrantList.add(idExtrant);
          //  extrantList.add(ext);
        }
        return extrantList;
    }


    @Override
    public List<Extrant> getExtrantArchiver(Long idMx) {
        List<MiddleWareExtrant> items = mxRep.listIntrantTourID(idMx);
        List<Extrant> extrantList = new ArrayList<>();
        for(MiddleWareExtrant idExtrant: items){
            Extrant ext = services.getExtrantByArchive(idExtrant.getIdExtrant());
            if(ext != null){
                extrantList.add(ext);
            }
        }
        return extrantList;
    }

    @Override
    public List<Extrant> getByTour(Long idMx) {
        List<MiddleWareExtrant> items = listIntrant(idMx);
        List<Extrant> extrantList = new ArrayList<>();
        for(MiddleWareExtrant idExtrant: items){
            Extrant ext = services.getExtrantById(idExtrant.getIdExtrant());
            extrantList.add(ext);
        }
        return extrantList;
    }

//    @Override
//    public List<MiddleWareExtrant> archiverTour(Long id) {
//        List<MiddleWareExtrant> wareExtrant = mxRep.archiverTourV2(id);
//        List<MiddleWareExtrant> f = new ArrayList<>();
//        for(MiddleWareExtrant wsx: wareExtrant){
//            wsx.setArchiver(true);
//            mxRep.save(wsx);
//            f.add(wsx);
//        }
//       return f;
//    }
//
//    @Override
//    public MiddleWareExtrant viderTour(Long id) {
//        MiddleWareExtrant wareExtrant = mxRep.findById(id).orElse(null);
//        assert wareExtrant != null;
//        wareExtrant.setVider(true);
//        return mxRep.save(wareExtrant);
//    }

    /*
    * Function : Arhiver  un tour - Vider
     */




}
