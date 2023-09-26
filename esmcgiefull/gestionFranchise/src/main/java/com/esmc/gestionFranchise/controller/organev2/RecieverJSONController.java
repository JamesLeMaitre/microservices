package com.esmc.gestionFranchise.controller.organev2;

import com.esmc.gestionFranchise.datafomater.AgentFichePosteJSON;
import com.esmc.gestionFranchise.datafomater.ImportData;

import com.esmc.gestionFranchise.datafomater.PosteTypeStructureJson;
import com.esmc.gestionFranchise.services.organev2.ReceiverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utiles.DataFormatter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


@RestController
@RequestMapping("api/seeder/")
public class RecieverJSONController  extends DataFormatter<Object> {

    @Autowired
    private ReceiverServiceImpl receiverService;

    @PostMapping("load/data")
    public Object loadResource(@RequestBody ImportData<AgentFichePosteJSON[]> data) throws IOException {
       // try {
            receiverService.loadReceiverJSON(data.getData());
            return "Init Data";
           // return  renderStringData(true,"init successfully","Done");
       /* } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }*/
    }

    @PostMapping("typeSupport/load/data")
    public Object loadTypeSupportResource(@RequestBody ImportData<String[]> data) throws IOException {

        try {
            receiverService.loadtypeSupportReceiverJSON(data.getData());
            return  renderStringData(true,"init successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("posteTypeSupport/load/data")
    public Object loadPosteTypeSupportResource(@RequestBody ImportData<PosteTypeStructureJson[]> data) throws IOException {

        try {
            receiverService.loadPosteTypeSupportResource(data.getData());
            return  renderStringData(true,"init successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("loadPosteSupportResourceJson/data")
    public Object loadPosteSupportResourceJson(@RequestBody ImportData<AgentFichePosteJSON[]> data) throws IOException {



        try {
            receiverService.loadPosteSupportResourceJson(data.getData());
            return  renderStringData(true,"init successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }

}
