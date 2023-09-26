package com.esmc.gestionMembre.dao;

import com.esmc.gestionMembre.entities.*;

import java.util.List;

public class McnpDao {
    private AncienCompteCredit ancienCompteCredit= null;
    private AncienGcp ancienGcp= null;
    private AncienEscompte ancienEscompte= null;
    private AncienEchange ancienEchange = null;
    private MembreFondateur11000 membreFondateur11000 =null;
    private List<RepartitionMF11000> repartitionMF11000 =null;
    private AncienDetailsSmsmoney ancienDetailsSmsmoney =null;
    private MembreFondateur107 membreFondateur107 =null;
    private List<RepartitionMf107> repartitionMf107 =null;
    private DetailMf107 detailMf107 =null;





    public McnpDao() {
    }

    public AncienCompteCredit getAncienCompteCredit() {
        return ancienCompteCredit;
    }

    public void setAncienCompteCredit(AncienCompteCredit ancienCompteCredit) {
        this.ancienCompteCredit = ancienCompteCredit;
    }

    public AncienGcp getAncienGcp() {
        return ancienGcp;
    }

    public void setAncienGcp(AncienGcp ancienGcp) {
        this.ancienGcp = ancienGcp;
    }

    public AncienEscompte getAncienEscompte() {
        return ancienEscompte;
    }

    public void setAncienEscompte(AncienEscompte ancienEscompte) {
        this.ancienEscompte = ancienEscompte;
    }

    public AncienEchange getAncienEchange() {
        return ancienEchange;
    }

    public void setAncienEchange(AncienEchange ancienEchange) {
        this.ancienEchange = ancienEchange;
    }

    public MembreFondateur11000 getMembreFondateur11000() {
        return membreFondateur11000;
    }

    public void setMembreFondateur11000(MembreFondateur11000 membreFondateur11000) {
        this.membreFondateur11000 = membreFondateur11000;
    }

    public List<RepartitionMF11000> getRepartitionMF11000() {
        return repartitionMF11000;
    }

    public void setRepartitionMF11000(List<RepartitionMF11000> repartitionMF11000) {
        this.repartitionMF11000 = repartitionMF11000;
    }

    public AncienDetailsSmsmoney getAncienDetailsSmsmoney() {
        return ancienDetailsSmsmoney;
    }

    public void setAncienDetailsSmsmoney(AncienDetailsSmsmoney ancienDetailsSmsmoney) {
        this.ancienDetailsSmsmoney = ancienDetailsSmsmoney;
    }

    public MembreFondateur107 getMembreFondateur107() {
        return membreFondateur107;
    }

    public void setMembreFondateur107(MembreFondateur107 membreFondateur107) {
        this.membreFondateur107 = membreFondateur107;
    }

    public List<RepartitionMf107> getRepartitionMf107() {
        return repartitionMf107;
    }

    public void setRepartitionMf107(List<RepartitionMf107> repartitionMf107) {
        this.repartitionMf107 = repartitionMf107;
    }

    public DetailMf107 getDetailMf107() {
        return detailMf107;
    }

    public void setDetailMf107(DetailMf107 detailMf107) {
        this.detailMf107 = detailMf107;
    }
}
