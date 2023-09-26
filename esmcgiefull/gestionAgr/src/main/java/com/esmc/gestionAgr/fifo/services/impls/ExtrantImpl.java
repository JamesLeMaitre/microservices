package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.*;
import com.esmc.gestionAgr.fifo.models.ExtrantInputv2;
import com.esmc.gestionAgr.fifo.repositories.*;
import com.esmc.gestionAgr.fifo.services.DetailSupportRepository;
import com.esmc.gestionAgr.fifo.services.ExtrantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExtrantImpl implements ExtrantService {
    private final ExtrantRepository extrantRepository;
    private final SupportUtiliseRepository supportUtiliseRepository;
    private final DetailTypeSupportRepository detailTypeSupportRepository;
    private final TypeSmAvrRepository typeSmAvrRepository;
    private final MiddleWareExtrantRepository middleWareExtrantRepository;
    private DetailSupportRepository detailSupportRepository;
    private DataSupportRepository dataSupportRepository;

    @Override
    public void addExtrantSupportv3(ExtrantInputv2 a) {
        Extrant extrant = new Extrant();
        MiddleWareExtrant mx = new MiddleWareExtrant();

        mx.setIntervenant(a.getPosteReceveur());
        addExtrantFunctionv2(extrant, a, mx);
        if (a.getPosteReceveurOther() != null) {
            Long[] posteRe = a.getPosteReceveurOther();
            for (Long id : posteRe) {
                Extrant ext = new Extrant();
                MiddleWareExtrant mxx = new MiddleWareExtrant();

                ext.setPosteReceveurOther(id);

                mxx.setIntervenant(id);
                addExtrantFunctionv2(ext, a, mxx);
            }
        }
    }

    public void addExtrantFunctionv2(Extrant a, ExtrantInputv2 extrantInputv2, MiddleWareExtrant mxE) {
        MiddleWareExtrant mx = new MiddleWareExtrant();
        a.setPosteEmetteur(extrantInputv2.getPosteEmetteur());
        a.setPosteReceveur(extrantInputv2.getPosteReceveur());
        a.setRefer(extrantInputv2.getRefer());
        a.setKsu(extrantInputv2.getKsu());
        a.setDetailAgrEmetteur(extrantInputv2.getDetailAgrEmetteur());
        a.setDetailAgrRecepteur(extrantInputv2.getDetailAgrRecepteur());
        a.setArchive(false);
        a.setDataInfo(extrantInputv2.getDataInfo());

        if (extrantInputv2.getIdTypeSupport() != null || extrantInputv2.getIdTSupport() == null) {

            SupportUtilise supportUtilise = supportUtiliseRepository.findById(extrantInputv2.getIdTypeSupport()).get();
            DetailSupport detailSupport = new DetailSupport();
            detailSupport.setIdTypeSupport(extrantInputv2.getIdTypeSupport());
            detailSupport.setLibelleTypeSupport(supportUtilise.getLibelle());
            detailSupport.setCode(supportUtilise.getCode());

            DetailSupport detailSupport1 = detailSupportRepository.save(detailSupport);
            DataSupport datas = new DataSupport();
            datas.setDetailSupport(detailSupport1);
            datas.setData(extrantInputv2.getData());
            dataSupportRepository.save(datas);
            mx.setIdDataSupport(datas.getId());
            a.setDetailSupport(detailSupport1);

        } else {
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
            dataSupportRepository.save(datas);
            mx.setIdDataSupport(datas.getId());

            a.setDetailTypeSupport(detailTypeSupport1);
            a.setTypeSmAvr(typeSmAvr);

        }

        a.setViewDev(false);
        extrantRepository.save(a);
        mx.setIdExtrant(a.getId());
        mx.setStep(true);
        mx.setIntervenant(mxE.getIntervenant());
        mx.setPosteEmetteur(a.getPosteEmetteur());

        middleWareExtrantRepository.save(mx);
    }
}
