package com.esmc.gestionAvr.putonchain.requests;

import com.esmc.gestionAvr.putonchain.entities.ParameterProducts;
import com.esmc.gestionAvr.putonchain.entities.Products;
import com.esmc.gestionAvr.putonchain.entities.SpaceType;
import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGoingOnChainRequest implements Serializable {
    private ParameterProducts parameterProducts;
    private StockRoomType stockRoomType;
    private SpaceType spaceType;
    private Products products;
    private Long idDetailAgr;
    private int quantity;
}
