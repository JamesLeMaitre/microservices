package com.esmc.gestionte.serviceinterface;


import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.entities.PassifPresentielleHistory;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.inputs.SelectPassifInput;
import com.esmc.input.KsuCheckInput;
import com.esmc.input.PassifCheckInput;
import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.List;

public interface PassifPresentielleInterface {

    List<PassifPresentielle> getAll();
    PassifPresentielle  getById(Long id);

    PassifPresentielle  create(PassifPresentielle data);
    PassifPresentielle  update(Long id, PassifPresentielle data);

    PassifPresentielle disable(Long id);

    PassifPresentielle enable(Long id);

    int getCountAll();

    void delete(Long id);

    PassifPresentielle getPassifPresentiellebyIdEmpreinte(String id) throws Exceptions;

    PassifPresentielle getPassifPresentiellebyNumeroRef(String numRef);

    PassifPresentielle getPassifPrentielleByCodeQrOrNumRef(String code);

    String checkPassifPrentielleByCodeQrOrNumRef(String codeQrOrNumRef, String numero);


    String checkPassifPrentielleTreatByKsu(Long idKsu);

    void disablePassifPresentielle(Long idKsu);

    String checkView(Long idBon);

    PassifPresentielle checkValidePassifGenerateActivateCode(Long idKsu, String code);

    PassifPresentielle getPassifPresentielleByvalReinitBCi(Double valBciReinit);

    PassifPresentielle getPassifPresentielleByNomAndPrenom(String nom, String prenom);

    PassifPresentielleHistory actionRequest(Long id, int stage, String numero) throws IOException, WriterException, Exceptions;

    String checkPassifPrentielleTreatByInfo(KsuCheckInput ksuCheckInput);

    String checkPassifPrentielleUseByCodeQrOrNumRef(String codeQrOrNumRef, String numero);

    PassifPresentielle checkExistPassifPresentielleByInfo(PassifCheckInput passifCheckInput);

    PassifPresentielle checkExistPassifPresentielleByInfoOk(PassifCheckInput passifCheckInput);

    PassifPresentielle selectPassifPresentielle(SelectPassifInput selectPassifInput);

    PassifPresentielle getSelectedPassifPresentielle(String code);
}
