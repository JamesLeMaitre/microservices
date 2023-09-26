package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.DetailSupport;
import com.esmc.gestionAvr.servicesInterfaces.DetailSupportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

@RestController
@RequestMapping(name = "api/detail_support/")
public class DetailSupportController extends DataFormatter<DetailSupport> {

    @Autowired
    private DetailSupportServiceInterface detailSupportServiceInterface;

    @GetMapping("by/id/{id}")
    public Object getDetailSupport(@PathVariable Long id) {

        try {
            DetailSupport detailSupport = detailSupportServiceInterface.getDetailSupportById(id);

            if (detailSupport == null) {
                return renderStringData(false, "", "Not found");
            }
            return renderData(true, detailSupport, "Opération éffectuer avec succès");
        } catch (Exception e) {
            return "Error when proccessing "+e;
        }
        }


}
