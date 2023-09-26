package com.esmc.gestionformation.exceptions;

public class Exceptions extends Exception {

    public Exceptions(String message) {
        super(message);
    }

    public static String alertGeneralException(String message){return message;}

    public static String libelleNotFound(String libelle){
        return "libelle :"+libelle+" existe";
    }



}
