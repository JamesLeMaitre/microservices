package com.esmc.gestionMembre.controllers;


import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.serviceInterfaces.*;
import com.esmc.gestionMembre.serviceInterfaces.recherche.AncienMembreServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.recherche.MembreServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.recherche.MoraleServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.recherche.PhysiqueServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;
import com.esmc.gestionMembre.input.DataInput;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import static com.esmc.gestionMembre.utils.Constants.*;

@RestController
@RequestMapping(path = "api/anciens/")
public class ExistenceMembreController extends DataFormatter<Object> {

    @Autowired
    private ExistenceMembreServiceInterface existenceMembreServiceinterface;


    @Autowired
    private AncienMembreServiceInterface ancienMembreServiceInterface;

    @Autowired
    private MembreMoraleInterface membreMoraleInterface;


    @Autowired
    private MembreServiceInterface membreServiceInterface;


    @Autowired
    private MoraleServiceInterface moraleServiceInterface;


    @Autowired
    private PhysiqueServiceInterface physiqueServiceInterface;


    @GetMapping(value = "systeme/{systeme}/typeCompte/{id}/code/{code}")
    public Object getMembre(@PathVariable String systeme , @PathVariable String id, @PathVariable String code) {

        switch (systeme.toLowerCase()) {
            case MCNPVALUE:
                if (id.equals(MCNPTABLEPHISIQUE)) {
                    return renderData(true, existenceMembreServiceinterface.MCNPancienMembre(code),"");
                } else if (id.equals(MCNPTABLEMORALE)) {
                    return renderData(true,existenceMembreServiceinterface.MCNPancienMembre(code),"");
                }

                break;
            case ESMCSARLUVALUE:
                if (id.equals(ESMCSARLUTABLEPHYSIQUE)) {
                   /* System.out.println("==================DATA===================");
                    System.out.println(existenceMembreServiceinterface.ESMCSARLUmembreFondateurMembre(code));*/
                    return renderData(true,existenceMembreServiceinterface.ESMCSARLUmembreFondateurMembre(code),"");
                } else if (id.equals(ESMCSARLUTABLEMORALE)) {

                   /* System.out.println("=================================================================================");
                    System.out.println("CM : "+systeme);
                    System.out.println("=================================================================================");

                    System.out.println("=================================================================================");
                    System.out.println("Personne : "+id);
                    System.out.println("=================================================================================");

                    System.out.println("=================================================================================");
                    System.out.println("Code M : "+code);
                    System.out.println("=================================================================================");*/

                    MembreMorale item = existenceMembreServiceinterface.ESMCSARLUmembreFondateurMembreMorale(code);

                  /*  System.out.println("=== ,==============================================================================");
                    System.out.println(item);
                    System.out.println("=================================================================================");*/

                    return renderData(true,item,"");
                }

                break;
            case REDEMARREVALUE:
                if (id.equals(REDEMARRETABLEMORALE)) {
                    return renderData(true,existenceMembreServiceinterface.RedemarrepersonneMorale(code),"");
                } else if (id.equals(REDEMARRETABLEPHISIQUE)) {
                    return renderData(true,existenceMembreServiceinterface.RedemarrepersonnePhysique(code),"");
                }

                break;
        }
        return renderStringData(false,"no case match","try again");
    }

    /**
     * @author JamesLeMaitre
     *
     * @param systeme
     * @param id
     * @param offset
     * @param pageSize
     * @return
     */
    @GetMapping(value = "systeme/{systeme}/typeCompte/{id}/{offset}/{pageSize}")
    public Object getMembreList(@PathVariable String systeme , @PathVariable String id, @PathVariable int offset, @PathVariable int pageSize) {

        switch (systeme.toLowerCase()) {
            case MCNPVALUE:
                // MCNPTABLEPHISIQUE = 1
                if (id.equals(MCNPTABLEPHISIQUE)) {

                    DataFormatter<Page<AncienMembre>> formatter = new DataFormatter<>();

                    //List<AncienMembre> items = existenceMembreServiceinterface.listAncienMembrePP();
                    List<Page<AncienMembre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListAncienMembrePP(offset, pageSize));

                    return formatter.renderDataArray(true, items,"");
                    //  MCNPTABLEMORALE = 3
                } else if (id.equals(MCNPTABLEMORALE)) {

                    DataFormatter<Page<AncienMembre>> formatter = new DataFormatter<>();

                    //List<AncienMembre> items = existenceMembreServiceinterface.listAncienMembrePM();

                    List<Page<AncienMembre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListAncienMembrePm(offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                }

                break;
            case ESMCSARLUVALUE:
                if (id.equals(ESMCSARLUTABLEPHYSIQUE)) {

                    DataFormatter<Page<Membre>> formatter = new DataFormatter<>();

                    //List<Membre> items = existenceMembreServiceinterface.listMembre();
                    List<Page<Membre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMembre(offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                } else if (id.equals(ESMCSARLUTABLEMORALE)) {

                    DataFormatter<Page<MembreMorale>> formatter = new DataFormatter<>();

                    //List<MembreMorale> items = existenceMembreServiceinterface.listMembreMorale();
                    List<Page<MembreMorale>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMembreMorale(offset, pageSize));

                    return formatter.renderDataArray(true, items,"");
                }

                break;
            case REDEMARREVALUE:
                if (id.equals(REDEMARRETABLEMORALE)) {

                    DataFormatter<Page<Morale>> formatter = new DataFormatter<>();

                    //List<Morale> items = existenceMembreServiceinterface.listMorale();
                    List<Page<Morale>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMorale(offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                } else if (id.equals(REDEMARRETABLEPHISIQUE)) {

                    DataFormatter<Page<Physique>> formatter = new DataFormatter<>();

                    List<Page<Physique>> items = Collections.singletonList(existenceMembreServiceinterface.pageListPhysique(offset, pageSize));

                    //List<Physique> items = existenceMembreServiceinterface.listPhysique();

                    return formatter.renderDataArray(true,items,"");
                }

                break;
        }
        return renderStringData(false,"no case match","try again");
    }

    /**
     * @author JamesLeMaitre
     *
     * @param data
     * @return
     */
    @PostMapping(value = "systeme/typeCompte/searchWords")
    public Object getMembreListv2(@RequestBody() DataInput data) {

        switch (data.getSysteme().toLowerCase()) {
            case MCNPVALUE:
                // MCNPTABLEPHISIQUE = 1
                if (data.getId().equals(MCNPTABLEPHISIQUE)) {

                    DataFormatter<AncienMembre> formatter = new DataFormatter<>();

                    List<AncienMembre> items = existenceMembreServiceinterface.listAncienMembrePP(data.getSearchWords());
                    //List<Page<AncienMembre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListAncienMembrePP(offset, pageSize));

                    return formatter.renderDataArray(true, items,"list of element");
                    //  MCNPTABLEMORALE = 3
                } else if (data.getId().equals(MCNPTABLEMORALE)) {

                    DataFormatter<AncienMembre> formatter = new DataFormatter<>();

                    List<AncienMembre> items = existenceMembreServiceinterface.listAncienMembrePM(data.getSearchWords());

                    //List<Page<AncienMembre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListAncienMembrePm(offset, pageSize));

                    return formatter.renderDataArray(true,items,"List of element");
                }

                break;
            case ESMCSARLUVALUE:
                if (data.getId().equals(ESMCSARLUTABLEPHYSIQUE)) {

                    DataFormatter<Membre> formatter = new DataFormatter<>();

                    List<Membre> items = existenceMembreServiceinterface.listMembre(data.getSearchWords());
                    //List<Page<Membre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMembre(offset, pageSize));

                    return formatter.renderDataArray(true,items,"List of element");
                } else if (data.getId().equals(ESMCSARLUTABLEMORALE)) {

                    DataFormatter<MembreMorale> formatter = new DataFormatter<>();

                    List<MembreMorale> items = existenceMembreServiceinterface.listMembreMorale(data.getSearchWords());
                    ///List<Page<MembreMorale>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMembreMorale(offset, pageSize));

                    return formatter.renderDataArray(true, items,"");
                }

                break;
            case REDEMARREVALUE:
                if (data.getId().equals(REDEMARRETABLEMORALE)) {

                    DataFormatter<Morale> formatter = new DataFormatter<>();

                    List<Morale> items = existenceMembreServiceinterface.listMorale(data.getSearchWords());
                    //List<Page<Morale>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMorale(offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                } else if (data.getId().equals(REDEMARRETABLEPHISIQUE)) {

                    DataFormatter<Physique> formatter = new DataFormatter<>();

                    //List<Page<Physique>> items = Collections.singletonList(existenceMembreServiceinterface.pageListPhysique(offset, pageSize));

                    List<Physique> items = existenceMembreServiceinterface.listPhysique(data.getSearchWords());

                    return formatter.renderDataArray(true,items,"");
                }

                break;
        }
        return renderStringData(false,"no case match","try again");
    }

/*    @GetMapping(value = "list/mcnp/pp")
    public List<AncienMembre> listAncienMembrePP() {
        return existenceMembreServiceinterface.listAncienMembrePP();
    }*/

    @GetMapping("list/mcnp/pp/{offset}/{pageSize}")
    public Object listAncienMembreP(@PathVariable int offset, @PathVariable int pageSize){
        DataFormatter<Page<AncienMembre>> dp =new DataFormatter<>();
        try {
            List<Page<AncienMembre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListAncienMembrePP(offset, pageSize));
            return  dp.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

/*    @GetMapping(value = "list/mcnp/pm")
    public List<AncienMembre> listAncienMembrePM() {
        return existenceMembreServiceinterface.listAncienMembrePM();

    }*/

    @GetMapping("list/mcnp/pm/{offset}/{pageSize}")
    public Object listAncienMembrePM(@PathVariable int offset, @PathVariable int pageSize){
        DataFormatter<Page<AncienMembre>> dp =new DataFormatter<>();
         try {
            List<Page<AncienMembre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListAncienMembrePm(offset, pageSize));
            return  dp.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list/redemare/pp/{offset}/{pageSize}")
    public Object listPhysique(@PathVariable int offset, @PathVariable int pageSize){
        DataFormatter<Page<Physique>> dp =new DataFormatter<>();
        try {
            List<Page<Physique>> items = Collections.singletonList(existenceMembreServiceinterface.pageListPhysique(offset, pageSize));
            return  dp.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

/*
   @GetMapping(value = "list/redemare/pp")
    public List<Physique> listPhysique() {
        return existenceMembreServiceinterface.listPhysique();
    }
*/

    @GetMapping("list/redemare/pm/{offset}/{pageSize}")
    public Object listMembre(@PathVariable int offset, @PathVariable int pageSize){
        DataFormatter<Page<Morale>> dp =new DataFormatter<>();
        try {
            List<Page<Morale>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMorale(offset, pageSize));
            return  dp.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

/*  @GetMapping(value = "list/redemare/pm")
    public List<Morale> listMorale() {
        return existenceMembreServiceinterface.listMorale();
    }

  @GetMapping(value = "list/esmc_sarlu/pp")
    public List<Membre> listMembre() {
        return existenceMembreServiceinterface.listMembre();
    }*/

    @GetMapping("list/esmc_sarlu/pp/{offset}/{pageSize}")
    public Object listMorale(@PathVariable int offset, @PathVariable int pageSize){
        DataFormatter<Page<Membre>> dp =new DataFormatter<>();
        try {
            List<Page<Membre>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMembre(offset, pageSize));
            return  dp.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list/esmc_sarlu/pm/{offset}/{pageSize}")
    public Object listMembreMorale(@PathVariable int offset, @PathVariable int pageSize){
        DataFormatter<Page<MembreMorale>> dp =new DataFormatter<>();
        try {
            List<Page<MembreMorale>> items = Collections.singletonList(existenceMembreServiceinterface.pageListMembreMorale(offset, pageSize));
            return  dp.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

/*  @GetMapping(value = "list/esmc_sarlu/pm")
    public List<MembreMorale> listMembreMorale() {
        return existenceMembreServiceinterface.listMembreMorale();
    }*/

    /**
     * @author Amemorte99
     *
     * @param systeme
     * @param id
     * @param offset
     * @param pageSize
     * @param search
     * @return
     */


    @GetMapping(value = "systeme/{systeme}/typeCompte/{id}/{offset}/{pageSize}/search/{search}")
    public Object getMembreListSearch(@PathVariable String systeme , @PathVariable String id, @PathVariable int offset, @PathVariable int pageSize,String search) {

        switch (systeme.toLowerCase()) {
            case MCNPVALUE:
                // MCNPTABLEPHISIQUE = 1
                if (id.equals(MCNPTABLEPHISIQUE)) {

                    DataFormatter<Page<AncienMembre>> formatter = new DataFormatter<>();

                    //List<AncienMembre> items = existenceMembreServiceinterface.listAncienMembrePP();
                    List<Page<AncienMembre>> items = Collections.singletonList(ancienMembreServiceInterface.pageListAncienMembrePPSearch(search,offset, pageSize));

                    return formatter.renderDataArray(true, items,"");
                    //  MCNPTABLEMORALE = 3
                } else if (id.equals(MCNPTABLEMORALE)) {

                    DataFormatter<Page<AncienMembre>> formatter = new DataFormatter<>();

                    //List<AncienMembre> items = existenceMembreServiceinterface.listAncienMembrePM();

                    List<Page<AncienMembre>> items = Collections.singletonList(ancienMembreServiceInterface.pageListAncienMembrePMSearch(search,offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                }

                break;
            case ESMCSARLUVALUE:
                if (id.equals(ESMCSARLUTABLEPHYSIQUE)) {

                    DataFormatter<Page<Membre>> formatter = new DataFormatter<>();

                    //List<Membre> items = existenceMembreServiceinterface.listMembre();
                    List<Page<Membre>> items = Collections.singletonList(membreServiceInterface.pageListMembrePPSearchByAttributeNom(search,offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                } else if (id.equals(ESMCSARLUTABLEMORALE)) {

                    DataFormatter<Page<MembreMorale>> formatter = new DataFormatter<>();

                    //List<MembreMorale> items = existenceMembreServiceinterface.listMembreMorale();
                    List<Page<MembreMorale>> items = Collections.singletonList(membreMoraleInterface.pageListMembreMoraleByAttributeDateLieuNaiss(search,offset, pageSize));

                    return formatter.renderDataArray(true, items,"");
                }

                break;
            case REDEMARREVALUE:
                if (id.equals(REDEMARRETABLEMORALE)) {

                    DataFormatter<Page<Morale>> formatter = new DataFormatter<>();

                    //List<Morale> items = existenceMembreServiceinterface.listMorale();
                    List<Page<Morale>> items = Collections.singletonList(moraleServiceInterface.pageListMoraleSearch(search,offset, pageSize));

                    return formatter.renderDataArray(true,items,"");
                } else if (id.equals(REDEMARRETABLEPHISIQUE)) {

                    DataFormatter<Page<Physique>> formatter = new DataFormatter<>();

                    List<Page<Physique>> items = Collections.singletonList(physiqueServiceInterface.pageListSearchPhysiqueByDateLieu(search,offset, pageSize));

                    //List<Physique> items = existenceMembreServiceinterface.listPhysique();

                    return formatter.renderDataArray(true,items,"");
                }

                break;
        }
        return renderStringData(false,"no case match","try again");
    }

    


}
