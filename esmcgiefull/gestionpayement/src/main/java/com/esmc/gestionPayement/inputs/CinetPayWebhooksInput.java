package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinetPayWebhooksInput {
    private String cpm_trans_id;
    private  String cpm_site_id ;
}
