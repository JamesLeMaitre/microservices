package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Region;
import com.esmc.gestionAchatFranchise.services.RegionImp;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(value="/api/region")
public class RegionController extends DataFormatter<Region> {
    @Autowired
    private RegionImp regionImp;

    @PostMapping("/save")
    public Object addRegion(@RequestBody Region region) {
        try{
            Region region1= regionImp.addRegion(region);
            return  renderData(true,region1,"Agent odd created successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }

    }

    @GetMapping("/getAll")
    public Formatter<List<Region>> getAllRegion(){
        return renderDataArray(true,regionImp.getRegion(), "gvvb");
    }

    @GetMapping("/get/{regionId}")
    public Object getRegion(@PathVariable("regionId") Long regionId) {
        return renderData(true,regionImp.getRegionById(regionId), "region");
    }


    @PutMapping("/update/{regionId}")

    public ResponseEntity<Region> updateRegion(@PathVariable("regionId") Long regionId, @RequestBody Region region) {
        region.setId(regionId);
        return new ResponseEntity<Region>(regionImp.updateRegion(region), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{regionId}")
    public Formatter<String> deleteRegion(@PathVariable Long regionId) {

        try{regionImp.deleteRegion(regionId);

            return renderStringData(true,"success","deleted successfully");
        }catch (Exception e){
            return  renderStringData(false,e.getMessage(),"error");
        }


    }

    @GetMapping("/list/{id}/pays")
    public Formatter<List<Region>> listRegionParPays(@PathVariable Long id) {
        return renderDataArray(true,regionImp.listPays(id),"true");
    }


}
