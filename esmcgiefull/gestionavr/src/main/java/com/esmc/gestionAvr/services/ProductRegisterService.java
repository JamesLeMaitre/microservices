package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.ProductRegistryValue;
import com.esmc.gestionAvr.repositories.ProductRegisterRepository;
import com.esmc.gestionAvr.repositories.ProductRegistryValueRepository;
import com.esmc.gestionAvr.servicesInterfaces.ProductRegisterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductRegisterService implements ProductRegisterServiceInterface {

    @Autowired
    private ProductRegisterRepository productRegisterRepository;

    @Autowired
    private ProductRegistryValueRepository productRegistryValueRepository;

    @Override
    public List<ProductRegistryValue> listProduct() {
        return productRegisterRepository.findAll();
    }

    @Override
    public Double montantTotalProduit(Long idKsu) {
        return productRegistryValueRepository.amountBciAvailable(idKsu);
    }
}
