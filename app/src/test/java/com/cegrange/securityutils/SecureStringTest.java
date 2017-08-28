package com.cegrange.securityutils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SecureStringTest {

    @Test
    public void conversionIsCorrect() throws Exception {
        String value = "dummy test";
        assertEquals(value, new SecureString(value).toString());
    }

    @Test
    public void encryptionIsCorrect() throws Exception {
        String value = "dummy test";
        assertNotEquals(value, new SecureString(value).getValue());
    }

    @Test
    public void equalsIsCorrect(){
        SecureString secureString1 = new SecureString("dummy test");
        SecureString secureString2 = new SecureString("dummy test");
        assertEquals(secureString1.isEqualTo(secureString2), true);
    }

    @Test
    public void notEqualsIsCorrect(){
        SecureString secureString1 = new SecureString("dummy test");
        SecureString secureString2 = new SecureString("different dummy test");
        assertEquals(secureString1.isEqualTo(secureString2), false);
    }
}