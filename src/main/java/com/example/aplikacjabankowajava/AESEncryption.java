package com.example.aplikacjabankowajava;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
public class AESEncryption {
    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";
    private static IvParameterSpec iv = null;
    static {
        try {
            iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private static SecretKeySpec skeySpec = null;
    static {
        try {
            skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public AESEncryption(){
    }
    public static String encryptData(String value) {
        try {
            final Cipher cipher =
                    Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            final byte[] encrypted = cipher.doFinal(value.getBytes());
            return java.util.Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String decryptData(String encrypted) {
        try {
            final Cipher cipher =
                    Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            final byte[] original =
                    cipher.doFinal(java.util.Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
