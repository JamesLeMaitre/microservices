package com.esmc.gestionContrat.service.annexes;

import com.esmc.gestionContrat.entities.annexes.Grille;
import com.esmc.gestionContrat.feign.DetailsAgrRestClient;
import com.esmc.gestionContrat.feign.KsuRestClient;
import com.esmc.gestionContrat.repositories.annexes.GrilleRepository;
import com.esmc.gestionContrat.serviceInterfaces.annexes.GrilleServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GrilleService implements GrilleServiceInterface {
    private final GrilleRepository grilleRepository;
    private final DetailsAgrRestClient detailsAgrRestClient;
    private final KsuRestClient ksuRestClient;

    @Override
    public List<Grille> getAll() {
        return grilleRepository.findAll();
    }

    @Override
    public Grille create(Grille grille, Long idDetailsAgr) {
        DetailsAgr detailsAgr = detailsAgrRestClient.getDetailAgrById(idDetailsAgr);
        Ksu ksu = ksuRestClient.getById(detailsAgr.getKsu());
        grille.setIdDetailsAgr(ksu.getId());
        return grilleRepository.save(grille);
    }

    @Override
    public void delete(Long id) {
        grilleRepository.deleteById(id);
    }

    @Override
    public Grille edit(Grille grille, Long id) {
        Grille grille1 = grilleRepository.findById(id).orElse(null);
        assert grille1 != null;
        grille1.setIdGrille(grille.getIdGrille());
        grille1.setPoste(grille.getPoste());
        grille1.setNiveau_etude_competence(grille.getNiveau_etude_competence());
        grille1.setPoste(grille.getPoste());
        grille1.setTranche1_debutant(grille.getTranche1_debutant());
        grille1.setTranche2_junior(grille.getTranche2_junior());
        grille1.setTranche3_senior(grille.getTranche3_senior());
        grille1.setAvantages(grille.getAvantages());
        grille1.setIdDetailsAgr(grille.getIdGrille());
        return grilleRepository.save(grille1);
    }

    @Override
    public Grille getById(Long id) {
        return grilleRepository.findById(id).orElse(null);
    }

    @Override
    public int getCountAll() {
        return 0;
    }
}
