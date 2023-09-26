package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.DebitServiceInputRepo;
import com.esmc.gestionPayement.ServicesInterface.DebitServiceInputService;
import com.esmc.gestionPayement.entities.DebitServiceInput;
import com.esmc.gestionPayement.inputs.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class DebitServiceInputImp implements DebitServiceInputService {
    @Autowired
    private DebitServiceInputRepo debitServiceInputRepo;
    @Override
    public List<DebitServiceInput> getDebitServiceInput() {
        return debitServiceInputRepo.findAll();
    }

    @Override
    public DebitServiceInput DebitServiceInput(Long id) {
        return debitServiceInputRepo.findById(id).get();
    }

    @Override
    public DebitServiceInput saveDebitServiceInput(DebitServiceInput debitServiceInput) {
        return debitServiceInputRepo.save(debitServiceInput);
    }

    @Override
    public void deleteDebitServiceInput(Long id) {
        debitServiceInputRepo.deleteById(id);
    }

    @Override
    public void updateDebitServiceInput(Long id, DebitServiceInput debitServiceInput) {
        debitServiceInputRepo.save(debitServiceInput);
    }


    @Override
    public String hashFunc(Input pass) throws NoSuchAlgorithmException {
        String code = pass.getCodePays()+pass.getTelephone()+pass.getClientSecret();
        String a = crypt(code);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte hashBytes[] = messageDigest.digest(a.getBytes(StandardCharsets.UTF_8));
        BigInteger noHash = new BigInteger(1, hashBytes);
        String hashStr = noHash.toString(16);
        return hashStr;
    }

    public String crypt(String code){
        Date today = new Date();
        int currentHour = today.getHours();
     /*   Integer tip = Integer.parseInt(code);
        System.out.println(tip);*/
      //  String currrentTimestamp = tip+System.currentTimeMillis()+"";

        String[] Values = code.split("");
        String cryptData ="";
        int getRandomValue = (int) (Math.random()*(10-1)) + 1;

       // System.out.println("Random key :"+getRandomValue);

        for(String  c : Values){
            int value = Integer.parseInt(c);
            int result = value + 3;
            if(currentHour % 2 ==0){
                result =  value + 2;
            }
            if(result >=10){
                result -=10;
            }
            cryptData = cryptData + result ;
        }
        System.out.println("Chaine cryptée avant le changement :" +cryptData);

        String partOne;
        String partTwo;
        String partThree;
        if(getRandomValue <= 5){
            partOne = cryptData.substring(0, 3);
            partTwo = cryptData.substring(3, 10);
            partThree = cryptData.substring(10, cryptData.length());
            cryptData = partTwo+partThree+partOne;
            System.out.println("Chaine cryptée après le changement :" +cryptData);
        } else {
            partOne = cryptData.substring(0, 5);
            partTwo = cryptData.substring(5, 9);
            partThree = cryptData.substring(9, cryptData.length());
            cryptData = partThree +partOne+partTwo;
        }
        return getRandomValue+cryptData;
       // return "Cryptage: "+getRandomValue+cryptData+"  currentTimeStamp: "+currrentTimestamp;

    }
}
