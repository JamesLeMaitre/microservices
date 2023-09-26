package com.esmc.gestionAchatFranchise.services;


import com.esmc.gestionAchatFranchise.servicesinterfaces.FonctionCantonInt;
import org.springframework.stereotype.Service;

@Service
public class FonctionCantonImpl implements FonctionCantonInt {

    Double prixUnCHD=105000000.00;

    @Override
    public int nombreCantonParMontant(Double montant) {

        Double sommeFileCanton=(prixUnCHD*4);


        int nbrAchat=0;
               nbrAchat = (int) (montant/sommeFileCanton);


        return nbrAchat;
    }

    @Override
    public Double prixCantonParNbCanton(int nbreCanton) {

        Double montantTotal=(prixUnCHD*4)*nbreCanton;

        return montantTotal;
    }
}
