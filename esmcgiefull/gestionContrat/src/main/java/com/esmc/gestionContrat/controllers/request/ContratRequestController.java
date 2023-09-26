package com.esmc.gestionContrat.controllers.request;

import com.esmc.gestionContrat.entities.Contrat;
import com.esmc.gestionContrat.request.ContratRequest;
import com.esmc.gestionContrat.request.ContratRequestInput;
import com.esmc.gestionContrat.service.ContratService;
import com.esmc.gestionContrat.service.request.ContratRequestService;
import com.esmc.gestionContrat.serviceInterfaces.request.ContratRequestServiceInterface;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contratStep/")
public class ContratRequestController extends DataFormatter<ContratRequest> {
    private final ContratRequestServiceInterface requestService;
    private final ContratRequestService contratRequestService;
    private final ContratService contratService;

    @PostMapping("add/{idDetailsAgr}")
    public Object create(@RequestBody() ContratRequest contratRequest, @PathVariable Long idDetailsAgr){
        try {
            return  renderData(true, contratRequestService.save(idDetailsAgr,contratRequest),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @GetMapping("list")
    public Object List() {
        try {
            List<ContratRequest> items = requestService.getContrat();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }



        @PostMapping("save/{idDetailsAgr}")
    public Object saveStep(@RequestBody() ContratRequestInput contratRequest, @PathVariable Long idDetailsAgr){
        try {
            return  renderData(true, requestService.createv2(idDetailsAgr,contratRequest),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
