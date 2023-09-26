package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.Settings;
import com.esmc.gestionAgr.fifo.entities.TerminalEchange;
import com.esmc.gestionAgr.fifo.repositories.TerminalEchangeRepository;
import com.esmc.gestionAgr.fifo.services.SettingsService;
import com.esmc.gestionAgr.fifo.services.TerminalEchangeService;
import com.esmc.gestionAgr.fifo.utils.SettingsConstant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TerminalEchangeImpl implements TerminalEchangeService {

    private final TerminalEchangeRepository terminalEchangeRepository;
    private final SettingsService settingsService;

    @Override
    public Double bcnrPrk(Double montant) {

        Settings stpck = settingsService.getSettingsById(SettingsConstant.PARAM_PCK);
        Settings stprk = settingsService.getSettingsById(SettingsConstant.PARAM_PRK);

        Double PCK = Double.parseDouble(stpck.getValue());
        Double PRK = Double.parseDouble(stprk.getValue());

        Double montantBCnr = montant * (PCK / PRK);

        return montantBCnr;
    }

    @Override
    public TerminalEchange getTeByDetailAgr(Long id) {
        return terminalEchangeRepository.findTerminalEchangeByDetailAgr(id);
    }
}
