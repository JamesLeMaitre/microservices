package com.esmcgie.services;

import com.esmcgie.models.ResponseTransaction;

public interface CorisService {
    String hashParameter(String param) throws Exception;

     String hashRequestParametersByWonder(String originalString) throws Exception; // doc update hash

    Object getClientInformation(String countryCode, String phone) throws Exception;

    ResponseTransaction transaction(String countryCode, String phone, String codePv, String withdrawalCode, String amount) throws Exception;

    ResponseTransaction payment(String countryCode, String phone, String codePv, String amount, String codeOTP ) throws Exception;
//    ResponseTransaction payment(String countryCode, String phone, String codePv, String amount , String codeOTP) throws Exception;

    void sendOTPCode(String countryCode, String phone) throws Exception;

    void getTransactionStatus(String codeOperation) throws Exception;
}
