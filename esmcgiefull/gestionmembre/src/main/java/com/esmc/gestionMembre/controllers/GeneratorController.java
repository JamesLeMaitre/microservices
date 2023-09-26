package com.esmc.gestionMembre.controllers;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.repositories.*;
import com.esmc.gestionMembre.serviceInterfaces.*;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping(path = "api/generator/")
public class GeneratorController {
   // REDEMARE
    @Autowired
    private MoraleRepository moraleRepository;

    @Autowired
    private PhysiqueRepository physiqueRepository;

    // MCNP
    @Autowired
    private AncienMembreRepository ancienMembreRepository;

    //ESMC SARL U
    @Autowired
    private MembreMoraleRepository membreMoraleRepository;

    @Autowired
    private MembreRepository membreRepository;

    interface  SYSTEM {
        final String VALUE_MCNP = "2";
        final String VALUE_REDEMARRE = "1";
        final String VALUE_ESMC_SARL_U = "3";
    }

    @GetMapping("system/code-membre/{code}")
    public Formatter<String> retrieveSystem(@PathVariable("code") String code) {

        DataFormatter<String> formatter = new DataFormatter<>();
        try {
            //MCNP
            AncienMembre ancienMembre = ancienMembreRepository.MCNPancienMembre(code);
            if(ancienMembre != null){
                return formatter.renderStringData(true, SYSTEM.VALUE_MCNP, "System");
            }
            //REDEMARRE
            Morale morale = moraleRepository.RedemarrepersonneMorale(code);
            if(morale != null){
                return formatter.renderStringData(true, SYSTEM.VALUE_REDEMARRE, "System Morale");
            }

            Physique physique = physiqueRepository.RedemarrepersonnePhysique(code);
            if(physique != null){
                return formatter.renderStringData(true, SYSTEM.VALUE_REDEMARRE, "System Physique");
            }
            //ESMC SARL U
            MembreMorale membreMorale = membreMoraleRepository.ESMCSARLUmembreFondateurMembreMorale(code);
            if(membreMorale != null){
                return formatter.renderStringData(true, SYSTEM.VALUE_ESMC_SARL_U, "System Morale");
            }

            Membre membre = membreRepository.ESMCSARLUmembreFondateurMembre(code);
            if(membre != null){
                return formatter.renderStringData(true, SYSTEM.VALUE_ESMC_SARL_U, "System Physique");
            }

            return formatter.renderStringData(false, "", "Code Membre erroné");
        } catch (Exception e) {
            return formatter.renderStringData(false, "", "Code Membre erroné");
        }

    }

}
