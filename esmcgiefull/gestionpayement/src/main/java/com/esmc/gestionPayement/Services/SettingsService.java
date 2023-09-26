package com.esmc.gestionPayement.Services;
import com.esmc.gestionPayement.Repositories.SettingsRepo;
import com.esmc.gestionPayement.ServicesInterface.SettingsServiceInterface;
import com.esmc.gestionPayement.entities.Settings;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.inputs.SettingsInput;
import com.esmc.models.SupportMarchandEnchage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SettingsService implements SettingsServiceInterface {

    @Autowired
    private SettingsRepo settingsRepository;

    @Autowired
    private TeRestClient teRestClient;


    @Override
    public List<Settings> getAll() {
        return settingsRepository.findAll();
    }

    @Override
    public Settings getSettingsById(Long id) {
        return settingsRepository.getSettingsById(id);
    }

    @Override
    public Settings getSettingsByCode(String code) {
        return  settingsRepository.getSettingsByCode(code);
    }

    @Override
    public Settings createSettings(SettingsInput data) {
        Settings settings = new Settings();
        settings.setCode(data.getCode());
        settings.setType(data.getType());
        settings.setLabel(data.getLabel());
        settings.setValue(data.getValue());
        return settingsRepository.save(settings);
    }

    @Override
    public Settings updateSettings(Long id, SettingsInput data) {
        Settings settings = settingsRepository.getSettingsById(id);
        if(data.getCode() != null){
            settings.setCode(data.getCode());
        }
        if(data.getType() != null){
            settings.setType(data.getType());
        }
        if(data.getLabel() != null){
            settings.setLabel(data.getLabel());
        }
        if(data.getValue() != null){
            settings.setValue(data.getValue());
        }

        return settingsRepository.save(settings);
    }

    @Override
    public void generateSeeds() {
        List<SupportMarchandEnchage> supportMarchandEnchageList = teRestClient.listsupportMarchand();
        String info ="";
        for (SupportMarchandEnchage s : supportMarchandEnchageList){
            Settings settings = new Settings();
            String code ="PARAM_"+s.getLibelle().replaceAll (" ","_");
            settings.setLabel(s.getLibelle());
            settings.setValue("1");
            settings.setCode(code);
            Settings settings1 = settingsRepository.save(settings);
            info += "public static final Long "+code+" = "+settings1.getId()+"";
        }

        System.out.println(info);
    }


}
