package com.esmc.gestionAchatFranchise.repositories.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;

public interface FlbOseAgenceOddRepository extends org.springframework.data.jpa.repository.JpaRepository<FlbOseAgenceOdd, Long> {
    FlbOseAgenceOdd getFlbOseAgenceOddById(Long id);

}
