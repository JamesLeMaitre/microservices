package com.esmc.security.repository;

import com.esmc.security.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long>  {
    public AppRole findByRolename(String rolename);
}
