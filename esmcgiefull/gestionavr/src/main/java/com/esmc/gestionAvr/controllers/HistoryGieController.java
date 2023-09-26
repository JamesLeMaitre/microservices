package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.HistoryGieAchat;
import com.esmc.gestionAvr.entities.Intrant;
import com.esmc.gestionAvr.services.HistoryGieService;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping(value = "api/HistoryGie/")
public class HistoryGieController extends DataFormatter<HistoryGieAchat> {

    @Autowired
    private HistoryGieService historyGieService;

    @GetMapping(value = "amount")
    public Object amount() {
        try {
            return  renderDoubleData(true,historyGieService.getAmount(),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

}
