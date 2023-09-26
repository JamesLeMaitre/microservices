package com.esmc.gestionPayement.ServicesInterface;

import com.esmc.gestionPayement.entities.Settings;
import com.esmc.gestionPayement.inputs.SettingsInput;

import java.util.List;

public interface SettingsServiceInterface {

    List<Settings> getAll();

    Settings getSettingsById(Long id);

    Settings getSettingsByCode(String code);

    Settings createSettings(SettingsInput data);

    Settings updateSettings(Long id, SettingsInput data);

    void generateSeeds();
}
