package com.esmc.gestionMembre.services;

import com.esmc.gestionMembre.controllers.ExistenceMembreController;
import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.entities.Releve;
import com.esmc.gestionMembre.feign.KsuRestClient;
import com.esmc.gestionMembre.feign.TeRestClient;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.repositories.MembreFondateur11000Repository;
import com.esmc.gestionMembre.repositories.ReleveRepository;
import com.esmc.gestionMembre.serviceInterfaces.*;
import com.esmc.gestionMembre.serviceInterfaces.recherche.RedemareServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.recherche.ReleveServiceInterface;
import com.esmc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.esmc.gestionMembre.utils.Constants.*;

@Service
@Transactional
public class ReleveService implements ReleveServiceInterface {
    @Autowired
    TeRestClient detailSMEnchangeClient;


    @Autowired
    private ReleveRepository releveRepository;

    @Autowired
    private ExistenceMembreController existenceMembreController;

    /**
     * @param a
     * @return
     */

    @Autowired
    private RedemareServiceInterface redemareService;

    @Autowired
    private McnpServiceInterface mcnpServiceInterface;

    @Autowired
    private EsmcSarluServiceInterface esmcSarluServiceInterface;

    @Autowired
    private TeRestClient teRestClient;


    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private ExistenceMembreServiceInterface existenceMembreServiceInterface;

    @Autowired
    private MembreFondateur11000Repository membreFondateur11000Repository;


    @Override
    public Releve saveReleve(Releve a) {
        return releveRepository.save(a);
    }

    /**
     * @return
     */
    @Override
    public List<Releve> listeDesreleves() {
        return releveRepository.findAll();
    }

    /**
     * @param a
     * @return
     */
    @Override
    public List<Releve> listeReleveKsu(Ksu a) {
        return releveRepository.listeReleveKsu(a);
    }

    /**
     * @param a
     * @return
     */
    @Override
    public Releve releveDeKsu(Ksu a) {
        return releveRepository.releveDeKsu(a);
    }

    /**
     * @param a
     * @return
     */
    @Override
    public Releve validerReleve(Releve a) {
        a.setValider(true);
        return releveRepository.save(a);
    }

    /**
     * @param a
     * @return
     */
    @Override
    public Releve cloturerReleve(Releve a) {
        a.setCloture(true);
        return releveRepository.save(a);
    }

    /**
     * aauthor Amemorte
     * @param systeme
     * @param produit
     * @param code
     * @param idKsu
     * @param idTe
     * @return
     *
     *  reviews et amelioration
     */

    @Override
    public Object saveReleve(String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        switch (systeme.replace(" ","")) {
            case REDEMARREVALUE:

                try {

                    Morale objMred = existenceMembreServiceInterface.RedemarrepersonneMorale(code);

                    Physique objPred = existenceMembreServiceInterface.RedemarrepersonnePhysique(code);

                    Ksu k = ksuRestClient.getById(idKsu);


                    if (produit.equalsIgnoreCase("CNCS")) {

                        String nomk = k.getNom().replace(" ","");

                        String nom_membre = objPred.getNom().replace(" ","");

                        String prenom = k.getPrenom().replace(" ","");

                        String premon_membre = objPred.getPrenom().replace(" ","");

                        if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {

                            Releve rel = this.getReleveCncsRedemare(systeme, produit, code, idKsu, idTe);
                            return rel;
                        }


                    } else if (produit.equalsIgnoreCase("CNP")) {

                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            if (objPred == null) {
                                return null;
                            } else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objPred.getNom().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");

                                String premon_membre = objPred.getPrenom().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getCnp(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (objMred == null) {
                                return null;
                            } else {

                                return this.getCnp(systeme, produit, code, idKsu, idTe);

                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
            // ============================================ Gestion des passif pour MCNP ==========================================================
            case MCNPVALUE :

                try {

                    if (produit.equalsIgnoreCase("CNCS")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        //  Formatter<AncienMembre> objP = (Formatter<AncienMembre>) existenceMembreController.getMembre(systeme, MCNPTABLEMORALE, code);

                        Ksu k = ksuRestClient.getById(idKsu);


                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = objP.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = objP.getPrenomMembre().replace(" ","");

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getReleveCncs(systeme, produit, code, idKsu, idTe);
                            }


                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            String nomk = ks.getNom().replace(" ","");

                            String nom_membre = objP.getNomMembre().replace(" ","");

                            String prenom = ks.getPrenom().replace(" ","");

                            String premon_membre = objP.getPrenomMembre().replace(" ","");


                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getReleveCncs(systeme, produit, code, idKsu, idTe);
                            } else {
                                return null;
                            }


                            //=====================================================Fin Personne Morale ==========================================================
                        }
                    } else if (produit.equalsIgnoreCase("MF11000")) {

                        MembreFondateur11000 membre = membreFondateur11000Repository.passifsMCNPmembreFondateur11000(code);

                        System.out.println("======================================01==============================");

                        if (membre == null) {
                            return null;
                        }

                        Ksu k = ksuRestClient.getById(idKsu);

                        System.out.println("======================================02==============================");

                        if (k == null) {
                            return null;
                        }

                        String nomk = k.getNom().replace(" ","");

                        String nom_membre = membre.getNom().replace(" ","");

                        String prenom = k.getPrenom().replace(" ","");

                        String premon_membre = membre.getPrenom().replace(" ","");

                        if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                            return this. getMF11000(systeme, produit, code, idKsu, idTe);
                        }




                    } else if (produit.equalsIgnoreCase("MF107")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        Ksu k = ksuRestClient.getById(idKsu);


                        if (code.contains("P") == true) {

                            //======================================================Persone Pysique ========================================================

                            if (objP == null) {
                                return null;
                            }else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");

                                String premon_membre = objP.getPrenomMembre().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getMF107(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            if (objP == null) {
                                return null;
                            } else {

                                String nomk = ks.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = ks.getPrenom().replace(" ","");

                                String premon_membre = objP.getPrenomMembre().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getMF107(systeme, produit, code, idKsu, idTe);
                                } else {
                                    return null;
                                }
                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }


                    } else if (produit.equalsIgnoreCase("RPGr")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        Ksu k = ksuRestClient.getById(idKsu);

                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            if (objP == null) {
                                return null;
                            }else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");

                                String premon_membre = objP.getPrenomMembre().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getRPgrMcnp(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            if (objP == null) {
                                return null;
                            } else {

                                String nomk = ks.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = ks.getPrenom().replace(" ","");

                                String premon_membre = objP.getPrenomMembre().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getRPgrMcnp(systeme, produit, code, idKsu, idTe);
                                } else {
                                    return null;
                                }
                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }

                    } else if (produit.equalsIgnoreCase("RPGnr")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        Ksu k = ksuRestClient.getById(idKsu);


                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            if (objP == null) {
                                return null;
                            }else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");

                                String premon_membre = objP.getPrenomMembre().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getRPgnrMcnp(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            if (objP == null) {
                                return null;
                            } else {

                                String nomk = ks.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = ks.getPrenom().replace(" ","");

                                String premon_membre = objP.getPrenomMembre().replace(" ","");

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getRPgnrMcnp(systeme, produit, code, idKsu, idTe);
                                } else {
                                    return null;
                                }
                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }

                    } else if (produit.equalsIgnoreCase("Ir")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        Ksu k = ksuRestClient.getById(idKsu);

                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            if (objP == null) {
                                return null;
                            }else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");;

                                String premon_membre = objP.getPrenomMembre().replace(" ","");;

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getIrMcnp(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            if (objP == null) {
                                return null;
                            } else {

                                String nomk = ks.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = ks.getPrenom().replace(" ","");;

                                String premon_membre = objP.getPrenomMembre().replace(" ","");;

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getIrMcnp(systeme, produit, code, idKsu, idTe);
                                } else {
                                    return null;
                                }
                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }


                    } else if (produit.equalsIgnoreCase("Inr")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        Ksu k = ksuRestClient.getById(idKsu);

                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            if (objP == null) {
                                return null;
                            }else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");;

                                String premon_membre = objP.getPrenomMembre().replace(" ","");;

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getInrMcnp(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            if (objP == null) {
                                return null;
                            } else {

                                String nomk = ks.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = ks.getPrenom().replace(" ","");;

                                String premon_membre = objP.getPrenomMembre().replace(" ","");;

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getInrMcnp(systeme, produit, code, idKsu, idTe);
                                } else {
                                    return null;
                                }
                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }


                    } else if (produit.equalsIgnoreCase("GCp")) {

                        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

                        Ksu k = ksuRestClient.getById(idKsu);

                        if (code.contains("P") == true) {
                            //======================================================Persone Pysique ========================================================

                            if (objP == null) {
                                return null;
                            }else {

                                String nomk = k.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = k.getPrenom().replace(" ","");;

                                String premon_membre = objP.getPrenomMembre().replace(" ","");;

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getGcpMcnp(systeme, produit, code, idKsu, idTe);
                                }
                            }

                            //=====================================================Fin Personne Pysique ===================================================
                        } else {
                            //=====================================================Personne Morale ========================================================

                            if (k.getCodeKsuRepresentant() == null) {
                                return null;
                            }

                            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

                            Ksu ks = ksa.getData();

                            if (objP == null) {
                                return null;
                            } else {

                                String nomk = ks.getNom().replace(" ","");

                                String nom_membre = objP.getNomMembre().replace(" ","");

                                String prenom = ks.getPrenom().replace(" ","");;

                                String premon_membre = objP.getPrenomMembre().replace(" ","");;

                                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                    return this.getGcpMcnp(systeme, produit, code, idKsu, idTe);
                                } else {
                                    return null;
                                }
                            }

                            //=====================================================Fin Personne Morale ==========================================================
                        }

                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                // ============================================ Fin de Gestion des passif pour MCNP ==========================================================
            case ESMCSARLUVALUE:

                // ============================================ Fin de Gestion des passif pour ESMCSARLU ==========================================================

                try {

                    Membre membrePhysique = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembre(code);

                    // MembreMorale membreMorale = existenceMembreServiceInterface.ESMCSARLUmembreFondateurMembreMorale(code);

                    Ksu k = ksuRestClient.getById(idKsu);

                    if (produit.equalsIgnoreCase("CMFH")){

                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getCmfh(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getCmfh(systeme, produit, code, idKsu, idTe);
                        }

                    } else if (produit.equalsIgnoreCase("BAn")) {

                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getBAn(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getBAn(systeme, produit, code, idKsu, idTe);
                        }


                    } else if (produit.equalsIgnoreCase("RPGr")) {
                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getRPGrEsmc(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getRPGrEsmc(systeme, produit, code, idKsu, idTe);
                        }
                    } else if (produit.equalsIgnoreCase("RPGnr")) {

                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getRPGnrEsmc(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getRPGnrEsmc(systeme, produit, code, idKsu, idTe);
                        }

                    } else if (produit.equalsIgnoreCase("Inr")) {

                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getIrEsmc(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getIrEsmc(systeme, produit, code, idKsu, idTe);
                        }

                    } else if (produit.equalsIgnoreCase("Gcp")) {
                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getGcpEsmc(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getGcpEsmc(systeme, produit, code, idKsu, idTe);
                        }
                    }
                    else if (produit.equalsIgnoreCase("OPI")) {

                        if (code.contains("P") == true) {

                            String nomk = k.getNom().replace(" ","");

                            String nom_membre = membrePhysique.getNomMembre().replace(" ","");

                            String prenom = k.getPrenom().replace(" ","");;

                            String premon_membre = membrePhysique.getPrenomMembre().replace(" ","");;

                            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                                return this.getOpiEsmc(systeme, produit, code, idKsu, idTe);
                            }
                        }else {
                            return this.getOpiEsmc(systeme, produit, code, idKsu, idTe);
                        }

                    }
                }catch (Exception e) {
                    System.out.println(e);
                }

            case ESMCGIE:
                if (produit.equalsIgnoreCase("BCi Présentiel")) {
                    List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

                    if (listre.size() > 0){
                        releveRepository.listReleveByKsu(idKsu, idTe);
                    }else{

                        PassifPresentielle  p = teRestClient.getPassifPrentielleByCodeQrOrNumRef(code);


//                        System.out.println(p.getPrenomsvendeur());

                        if (p == null) {
                            return null;
                        }

                        if (p != null) {
                            Double montant = Double.parseDouble(p.getValeurInitialXOF());
                            if (montant > 0) {
                                r = new Releve();
                                r.setOrigine(ESMCGIE);
                                r.setType(produit);
                                r.setMembre(code);
                                r.setMontant(Double.parseDouble(p.getValeurInitialXOF()));
                                r.setKsu(idKsu);
                                r.setTerminalEchange(idTe);
                                r.setValider(true);
                                releveRepository.save(r);
                                return r;
                            }
                        }

                        return p;
                    }
                }
        }

        return null;
    }


    @Override
    public List<Releve> listRevele(Long idKsu, Long idTe) {
        return releveRepository.listReleveByKsu(idKsu, idTe);
    }

    @Override
    public boolean validationReleve(String systeme, String produit, String code,   Long idKsu, Long idTe) {

        System.out.println("Systeme : "+systeme+" produit : "+produit+" code : "+code+" ksu : "+idKsu);

        List<Releve> listr = releveRepository.listReleveBySystemAndCodeAndKsu(systeme, produit, code,  idKsu, idTe);

        System.out.println("Liste des Releve :" +listr.size());

        if (listr.size() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public List<String> listReleveByProduit(Long id, Long idTe) {
        return releveRepository.listReleveByProduit(id, idTe);
    }

    @Override
    public List<Releve> listReleveByKsuAndProduit(Long id, Long idTe, String produit) {
        return releveRepository.listReleveByKsuAndProduit(id, idTe, produit);
    }

    @Override
    public DetailSMEnchange renitialisationBCI(Double montant, Long idTe) {
        return detailSMEnchangeClient.renitialisationBCI(montant, idTe);
    }

    @Override
    public DetailSMEnchange renitialisationPassif(Double montant, Long idTe) {
        return detailSMEnchangeClient.renitialisationPassif(montant, idTe);
    }


    @Override
    public Releve getReleveByIdAndMontant(Long id, double valeur) {
        return releveRepository.getReleve(id, valeur);
    }

    @Override
    public String codeMembreRedemare(String code) {
        String codes = code+"";
        int numero = 13 - code.length();

        for(int i =0; i<numero; i++) {
            codes = "0" + codes;
        }
        return codes;
    }

    @Override
    public String codeMembre(String code)
    {
        String codes = code+"";

        if (codes.length() == 20) {
            int numero = 20 - code.length();

            for(int i =0; i<numero; i++) {
                codes = "0" + codes;
            }
        }
        return codes;
    }


    @Override
    public String codeMembreGie(String code) {
        String codes = code+"";
        int numero = 23 - code.length();

        for(int i =0; i<numero; i++) {
            codes = "0" + codes;
        }
        return codes;
    }



    /// =============== Debut des fonctions ==============

    public Releve getReleveCncsRedemare (String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        List<Releve> listr = releveRepository.listReleveBySystemAndCodeAndKsu(systeme, produit, code, idKsu, idTe);

        if (listr.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {
            Result<Long, List<Credit>> listc1 = redemareService.getCreditCNCS(code, idKsu);
            List<Credit> listc = listc1.getValue();

            if (listc.size() > 0) {
                for (Credit c : listc) {
                   Double montant = c.getMontplace();
                    r = new Releve();
                    r.setOrigine(REDEMARREVALUE);
                    r.setType(c.getLibelle());
                    r.setMembre(c.getMembre());
                    r.setMontant(c.getMontplace());
                    r.setKsu(idKsu);
                    r.setTerminalEchange(idTe);
                    r.setValider(true);
                    releveRepository.save(r);
                }

            }

        }
        return r;
    }

    public Releve getReleveCncs (String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {

            Result<Long, List<AncienCompte>> listAC1 = mcnpServiceInterface.getPassifCncsMorale(code, idKsu);

            List<AncienCompte> listAC = listAC1.getValue();

            Result<Long, List<AncienCompteCredit>> listacc1 = mcnpServiceInterface.getPassifCncsPhysique(code, idKsu);

            List<AncienCompteCredit> listacc = listacc1.getValue();

            Result<Long, List<AncienEchange>> listae1 = mcnpServiceInterface.getPassifCncsEchange(code, idKsu);

            List<AncienEchange> listae = listae1.getValue();

            Result<Long, List<AncienCompteCredit>> listacct1 = mcnpServiceInterface.getPassifCncs(code, idKsu);

            List<AncienCompteCredit> listacct = listacct1.getValue();

            if (listAC.size() > 0 || listacc.size() > 0 || listae.size() > 0 || listacct.size() > 0) {
                for (AncienCompte a: listAC) {
                    Double montant = a.getSolde();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getSolde());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
                for (AncienCompteCredit a: listacc) {
                    double montant = a.getMontantPlace();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getMontantPlace());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
                for (AncienEchange a: listae) {
                    Double montant = a.getMontant();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getMontant());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
                for (AncienCompteCredit a: listacct) {
                    double montant = a.getMontantPlace();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getMontantPlace());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getMF107(String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code , idKsu, idTe);

        if (listre.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {
            Result<Long, List<RepartitionMf107>> listMf1071 = mcnpServiceInterface.passifsMCNPmembreFondateurRepartitionMf107(code, idKsu);
            List<RepartitionMf107> listMf107 = listMf1071.getValue();

            if (listMf107.size() >0) {
                for (RepartitionMf107 re : listMf107) {
                    Double montant = re.getSoldeRep();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(re.getCodeMembre());
                        r.setMontant(re.getSoldeRep());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }


    public Releve getMF11000(String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {
            Result<Long, List<RepartitionMF11000>> listrep1 = mcnpServiceInterface.listPassifRepMf1100(code, idKsu);

            List<RepartitionMF11000> listrep = listrep1.getValue();

            if (listrep.size() > 0) {
                for (RepartitionMF11000 re: listrep) {
                    Double montant = Double.parseDouble(re.getSoldeRep());
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(Double.parseDouble(re.getSoldeRep()));
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getRPgrMcnp(String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {
            Result<Long, List<AncienCompteCredit>> listrpgr1 = mcnpServiceInterface.listRPGr(code, idKsu);

            List<AncienCompteCredit> listrpgr = listrpgr1.getValue();

            if (listrpgr.size() > 0) {
                for (AncienCompteCredit a: listrpgr) {
                    double montant = a.getMontantPlace();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(a.getCodeMembre());
                        r.setMontant(a.getMontantPlace());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getRPgnrMcnp(String systeme, String produit, String code, Long idKsu, Long idTe) {

        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else {
            Result<Long, List<AncienCompteCredit>> listrpgnr1 = mcnpServiceInterface.listRPGnr(code, idKsu);
            List<AncienCompteCredit> listrpgnr = listrpgnr1.getValue();

            if (listrpgnr.size() > 0){
                for (AncienCompteCredit a:listrpgnr){
                    double montant = a.getMontantCredit();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(a.getCodeMembre());
                        r.setMontant(a.getMontantCredit());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }

        }
        return r;
    }

    public Releve getIrMcnp(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else {
            Result<Long, List<AncienCompteCredit>> listIr1 = mcnpServiceInterface.listIr(code, idKsu);
            List<AncienCompteCredit> listIr = listIr1.getValue();

            if (listIr.size() > 0){
                for (AncienCompteCredit a:listIr){
                    double montant = a.getMontantPlace();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(a.getCodeMembre());
                        r.setMontant(a.getMontantPlace());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getInrMcnp(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else {
            Result<Long, List<AncienCompteCredit>> listInr1 = mcnpServiceInterface.listInr(code, idKsu);

            List<AncienCompteCredit> listInr = listInr1.getValue();

            if (listInr.size() > 0){
                for (AncienCompteCredit a:listInr){
                    double montant = a.getMontantCredit();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(MCNPVALUE);
                        r.setType(produit);
                        r.setMembre(a.getCodeMembre());
                        r.setMontant(a.getMontantCredit());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getGcpMcnp(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else {

            Double montantgcp = mcnpServiceInterface.sommepassifsMCNPGcp(code, idKsu);

            // Double montantEscompte = mcnpServiceInterface.sommepassifsMCNPAncienEchangeNbContreNb(code);

            //Double montantEchange = mcnpServiceInterface.sommepassifsMCNPAncienEchangeNbContreNn(code);

            //Double montantTotal = montantgcp - (montantEscompte + montantEchange);

            if (montantgcp != null) {
                Double montant = montantgcp;
                if (montant > 0) {
                    r = new Releve();
                    r.setOrigine(MCNPVALUE);
                    r.setType(produit);
                    r.setMembre(code);
                    r.setMontant(montantgcp);
                    r.setKsu(idKsu);
                    r.setTerminalEchange(idTe);
                    r.setValider(true);
                    releveRepository.save(r);
                }
            }
        }
        return r;
    }

    public Releve getCnp(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {

            Result<Long, List<Place>> listp1 = redemareService.getPlaceCnp(code, idKsu);

            List<Place> lisp = listp1.getValue();

            if (lisp.size() > 0) {
                for (Place p : lisp) {
                    Double montant = Double.parseDouble(p.getMontant());
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(REDEMARREVALUE);
                        r.setType(p.getLib());
                        r.setMembre(p.getMembre());
                        r.setMontant(Double.parseDouble(p.getMontant()));
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getCmfh(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else {
            List<Souscription> listCMHF = esmcSarluServiceInterface.getSouscrition(code, idKsu);

            if (listCMHF.size()>0){
                for (Souscription a:listCMHF){
                    if (a.getSouscriptionAutonome() == 0){
                        Double montant = a.getSouscriptionMontant();
                        if (montant > 0) {
                            r = new Releve();
                            r.setOrigine(ESMCSARLUVALUE);
                            r.setType(produit);
                            r.setMembre(code);
                            r.setMontant(a.getSouscriptionMontant());
                            r.setKsu(idKsu);
                            r.setTerminalEchange(idTe);
                            r.setValider(true);
                            releveRepository.save(r);
                            return r;
                        }
                    }else {
                        Double montant = a.getSouscriptionMontant() - 5000;
                        if (montant > 0) {
                            r = new Releve();
                            r.setOrigine(ESMCSARLUVALUE);
                            r.setType(produit);
                            r.setMembre(code);
                            r.setMontant(a.getSouscriptionMontant() - 5000);
                            r.setKsu(idKsu);
                            r.setTerminalEchange(idTe);
                            r.setValider(true);
                            releveRepository.save(r);
                        }
                    }
                }
            }
        }
        return r;
    }

    public Releve getBAn(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme, produit, code, idKsu, idTe);

        if (listre.size() > 0) {
            releveRepository.listReleveByKsu(idKsu, idTe);
        } else {
            List<BonNeutreDetail> listBan = esmcSarluServiceInterface.passifsESMCSARLUBonNeutre(code, idKsu);

            if (listBan.size() > 0) {
                for (BonNeutreDetail a : listBan) {
                    Double montant = a.getBonNeutreDetailMontantSolde();
                    if (montant > 0)  {
                        r = new Releve();
                        r.setOrigine(ESMCSARLUVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(montant);
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }

                }

            }
        }
        return r;
    }

    public Releve getRPGrEsmc(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else{
            List<CompteCredit> listRpgr = esmcSarluServiceInterface.passifsEsmcSarluRpgr(code, idKsu);

            if (listRpgr.size()>0) {
                for (CompteCredit a : listRpgr) {
                    Double montant = a.getMontantPlace();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(ESMCSARLUVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getMontantPlace());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getRPGnrEsmc(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else{
            List<CompteCredit> listRpgnr = esmcSarluServiceInterface.passifsEsmcSarluRpgNr(code, idKsu);
            if (listRpgnr.size()>0) {
                for (CompteCredit a : listRpgnr) {
                    Double montant = a.getMontantPredit();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(ESMCSARLUVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getMontantPredit());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }

        }
        return r;
    }

    public Releve getIrEsmc(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else{
            List<CompteCredit> listInr = esmcSarluServiceInterface.passifsEsmcSarluRpgNr(code, idKsu);

            if (listInr.size()>0) {
                for (CompteCredit a : listInr) {
                    Double montant = a.getMontantPredit();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(ESMCSARLUVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getMontantPredit());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }

    public Releve getGcpEsmc(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else{
            List<Gcp> listGcp = esmcSarluServiceInterface.getpassifsESMCSARLUGcp(code, idKsu);

            if (listGcp.size()>0) {
                for (Gcp a : listGcp) {
                    Double montant = a.getReste();
                    if (montant > 0) {
                        r = new Releve();
                        r.setOrigine(ESMCSARLUVALUE);
                        r.setType(produit);
                        r.setMembre(code);
                        r.setMontant(a.getReste());
                        r.setKsu(idKsu);
                        r.setTerminalEchange(idTe);
                        r.setValider(true);
                        releveRepository.save(r);
                    }
                }
            }
        }
        return r;
    }


    public Releve getOpiEsmc(String systeme, String produit, String code, Long idKsu, Long idTe) {
        Releve r = null;

        List<Releve> listre = releveRepository.listReleveBySystemAndCodeAndKsu(systeme,produit, code, idKsu, idTe);

        if (listre.size() > 0){
            releveRepository.listReleveByKsu(idKsu, idTe);
        }else{
            //List<Traite> listOpi = esmcSarluServiceInterface.listOpiMembre(code);
            List<Map<String,Object>> listOpi = esmcSarluServiceInterface.getAllOpiByMembre(code, idKsu);

            if (listOpi.size()>0) {
                for (Map<String,Object> a : listOpi) {
                    Double montant = (Double) (a.get("montant"));
                   if (montant > 0) {
                       r = new Releve();
                       r.setOrigine(ESMCSARLUVALUE);
                       r.setType(produit);
                       r.setMembre(code);
                       r.setMontant((Double) (a.get("montant")));
                       r.setKsu(idKsu);
                       r.setTerminalEchange(idTe);
                       r.setValider(true);
                       releveRepository.save(r);
                   }
                }
            }
        }
        return r;
    }

    @Override
    public Releve getReleveById(Long id) {
        return releveRepository.findById(id).orElseThrow(null);
    }

}
