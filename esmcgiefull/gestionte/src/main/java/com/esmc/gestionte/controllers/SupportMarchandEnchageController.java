package com.esmc.gestionte.controllers;



import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.serviceinterface.SupportMarchandEnchageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */


@RestController
@RequestMapping(value = "api/supportMarchandEnchage/")
public class SupportMarchandEnchageController extends DataFormatter<SupportMarchandEnchage> {

    @Autowired
    private SupportMarchandEnchageService supportMarchandEnchageService;


    @GetMapping("getAll")
    public List<SupportMarchandEnchage> getSupportMarchandEnchage(){
        return  supportMarchandEnchageService.getSupportMarchandEnchage();
    }

    @PostMapping("save")
    public SupportMarchandEnchage addNewSupportMarchandEnchage(@RequestBody SupportMarchandEnchage supportMarchandEnchage){
          return  supportMarchandEnchageService.addNewSupportMarchandEnchage(supportMarchandEnchage);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateSupportMarchandEnchage(@PathVariable("id") Long id,@RequestBody SupportMarchandEnchage supportMarchandEnchage ){
        try {
            supportMarchandEnchageService.updateSupportMarchandEnchage(id,supportMarchandEnchage);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteSupportMarchandEnchage(@PathVariable("id") Long id){
            supportMarchandEnchageService.deleteSupportMarchandEnchage(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            supportMarchandEnchageService.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("findById/{id}")
    public SupportMarchandEnchage supportMarchandEnchageParId(@PathVariable Long id) {
        return supportMarchandEnchageService.getSupportMarchandById(id);
    }

    @GetMapping("findByLibelle/{libelle}")
    public SupportMarchandEnchage supportMarchandEnchageParLibelle(@PathVariable("libelle") String libelle) {
        return supportMarchandEnchageService.getSupportMarchandEchangeByLibelle(libelle);
    }


    @GetMapping("listCircuitBan")
    public Object listCircuitBan() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBan();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCr")
    public Object listCircuitBCr() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCr();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    @GetMapping("listCircuitBCnr")
    public Object listCircuitBCnr() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCnr();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("circuitMPRgBAn")
    public Object circuitMPRgBAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.circuitMPRgBAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationSMCI_Services_Interim")
    public Object sourceMutationSMCI_Services_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationSMCI_Services_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    @GetMapping("listCircuitBLGCp")
    public Object listCircuitBLGCp() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBLGCp();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBLGCsc")
    public Object listCircuitBLGCsc() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBLGCsc();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitSMCIPN")
    public Object listCircuitSMCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitSMCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitTSMCIPNBudget")
    public Object listCircuitTSMCIPNBudget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitTSMCIPNBudget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitTSMCIPNBudgetAnticipe")
    public Object listCircuitTSMCIPNBudgetAnticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitTSMCIPNBudgetAnticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitTSMCIPNServiceInterim")
    public Object listCircuitTSMCIPNServiceInterim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitTSMCIPNServiceInterim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitTSMCIPNAutre")
    public Object listCircuitTSMCIPNAutre() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitTSMCIPNAutre();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBanOpi")
    public Object listCircuitBanOpi() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBanOpi();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }




    @GetMapping("listCircuitOpi")
    public Object listCircuitOpi() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitOpi();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMABAn")
    public Object sourceMABAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMABAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAn")
    public Object sourceBAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAnOpi")
    public Object sourceBAnOpi() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAnOpi();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBLGCp")
    public Object sourceBLGCp() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBLGCp();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBLGCsc")
    public Object sourceBLGCsc() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBLGCsc();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listsm_franchise")
    public Object listsm_franchise() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listSmFranchise();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    /********** JAMES LE MAITRE
     *
     *      31/10/2022
     *
     */


    @GetMapping("sourceBAi_SMCI_Budget")
    public Object List() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMC_PN_Budget")
    public Object ListsourceBAi_SMC_PN_Budget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMC_PN_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMCI_Budget_Anticipe")
    public Object ListsourceBAi_SMCI_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMC_PN_Budget_Anticipe")
    public Object ListsourceBAi_SMC_PN_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMC_PN_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMCI_Service_Interim")
    public Object ListsourceBAi_SMCI_Service_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_Service_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMC_PN_Service_Interim")
    public Object ListsourceBAi_SMC_PN_Service_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMC_PN_Service_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMCI_PN_Service_Interim")
    public Object ListsourceBAi_SMCI_PN_Service_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_PN_Service_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMCI_Autres")
    public Object ListsourceBAi_SMCI_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMC_PN_Autres")
    public Object ListsourceBAi_SMC_PN_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMC_PN_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMCI_PN_Autres")
    public Object ListsourceBAi_SMCI_PN_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_PN_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    /******
     * *
     *
     */

    @GetMapping("listBCi_SMCI_Budget")
    public Object sourceBCi_SMCI_Budget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMC_PN_Budget")
    public Object listBCi_SMC_PN_Budget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMC_PN_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMCI_Budget_Anticipe")
    public Object listBCi_SMCI_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMC_PN_Budget_Anticipe")
    public Object listBCi_SMC_PN_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMC_PN_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMCI_Service_Interim")
    public Object listBCi_SMCI_Service_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_Service_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMC_PN_Service_Interim")
    public Object listBCi_SMC_PN_Service_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMC_PN_Service_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMCI_PN_Service_Interim")
    public Object listBCi_SMCI_PN_Service_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_PN_Service_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMCI_Autres")
    public Object listBCi_SMCI_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMC_PN_Autres")
    public Object listBCi_SMC_PN_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMC_PN_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMCI_PN_Autres")
    public Object listBCi_SMCI_PN_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_PN_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAiOPi")
    public Object sourceBAiOPi() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAiOpi();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCiBAiOPI")
    public Object listBCiBAiOPI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCiBAiOPI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBLGCp")
    public Object listBLGCp() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBLGCp();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBLGCsc")
    public Object sourceMutationBLGCsc() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBLGCsc();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listOPI")
    public Object listOPI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listOPI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    @GetMapping("listMPRg_BPSDEVpPN_BAn")
    public Object listMPRg_BPSDEVpPN_BAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg_BPSDEVpPN_BAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBPSDEVpPN_BPSDEVpIPN")
    public Object  sourceBPSDEVpPN_BPSDEVpIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDEVpPN_BPSDEVpIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN")
    public Object  listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVpIPN_BPSDEpIPN_BPSDVpIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDEpIPN_BPSDEpPN")
    public Object  sourceMutationBPSDEpIPN_BPSDEpPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDEpIPN_BPSDEpPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDVpIPN_BPSDVpPN")
    public Object  sourceMutationBPSDVpIPN_BPSDVpPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDVpIPN_BPSDVpPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRg_BPSDEVpI_BAn")
    public Object  listMPRg_BPSDEVpI_BAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg_BPSDEVpI_BAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDEVpI_BPSDEVpIPN")
    public Object  sourceMutationBPSDEVpI_BPSDEVpIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDEVpI_BPSDEVpIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDEpIPN_BPSDEpI")
    public Object  sourceMutationBPSDEpIPN_BPSDEpI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDEpIPN_BPSDEpI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDVpIPN_BPSDVpI")
    public Object  sourceMutationBPSDVpIPN_BPSDVpI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDVpIPN_BPSDVpI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRg_BPSDEVCPN_BAn")
    public Object  listMPRg_BPSDEVCPN_BAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg_BPSDEVCPN_BAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDEVCPN_BPSDVpI")
    public Object  sourceMutationBPSDEVCPN_BPSDVpI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDEVCPN_BPSDVpI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN")
    public Object  listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVCIPN_BPSDECIPN_BPSDVCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }



    @GetMapping("sourceMutationBPSDVCIPN_BPSDVCPN")
    public Object  sourceMutationBPSDVCIPN_BPSDVCPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDVCIPN_BPSDVCPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRg_BPSDEVCI_BAn")
    public Object  listMPRg_BPSDEVCI_BAn() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg_BPSDEVCI_BAn();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDEVCI_BPSDEVCIPN")
    public Object  sourceMutationBPSDEVCI_BPSDEVCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDEVCI_BPSDEVCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDECIPN_BPSDECI")
    public Object  sourceMutationBPSDECIPN_BPSDECI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDECIPN_BPSDECI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceMutationBPSDVCIPN_BPSDVCIPN")
    public Object  sourceMutationBPSDVCIPN_BPSDVCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceMutationBPSDVCIPN_BPSDVCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCr_jour")
    public Object  listCircuitBCr_jour() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCr_jour();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCr_limite_11_2")
    public Object  listCircuitBCr_limite_11_2() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCr_limite_11_2();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCriRMSBCr22_4")
    public Object  listCircuitBCriRMSBCr22_4() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCriRMSBCr22_4();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCr_illimite_22_4")
    public Object  listCircuitBCr_illimite_22_4() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCriRMSBCr22_4();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCnr5_6_PRK_6_5")
    public Object  listCircuitBCnr5_6_PRK_6_5() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCnr5_6_PRK_6_5();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCnrPRK_6_5_8")
    public Object  listCircuitBCnrPRK_6_5_8() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCnrPRK_6_5_8();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCnrIMM_PRK8_PRK_9")
    public Object  listBCnrIMM_PRK8_PRK_9() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCnrIMM_PRK8_PRK_9();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCnrPREIMM11_2_PRK_22_4")
    public Object  listBCnrPREIMM11_2_PRK_22_4() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCnrPREIMM11_2_PRK_22_4();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCnrPRE_9_22_4")
    public Object  listBCnrPRE_9_22_4() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCnrPRE_9_22_4();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMCI_Budget")
    public Object  sourceSMCI_Budget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMCI_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMC_PN_Budget")
    public Object  sourceSMC_PN_Budget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMC_PN_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBCi_SMCPN_Budget")
    public Object  sourceBCi_SMCPN_Budget() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBCi_SMCPN_Budget();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMCI_Budget_Anticipe")
    public Object  sourceSMCI_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMCI_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMC_PN_Budget_Anticipe")
    public Object  sourceSMC_PN_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMC_PN_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBCi_SMCPN_Budget_Anticipe")
    public Object  sourceBCi_SMCPN_Budget_Anticipe() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBCi_SMCPN_Budget_Anticipe();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    /**********NEW****************/

    @GetMapping("listBPSDVCIPN_BPSDVCI")
    public Object  listBPSDVCIPN_BPSDVCI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDVCIPN_BPSDVCI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDECIPN_BPSDECI")
    public Object  listBPSDECIPN_BPSDECI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDECIPN_BPSDECI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDEVCIPN_BPSDEVCI")
    public Object  listBPSDEVCIPN_BPSDEVCI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVCIPN_BPSDEVCI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDEVCIPN_BPSDEVCPN")
    public Object  listBPSDEVCIPN_BPSDEVCPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVCIPN_BPSDEVCPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDEVCIPN_BPSDVCIPN")
    public Object  listBPSDEVCIPN_BPSDVCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVCIPN_BPSDVCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDVCIPN_BPSDECIPN")
    public Object  listBPSDVCIPN_BPSDECIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDVCIPN_BPSDECIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDVCIPN_BPSDVCPN")
    public Object  listBPSDVCIPN_BPSDVCPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDVCIPN_BPSDVCPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }




    @GetMapping("listBPSDEVCPN_BPSDEVCIPN")
    public Object  listBPSDEVCPN_BPSDEVCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVCPN_BPSDEVCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBPSDEVCI_BPSDEVCIPN")
    public Object  listBPSDEVCI_BPSDEVCIPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDEVCI_BPSDEVCIPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRg_BPSDEVCPN")
    public Object  listMPRg_BPSDEVCPN() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg_BPSDEVCPN();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRg_BPSDEVCI")
    public Object  listMPRg_BPSDEVCI() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg_BPSDEVCI();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMC_PN_Services_Interim")
    public Object  sourceSMC_PN_Services_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMC_PN_Services_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMCIPN_Services_Interim")
    public Object  sourceSMCIPN_Services_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMCIPN_Services_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBAi_SMCI_Services_Interim")
    public Object  sourceBAi_SMCI_Services_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBAi_SMCI_Services_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMCI_Autres")
    public Object  sourceSMCI_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMCI_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMC_PN_Autres")
    public Object  sourceSMC_PN_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMC_PN_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listBCi_SMCI_Services_Interim")
    public Object  listBCi_SMCI_Services_Interim() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBCi_SMCI_Services_Interim();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceSMCIPN_Autres")
    public Object  sourceSMCIPN_Autres() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceSMCIPN_Autres();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBC")
    public Object  listCircuitBC() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBC();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitBCi_BLGCp")
    public Object  listCircuitBCi_BLGCp() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitBCi_BLGCp();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBPSDE")
    public Object  sourceBPSDE() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBPSDE();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBPSDV")
    public Object  sourceBPSDV() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBPSDV();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceBPSDEV")
    public Object  sourceBPSDEV() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceBPSDEV();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitMPRgBAn_OPI_non_echus")
    public Object  listCircuitMPRgBAn_OPI_non_echus() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitMPRgBAn_OPI_non_echus();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listCircuitDecaissment")
    public Object  listCircuitDecaissment() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitDecaissment();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    @GetMapping("listCircuitDecaissementByID/sm/{id}")
    public Object  listCircuitDecaissementByID(@PathVariable Long id) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitDecaissementByID(id);
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("fond_propre/agr/{idAgr}")
    public Object listDecaissemntFondsPropre(@PathVariable Long idAgr) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listDecaissementFondPropre(idAgr);

            if (items.isEmpty()) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, items, "Operation successifully");
        } catch (Exception e) {
            return "Error processing "+e;
        }
    }

    @GetMapping("fond_commun/agr/{idAgr}")
    public Object listDecaissemntFondsCommun(@PathVariable Long idAgr) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listDecaissementFondCommun(idAgr);

            if (items.isEmpty()) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, items, "Operation successifully");
        } catch (Exception e) {
            return "Error processing "+e;
        }
    }

    @GetMapping("listCircuitDecaissementValeurPropre/idAgr/{idAgr}/idSm/{idSm}")
    public Object listCircuitDecaissementValeurPropre(@PathVariable Long idAgr,@PathVariable Long idSm) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitDecaissementValeurPropre(idAgr,idSm);

            if (items.isEmpty()) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, items, "Operation successfully");
        } catch (Exception e) {
            return "Error processing "+e;
        }
    }

    @GetMapping("listCircuitDecaissementValeurCommun/idAgr/{idAgr}/idSm/{idSm}")
    public Object listCircuitDecaissementValeurCommun(@PathVariable Long idAgr,@PathVariable Long idSm) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listCircuitDecaissementValeurCommun(idAgr,idSm);

            if (items.isEmpty()) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, items, "Operation successfully");
        } catch (Exception e) {
            return "Error processing "+e;
        }
    }

    @GetMapping("decaissement/list/type/{type}/idAgr/{idAgr}/idSm/{idSm}")
    public Object mutationDecaissementList(@PathVariable Long type, @PathVariable Long idAgr,@PathVariable Long idSm) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.muttationDecaissementList(idAgr, idSm, type);

            if (items.isEmpty()) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, items, "Operation successfully");
        } catch (Exception e) {
            return "Error processing "+e;
        }
    }

    @GetMapping("decaissement_sm/idSm/{idSm}/idAgr/{idAgr}/type/{type}")
    public Object mutationDecaissementSm(@PathVariable Long type, @PathVariable Long idAgr,@PathVariable Long idSm) {
        try {
         SupportMarchandEnchage items = supportMarchandEnchageService.decaissementSM(idSm, idAgr, type);

            if (items == null) {
                return renderStringData(false, "", "Not found");
            }
            return renderData(true, items, "Operation successfully");
        } catch (Exception e) {
            return "Error processing "+e;
        }
    }

    /***** MPRG Mutation && No mutation ******/

    @GetMapping("listMPRGBAn_No_Mutation")
    public Object  listMPRGBAn_No_Mutation() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRGBAn_No_Mutation();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRGOPI_No_Mutation")
    public Object  listMPRGOPI_No_Mutation() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRGOPI_No_Mutation();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRGBAn_OPI_non_echus")
    public Object  listMPRGBAn_OPI_non_echus() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRGBAn_OPI_non_echus();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    // =============== NEW MUTATION=====================

    @GetMapping("listBPSDE")
    public Object  listBPSDE() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listBPSDE();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("checkFatherChild/idSm/{idFather}")
    public Object  checkFatherChild(@PathVariable Long idFather) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.checkFatherChild(idFather);
            if(items == null){
                return renderStringData(false,"","No element");
            }
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listMPRg")
    public Object  listMPRg() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMPRg();
            if(items == null){
                return renderStringData(false,"","No element");
            }
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("sourceEqualBPSDEV")
    public Object  sourceEqualBPSDEV() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.sourceEqualBPSDEV();
            if(items == null){
                return renderStringData(false,"","No element");
            }
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("list/ma_ban/te/{idTe}/ksu/{idKsu}")
    public Object  listMaBan(@PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu) {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMaBanAppro(idTe, idKsu);
            if(items == null){
                return renderStringData(false,"","Not found");
            }
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("list/ma_ban")
    public Object  listMaBanSpecial() {
        try {
            List<SupportMarchandEnchage> items = supportMarchandEnchageService.listMaBAnAndMaBAnSpecial();
            if(items == null){
                return renderStringData(false,"","Not found");
            }
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


}
