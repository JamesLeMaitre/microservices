package com.esmc.gestionMembre.dao;

import com.esmc.gestionMembre.entities.*;

public class EmcSarluDao {
    private Souscription souscription = null;
    private Integrateur integrateur = null;
    private BonNeutre bonNeutre= null;
    private CompteCredit compteCredit = null;
    private Gcp gcp= null;
    private Tpagcp tpagcp=null;



    public EmcSarluDao() {
    }

    public Souscription getSouscription() {
        return souscription;
    }

    public void setSouscription(Souscription souscription) {
        this.souscription = souscription;
    }

    public Integrateur getIntegrateur() {
        return integrateur;
    }

    public void setIntegrateur(Integrateur integrateur) {
        this.integrateur = integrateur;
    }

    public BonNeutre getBonNeutre() {
        return bonNeutre;
    }

    public void setBonNeutre(BonNeutre bonNeutre) {
        this.bonNeutre = bonNeutre;
    }

    public CompteCredit getCompteCredit() {
        return compteCredit;
    }

    public void setCompteCredit(CompteCredit compteCredit) {
        this.compteCredit = compteCredit;
    }

    public Gcp getGcp() {
        return gcp;
    }

    public void setGcp(Gcp gcp) {
        this.gcp = gcp;
    }

    public Tpagcp getTpagcp() {
        return tpagcp;
    }

    public void setTpagcp(Tpagcp tpagcp) {
        this.tpagcp = tpagcp;
    }
}
