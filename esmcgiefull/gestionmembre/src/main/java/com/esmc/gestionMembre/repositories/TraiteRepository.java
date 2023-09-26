package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Traite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface TraiteRepository extends JpaRepository<Traite, Long> {

    @Query("select a from Traite a where a.traiteTegcp = :x and a.traiteDateDebut > '2019-12-31'")
    public List<Traite> listOpi (@Param("x") int id);


    @Query(value ="select t.traite_code_banque as banque,t.traite_date_debut as dateDebut,t.traite_date_fin as dateFin,t.traite_montant as montant" +
            " from eu_traite t, tpagcp tpa where t.traite_tegcp=tpa.id_tpagcp and tpa.code_membre = :x and t.traite_date_debut >= '2018-07-01' and tpa.ntf != 1 order by t.traite_date_debut" ,nativeQuery = true)
    public List<Map<String,Object>> findAllOpiByMembreO(@Param("x") String code);

    @Query(value ="select t.traite_code_banque as banque,t.traite_date_debut as dateDebut,t.traite_date_fin as dateFin,t.traite_montant as montant " +
            "from eu_traite t, tpagcp tpa where t.traite_tegcp=tpa.id_tpagcp and tpa.code_membre = :x and t.traite_date_fin > 2019-12-31" ,nativeQuery = true)
    public List<Map<String,Object>> findAllOpiByMembre(@Param("x") String code);
}
