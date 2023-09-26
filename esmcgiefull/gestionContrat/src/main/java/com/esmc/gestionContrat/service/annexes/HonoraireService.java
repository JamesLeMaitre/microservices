package com.esmc.gestionContrat.service.annexes;

import com.esmc.gestionContrat.entities.annexes.Grille;
import com.esmc.gestionContrat.entities.annexes.Honoraire;
import com.esmc.gestionContrat.feign.DetailsAgrRestClient;
import com.esmc.gestionContrat.feign.KsuRestClient;
import com.esmc.gestionContrat.repositories.annexes.GrilleRepository;
import com.esmc.gestionContrat.repositories.annexes.HonorairesRepository;
import com.esmc.gestionContrat.serviceInterfaces.annexes.HonoraireServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class HonoraireService implements HonoraireServiceInterface {
    private final HonorairesRepository honorairesRepository;
    private final DetailsAgrRestClient detailsAgrRestClient;
    private final KsuRestClient ksuRestClient;

    @Override
    public List<Honoraire> getAll() {
        return honorairesRepository.findAll();
    }

    @Override
    public Honoraire create(Honoraire honoraire, Long idDetailsAgr) {
        DetailsAgr detailsAgr = detailsAgrRestClient.getDetailAgrById(idDetailsAgr);
        Ksu ksu = ksuRestClient.getById(detailsAgr.getKsu());
        honoraire.setIdDetailsAgr(ksu.getId());
        honoraire.setDate_signature(new Date());
        return honorairesRepository.save(honoraire);
    }

    @Override
    public void delete(Long id) {
        honorairesRepository.deleteById(id);
    }

    @Override
    public Honoraire edit(Honoraire honoraire, Long id) {
        Honoraire hono = honorairesRepository.findById(id).orElse(null);
        assert hono != null;
        hono.setIdHonoraire(honoraire.getIdHonoraire());
        hono.setIdDetailsAgr(honoraire.getIdDetailsAgr());
        hono.setDate_signature(honoraire.getDate_signature());
        hono.setMontant_mensuel(honoraire.getMontant_mensuel());
        hono.setMontant_global(honoraire.getMontant_global());
        hono.setDate_signature(honoraire.getDate_signature());
        hono.setLieu_signature(honoraire.getLieu_signature());
        hono.setPeriode_tache(honoraire.getPeriode_tache());
        System.out.println("=======OK==========");
        return honorairesRepository.save(hono);
    }

    @Override
    public Honoraire getById(Long id) {
        return honorairesRepository.findById(id).orElse(null);
    }

    @Override
    public int getCountAll() {
        return 0;
    }
}
