package com.cegrange.securityutils;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class SecureStringTest {

    // SECURESTRING USES AES256PKCS7Padding WITH 32 BYTE KEY
    private static final String KEY = "12345678901234567890123456789012";
    private static final String WRONG_KEY = "1234567890123456";

    @Test
    public void encryptionIsCorrect()
            throws NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String value = "dummy test";
        String encoded = new SecureString(KEY, value).encoded();
        assertNotEquals(value, encoded);
    }

    @Test
    public void decryptionIsCorrect()
            throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String value = "dummy test";
        String decoded = new SecureString(KEY, value).decoded();
        assertEquals(value, decoded);
    }

    @Test
    public void equalsIsCorrect()
            throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecureString secureString1 = new SecureString(KEY, "dummy test");
        SecureString secureString2 = new SecureString(KEY, "dummy test");
        assertTrue(secureString1.isEqualTo(secureString2));
    }

    @Test
    public void notEqualsIsCorrect()
            throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecureString secureString1 = new SecureString(KEY, "dummy test");
        SecureString secureString2 = new SecureString(KEY, "different dummy test");
        assertFalse(secureString1.isEqualTo(secureString2));
    }

    @Test
    public void isEmptyIsCorrect()
            throws NoSuchPaddingException, IOException, InvalidAlgorithmParameterException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecureString secureString1 = new SecureString(KEY, "");
        assertTrue(secureString1.isEmpty());
    }

    @Test(expected = InvalidAlgorithmParameterException.class)
    public void isWrongKeyLength() throws NoSuchPaddingException, UnsupportedEncodingException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException,
            BadPaddingException, InvalidKeyException {
        new SecureString("dummy test", WRONG_KEY);
    }
}