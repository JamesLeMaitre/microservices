package com.esmc.gestionAchatFranchise.controllers;


import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.Franchise;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.services.FranchiseImp;
import com.esmc.gestionAchatFranchise.servicesinterfaces.FranchiseInt;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author Merveil
 * @since 18/05/2022
 * @apiNote all operation related to franchise table  are handle here
 */

@RestController
@RequestMapping(value="/api/franchise")
public class FranchiseController extends DataFormatter<Franchise> {
    @Autowired
    private FranchiseInt franchiseImp;

    @PostMapping("/add")
    public Object addFranchise(@RequestBody Franchise franchise) {
        return renderData(true, franchiseImp.addFranchise(franchise),"save");
    }

    // USE THIS PATH TO INSERT BY FILLING TYPEFRANCHISE AND DETAILAGR ID
    @PostMapping("/addTwo/{franchiseId}")
    public Object addTypeAndDetailOfTableFranchise(@PathVariable("franchiseId") Long franchiseId, @RequestBody Franchise franchise) throws ExceptionsHandler {
        franchiseImp.addTypeFranchiseAndDetailAgr(franchiseId,franchise);
        return new ResponseEntity<Franchise>( HttpStatus.OK);
    }

  /*  @GetMapping("/getAll")
    public Formatter<List<Franchise>> getAllFranchise(){
        return renderDataArray(true,franchiseImp.getFranchise(), "get all");
    }*/

    @GetMapping("/getAll")
    public Object List(){
        try {
            List<Franchise> items = franchiseImp.getFranchise();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("/{franchiseId}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable("franchiseId") Long franchiseId) {
        return new ResponseEntity<Franchise>(franchiseImp.getFranchiseById(franchiseId), HttpStatus.OK);
    }

//    @PutMapping("/update/{franchiseId}")
//    public Object updateFranchise(@PathVariable("franchiseId") Long franchiseId, @RequestBody FranchiseRequest request) throws Exception  {
//            Franchise franchise = franchiseImp.getFranchiseById(franchiseId);
//            if(franchise == null){
//                return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
//            }
//           return new ResponseEntity<Franchise>(franchiseImp.updateFranchise(franchiseId,request), HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{franchiseId}")
    public Object deleteFranchise(@PathVariable Long franchiseId) {
        try{
            franchiseImp.deleteFranchise(franchiseId);
            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    // USE THIS FUNCTION TO RETRIEVE typeFranchise by Franchise
    @GetMapping("/search/{id}/typeFranchise")
    public Formatter<List<Franchise>>listFranchiseParTypeFranchise(@PathVariable Long id) {
        return renderDataArray(true,franchiseImp.listTypeFranchise(id),"franchise");
    }

    // USE THIS FUNCTION TO RETRIEVE centreFranchise by Franchise
    @GetMapping("/search/{id}/centreFranchise")
    public Formatter<List<Franchise>>listFranchiseParCentreFranchise(@PathVariable Long id) {

        return renderDataArray(true,franchiseImp.listCentreFranchise(id),"list franchise");
    }

    // USE THIS FUNCTION TO RETRIEVE detailAgr by Franchise
    @GetMapping("/search/{id}/detailAgr")
    public Formatter<List<Franchise>> listFranchiseParDetailAgr(@PathVariable Long id) {

        return renderDataArray(true,franchiseImp.listDetailAgr(id),"search");
    }

    // USE THIS FUNCTION TO RETRIEVE typeFranchise and centreFranchise by Franchise
    @GetMapping("/searchList/{id}/{id2}/typeAndCentre")
    public Formatter<List<Franchise>> listFranchiseParTypeFranchiseAndCentreFranchise(@PathVariable Long id, @PathVariable Long id2) {

        return renderDataArray(true,franchiseImp.listTypeFranchiseAndAndCentre(id,id2),"searchlist");
    }

    // USE THIS FUNCTION TO RETRIEVE typeFranchise and detailAgr by Franchise
    @GetMapping("/searchList/{id}/{id2}/typeAndDetail")
    public Formatter<List<Franchise>> listFranchiseParTypeFranchiseAndDetailAgr(@PathVariable Long id, @PathVariable Long id2) {

        return renderDataArray(true,franchiseImp.listTypeFranchiseAndAndDetailAgr(id,id2),"search list");
    }

    // USE THIS FUNCTION TO RETRIEVE typeFranchise centreFranchise and detailAgr by Franchise
    @GetMapping("/searchList/{id}/{id2}/{id3}/typeCentreDetail")
    public Formatter<List<Franchise>> listFranchiseParTypeParCentreParDetail(@PathVariable Long id, @PathVariable Long id2, @PathVariable Long id3) {
        return renderDataArray(true,franchiseImp.listTypeFranchiseAndCentreFranchiseAndDetailAgr(id, id2, id3),"search list");
    }

    // USE THIS FUNCTION TO RETRIEVE typeFranchise detailAgr and ksu  by Franchise

    @GetMapping("/searchList/{id}/{id2}/{id3}/typeKsuDetail")
    public Formatter<List<Franchise>> listFranchiseParTypeParKsuParDetail(@PathVariable Long id, @PathVariable Long id2, @PathVariable Long id3) {
        return renderDataArray(true,franchiseImp.listTypeFranchiseAndKsuAndDetailAgr(id, id2, id3),"list franchise");
    }

    @GetMapping({"search/{codeId}"})
    public Formatter<String> testCode(@PathVariable String codeId){
        return renderStringData(true,franchiseImp.achatFranchiseByCode(codeId),"oui");
    }

   // @GetMapping("/{fid}")
    //public List<Franchise> getDateVerification(@PathVariable("fid") Long fid) {
      //  return franchiseImp.achatFranchiseGestionDelai(fid);
   // }

    @GetMapping({"/search/dateVerification/{franchiseId}"})
    public ResponseEntity<?> testVerificationDelai(@PathVariable Long franchiseId  ) throws ExceptionsHandler {
        try {
            return new ResponseEntity<>(  franchiseImp.achatFranchiseGestionDelai(franchiseId),HttpStatus.OK);
        }catch (ExceptionsHandler E){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

/*    @GetMapping("/search/dateVerification/{franchiseId}")
    public Object testVerificationDelai(@PathVariable Long franchiseId) {
        try {

            return  renderData(true, franchiseImp.achatFranchiseGestionDelai(franchiseId),"disable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }*/

    @PutMapping(value = "update/{id}")
    public Object updateFranchise(@RequestBody Franchise fr, @PathVariable Long id) {
        fr.setId(id);
        return renderData(true,franchiseImp.updateFranchise(fr),"franchise update");
    }




}
