package com.example.webapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    static final boolean DEVELOPING_MOBILE_WEB_UI = false;
    static final String DEVEL_SERVER = "http://192.168.0.110:8080"; // modify it!

    static final String LOG_TAG = "WebApp";

    public WebView wv;
    private AndroidAPIsForJavascript android_js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        this.wv = (WebView) findViewById(R.id.webView);
        overrideLinkOpening();
        wv.getSettings().setJavaScriptEnabled(true);
        exposeJavascriptAPI();
        logJavascriptConsoleAPI();
        disableScrolling();

        String url;
        if (DEVELOPING_MOBILE_WEB_UI) {
//            url = DEVEL_SERVER + "/recommendations";
            url = DEVEL_SERVER;
        } else {
            url = "file:///android_asset/index.html";
        }
        wv.loadUrl(url);
    }

    private void overrideLinkOpening() {
        // Control which links open in the system browser
        // related: onKeyDown overrides back button navigation
        this.wv.setWebViewClient(new PlateWebViewClient());
    }

    private class PlateWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getScheme().equals("file")) {
                return false;
            } else {
                // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.wv.canGoBack()) {
            Log.i(LOG_TAG, "goBack: " + this.wv.getUrl());
            this.wv.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    private void exposeJavascriptAPI() {
        // Expose "AndroidAPIsForJavascript" under the name "Android"
        if (this.android_js == null)
            this.android_js = new AndroidAPIsForJavascript(this);
        this.wv.addJavascriptInterface(this.android_js, "Android");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void logJavascriptConsoleAPI() {
        this.wv.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d(LOG_TAG, cm.message() + " -- From line "
                        + cm.lineNumber() + " of "
                        + cm.sourceId() );
                return true;
            }
        });
    }

    private void disableScrolling() {
        // don't draw scroll bars
        this.wv.setHorizontalScrollBarEnabled(false);
        this.wv.setVerticalScrollBarEnabled(false);
        // NOTE: content may still scroll if over sized
    }
}
