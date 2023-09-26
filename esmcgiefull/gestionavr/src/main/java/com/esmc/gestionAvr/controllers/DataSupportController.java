package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.DataSupport;
import com.esmc.gestionAvr.inputs.DsUpgrade13_10;
import com.esmc.gestionAvr.inputs.InputUpdateV2;
import com.esmc.gestionAvr.inputs.PageRequestInput;
import com.esmc.gestionAvr.servicesInterfaces.DataSupportServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/dataSupport/")
public class DataSupportController extends DataFormatter<DataSupport> {

    @Autowired
    private DataSupportServiceInterface dataSupportServiceInterface;

    @CacheEvict(value="datas", allEntries=true)
    @GetMapping("by/idDetailSupport/{id}")
    public Object getDataSupport(@PathVariable Long id) {
        try {
            DataSupport dataSupport = dataSupportServiceInterface.getDataSupportByDetailSupport(id);
            if(dataSupport == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, dataSupport,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @CachePut("listDatas")
    @GetMapping("list")
    public Object listDataSupportV1() {
        try {
            List<DataSupport> dataSupport = dataSupportServiceInterface.lisDataSupport();
            if(dataSupport == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderDataArray(true, dataSupport,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @Cacheable(value = "datas",key = "#id")
    @GetMapping("by/idDetailTypeSupport/{id}")
    public Object getDataTypeSupport(@PathVariable Long id) {
        try {
            DataSupport dataSupport = dataSupportServiceInterface.getDataSupportByDetailTypeSupport(id);
            if(dataSupport == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, dataSupport,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }


    @GetMapping("getListV2/{id}/{page}/{elm}")
    public Object getDataSupportByDetailTypeSupportv2(@PathVariable Long id,@PathVariable int page,@PathVariable int elm) {
        DataFormatter<Page<DataSupport>> df = new DataFormatter<>();
        try {
            Page<DataSupport> dataSupport = dataSupportServiceInterface.getDataSupportByDetailTypeSupportv2(id,page,elm);

            if(dataSupport.isEmpty()){
                return  renderStringData(false, "","Data not found");
            }
            return  df.renderData(true, dataSupport,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }



    @GetMapping("getListDataSupportByIdDetailTypeSupport/idDetailTypeSupport/{id}")
    public Object getDataTypeSupports(@PathVariable Long id) {
        try {
            List<DataSupport> oklm = dataSupportServiceInterface.getDataByDetailTypeSupport(id);
            if(oklm.isEmpty()){
                return  renderStringData(false, "","Data not found");
            }
            return  renderDataArray(true, oklm,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @Cacheable(value = "datas",key = "#id")
    @GetMapping("getListDataSupportByIdDetailTypeSupport/idDetailTypeSupportProd/{id}")
    public Object getDataByDetailTypeSupportV2(@PathVariable Long id) {
        DataFormatter<List<Object>> dt = new DataFormatter<>();
        try {
            List<Object> oklm = dataSupportServiceInterface.getDataByDetailTypeSupportV2(id);
            if(oklm.isEmpty()){
                return  renderStringData(false, "","Data not found");
            }
            return  dt.renderData(true, oklm,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @PutMapping("updateDataSupport/idDetailSupport/{id}")
    public Object updateDat(@PathVariable Long id, @RequestBody DataSupport data) {
        try {
            DataSupport dataSupport = dataSupportServiceInterface.updateDataSupport(id,data);
            if(dataSupport == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, dataSupport,"Update Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

   @PostMapping("upgrade-content")
    public Object upgrade_content(@RequestBody() PageRequestInput data) {
       DataFormatter<Page<DataSupport>> df = new DataFormatter<>();
        try {
            Page<DataSupport> ds = dataSupportServiceInterface.upgrade_content(data.getId(), data.getPage(), data.getElm());

            return  df.renderData(true,ds,"Update Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @GetMapping("supreme-validation/{id}")
    public Object supreme_validation(@PathVariable Long id) throws JsonProcessingException {
        try {
        DataFormatter<Object> dataFormatter = new DataFormatter<>();
            List<Object> dataSupportList = dataSupportServiceInterface.superValidation(id);
            return  dataFormatter.renderDataArray(true,dataSupportList ,"Supreme validation donne successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }


    @GetMapping("id/{id}")
    public Object setDataSupportAgentGeneralV1(@PathVariable Long id) {
        DataFormatter<JsonNode> dataFormatter = new DataFormatter<>();
        try {
            JsonNode oklm = dataSupportServiceInterface.setDataSupportAgentGeneral(id);
//            if(oklm.isEmpty()){
//                return  renderStringData(false, "","Data not found");
//            }
            return  dataFormatter.renderData(true, oklm,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @GetMapping("idv3/{id}/{data}")
    public Object getNumBon(@PathVariable Long id,@PathVariable("data")String data) {
        DataFormatter<JSONObject> dataFormatter = new DataFormatter<>();
        try {
            JSONObject oklm = dataSupportServiceInterface.returnNumbonCommande(id,data);
//            if(oklm.isEmpty()){
//                return  renderStringData(false, "","Data not found");
//            }
            return  dataFormatter.renderData(true, oklm,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @PutMapping("update-content")
    public Object updateDataSupportV2_ec(@RequestBody InputUpdateV2 data) {

        try {
            DataSupport oklm = dataSupportServiceInterface.updateDataSupportV2(data.getId(), data.getData());
//            if(oklm.isEmpty()){
//                return  renderStringData(false, "","Data not found");
//            }
            return  renderData(true, oklm,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @GetMapping("idv2/{id}")
    public Object setDataSupportAgentGeneralV2(@PathVariable Long id) throws JsonProcessingException {
        DataFormatter<Object> dataFormatter = new DataFormatter<>();
       // try {
        Object oklm = dataSupportServiceInterface.setDataSupportAgentGeneralV2(id);
//            if(oklm.isEmpty()){
//                return  renderStringData(false, "","Data not found");
//            }
            return  dataFormatter.renderData(true, oklm,"Operation Successfully ");
//        } catch (Exception e) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  "Error while processing "+e;
//        }
    }

    @PostMapping("update-datasupport")
    public Object upgrade_dt(@RequestBody DsUpgrade13_10 ds ) {
        try {
            DataSupport oklm = dataSupportServiceInterface.createAndUpdate(ds);
            if(oklm == null){
                return  renderStringData(false, "","ID DetailTypeSupport Not Found !");
            }
            return  renderData(true, oklm,"Operation Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }
}
