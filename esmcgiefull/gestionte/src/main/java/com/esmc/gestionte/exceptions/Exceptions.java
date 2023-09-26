package com.esmc.gestionte.exceptions;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

public class Exceptions extends Exception{

    public Exceptions(String message) {
        super(message);
    }

    public static String alertGeneralException(String message){return message;}

    public static String codeSMNotFound(String codeSM){
        return "le codeSM :"+codeSM+" existe";
    }
}
