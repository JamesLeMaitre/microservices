package com.esmc.gestionte.serviceinterface;


import com.esmc.gestionte.entities.TerminalEchange;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.models.DetailsAgr;

import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */


public interface TerminalEchangeService {
    /**
     * Liste des agr
     * @return
     */
    List<TerminalEchange> getSTerminalEchange();

    /**
     * creation d'un detailSMEnchange
     * @param list
     * @return
     */
    public void addNewTerminalEchange(List<DetailsAgr> list) throws Exceptions;

    /**
     * supprimer un t
     * @param id
     */
    void deleteTerminalEchange(Long id) throws Exceptions;

    public void updateTerminalEchange(Long id, TerminalEchange terminalEchange)throws Exceptions;

    public TerminalEchange getById(Long id) throws  Exceptions;

    public Double jour(Double montant);
    public Double limitee_11_2(Double montant);
    public Double limitee_22_4(Double montant);
    public Double []  illimitee_22_4(Double montant);

    Double bcnrPrk(Double montant);

    Double bcnrPrk7(Double montant);

    Double margeMABAn(Double montant);

    public Double MPRgBAnEntrerM(Double MPRgBAn)throws Exceptions;
    public Double encaissementBAn (Double BPS,Double PCK,Double PRK )throws Exceptions;
    public Double dencaissementBAn (Double BPS,Double PCK,Double PRK,String typeBPS )throws Exceptions;

    public TerminalEchange findByIdDetailAgr(Long id) throws  Exceptions;

    public TerminalEchange teByDetailAgr(Long id);

    public TerminalEchange creationTeByAgr(Long id);











}
