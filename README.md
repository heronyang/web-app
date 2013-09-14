web-app
=======
Android Web App Prototype

## Introduction
This is a prototype Android Web App, which allows developers to get started with.
In this app prototype, developers can easily develope new design by writing websites without writing Android java code in most of the time. Also, often-used guesture handlers, such as swipe, long press, etc are included in this app.

## How to Build and Run

* `git clone https://github.com/heronyang/web-app.git`
* `cd web-app && make`
* open Android Studio and import **web-app/android**
* run

## Development Evironment
### Default Settings (app built-in websites)
There's a directory **web-app/android/WebApp/src/main/assets**, which is a symlink to **web-app/media**.
Developers are expected to development there app websites under this directory, **web-app/media**.
To update new websites, developers have to reinstall the app.

### Remote Websites
While developing the app websites, it may be much easier to use remote website. So, there's no need to reinstall the app whenever the app websites are changed.

Switch to remote websites:
* setup a remote web server, or use the python socket program in the repo `./devel-server --addrport=0.0.0.0:8080`
* in **web-app/android/WebAppi/src/main/java/com/example/webapp/MainActivity.java** set `DEVELOPING_MOBILE_WEB_UI` to **true**
* in **web-app/android/WebAppi/src/main/java/com/example/webapp/MainActivity.java** set `DEVEL_SERVER` to **the location of the remote web server**

Tip: By double clicking the page on Android app while runing, the app reloads the new websites, which is a good way to test new code then debug.

## How to Import in Android Studio

## Where to Start Changing the Code
### Customize App Name
Modify the following code in **web-app/android/WebApp/src/main/res/values/strings.xml**
```xml
<string name="app_name">Web App</string>
```
to
```xml
<string name="app_name">**New Name**</string>
```

### App Website Developing
Edit **web-app/media/index.html** to match your design.

## Create Whole Android Project on Your Own
If the default Android Project doesn't match your expectation or not running well, you may want to start building the Android Project on your own using Android Studio. Here are the steps:

###
* `git clone https://github.com/heronyang/web-app.git`
* `cd web-app && make`
* open Android Studio
* **File** -> **New Project**
![ScreenShot](https://lh5.googleusercontent.com/-R6c_gzAVNDY/UjSrQF6sleI/AAAAAAAAJbY/YC4PJxX7yHU/w847-h716-no/Screen+Shot+2013-09-14+at+10.02.00+PM.png)
* **Next** -> **Next** -> **Finish** Do any changes if needed.
* modify code
**src/main/layout/activity_main.xml**
replace
```xml
<TextView
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="@string/hello_world" />
```
with

```xml
<WebView
android:id="@+id/webView"
android:layout_width="fill_parent"
android:layout_height="fill_parent"
android:layout_alignParentRight="false"/>
```
**src/main/AndroidManifest.xml**
add the following two lines right before </manifest>

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```
**scr/java/com.example.webapp/MainActivity**
replace all the code in class MainActivity, and press ‘OK’ when Android Studio ask the to select classes to import (select all). There may be errors since AndroidAPIsForJavascript is not created yet.

```java
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
```

**create new class**
* right click on java/com.example.webapp
* create new classes with name AndroidAPIsForJavascript
* paste the following code in AndroidAPIsForJavascript class, and press ‘OK’ when Android Studio ask the things to import

```java
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
```

**link with app websites**
`cd WebAppProject/WebApp/src/main & ln -s ../../../../media/ assets`

**Run**
run in Android Studio
