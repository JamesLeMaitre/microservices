package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.Code;
import com.esmc.gestionformation.feign.AgrRestClient;
import com.esmc.gestionformation.feign.KsuRestClient;
import com.esmc.gestionformation.inputs.CodeInput;
import com.esmc.gestionformation.inputs.CodeView;
import com.esmc.gestionformation.serviceinterfaces.CodeServiceInterface;
import com.esmc.gestionformation.serviceinterfaces.RegistreServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;
import utiles.Messagerie;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping("api/codes/")
public class CodeController extends Messagerie {

    @Autowired
    private CodeServiceInterface codeServiceInterface;

    @Autowired
    private RegistreServiceInterface registreServiceInterface;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Autowired
    private AgrRestClient agrRestClient;

    DataFormatter<Code> formatter = new DataFormatter<>();

    @PostMapping("save")
    public Object addCode(@RequestBody Code c) {

        try {

           Code cod = codeServiceInterface.addCode(c);

            DetailsAgr detailsAgr = agrRestClient.findDetailAgrById(cod.getIdAgr());

            Ksu k = ksuRestClient.getById(detailsAgr.getKsu());
            System.out.println(k);

            String message = "Votre code d'accès à la Salle de Formation est "+cod.getCode();

//            sendSms(k.getTelephone(), message);
//            sendMail(k.getEmail(), "ESMC GIE", message);

            return formatter.renderData(true, cod, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*Path for Fanuel & Merveil Franchise*/
    @PostMapping("savev2")
    public Object addCodev2(@RequestBody Code c) {

        try {

            Code cod = codeServiceInterface.addCodev2(c);

            DetailsAgr detailsAgr = agrRestClient.findDetailAgrById(cod.getIdAgr());

            Ksu k = ksuRestClient.getById(detailsAgr.getKsu());
            System.out.println(k);

            String message = "Votre code d'accès à la Salle de Formation est "+cod.getCode();

            sendSms(k.getTelephone(), message);
//            sendMail(k.getEmail(), "ESMC GIE", message);

            return formatter.renderData(true, cod, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping("update/{id}")
    public Object updateCode(@PathVariable Long id, @RequestBody Code c) {
        try {
            return formatter.renderData(true,  codeServiceInterface.updateCode(id, c), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @DeleteMapping("delete/{id}")
    public void deleteCode(@PathVariable Long id) {
        codeServiceInterface.deleteCode(id);
    }

    @GetMapping("list")
    public Object listCode() {

        try {
            return formatter.renderDataArray(true, codeServiceInterface.listCode(), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("list/by/type/{idType}")
    public Object listCodeByType(@PathVariable Long idType) {

        try {
            return formatter.renderDataArray(true, codeServiceInterface.listCodeByType(idType), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list/by/salle/{idSalle}")
    public Object listCodeBySalle(@PathVariable Long idSalle) {

        try {
            return formatter.renderDataArray(true, codeServiceInterface.listCodeBySalle(idSalle), "Opperation Successiful") ;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("list/by/type/{idType}/salle{idSalle}")
    public Object listCodeByTypeAndSalle(@PathVariable Long idType, @PathVariable Long idSalle) {

        try {
            return  formatter.renderDataArray(true,codeServiceInterface.listCodeByTypeAndSalle(idType, idSalle), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }



    @PostMapping("getCode")
    public Object getCodeByCode(@RequestBody CodeInput data) {

        try {
            Code c = codeServiceInterface.getCodeByCode(data.getCode());

            if(c == null) {
                return formatter.renderStringData(false, "", "Not found");
            }
            if(c.isEtat()){
                return formatter.renderStringData(false, "Code already used !", "Operation Successful");
            }
            return formatter.renderData(true, c, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("getMyCurrentAccessCode")
    public Object getCodeByID(@RequestBody CodeInput data) {

        try {
            String c = codeServiceInterface.getCodeByIdAgrIdDetAgr(data.getIdPoste(),data.getIdAgr(),data.getIdDetailAgrFranchiser());

            if(c == null) {
                return formatter.renderStringData(false, "", "Not found");
            }
//            if(c.isEtat()){
//                return formatter.renderStringData(false, "Code already used !", "Operation Successful");
//            }
            return formatter.renderStringData(true, c, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("getAllByFranchise/idDetailsAgrFranchise/{idDetailAgrFranchise}")
    public Object getAll(@PathVariable("idDetailAgrFranchise") Long  id) {
        DataFormatter<CodeView> dataFormatter=new DataFormatter<CodeView>();
        try {
            List<CodeView> codeView = codeServiceInterface.getAll(id);
            if(codeView == null) {
                return formatter.renderStringData(false, "", "No Candidate for this Franchise");
            }
            return dataFormatter.renderDataArray(true, codeView, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("updateCode/idCode/{idCode}")
    public Object updateView(@PathVariable("idCode") Long  id) {

        try {
           Code codeView = codeServiceInterface.updateCodeListAdmin(id);
            if(codeView == null) {
                return formatter.renderStringData(false, "", "This Code don't exist !");
            }
            return formatter.renderData(true, codeView, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
    Registre Path
     */

    @PostMapping("checkCodeInitRegistre")
    public Object checkInitRegistry(@RequestBody CodeInput data) {
        try {
            Code c = codeServiceInterface.checkInit(data);
            if(c == null) {
                return formatter.renderStringData(false, "", "Operation Not Done");
            }
            if(registreServiceInterface.checkExist(data)!= null){
                return formatter.renderData(true, c, "Operation Successful");
            }
           return formatter.renderStringData(false,"","Data already initialize !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  formatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}
