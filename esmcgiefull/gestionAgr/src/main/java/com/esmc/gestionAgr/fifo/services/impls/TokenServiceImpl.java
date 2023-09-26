package com.esmc.gestionAgr.fifo.services.impls;


import com.esmc.gestionAgr.fifo.models.TokenEntity;
import com.esmc.gestionAgr.fifo.repositories.ReferenceTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class TokenServiceImpl {

    private static final String PREFIX = "AQS-";
    private static final String SUFFIX = "-ESMC";
    private static final int KEY_SIZE = 16;
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY = "MaCléSecrete32B".getBytes();
    private static byte[] key;
    private final ReferenceTokenRepository referenceTokenRepository;

    public static String encrypt(String plaintext, int key) {

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            int charCode = c;
            charCode += key;
            ciphertext.append((char) charCode);
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            int charCode = c;
            charCode -= key;
            plaintext.append((char) charCode);
        }
        return plaintext.toString();
    }

    public static String convertDoubleToLetters(Double number) {

        double integerPart = Math.floor(number);
        double decimalPart = Math.abs(number - integerPart);

        String[] letters = {"A", "C", "D", "B", "E", "G", "F", "J", "I", "H", "Z"};
        String num = Double.toString(number);
        StringBuilder sb = new StringBuilder();
        for (char c : num.toCharArray()) {
            if (Character.isDigit(c)) {
                int digit = Integer.parseInt(String.valueOf(c));
                sb.append(letters[digit]);
            } else {
                sb.append(c);
            }
        }


        return sb.toString();
    }

    public static Double decryptLettre(String code) {
        String[] letters = {"A", "C", "D", "B", "E", "G", "F", "J", "I", "H", "Z"};


        String entierLettre = code.replaceAll("\\..*", "");
        String decimaLettre = code.replaceAll("^.*\\.", "");


        System.out.println("code " + code + " entierLettre " + entierLettre + " decimaLettre " + decimaLettre);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entierLettre.length(); i++) {
            char letter = entierLettre.charAt(i);
            for (int j = 0; j < letters.length; j++) {
                if (letters[j].equals(Character.toString(letter))) {
                    sb.append(j);
                    break;
                }

            }
        }

        StringBuilder sbD = new StringBuilder();

        for (int i = 0; i < decimaLettre.length(); i++) {
            char letter = decimaLettre.charAt(i);
            for (int j = 0; j < letters.length; j++) {
                if (letters[j].equals(Character.toString(letter))) {
                    sbD.append(j);
                    break;
                }

            }
        }
        System.out.println(" fin decimal " + sbD);


        return Double.valueOf(sb + "." + sbD);
    }

    private static byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[KEY_SIZE];
        random.nextBytes(key);
        return key;
    }

    private static byte[] concatenate(byte[]... data) {
        int length = 0;
        for (byte[] array : data) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int offset = 0;
        for (byte[] array : data) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    public String generateCode2(TokenEntity obj) {
        String date = String.valueOf(System.currentTimeMillis());
        String montantCrypter = convertDoubleToLetters(obj.getValue());
        String liblleCrypter = encrypt(obj.getLibelle(), 3);
        return PREFIX + String.format("%04d", obj.getIdKsu()) +
                "-AZ" + montantCrypter.replace(".", "M") +
                "-" + liblleCrypter + "-" + date + "-" + obj.getIdRefParent() + SUFFIX;
    }

    public TokenEntity setFieldsFromDecryptedString(String decryptedValue) {
        TokenEntity obj = new TokenEntity();
        // Utilisez la méthode split() pour séparer les champs de la chaîne déchiffrée
        String[] fields = decryptedValue.split("-", 6);
        obj.setIdKsu(Long.parseLong(fields[1]));

        String valAmount = fields[2];
        valAmount = valAmount.replaceAll("AZ", "");
        valAmount = valAmount.replace("M", ".");

        valAmount = String.valueOf(decryptLettre(valAmount));


        obj.setValue(Double.valueOf(valAmount));

        //obj.setValue(Double.parseDouble(su));
        String libelleDecrypter = decrypt(fields[3], 3);
        obj.setLibelle(libelleDecrypter);
        // Utilisez la classe LocalDateTime pour convertir la chaîne de timestamp en objet LocalDateTime

        String dateLocal = fields[4];
//        System.out.println("datttttttttt " + dateLocal);
        //dateLocal = dateLocal.replaceAll("-ESMC", "");
        obj.setTimestamp(dateLocal);

        String id = fields[5];

        id = id.replaceAll("-ESMC", "");
        System.out.println("iddddddddd " + id);


        if (id.equals("null")) {
            obj.setIdRefParent(0L);
        } else {
            obj.setIdRefParent(Long.valueOf(id));
        }

        return obj;
    }


}
