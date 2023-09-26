package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.ZoneMonnetaire;
import com.esmc.gestionAchatFranchise.services.ZoneMonnetaireImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/zoneMonnetaire")
public class ZoneMonnetaireController {
    @Autowired
    private ZoneMonnetaireImp zoneMonnetaireImp;

    @PostMapping("/save")
    public ResponseEntity<ZoneMonnetaire> addZoneMonnetaire(@RequestBody ZoneMonnetaire zoneMonnetaire) {
        return new ResponseEntity<ZoneMonnetaire>( zoneMonnetaireImp.addZoneMonnetaire(zoneMonnetaire), HttpStatus.CREATED);
    }

    @GetMapping({"/getAll"})
    public ResponseEntity<List<ZoneMonnetaire>> getAllZoneMonnetaire(){
        return new ResponseEntity<List<ZoneMonnetaire>>(zoneMonnetaireImp.getZoneMonnetaire(), HttpStatus.OK);
    }

    @GetMapping("/get/{zoneMonnetaireId}")
    public ResponseEntity<ZoneMonnetaire> getZoneMonnetaire(@PathVariable("zoneMonnetaireId") Long zoneMonnetaireId) {
        return new ResponseEntity<ZoneMonnetaire>(zoneMonnetaireImp.getZoneMonnetaireById(zoneMonnetaireId), HttpStatus.OK);
    }


    @PutMapping("/update/{zoneMonnetaireId}")
    public ResponseEntity<ZoneMonnetaire> updateZoneMonnetaire(@PathVariable("zoneMonnetaireId") Long zoneMonnetaireId, @RequestBody ZoneMonnetaire zoneMonnetaire) {
        zoneMonnetaire.setId(zoneMonnetaireId);
        return new ResponseEntity<>(zoneMonnetaireImp.updateZoneMonnetaire(zoneMonnetaire), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{zoneMonnetaireId}")
    public ResponseEntity<ZoneMonnetaire> deleteZoneMonnetaire(@PathVariable Long zoneMonnetaireId) {
        zoneMonnetaireImp.deleteZoneMonnetaire(zoneMonnetaireId);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @GetMapping("/list/{id}/continent")
    public List<ZoneMonnetaire> listZoneMonnetaireParContinent(@PathVariable Long id) {
        return zoneMonnetaireImp.listContinent(id);
    }

}
