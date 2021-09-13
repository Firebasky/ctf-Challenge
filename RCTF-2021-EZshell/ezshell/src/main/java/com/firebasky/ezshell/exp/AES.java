package com.firebasky.ezshell.exp;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    public static void main(String[] args) throws Exception {
        byte[] string = readFile("路径\\cmd.class");
        String k = "ZTQ1ZTMyOWZlYjVkOTI1Yg==";//key 密钥e45e329feb5d925b
        String encrypt = encrypt(k, string);
        System.out.println(encrypt);
    }
    public static byte[] readFile(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return buffer;
    }
    public static String encrypt(final String secret, final byte[] data) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        try {
            Cipher cipher = Cipher.getInstance("AES");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(data);
            return Base64.getEncoder().encodeToString(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while encrypting data", e);
        }
    }
    public static String decrypt(final String secret, final String encryptedString) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);

        try {
            Cipher cipher = Cipher.getInstance("AES");
            // rebuild key using SecretKeySpec
            SecretKey originalKey = new SecretKeySpec(Arrays.copyOf(decodedKey, 16), "AES");
            cipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] cipherText = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
            return new String(cipherText);
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occured while decrypting data", e);
        }
    }
}