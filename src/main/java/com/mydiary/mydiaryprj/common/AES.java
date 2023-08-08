package com.mydiary.mydiaryprj.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

//암호화를 복호화 하기위해서
public class AES {

    private static final String UTF_8 = "UTF-8";
    private static final String AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
    private static final String AES = "AES";

    public static String encrypt(final String value, final String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, getKeySpec(key), getParameterSpec());

            byte[] encryptedBytes = cipher.doFinal(value.getBytes(UTF_8));

            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt(final String value, final String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5PADDING);
            cipher.init(Cipher.DECRYPT_MODE, getKeySpec(key), getParameterSpec());

            byte[] encryptedBytes = Base64.getDecoder().decode(value);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes, UTF_8);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static Key getKeySpec(final String key) {
        try {
            return new SecretKeySpec(key.getBytes(UTF_8), AES);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static AlgorithmParameterSpec getParameterSpec() {
        try {
            return new IvParameterSpec(new byte[16]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
