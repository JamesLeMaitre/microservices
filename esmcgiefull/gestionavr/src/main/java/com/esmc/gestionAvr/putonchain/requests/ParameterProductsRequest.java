package com.esmc.gestionAvr.putonchain.requests;

import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParameterProductsRequest implements Serializable {
    private StockRoomType stockRoomType;
    private String wording;
    private Category category;
    private int minimumQuantity;
    private int maximumQuantity;
}
