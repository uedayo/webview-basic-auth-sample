package com.uedayo.android.basicauthsample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewDatabase;

/**
 * Created by ueda on 2013/10/20.
 */
public class CustomizedWebViewActivity extends Activity {
    private final String HOST = "basic-auth-sample.herokuapp.com";
    private final String URL = "http://" + HOST + "/";
    private final String USERNAME = "user";
    private final String PASSWORD = "pass";

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);
        setBasicAuth();
        setContentView(webview);
        webview.loadUrl(URL);
    }

    protected void setBasicAuth() {
        WebViewDatabase.getInstance(this).clearHttpAuthUsernamePassword();
        webview.setHttpAuthUsernamePassword(HOST, HOST, USERNAME, PASSWORD);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                String[] up = view.getHttpAuthUsernamePassword(host, realm);
                handler.proceed(USERNAME, PASSWORD);
                if (up != null && up.length == 2) {
                    handler.proceed(up[0], up[1]);
                }
                else {
                    Log.d("", "Could not find username/password for domain: " + host + ", with realm = " + realm);
                }
            }
        });
    }
}