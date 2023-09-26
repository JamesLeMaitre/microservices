package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Settings;

public interface SettingsService {
    Settings getSettingsById(Long id);

    Settings addSettings(Settings settings);

    Settings updateSettings(String type);

    Settings getByCode(String code);
}
