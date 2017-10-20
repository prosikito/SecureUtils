package com.cegrange.securityutils.crypto;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by cegrange on 19/10/2017.
 *
 */

public class AES256PKCS7Padding {

    // MUST BE 16 CHARACTERS LONG
    private static final String TEXT_ENCODING = "UTF-8";
    private static final String ALGORITHM_ENCODING = "AES/CBC/PKCS7Padding";
    private static final String KEY_ENCODING = "AES";

    private AES256PKCS7Padding() {
        // hide public constructor
    }

    private static SecretKey generateKeyAES256(String key)
            throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
        if (key.getBytes().length != 32){
            throw new InvalidAlgorithmParameterException("Key length must be 32 bytes");
        }
        return new SecretKeySpec(key.getBytes(TEXT_ENCODING), KEY_ENCODING);
    }

    public static String encryptAES256PKCS7Padding(String key, String src)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            UnsupportedEncodingException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException {

        Cipher cipher = Cipher.getInstance(ALGORITHM_ENCODING);
        cipher.init(Cipher.ENCRYPT_MODE, generateKeyAES256(key),
                new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return Base64.encodeBytes(cipher.doFinal(src.getBytes()));
    }

    public static String decryptAES256PKCS7Padding(String key, String src)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM_ENCODING);
        cipher.init(Cipher.DECRYPT_MODE, generateKeyAES256(key),
                new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return new String(cipher.doFinal(Base64.decode(src)));
    }
}
