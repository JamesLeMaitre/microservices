package com.esmc.gestionAvr.dao;

import com.esmc.gestionAvr.entities.Fifo;

public class VendeursEnAttenteDeQuota {
    public double montantTotal;
    public Fifo fifo;

    public VendeursEnAttenteDeQuota( double montantTotal, Fifo fifo) {
        this.montantTotal = montantTotal;
        this.fifo = fifo;
    }

    public VendeursEnAttenteDeQuota() {
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Fifo getFifo() {
        return fifo;
    }

    public void setFifo(Fifo fifo) {
        this.fifo = fifo;
    }
}
