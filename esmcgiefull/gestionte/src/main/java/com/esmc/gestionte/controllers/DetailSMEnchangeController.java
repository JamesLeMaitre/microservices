package com.esmc.gestionte.controllers;


import com.esmc.gestionte.constant.DetailTotal;
import com.esmc.gestionte.entities.DetailSMEnchange;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.feign.AchatKsuClientRest;
import com.esmc.gestionte.feign.CertificationRestClient;
import com.esmc.gestionte.feign.PayementRestClient;
import com.esmc.gestionte.inputs.PayerVendeurKsu;
import com.esmc.gestionte.request.ApproRequest;
import com.esmc.gestionte.request.DetailSMEnchangeRequest;
import com.esmc.gestionte.serviceinterface.DetailSMEnchangeService;
import com.esmc.input.CheckTraitementInput;
import com.esmc.models.CheckTraitement;
import com.esmc.models.Formatter;
import com.esmc.models.Settings;
import com.google.zxing.WriterException;
import constants.AccountConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.IOException;
import java.util.List;

import static constants.ControllerMessage.SUCCESS_MESSAGE;



/**
 * @author Amemorte
 * @since 05/05/2022
 */

@RestController
@RequestMapping(value="api/detailSMEnchange/")
public class DetailSMEnchangeController extends DataFormatter<DetailSMEnchange> {


    @Autowired
    private DetailSMEnchangeService detailSMEnchangeImpl;

    @Autowired
    private PayementRestClient payementRestClient;

    @Autowired
    private AchatKsuClientRest achatKsuClientRest;

    @Autowired
    private CertificationRestClient certificationRestClient;


    @GetMapping("detail_agr/{id}")
    public List<DetailSMEnchange> listDetailSMEnchangeParTe(@PathVariable Long id) throws Exceptions, IOException, WriterException{
        return  detailSMEnchangeImpl.listDetailSmeByTe(id);
    }

    @PostMapping("change/selected/status/{selectElementId}")
    public void changeSelectDetailSMEnchange(@PathVariable("selectElementId") Long selectElementId) throws Exceptions, IOException, WriterException{
         detailSMEnchangeImpl.changeSelectDetailSMEnchange(selectElementId);
    }

    @GetMapping("change/correct-change-selected/status/{selectElementId}/{status}")
    public void useChangeSelectedElement(@PathVariable("selectElementId") Long selectElementId, @PathVariable("status") int status) throws Exceptions, IOException, WriterException{
        Boolean stat = false;
        if(status == 1){
            stat=true;
        }
        detailSMEnchangeImpl.changeStatuSelectDetailSMEnchange(selectElementId, stat);
    }


    @GetMapping("list/by/te/{idTe}")
    public Formatter<List<DetailSMEnchange>> listDetailSMEnchangeByTeAndSupportExchange(@PathVariable Long idTe) throws Exceptions, IOException, WriterException {
        DataFormatter renderer  = new DataFormatter<List<DetailSMEnchange>>();
        return   renderer.renderDataArray(true,detailSMEnchangeImpl.listDetailSMEnchangeByTeAndSupportExchange(idTe),"list of details");
    }


    @GetMapping("getAll")
    public List<DetailSMEnchange> getDetailSMEnchange(){
        return  detailSMEnchangeImpl.getDeatilSMEnchange();
    }



    @PutMapping("update/{id}")
    public ResponseEntity<?> updateDetailSMEnchange(@PathVariable("id") Long id,@RequestBody DetailSMEnchange detailSMEnchange ){
        try {
            detailSMEnchangeImpl.updateDetailSMEnchange(id,detailSMEnchange);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDetailSMEnchange(@PathVariable ("id") Long id){
        try {
            detailSMEnchangeImpl.deleteDetailSMEnchange(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            detailSMEnchangeImpl.getById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exceptions e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @GetMapping("encaissement/{montant}/type/{type}/teEn/{teEn}")
    public void registerEncaissement(@PathVariable ("montant") Double montant,@PathVariable ("type") String type,@PathVariable ("teEn") Long teEn ) throws IOException, WriterException {
        try {
            detailSMEnchangeImpl.encaissement(montant,type,teEn);
            //return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
           // return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    @GetMapping("encaissement/{montant}/type/{type}/teEnv/{teEnv}")
    public ResponseEntity<?> registerNewEncaissement(@PathVariable ("montant") Double montant,@PathVariable ("type") String type,@PathVariable ("teEnv") Long teEn ) throws IOException, WriterException {
        try {
            detailSMEnchangeImpl.encaissementVide(montant,type,teEn);
            return new ResponseEntity<>("Opération éffectuée avec succès.", HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    @GetMapping("decaissement/{montant}/type/{type}/teDe/{teDe}")
    public void registerNewDecaissement(@PathVariable ("montant") Double montant,@PathVariable ("type") String type,@PathVariable ("teDe") Long te) throws IOException, WriterException {
        try {
            detailSMEnchangeImpl.decaissement(montant,type,te);
            //return new ResponseEntity<>(Util.SUCCES_MESSAGE, HttpStatus.OK);
        } catch (Exceptions e) {
            //return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
    @GetMapping("echange/{montant}/type/{type}/teEn/{teEn}/teDe/{teDe}")
    public ResponseEntity<?> echange(@PathVariable ("montant") Double montant,@PathVariable ("type") String type,@PathVariable ("teEn") Long teEn,@PathVariable ("teDe") Long teDe) throws IOException, WriterException {
        try {
            detailSMEnchangeImpl.enchange(montant,type,teEn,teDe);
            return new ResponseEntity<>("Opération éffectuée avec succès.", HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @GetMapping("cumule-amount-byksu/type/{type}/te/{te}")
    public Double cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(@PathVariable ("type") String type,@PathVariable ("te") Long te) throws IOException, WriterException {
        try {
            return detailSMEnchangeImpl.cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(type,te);
        } catch (Exceptions e) {
            return 0.0;
        }
    }

    @GetMapping("renitialisationPassif/montant/{montant}/idTe/{idTe}")
    public DetailSMEnchange renitialisationPassif(@PathVariable ("montant") Double montant,@PathVariable ("idTe") Long idTe) throws IOException, WriterException, Exceptions {

        DetailSMEnchange aDouble = detailSMEnchangeImpl.renitialisationPassif(montant, idTe);
        return aDouble;

    }

    @GetMapping("renitialisationBCI/montant/{montant}/idTe/{idTe}")
    public DetailSMEnchange renitialisationBCI(@PathVariable ("montant") Double montant,@PathVariable ("idTe") Long idTe) throws IOException, WriterException, Exceptions {

        DetailSMEnchange aDouble = detailSMEnchangeImpl.renitialisationBCI(montant, idTe);

        if(aDouble == null) {
            return null;
        }

        return aDouble;

    }

    @GetMapping("listbcireinitialiser/{id}")
    public List<DetailSMEnchange> listBciReinitialiser(@PathVariable Long id) throws Exceptions, IOException, WriterException {
        return detailSMEnchangeImpl.listBciReinitialiser(id);
    }
    @GetMapping("listDSMEByIdTeAndSM/{idTe}/supportSME/{supportSME}")
    public List<DetailSMEnchange> listDSMEByIdTeAndSM(@PathVariable("idTe") Long idTe,@PathVariable("supportSME") Long supportSME) throws Exceptions {
        return detailSMEnchangeImpl.listDSMEByIdTeAndSM(idTe,supportSME);
    }

    @GetMapping("approvisionnement/montant/{montant}/idTe/{idTe}")
    public ResponseEntity<?> approvisionnementBAn(@PathVariable("montant") Double montant,@PathVariable("idTe") Long idTe) throws Exceptions, IOException, WriterException {

        try {
            return new ResponseEntity<>(detailSMEnchangeImpl.approvisionnement(montant,idTe), HttpStatus.OK);
        } catch (Exceptions e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @GetMapping("listDetailSmeByTe/{id}")
    public List<DetailSMEnchange> listDetailSmeByTe(@PathVariable Long id) throws Exceptions, IOException, WriterException {
        return detailSMEnchangeImpl.listDetailSmeByTe(id);
    }

    @GetMapping("sommeTotalBySME/{idTe}")
    public DetailTotal [] sommeTotal(@PathVariable("idTe") Long idTE) throws Exceptions {
        return detailSMEnchangeImpl.sommeTotalByIdTe(idTE);
    }

    @GetMapping("MutationMPRgBAnEntrerM/{montant}/idTe/{idTe}")
    public ResponseEntity<Double> findTElokByIdDetailAgr(@PathVariable ("montant") Double MPRgBAn,@PathVariable ("idTe") Long idTe) throws Exceptions {

        try {

            return new ResponseEntity<Double>(detailSMEnchangeImpl.MutationMPRgBAnEntrerM(MPRgBAn,idTe),HttpStatus.OK);
        }catch (Exceptions | IOException | WriterException e){
            return new ResponseEntity<Double>(HttpStatus.CONFLICT);
        }

    }

    @GetMapping("te/{idTe}/montant/{montant}")
    public Object mutationProcessusMevBan(@PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) throws Exceptions, IOException, WriterException {
       DetailSMEnchange item = detailSMEnchangeImpl.mutationProcessusMevBan(idTe, montant);

       if (item == null) {
           return renderStringData(false, "", "Not Found");
       } else  {
           return renderData(true, item, "Operation successed");
       }

    }

    @GetMapping("bpsd_ev_Blgcsc/{idTe}/montant/{montant}")
    public void generatedCircuitMevBGCs(@PathVariable("idTe") Long idTe, @PathVariable("montant")  Double montant) throws Exceptions, IOException, WriterException {
        detailSMEnchangeImpl.generatedCircuitMevBGCs(idTe, montant);
    }


    @GetMapping("bpsd_ev/{idTransaction}")
    public Object gennerateMev(@PathVariable("idTransaction") Long idTransaction) throws Exceptions, IOException, WriterException {

        DetailSMEnchange item = detailSMEnchangeImpl.gennerateMev(idTransaction);

        if (item == null) {
            return renderStringData(false, "", "Not Found");
        }

        return renderData(true, item, "Opperation succed  ") ;
    }

    @GetMapping("mutation_bpsd_ev_maban/admin/id/{idSm}/te/{idTe}/montant/{montant}")
    public Object mutationProcessusMevMABAn(@PathVariable("id") Long idSm, @PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) throws Exceptions, IOException, WriterException {
        DetailSMEnchange itm =  detailSMEnchangeImpl.mutationProcessusMevMABAn(idSm, idTe, montant);
        if (itm == null) {
            return renderStringData(false, "Le SM n'est pas disponible", "Not found");
        }
        return renderData(true, itm, "Success");
    }

    @GetMapping("mutation_bpsd_ev_maban_normal/id/{id}/te/{idTe}/montant/{montant}")
    public Object mutationProcessusMevMABAnNormal(@PathVariable("id") Long id, @PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) throws Exceptions, IOException, WriterException {
        DetailSMEnchange itm =  detailSMEnchangeImpl.mutationProcessusMevMABAnNormal(id, idTe, montant);
        if (itm == null) {
            return renderStringData(false, "Le SM n'est pas disponible", "Not found");
        }
        return renderData(true, itm, "Success");
    }

    @GetMapping("mutation_fifo_acheteur/te_acheteur/{idTeAcheteur}/te_vendeur/{idTeVendeur}/montant/{montant}")
    public Boolean mutationPourAchatFifo(@PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant) throws Exceptions, IOException, WriterException {
        DetailSMEnchange itm = detailSMEnchangeImpl.mutationPourAchatFifo(idTeAcheteur, idTeVendeur, montant);
        if (itm == null) {
            return false;
        }
        return true;
    }

    @GetMapping("mutation_fifo_acheteur_admin/te_acheteur/{idTeAcheteur}/te_vendeur/{idTeVendeur}/montant/{montant}")
    public Boolean mutationPourAchatFifoAdmin(@PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant) throws Exceptions, IOException, WriterException {
        DetailSMEnchange itm = detailSMEnchangeImpl.mutationPourAchatFifoAdmin(idTeAcheteur, idTeVendeur, montant);
        if (itm == null) {
            return false;
        }
        return true;
    }

    @GetMapping("mutation_blgcp_opi/te/{idTe}/id/{id}/montant/{montant}")
    public void mutationProcessusBLGCpOPI(@PathVariable("idTe") Long idTe, @PathVariable("id") Long id, @PathVariable("montant") Double montant) throws Exceptions, IOException, WriterException {
        detailSMEnchangeImpl.mutationProcessusBLGCpOPI(idTe, id, montant);
    }

    @GetMapping("check_total_sm/te/{idTe}/montant/{montant}")
    public Object checkSmFondsEntre(@PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) {
       return   detailSMEnchangeImpl.checkSmFondsEntre(idTe, montant);
    }

    @GetMapping("listdSMFondsDisponible/sm/{idSm}/te/{idTe}")
    public List<DetailSMEnchange> listdSMFondsDisponible(@PathVariable("idSm") Long idSm, @PathVariable("idTe") Long idTe) {
        return detailSMEnchangeImpl.listdSMFondsDisponible(idSm, idTe);
    }

    @GetMapping("achat_desendettement/te/{idTe}")
    public Boolean achatDesendettement(@PathVariable("idTe") Long idTe) throws Exceptions, IOException, WriterException {
       DetailSMEnchange itm = detailSMEnchangeImpl.achatDesendettement(idTe);
        if (itm == null) {
            return false;
        }
        return true;

    }

    @GetMapping("achat_franchise_normal/te/{idTe}/id_sm/{idSm}/code/{code}")
    public Object achatFranchiseNormalByBciBlgp(@PathVariable("idTe") Long idTe, @PathVariable("idSm") Long idSm, @PathVariable("code") String code) throws Exceptions, IOException, WriterException {
        DetailSMEnchange itm = detailSMEnchangeImpl.achatFranchiseNormalByBciBlgp(idTe, idSm, code);

        if (itm == null) {
            return renderStringData(false, "" , "SM Value Insufficient");
        }
        return renderData(true, itm, "Operation Successful");

    }

    @GetMapping("amount/te/{idTe}")
    public Double amountBciRiTe(@PathVariable("idTe") Long idTe) {
       return  detailSMEnchangeImpl.amountBciTe(idTe);
    }

    @GetMapping("montant_total_sm/sm/{idSm}/te/{idTe}")
    public Double checkSmTotalCurrent(@PathVariable("idSm") Long idSm, @PathVariable("idTe") Long idTe) throws Exceptions, IOException, WriterException {
       return detailSMEnchangeImpl.checkSmTotalCurrent(idSm, idTe);

    }

    @GetMapping("amount_produit/te/{idTe}")
    public Double sommeSm(@PathVariable("idTe") Long idTe) {
        return  detailSMEnchangeImpl.sommeSm(idTe);
    }

    @GetMapping("prelevent_produit/te/{idTe}/montant/{montant}")
    public DetailSMEnchange mutationFondDisponibleUsed(@PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) {
        return   detailSMEnchangeImpl.mutationFondDisponibleUsed(idTe, montant);
    }

    @GetMapping("decaissement_mprg/{idTrans}")
    public Object mutationMPRgOPIBpsdv(@PathVariable("idTrans") Long idTrans)  throws Exceptions, IOException, WriterException {

        DetailSMEnchange item = detailSMEnchangeImpl.mutationMPRgOPIBpsdv(idTrans);

        if (item == null) {
            return renderStringData(false, "", "Not Found");
        }
        return  renderData(true, item, "Opperation Succed");
    }

    @GetMapping("muttation_bcnr_prk_7/te/{idTe}/id_sm/{idSm}/montant/{montant}")
    public Object mutationProcessusBCnrPrk7(@PathVariable("idTe") Long idTe, @PathVariable("idSm") Long idSm, @PathVariable("montant") Double montant)  throws Exceptions, IOException, WriterException {

        DetailSMEnchange item = detailSMEnchangeImpl.mutationProcessusBCnrPrk7(idTe, idSm, montant);

        if (item == null) {
            return renderStringData(false, "", "Valeur Bcnr Insuffisant");
        }
        return  renderData(true, item, "Opperation Succed");
    }


    @GetMapping("montant_approvisionnement_ban/te/{idTe}/id_sm/{idSm}/code/{code}")
    public Object checkSmTotal(@PathVariable("idTe") Long idTe, @PathVariable("idSm") Long idSm, @PathVariable("code") String code)  throws Exceptions, IOException, WriterException {

        Formatter<Settings> setting = payementRestClient.getSettingUsedByTe(code);

        Settings ban = setting.getData();

        Double montant = Double.parseDouble(ban.getValue());

        boolean item = detailSMEnchangeImpl.checkSmTotal(idTe, idSm, montant);


        if (!item) {
            return renderStringData(false, "", "Valeur SM Insuffisant");
        }
        return  renderStringData(true, "", "Valeur SM disponible");
    }

    @GetMapping("list_bpsd/te/{idTe}")
    public Object listDetailSm(@PathVariable("idTe") Long idTe) {
        try {
            List<DetailSMEnchange> items = detailSMEnchangeImpl.listDetailSm(idTe);

            if (items.size() == 0) {
                return renderStringData(false, "", "Liste vide");
            }
            return  renderDataArray(true, items, "Opperation Successiful");
        } catch (Exception e) {
          return  "Error while proccessing"+e;
        }
    }

    @GetMapping("montant_ban/te/{idTe}/id_sm/{idSm}/montant/{montant}")
    public Object mutationProcessusBan(@PathVariable("idTe") Long idTe, @PathVariable("idSm") Long idSm, @PathVariable("montant") Double montant)  throws Exceptions, IOException, WriterException {

      try {
          DetailSMEnchange item = detailSMEnchangeImpl.mutationProcessusBan(idTe, idSm, montant);

          if (item == null) {
              return renderStringData(false, "", "Valeur SM Insuffisant");
          }
          return  renderStringData(true, "", "Valeur SM disponible");
      } catch (Exception e) {
          return "Error processing"+e;
      }

    }

    @GetMapping("muttattion_general/te/{idTe}/idSmMuttant/{idSmMuttant}/idSmMutter/{idSmMutter}/montant/{montant}")
    public Object mutationSMProcessus(@PathVariable("idTe") Long idTe, @PathVariable("idSmMuttant") Long idSmMuttant,  @PathVariable("idSmMutter") Long idSmMutter, @PathVariable("montant") Double montant)  throws Exceptions, IOException, WriterException {

        try {
            DetailSMEnchange item = detailSMEnchangeImpl.mutationSMProcessus(idTe, idSmMuttant, idSmMutter, montant);

            if (item == null) {
                return renderStringData(false, "", "Valeur SM Insuffisant");
            }
            return  renderData(true, item, "Operation Successful");
        } catch (Exception e) {
            return "Error processing"+e;
        }

    }

    @PostMapping("mutation_general")
    public Object mutationSMProcessus(@RequestBody() DetailSMEnchangeRequest dRe) throws Exception {
      //  try {
            DetailSMEnchange item = detailSMEnchangeImpl.mutationSMProcessus(dRe.getIdTe(), dRe.getIdSmMuttan(), dRe.getIdSmMutter(), dRe.getMontant());

            if (item == null) {
                return renderStringData(false, "", "Valeur SM Insuffisant");
            }
            return  renderData(true, item, "Operation Successifully");
       // } catch (Exception e) {
          //  return "Error processing "+e;
      //  }

    }

    @GetMapping("list_sm/idSm/{idSm}/te/{idTe}")
    public Object listSm(@PathVariable("idSm") Long idSm, @PathVariable("idTe") Long idTe) {
        try {
            List<DetailSMEnchange> items = detailSMEnchangeImpl.listSm(idSm, idTe);

            if (items.size() == 0) {
                return renderStringData(false, "", "La liste es vide");
            }

            return renderDataArray(true, items, "Opperation Successiful");
        } catch (Exception e) {
            return  "Error while processing"+e;
        }
    }

    @GetMapping("depot_interim/idSm/{idSm}/te/{idTe}/montant/{montant}")
    public Object depotAInterim(@PathVariable("idSm") Long idIdSm, @PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) {
        try {
            Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

            DetailSMEnchange items = detailSMEnchangeImpl.depotAInterim(idIdSm, idTe, admin, montant);

            if (items == null) {
                return renderStringData(false, "", "Valeur de SM non disponible !");
            }
            return renderData(true, items, "Operation Successful");
        } catch (Exceptions  | IOException | WriterException w) {
            return "error processing";
        }
    }

    @GetMapping("depuis_interim_vers_destinantaire/idSm/{idSm}/te/{idTe}/montant/{montant}")
    public Object sendToReceipient(@PathVariable("idSm") Long idIdSm, @PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) {
        try {
            Long admin = AccountConstant.TeEsmcGieFranchiseZeroOBPS;

            DetailSMEnchange items = detailSMEnchangeImpl.depotAInterim(idIdSm, admin, idTe, montant);

            if (items == null) {
                return renderStringData(false, "", "Valeur de SM non disponible !");
            }
            return renderData(true, items, "Operation Successful");
        } catch (Exceptions  | IOException | WriterException w) {
            return "error processing";
        }
    }

    @GetMapping("sommeTotalSm/sm/{idSm}/te/{idTe}")
    public Double sommeTotalSm(@PathVariable("idSm") Long idSm,  @PathVariable("idTe") Long idTe) {
        return detailSMEnchangeImpl.sommeTotalSm(idSm, idTe);

    }

    @GetMapping("listSmMuter/te/{idTe}/sm/{idSm}")
    public Object List(@PathVariable("idSm") Long idSm, @PathVariable("idTe") Long idTe) {
        try {
            List<DetailSMEnchange> items = detailSMEnchangeImpl.listSmMuter(idTe,idSm);
            return renderDataArray(true, items, "Operation Successful");
        } catch  (Exception w) {
            return "error processing";
        }
    }

    @PostMapping("approvisionnement/BAn")
    public Object supply(@RequestBody() ApproRequest data) throws Exception {
    try {
         boolean res =  detailSMEnchangeImpl.supply(data.getIdTEAcheteur(), data.getIdSMVendeur(),
                data.getIdTEVendeur(), data.getIdSMAcheteur(), data.getMontant());
        if (!res) {
            return renderStringData(false, "", "Valeur de SM non disponible");
        }
        return  renderStringData(true, "Is Zooooooo", "Operation Successfully");
         } catch (Exception e) {
          return "Error processing "+e;
         }
    }

    @GetMapping("debitSm/sm/{idSm}/idTeAcheteur/{idTeAcheteur}/idTeVendeur/{idTeVendeur}/montant/{montant}")
    public Object debitSM(@PathVariable("idSm") Long idSm, @PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant) {
        try {
            DetailSMEnchange items = detailSMEnchangeImpl.debitSm(idSm, idTeAcheteur, idTeVendeur, montant);
            if (items == null) {
                return renderStringData(false, "", "Data not found");
            }
            return renderData(true, items, "Operation Successful");
        } catch  (Exception w) {
            return "error processing";
        }
    }

    @GetMapping("debitSm_pour_achat_ksu/sm/{idSm}/montant/{montant}/refer/{refer}")
    public Object debitSMPourAchatKsu(@PathVariable("idSm") Long idSm,  @PathVariable("montant") Double montant, @PathVariable("refer") Long refer) {
        try {
            DetailSMEnchange items = detailSMEnchangeImpl.achatCertifSmAchatKsu(idSm, montant, refer);
            if (items == null) {
                return renderStringData(false, "", "Data not found");
            }
            return renderData(true, items, "Operation Successful");
        } catch  (Exception w) {
            return "error processing";
        }
    }


    @PostMapping("payer_certification")
    public Object payerCert(@RequestBody() CheckTraitementInput data) {
        try {
            DetailSMEnchange items = detailSMEnchangeImpl.achatCertifSm(data.getIdSm(), data.getIdTeAcheteur(), data.getIdTeVendeur(), data.getMontant());
            DataFormatter<CheckTraitement> dataFormatter = new DataFormatter<>();

            if (items == null) {
                return renderStringData(false, "", "Valeur de SM non disponible !");
            }
            dataFormatter.renderData(true,certificationRestClient.savecheck(data),SUCCESS_MESSAGE);
            return renderData(true, items, SUCCESS_MESSAGE);
        } catch (Exceptions  | IOException | WriterException w) {
            return "error processing";
        }
    }

    @PostMapping("payentVendeurKsu")
    public Object payentVendeurKsu(@RequestBody PayerVendeurKsu data)  {
        try {
            DetailSMEnchange items = detailSMEnchangeImpl.payementVandeurKsu(data);
            if (items == null) {
                return renderStringData(false, "", "Data not found");
            }
            return renderData(true, items, "Operation Successful");
        } catch  (Exception w) {
            return "error processing "+w;
        }
    }


    @GetMapping("appro_ban_by_passif/idte/{idTe}/type/{type}")
    public Object approBanParPassif(@PathVariable("idTe") Long idTe, @PathVariable("type") int type)  {
        try {
            DetailSMEnchange item = detailSMEnchangeImpl.approBAnByPassif(idTe, type);

            if(item == null) {
                return renderStringData(false, "", "Data not found");
            }
            return renderData(true, item, "Opération éffectuer avec succès");
        } catch (Exceptions | IOException | WriterException exception) {
            return renderStringData(false, "", "Error while proccessing "+exception);
        }
    }

}