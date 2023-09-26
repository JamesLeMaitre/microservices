package com.esmc.gestionformation.serviceinterfaces;

import com.esmc.gestionformation.entities.Code;
import com.esmc.gestionformation.inputs.CodeInput;
import com.esmc.gestionformation.inputs.CodeView;
import com.esmc.gestionformation.inputs.ObjectInput;

import java.util.List;

public interface CodeServiceInterface {

    public Code addCode(Code c);
    public Code addCodev2(Code c);
    public Code updateCode(Long id, Code c);
    public void deleteCode(Long id);
    public List<Code> listCode();
    public List<Code> listCodeByType(Long idType);
    public List<Code> listCodeBySalle(Long idSalle);
    public List<Code> listCodeByTypeAndSalle(Long idType, Long idSalle);

    public Code getCodeByCode(String code);

    Code updateCodeListAdmin(Long idCode);

    public String getCodeByIdAgrIdDetAgr(Long idPoste, Long idAgr, Long idAgrFran) ;

    List<CodeView> getAll(Long id);

    Code checkExist(Code code);


    /* Registr Path*/

     Code checkInit(CodeInput code);

}
