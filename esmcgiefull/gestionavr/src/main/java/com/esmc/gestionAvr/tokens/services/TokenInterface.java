package com.esmc.gestionAvr.tokens.services;

import com.esmc.gestionAvr.tokens.entities.Token;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface TokenInterface {
    Token addToken(String libelle, Long idksu, Double value) throws Exception;


    Token cumulToken(Token[] tokens) throws Exception;

    Token getById(Long id);
}
