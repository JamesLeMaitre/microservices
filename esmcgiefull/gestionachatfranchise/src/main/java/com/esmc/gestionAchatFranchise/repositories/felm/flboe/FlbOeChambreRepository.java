package com.esmc.gestionAchatFranchise.repositories.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;

public interface FlbOeChambreRepository extends org.springframework.data.jpa.repository.JpaRepository<FlbOeChambre, Long> {
    FlbOeChambre getFlbOeChambreById(Long id);
}