package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.AffectationSMAvr;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AffectationSMAvrInterface {
    public AffectationSMAvr addAffectation (AffectationSMAvr a);
    public AffectationSMAvr updateAffectationSMAvr( AffectationSMAvr affectationSMAvr);
    public void deleteAffectationSMAvr(Long id) ;
    public List<AffectationSMAvr> listAffectationSMAvr();

    public List<AffectationSMAvr>  AffSMAvrByEchange(Long id);

    public List<AffectationSMAvr> AffSMAvrByTypeAvr(Long id);

    public List<AffectationSMAvr> AffSMAvrByTypeSmavr(Long id);

    public List<AffectationSMAvr> AffSMAvrByTypeAvrEchangeSMAVR(Long id1,Long id2);

   // public List<AffectationSMAvr> getSmavr( String typeSmAvr, String typeAvr);
}
