package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.StepCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StepCertificationRepository extends JpaRepository<StepCertification,Long> {
    @Query("select  st from StepCertification st where st.idDetailAgr=:x and st.idDetailAgrFranchiser=:y and st.idPoste=:z")
    StepCertification getStepCertificationByIdDetailAgrAndIdDetailAgrFranchiserAndIdPoste(@Param("x")Long idx,@Param("y")Long idy,@Param("z")Long idz);
}
