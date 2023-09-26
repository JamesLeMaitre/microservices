package com.esmc.gestionMembre.services;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.feign.KsuRestClient;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.repositories.*;
import com.esmc.gestionMembre.serviceInterfaces.EsmcSarluServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.ExistenceMembreServiceInterface;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.*;

@Service
@Transactional
public class EsmcSarluService implements EsmcSarluServiceInterface {

    @Autowired
    private IntegrateurRepository integrateurRepository;

    @Autowired
    private SouscriptionRepository souscriptionRepository;

    @Autowired
    private BonNeutreRepository bonNeutreRepository;

    @Autowired
    private BonNeutreDetailRepository bonNeutreDetailRepository;

    @Autowired
    private CompteCreditRepository compteCreditRepository;

    @Autowired
    private GcpRepository gcpRepository;

    @Autowired
    private TraiteRepository traiteRepository;

    @Autowired
    private TpagcpRepository tpagcpRepository;

    @Autowired
    private ExistenceMembreServiceInterface existenceMembreServiceInterface;

    @Autowired
    private KsuRestClient ksuRestClient;


    /**
     * @param code
     * @return
     */

    /**
     * @author Amemorte

     *
     *  reviews et amelioration
     */
    @Override
    public List<Souscription> getSouscrition(String code, Long idKsu) {

        List<Souscription> souscriptions = new ArrayList<>();



        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);


        Ksu k = ksuRestClient.getById(idKsu);


        System.out.println("ksu "+k);


        System.out.println("Membre  "+membrePhysique);


        List<Integrateur> listInteg = integrateurRepository.listpassifsIntegrateur(code);





        if (code.contains("P") == true) {

            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            //String nomk = k.getNom().replace(" ", "");


//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");





            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                if (listInteg.size() >  0) {
                    for (Integrateur i: listInteg) {

                        Souscription s ;

                        System.out.println("idSous1 : "+i.getIntegrateurSouscription());

                        s = souscriptionRepository.passifsESMCSARLUSouscription(i.getIntegrateurSouscription());

                        // System.out.println("idSous2 : "+s.getSouscriptionId());

                        if (s != null) {
                            souscriptions.add(s);

                        }
                        return souscriptions;
                    }
                }
            }
        }
        return null;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Double sommeSouscritionPassifs(String code) {

        Double sommeMontant = 0.0;

        List<Integrateur> listInteg = integrateurRepository.listpassifsIntegrateur(code);

        if (listInteg.size() >  0) {
            for (Integrateur i: listInteg) {

                System.out.println("idSous : "+i.getIntegrateurSouscription());

                Souscription s = souscriptionRepository.passifsESMCSARLUSouscription(i.getIntegrateurSouscription());

                if (s != null) {

                    System.out.println("Id : "+s.getSouscriptionId());

                    if (s.getSouscriptionAutonome() == 1) {
                        sommeMontant = sommeMontant + Double.valueOf((s.getSouscriptionNombre() - 1) * 2187.5);

                    } else {
                        sommeMontant = sommeMontant + Double.valueOf(s.getSouscriptionNombre() * 2187.5);

                    }
                }

            }
        }
        return sommeMontant;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<BonNeutreDetail> passifsESMCSARLUBonNeutre(String code, Long idKsu) {

        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);


        BonNeutre bn = bonNeutreRepository.passifsESMCSARLUBonNeutre(code);

        List<BonNeutreDetail> listB = Collections.emptyList();

        Ksu k = ksuRestClient.getById(idKsu);

        if (code.contains("P") == true) {

//            String nomk = k.getNom().replace(" ", "");
//
//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");



            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                if (bn != null) {
                    listB = bonNeutreDetailRepository.getBan(bn.getBonNeutreId());
                    return listB;
                }
            }
        }else {
            if (bn != null) {
                listB = bonNeutreDetailRepository.getBan(bn.getBonNeutreId());
                return listB;
            }
        }
        return null;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<CompteCredit> passifsEsmcSarluRpgNr(String code, Long idKsu) {

        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);

        List<CompteCredit> listB = null;

        Ksu k = ksuRestClient.getById(idKsu);

        if (code.contains("P") == true) {

//            String nomk = k.getNom().replace(" ", "");
//
//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");



            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listB = compteCreditRepository.passifsEsmcSarluRpgNr(code);
                return listB;
            }
        }

        return listB;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<CompteCredit> passifsEsmcSarluRpgr(String code, Long idKsu) {

        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);

        List<CompteCredit> listB = null;

        Ksu k = ksuRestClient.getById(idKsu);

        if (code.contains("P") == true) {

//            String nomk = k.getNom().replace(" ", "");
//
//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");


            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            System.out.println("=============================================");

            System.out.println("Nom k "+nomk);

            System.out.println("Prenom k "+prenom);

            System.out.println("Nom M "+nom_membre);

            System.out.println("Prenom M "+premon_membre);

            System.out.println("=============================================");

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listB = compteCreditRepository.passifsEsmcSarluRpgr(code);
                return listB;
            }
        }

        return listB;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<CompteCredit> passifsEsmcSarluInr(String code, Long idKsu) {

        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);

        List<CompteCredit> listB = null;

        Ksu k = ksuRestClient.getById(idKsu);

        if (code.contains("P") == true) {

//            String nomk = k.getNom().replace(" ", "");
//
//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");


            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listB = compteCreditRepository.passifsEsmcSarluInr(code);
                return listB;
            }
        }

        return listB;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<Gcp> getpassifsESMCSARLUGcp(String code, Long idKsu) {

        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);

        List<Gcp> listB = null;

        Ksu k = ksuRestClient.getById(idKsu);

        if (code.contains("P") == true) {
//
//            String nomk = k.getNom().replace(" ", "");
//
//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");



            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listB = gcpRepository.listpassifsESMCSARLUGcp(code);
                return listB;
            }
        }

        return listB;

    }

    /**
     * @param id
     * @return
     */
//    @Override
////    public List<Traite> getOpi(Long id) {
////        for (int i=0; i < getOpi(id).size();i++){
////            return traiteRepository.listOpi(id);
////        }
////    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllOpiByMembre(String code, Long idKsu) {

        Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);

        List<Map<String, Object>> listB = null;

        Ksu k = ksuRestClient.getById(idKsu);

        if (code.contains("P") == true) {

//            String nomk = k.getNom().replace(" ", "");
//
//            String nom_membre = membrePhysique.getNomMembre().replace(" ", "");
//
//            String prenom = k.getPrenom().replace(" ", "");
//
//            String premon_membre = membrePhysique.getPrenomMembre().replace(" ", "");




            String nomk =  Normalizer.normalize(k.getNom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String nom_membre = Normalizer.normalize( membrePhysique.getNomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();


            String prenom = Normalizer.normalize( k.getPrenom(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            String premon_membre = Normalizer.normalize( membrePhysique.getPrenomMembre(), Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listB = traiteRepository.findAllOpiByMembreO(code);
                return listB;
            }
        } else {
            MembreMorale membreMorale = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembreMorale(code);

            Ksu ksu = ksuRestClient.getById(idKsu);

            String raisonSocalMembre = membreMorale.getRaisonSociale().replace(" ", "");
            String raisonSocialKsu = ksu.getRaisonSocial().replace(" ", "");

            if (raisonSocialKsu.equalsIgnoreCase(raisonSocalMembre)) {
                listB = traiteRepository.findAllOpiByMembreO(code);
                return listB;
            }
        }
        return listB;
    }

    @Override
    public List<Map<String, Object>> getAllOpiByMembreOpi(String code) {
        return traiteRepository.findAllOpiByMembre(code);
    }


    @Override
    public List<Traite> listOpiMembre(String code) {

        List<Tpagcp> listtp = tpagcpRepository.passifsESMCSARLUTpagcp(code);

        List<Traite> lista = null;

        if (listtp.size() > 0) {
            for (Tpagcp  t: listtp) {
                int rt = Integer.parseInt(t.getIdTpagcp().toString());

                lista = traiteRepository.listOpi(rt);
            }
        }
        return lista;
    }


}
