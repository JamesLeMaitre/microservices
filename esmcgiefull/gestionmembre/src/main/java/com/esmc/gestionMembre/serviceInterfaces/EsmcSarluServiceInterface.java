package com.esmc.gestionMembre.serviceInterfaces;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.input.Result;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EsmcSarluServiceInterface {
    public List<Souscription> getSouscrition(String code, Long idKsu);

    public Double sommeSouscritionPassifs(String code);

    public List<BonNeutreDetail> passifsESMCSARLUBonNeutre(String code, Long idKsu);

    public  List<CompteCredit> passifsEsmcSarluRpgNr(String code, Long idKsu);

    public List<CompteCredit> passifsEsmcSarluRpgr( String code, Long idKsu);

    public List<CompteCredit> passifsEsmcSarluInr( String code, Long idKsu);

    public List<Gcp> getpassifsESMCSARLUGcp(String code, Long idKsu);

    //public List<Traite> getOpi(Long id);

    List<Map<String, Object>> getAllOpiByMembre(String code, Long idKsu);

    List<Map<String, Object>> getAllOpiByMembreOpi(String code);

    public List<Traite> listOpiMembre(String code);


}
