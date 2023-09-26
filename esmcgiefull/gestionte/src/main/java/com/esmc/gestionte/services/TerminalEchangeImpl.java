package com.esmc.gestionte.services;


import com.esmc.gestionte.entities.TerminalEchange;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.feign.AchatKsuClientRest;
import com.esmc.gestionte.feign.DetailAgrRestClient;
import com.esmc.gestionte.feign.KsuRestClient;
import com.esmc.gestionte.feign.PayementRestClient;
import com.esmc.gestionte.repositories.ParametreRepository;
import com.esmc.gestionte.repositories.TerminalEchangeRepository;
import com.esmc.gestionte.serviceinterface.DetailSMEnchangeService;
import com.esmc.gestionte.serviceinterface.TerminalEchangeService;
import com.esmc.models.BanKsu;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import com.esmc.models.Settings;
import constants.SettingsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Amemorte
 * @since 05/05/2022
 */


@Service
public class TerminalEchangeImpl implements TerminalEchangeService {

    @Autowired
    private TerminalEchangeRepository terminalEchangeRepository;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private ParametreRepository parametreRepository;

    @Autowired
    private PayementRestClient payementRestClient;

    @Autowired
    private AchatKsuClientRest achatKsuClientRest;

    @Autowired
    private DetailSMEnchangeService detailSMEnchangeService;


    /**
     * @param id
     * @return
     */
    @Override
    public TerminalEchange teByDetailAgr(Long id) {
        return terminalEchangeRepository.findByDetailAgr(id);
    }


    @Override
    public List<TerminalEchange> getSTerminalEchange() {
        return  terminalEchangeRepository.findAll();
    }

    @Override
    public void addNewTerminalEchange(List<DetailsAgr> list) throws Exceptions {

        for (DetailsAgr a:list) {
            TerminalEchange per = new TerminalEchange();
            per.setDateCreate(new Date());
            per.setDateUpdate(new Date());
            per.setDetailAgr(a.getId());
            terminalEchangeRepository.save(per);
        }

    }

    @Override
    public void deleteTerminalEchange(Long id) throws Exceptions {
        boolean exists = terminalEchangeRepository.existsById(id);
        if(!exists)
            throw  new Exceptions(Exceptions.alertGeneralException("agr dont l'id "+id+"n'existe pas "));

        terminalEchangeRepository.deleteById(id);

    }

    @Override
    public void updateTerminalEchange(Long id, TerminalEchange terminalEchange) throws Exceptions {

        if(!isPresent(terminalEchange.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        TerminalEchange terminalEchange1=getById(terminalEchange.getId());

        terminalEchange1.setDateUpdate(new Date());
        terminalEchange1.setDetailAgr(terminalEchange.getDetailAgr());



        //update detailSMEnchange
        terminalEchange=terminalEchangeRepository.save(terminalEchange1);

        if (terminalEchange==null){
            throw new Exceptions(Exceptions.alertGeneralException("entrer les valeurs"));
        }
    }

    @Override
    public TerminalEchange getById(Long id) throws Exceptions {
        if(!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'existe pas"));
        Optional<TerminalEchange> opad=terminalEchangeRepository.findById(id);
        return  opad.get();
    }


    @Override
    public Double jour(Double montant)  {
        Double montantM=0.0;

        montantM=(montant*5.6)/8;

        System.out.println("montant par jour : "+montantM);

        return montantM;
    }

    @Override
    public Double limitee_11_2(Double montant)   {

        Double montantA=0.0;
        montantA=(montant*5.6)/8;

        return montantA;

    }

    @Override
    public Double limitee_22_4(Double montant)   {
        Double montantG=0.0;
        montantG=(montant*11.2)/5.6;
        return montantG;
    }

    @Override
    public Double[] illimitee_22_4(Double montant){

        Double marchandPourSoi=0.0,
                montantMarchand=0.0,montantNonMarchand=0.0,
                montantRSoi=0.0,montantRM=0.0,montantRN=0.0;

        marchandPourSoi=montant*22.4;
        montantRSoi=marchandPourSoi;

        montantMarchand=montant*48.125;
        montantRM=montantMarchand;

        montantNonMarchand=montantMarchand*60;
        montantRN=montantNonMarchand;


        Double[] resultat={
                montantRSoi,
                montantRM,
                montantRN
        };


        return resultat;
    }

    @Override
    public Double bcnrPrk(Double montant) {

        Settings stpck = payementRestClient.getSettingById(SettingsConstant.PARAM_PCK);
        Settings stprk = payementRestClient.getSettingById(SettingsConstant.PARAM_PRK);

        Double PCK = Double.parseDouble(stpck.getValue());
        Double PRK = Double.parseDouble(stprk.getValue());

       Double montantBCnr = montant * (PCK / PRK);

        return montantBCnr;
    }

    @Override
    public Double bcnrPrk7(Double montant) {

        Settings stpck = payementRestClient.getSettingById(SettingsConstant.PARAM_PCK);
        Settings stprk = payementRestClient.getSettingById(SettingsConstant.PARAM_PRK_FRANCHISE);


        Double PCK = Double.parseDouble(stpck.getValue());
        Double PRK = Double.parseDouble(stprk.getValue());

        Double montantBCnr =( montant * PCK) / PRK;
        System.out.println(montant);
        System.out.println("Montant : "+montantBCnr);

        return montantBCnr;
    }

    @Override
    public Double margeMABAn(Double montant) {

        Settings stpck = payementRestClient.getSettingById(SettingsConstant.PARAM_PCK);
        Settings stprk = payementRestClient.getSettingById(SettingsConstant.PARAM_PRK_MARGE);
        Double PCK = Double.parseDouble(stpck.getValue());
        Double PRK = Double.parseDouble(stprk.getValue());
        Double montantBCnr =( montant * PRK) / PCK;
        return montantBCnr;
    }

    @Override
    public Double MPRgBAnEntrerM(Double MPRgBAn) throws Exceptions {
        Double MPRgBAnEntrerMN = 0.33 * MPRgBAn + MPRgBAn;
        return MPRgBAnEntrerMN;
    }

    @Override
    public Double encaissementBAn(Double BPS, Double PCK, Double PRK) throws Exceptions {

        Double BAn=BPS*PCK/PRK;
        System.out.println(BAn);
        return BAn;
    }

    @Override
    public Double dencaissementBAn(Double BPS, Double PCK, Double PRK,String typeBPS) throws Exceptions {

        Double decaissement=0.0;
        if (typeBPS.equals("TTC")){

            decaissement=BPS*0.18;

        }else {

            decaissement=(BPS*0.18)+BPS;
        }
        System.out.println(decaissement);
        return decaissement;
    }

    /**
     * @param id
     */

    /**
     * @param id
     * @return
     * @throws Exceptions
     */
    @Override
    public TerminalEchange findByIdDetailAgr(Long id)  {
        return terminalEchangeRepository.findByDetailAgr(id);
    }

    /**
     * @param id
     */
    @Override
    public TerminalEchange creationTeByAgr(Long id) {

        List<DetailsAgr> listDetailA = detailAgrRestClient.listDetailAgrParKsu(id);


        TerminalEchange te1 = null;

        if (listDetailA.size() > 0) {

            for (DetailsAgr d: listDetailA) {
                TerminalEchange te = new TerminalEchange();
                te.setDetailAgr(d.getId());
                te1 = terminalEchangeRepository.save(te);

            }
            Ksu k = ksuRestClient.getById(id);
//            BanKsu b = achatKsuClientRest.getBanKsuByCodeBan(k.getCodeBanKsu());

//            try {
//                Double values = Double.valueOf(b.getTotal());
//
//                detailSMEnchangeService.generatedCircuitMevBGCs(te1.getId(), values);
//
//            } catch (Exception e) {
//                System.out.println(e);
//            }
        }
        return te1;
    }


    public boolean isPresent(Long id){
        Optional<TerminalEchange> opab=terminalEchangeRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }
}
