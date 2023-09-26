package com.esmc.gestionMembre.dao;

import com.esmc.gestionMembre.entities.Credit;
import com.esmc.gestionMembre.entities.Place;

public class RedemarreDao {
    private Credit credit = null;
    private  Place place = null;


    public RedemarreDao() {
    }

    public RedemarreDao(Credit credit, Place place) {
        this.credit = credit;
        this.place = place;
    }

    public RedemarreDao(Credit credit) {
        this.credit = credit;
    }

    public RedemarreDao(Place place) {
        this.place = place;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
