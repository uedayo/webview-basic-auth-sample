package com.uedayo.android.basicauthsample;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by ueda on 2013/10/20.
 */
public class DefaultWebViewActivity extends Activity {
    private final String HOST = "basic-auth-sample.herokuapp.com";
    private final String URL = "http://" + HOST + "/";
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);
        webview.setWebViewClient(new WebViewClient());
        setContentView(webview);
        webview.loadUrl(URL);
    }
}