package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.ProductRegistryValue;

import java.util.List;

public interface ProductRegisterServiceInterface {

    public List<ProductRegistryValue> listProduct();

    Double montantTotalProduit(Long idKsu);
}
