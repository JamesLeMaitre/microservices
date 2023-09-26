package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.Settings;
import com.esmc.gestionPayement.entities.TransactionInRequest;
import com.esmc.models.SupportMarchandEnchage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SettingsRepo extends JpaRepository<Settings, Long> {

    Settings getSettingsById(Long id);

    Settings getSettingsByCode(String code);
}