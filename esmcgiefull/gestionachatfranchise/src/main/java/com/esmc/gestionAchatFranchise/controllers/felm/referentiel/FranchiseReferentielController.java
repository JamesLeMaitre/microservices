package com.esmc.gestionAchatFranchise.controllers.felm.referentiel;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseIndicateur;
import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.referentiel.FranchiseReferentielInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/franchiseReferentiel")
public class FranchiseReferentielController extends DataFormatter<FranchiseReferentiel> {
    @Autowired
    private FranchiseReferentielInterface franchiseReferentielInterface;


    @GetMapping("listByIdDetailAgr/{idDetailAgr}")
    public Object List(@PathVariable("idDetailAgr") Long id){
        try {
            List<FranchiseReferentiel> items = franchiseReferentielInterface.getByIdDetailAgr(id);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
