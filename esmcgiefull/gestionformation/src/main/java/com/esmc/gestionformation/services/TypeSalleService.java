package com.esmc.gestionformation.services;

import com.esmc.gestionformation.entities.TypeSalles;
import com.esmc.gestionformation.repositories.TypeSaleRepository;
import com.esmc.gestionformation.serviceinterfaces.TypeSalleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TypeSalleService implements TypeSalleServiceInterface {

    @Autowired
    private TypeSaleRepository typeSaleRepository;

    @Override
    public TypeSalles addTypeSalles(TypeSalles s) {
        return typeSaleRepository.save(s);
    }

    @Override
    public TypeSalles updateTypeSalles(Long id, TypeSalles s) {
        TypeSalles salle = typeSaleRepository.findById(id).orElse(null);

        salle.setLibelle(s.getLibelle());
        salle.setDateUpdate(new Date());
        return typeSaleRepository.save(salle);
    }

    @Override
    public void deleteTypeSalles(Long id) {

    }

    @Override
    public List<TypeSalles> listTypeSalles() {
        return typeSaleRepository.findAll();
    }
}
