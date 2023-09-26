package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.DataSupport;
import com.esmc.gestionAvr.inputs.DsUpgrade13_10;
import com.esmc.gestionAvr.inputs.DtSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Page;


import java.util.List;

public interface DataSupportServiceInterface {

    public DataSupport addDataSupport(DataSupport dataSupport);

    public List<DataSupport> lisDataSupport();
     DataSupport getDataSupportByDetailSupport(Long id);
     DataSupport getDataSupportByDetailTypeSupport(Long id);

    Page<DataSupport> getDataSupportByDetailTypeSupportv2(Long id, int page,int elm);

    List<DataSupport> getDataByDetailTypeSupport(Long id);

    List<Object> getDataByDetailTypeSupportV2(Long id) throws JsonProcessingException;

    List<Object> superValidation(Long id) throws JsonProcessingException;

    DataSupport updateDataSupport(Long id, DataSupport data);

    DataSupport updateDataSupportV2(Long id, String data);

    DataSupport updateDataSupportV3(Long id, String data, Long idTypeSupport);

    Page<DataSupport> upgrade_content(Long id, int page, int elm) throws Exception;

    JsonNode setDataSupportAgentGeneral(Long id) throws JsonProcessingException;

    Object setDataSupportAgentGeneralV2(Long id) throws JsonProcessingException;

    JSONObject returnNumbonCommande(Long id, String data) throws JsonProcessingException;

    DataSupport getById(Long id);
    DataSupport createAndUpdate(DsUpgrade13_10 ds);
}
