package com.esmc.demandeAchatBanKsu.controllers;



import com.esmc.demandeAchatBanKsu.entities.Errors;
import com.esmc.demandeAchatBanKsu.entities.MaBanKsu;
import com.esmc.demandeAchatBanKsu.exceptions.ErrorException;
import com.esmc.demandeAchatBanKsu.exceptions.ErrorExceptionHandle;
import com.esmc.demandeAchatBanKsu.repositories.ErrorRepository;
import com.esmc.demandeAchatBanKsu.servicesInterface.MaBanKsuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/MaBanKsu")
public class MaBanKsuController {

    @Autowired
    private MaBanKsuService maBanKsuService;
    @Autowired
    private  ErrorRepository errorRepository;

      @PostMapping("type_mabanksu/{id}/save")
    public Object savemabanKsu(@PathVariable Long id, @RequestBody MaBanKsu maBanKsu){
          try {
             return maBanKsuService.saveMabanKsu(id, maBanKsu);
          } catch (Exception handle){
              HttpStatus request = HttpStatus.BAD_REQUEST;
              StringWriter sw = new StringWriter();
              handle.printStackTrace(new PrintWriter(sw));
              String exceptionAsString = sw.toString();
              System.out.println(handle);
              Errors er = new Errors();
              er.setMessage(handle.getMessage());
              er.setHttpStatus(request);
              er.setZonedDateTime(ZonedDateTime.now(ZoneId.of("Z")));
              er.setThrowable(exceptionAsString);
              errorRepository.save(er);
              return handle;
          }
      /*  try {
            maBanKsuService.saveMabanKsu(id, maBanKsu);
        } catch (ErrorExceptionHandle handle){
            throw new ErrorExceptionHandle("SaveError");
        }
         */
        //throw new ErrorExceptionHandle("Eror Savez");
     //   try{
            //return maBanKsuService.saveMabanKsu(id, maBanKsu);
       // }catch (Exception e){

       // }

    }

    @GetMapping("/list")
    public ResponseEntity<List<MaBanKsu>>getmaBanKsu(){
        return new ResponseEntity<List<MaBanKsu>>(maBanKsuService.getMaBanKsu(),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public MaBanKsu mabanKsu(@PathVariable("id") Long id){
        return maBanKsuService.MaBanKsu(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MaBanKsu> updatemabanKsu(@PathVariable("id") Long id , @RequestBody MaBanKsu maBanKsu ){
        maBanKsu.setId(id);
        return new ResponseEntity<MaBanKsu>( maBanKsuService.updateMabanKsu(maBanKsu),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MaBanKsu>  deletemabanKsu(@PathVariable Long id ){
        maBanKsuService.deleteMabanksu(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("typeMaBanKsu/{id}")
    public MaBanKsu findMaBanKsuByTypeMaBaKsu(@PathVariable Long id) {
        return maBanKsuService.findMaBanKSUByTypeMaBanKSu(id);
    }

    @GetMapping("curentMaBanKsuId")
    public MaBanKsu getCurrent() {
        return maBanKsuService.getCurrentMaBanKsu();
    }

/*
    @GetMapping("/getBytype/{tpemabanksu_id}")
    public  String typeMaBanKSu(@PathVariable("tpemabanksu_id") String type){
        return  maBanKsuService.findMaBanKSUByTypeMaBanKSu(type);
    }*/

    @GetMapping("/code_representant/{code}")
    public MaBanKsu findMaBanKsuByCodeRepresentant(@PathVariable("code") String code) {
        return maBanKsuService.getMaBanKsuByCodeRepresentant(code);

    }
}
