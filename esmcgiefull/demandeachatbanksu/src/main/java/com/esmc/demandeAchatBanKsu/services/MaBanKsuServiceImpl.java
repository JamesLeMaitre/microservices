package com.esmc.demandeAchatBanKsu.services;

import com.esmc.demandeAchatBanKsu.entities.MaBanKsu;
import com.esmc.demandeAchatBanKsu.entities.TypeMABanKSU;
import com.esmc.demandeAchatBanKsu.feign.KsuRestClient;
import com.esmc.demandeAchatBanKsu.repositories.MaBanKsuRepository;
import com.esmc.demandeAchatBanKsu.repositories.TypeMABanKSURepository;
import com.esmc.demandeAchatBanKsu.servicesInterface.MaBanKsuService;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import utiles.UseFullFunctions;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class MaBanKsuServiceImpl implements MaBanKsuService {
    @Autowired
    private MaBanKsuRepository maBanKsuRepository;

    @Autowired
    private TypeMABanKSURepository typeMABanKSURepository;

    @Autowired
    private KsuRestClient ksuRestClient;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();


    @Override
    public List<MaBanKsu> getMaBanKsu() {

        List<MaBanKsu> ma = maBanKsuRepository.findAll();

        List<MaBanKsu> listMa = new ArrayList<MaBanKsu>();

        for (MaBanKsu m : ma) {
            Ksu k = ksuRestClient.ksuParMaBanKsu(m.getId());
            if (k != null) {
                listMa.add(m);
            }
        }
        return listMa;
    }

    @Override
    public MaBanKsu MaBanKsu(Long id) {
        return maBanKsuRepository.findById(id).orElse(null);
    }

    @Override
    public MaBanKsu saveMabanKsu(Long id, MaBanKsu maBanKsu) {

        TypeMABanKSU t = typeMABanKSURepository.findById(maBanKsu.getTypeMABanKSU().getId()).orElse(null);

        maBanKsu.setTypeMABanKSU(t);

        if(maBanKsu.getPhotoJustificationAdresse()!=null){
            maBanKsu.setPhotoJustificationAdresse(useFullFunctions.getUploadImageByBase64(maBanKsu.getPhotoJustificationAdresse()));
        }

        return maBanKsuRepository.save(maBanKsu);
    }

    @Override
    public void deleteMabanksu(@PathVariable  Long id) {
        maBanKsuRepository.deleteById(id);
    }

    @Override
    public MaBanKsu updateMabanKsu(MaBanKsu maBanKsu) {
        return maBanKsuRepository.save(maBanKsu);
    }

    @Override
    public MaBanKsu findMaBanKSUByTypeMaBanKSu(Long id) {
        return maBanKsuRepository.findMaBanKSUByTypeMaBanKSu(id);
    }

    /**
     * @return
     */
    @Override
    public MaBanKsu getCurrentMaBanKsu() {
        return maBanKsuRepository.findMaBanKsuByIdMax();
    }

    @Override
    public MaBanKsu getMaBanKsuByCodeRepresentant(String code) {
        return maBanKsuRepository.findMaBanKsuByCodeKsuRepresentant(code);
    }
}
