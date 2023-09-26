package com.esmc.gestionMembre.services;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.feign.KsuRestClient;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.repositories.*;
import com.esmc.gestionMembre.serviceInterfaces.ExistenceMembreServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.McnpServiceInterface;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class McnpService implements McnpServiceInterface {

    @Autowired
    private RepartitionMF11000Repository repartitionMF11000Repository;

    @Autowired
    private RepartitionMf107Repository repartitionMf107Repository;

    @Autowired
    private AncienDetailsSmsmoneyRepository ancienDetailsSmsmoneyRepository;

    @Autowired
    private AncienCompteCreditRepository ancienCompteCreditRepository;

    @Autowired
    private AncienGcpRepository ancienGcpRepository;

    @Autowired
    private AncienEchangeRepository ancienEchangeRepository;

    @Autowired
    private AncienCompteRepository ancienCompteRepository;

    @Autowired
    private ExistenceMembreServiceInterface existenceMembreServiceInterface;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private MembreFondateur11000Repository membreFondateur11000Repository;



    /**
     * @param code
     * @return
     */
    @Override
    public Result<Long, List<RepartitionMF11000>> listPassifRepMf1100(String code, Long idKsu) {

        List<RepartitionMF11000> listrep = new ArrayList<RepartitionMF11000>();

        Result<Long, List<RepartitionMF11000>> result = new Result<Long, List<RepartitionMF11000>>();

        MembreFondateur11000 membre = membreFondateur11000Repository.passifsMCNPmembreFondateur11000(code);

        if (membre == null) {
            result.setKey(0L);
            result.setValue(null);
            return result;
        }

        Ksu k = ksuRestClient.getById(idKsu);
        if (k == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        String nom_membre = membre.getNom().replace(" ", "");
        String premon_membre = membre.getPrenom().replace(" ", "");


        if (k.getNom().equalsIgnoreCase(nom_membre) && k.getPrenom().equalsIgnoreCase(premon_membre)) {
            listrep = repartitionMF11000Repository.passifsMCNPmembreFondateurRepartitionMF11000(code);

            if (listrep.size() > 0) {
                result.setKey(2L);
                result.setValue(listrep);
            } else {
                result.setKey(3L);
                result.setValue(null);
            }
        }

        return result;

    }

    /**
     * @author Amemorte

     *
     *  reviews et amelioration
     */
    @Override
    public Result<Long, List<RepartitionMf107>> passifsMCNPmembreFondateurRepartitionMf107(String code, Long idKsu) {

        List<RepartitionMf107> listrep = new ArrayList<RepartitionMf107>();

        Result<Long, List<RepartitionMf107>> result = new Result<Long, List<RepartitionMf107>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        System.out.println("===========================MEMBRE================================");
        System.out.println(objP);
        System.out.println("=================================================================");

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        System.out.println("===========================KSU================================");
        System.out.println(k);
        System.out.println("=================================================================");

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = repartitionMf107Repository.passifsMCNPmembreFondateurRepartitionMf107(code);

                System.out.println("===========================KSU================================");
                System.out.println(k);
                System.out.println("=================================================================");


                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================


            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            System.out.println(ksa);



            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;

            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = repartitionMf107Repository.passifsMCNPmembreFondateurRepartitionMf107(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }


        return result;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<AncienDetailsSmsmoney> passifsMCNPmembreFondateurAncienDetailsSmsmoney(String code) {
        return ancienDetailsSmsmoneyRepository.passifsMCNPmembreFondateurAncienDetailsSmsmoney(code);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public AncienCompteCredit passifsMCNPAncienCompteCredit(String code) {
        return ancienCompteCreditRepository.passifsMCNPAncienCompteCredit(code);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Result<Long, List<AncienCompteCredit>> listRPGr(String code, Long idKsu) {

        List<AncienCompteCredit> listrep = new ArrayList<AncienCompteCredit>();

        Result<Long, List<AncienCompteCredit>> result = new Result<Long, List<AncienCompteCredit>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================


            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listRPGr(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            System.out.println("=============================03==============================");

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            System.out.println(ksa);

            System.out.println("=============================04==============================");

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;



            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listRPGr(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }


        return result;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Result<Long, List<AncienCompteCredit>> listRPGnr(String code, Long idKsu) {

        List<AncienCompteCredit> listrep = new ArrayList<AncienCompteCredit>();

        Result<Long, List<AncienCompteCredit>> result = new Result<Long, List<AncienCompteCredit>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listRPGnr(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            System.out.println("=============================03==============================");

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            System.out.println(ksa);

            System.out.println("=============================04==============================");

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;



            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listRPGnr(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }


        return result;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public Result<Long, List<AncienCompteCredit>> listIr(String code, Long idKsu) {

        List<AncienCompteCredit> listrep = new ArrayList<AncienCompteCredit>();

        Result<Long, List<AncienCompteCredit>> result = new Result<Long, List<AncienCompteCredit>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listIr(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;



            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listIr(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return result;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public Result<Long, List<AncienCompteCredit>> listInr(String code, Long idKsu) {


        List<AncienCompteCredit> listrep = new ArrayList<AncienCompteCredit>();

        Result<Long, List<AncienCompteCredit>> result = new Result<Long, List<AncienCompteCredit>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listInr(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }

            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            System.out.println("=============================03==============================");

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;



            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listInr(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return result;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Double sommepassifsMCNPGcp(String code, Long idKsu) {

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            return null;
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            return null;
        }

        Double listrep = 0.0;

        Double resul = null;

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.sommepassifsMCNPAncienEchangeNbContreNn(code);

                return listrep;

            }

            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================


            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());


            Ksu ks = ksa.getData();

            if (ks == null) {
                return null;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienGcpRepository.sommepassifsMCNPGcp(code);

                return listrep;

            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return resul;

//        Double m = 0.0;
//
//
//        Double  gcp = ancienGcpRepository.sommepassifsMCNPGcp(code);
//
//                if (gcp == 0) {
//                    return m;
//                }
//
//        return gcp;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Double sommepassifsMCNPAncienEchangeNbContreNb(String code, Long idKsu) {

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            return null;
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            return null;
        }

        Double listrep = 0.0;

        Double resul = null;

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.sommepassifsMCNPAncienEchangeNbContreNn(code);

                return listrep;

            }

            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================


            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                return null;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.sommepassifsMCNPAncienEchangeNbContreNb(code);

                return listrep;

            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return resul;

    }

    /**
     * @param code
     * @return
     */
    @Override
    public Double sommepassifsMCNPAncienEchangeNbContreNn(String code, Long idKsu) {

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            return null;
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            return null;
        }

        Double listrep = 0.0;

        Double resul = null;

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.sommepassifsMCNPAncienEchangeNbContreNn(code);

                return listrep;

            }

            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================


            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                return null;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.sommepassifsMCNPAncienEchangeNbContreNn(code);

                return listrep;

            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return resul;
    }

    /**
     * @param
     * @return
     */
    @Override
    public List<AncienGcp> listpassifMCNPGcp(String code) {

        return ancienGcpRepository.listpassifMCNPGcp(code);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<AncienEchange> getpassifsMCNPAncienEchangeNbContreNb(String code) {
        return ancienEchangeRepository.listpassifsMCNPAncienEchangeNbContreNb(code);
    }

    /**
     * @param code
     * @return
     */
    @Override
    public List<AncienEchange> getpassifsMCNPAncienEchangeNbContreNn(String code) {
        return ancienEchangeRepository.listpassifsMCNPAncienEchangeNbContreNn(code);
    }

    @Override
    public Result<Long, List<AncienCompte>> getPassifCncsMorale(String code, Long idKsu) {

        List<AncienCompte> listrep = new ArrayList<AncienCompte>();

        Result<Long, List<AncienCompte>> result = new Result<Long, List<AncienCompte>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (k == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");
            String nom_membre = objP.getNomMembre().replace(" ","");
            String prenom = k.getPrenom().replace(" ","");;
            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteRepository.listAncienCompteCncs(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }
            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());


            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteRepository.listAncienCompteCncs(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }
            
            //=====================================================Fin Personne Morale ==========================================================
        }

        return result;

    }

    @Override
    public Result<Long, List<AncienCompteCredit>> getPassifCncsPhysique(String code, Long idKsu) {

        List<AncienCompteCredit> listrep = new ArrayList<AncienCompteCredit>();

        Result<Long, List<AncienCompteCredit>> result = new Result<Long, List<AncienCompteCredit>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (k == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================
            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listCNCS(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }
            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());


            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listCNCS(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }

            //=====================================================Fin Personne Morale ==========================================================
        }

        return result;

    }

    @Override
    public Result<Long, List<AncienEchange>> getPassifCncsEchange(String code, Long idKsu) {


        List<AncienEchange> listrep = new ArrayList<AncienEchange>();

        Result<Long, List<AncienEchange>> result = new Result<Long, List<AncienEchange>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.listCNCS(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }

            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================


            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;



            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienEchangeRepository.listCNCS(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return result;

    }

    @Override
    public Result<Long, List<AncienCompteCredit>> getPassifCncs(String code, Long idKsu) {

        List<AncienCompteCredit> listrep = new ArrayList<AncienCompteCredit>();

        Result<Long, List<AncienCompteCredit>> result = new Result<Long, List<AncienCompteCredit>>();

        AncienMembre objP = existenceMembreServiceInterface.MCNPancienMembre(code);

        if (objP == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objP == null) {
            result.setKey(1L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {

            //======================================================Persone Pysique ========================================================

            String nomk = k.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = k.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listCNCSEchange(code);

                if (listrep.size() > 0) {
                    result.setKey(2L);
                    result.setValue(listrep);
                } else {
                    result.setKey(3L);
                    result.setValue(null);
                }
            }

            //=====================================================Fin Personne Pysique ===================================================
        } else {
            //=====================================================Personne Morale ========================================================

            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(4L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ","");

            String nom_membre = objP.getNomMembre().replace(" ","");

            String prenom = ks.getPrenom().replace(" ","");;

            String premon_membre = objP.getPrenomMembre().replace(" ","");;


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listrep = ancienCompteCreditRepository.listCNCSEchange(code);

                if (listrep.size() > 0) {
                    result.setKey(5L);
                    result.setValue(listrep);
                } else {
                    result.setKey(6L);
                    result.setValue(null);
                }
            }


            //=====================================================Fin Personne Morale ==========================================================
        }

        return result;

    }


}
