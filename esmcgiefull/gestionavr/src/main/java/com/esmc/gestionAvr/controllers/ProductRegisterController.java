package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.ProductRegistryValue;
import com.esmc.gestionAvr.servicesInterfaces.ProductRegisterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.util.List;

@RestController
@RequestMapping("api/produits/")
public class ProductRegisterController extends DataFormatter<ProductRegistryValue> {

    @Autowired
    private ProductRegisterServiceInterface productRegisterServiceInterface;

    @GetMapping("listProduit")
    public Object listProductRegister() {
        try {
            List<ProductRegistryValue> items = productRegisterServiceInterface.listProduct();

            if (items.isEmpty()) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, items, "Opération éffectuer avec succès");

        } catch (Exception e) {
            return "Error while proccessing "+e;
        }
    }

    @GetMapping("totalProduct/ksu/{idKsu}")
    public Object totalProductRegister(@PathVariable("idKsu") Long idKsu) {
        try {
            Double items = productRegisterServiceInterface.montantTotalProduit(idKsu);
            return renderDoubleData(true, items, "Opération éffectuer avec succès");

        } catch (Exception e) {
            return "Error while proccessing "+e;
        }
    }

}
