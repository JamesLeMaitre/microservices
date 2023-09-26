package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.dao.VendeursEnAttenteDeQuota;
import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.utils.enums.KsuType;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FifoInterface {

    public void addFifo(Fifo f, Long id) throws Exception;

    public Fifo addFifoByCategorie(Fifo f);

    Long getKsuByDetailAgr(Long detailAgr);

    public List<VendeursEnAttenteDeQuota> vendeursEnAttenteDeQuotas();

    void setNumOrder(Vague vague, KsuType ksuType);

    List<Fifo> findAllByKsuType(KsuType ksuType);

    public Fifo getFifoById(Long id);

    public Fifo updateFifo(Fifo f);

    public void deleteFifo(Long id);

    public List<Fifo> listFifo();

    public List<Fifo> ListFifoActif();

    public List<Fifo> ListFifoInactif();

    public List<Fifo> ListFifoByDetailAgrId(Long id);

    public List<Fifo> ListFifoVenteActif();

    public List<Fifo> ListFifoAchatActif();

    public List<Fifo> ListFifoVenteInactif();

    public List<Fifo> ListFifoAchatInactif();

    /*public List<Fifo> ListFifoByFicheODDId(Long id);*/

    /*public List<Fifo> ListFifoByByDetailAgrIdAndFicheODDId(Long id, Long id2);*/

    public List<Fifo> ListFifoByTypeAvr(String libelle);

    public Fifo echangeFifo(int numOrdre);

    public List<Fifo> ListFifoByTypeAvrAchat();

    public List<Fifo> ListFifoByTypeAvrVente();

    public int setNextNumOrdreAchat();

    public void changement();

    Long getKsu(Long idDetailAgr);

    Fifo getFirstAchatFromExtern(boolean b, Long currentFirstVenteTe);


    List<Fifo> getFifiByIdDetailsAgr(Long id);


    Fifo getNumOrdre(Long id, Long ksuType);

    boolean checkIfExistInTour(Long idKsu, Long ksuType);
}
