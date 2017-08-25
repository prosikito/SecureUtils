package com.cegrange.securityutils;

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

    private byte[] value;

    public SecureString(String value) {
        encode(value);
    }

    public String toString(){
        return decode();
    }

    private String decode(){
        try {
            return Crypto.decode(value);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException |
                InvalidAlgorithmParameterException e) {
            Logger.log(e);
            return new String(value);
        }
    }

    private void encode(String value){
        try {
            this.value = Crypto.encode(value);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException |
                InvalidAlgorithmParameterException e) {
            Logger.log(e);
            this.value = value.getBytes();
        }
    }

    public boolean isEmpty(){
        return decode().isEmpty();
    }

    public boolean equals(SecureString secureString){
        return secureString.toString().equals(this.toString());
    }

    public String getValue(){
        return new String(value);
    }
}
