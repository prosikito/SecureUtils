package com.cegrange.securityutils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by cegrange on 18/08/2017.
 *
 */

class Crypto {

    private static final String KEY = "1234567890123456";
    private static final String ENCODING = "UTF-8";

    private Crypto(){
        // unused
    }

    private static SecretKey generateKey() throws UnsupportedEncodingException {
        return new SecretKeySpec(Arrays.copyOf(KEY.getBytes(ENCODING), 16), "AES");    }

    static byte[] encode(String message)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        /* Encrypt the message. */
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, generateKey());
        return cipher.doFinal(message.getBytes(ENCODING));
    }

    static String decode(byte[] cipherText)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        /* Decrypt the message, given derived encContentValues and initialization vector. */
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, generateKey());
        return new String(cipher.doFinal(cipherText), ENCODING);
    }
}
