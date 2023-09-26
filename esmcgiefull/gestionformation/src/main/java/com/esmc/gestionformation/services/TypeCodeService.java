package com.esmc.gestionformation.services;

import com.esmc.gestionformation.entities.TypeCodes;
import com.esmc.gestionformation.repositories.TypeCodeRepository;
import com.esmc.gestionformation.serviceinterfaces.TypeCodeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TypeCodeService implements TypeCodeServiceInterface {

    @Autowired
    private TypeCodeRepository TypeCodesRepository;

    @Override
    public TypeCodes addTypeCodes(TypeCodes t) {
        return TypeCodesRepository.save(t);
    }

    @Override
    public TypeCodes updateTypeCodes(Long id, TypeCodes t) {
        TypeCodes code = TypeCodesRepository.findById(id).orElse(null);

        code.setLibelle(t.getLibelle());
        code.setDateUpdate(new Date());

        return TypeCodesRepository.save(code);
    }

    @Override
    public void deleteTypeCodes(Long id) {
        TypeCodesRepository.deleteById(id);
    }

    @Override
    public List<TypeCodes> listTypeCodes() {
        return TypeCodesRepository.findAll();
    }
}
