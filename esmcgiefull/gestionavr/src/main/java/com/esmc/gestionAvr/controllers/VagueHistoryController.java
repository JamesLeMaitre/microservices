package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.entities.VagueHistory;
import com.esmc.gestionAvr.servicesInterfaces.VagueHistoryServiceInterface;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping("api/vagueHistory/request")
public class VagueHistoryController extends DataFormatter<VagueHistory> {

    @Autowired
    private VagueHistoryServiceInterface vagueHistoryServiceInterface;

    @GetMapping("list")
    public Formatter<List<VagueHistory>> List(){
        List<VagueHistory> items = vagueHistoryServiceInterface.getAll();
        return  renderDataArray(true,items,"vagueHistory list");
    }
}
