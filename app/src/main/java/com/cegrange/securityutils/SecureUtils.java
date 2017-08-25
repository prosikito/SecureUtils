package com.cegrange.securityutils;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by cegrange on 18/08/2017.
 *
 */

public class SecureUtils {

    private static String key = "santander17";
    private static String encryption = "AES";

    public SecureUtils(String key, String encryption) {
        if (key != null)
            this.key = key;
        if (encryption != null)
            this.encryption = encryption;
    }
}
