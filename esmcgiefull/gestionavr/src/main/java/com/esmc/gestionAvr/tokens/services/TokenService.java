package com.esmc.gestionAvr.tokens.services;

import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.tokens.entities.Token;
import com.esmc.gestionAvr.tokens.repositories.TokenRepository;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class TokenService implements TokenInterface {

    private final TokenRepository repository;
    private final KsuClient ksuClient;
    private final DetailAgrClient detailAgrClient;

    @Override
    public Token addToken(String libelle, Long idDetailAgr, Double value) throws Exception {
        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgr);
        Ksu k = ksuClient.getById(detailsAgr.getKsu());
        String token = tokenization(libelle, idDetailAgr, value);
        Token token1 = new Token();
        token1.setValue_token(value);
        token1.setIdKsu(k.getId());
        token1.setTokens("ESMC-"+token);
        token1.setLibelle(libelle);
        log.info("decrypt : {}",token);
        return repository.save(token1);
    }

    public String tokenization(String libelle, Long idDetailAgr, Double value) throws  Exception {
        // Récupération des détails de l'agrément et de l'ID KSU
        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgr);
        Ksu k = ksuClient.getById(detailsAgr.getKsu());

        // Timestamp actuel
//        String time = System.currentTimeMillis() + "";

        // Construction de la chaîne de caractères à chiffrer
        String text = k.getId() + libelle + value;

//        // Génération d'une clé de chiffrement aléatoire
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] keyBytes = new byte[16];
//        secureRandom.nextBytes(keyBytes);
//        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
//
//        // Initialisation du chiffrement
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//        // Chiffrement de la chaîne de caractères
//        byte[] encryptedText = cipher.doFinal(text.getBytes());
//        return new String(encryptedText);


        long timestamp = Instant.now().getEpochSecond();
        String keyString = Long.toString(timestamp);
        byte[] keyBytes = keyString.getBytes();

        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        keyBytes = sha.digest(keyBytes);
        keyBytes = Arrays.copyOf(keyBytes, 16);
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal((text + " " + timestamp).getBytes());
        return new String(encryptedBytes);
    }




        public static String decrypt(String encryptedText) throws Exception {
            // Récupération de l'horodatage de la fin de la chaîne chiffrée
            byte[] encryptedBytes = encryptedText.getBytes();
            long timestamp = Long.parseLong(new String(encryptedBytes).trim().split(" ")[1]);
            String keyString = Long.toString(timestamp);
            byte[] keyBytes = keyString.getBytes();
            // Génération de la clé de déchiffrement à partir de l'horodatage
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            keyBytes = sha.digest(keyBytes);
            keyBytes = Arrays.copyOf(keyBytes, 16);
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            // Initialisation du déchiffrement
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            // Déchiffrement de la chaîne de caractères
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            // Affichage des résultats
            return new String(decryptedBytes).split(" ")[0];
        }


    public <T> T getFirstElement(List<T> listElements) {
        T element = null;
        if (listElements.size() > 0) {
            element = listElements.get(0);
        }
        return element;
    }

    @Override
    public Token cumulToken(Token[] tokens) throws Exception {
        List<Token> tokenList = new ArrayList<>();
        List<String> libelles = new ArrayList<>();
        for (Token token : tokens) {
            Token token1 = repository.getByTokens(token.getTokens());
            token1.setState(true);
            tokenList.add(token);
            libelles.add(token.getLibelle());
        }
        Token t = new Token();
        t.setValue_token(tokenList.stream().mapToDouble(Token::getValue_token).sum());
        t.setIdKsu(getFirstElement(tokenList).getIdKsu());
        t.setLibelle(libelles.toString());
        t.setTokens(tokenization(tokenList.stream().map(Token::getTokens).toString(), getFirstElement(tokenList).getIdKsu(), tokenList.stream().mapToDouble(Token::getValue_token).sum()));
        return repository.save(t);
    }

    @Override
    public Token getById(Long id) {
        return repository.findById(id).orElse(null);
    }


}
