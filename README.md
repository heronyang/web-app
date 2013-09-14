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
