package com.esmc.gestionPayement.Controllers;


import com.esmc.gestionPayement.ServicesInterface.SettingsServiceInterface;
import com.esmc.gestionPayement.entities.Settings;
import com.esmc.gestionPayement.inputs.SettingsInput;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;
@RestController
@RequestMapping("api/settings/request")
public class SettingsController extends DataFormatter<Settings> {

    @Autowired
    private SettingsServiceInterface settingsServiceInterface;

    @PostMapping("add")
    public Object createSettings(@RequestBody() SettingsInput data){
        if(settingsServiceInterface.getSettingsByCode(data.getCode())==null){
            return  renderData(true, settingsServiceInterface.createSettings(data),"Create settings");
        }
        return  renderStringData(false,"Error while procssing" ,"Settings code already exists");
    }

    @PutMapping(value = "edit/{id}")
    public Object updateSettings(@PathVariable Long id, @RequestBody SettingsInput data) {
        if( settingsServiceInterface.getSettingsById(id)==null){
            return  renderStringData(false,"Error while procssing" ,"Settings item not found");
        }
        if(data.getCode() != null &&  settingsServiceInterface.getSettingsByCode(data.getCode())!=null){
            return  renderStringData(false,"Error while procssing" ,"Settings code already exists");
        }
        return  renderData(true, settingsServiceInterface.updateSettings(id,data),"update settings");
    }

    @GetMapping("by/code/{code}")
    public Object getSettingUsedByTe(@PathVariable("code") String code){
        Settings item = settingsServiceInterface.getSettingsByCode(code);
        if(item == null){
            return  renderStringData(false,"Error while procssing" ,"Settings item not found");
        }
        return  renderData(true,item,"settings by code");
    }

    @GetMapping("by/id/{id}")
    public Settings getSettingById(@PathVariable("id") Long id){
        Settings item = settingsServiceInterface.getSettingsById(id);
        return item;
    }

    @GetMapping("seeds")
    public Object seeds(){
        settingsServiceInterface.generateSeeds();
        return  renderStringData(true,"settings","settings seeds");
    }


    @GetMapping("/list")
    public Object getSettingUsedByTe(){
        List<Settings> set =settingsServiceInterface.getAll();
        return  renderDataArray(true,set,"settings list");
    }


}