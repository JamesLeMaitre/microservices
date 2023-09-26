package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.*;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineValeur;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseCible;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseIndicateur;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineValeur;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillInstitution;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineValeur;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChambre;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseCible;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseIndicateur;
import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;
import com.esmc.gestionAchatFranchise.inputs.achatFranchiseInput;
import com.esmc.gestionAchatFranchise.repositories.MondeRepository;
import com.esmc.gestionAchatFranchise.repositories.affectation.*;
import com.esmc.gestionAchatFranchise.repositories.franchise.fill.FranchiseFillChaineDistributionRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.fill.FranchiseFillChaineValeurRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.fill.FranchiseFillInstitutionRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.flboe.FranchiseFlbOeChaineDistributionRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.flboe.FranchiseFlbOeChaineValeurRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.flboe.FranchiseFlbOeChambreRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.flbose.FranchiseFlbOseAgenceOddRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.flbose.FranchiseFlbOseCibleRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.flbose.FranchiseFlbOseIndicateurRepository;
import com.esmc.gestionAchatFranchise.repositories.franchise.referenciel.FranchiseReferentielRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.AchatServiceInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ContinentInt;
import com.esmc.gestionAchatFranchise.servicesinterfaces.StatistiqueInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillChaineDistributionInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillChaineValeurInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillInstitutionInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe.FlbOeChaineDistributionInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe.FlbOeChaineValeurInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe.FlbOeChambreInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseAgenceOddInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseCibleInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseIndicateurInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class AchatServiceV0  {

    @Autowired
    private FillChaineDistributionInterface fillChaineDistributionInterface;

    @Autowired
    private FillChaineValeurInterface fillChaineValeurInterface;

    @Autowired
    private FillInstitutionInterface fillInstitutionInterface;

    @Autowired
    private FlbOseAgenceOddInterface flbOseAgenceOddInterface;

    @Autowired
    private FlbOseCibleInterface flbOseCibleInterface;

    @Autowired
    private FlbOseIndicateurInterface flbOseIndicateurInterface;

    @Autowired
    private FlbOeChaineDistributionInterface flbOeChaineDistributionInterface;

    @Autowired
    private FlbOeChaineValeurInterface flbOeChaineValeurInterface;

    @Autowired
    private FlbOeChambreInterface flbOeChambreInterface;

    @Autowired
    private MondeRepository mondeRepository;


    @Autowired
    private FranchiseFillChaineDistributionRepository franchiseFillChaineDistributionRepository;

    @Autowired
    private FranchiseFillChaineValeurRepository franchiseFillChaineValeurRepository;

    @Autowired
    private FranchiseFillInstitutionRepository franchiseFillInstitutionRepository;

    @Autowired
    private FranchiseFlbOeChaineDistributionRepository franchiseFlbOeChaineDistributionRepository;

    @Autowired
    private FranchiseFlbOeChaineValeurRepository franchiseFlbOeChaineValeurRepository;

    @Autowired
    private FranchiseFlbOeChambreRepository franchiseFlbOeChambreRepository;

    @Autowired
    private FranchiseFlbOseAgenceOddRepository franchiseFlbOseAgenceOddRepository;

    @Autowired
    private FranchiseFlbOseCibleRepository franchiseFlbOseCibleRepository;

    @Autowired
    private FranchiseFlbOseIndicateurRepository franchiseFlbOseIndicateurRepository;

    @Autowired
    public CantonImp cantonImp;

    @Autowired
    public CommuneImp communeImp;
    @Autowired
    public PrefectureImp prefectureImp;

    @Autowired
    public RegionImp regionImp;

    @Autowired
    public PaysImp paysImp;
    @Autowired
    public ZoneMonnetaireImp zoneMonnetaireImp;

    @Autowired
    public ContinentInt continentInt;

    @Autowired
    public StatistiqueInterface statistiqueInterface;

    //Repositories

    @Autowired
    public CibleInstitutionRepository cibleInstitutionRepository;

    @Autowired
    public CibleChambreRepository cibleChambreRepository;

    @Autowired
    public IndicateurInstitutionRepository indicateurInstitutionRepository;

    @Autowired
    public IndicateurChambreRepository indicateurChambreRepository;

    @Autowired
    public AffectationFranchiseRepository affectationFranchiseRepository;

    @Autowired
    public FranchiseReferentielRepository franchiseReferentielRepository;


    public Boolean achat_franchise_decoupage_geographique(achatFranchiseInput data) {
        /*
         * Action to be done
         * 1 - Check if the item exist on product registries
         * 2 - Check this item is available
         * 3 - Check if the element all the element of the ancestor have been bought
         * 4 - Check transaction
         * 5 - Buy element (Set the DetailAgr of the user to Franchise)
         */

        int stage = data.getStage();
        Long idFranchise = data.getIdFranchise();
        Long OwnerDetailAgr = data.getIdDetailAgr();
        FranchiseReferentiel franchiseReferentiel = new FranchiseReferentiel();
        franchiseReferentiel.setStage(stage);
        franchiseReferentiel.setIdDetailAgr(OwnerDetailAgr);
        franchiseReferentiel.setTypeDecoupage(1);
       switch (stage){
           case 1:
               // canton
               Canton canton = cantonImp.getCantonById(idFranchise);
               if(canton == null){
                   return false;
               }
               //check if available
               if(canton.getIdDetailAgr() != null)  {
                   return false;
               }
               //translation
               // IF payment is ok
               canton.setIdDetailAgr(OwnerDetailAgr);
               canton.setIsBuy(true);
               canton.setDateBuy(new Date());
               Canton canton1 = cantonImp.updateCanton(canton.getId(),canton);
               franchiseReferentiel.setIdFranchise(canton1.getId());
               franchiseReferentiel.setLabel(canton1.getLibelle());
               franchiseReferentielRepository.save(franchiseReferentiel);
               return true;
           case 2:
            //commune
               Commune commune = communeImp.getCommuneById(idFranchise);
               if(commune == null){
                   return false;
               }

               ///check if available
               if(commune.getIdDetailAgr() != null){
                   return false;
               }

               //check if children have been bought
               //check all the related canto have been bought
               if(cantonImp.getCountFreeCantonByCommune(idFranchise)>0){
                   return false;
               }

               commune.setIdDetailAgr(OwnerDetailAgr);
               commune.setIsBuy(true);
               commune.setDateBuy(new Date());
               Commune commune1 = communeImp.updateCommune(commune);
               franchiseReferentiel.setIdFranchise(commune1.getId());
               franchiseReferentiel.setLabel(commune1.getLibelle());
               franchiseReferentielRepository.save(franchiseReferentiel);
               return true;

           case 3:
            //prefecture

               Prefecture prefecture = prefectureImp.getPrefectureById(idFranchise);
               if(prefecture != null){
                   return false;
               }

                    ///check if available
               if(prefecture.getIdDetailAgr() != null){
                   return false;
               }

                //check if children have been bought
                //check all the related canto have been bought
               int countFreeChildCommune = communeImp.getCountFreeCommuneByPrefecture(idFranchise);
               if(countFreeChildCommune>0){
                   return false;
               }

               prefecture.setIdDetailAgr(OwnerDetailAgr);
               prefecture.setIsBuy(true);
               prefecture.setDateBuy(new Date());
               Prefecture prefecture1 = prefectureImp.updatePrefecture(prefecture);
               franchiseReferentiel.setIdFranchise(prefecture1.getId());
               franchiseReferentiel.setLabel(prefecture1.getLibelle());
               franchiseReferentielRepository.save(franchiseReferentiel);
               return true;

            case 4 :
            //region

                Region region = regionImp.getRegionById(idFranchise);
                if(region == null){
                    return false;
                }

                ///check if available
                if(region.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related canto have been bought
                int countFreeChildPrefecture = prefectureImp.getCountFreePrefectureByRegion(idFranchise);
                if(countFreeChildPrefecture>0){
                    return false;
                }

                region.setIdDetailAgr(OwnerDetailAgr);
                region.setIsBuy(true);
                region.setDateBuy(new Date());
                Region region1 = regionImp.updateRegion(region);
                franchiseReferentiel.setIdFranchise(region1.getId());
                franchiseReferentiel.setLabel(region1.getLibelle());
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;
            case 5 :
            //pays
                Pays pays = paysImp.getPaysById(idFranchise);
                if(pays == null){
                    return false;
                }

                ///check if available
                if(pays.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related child have been bought
                int countFreeChildRegion = regionImp.getCountFreeRegionByPays(idFranchise);
                if(countFreeChildRegion>0){
                    return false;
                }

                pays.setIdDetailAgr(OwnerDetailAgr);
                pays.setIsBuy(true);
                pays.setDateBuy(new Date());
                Pays pays1 = paysImp.updatePays(pays);
                franchiseReferentiel.setIdFranchise(pays1.getId());
                franchiseReferentiel.setLabel(pays1.getLibelle());
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;
           case 6 :
            //zonemonetaie
               ZoneMonnetaire zoneMonnetaire = zoneMonnetaireImp.getZoneMonnetaireById(idFranchise);
               System.out.println(zoneMonnetaire);
               if(zoneMonnetaire == null){
                   return false;
               }



                ///check if available
               if(zoneMonnetaire.getIdDetailAgr() != null){
                   return false;
               }

                //check if children have been bought
                //check all the related child have been bought
               int countFreeChildPays = paysImp.getCountFreePaysByZoneMonnetaire(idFranchise);
               System.out.println(countFreeChildPays);
               if(countFreeChildPays>0){
                   return false;
               }

               zoneMonnetaire.setIdDetailAgr(OwnerDetailAgr);
               zoneMonnetaire.setIsBuy(true);
               zoneMonnetaire.setDateBuy(new Date());
               ZoneMonnetaire zoneMonnetaire1 = zoneMonnetaireImp.updateZoneMonnetaire(zoneMonnetaire);
               franchiseReferentiel.setIdFranchise(zoneMonnetaire1.getId());
               franchiseReferentiel.setLabel(zoneMonnetaire1.getLibelle());
               franchiseReferentielRepository.save(franchiseReferentiel);
               return true;

           case 7:
            //continent
               Continent continent = continentInt.getContinentById(idFranchise);
               if(continent == null){
                   return false;
               }

                ///check if available
               if(continent.getIdDetailAgr() != null){
                   return false;
               }

                //check if children have been bought
                //check all the related child have been bought
               int countFreeChildZoneMonnetaire = zoneMonnetaireImp.getCountFreeZoneMonnetaireByContinent(idFranchise);
               if(countFreeChildZoneMonnetaire>0){
                   return false;
               }

               continent.setIdDetailAgr(OwnerDetailAgr);
               continent.setIsBuy(true);
               continent.setDateBuy(new Date());
               Continent continent1 = continentInt.updateContinent(continent);
               franchiseReferentiel.setIdFranchise(continent1.getId());
               franchiseReferentiel.setLabel(continent1.getLibelle());
               franchiseReferentielRepository.save(franchiseReferentiel);
               return true;
           case 8 :
            //monde
               Monde monde = mondeRepository.findById(idFranchise).orElse(null);
               if(monde == null){
                   return false;
               }

               ///check if available
               if(monde.getIdDetailAgr() != null){
                   return false;
               }

               //check if children have been bought
               //check all the related child have been bought
               int countFreeChildContinent = continentInt.getCountFreeContinentByMonde();
               if(countFreeChildContinent>0){
                   return false;
               }

               monde.setIdDetailAgr(OwnerDetailAgr);
               monde.setIsBuy(true);
               monde.setDateBuy(new Date());
               Monde monde1 = mondeRepository.save(monde);
               franchiseReferentiel.setIdFranchise(monde1.getId());
               franchiseReferentiel.setLabel(monde1.getLibelle());
               franchiseReferentielRepository.save(franchiseReferentiel);
               return true;
           default:
               //other value
            return false;
        }

    }

    public Boolean achat_franchise_decoupage_geographique_check(achatFranchiseInput data) {
        /***
         *
         * Action to be done
         * 1 - Check if the item exist on product registries
         * 2 - Check this item is available
         * 3 - Check if the element all the element of the ancestor have been bought
         * 4 - Check transaction
         * 5 - Buy element (Set the DetailAgr of the user to Franchise)
         *
         *
         */
        int stage = data.getStage();
        Long idFranchise = data.getIdFranchise();
        Long OwnerDetailAgr = data.getIdDetailAgr();
        switch (stage){
            case 1:
                // canton
                Canton canton = cantonImp.getCantonById(idFranchise);
                if(canton == null){
                    return false;
                }
                //check if available
                if(canton.getIdDetailAgr() != null)  {
                    return false;
                }
                return true;
            case 2:
                //commune
                Commune commune = communeImp.getCommuneById(idFranchise);
                if(commune == null){
                    return false;
                }

                ///check if available
                if(commune.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related canto have been bought
                if(cantonImp.getCountFreeCantonByCommune(idFranchise)>0){
                    return false;
                }


                return true;

            case 3:
                //prefecture

                Prefecture prefecture = prefectureImp.getPrefectureById(idFranchise);
                if(prefecture != null){
                    return false;
                }

                ///check if available
                if(prefecture.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related canto have been bought
                int countFreeChildCommune = communeImp.getCountFreeCommuneByPrefecture(idFranchise);
                if(countFreeChildCommune>0){
                    return false;
                }


                return true;

            case 4 :
                //region

                Region region = regionImp.getRegionById(idFranchise);
                if(region == null){
                    return false;
                }

                ///check if available
                if(region.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related canto have been bought
                int countFreeChildPrefecture = prefectureImp.getCountFreePrefectureByRegion(idFranchise);
                if(countFreeChildPrefecture>0){
                    return false;
                }
                return true;
            case 5 :
                //pays
                Pays pays = paysImp.getPaysById(idFranchise);
                if(pays == null){
                    return false;
                }

                ///check if available
                if(pays.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related child have been bought
                int countFreeChildRegion = regionImp.getCountFreeRegionByPays(idFranchise);
                if(countFreeChildRegion>0){
                    return false;
                }
                return true;
            case 6 :
                //zonemonetaie
                ZoneMonnetaire zoneMonnetaire = zoneMonnetaireImp.getZoneMonnetaireById(idFranchise);
                System.out.println(zoneMonnetaire);
                if(zoneMonnetaire == null){
                    return false;
                }



                ///check if available
                if(zoneMonnetaire.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related child have been bought
                int countFreeChildPays = paysImp.getCountFreePaysByZoneMonnetaire(idFranchise);
                System.out.println(countFreeChildPays);
                if(countFreeChildPays>0){
                    return false;
                }
                zoneMonnetaireImp.updateZoneMonnetaire(zoneMonnetaire);
                return true;

            case 7:
                //continent
                Continent continent = continentInt.getContinentById(idFranchise);
                if(continent == null){
                    return false;
                }

                ///check if available
                if(continent.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related child have been bought
                int countFreeChildZoneMonnetaire = zoneMonnetaireImp.getCountFreeZoneMonnetaireByContinent(idFranchise);
                if(countFreeChildZoneMonnetaire>0){
                    return false;
                }

                continentInt.updateContinent(continent);
                return true;
            case 8 :
                //monde
                Monde monde = mondeRepository.findById(idFranchise).orElse(null);
                if(monde == null){
                    return false;
                }

                ///check if available
                if(monde.getIdDetailAgr() != null){
                    return false;
                }

                //check if children have been bought
                //check all the related child have been bought
                int countFreeChildContinent = continentInt.getCountFreeContinentByMonde();
                if(countFreeChildContinent>0){
                    return false;
                }

                mondeRepository.save(monde);
                return true;
            default:
                //other value
                return false;
        }

    }

    public <T> T getFirstElement(List<T> listElements){
        T element = null;
        if(listElements.size() > 0){
            element = listElements.get(0);
        }

        return element;
    }

    public Boolean achat_franchise_decoupage_centre(achatFranchiseInput data) {
        /***
         *
         *
         * Action to be done
         * 1 - Check if the item exist on product registries
         * 2 - Check this item is available
         * 3 - Check if the element all the element of the ancestor have been bought
         * 4 - Check transaction
         * 5 - Buy element (Set the DetailAgr of the user to Franchise)
         *
         *
         */
        int stage = data.getStage();
        Long idFranchise = data.getIdFranchise();
        Long OwnerDetailAgr = data.getIdDetailAgr();
        Long idFranchiseCanton = data.getIdFranchiseCanton();
        Long cible = data.getIdFranchiseCible();
        Long indicateur = data.getIdFranchiseIndicateur();
        //System.out.println(data.getCibleElement());
        Boolean CibleElement = data.getCibleElement() ==1;
        FranchiseReferentiel franchiseReferentiel = new FranchiseReferentiel();
        franchiseReferentiel.setStage(stage);
        franchiseReferentiel.setIdDetailAgr(OwnerDetailAgr);
        franchiseReferentiel.setTypeDecoupage(2);
        switch (stage){
            case 1:
                // Franchise Fill Chaine Distributor
                FillChaineDistribution fillChaineDistribution =  fillChaineDistributionInterface.getById(idFranchise);
                if(fillChaineDistribution == null){
                    return false;
                }

                // check franchise chaine Distribution
                FranchiseFillChaineDistribution  franchiseFillChaineDistribution;
                if(CibleElement == true){
                    franchiseFillChaineDistribution =   getFirstElement(franchiseFillChaineDistributionRepository.getByTypeCibleElement(cible,idFranchise));
                }else {
                    franchiseFillChaineDistribution =    getFirstElement(franchiseFillChaineDistributionRepository.getByTypeIndicateurElement(indicateur,idFranchise));
                }
                System.out.println(franchiseFillChaineDistribution);
                if(franchiseFillChaineDistribution == null){
                    //check if available
                    franchiseFillChaineDistribution = new FranchiseFillChaineDistribution();
                }else{
                    if(franchiseFillChaineDistribution.getIdDetailAgr() != null)  {
                        return false;
                    }
                }

                //translation
                // check and create the ancestor
                //1 - Chaine Valeur

                // check franchise chaine Valeur
                FranchiseFillChaineValeur franchiseFillChaineValeur;
                FillChaineValeur fillChaineValeur =  this.fillChaineValeurInterface.getById(fillChaineDistribution.getIdChaineValeur());
                if(CibleElement == true){
                    franchiseFillChaineValeur =   getFirstElement(franchiseFillChaineValeurRepository.getByTypeCibleElement(cible,fillChaineValeur.getId()));
                }else {
                    franchiseFillChaineValeur =    getFirstElement(franchiseFillChaineValeurRepository.getByTypeIndicateurElement(indicateur,fillChaineValeur.getId()));
                }
                System.out.println(franchiseFillChaineValeur);
                 if(franchiseFillChaineValeur == null){
                     franchiseFillChaineValeur = new FranchiseFillChaineValeur();
                     franchiseFillChaineValeur.setFillChaineValeur(fillChaineValeur);
                     franchiseFillChaineValeur.setLibelle(fillChaineValeur.getLibelle());
                     //franchiseFillChaineValeur.setIdDetailAgr(OwnerDetailAgr);
                     //franchiseFillChaineValeur.setIsBuy(true);
                     //franchiseFillChaineValeur.setDateBuy(new Date());
                     franchiseFillChaineValeur=  franchiseFillChaineValeurRepository.save(franchiseFillChaineValeur);
                 }

                //2 - Institution

                // check franchise chaine Valeur
                FranchiseFillInstitution franchiseFillInstitution;
                FillInstitution fillInstitution =  this.fillInstitutionInterface.getById(fillChaineValeur.getIdInstitution());
                if(CibleElement == true){
                    franchiseFillInstitution =   getFirstElement(franchiseFillInstitutionRepository.getByTypeCibleElement(cible,fillInstitution.getId()));
                }else {
                    franchiseFillInstitution =    getFirstElement(franchiseFillInstitutionRepository.getByTypeIndicateurElement(indicateur,fillInstitution.getId()));
                }
                if(franchiseFillInstitution == null){
                    franchiseFillInstitution = new FranchiseFillInstitution();
                    franchiseFillInstitution.setFillInstitution(fillInstitution);
                    franchiseFillInstitution.setLibelle(fillInstitution.getLibelle());
                    if(CibleElement == true){
                        franchiseFillInstitution.setIdCible(cible);
                    }else {
                        franchiseFillInstitution.setIdIndicateur(indicateur);
                    }
                    //franchiseFillInstitution.setIdDetailAgr(OwnerDetailAgr);
                    //franchiseFillInstitution.setIsBuy(true);²
                    //franchiseFillInstitution.setDateBuy(new Date());
                    franchiseFillInstitution =   franchiseFillInstitutionRepository.save(franchiseFillInstitution);
                }

                //if franchise not get institution to it to him
                if(franchiseFillChaineValeur.getIdInstitution() == null) {
                    franchiseFillChaineValeur.setIdInstitution(franchiseFillInstitution.getId());
                    franchiseFillChaineValeurRepository.save(franchiseFillChaineValeur);
                }

                // IF payment is ok
                franchiseFillChaineDistribution.setIdDetailAgr(OwnerDetailAgr);
                franchiseFillChaineDistribution.setLibelle(fillChaineDistribution.getLibelle());
                franchiseFillChaineDistribution.setIdChaineValeur(franchiseFillChaineValeur.getId());
                franchiseFillChaineDistribution.setFillChaineDistribution(fillChaineDistribution);
                franchiseFillChaineDistribution.setIsBuy(true);
                franchiseFillChaineDistribution.setDateBuy(new Date());
                FranchiseFillChaineDistribution franchiseFillChaineDistribution1 = franchiseFillChaineDistributionRepository.save(franchiseFillChaineDistribution);
                franchiseReferentiel.setIdFranchise(franchiseFillChaineDistribution1.getId());
                franchiseReferentiel.setLabel(franchiseFillChaineDistribution1.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);

                return true;
            case 2:
                // Franchise Fill Chaine Valeur
                FillChaineValeur fillChaineValeur1 =  fillChaineValeurInterface.getById(idFranchise);
                System.out.println(fillChaineValeur1);
                if(fillChaineValeur1 == null) {
                    return false;
                }
                System.out.println(idFranchise);

                FranchiseFillChaineValeur franchiseFillChaineValeur1;
                if(CibleElement == true) {
                    franchiseFillChaineValeur1 =   getFirstElement(franchiseFillChaineValeurRepository.getByTypeCibleElement(cible,fillChaineValeur1.getId()));
                }else {
                    franchiseFillChaineValeur1 =    getFirstElement(franchiseFillChaineValeurRepository.getByTypeIndicateurElement(indicateur,fillChaineValeur1.getId()));
                }


                //FranchiseFillChaineValeur franchiseFillChaineValeur1 = franchiseFillChaineValeurRepository.findById(idFranchise).orElse(null);
                if(franchiseFillChaineValeur1 == null){
                    return false;
                }
                System.out.println(franchiseFillChaineValeur1);
                //check if available
                if(franchiseFillChaineValeur1.getIdDetailAgr() != null)  {
                    return false;
                }

                //check all the related child have been bought
                int countFreeChildFillChaineValeur = franchiseFillChaineDistributionRepository.getCountFillChaineDistributionByFillChaineValeur(idFranchise);
                System.out.println(countFreeChildFillChaineValeur);
                if(countFreeChildFillChaineValeur>0){
                    return false;
                }
                //translation
                // IF payment is ok
                franchiseFillChaineValeur1.setIdDetailAgr(OwnerDetailAgr);
                franchiseFillChaineValeur1.setIsBuy(true);
                franchiseFillChaineValeur1.setDateBuy(new Date());
                FranchiseFillChaineValeur franchiseFillChaineValeur2 = franchiseFillChaineValeurRepository.save(franchiseFillChaineValeur1);
                franchiseReferentiel.setIdFranchise(franchiseFillChaineValeur2.getId());
                franchiseReferentiel.setLabel(franchiseFillChaineValeur2.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;
            case 3:
                // Franchise Fill Institution

                FranchiseFillInstitution franchiseFillInstitution1 = franchiseFillInstitutionRepository.findById(idFranchise).orElse(null);
                if(franchiseFillInstitution1 == null){
                    return false;
                }
                //check if available
                if(franchiseFillInstitution1.getIdDetailAgr() != null)  {
                    return false;
                }
                //check all the related child have been bought
                int countFreeChildFillInstitution = franchiseFillChaineValeurRepository.getCountFillChaineValeurByFillInstitution(idFranchise);
                System.out.println(countFreeChildFillInstitution);
                if(countFreeChildFillInstitution>0){
                    return false;
                }
                //translation
                // IF payment is ok
                franchiseFillInstitution1.setIdDetailAgr(OwnerDetailAgr);
                franchiseFillInstitution1.setIsBuy(true);
                franchiseFillInstitution1.setDateBuy(new Date());
                FranchiseFillInstitution franchiseFillInstitution2 = franchiseFillInstitutionRepository.save(franchiseFillInstitution1);
                franchiseReferentiel.setIdFranchise(franchiseFillInstitution2.getId());
                franchiseReferentiel.setLabel(franchiseFillInstitution2.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;

            case 4:
                // Franchise FlbOe Chaine Distributor
                FlbOeChaineDistribution flbOeChaineDistribution =  flbOeChaineDistributionInterface.getById(idFranchise);
                if(flbOeChaineDistribution == null){
                    return false;
                }

                // check franchise chaine Distribution
                FranchiseFlbOeChaineDistribution  franchiseFlbOeChaineDistribution;
                if(CibleElement == true){
                    franchiseFlbOeChaineDistribution =   getFirstElement(franchiseFlbOeChaineDistributionRepository.getByTypeCibleElement(cible,idFranchise));
                }else {
                    franchiseFlbOeChaineDistribution =    getFirstElement(franchiseFlbOeChaineDistributionRepository.getByTypeIndicateurElement(indicateur,idFranchise));
                }
                System.out.println(franchiseFlbOeChaineDistribution);
                if(franchiseFlbOeChaineDistribution == null){
                    //check if available
                    franchiseFlbOeChaineDistribution = new FranchiseFlbOeChaineDistribution();
                }else{
                    if(franchiseFlbOeChaineDistribution.getIdDetailAgr() != null)  {
                        return false;
                    }
                }

                //translation
                // check and create the ancestor
                //1 - Chaine Valeur
                // check franchise chaine Valeur
                FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur;
                FlbOeChaineValeur flbOeChaineValeur =  this.flbOeChaineValeurInterface.getById(flbOeChaineDistribution.getIdChaineValeur());
                if(CibleElement == true){
                    franchiseFlbOeChaineValeur =   getFirstElement(franchiseFlbOeChaineValeurRepository.getByTypeCibleElement(cible,flbOeChaineValeur.getId()));
                }else {
                    franchiseFlbOeChaineValeur =    getFirstElement(franchiseFlbOeChaineValeurRepository.getByTypeIndicateurElement(indicateur,flbOeChaineValeur.getId()));
                }
                System.out.println(franchiseFlbOeChaineValeur);
                if(franchiseFlbOeChaineValeur == null){
                    franchiseFlbOeChaineValeur = new FranchiseFlbOeChaineValeur();
                    franchiseFlbOeChaineValeur.setFlbOeChaineValeur(flbOeChaineValeur);
                    franchiseFlbOeChaineValeur.setLibelle(flbOeChaineValeur.getLibelle());
                    //franchiseFlbOeChaineValeur.setIdDetailAgr(OwnerDetailAgr);
                    //franchiseFlbOeChaineValeur.setIsBuy(true);
                    //franchiseFlbOeChaineValeur.setDateBuy(new Date());
                    franchiseFlbOeChaineValeur=  franchiseFlbOeChaineValeurRepository.save(franchiseFlbOeChaineValeur);
                }

                //2 - Chambre

                // check franchise chaine Valeur
                FranchiseFlbOeChambre franchiseFlbOeChambre;
                FlbOeChambre flbOeChambre =  this.flbOeChambreInterface.getById(flbOeChaineValeur.getIdChambre());
                if(CibleElement == true){
                    franchiseFlbOeChambre =   getFirstElement(franchiseFlbOeChambreRepository.getByTypeCibleElement(cible,flbOeChambre.getId()));
                }else {
                    franchiseFlbOeChambre =    getFirstElement(franchiseFlbOeChambreRepository.getByTypeIndicateurElement(indicateur,flbOeChambre.getId()));
                }
                if(franchiseFlbOeChambre == null){
                    franchiseFlbOeChambre = new FranchiseFlbOeChambre();
                    franchiseFlbOeChambre.setFlbOeChambre(flbOeChambre);
                    franchiseFlbOeChambre.setLibelle(flbOeChambre.getLibelle());
                    if(CibleElement == true){
                        franchiseFlbOeChambre.setIdCible(cible);
                    }else {
                        franchiseFlbOeChambre.setIdIndicateur(indicateur);
                    }
                    //franchiseFlbOeChambre.setIdDetailAgr(OwnerDetailAgr);
                    //franchiseFlbOeChambre.setIsBuy(true);²
                    //franchiseFlbOeChambre.setDateBuy(new Date());
                    franchiseFlbOeChambre =   franchiseFlbOeChambreRepository.save(franchiseFlbOeChambre);
                }

                //if franchise not get institution to it to him
                if(franchiseFlbOeChaineValeur.getIdChambre() == null) {
                    franchiseFlbOeChaineValeur.setIdChambre(franchiseFlbOeChambre.getId());
                    franchiseFlbOeChaineValeurRepository.save(franchiseFlbOeChaineValeur);
                }

                // IF payment is ok
                franchiseFlbOeChaineDistribution.setIdDetailAgr(OwnerDetailAgr);
                franchiseFlbOeChaineDistribution.setLibelle(flbOeChaineDistribution.getLibelle());
                franchiseFlbOeChaineDistribution.setIdChaineValeur(franchiseFlbOeChaineValeur.getId());
                franchiseFlbOeChaineDistribution.setFlbOeChaineDistribution(flbOeChaineDistribution);
                franchiseFlbOeChaineDistribution.setIsBuy(true);
                franchiseFlbOeChaineDistribution.setDateBuy(new Date());
                FranchiseFlbOeChaineDistribution franchiseFlbOeChaineDistribution1 = franchiseFlbOeChaineDistributionRepository.save(franchiseFlbOeChaineDistribution);
                franchiseReferentiel.setIdFranchise(franchiseFlbOeChaineDistribution1.getId());
                franchiseReferentiel.setLabel(franchiseFlbOeChaineDistribution1.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;
            case 5:
                // Franchise FlbOe Chaine Valeur
                FlbOeChaineValeur flbOeChaineValeur1 =  flbOeChaineValeurInterface.getById(idFranchise);
                System.out.println(flbOeChaineValeur1);
                if(flbOeChaineValeur1 == null) {
                    return false;
                }
                System.out.println(idFranchise);

                FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur1;
                if(CibleElement == true) {
                    franchiseFlbOeChaineValeur1 =   getFirstElement(franchiseFlbOeChaineValeurRepository.getByTypeCibleElement(cible,flbOeChaineValeur1.getId()));
                }else {
                    franchiseFlbOeChaineValeur1 =    getFirstElement(franchiseFlbOeChaineValeurRepository.getByTypeIndicateurElement(indicateur,flbOeChaineValeur1.getId()));
                }


                //FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur1 = franchiseFlbOeChaineValeurRepository.findById(idFranchise).orElse(null);
                if(franchiseFlbOeChaineValeur1 == null){
                    return false;
                }
                System.out.println(franchiseFlbOeChaineValeur1);
                //check if available
                if(franchiseFlbOeChaineValeur1.getIdDetailAgr() != null)  {
                    return false;
                }

                //check all the related child have been bought
                int countFreeChildFlbOeChaineValeur = franchiseFlbOeChaineDistributionRepository.getCountFlbOeChaineDistributionByFlbOeChaineValeur(idFranchise);
                System.out.println(countFreeChildFlbOeChaineValeur);
                if(countFreeChildFlbOeChaineValeur>0){
                    return false;
                }
                //translation
                // IF payment is ok
                franchiseFlbOeChaineValeur1.setIdDetailAgr(OwnerDetailAgr);
                franchiseFlbOeChaineValeur1.setIsBuy(true);
                franchiseFlbOeChaineValeur1.setDateBuy(new Date());
                FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur2 = franchiseFlbOeChaineValeurRepository.save(franchiseFlbOeChaineValeur1);
                franchiseReferentiel.setIdFranchise(franchiseFlbOeChaineValeur2.getId());
                franchiseReferentiel.setLabel(franchiseFlbOeChaineValeur2.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);

                return true;
            case 6:
                // Franchise FlbOe Chambre


                FranchiseFlbOeChambre franchiseFlbOeChambre1 = franchiseFlbOeChambreRepository.findById(idFranchise).orElse(null);
                if(franchiseFlbOeChambre1 == null){
                    return false;
                }
                //check if available
                if(franchiseFlbOeChambre1.getIdDetailAgr() != null)  {
                    return false;
                }
                //check all the related child have been bought
                int countFreeChildFlbOeChambre = franchiseFlbOeChaineValeurRepository.getCountFlbOeChaineValeurByFlbOeChambre(idFranchise);
                System.out.println(countFreeChildFlbOeChambre);
                if(countFreeChildFlbOeChambre>0){
                    return false;
                }
                //translation
                // IF payment is ok
                franchiseFlbOeChambre1.setIdDetailAgr(OwnerDetailAgr);
                franchiseFlbOeChambre1.setIsBuy(true);
                franchiseFlbOeChambre1.setDateBuy(new Date());
                FranchiseFlbOeChambre franchiseFlbOeChambre2 =  franchiseFlbOeChambreRepository.save(franchiseFlbOeChambre1);
                franchiseReferentiel.setIdFranchise(franchiseFlbOeChambre2.getId());
                franchiseReferentiel.setLabel(franchiseFlbOeChambre2.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;

            case 7:
                // Franchise FlbOse Cible

                FlbOseCible flbOseCible =  flbOseCibleInterface.getById(idFranchise);
                System.out.println("=====================01=======================");
                System.out.println(flbOseCible);
                System.out.println("=============================================");
                if(flbOseCible == null){
                    return false;
                }

                // check franchise Ose Cible
                FranchiseFlbOseCible franchiseFlbOseCible;

                franchiseFlbOseCible =   getFirstElement(franchiseFlbOseCibleRepository.getByTypeCantonElement(idFranchise,idFranchiseCanton));
                System.out.println("=====================03=======================");
                System.out.println(franchiseFlbOseCible);
                System.out.println("=============================================");

                if(franchiseFlbOseCible == null){
                    franchiseFlbOseCible = new FranchiseFlbOseCible();
                }else {

                    //check if available
                    if(franchiseFlbOseCible.getIdDetailAgr() != null)  {
                        return false;
                    }
                }



                //Check Agence Odd
                FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd1;
                FlbOseAgenceOdd flbOseAgenceOdd =  this.flbOseAgenceOddInterface.getById(flbOseCible.getIdAgenceOdd());
                franchiseFlbOseAgenceOdd1 =   getFirstElement(franchiseFlbOseAgenceOddRepository.getByTypeCantonElement(flbOseCible.getIdAgenceOdd(),idFranchiseCanton));
                if(franchiseFlbOseAgenceOdd1 == null){
                    franchiseFlbOseAgenceOdd1 = new FranchiseFlbOseAgenceOdd();
                    franchiseFlbOseAgenceOdd1.setLibelle(flbOseAgenceOdd.getLibelle());
                    franchiseFlbOseAgenceOdd1.setFlbOseAgenceOdd(flbOseAgenceOdd);
                    franchiseFlbOseAgenceOdd1.setIdCanton(idFranchiseCanton);
                    //franchiseFlbOeChaineValeur.setIdDetailAgr(OwnerDetailAgr);
                    //franchiseFlbOeChaineValeur.setIsBuy(true);
                    //franchiseFlbOeChaineValeur.setDateBuy(new Date());
                    franchiseFlbOseAgenceOdd1 =  franchiseFlbOseAgenceOddRepository.save(franchiseFlbOseAgenceOdd1);
                }

                // IF payment is ok
                franchiseFlbOseCible.setIdDetailAgr(OwnerDetailAgr);
                franchiseFlbOseCible.setFlbOseCible(flbOseCible);
                franchiseFlbOseCible.setIdAgenceOdd(franchiseFlbOseAgenceOdd1.getId());
                franchiseFlbOseCible.setIsBuy(true);
                franchiseFlbOseCible.setLibelle(flbOseCible.getLibelle());
                franchiseFlbOseCible.setDateBuy(new Date());
                FranchiseFlbOseCible franchiseFlbOseCible1 =  franchiseFlbOseCibleRepository.save(franchiseFlbOseCible);
                franchiseReferentiel.setIdFranchise(franchiseFlbOseCible1.getId());
                franchiseReferentiel.setLabel(franchiseFlbOseCible1.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;
            case 8 :
                // Franchise FlbOse Indicateur
                FlbOseIndicateur flbOseIndicateur =  flbOseIndicateurInterface.getById(idFranchise);
                if(flbOseIndicateur == null){
                    return false;
                }

                // check franchise Ose Cible
                FranchiseFlbOseIndicateur franchiseFlbOseIndicateur;

                franchiseFlbOseIndicateur =   getFirstElement(franchiseFlbOseIndicateurRepository.getByTypeCantonElement(idFranchise,idFranchiseCanton));
                System.out.println(franchiseFlbOseIndicateur);
                System.out.println(idFranchise);
                System.out.println(idFranchiseCanton);
                if(franchiseFlbOseIndicateur == null){
                    franchiseFlbOseIndicateur = new FranchiseFlbOseIndicateur();
                }else{
                    //check if available

                    if(franchiseFlbOseIndicateur.getIdDetailAgr() != null)  {
                        return false;
                    }
                }


                //Check Agence Odd
                FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd3;
                FlbOseAgenceOdd flbOseAgenceOdd3 =  this.flbOseAgenceOddInterface.getById(flbOseIndicateur.getIdAgenceOdd());
                franchiseFlbOseAgenceOdd3 =   getFirstElement(franchiseFlbOseAgenceOddRepository.getByTypeCantonElement(flbOseIndicateur.getIdAgenceOdd(),idFranchiseCanton));
                if(franchiseFlbOseAgenceOdd3 == null){
                    franchiseFlbOseAgenceOdd3 = new FranchiseFlbOseAgenceOdd();
                    franchiseFlbOseAgenceOdd3.setLibelle(flbOseAgenceOdd3.getLibelle());
                    franchiseFlbOseAgenceOdd3.setFlbOseAgenceOdd(flbOseAgenceOdd3);
                    franchiseFlbOseAgenceOdd3.setIdCanton(idFranchiseCanton);
                    //franchiseFlbOeChaineValeur.setIdDetailAgr(OwnerDetailAgr);
                    //franchiseFlbOeChaineValeur.setIsBuy(true);
                    //franchiseFlbOeChaineValeur.setDateBuy(new Date());
                    franchiseFlbOseAgenceOdd3 = franchiseFlbOseAgenceOddRepository.save(franchiseFlbOseAgenceOdd3);
                }

                // IF payment is ok
                franchiseFlbOseIndicateur.setIdDetailAgr(OwnerDetailAgr);
                franchiseFlbOseIndicateur.setFlbOseIndicateur(flbOseIndicateur);
                franchiseFlbOseIndicateur.setIdAgenceOdd(franchiseFlbOseAgenceOdd3.getId());
                franchiseFlbOseIndicateur.setIsBuy(true);
                franchiseFlbOseIndicateur.setDateBuy(new Date());
                franchiseFlbOseIndicateur.setLibelle(flbOseIndicateur.getLibelle());
                FranchiseFlbOseIndicateur franchiseFlbOseIndicateur1 = franchiseFlbOseIndicateurRepository.save(franchiseFlbOseIndicateur);
                franchiseReferentiel.setIdFranchise(franchiseFlbOseIndicateur1.getId());
                franchiseReferentiel.setLabel(franchiseFlbOseIndicateur1.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);

                return true;
            case 9 :
                // Franchise FlbOse  AgenceOdd
                FlbOseAgenceOdd flbOseAgenceOdd4 =  this.flbOseAgenceOddInterface.getById(idFranchise);

                if(flbOseAgenceOdd4 == null){
                    return false;
                }

                //FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd2 = franchiseFlbOseAgenceOddRepository.findById(idFranchise).orElse(null);
                FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd2 =   getFirstElement(franchiseFlbOseAgenceOddRepository.getByTypeCantonElement(idFranchise,idFranchiseCanton));

                if(franchiseFlbOseAgenceOdd2 == null){
                    return false;
                }
                //check if available
                if(franchiseFlbOseAgenceOdd2.getIdDetailAgr() != null)  {
                    return false;
                }

                int countFreeChildFlbOseCible = franchiseFlbOseCibleRepository.getCountFlbOseCibleByFlbOseAgenceOdd(idFranchise);
                int countFreeChildFlbOseIndicateur = franchiseFlbOseIndicateurRepository.getCountFlbOseIndicateurByFlbOseAgenceOdd(idFranchise);
                if((countFreeChildFlbOseCible+countFreeChildFlbOseIndicateur)>0){
                    return false;
                }
                //translation
                // IF payment is ok
                franchiseFlbOseAgenceOdd2.setIdDetailAgr(OwnerDetailAgr);
                franchiseFlbOseAgenceOdd2.setFlbOseAgenceOdd(flbOseAgenceOdd4);
                franchiseFlbOseAgenceOdd2.setLibelle(flbOseAgenceOdd4.getLibelle());
                franchiseFlbOseAgenceOdd2.setIsBuy(true);
                franchiseFlbOseAgenceOdd2.setDateBuy(new Date());
                FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd = franchiseFlbOseAgenceOddRepository.save(franchiseFlbOseAgenceOdd2);
                franchiseReferentiel.setIdFranchise(franchiseFlbOseAgenceOdd.getId());
                franchiseReferentiel.setLabel(franchiseFlbOseAgenceOdd.getLibelle() + "-" + idFranchiseCanton);
                franchiseReferentielRepository.save(franchiseReferentiel);
                return true;
            default:
                //other value
                return false;
        }
    }

    public Boolean achat_franchise_decoupage_centre_check(achatFranchiseInput data) {

        /***
         *
         *
         * Action to be done
         * 1 - Check if the item exist on product registries
         * 2 - Check this item is available
         * 3 - Check if the element all the element of the ancestor have been bought
         * 4 - Check transaction
         * 5 - Buy element (Set the DetailAgr of the user to Franchise)
         *
         *
         */
        int stage = data.getStage();
        Long idFranchise = data.getIdFranchise();
        Long idFranchiseCanton = data.getIdFranchiseCanton();
        Long cible = data.getIdFranchiseCible();
        Long indicateur = data.getIdFranchiseIndicateur();
        //System.out.println(data.getCibleElement());
        Boolean CibleElement = data.getCibleElement() ==1;
        switch (stage) {
            case 1:
                // Franchise Fill Chaine Distributor
                FillChaineDistribution fillChaineDistribution = fillChaineDistributionInterface.getById(idFranchise);
                if (fillChaineDistribution == null) {
                    return false;
                }

                // check franchise chaine Distribution
                FranchiseFillChaineDistribution franchiseFillChaineDistribution;
                if (CibleElement == true) {
                    franchiseFillChaineDistribution = getFirstElement(franchiseFillChaineDistributionRepository.getByTypeCibleElement(cible, idFranchise));
                } else {
                    franchiseFillChaineDistribution = getFirstElement(franchiseFillChaineDistributionRepository.getByTypeIndicateurElement(indicateur, idFranchise));
                }
                System.out.println(franchiseFillChaineDistribution);
                if (franchiseFillChaineDistribution != null) {
                    if (franchiseFillChaineDistribution.getIdDetailAgr() != null) {
                        return false;
                    }
                }

                return true;
            case 2:
                // Franchise Fill Chaine Valeur
                FillChaineValeur fillChaineValeur1 = fillChaineValeurInterface.getById(idFranchise);
                if (fillChaineValeur1 == null) {
                    return false;
                }

                FranchiseFillChaineValeur franchiseFillChaineValeur1;
                if (CibleElement == true) {
                    franchiseFillChaineValeur1 = getFirstElement(franchiseFillChaineValeurRepository.getByTypeCibleElement(cible, fillChaineValeur1.getId()));
                } else {
                    franchiseFillChaineValeur1 = getFirstElement(franchiseFillChaineValeurRepository.getByTypeIndicateurElement(indicateur, fillChaineValeur1.getId()));
                }

                if (franchiseFillChaineValeur1 == null) {
                    return false;
                }
                System.out.println(franchiseFillChaineValeur1);
                //check if available
                if (franchiseFillChaineValeur1.getIdDetailAgr() != null) {
                    return false;
                }

                //check all the related child have been bought
                int countFreeChildFillChaineValeur = franchiseFillChaineDistributionRepository.getCountFillChaineDistributionByFillChaineValeur(idFranchise);
                System.out.println(countFreeChildFillChaineValeur);
                if (countFreeChildFillChaineValeur > 0) {
                    return false;
                }

                return true;
            case 3:
                // Franchise Fill Institution
                FranchiseFillInstitution franchiseFillInstitution1 = franchiseFillInstitutionRepository.findById(idFranchise).orElse(null);
                if (franchiseFillInstitution1 == null) {
                    return false;
                }
                //check if available
                if (franchiseFillInstitution1.getIdDetailAgr() != null) {
                    return false;
                }
                //check all the related child have been bought
                int countFreeChildFillInstitution = franchiseFillChaineValeurRepository.getCountFillChaineValeurByFillInstitution(idFranchise);
                System.out.println(countFreeChildFillInstitution);
                if (countFreeChildFillInstitution > 0) {
                    return false;
                }
                return true;

            case 4:
                // Franchise FlbOe Chaine Distributor
                FlbOeChaineDistribution flbOeChaineDistribution = flbOeChaineDistributionInterface.getById(idFranchise);
                if (flbOeChaineDistribution == null) {
                    return false;
                }

                // check franchise chaine Distribution
                FranchiseFlbOeChaineDistribution franchiseFlbOeChaineDistribution;
                if (CibleElement == true) {
                    franchiseFlbOeChaineDistribution = getFirstElement(franchiseFlbOeChaineDistributionRepository.getByTypeCibleElement(cible, idFranchise));
                } else {
                    franchiseFlbOeChaineDistribution = getFirstElement(franchiseFlbOeChaineDistributionRepository.getByTypeIndicateurElement(indicateur, idFranchise));
                }
                if (franchiseFlbOeChaineDistribution != null) {
                    if (franchiseFlbOeChaineDistribution.getIdDetailAgr() != null) {
                        return false;
                    }
                }

                return true;
            case 5:
                // Franchise FlbOe Chaine Valeur
                FlbOeChaineValeur flbOeChaineValeur1 = flbOeChaineValeurInterface.getById(idFranchise);
                System.out.println(flbOeChaineValeur1);
                if (flbOeChaineValeur1 == null) {
                    return false;
                }
                System.out.println(idFranchise);

                FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur1;
                if (CibleElement == true) {
                    franchiseFlbOeChaineValeur1 = getFirstElement(franchiseFlbOeChaineValeurRepository.getByTypeCibleElement(cible, flbOeChaineValeur1.getId()));
                } else {
                    franchiseFlbOeChaineValeur1 = getFirstElement(franchiseFlbOeChaineValeurRepository.getByTypeIndicateurElement(indicateur, flbOeChaineValeur1.getId()));
                }


                //FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur1 = franchiseFlbOeChaineValeurRepository.findById(idFranchise).orElse(null);
                if (franchiseFlbOeChaineValeur1 == null) {
                    return false;
                }
                System.out.println(franchiseFlbOeChaineValeur1);
                //check if available
                if (franchiseFlbOeChaineValeur1.getIdDetailAgr() != null) {
                    return false;
                }

                //check all the related child have been bought
                int countFreeChildFlbOeChaineValeur = franchiseFlbOeChaineDistributionRepository.getCountFlbOeChaineDistributionByFlbOeChaineValeur(idFranchise);
                if (countFreeChildFlbOeChaineValeur > 0) {
                    return false;
                }

                return true;
            case 6:
                // Franchise FlbOe Chambre


                FranchiseFlbOeChambre franchiseFlbOeChambre1 = franchiseFlbOeChambreRepository.findById(idFranchise).orElse(null);
                if (franchiseFlbOeChambre1 == null) {
                    return false;
                }
                //check if available
                if (franchiseFlbOeChambre1.getIdDetailAgr() != null) {
                    return false;
                }
                //check all the related child have been bought
                int countFreeChildFlbOeChambre = franchiseFlbOeChaineValeurRepository.getCountFlbOeChaineValeurByFlbOeChambre(idFranchise);
                System.out.println(countFreeChildFlbOeChambre);
                if (countFreeChildFlbOeChambre > 0) {
                    return false;
                }
                return true;

            case 7:
                // Franchise FlbOse Cible

                FlbOseCible flbOseCible = flbOseCibleInterface.getById(idFranchise);
                if (flbOseCible == null) {
                    return false;
                }

                // check franchise Ose Cible
                FranchiseFlbOseCible franchiseFlbOseCible;

                franchiseFlbOseCible = getFirstElement(franchiseFlbOseCibleRepository.getByTypeCantonElement(idFranchise, idFranchiseCanton));

                if (franchiseFlbOseCible == null) {
                    franchiseFlbOseCible = new FranchiseFlbOseCible();
                } else {

                    //check if available
                    if (franchiseFlbOseCible.getIdDetailAgr() != null) {
                        return false;
                    }
                }

                return true;
            case 8:
                // Franchise FlbOse Indicateur
                FlbOseIndicateur flbOseIndicateur = flbOseIndicateurInterface.getById(idFranchise);
                if (flbOseIndicateur == null) {
                    return false;
                }

                // check franchise Ose Cible
                FranchiseFlbOseIndicateur franchiseFlbOseIndicateur;

                franchiseFlbOseIndicateur = getFirstElement(franchiseFlbOseIndicateurRepository.getByTypeCantonElement(idFranchise, idFranchiseCanton));
                if (franchiseFlbOseIndicateur != null) {

                    if (franchiseFlbOseIndicateur.getIdDetailAgr() != null) {
                        return false;
                    }
                }

                return true;
            case 9:
                // Franchise FlbOse  AgenceOdd
                FlbOseAgenceOdd flbOseAgenceOdd4 = this.flbOseAgenceOddInterface.getById(idFranchise);

                if (flbOseAgenceOdd4 == null) {
                    return false;
                }

                //FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd2 = franchiseFlbOseAgenceOddRepository.findById(idFranchise).orElse(null);
                FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd2 = getFirstElement(franchiseFlbOseAgenceOddRepository.getByTypeCantonElement(idFranchise, idFranchiseCanton));

                if (franchiseFlbOseAgenceOdd2 == null) {
                    return false;
                }
                //check if available
                if (franchiseFlbOseAgenceOdd2.getIdDetailAgr() != null) {
                    return false;
                }

                int countFreeChildFlbOseCible = franchiseFlbOseCibleRepository.getCountFlbOseCibleByFlbOseAgenceOdd(idFranchise);
                int countFreeChildFlbOseIndicateur = franchiseFlbOseIndicateurRepository.getCountFlbOseIndicateurByFlbOseAgenceOdd(idFranchise);
                if ((countFreeChildFlbOseCible + countFreeChildFlbOseIndicateur) > 0) {
                    return false;
                }

                return true;
            default:
                //other value
                return false;
        }
    }

    public Canton getCentreByFranchiseFlbOseAgenceOdd(Long idFranchise) {
        FranchiseFlbOseAgenceOdd franchiseFlbOseAgenceOdd2 = franchiseFlbOseAgenceOddRepository.findById(idFranchise).orElse(null);
        if(franchiseFlbOseAgenceOdd2 == null){
            return null;
        }

        Canton canton = cantonImp.getCantonById(franchiseFlbOseAgenceOdd2.getIdCanton());
        return  canton;
    }

    public Canton getCentreByFranchiseFlbOseIndicateur(Long idFranchise) {
        // check franchise Ose Cible
        FranchiseFlbOseIndicateur franchiseFlbOseIndicateur = franchiseFlbOseIndicateurRepository.findById(idFranchise).orElse(null);

        if(franchiseFlbOseIndicateur == null){
            return null;
        }
        return  this.getCentreByFranchiseFlbOseAgenceOdd(franchiseFlbOseIndicateur.getIdAgenceOdd());
    }

    public Canton getCentreByFranchiseFlbOseCible(Long idFranchise) {
        // check franchise Ose Cible
        FranchiseFlbOseCible franchiseFlbOseCible = franchiseFlbOseCibleRepository.findById(idFranchise).orElse(null);

        if(franchiseFlbOseCible == null){
            return null;
        }
        return  this.getCentreByFranchiseFlbOseAgenceOdd(franchiseFlbOseCible.getIdAgenceOdd());
    }

    public Canton getCentreByFranchiseFlbOeChambre(Long idFranchise) {
        FranchiseFlbOeChambre franchiseFlbOeChambre1 = franchiseFlbOeChambreRepository.findById(idFranchise).orElse(null);
        if (franchiseFlbOeChambre1 == null) {
            return null;
        }
        if(franchiseFlbOeChambre1.getIdCible() != null){
            return  this.getCentreByFranchiseFlbOseCible(franchiseFlbOeChambre1.getIdCible());
        }else if(franchiseFlbOeChambre1.getIdIndicateur() != null){
            return  this.getCentreByFranchiseFlbOseIndicateur(franchiseFlbOeChambre1.getIdIndicateur());
        }
        return null;


    }


    public Canton getCentreByFranchiseFillInstitution(Long idFranchise) {
        FranchiseFillInstitution franchiseFillInstitution = franchiseFillInstitutionRepository.findById(idFranchise).orElse(null);
        if (franchiseFillInstitution == null) {
            return null;
        }
        if(franchiseFillInstitution.getIdCible() != null){
            return  this.getCentreByFranchiseFlbOseCible(franchiseFillInstitution.getIdCible());
        }else if(franchiseFillInstitution.getIdIndicateur() != null){
            return  this.getCentreByFranchiseFlbOseIndicateur(franchiseFillInstitution.getIdIndicateur());
        }
        return null;
    }


    public Canton getCentreByFranchiseFlbOeChaineValeur(Long idFranchise) {
        FranchiseFlbOeChaineValeur franchiseFlbOeChaineValeur = franchiseFlbOeChaineValeurRepository.findById(idFranchise).orElse(null);
        if (franchiseFlbOeChaineValeur == null) {
            return null;
        }
        return  this.getCentreByFranchiseFlbOeChambre(franchiseFlbOeChaineValeur.getIdChambre());
    }

    public Canton getCentreByFranchiseFlbOeChaineDistribution(Long idFranchise) {
        FranchiseFlbOeChaineDistribution franchiseFlbOeChaineDistribution = franchiseFlbOeChaineDistributionRepository.findById(idFranchise).orElse(null);
        if (franchiseFlbOeChaineDistribution == null) {
            return null;
        }
        return  this.getCentreByFranchiseFlbOeChaineValeur(franchiseFlbOeChaineDistribution.getIdChaineValeur());
    }

    public Canton getCentreByFranchiseFillChaineValeur(Long idFranchise) {
        FranchiseFillChaineValeur franchiseFillChaineValeur = franchiseFillChaineValeurRepository.findById(idFranchise).orElse(null);
        if (franchiseFillChaineValeur == null) {
            return null;
        }
        return  this.getCentreByFranchiseFillInstitution(franchiseFillChaineValeur.getIdInstitution());
    }

    public Canton getCentreByFranchiseFillChaineDistribution(Long idFranchise) {
        FranchiseFillChaineDistribution franchiseFillChaineDistribution = franchiseFillChaineDistributionRepository.findById(idFranchise).orElse(null);
        if (franchiseFillChaineDistribution == null) {
            return null;
        }
        return  this.getCentreByFranchiseFlbOeChaineValeur(franchiseFillChaineDistribution.getIdChaineValeur());
    }


    public Canton achat_franchise_decoupage_centre_getcentre(int stage, Long idFranchise) {
        Canton canton ;

        switch (stage) {
            case 1:
                return this.getCentreByFranchiseFillChaineDistribution(idFranchise);
            case 2:
                return this.getCentreByFranchiseFillChaineValeur(idFranchise);
            case 3:
                return this.getCentreByFranchiseFillInstitution(idFranchise);

            case 4:
                return this.getCentreByFranchiseFlbOeChaineDistribution(idFranchise);
            case 5:
                return this.getCentreByFranchiseFlbOeChaineValeur(idFranchise);
            case 6:
                return this.getCentreByFranchiseFlbOeChambre(idFranchise);

            case 7:
                return this.getCentreByFranchiseFlbOseCible(idFranchise);
            case 8:
               return this.getCentreByFranchiseFlbOseIndicateur(idFranchise);
            case 9:
                return  this.getCentreByFranchiseFlbOseAgenceOdd(idFranchise);
            default:
                //other value
                return null;
        }

    }


    public List<FranchiseFillChaineDistribution> distributor_fill_available() {
        return franchiseFillChaineDistributionRepository.distributor_fill_available();
    }

    public List<FranchiseFlbOeChaineDistribution> distributor_flboe_available() {
        return franchiseFlbOeChaineDistributionRepository.distributor_flboe_available();
    }

    public List<FranchiseReferentiel> listFranchiseReferenciel(List<Long> ids) {
        return franchiseReferentielRepository.findByIds(ids);
    }


}
