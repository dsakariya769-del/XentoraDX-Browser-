package com.xentoradx.browser;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.GridLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private LinearLayout homeScreenLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Root Layout (Deep Premium Dark Background)
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(Color.parseColor("#020609")); 

        // 2. EXACT TOP BAR (Sleek Space Between)
        LinearLayout topBar = new LinearLayout(this);
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        topBar.setPadding(45, 50, 45, 30);
        topBar.setGravity(Gravity.CENTER_VERTICAL);

        // Brand Text (Neon Cyan Logo Typeface)
        TextView brandText = new TextView(this);
        brandText.setText("✕ XentoraDX");
        brandText.setTextColor(Color.parseColor("#4DEEEA")); 
        brandText.setTextSize(22);
        brandText.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams brandParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        brandText.setLayoutParams(brandParams);
        topBar.addView(brandText);

        // Action Icons Layout
        LinearLayout topIconsLayout = new LinearLayout(this);
        topIconsLayout.setOrientation(LinearLayout.HORIZONTAL);
        topIconsLayout.setGravity(Gravity.END);
        
        // Exact Unicode Shapes representing Vector Glyphs for Search, Mic, Scan
        topIconsLayout.addView(createTopIcon("⌕")); // Sleek Minimal Search Vector Outline
        topIconsLayout.addView(createTopIcon("SC")); 
        topIconsLayout.addView(createTopIcon("⚃"));  // Scanner Outline Graphic Object

        topBar.addView(topIconsLayout);
        mainLayout.addView(topBar);

        // 3. CLEAN CONTENT FRAME
        homeScreenLayout = new LinearLayout(this);
        homeScreenLayout.setOrientation(LinearLayout.VERTICAL);
        homeScreenLayout.setPadding(40, 80, 40, 40);
        homeScreenLayout.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams homeParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        homeScreenLayout.setLayoutParams(homeParams);

        // Grid Design - Exactly 3 Columns as shown in image reference
        GridLayout appsGrid = new GridLayout(this);
        appsGrid.setColumnCount(3);
        appsGrid.setUseDefaultMargins(false);
        appsGrid.setGravity(Gravity.CENTER);

        // Grid Content Construction
        addAppIcon(appsGrid, "▶", "YouTube", "https://www.youtube.com", "#FF0000");
        addAppIcon(appsGrid, "❖", "ChatGPT", "https://chatgpt.com", "#10A37F");
        addAppIcon(appsGrid, "G", "News", "https://news.google.com", "#4285F4");
        addAppIcon(appsGrid, "🌐", "Instagram", "https://www.instagram.com", "#E1306C");
        addAppIcon(appsGrid, "f", "Facebook", "https://www.facebook.com", "#1877F2");
        addAppIcon(appsGrid, "𝕏", "X", "https://x.com", "#FFFFFF");
        addAppIcon(appsGrid, "🌐", "Pinterest", "https://www.pinterest.com", "#BD081C");

        homeScreenLayout.addView(appsGrid);
        mainLayout.addView(homeScreenLayout);

        // 4. WEBVIEW CONTROLLER
        myWebView = new WebView(this);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setVisibility(View.GONE);
        mainLayout.addView(myWebView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f));

        // 5. SCREEN-MATCH BOTTOM NAVIGATION BAR
        LinearLayout bottomBar = new LinearLayout(this);
        bottomBar.setOrientation(LinearLayout.HORIZONTAL);
        bottomBar.setBackgroundColor(Color.parseColor("#020609"));
        bottomBar.setPadding(10, 40, 10, 40);
        bottomBar.setGravity(Gravity.CENTER_VERTICAL);

        // Structured Layout Buttons matching exact image requirements
        TextView btnHistory = createNavButton("🕒", "#4DEEEA");
        TextView btnAddTab = createNavButton("＋", "#4DEEEA");
        TextView btnHome = createNavButton("⌂", "#4DEEEA");
        TextView btnTabs = createNavButton("⧉", "#4DEEEA");
        TextView btnDX = createNavButton("DX", "#4DEEEA");
        btnDX.setTypeface(null, android.graphics.Typeface.BOLD);

        bottomBar.addView(btnHistory);
        bottomBar.addView(btnAddTab);
        bottomBar.addView(btnHome);
        bottomBar.addView(btnTabs);
        bottomBar.addView(btnDX);

        mainLayout.addView(bottomBar, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // 6. ACTION INTERACTION LISTENERS
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.setVisibility(View.GONE);
                homeScreenLayout.setVisibility(View.VISIBLE);
            }
        });

        setContentView(mainLayout);
    }

    private TextView createTopIcon(String iconStr) {
        TextView tv = new TextView(this);
        tv.setText(iconStr);
        tv.setTextColor(Color.parseColor("#4DEEEA"));
        tv.setTextSize(20);
        tv.setPadding(30, 10, 30, 10);
        return tv;
    }

    private TextView createNavButton(String text, String colorHex) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.parseColor(colorHex));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        tv.setLayoutParams(params);
        return tv;
    }

    private void addAppIcon(GridLayout grid, String glyph, final String name, final String url, String accentColor) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setGravity(Gravity.CENTER);
        container.setPadding(35, 40, 35, 40);

        // Core App Square Frame (Highly Uniform Vector Look)
        TextView itemFrame = new TextView(this);
        itemFrame.setText(glyph);
        itemFrame.setTextSize(24);
        itemFrame.setTextColor(Color.parseColor("#4DEEEA"));
        itemFrame.setGravity(Gravity.CENTER);
        
        // Exact sizing bounds to enforce grid consistency
        LinearLayout.LayoutParams frameParams = new LinearLayout.LayoutParams(160, 160);
        itemFrame.setLayoutParams(frameParams);

        GradientDrawable cardShape = new GradientDrawable();
        cardShape.setColor(Color.parseColor("#091117")); 
        cardShape.setCornerRadius(40f); 
        itemFrame.setBackground(cardShape);

        // Vector Sub-text styling
        TextView label = new TextView(this);
        label.setText(name);
        label.setTextColor(Color.parseColor("#5F727D")); 
        label.setTextSize(13);
        label.setPadding(0, 18, 0, 0);
        label.setGravity(Gravity.CENTER);

        container.addView(itemFrame);
        container.addView(label);

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeScreenLayout.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);
                myWebView.loadUrl(url);
            }
        });

        grid.addView(container);
    }

    @Override
    public void onBackPressed() {
        if (myWebView.getVisibility() == View.VISIBLE) {
            if (myWebView.canGoBack()) {
                myWebView.goBack();
            } else {
                myWebView.setVisibility(View.GONE);
                homeScreenLayout.setVisibility(View.VISIBLE);
            }
        } else {
            super.onBackPressed();
        }
    }
            }
