package com.esmc.gestionCertification.controller;

import com.esmc.gestionCertification.entities.DataCertification;
import com.esmc.gestionCertification.services.DataCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("api/dataCertification/")
public class DataCertificationController extends DataFormatter<DataCertification> {
    @Autowired
    private DataCertificationService dataCertificationService;


    @GetMapping("by/idPanierCertification/{id}")
    public Object getDataSupport(@PathVariable Long id) {
        try {
            DataCertification dataCertification = dataCertificationService.getDataCertification(id);
            if(dataCertification == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, dataCertification,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }
}
