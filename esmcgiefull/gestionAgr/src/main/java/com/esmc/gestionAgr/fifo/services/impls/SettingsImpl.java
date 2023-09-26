package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.Settings;
import com.esmc.gestionAgr.fifo.repositories.SettingsRepository;
import com.esmc.gestionAgr.fifo.services.SettingsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SettingsImpl implements SettingsService {
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
