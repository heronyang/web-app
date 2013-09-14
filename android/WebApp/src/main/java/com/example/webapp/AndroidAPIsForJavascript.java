package com.example.webapp;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by heron on 9/14/13.
 */
public class AndroidAPIsForJavascript {
    private static final String TAG = MainActivity.LOG_TAG;
    MainActivity activity;
    Context ctx;

    AndroidAPIsForJavascript(MainActivity a) {
        this.activity = a;
        this.ctx = a.getApplication();
    }

    @JavascriptInterface
    public String getPhoneNumber() {
        // requires android.permission.READ_PHONE_STATE
        // NOTE: may return null or ""
        TelephonyManager tm = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String t =  tm.getLine1Number();
        return t;
    }

    @JavascriptInterface
    public void test() {
        Log.v(TAG, "test");
    }

}
