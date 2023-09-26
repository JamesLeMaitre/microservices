package com.esmcgie.services.impls;

import com.esmcgie.models.RequestOptions;
import com.esmcgie.models.ResponseTransaction;
import com.esmcgie.services.CorisService;
import com.esmcgie.utils.CorisBaseService;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class CorisServiceImpl extends CorisBaseService implements CorisService {

    private final RequestOptions requestOptions;

    /**
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public String hashParameter(String param) throws Exception {
        return hashRequestParameters(param);
    }

    @Override
    public String hashRequestParametersByWonder(String originalString) throws Exception {
        String sha256hex = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();
        return sha256hex;
    }


    /**
     * @param countryCode
     * @param phone
     * @return
     */
    @Override
    public Object getClientInformation(String countryCode, String phone) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        return getClientInfo(requestOptions.getClientId(), requestOptions.getClientSecret(), map);
    }

    /**
     * @param countryCode
     * @param phone
     * @param codePv
     * @param withdrawalCode
     * @param amount
     * @return
     * @throws Exception
     */
    @Override
    public ResponseTransaction transaction(String countryCode, String phone, String codePv, String withdrawalCode, String amount) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        map.put("codePv", codePv);
        map.put("withdrawalCode", withdrawalCode);
        map.put("amount", amount);
        return internetPayment(requestOptions.getClientId(), requestOptions.getClientSecret(), map);
    }

    /**
     * @param countryCode
     * @param phone
     * @param codePv
     * @param amount
     * @return
     * @throws Exception
     */
    @Override
    public ResponseTransaction payment(String countryCode, String phone, String codePv, String amount , String codeOTP) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        map.put("codePv", codePv);
        map.put("amount", amount);
        map.put("codeOTP",codeOTP);
        return paymentOfProperty(requestOptions.getClientId(), requestOptions.getClientSecret(), map);
    }

    /**
     * @param countryCode
     * @param phone
     * @throws Exception
     */
    @Override
    public void sendOTPCode(String countryCode, String phone) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        setOTPCode(requestOptions.getClientId(), requestOptions.getClientSecret(), map);
    }

    @Override
    public void getTransactionStatus(String codeOperation) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("codeOperation",codeOperation);
        setTransactionStatus(requestOptions.getClientId(),requestOptions.getClientSecret(),map);
    }
}
