package com.esmc.gestionMembre.dao;

import com.esmc.models.Ksu;
import com.esmc.models.MaBanKsu;

public class ExistenceMembreDao {
    private Ksu ksu;
    private MaBanKsu maBanKsu;

    public ExistenceMembreDao() {
    }

    public ExistenceMembreDao(Ksu ksu, MaBanKsu maBanKsu) {
        this.ksu = ksu;
        this.maBanKsu = maBanKsu;
    }

    public Ksu getKsu() {
        return ksu;
    }

    public void setKsu(Ksu ksu) {
        this.ksu = ksu;
    }

    public MaBanKsu getMaBanKsu() {
        return maBanKsu;
    }

    public void setMaBanKsu(MaBanKsu maBanKsu) {
        this.maBanKsu = maBanKsu;
    }
}
