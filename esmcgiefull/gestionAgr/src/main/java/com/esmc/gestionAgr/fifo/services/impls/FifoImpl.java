package com.esmc.gestionAgr.fifo.services.impls;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Vague;
import com.esmc.gestionAgr.fifo.repositories.FifoRepository;
import com.esmc.gestionAgr.fifo.repositories.VagueRepository;
import com.esmc.gestionAgr.fifo.services.FifoService;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FifoImpl implements FifoService {
    private final FifoRepository fifoRepository;
    private final VagueRepository vagueRepository;

    @Override
    public void setNumOrder(Vague vague, KsuType ksuType) {
        int count = findAllByKsuType(ksuType).size();
        switch (vague.getLabel()) {
            case "Vague1" -> {
                if (vague.getStatus()) {
                    if (KsuType.SELLER == ksuType) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fifo : fifos) {
                            fifo.setNumOrdre(count--);
                            fifoRepository.save(fifo);
                        }
                    } else if (ksuType == KsuType.BUYER) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fi : fifos) {
                            fi.setNumOrdre(count--);
                            fifoRepository.save(fi);
                        }
                    }
                }
            }
            case "Vague2" -> {
                if (!vague.getStatus()) {
                    if (KsuType.SELLER == ksuType) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fifo : fifos) {
                            fifo.setNumOrdre(count--);
                            fifoRepository.save(fifo);
                        }
                    } else if (ksuType == KsuType.BUYER) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fi : fifos) {
                            fi.setNumOrdre(count--);
                            fifoRepository.save(fi);
                        }
                    }
                }
            }
        }

    }

    public int setNextNumOrdreVente() {
        int last = fifoRepository.dernierFifoVente();
        int NextNum = 1;
        if (last != 0) {
            NextNum = last + 1;
        }
        return NextNum;
    }

    public int setNextNumOrdreAchat() {
        int last = fifoRepository.dernierFifoAchat();
        int NextNum = 1;
        if (last != 0) {
            NextNum = last + 1;
        }
        return NextNum;
    }


    @Override
    public List<Fifo> findAllByKsuType(KsuType ksuType) {
        return fifoRepository.findAllByKsuTypeAndTreated(ksuType, false);
    }

    @Override
    public Fifo getFifoById(Long id) {
        return fifoRepository.findFifoById(id);
    }

    //    public int setNextNumOrdre(KsuType ksuType){
//        return fifoRepository.getLastFifoByKsuTypeAndPanierTourIsFalse(ksuType)
//                .stream().findFirst()
//                .map(Fifo::getNumOrdre)
//                .orElse(0) + 1;
//    }
    public int setNextNumOrdre(KsuType ksuType) {
        List<Fifo> lastFifos = fifoRepository.getLastFifoByKsuTypeAndPanierTourIsFalse(ksuType);
        return lastFifos.isEmpty() ? 1 : lastFifos.get(lastFifos.size() - 1).getNumOrdre() + 1;
    }

}
