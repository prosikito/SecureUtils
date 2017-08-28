package com.cegrange.securityutils;

import android.util.Log;

/**
 * Created by cegrange on 18/08/2017.
 *
 */

class Logger {

    private static final String TAG = "Security Utils";

    private Logger() {
        // hide public constructor
    }

    static void log(Exception e){
        if (BuildConfig.DEBUG)
            Log.e(TAG, e.getMessage());
    }
}
