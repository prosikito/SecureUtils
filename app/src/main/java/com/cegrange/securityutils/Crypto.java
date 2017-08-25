package com.cegrange.securityutils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by cegrange on 18/08/2017.
 *
 */

class Crypto {

    private static final byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static final IvParameterSpec ivspec = new IvParameterSpec(iv);
    private static final String key = "1234567890123456";

    private Crypto(){
        // unused
    }

    private static SecretKey generateKey() throws UnsupportedEncodingException {
        return new SecretKeySpec(Arrays.copyOf(key.getBytes("UTF-8"), 16), "AES");    }

    static byte[] encode(String message)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        /* Encrypt the message. */
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    static String decode(byte[] cipherText)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        /* Decrypt the message, given derived encContentValues and initialization vector. */
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, generateKey());
        return new String(cipher.doFinal(cipherText), "UTF-8");
    }
}
