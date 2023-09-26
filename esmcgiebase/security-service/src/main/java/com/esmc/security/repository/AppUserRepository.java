package com.esmc.security.repository;

import com.esmc.security.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>  {
    public AppUser findByUsername(String username);
}
