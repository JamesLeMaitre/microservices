package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.ZoneMonnetaire;
import com.esmc.gestionAchatFranchise.inputs.jsonFil;
import com.esmc.gestionAchatFranchise.inputs.jsonFlboe;
import com.esmc.gestionAchatFranchise.inputs.jsonFlbose;
import com.esmc.gestionAchatFranchise.services.PaysImp;
import com.esmc.gestionAchatFranchise.services.ZoneMonnetaireImp;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ContinentInt;
import com.esmc.gestionAchatFranchise.servicesinterfaces.StatistiqueInterface;
import com.esmc.models.Formatter;
import com.esmc.models.FormatterDoubleString;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.io.FileReader;

@RestController
@RequestMapping(value="/api/statistique")
public class StatistiqueController extends DataFormatter<String> {

    private int defaultCanton = 390;
    private int defaultPrefecture = 117;
    private int defaultCommune = 39;
    private int defaultRegion = 6;


    @Autowired
    public PaysImp paysImp;

    @Autowired
    public ZoneMonnetaireImp zoneMonnetaireImp;

    @Autowired
    public ContinentInt continentInt;

    @Autowired
    public StatistiqueInterface statistiqueInterface;



    public int defaultToTalValueForCountry(){
        return defaultCanton + defaultCommune+ defaultPrefecture+ defaultRegion +1;
    }

    @GetMapping("/cumule/element/by/country/{id}")
    public Formatter<String> valueForCountry(@PathVariable("id")  Long id){
        String total = (defaultToTalValueForCountry())+"";
       return renderStringData(true, total,"Total element by country");
    }

    @GetMapping("/cumule/element/by/zone-monetaire/{id}")
    public Formatter<String> valueForZoneMonetaire(@PathVariable("id")  Long id){
        try {
            ZoneMonnetaire zoneMonnetaire = zoneMonnetaireImp.getZoneMonnetaireById(id);
            if(zoneMonnetaire != null){
                int nbPays = zoneMonnetaireImp.countPaysByZoneMonetaire(id);
                int totalValue = nbPays*defaultToTalValueForCountry();
                String total =  totalValue +"";
                return renderStringData(true, total,"Total element by monetary zone");
            }else{
                return renderStringData(false,"Zone Monetaire not found","An error occurred");
            }
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("/cumule/element/by/continent/{id}")
    public Formatter<String> valueForContinent(@PathVariable("id")  Long id){
            try {
                ZoneMonnetaire zoneMonnetaire = zoneMonnetaireImp.getZoneMonnetaireById(id);
                if(zoneMonnetaire != null){
                    int nbPaysContinerts = zoneMonnetaireImp.countPaysContinents(id);
                    int totalValue = nbPaysContinerts*(defaultToTalValueForCountry()+1);
                    String total =  totalValue +"";
                    return renderStringData(true, total,"Total element by continents");
                }else{
                    return renderStringData(false,"Zone Monetaire not found","An error occurred");
                }
            }catch (Exception e){
                return renderStringData(false,e.getMessage(),"An error occurred");
            }
    }

    @GetMapping("/cumule/element/for/world")
    public Formatter<String> valueForWord(){
        try {
                int totalPays = paysImp.count();
                int totalZoneMonetaire = zoneMonnetaireImp.count();
                String total = zoneMonnetaireImp.countPaysByWord() +"";
                return renderStringData(true, total,"Total element by country");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("felm/{typeElement}/cumule/stage/{stage}/element/{id}")
    public Formatter<String> valueFroFelmElement(@PathVariable("typeElement") Long typeElement, @PathVariable("stage") Long stage, @PathVariable("id") Long id){
        try {

            String total = "";
            return renderStringData(true, total,"Total element by country");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("fil/cumule/stage/{stage}/element/{id}")
    public Object valueFroFILElement( @PathVariable("stage") int stage, @PathVariable("id") Long id){
        try {
            double value = statistiqueInterface.getFillStat(stage,id);
            return renderDoubleStringData(true, value,"Total element fil");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("flbose/cumule/stage/{stage}/element/{id}")
    public Object valueFroFLBOSEElement( @PathVariable("stage") int stage, @PathVariable("id") Long id){
        try {
            double value = statistiqueInterface.getFlboseStat(stage,id);
            return renderDoubleStringData(true, value,"Total element flbose");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("flboe/cumule/stage/{stage}/element/{id}")
    public Object valueFroFLBOEElement( @PathVariable("stage") int stage, @PathVariable("id") Long id){
        try {
            double value = statistiqueInterface.getFlboeStat(stage,id);
            return renderDoubleStringData(true, value,"Total element flboe");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("felm/cumule/stage/{stage}/element/{id}")
    public Object valueFroFLEMElement(@PathVariable("stage") int stage, @PathVariable("id") Long id){
        try {
            double value = statistiqueInterface.getFlbemStat(stage,id);
            return renderDoubleStringData(true, value,"Total element felm");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }


    @GetMapping("decoupage/stage/{stage}")
    public Formatter<String> decoupage( @PathVariable("stage") int stage){
        try {
            double value = statistiqueInterface.getDecoupage(stage);
            String total = ""+value;
            return renderStringData(true, total,"Total ");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("decoupage/geo/stage/{stage}/element/{id}")
    public Formatter<String> decoupageGeo( @PathVariable("stage") int stage, @PathVariable("id") Long id){
        try {
            double value = statistiqueInterface.getDecoupageGeo(stage, id);
            String total = ""+value;
            return renderStringData(true, total,"Total ");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }

    @GetMapping("builder")
    public Formatter<String> builder(){
        try {
            double value = statistiqueInterface.builder();
            String total = ""+value;
            return renderStringData(true, total,"building ");
        }catch (Exception e){
            return renderStringData(false,e.getMessage(),"An error occurred");
        }
    }


    @PostMapping("initFill")
    public void initFill (@RequestBody jsonFil fil){
        statistiqueInterface.initFilesFill(fil);
    }

    @PostMapping("initFlbOe")
    public void initFlboe (@RequestBody jsonFlboe flboe){
        statistiqueInterface.initFilesFlboe(flboe);
    }

    @PostMapping("initFlbOse")
    public void initFlbose (@RequestBody jsonFlbose flbose){
        statistiqueInterface.initFilesFlbose(flbose);
    }

    @GetMapping("seedFill")
    public void seedFill (){
        statistiqueInterface.initPlugFilesFill();
    }

    @GetMapping("seedFlboe")
    public void seedFlboe (){
        statistiqueInterface.initPlugFlboeFill();
    }

    @GetMapping("initDecoupage")
    public String initDecoupage (){
        statistiqueInterface.initDecoupage();
        return "ok";
    }


}
