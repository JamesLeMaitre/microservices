package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.feign.TypeSupportClient;
import com.esmc.gestionAvr.inputs.DataProuvedValue;
import com.esmc.gestionAvr.inputs.DsUpgrade13_10;
import com.esmc.gestionAvr.inputs.DtSupport;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.servicesInterfaces.DataSupportServiceInterface;
import com.esmc.models.SupportUtilise;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utiles.UseFullFunctions;

import java.util.*;
import java.util.regex.Pattern;

@Service
@Transactional
public class DataSupportService implements DataSupportServiceInterface {

    @Autowired
    private DataSupportRepostory dataSupportRepostory;

    @Autowired
    private DetailSupportRepository detailSupportRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    public UseFullFunctions useFullFunctions = new UseFullFunctions();
    @Autowired
    private DetailTypeSupportRepository detailTypeSupportRepository;

    @Autowired
    private TypeSmAvrRepository typeSmAvrRepository;

    @Autowired
    private TypeSupportClient tpClient;

    @Override
    public DataSupport addDataSupport(DataSupport dataSupport) {
        return dataSupportRepostory.save(dataSupport);
    }

    @Override
    public List<DataSupport> lisDataSupport() {
        return dataSupportRepostory.getDataSupportPage(PageRequest.of(10,150));
    }

    @Override
    public DataSupport getDataSupportByDetailSupport(Long id) {
        return dataSupportRepostory.getDataSupport(id);
    }

    @Override
    public DataSupport getDataSupportByDetailTypeSupport(Long id) {
        return dataSupportRepostory.getDataTypeSupport(id);
    }

    @Override
    public Page<DataSupport> getDataSupportByDetailTypeSupportv2(Long id, int page,int elm) {
        return dataSupportRepostory.getDataSupportByDetailTypeSupportPage(id,PageRequest.of(page,elm));
    }

    @Override
    public List<DataSupport> getDataByDetailTypeSupport(Long id) {
        // List<Long> detailTypeSupport = detailSupportRepository.getByIDV2(id);
        // List<DataSupport> listJames = new ArrayList<>();
        // for (Long kean : detailTypeSupport) {
        // DataSupport leBoss = dataSupportRepostory.getDataSupportV2(kean);
        // if(leBoss != null){
        // listJames.add(leBoss);
        // }
        // }
        return dataSupportRepostory.getListBon(id);
    }

    @Override
    public List<Object> getDataByDetailTypeSupportV2(Long id) throws JsonProcessingException {
        int i = 0;
        List<Object> dataSupportList = new ArrayList<>();

        do {
            Page<DataSupport> supports = dataSupportRepostory.getListBonV2(id, PageRequest.of(i + 1, 15));
            for (DataSupport dataSupport : supports) {
                String asStrMap = dataSupport.getData();
                ObjectMapper mapper = new ObjectMapper();
                DtSupport data = mapper.readValue(asStrMap, DtSupport.class);
                if (Objects.equals(data.getDataSupport().getIdBon(), "897efe6fdb3")) {
                    dataSupportList.add(data);

                }
            }
        } while (dataSupportList.isEmpty());
        return dataSupportList;
        // return dataSupportRepostory.getListBonV2(id,PageRequest.of(0,5));
    }

    @Override
    public List<Object> superValidation(Long id) throws JsonProcessingException {
        // On recupère le premier élément de la liste
        List<DataSupport> dataSupportList = dataSupportRepostory.getListBon(id).subList(0, 1);
        // Je déclare une liste vite auquelle j'affecterai les données
        List<Object> dtSupports = new ArrayList<>();
        // on boucle sur chaque DataSupport de la list içi le seule element de la list
        // recuperate
        for (DataSupport dataSupport : dataSupportList) {
            // Get DataSupport data as String
            String asStrMap = dataSupport.getData();
            ObjectMapper mapper = new ObjectMapper();
            // Read this data as JsonTree
            DtSupport data = mapper.readValue(asStrMap, DtSupport.class);
            // Here I get Technopole data cause of it's an array, so we loop on this
            DataProuvedValue[] dtas = data.getDataSupport().getDataApouved().getOperation();
            // dtSupports.add(data);
            Long countTechnopole = Arrays.stream(dtas).filter(x -> !x.getAprouve()).count();
            // Long countACNEV = Arrays.stream(dtas).filter(x-> !x.getAprouve()).count();
            System.out.println("COUNT APROUVE VALUE");
            System.out.println(countTechnopole);

            // We use switch Case to refer what we need to approuve
            for (DataProuvedValue dataProuvedValue : dtas) {
                // DataProuvedValue[] dataAll ;
                // dtSupports.add()
                if (Objects.equals(dataProuvedValue.getIdPost(), "1")) {
                    if (!dataProuvedValue.getAprouve()) {
                        dataProuvedValue.setAprouve(true);
                        String jsonString = mapper.writeValueAsString(data);
                        updateDataSupportV2(dataSupport.getId(), jsonString);
                    }
                } else if (Objects.equals(dataProuvedValue.getIdPost(), "24")) {
                    if (!dataProuvedValue.getAprouve()) {
                        dataProuvedValue.setAprouve(true);
                        String jsonString = mapper.writeValueAsString(data);
                        updateDataSupportV2(dataSupport.getId(), jsonString);

                    } // 33 -> Technopole
                } else if (Objects.equals(dataProuvedValue.getIdPost(), "257")) {
                    if (!dataProuvedValue.getAprouve()) {
                        dataProuvedValue.setAprouve(true);
                        String jsonString = mapper.writeValueAsString(data);
                        updateDataSupportV2(dataSupport.getId(), jsonString);
                        dtSupports.add(dataSupport);
                    }
                }

            }
        }
        return dtSupports;
    }

    @Override
    public DataSupport updateDataSupport(Long id, DataSupport data) {
        DataSupport dataSupport = dataSupportRepostory.findById(id).orElse(null);
        assert dataSupport != null;
        dataSupport.setId(dataSupport.getId());
        dataSupport.setData(data.getData());
         dataSupport.setDetailSupport(data.getDetailSupport());
        dataSupport.setDetailTypeSupport(data.getDetailTypeSupport());
        return dataSupportRepostory.save(dataSupport);

    }

    @Override
    public DataSupport updateDataSupportV2(Long id, String data) {
//        System.out.println("ID UP");
//        System.out.println(id);
        DataSupport dataSupport = dataSupportRepostory.findById(id).orElse(null);
//        System.out.println("======ID============");
        assert dataSupport != null;
//        System.out.println(dataSupport.getId());
//        System.out.println(dataSupport.getId());
        dataSupport.setId(dataSupport.getId());
        dataSupport.setData(data);
        // dataSupport.setDetailSupport(data.getDetailSupport());
        // dataSupport.setDetailTypeSupport(data.getDetailTypeSupport());
        return dataSupportRepostory.save(dataSupport);

    }


    @Override
    public DataSupport updateDataSupportV3(Long id, String data, Long idTypeSupport) {
//        System.out.println("ID UP");
//        System.out.println(id);
        DataSupport dataSupport = dataSupportRepostory.findById(id).orElse(null);
        if(idTypeSupport > 12L){
            SupportUtilise supportUtilise = tpClient.getSupportUtlise(idTypeSupport);
            DetailSupport detailSupport = new DetailSupport();
            detailSupport.setIdTypeSupport(idTypeSupport);
            detailSupport.setLibelleTypeSupport(supportUtilise.getLibelle());
            detailSupport.setCode(supportUtilise.getCode());

            DetailSupport detailSupport1 = detailSupportRepository.save(detailSupport);
            assert dataSupport != null;
            dataSupport.setDetailSupport(detailSupport1);
        } else {
            TypeSmAvr typeSmAvr = typeSmAvrRepository.findById(idTypeSupport).orElse(null);

            DetailTypeSupport detailTypeSupport = new DetailTypeSupport();
            assert typeSmAvr != null;
            detailTypeSupport.setIdTSupport(typeSmAvr.getId());
            detailTypeSupport.setLibelleTypeSupport(typeSmAvr.getLibelle());
            detailTypeSupport.setCode(typeSmAvr.getCode());

            DetailTypeSupport detailTypeSupport1 = detailTypeSupportRepository.save(detailTypeSupport);
            assert dataSupport != null;
            dataSupport.setDetailTypeSupport(detailTypeSupport1);
        }
//        System.out.println("======ID============");
//        System.out.println(dataSupport.getId());
//        System.out.println(dataSupport.getId());
        dataSupport.setId(dataSupport.getId());
        dataSupport.setData(data);


        // dataSupport.setDetailSupport(data.getDetailSupport());
        // dataSupport.setDetailTypeSupport(data.getDetailTypeSupport());
        return dataSupportRepostory.save(dataSupport);

    }

    @Override
    public Page<DataSupport> upgrade_content(Long id, int page,int elm) throws Exception {
        // bon de livraison
          return upgrade_data_support_bon_livraison( id,page,elm);
    }

    @Override
    public JsonNode setDataSupportAgentGeneral(Long id) throws JsonProcessingException {
        DataSupport dataSupport = getDataSupportByDetailSupport(id);
        String asStrMap = dataSupport.getData();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(asStrMap);

    }

    @Override
    public Object setDataSupportAgentGeneralV2(Long id) throws JsonProcessingException {
        DataSupport dataSupport = getDataSupportByDetailSupport(id);
        String asStrMap = dataSupport.getData();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(asStrMap);
        JsonNode base64 = node.at("/dataSupport/infoBonLivraison/numBonCommande");
        System.out.println("=======");
        // System.out.println(base64);
        System.out.println("=======");
        // return getKeysInJsonUsingJsonNodeFieldNames(asStrMap,mapper);
        return base64;

    }

    @Override
    public JSONObject returnNumbonCommande(Long id, String data) throws JsonProcessingException {
        List<DataSupport> dataSupportList = getDataByDetailTypeSupport(id);

        JSONObject jsonObject = new JSONObject();
        List<JsonNode> strings = new ArrayList<>();
        for (DataSupport dataSupport : dataSupportList) {
            String asStrMap = dataSupport.getData();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(asStrMap);
            // String st = data.toLowerCase().trim();

            JsonNode nomVendeur = node.at("/dataSupport/infoBonLivraison/acteurVendeur");
            JsonNode nomVend = nomVendeur;
            String cd = nomVend.toString().substring(1, nomVend.toString().length() - 1);
            if (nomVend.equals(cd)) {
                JsonNode key = node.at("/dataSupport/infoBonLivraison/numBonCommande");
//                System.out.println("======");
//                System.out.println(key);
//                System.out.println("======");
                jsonObject.put(data, key);
            }
            JsonNode base64 = node.at("/dataSupport/infoBonLivraison/numBonCommande");
            strings.add(base64);
        }
        return jsonObject;
    }

    @Override
    public DataSupport getById(Long id) {
        return dataSupportRepostory.findById(id).orElse(null);
    }

    @Override
    public DataSupport createAndUpdate(DsUpgrade13_10 ds) {
        DetailTypeSupport detailTypeSupport = detailTypeSupportRepository.findById(ds.getIdDetailTypeSupport()).orElse(null);

        DataSupport dataSupport = new DataSupport();
        dataSupport.setDetailTypeSupport(detailTypeSupport);
        dataSupport.setData(ds.getData());
        return dataSupportRepostory.save(dataSupport);
    }

    public List<String> getKeysInJsonUsingJsonNodeFieldNames(String json, ObjectMapper mapper)
            throws JsonMappingException, JsonProcessingException {

        List<String> keys = new ArrayList<>();
        JsonNode jsonNode = mapper.readTree(json);
        Iterator<String> iterator = jsonNode.fieldNames();
        iterator.forEachRemaining(e -> keys.add(e));
        return keys;
    }
    /*
    *===================================ATTACHEMENT FILES===============================
    * Update file : 106 :25
     */



    public void registerFile(DataSupport da) throws Exception {
        // System.out.println(da);
        String asStrMap = da.getData();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(asStrMap);
        String base64 = node.get("dataSupport").get("preuve").toPrettyString();

        long currenttime =  System.nanoTime();
        String[] strings = base64.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        String fileimage="pic_"+currenttime;


       Attachment attachment = new Attachment(fileimage+"."+extension, "image/jpeg");
        Attachment savedAttachment = attachmentRepository.save(attachment);

        String folder=savedAttachment.getId();

        String newPicture =  this.useFullFunctions.getNewUploadImageByBase64(base64,folder,fileimage);
        //System.out.println("picture nama");
        System.out.println(newPicture);
        String newData = asStrMap.replaceAll(Pattern.quote(base64), '"'+folder+'"');
       // System.out.println(newData);
        da.setData(newData);
        dataSupportRepostory.save(da);

    }

    public Page<DataSupport> upgrade_data_support_bon_livraison(Long id, int page,int elm) throws Exception {
      // List<DataSupport> dataSupportList = dataSupportRepostory.findAll().subList(0,1);
       Page<DataSupport> dataSupportList = getDataSupportByDetailTypeSupportv2(id,page,elm);
        for (DataSupport da: dataSupportList) {
             try {
                registerFile(da);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        Page<DataSupport> ds = getDataSupportByDetailTypeSupportv2(id,page,elm);
        return ds;
    }

//    public void upgrade_data_support_bon_livraison(Long id) throws Exception {
//        // List<DataSupport> dataSupportList = dataSupportRepostory.findAll().subList(0,1);
//        DataSupport dataSupportList = getDataSupportByDetailTypeSupportv2(id,page,elm);
//
//        for (DataSupport da: dataSupportList) {
//            try {
//                registerFile(da);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//
//        }
//        Page<DataSupport> ds = getDataSupportByDetailTypeSupportv2(id,page,elm);
//        return ds;
//    }
}
