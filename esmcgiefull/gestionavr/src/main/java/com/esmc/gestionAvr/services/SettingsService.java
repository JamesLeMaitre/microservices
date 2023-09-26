package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Settings;

public interface SettingsService {
    Settings getSettingsById(Long id);

    Settings addSettings(Settings settings);

    Settings updateSettings(String type);

    Settings getByCode(String code);
}
