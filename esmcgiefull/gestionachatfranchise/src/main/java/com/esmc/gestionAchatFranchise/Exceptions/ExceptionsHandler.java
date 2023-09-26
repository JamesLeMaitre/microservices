package com.esmc.gestionAchatFranchise.Exceptions;

public class ExceptionsHandler extends Exception {

    public ExceptionsHandler (String messageHandler) {
        super(messageHandler);
    }

    public static String alertGeneralException(String messageHandler){return messageHandler;}

    public static String codeNotFound(String code){
        return "the purchase code :"+code+" exist";
    }
}
