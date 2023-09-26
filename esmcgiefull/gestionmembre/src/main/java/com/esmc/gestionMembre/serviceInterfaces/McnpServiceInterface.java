package com.esmc.gestionMembre.serviceInterfaces;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.input.Result;


import java.util.List;

public interface McnpServiceInterface {

    public Result<Long, List<RepartitionMF11000>> listPassifRepMf1100(String code, Long idKsu);

    Result<Long, List<RepartitionMf107>> passifsMCNPmembreFondateurRepartitionMf107 (String code, Long idKsu);

    List<AncienDetailsSmsmoney> passifsMCNPmembreFondateurAncienDetailsSmsmoney (String code);

    AncienCompteCredit passifsMCNPAncienCompteCredit(String code);

    public Result<Long, List<AncienCompteCredit>> listRPGr(String code, Long idKsu);

    Result<Long, List<AncienCompteCredit>> listRPGnr(String code, Long idKsu);

    public Result<Long, List<AncienCompteCredit>> listIr(String code, Long idKsu);

    Result<Long, List<AncienCompteCredit>>  listInr(String code, Long idKsu);

    public Double sommepassifsMCNPGcp(String code, Long idKsu);

    public Double sommepassifsMCNPAncienEchangeNbContreNb(String code, Long idKsu);

    public Double sommepassifsMCNPAncienEchangeNbContreNn(String code, Long idKsu);

    public List<AncienGcp> listpassifMCNPGcp(String code);

    public List<AncienEchange>  getpassifsMCNPAncienEchangeNbContreNb(String code);


    public List<AncienEchange>  getpassifsMCNPAncienEchangeNbContreNn(String code);


    Result<Long, List<AncienCompte>> getPassifCncsMorale(String code, Long idKsu);

    Result<Long, List<AncienCompteCredit>> getPassifCncsPhysique(String code, Long idKsu);

    Result<Long, List<AncienEchange>> getPassifCncsEchange(String code, Long idKsu);

    public Result<Long, List<AncienCompteCredit>> getPassifCncs(String code, Long idKsu);
}
