package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Settings;
import com.esmc.gestionAvr.repositories.SettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SettingsServiceImpl implements SettingsService{
    private final SettingsRepository settingsRepository;

    @Override
    public Settings getSettingsById(Long id) {
        return settingsRepository.getSettingsById(id);
    }

    @Override
    public Settings addSettings(Settings settings) {
        return settingsRepository.save(settings);
    }

    @Override
    public Settings updateSettings(String type) {
        Settings data = new Settings();
        Settings settings = settingsRepository.findByType(type);
        data.setCode(settings.getCode());
        data.setId(settings.getId());
        data.setLabel(settings.getLabel());
        data.setValue(settings.getValue());
        data.setType(settings.getType());
        return settingsRepository.save(data);
    }

    @Override
    public Settings getByCode(String code){
        return settingsRepository.findByCode(code);
    }
}
