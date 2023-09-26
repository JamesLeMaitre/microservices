package com.esmc.gestionAvr.tokens.entities;

import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
@AllArgsConstructor
public class Tokenization {
    private final KsuClient ksuClient;
    private final DetailAgrClient detailAgrClient;
    public String tokenization(String libelle, Long idKsu) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Récupération des détails de l'agrément et de l'ID KSU
        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idKsu);
        Ksu k = ksuClient.getById(detailsAgr.getKsu());

        // Timestamp actuel
        String time = System.currentTimeMillis()+"";

        // Construction de la chaîne de caractères à chiffrer
        String text = k.getId()+libelle+time;

        // Génération d'une clé de chiffrement aléatoire
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[16];
        secureRandom.nextBytes(keyBytes);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        // Initialisation du chiffrement
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Chiffrement de la chaîne de caractères
        byte[] encryptedText = cipher.doFinal(text.getBytes());
        return new String(encryptedText);
    }

}
