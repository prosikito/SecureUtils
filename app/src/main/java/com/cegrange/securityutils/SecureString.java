package com.cegrange.securityutils;


import com.cegrange.securityutils.crypto.AES256PKCS7Padding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by cegrange on 18/08/2017.
 *
 */

public class SecureString {

    private String value;
    private String key;

    public SecureString(String key, String value)
            throws NoSuchPaddingException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        this.key   = key;
        this.value = encodeValue(value);
    }

    private String decodeValue() throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException {
        return AES256PKCS7Padding.decryptAES256PKCS7Padding(key, value);
    }

    private String encodeValue(String value)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            UnsupportedEncodingException, BadPaddingException,
            IllegalBlockSizeException, InvalidAlgorithmParameterException,
            InvalidKeyException {
        return AES256PKCS7Padding.encryptAES256PKCS7Padding(key, value);
    }

    public boolean isEmpty()
            throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        return decoded().isEmpty();
    }

    public boolean isEqualTo(SecureString secureString)
            throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IOException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        return secureString != null && secureString.decoded().equals(this.decoded());
    }

    public String decoded()
            throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IOException,
            BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return decodeValue();
    }

    public String encoded(){
        return value;
    }
}
