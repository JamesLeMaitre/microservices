package com.esmc.gestionMembre.serviceInterfaces;

import com.esmc.gestionMembre.entities.Credit;

import java.util.List;

public interface CreditServiceInterface {
    List<Credit> getCreditByCodeMemebre(String codeMemebre);
}
