package com.esmc.gestionAvr.entities;

import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExtrantIntrantResponse {

    private JSONObject recepteur;
    private JSONObject emetteur;
    private JSONObject bon;
    private JSONObject[] items;

}
