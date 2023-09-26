package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Panier;
import com.esmc.gestionAvr.repositories.AvrRepository;
import com.esmc.gestionAvr.repositories.PanierRepository;
import com.esmc.gestionAvr.servicesInterfaces.PanierServiceInterface;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class PanierService implements PanierServiceInterface {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private AvrServices avrServices;

    @Autowired
    private AvrRepository avrRepository;

    @Override
    public Panier addPanier(Panier panier) {
        Panier p = panierRepository.findPanierByKsuAndAvr(panier.getKsu(), panier.getAvr().getId());

        if (p != null) {
            p.setQuantite(p.getQuantite() + panier.getQuantite());
            panierRepository.save(p);
            return p;
        }

//        Avr a  = avrRepository.findById(p.getId()).orElse(null);
//        int restant = a.getQuantite() - p.getQuantite();
//        a.setQuantite(restant);
//        avrRepository.save(a);

        return panierRepository.save(panier);
    }

    @Override
    public Panier updatePanier(Long id, Panier panier) {
        panier.setDateUpdate(new Date());
        return panierRepository.save(panier);
    }

    @Override
    public void deletePanier(Long id) {
        panierRepository.deleteById(id);
    }

    @Override
    public List<Panier> getAllPanier() {
        return panierRepository.findAll();
    }

    @Override
    public List<Panier> panierDeKsu(Long id) {
        return panierRepository.listPanierFalseByKsu(id);
    }

    @Override
    public Double prixTotalPanier(Long id) {
        return panierRepository.prixTotalPanier(id);
    }

    /**
     * @param id
     */
    @Override
    public List<Ksu> validerCommande(Long id) {


        List<Panier> listp = panierRepository.panierDeKsu(id);;

        List<Ksu> ksu = new LinkedList<>();

            if (listp.size() > 0 ) {
                for (Panier p: listp) {
                    Ksu k = avrServices.getVendeur(p.getAvr().getId());
                    System.out.println(("ksu : "+k.getCodeKsu()));

                    if (!ksu.contains(k)) {
                        ksu.add(k);
                    }
                }
            }

      return ksu;
    }

    /**
     * @param p
     * @return
     */
    @Override
    public Panier findById(Panier p) {

        Panier pa = panierRepository.findById(p.getId()).orElse(null);

        if (pa != null) {
            pa.setQuantite(p.getQuantite());
            panierRepository.save(pa);
        }

        return pa;
    }


}
