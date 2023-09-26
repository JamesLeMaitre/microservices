package com.esmc.gestionKsu.controllers;

import com.esmc.gestionKsu.entities.Ksu;
import com.esmc.gestionKsu.feign.AgrRestClient;
import com.esmc.gestionKsu.feign.AvrRestClient;
import com.esmc.gestionKsu.feign.FranchiseRestClient;
import com.esmc.gestionKsu.feign.MaBanKsuRestClient;
import com.esmc.gestionKsu.inputs.NetworkActivationInput;
import com.esmc.gestionKsu.serviceinterfaces.KsuServiceInterface;
import com.esmc.input.KsuCheckInput;
import com.esmc.models.BanKsu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * {@link Ksu} controller
 * @author katoh <katohdavid@gmail.com>
 */
@RestController
@RequestMapping(value = "api/ksus/")
public class KsuController extends DataFormatter<Ksu> {

    @Autowired
    private KsuServiceInterface ksuService;

    @Autowired
    private MaBanKsuRestClient maBanKsuRestClient;

    @Autowired
    private AgrRestClient agrRestClient;

    @Autowired
    private AvrRestClient avrRestClient;

    @Autowired
    private FranchiseRestClient franchiseRestClient;

    @GetMapping("codeksu/{code}")
    public Object ksuPar(@PathVariable("code") String code) {
        Ksu tmpKsu = ksuService.ksuParCodeKsu(code);
        if(tmpKsu == null){
            return renderData(false,null,"Ksu inexistant");
        }else {
            return renderData(true,tmpKsu,"Ksu trouve");
        }
    }

    @PutMapping(value = "updateKsuReference/{id}")
    public Object update(@PathVariable Long id, @RequestBody Ksu data) {
        try {
            return  renderData(true, ksuService.updateKSU(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @PostMapping("getBySpecificInfo")
    public Object getBySpecificInfo(@RequestBody KsuCheckInput ksuCheckInput){
        Ksu tmpKsu = ksuService.ksuByInfo(ksuCheckInput.getFirstName().toUpperCase(),ksuCheckInput.getLastName().toUpperCase(),ksuCheckInput.getNumero());

        if (tmpKsu == null) {
          return renderData(true, ksuService.ksuByInfoPM(ksuCheckInput.getRaisonSocial().toUpperCase(), ksuCheckInput.getNumero()), "Opération éffectuer avec succès");
        }
        return  renderData(true, tmpKsu, "Opération éffectuer avec succès") ;
    }


    @GetMapping("mabanksu/{id}")
    public Ksu ksuParMaBanKsu(@PathVariable("id") Long id) {
        return ksuService.ksuByMaBanKsu(id);
    }

    @GetMapping("list")
    public PagedModel<EntityModel<Ksu>> getAll(Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getAll(page,pagedResourcesAssembler);
    }

    @GetMapping("getByAbonnement/{id}")
    public PagedModel<EntityModel<Ksu>> getByAbonnement(@PathVariable Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getByAbonnement(id,page,pagedResourcesAssembler);
    }

    @GetMapping("getByPieceIdentite/{id}")
    public PagedModel<EntityModel<Ksu>> getByPieceIdentite(@PathVariable Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getByPieceIdentite(id,page,pagedResourcesAssembler);
    }

    @GetMapping("getByTypePM/{id}")
    public PagedModel<EntityModel<Ksu>> getByTypePM(@PathVariable Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getByTypePM(id,page,pagedResourcesAssembler);
    }

    @GetMapping("getByTypeOperateur/{id}")
    public PagedModel<EntityModel<Ksu>> getByTypeOperateur(@PathVariable Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getByTypeOperateur(id,page,pagedResourcesAssembler);
    }

    @GetMapping("getByBanque/{id}")
    public PagedModel<EntityModel<Ksu>> getByBanque(@PathVariable Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getByBanque(id,page,pagedResourcesAssembler);
    }

    @GetMapping("getBySecteur/{id}")
    public PagedModel<EntityModel<Ksu>> getBySecteur(@PathVariable Long id, Pageable page, PagedResourcesAssembler<Ksu> pagedResourcesAssembler) {
        return ksuService.getBySecteur(id,page,pagedResourcesAssembler);
    }


    @GetMapping("current")
    public Ksu getCurrentKsuById() {
        return ksuService.currentKsu();
    }

/*    @PostMapping("typeBanMaKsu/{idtype}/{idMaBanKsu}/save")
    public Ksu ajouterKsu(@PathVariable  Long idtype, @PathVariable Long idMaBanKsu, @RequestBody Ksu k) throws Exception {
        return ksuService.addKsu(idtype, idMaBanKsu, k);
    }*/

//    @PostMapping("sendSMS/{num}")
//    public Object sendMessage(@PathVariable String num, @RequestBody String mesg){
//        return ksuService.sendMessage(num,mesg);
//    }
    @PostMapping("typeBanMaKsu/{idtype}/{idMaBanKsu}/save")
    public Object create(@PathVariable  Long idtype, @PathVariable Long idMaBanKsu, @RequestBody Ksu k){
//        try {

          BanKsu b = maBanKsuRestClient.getBanKsuByCodeBan(k.getCodeBanKsu());
          if (b == null){
              return renderStringData(false,"","Code BAn non valide");
          }
            Ksu item =  ksuService.addKsu(idtype, idMaBanKsu, k);
            if (item == null) {
                return renderStringData(false, "", "Ce Code BAn est déjà utilisé");
            }

            return  renderData(true, item,"Operation éffectuer avec succès");
//        } catch (Exception e) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  renderStringData(false,"Error while processing" ,"Erreur lors de la création du KSU");
//        }
    }



    @GetMapping("getKsu/byActivationCode/{code}")
    public Object getbyActivationCode(@PathVariable String code) {
        try {
            Ksu ksu = ksuService.getKsuByActivationCode(code);
            //String finger = ksu.getTempFingerPrintKey();
            //ksuService.clearTempFingerPrintKey(ksu.getId());
            if(ksu == null){
                return renderStringData(false,"Error while processing", "Ksu not found");
            }
            //ksu.setTempFingerPrintKey(finger);
            return renderData(true, ksu, "Ksu network activate successfully for some minutes");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(), "Ksu not found");
        }
    }

    @PostMapping("activate/network")
    public Object activateNetwork( @RequestBody NetworkActivationInput networkActivationInput)  {
        try {
        Ksu ksu = ksuService.activateNetwork(networkActivationInput);
        if(ksu == null){
            return renderStringData(false,"Error while processing", "Ksu not found");
        }
        return renderStringData(true, ksu.getCodeConnexion(), "Ksu network activate successfully for some minutes");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(), "Ksu not found");
        }
    }
    @GetMapping("getById/{id}")
    public Ksu getById(@PathVariable Long id){
        return ksuService.getById(id);
    }

    @GetMapping("code_representant/{code}")
    public Ksu getKsuByCodeRepresentant(@PathVariable("code") String code){
        return ksuService.getKsuByCodeRepresentant(code);
    }
    @GetMapping("list/{id}")
    public List<Ksu> listKsuById(@PathVariable Long id){
        return ksuService.listKsuById(id);
    }


}
