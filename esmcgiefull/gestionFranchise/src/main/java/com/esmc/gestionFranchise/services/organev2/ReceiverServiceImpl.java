package com.esmc.gestionFranchise.services.organev2;


import com.esmc.gestionFranchise.datafomater.*;
import com.esmc.gestionFranchise.entities.*;
import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.entities.organev2.PosteTypeSupport;
import com.esmc.gestionFranchise.entities.organev2.TypeSupport;
import com.esmc.gestionFranchise.repositories.*;
import com.esmc.gestionFranchise.repositories.organev2.PosteRepo;
import com.esmc.gestionFranchise.repositories.organev2.PosteTypeSupportRepo;
import com.esmc.gestionFranchise.repositories.organev2.TypeSupportRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.ReceiverInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReceiverServiceImpl implements ReceiverInterface {



    @Autowired

    private FichePosteRepo fichePosteRepo;

    @Autowired
    private TacheRepo tacheRepo;

    @Autowired
    private TypeSupportRepo typeSupportRepo;

    @Autowired
    private PosteTypeSupportRepo posteTypeSupportRepo;


    @Autowired
    private ManuelProcedureRepo manuelProcedureRepo;

    @Autowired
    private TableDescriptionEpRepo tableDescriptionEpRepo;

    @Autowired
    private  IntervenantRepo intervenantRepo;

    @Autowired
    private PosteRepo  posteRepo;

    @Override
    public void loadReceiverJSON(AgentFichePosteJSON[] data) {
        for( AgentFichePosteJSON ag:data){

            Poste poste= posteRepo.findById(ag.getId()).orElse(null);

            FichePoste fichePoste = new FichePoste();
            fichePoste.setLibelle(ag.getFichePoste().getLibelle());
            fichePoste.setPoste(poste);


            FichePoste savedFichePost = fichePosteRepo.save(fichePoste);//generation 3

            for( TacheJSON tachesValue:ag.getFichePoste().getTaches()){
                Tache tache = new Tache();
                tache.setLibelle(tachesValue.getLibelle());
                tache.setFichePoste(savedFichePost);
                Tache savedTache = tacheRepo.save(tache);//generation 3

                for( ProcedureJSON procedureValue:tachesValue.getProcedure()){
                    ManuelProcedure manuelProcedure = new ManuelProcedure();
                    manuelProcedure.setLibelle(procedureValue.getLibelle());
                    manuelProcedure.setTache(savedTache);
                    ManuelProcedure savedProcedure = manuelProcedureRepo.save(manuelProcedure);//generation 3
                    for( TdepJSON tdeValue:procedureValue.getTdep()){
                        TableDescriptionEp tableDescriptionEp = new TableDescriptionEp();
                        tableDescriptionEp.setLibelle(tdeValue.getLibelle());
                        tableDescriptionEp.setManuelProcedure(savedProcedure);
                        TableDescriptionEp savedTde = tableDescriptionEpRepo.save(tableDescriptionEp);//generation 3


                        for( Long intervenantValue:tdeValue.getIntervenants()){
                            Intervenant intervenant = new Intervenant();
                            Poste poste1= posteRepo.findById(intervenantValue).orElse(null);
                            intervenant.setPoste(poste1);
                            intervenant.setLibelle(poste1.getLibelle());
                            intervenant.setTableDescriptionEp(savedTde);
                             intervenantRepo.save(intervenant);//generation 3
                        }
                    }
                }
            }
        }

    }

    @Override
    public void loadtypeSupportReceiverJSON(String[] data) {
        for (String support : data){
            TypeSupport typeSupport = new TypeSupport();
            typeSupport.setLibelle(support);
            typeSupportRepo.save(typeSupport);
        }
    }

    @Override
    public void loadPosteTypeSupportResource(PosteTypeStructureJson[] data) {
        for (PosteTypeStructureJson posteTypeStructureJson : data){
            TableDescriptionEp tableDescriptionEp = tableDescriptionEpRepo.findById(posteTypeStructureJson.getTdep()).orElse(null);
            Poste posteSender = posteRepo.findById(posteTypeStructureJson.getEmetteur()).orElse(null);
            Poste posteReciever = posteRepo.findById(posteTypeStructureJson.getRecepteur()).orElse(null);
            for (Long support : posteTypeStructureJson.getSupports()){
                TypeSupport typeSupport = typeSupportRepo.findById(support).orElse(null);
                PosteTypeSupport posteTypeSupport = new PosteTypeSupport();
                posteTypeSupport.setSenderPoste(posteSender);
                posteTypeSupport.setReceiverPoste(posteReciever);
                posteTypeSupport.setTypeSupport(typeSupport);
                posteTypeSupport.setTableDescriptionEp(tableDescriptionEp);
                posteTypeSupportRepo.save(posteTypeSupport);
            }
        }
    }
    @Override
    public void loadPosteSupportResourceJson(AgentFichePosteJSON[] data) {
        for( AgentFichePosteJSON ag:data){

            Poste poste= posteRepo.findById(ag.getId()).orElse(null);

            FichePoste fichePoste = new FichePoste();
            fichePoste.setLibelle(ag.getFichePoste().getLibelle());
            fichePoste.setPoste(poste);


            FichePoste savedFichePost = fichePosteRepo.save(fichePoste);//generation 3

            for( TacheJSON tachesValue:ag.getFichePoste().getTaches()){
                Tache tache = new Tache();
                tache.setLibelle(tachesValue.getLibelle());
                tache.setFichePoste(savedFichePost);
                Tache savedTache = tacheRepo.save(tache);//generation 3

                for( ProcedureJSON procedureValue:tachesValue.getProcedure()){
                    ManuelProcedure manuelProcedure = new ManuelProcedure();
                    manuelProcedure.setLibelle(procedureValue.getLibelle());
                    manuelProcedure.setTache(savedTache);
                    ManuelProcedure savedProcedure = manuelProcedureRepo.save(manuelProcedure);//generation 3
                    for( TdepJSON tdeValue:procedureValue.getTdep()){
                        TableDescriptionEp tableDescriptionEp = new TableDescriptionEp();
                        tableDescriptionEp.setLibelle(tdeValue.getLibelle());
                        tableDescriptionEp.setManuelProcedure(savedProcedure);
                        TableDescriptionEp savedTde = tableDescriptionEpRepo.save(tableDescriptionEp);//generation 3


                        for( Long intervenantValue:tdeValue.getIntervenants()){
                            Intervenant intervenant = new Intervenant();
                            Poste poste1= posteRepo.findById(intervenantValue).orElse(null);
                            intervenant.setPoste(poste1);
                            intervenant.setLibelle(savedTde.getLibelle());
                            intervenant.setTableDescriptionEp(savedTde);
                            intervenantRepo.save(intervenant);//generation 3
                        }

                        for (SupportJSON supportJSON : tdeValue.getSupport()){
                            TypeSupport typeSupport = typeSupportRepo.findById(supportJSON.getId()).orElse(null);

                            for (UtilisateurJSON utilisateurJSON : supportJSON.getUtilisateur()){
                                //System.out.println("maa"+utilisateurJSON.getEmetteur());
                                Poste posteSender = posteRepo.findById(utilisateurJSON.getEmetteur()).orElse(null);
                                for (Long emetteur : utilisateurJSON.getRecepteur() ) {
                                    Poste posteReciever = posteRepo.findById(emetteur).orElse(null);
                                    PosteTypeSupport posteTypeSupport = new PosteTypeSupport();
                                    posteTypeSupport.setSenderPoste(posteSender);
                                    posteTypeSupport.setReceiverPoste(posteReciever);
                                    posteTypeSupport.setTypeSupport(typeSupport);
                                    posteTypeSupport.setTableDescriptionEp(savedTde);
                                    posteTypeSupportRepo.save(posteTypeSupport);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
