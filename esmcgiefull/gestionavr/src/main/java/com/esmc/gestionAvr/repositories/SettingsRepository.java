package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings,Long> {
    Settings getSettingsById(Long id);

    Settings findByType(String type);

    Settings findByCode(String code);
}
