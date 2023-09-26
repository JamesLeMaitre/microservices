package com.esmc.gestionFranchise.services.organev2;

import com.esmc.gestionFranchise.entities.AgentRecruteur;
import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.feign.AchatFranchiseRestClient;
import com.esmc.gestionFranchise.feign.AgrRestClient;
import com.esmc.gestionFranchise.feign.KsuRestClient;
import com.esmc.gestionFranchise.inputs.IdsClass;
import com.esmc.gestionFranchise.repositories.AgentRecruteurRepo;
import com.esmc.gestionFranchise.repositories.DetailsAgrRepo;
import com.esmc.gestionFranchise.repositories.organev2.AgentPosteRepo;
import com.esmc.gestionFranchise.repositories.organev2.PosteRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.MonitorServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Formatter;
import com.esmc.models.FranchiseReferentiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.FirstElement;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MonitorFranchiseService extends FirstElement implements MonitorServiceInterface  {
    @Autowired
    private AgentRecruteurRepo agentRecruteurRepo;

    @Autowired
    AgrRestClient agrRestClient;

    @Autowired
    AgentPosteRepo agentPosteRepo;

    @Autowired
    private DetailsAgrRepo detailsAgrRepo;
    @Autowired
    PosteRepo posteRepo;

    @Autowired
    private AchatFranchiseRestClient achatFranchiseRestClient;

    @Autowired
    private KsuRestClient ksuRestClient;


    @Override
    public AgentPoste franchise_master_generator(Long idFranchise, Long idPoste) {
        String codePourFaire = ""+System.currentTimeMillis();
        AgentRecruteur agentRecruteur = new AgentRecruteur();
//        agentRecruteur.setIdDetailsAgrFranchiser();
        agentRecruteur.setCodePovoirFaire(codePourFaire);
//      agentRecruteur.setIdKsu(agentRecruteur.getIdKsu());
//        agentRecruteur.setIdDetailAgr(agentPoste.getIdDetailAgr());
        AgentRecruteur agentRecruteurSaved =  agentRecruteurRepo.save(agentRecruteur);
       // List<Ksu> lk = ksuRestClient.listKsuById(agentRecruteurSaved.getIdKsu());
       // Ksu k = getFirstElement(lk);
       // Formatter<DetailsAgr> dt = agrRestClient.findDetailAgrByKSU(k.getId());
        Poste poste = posteRepo.findById(idPoste).orElse(null);

        AgentPoste agentPoste1 = new AgentPoste();
        agentPoste1.setIdFranchise(idFranchise);
        agentPoste1.setPoste(poste);
       // agentPoste1.setIdDetailAgr(dt.getData().getId());
        agentPoste1.setAgentRecruteur(agentRecruteurSaved);
        AgentPoste agentPostesaved = agentPosteRepo.save(agentPoste1);
        return agentPostesaved;
    }

    @Override
    public AgentPoste franchise_master_generatorv2(Long idDetailsAgrFranchiser, Long idPoste, Long idDetailAgr) {

        Formatter<DetailsAgr> agrFormatter =  agrRestClient.findDetailAgrByKSU(idDetailsAgrFranchiser);
        Formatter<DetailsAgr> agrFormatterClient =  agrRestClient.findDetailAgrByKSU(idDetailAgr);


        // Check if is Franchiser
        if(agrFormatter.getData().isFranchise()){
            Poste poste = posteRepo.findById(idPoste).orElse(null);
            String result = poste.getCode().substring(0,3).toUpperCase()+"-";
            String codePourFaire = result+System.currentTimeMillis();
           // String initial = s.getSalleFormation().getLibelle();
            AgentRecruteur agentRecruteur = new AgentRecruteur();
            agentRecruteur.setCodePovoirFaire(codePourFaire);
            agentRecruteur.setIdDetailsAgrFranchiser(agrFormatter.getData().getId());
            agentRecruteur.setIdAgr(idDetailAgr);


              agentRecruteur.setIdKsu(agrFormatterClient.getData().getKsu());
            System.out.println(agrFormatterClient.getData().getKsu());
// ->       agentRecruteur.setIdDetailAgr(agentPoste.getIdDetailAgr());
        AgentRecruteur agentRecruteurSaved =  agentRecruteurRepo.save(agentRecruteur);


        AgentPoste agentPoste1 = new AgentPoste();
        agentPoste1.setIdFranchise(idDetailsAgrFranchiser);
        agentPoste1.setPoste(poste);
        agentPoste1.setAgentRecruteur(agentRecruteurSaved);
        agentPoste1.setIdKsu(agrFormatterClient.getData().getKsu());
        agentPoste1.setIdDetailAgrFranchise(idDetailsAgrFranchiser);
        agentPoste1.setIdDetailAgr(idDetailAgr);
        AgentPoste agentPostesaved = agentPosteRepo.save(agentPoste1);
        return agentPostesaved;
        }
        return null;
    }

    @Override
    public AgentPoste franchise_affectation_agent(Long idAgentPoste, Long idDetailAgr) {
        AgentPoste agentPoste = agentPosteRepo.findById(idAgentPoste).orElse(null);
        //agentPoste.setIdKsu(idDetailAgr);

        String codePourFaire = System.currentTimeMillis()+"-"+idDetailAgr;
        AgentRecruteur agentRecruteur = agentRecruteurRepo.findById(agentPoste.getAgentRecruteur().getId()).orElse(null);
        agentRecruteur.setCodePovoirFaire(codePourFaire);
        agentRecruteur.setIdDetailsAgrFranchiser(idDetailAgr);
        agentPoste.setIdDetailAgr(idDetailAgr);
        agentRecruteurRepo.save(agentRecruteur);
        AgentPoste agentPostesaved = agentPosteRepo.save(agentPoste);
        return agentPostesaved;
    }

    @Override
    public List<AgentPoste> list_franchise_affectation_agent(Long idFranchise) {
        return agentPosteRepo.findByFranchise(idFranchise);
    }

    @Override
    public List<FranchiseReferentiel> list_franchise_by_idDetailAgr(Long idDetailAgr) {
        List<Long> list_franchise = agentPosteRepo.list_franchise_by_idDetailAgr(idDetailAgr);
        System.out.println(list_franchise);
        IdsClass idsClass = new IdsClass();
        idsClass.setIds(list_franchise);
        return achatFranchiseRestClient.listFranchiseReferenciel(idsClass);
    }

    @Override
    public List<AgentRecruteur> getCodePouvoirFaire(Long id) {
        List<AgentPoste> agentPoste = agentRecruteurRepo.getAgentPoste(id);
        if (agentPoste.isEmpty()){
            return null;
        }
        List<AgentRecruteur> agentRecruteur =new ArrayList<>();

        for (AgentPoste a : agentPoste){
            AgentRecruteur ar = agentRecruteurRepo.findById(a.getAgentRecruteur().getId()).orElse(null);
            agentRecruteur.add(ar);
        }
        return agentRecruteur;
    }

    @Override
    public AgentRecruteur get(Long idAgr, Long idPoste, Long idDetailsAgrFranchiser) {
        //Formatter<DetailsAgr> agrFormatterClient =  agrRestClient.findDetailAgrByKSU(idAgr);
        //agentRecruteur.setIdKsu(agrFormatterClient.getData().getKsu());

        AgentPoste poste = agentPosteRepo.getAgentPoste(idAgr,idPoste,idDetailsAgrFranchiser);
        AgentRecruteur agentRecruteur = agentRecruteurRepo.findById(poste.getAgentRecruteur().getId()).orElse(null);
//        Formatter<DetailsAgr> agrFormatterClient =  agrRestClient.findDetailAgrByKSU(idAgr);
//        System.out.println("======================");
//        System.out.println(agrFormatterClient.getData().getKsu());
//
//        Long idKsu = agrFormatterClient.getData().getKsu();
//        System.out.println(idKsu);
//
//        AgentRecruteur ag = agentRecruteurRepo.getCodePv(idAgr,idDetailsAgrFranchiser,idKsu);
        return agentRecruteur;
    }

    @Override
    public AgentPoste get_franchise_by_codePouvoirFaire(String codePouvoirFaire) {
        AgentRecruteur agentRecruteur = agentRecruteurRepo.findByCodePouvoirFaire(codePouvoirFaire);
        AgentPoste agentPoste =  agentPosteRepo.findByAgentRecruteur(agentRecruteur.getId());
        return agentPoste;
    }


}
