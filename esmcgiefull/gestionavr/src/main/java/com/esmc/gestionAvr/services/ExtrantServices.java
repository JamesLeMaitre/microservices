package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.feign.TypeSupportClient;
import com.esmc.gestionAvr.inputs.ExtrantInput;
import com.esmc.gestionAvr.inputs.ExtrantInputv2;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.servicesInterfaces.ExtrantServiceInterface;

import com.esmc.models.SupportUtilise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExtrantServices implements ExtrantServiceInterface {
    @Autowired
    private  ExtrantRepository extrantRepository;

    @Autowired
    private IntrantRepository intrantRepository;

    @Autowired
    private DetailSupportRepository detailSupportRepository;

    @Autowired
    private TypeSupportClient tpClient;

    @Autowired
    private DataSupportRepostory dataSupportRepostory;

    @Autowired
    private DetailTypeSupportRepository detailTypeSupportRepository;

    @Autowired
    private TypeSmAvrRepository typeSmAvrRepository;

    @Autowired
    private MiddleWareExtrantRepository mxRepo;

    @Autowired
    private MiddleWareExtrantService mdx;

    @Override
    public Extrant addExtrant(Long id, Extrant extrant) {
        extrant.setKsu(id);
        return extrantRepository.save(extrant);
    }

    @Override
    public Extrant updateExtrant(Long id, Extrant extrant) {
        Extrant extrant1 = extrantRepository.findById(id).orElse(null);
        assert extrant1 != null : "ID null";
        extrant1.setSupportEtablie(extrant.isSupportEtablie());
        extrant1.setMontant(extrant.getMontant());
        extrant1.setRefer(extrant.getRefer());
        extrant1.setAvr(extrant.getAvr());
        extrant1.setArchive(extrant.isArchive());
        extrant1.setKsu(extrant.getKsu());
        extrant1.setKsuEmetteur(extrant.getKsuEmetteur());
        extrant1.setKsuRecepteur(extrant.getKsuRecepteur());
        extrant1.setDetailAgrEmetteur(extrant.getDetailAgrEmetteur());
        extrant1.setDetailAgrRecepteur(extrant.getDetailAgrRecepteur());
        extrant1.setPosteEmetteur(extrant.getPosteEmetteur());
        extrant1.setPosteReceveur(extrant.getPosteReceveur());
        extrant1.setTypeSmAvr(extrant.getTypeSmAvr());
        extrant1.setDetailSupport(extrant.getDetailSupport());
        extrant1.setDataInfo(extrant.getDataInfo());
        extrant1.setViewDev(extrant1.isViewDev());

        return extrantRepository.save(extrant1);
    }

    @Override
    public Extrant updateExtrantV2(Long id, Long idDetail, Extrant extrant, String data) {

        Extrant extrant1 = extrantRepository.findById(id).orElse(null);
        System.out.println("Extr"+extrant1);

        SupportUtilise supportUtilise = tpClient.getSupportUtlise(idDetail);
        DataSupport dataSupport =  dataSupportRepostory.getDataSupport(extrant.getDetailSupport().getId());

        System.out.println("DATA SUpp");
        System.out.println(dataSupport);
        DetailSupport detailSupport = new DetailSupport();
        detailSupport.setIdTypeSupport(idDetail);
        detailSupport.setLibelleTypeSupport(supportUtilise.getLibelle());
        detailSupport.setCode(supportUtilise.getCode());

        DetailSupport detailSupport1 = detailSupportRepository.save(detailSupport);
        assert dataSupport != null;
        dataSupport.setData(data);
        dataSupport.setDetailSupport(detailSupport1);
        dataSupportRepostory.save(dataSupport);
        assert extrant1 != null : "ID null";
        extrant1.setSupportEtablie(extrant.isSupportEtablie());
        extrant1.setMontant(extrant.getMontant());
        extrant1.setRefer(extrant.getRefer());
        extrant1.setAvr(extrant.getAvr());
        extrant1.setArchive(extrant.isArchive());
        extrant1.setKsu(extrant.getKsu());
        extrant1.setKsuEmetteur(extrant.getKsuEmetteur());
        extrant1.setKsuRecepteur(extrant.getKsuRecepteur());
        extrant1.setDetailAgrEmetteur(extrant.getDetailAgrEmetteur());
        extrant1.setDetailAgrRecepteur(extrant.getDetailAgrRecepteur());
        extrant1.setPosteEmetteur(extrant.getPosteEmetteur());
        extrant1.setPosteReceveur(extrant.getPosteReceveur());
        extrant1.setTypeSmAvr(extrant.getTypeSmAvr());
        extrant1.setDetailSupport(detailSupport1);
        extrant1.setDataInfo(extrant.getDataInfo());
        extrant1.setViewDev(extrant1.isViewDev());

        return extrantRepository.save(extrant1);
    }

    @Override
    public void deleteExtrant(Long id) {
        extrantRepository.deleteById(id);
    }

    @Override
    public void deleteDev(Long id) {
        Extrant extrant = extrantRepository.findById(id).orElse(null);
        assert extrant != null;
        extrant.setViewDev(true);
        extrantRepository.save(extrant);
    }

    @Override
    public List<Extrant> getAllExtrant() {
        return extrantRepository.getAllV2();
    }

    @Override
    public List<Extrant> recuperatiionExtrantAvr(Long id) {
        return extrantRepository.recuperatiionExtrantAvr(id);
    }

    @Override
    public List<Extrant> ExtrantByKsu(Long id) {
        return extrantRepository.ExtrantByKsu(id);
    }

    @Override
    public List<Extrant> getByIdAvr(Long id) {
        return extrantRepository.getByIdAvr(id);
    }

    @Override
    public List<Extrant> getByIdDetailAgr(Long id) {
        return extrantRepository.getExtrantByDetailAgrFalse(id);
    }

    @Override
    public String getByIdPosteEmetteur(Long id) {
        Extrant  extrantList = extrantRepository.getByPosteEmetteur(id);
        DetailSupport detailSupport = detailSupportRepository.findById(extrantList.getDetailSupport().getId()).orElse(null);
        assert detailSupport != null : "ID null";
        return tpClient.getLibelleById(detailSupport.getIdTypeSupport());
    }

    @Override
    public List<Extrant> getByListPosteEmetteur(Long id) {
        return extrantRepository.getListByPosteEmetteur(id);
    }

    @Override
    public List<Extrant> getListByPosteEmetteurEtablie(Long id) {
        return extrantRepository.getListByPosteEmetteurEtablie(id);
    }

    @Override
    public Extrant getExtrantById(Long id) {
        return extrantRepository.getExtrantByNoArchiveV2(id);
    }

    @Override
    public Extrant getExtrantByArchive(Long id) {
        return extrantRepository.getExtrantArchive(id);
    }

    @Override
    public String getLibelle(Long id) {
        return tpClient.getTypeSupportById(id).getLibelle();
    }

    @Override
    public Extrant addExtrantSupport(ExtrantInput a) {
        Extrant extrant = new Extrant();
        extrant.setDetailAgrEmetteur(a.getDetailAgrEmetteur());
        extrant.setDetailAgrRecepteur(a.getDetailAgrRecepteur());
        extrant.setRefer(a.getRefer());

        DetailSupport detailSupport = new DetailSupport();
        detailSupport.setIdTypeSupport(a.getIdTypeSupport());
        DetailSupport detailSupport1 = detailSupportRepository.save(detailSupport);
        extrant.setDetailSupport(detailSupport1);

        Intrant intrant = new Intrant();
        intrant.setDetailSupport(detailSupport);
        intrant.setDetailAgrEmetteur(a.getDetailAgrEmetteur());
        intrant.setDetailAgrRecepteur(a.getDetailAgrRecepteur());
        intrant.setRefer(a.getRefer());

        intrantRepository.save(intrant);
        return extrantRepository.save(extrant);
    }

    @Override
    public DetailSupport updateExtrantSupport(Long id, ExtrantInput a) {
        DetailSupport detailSupport = detailSupportRepository.findById(id).orElse(null);
        //detailSupport.setData(a.getData());
        assert detailSupport != null;
        return detailSupportRepository.save(detailSupport);
    }

    /** @author JamesLeMaitre
     * @param
     */

    @Override
    public void addExtrantSupportv2(ExtrantInputv2 a) {
            Extrant extrant = new Extrant();
            Intrant intrant = new Intrant();
            intrant.setPosteReceveur(a.getPosteReceveur());
        addExtrantFunction(extrant,a,intrant);
        if (a.getPosteReceveurOther()!=null) {
            Long[] posteRe = a.getPosteReceveurOther();
            if (posteRe.length > 0) {
                for (Long id : posteRe) {
                    Extrant ext = new Extrant();
                    Intrant intr = new Intrant();
                    ext.setPosteReceveurOther(id);
                    intr.setPosteReceveurOther(id);
                    intr.setPosteReceveur(id);
                    addExtrantFunction(ext, a, intr);
                }
            }
        }
    }

    @Override
    public List<Extrant> getExtrantByDetailsSupport(Long id) {
        return extrantRepository.getExtrantByDetailsSupport(id);
    }

    @Override
    public Extrant archivage(Long id) {
        Extrant data = extrantRepository.findById(id).orElse(null);
        assert data != null : "ID null";
        data.setArchive(true);
        mdx.archivage(data.getId());
        return extrantRepository.save(data);
    }

    @Override
    public List<Extrant> listArchivage(Long id) {
        return extrantRepository.listArchiver(id);
    }

    @Override
    public Extrant getByID(Long id) {
        return extrantRepository.findById(id).orElse(null);
    }

    @Override
    public Extrant viderExtrant(Long id) {
        Extrant extrant = extrantRepository.findById(id).orElse(null);
        assert extrant != null;
        extrant.setViewDev(true);
        return extrantRepository.save(extrant);
    }

    @Override
    public Extrant getNoAchive(Long id) {
        return extrantRepository.getNoArchiver(id);
    }



    public void addExtrantFunction(Extrant a, ExtrantInputv2 extrantInputv2, Intrant intrant) {
        a.setPosteEmetteur(extrantInputv2.getPosteEmetteur());
        a.setPosteReceveur(extrantInputv2.getPosteReceveur());
        a.setRefer(extrantInputv2.getRefer());
        a.setKsu(extrantInputv2.getKsu());
        a.setDetailAgrEmetteur(extrantInputv2.getDetailAgrEmetteur());
        a.setDetailAgrRecepteur(extrantInputv2.getDetailAgrRecepteur());
        //a.setDataInfo(extrantInputv2.getDataInfo());

        if(extrantInputv2.getIdTypeSupport()!= null || extrantInputv2.getIdTSupport() == null){
            SupportUtilise supportUtilise = tpClient.getSupportUtlise(extrantInputv2.getIdTypeSupport());
            DetailSupport detailSupport = new DetailSupport();
            detailSupport.setIdTypeSupport(extrantInputv2.getIdTypeSupport());
            detailSupport.setLibelleTypeSupport(supportUtilise.getLibelle());
            detailSupport.setCode(supportUtilise.getCode());

            DetailSupport detailSupport1 = detailSupportRepository.save(detailSupport);
            DataSupport datas = new DataSupport();
            datas.setDetailSupport(detailSupport1);
            datas.setData(extrantInputv2.getData());
            dataSupportRepostory.save(datas);

            a.setDetailSupport(detailSupport1);

            intrant.setDetailSupport(detailSupport);

        } else if(extrantInputv2.getIdTSupport() !=null || extrantInputv2.getIdTypeSupport() == null){

            TypeSmAvr typeSmAvr = typeSmAvrRepository.findById(extrantInputv2.getIdTSupport()).orElse(null);

            DetailTypeSupport detailTypeSupport = new DetailTypeSupport();
            detailTypeSupport.setIdTSupport(typeSmAvr.getId());
            detailTypeSupport.setLibelleTypeSupport(typeSmAvr.getLibelle());
            detailTypeSupport.setCode(typeSmAvr.getCode());

            DetailTypeSupport detailTypeSupport1 = detailTypeSupportRepository.save(detailTypeSupport);
            DataSupport datas = new DataSupport();
            datas.setDetailTypeSupport(detailTypeSupport1);
            datas.setData(extrantInputv2.getData());
            dataSupportRepostory.save(datas);

            a.setDetailTypeSupport(detailTypeSupport1);
            a.setTypeSmAvr(typeSmAvr);

            intrant.setDetailTypeSupport(detailTypeSupport1);
            intrant.setTypeSmAvr(typeSmAvr);
        }
        intrant.setPosteEmetteur(extrantInputv2.getPosteEmetteur());
       // intrant.setPosteReceveur(extrantInputv2.getPosteReceveur());
        intrant.setRefer(extrantInputv2.getRefer());
        intrant.setKsu(extrantInputv2.getKsu());
        intrant.setDetailAgrEmetteur(extrantInputv2.getDetailAgrEmetteur());
        intrant.setDetailAgrRecepteur(extrantInputv2.getDetailAgrRecepteur());
       // intrant.setDataInfo(extrantInputv2.getDataInfo());

        intrantRepository.save(intrant);
        extrantRepository.save(a);
    }

    @Override
    public List<Extrant> getExtrantByDetailAgrFalse(Long id) {
        return extrantRepository.getExtrantByDetailAgrFalse(id);
    }

    @Override
    public List<Extrant> getExtrantByDetailAgrTrue(Long id) {
        return extrantRepository.getExtrantByDetailAgrTrue(id);
    }

    @Override
    public Extrant archivageByIdDetailAgr(Long id) {
        Extrant data = extrantRepository.findById(id).orElse(null);
        assert data != null : "ID null";
        data.setArchive(true);
        return extrantRepository.save(data);
    }

    @Override
    public int countBy(Long id) {
        return extrantRepository.countTrue(id);
    }


    // =========================================================================================================================================
    /*
    * New Version of Save Extrant
     */

    @Override
    public void addExtrantSupportv3(ExtrantInputv2 a) {
        Extrant extrant = new Extrant();
        MiddleWareExtrant mx = new MiddleWareExtrant();
       // Intrant intrant = new Intrant();
        //intrant.setPosteReceveur(a.getPosteReceveur());
        mx.setIntervenant(a.getPosteReceveur());
        addExtrantFunctionv2(extrant,a,mx);
        if (a.getPosteReceveurOther()!=null) {
            Long[] posteRe = a.getPosteReceveurOther();
            if (posteRe.length > 0) {
                for (Long id : posteRe) {
                    Extrant ext = new Extrant();
                    MiddleWareExtrant mxx = new MiddleWareExtrant();
                    //Intrant intr = new Intrant();
                    ext.setPosteReceveurOther(id);
//                    intr.setPosteReceveurOther(id);
//                    intr.setPosteReceveur(id);

                    // mx.setIdExtrant(extrant.getId());
                   // mx.setStep(true);
                    mxx.setIntervenant(id);
                    addExtrantFunctionv2(ext, a,mxx);
                }
            }
        }
    }
    public void addExtrantFunctionv2(Extrant a, ExtrantInputv2 extrantInputv2,MiddleWareExtrant mxE) {
         MiddleWareExtrant mx = new MiddleWareExtrant();
        a.setPosteEmetteur(extrantInputv2.getPosteEmetteur());
        a.setPosteReceveur(extrantInputv2.getPosteReceveur());
        a.setRefer(extrantInputv2.getRefer());
        a.setKsu(extrantInputv2.getKsu());
        a.setDetailAgrEmetteur(extrantInputv2.getDetailAgrEmetteur());
        a.setDetailAgrRecepteur(extrantInputv2.getDetailAgrRecepteur());
        a.setArchive(false);
        a.setDataInfo(extrantInputv2.getDataInfo());

        if(extrantInputv2.getIdTypeSupport()!= null || extrantInputv2.getIdTSupport() == null){
            SupportUtilise supportUtilise = tpClient.getSupportUtlise(extrantInputv2.getIdTypeSupport());
            DetailSupport detailSupport = new DetailSupport();
            detailSupport.setIdTypeSupport(extrantInputv2.getIdTypeSupport());
            detailSupport.setLibelleTypeSupport(supportUtilise.getLibelle());
            detailSupport.setCode(supportUtilise.getCode());

            DetailSupport detailSupport1 = detailSupportRepository.save(detailSupport);
            DataSupport datas = new DataSupport();
            datas.setDetailSupport(detailSupport1);
            datas.setData(extrantInputv2.getData());
            dataSupportRepostory.save(datas);
            mx.setIdDataSupport(datas.getId());

            a.setDetailSupport(detailSupport1);

            //intrant.setDetailSupport(detailSupport);

        } else
        {
            TypeSmAvr typeSmAvr = typeSmAvrRepository.findById(extrantInputv2.getIdTSupport()).orElse(null);

            DetailTypeSupport detailTypeSupport = new DetailTypeSupport();
            assert typeSmAvr != null;
            detailTypeSupport.setIdTSupport(typeSmAvr.getId());
            detailTypeSupport.setLibelleTypeSupport(typeSmAvr.getLibelle());
            detailTypeSupport.setCode(typeSmAvr.getCode());

            DetailTypeSupport detailTypeSupport1 = detailTypeSupportRepository.save(detailTypeSupport);
            DataSupport datas = new DataSupport();
            datas.setDetailTypeSupport(detailTypeSupport1);
            datas.setData(extrantInputv2.getData());
            dataSupportRepostory.save(datas);
            mx.setIdDataSupport(datas.getId());

            a.setDetailTypeSupport(detailTypeSupport1);
            a.setTypeSmAvr(typeSmAvr);

           // intrant.setDetailTypeSupport(detailTypeSupport1);
           // intrant.setTypeSmAvr(typeSmAvr);
        }
        //intrant.setPosteEmetteur(extrantInputv2.getPosteEmetteur());
        // intrant.setPosteReceveur(extrantInputv2.getPosteReceveur());
        //intrant.setRefer(extrantInputv2.getRefer());
        //intrant.setKsu(extrantInputv2.getKsu());
        //intrant.setDetailAgrEmetteur(extrantInputv2.getDetailAgrEmetteur());
        //intrant.setDetailAgrRecepteur(extrantInputv2.getDetailAgrRecepteur());
        // intrant.setDataInfo(extrantInputv2.getDataInfo());
        a.setViewDev(false);
        extrantRepository.save(a);
        //mx.setArchiver(false);
       // mx.setVider(false);
        mx.setIdExtrant(a.getId());
        mx.setStep(true);
        mx.setIntervenant(mxE.getIntervenant());
        mx.setPosteEmetteur(a.getPosteEmetteur());

        mxRepo.save(mx);

        //intrantRepository.save(intrant);

    }

}
