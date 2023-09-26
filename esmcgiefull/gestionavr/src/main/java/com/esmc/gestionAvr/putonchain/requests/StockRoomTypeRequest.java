package com.esmc.gestionAvr.putonchain.requests;

import com.esmc.gestionAvr.putonchain.entities.CenterType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRoomTypeRequest implements Serializable {
    private String wording;

    private CenterType centerType;
}
