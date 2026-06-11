package com.xentoradx.browser;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WebView ko layout se connect karna
        myWebView = findViewById(R.id.webView);
        
        // WebSettings configure karna taaki Google properly chale
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        // Links ko app ke andar hi kholne ke liye
        myWebView.setWebViewClient(new WebViewClient());

        // Jab app khule toh sabse pehle Google load ho
        myWebView.loadUrl("https://www.google.com");
    }

    // Back button dabane par browser page peeche jaye, app band na ho
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
