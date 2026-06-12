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

        // 1. Root Layout (Ekdam Dark Jaisa Photo Mein Hai)
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(Color.parseColor("#03070B")); 

        // 2. SAME-TO-SAME TOP BAR
        LinearLayout topBar = new LinearLayout(this);
        topBar.setOrientation(LinearLayout.HORIZONTAL);
        topBar.setPadding(40, 40, 40, 40);
        topBar.setGravity(Gravity.CENTER_VERTICAL);

        // Logo Text (XentoraDX)
        TextView brandText = new TextView(this);
        brandText.setText("✕ XentoraDX");
        brandText.setTextColor(Color.parseColor("#40E0D0")); // Neon Cyan/Turquoise
        brandText.setTextSize(22);
        brandText.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams brandParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        brandText.setLayoutParams(brandParams);
        topBar.addView(brandText);

        // Top Right Icons: Search, Mic, Scanner (Bina VPN ke)
        TextView iconSearch = createTopIcon("🔍");
        TextView iconMic = createTopIcon("🎙️");
        TextView iconScan = createTopIcon("🔲");
        topBar.addView(iconSearch);
        topBar.addView(iconMic);
        topBar.addView(iconScan);

        mainLayout.addView(topBar);

        // 3. HOME SCREEN CONTENT (Scrollable ya Fixed Frame)
        homeScreenLayout = new LinearLayout(this);
        homeScreenLayout.setOrientation(LinearLayout.VERTICAL);
        homeScreenLayout.setPadding(40, 60, 40, 40);
        homeScreenLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams homeParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        homeScreenLayout.setLayoutParams(homeParams);

        // Apps Ka Grid Layout (3 Columns - Jaisa Screenshot Mein Hain)
        GridLayout appsGrid = new GridLayout(this);
        appsGrid.setColumnCount(3);
        appsGrid.setRowCount(3);
        appsGrid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        appsGrid.setUseDefaultMargins(true);

        // Saare Apps Jodna (Bina Games aur Anime ke)
        addAppIcon(appsGrid, "▶️", "YouTube", "https://www.youtube.com");
        addAppIcon(appsGrid, "🤖", "ChatGPT", "https://chatgpt.com");
        addAppIcon(appsGrid, "🌐", "News", "https://news.google.com");
        addAppIcon(appsGrid, "📸", "Instagram", "https://www.instagram.com");
        addAppIcon(appsGrid, "🔷", "Facebook", "https://www.facebook.com");
        addAppIcon(appsGrid, "✕", "X", "https://x.com");
        addAppIcon(appsGrid, "📌", "Pinterest", "https://www.pinterest.com");

        homeScreenLayout.addView(appsGrid);
        mainLayout.addView(homeScreenLayout);

        // 4. WEBVIEW (Shuruat mein hidden rahega, jab click karenge tabhi khulega)
        myWebView = new WebView(this);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setVisibility(View.GONE); // Pehle mukhya screen dikhegi
        mainLayout.addView(myWebView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f));

        // 5. SAME-TO-SAME BOTTOM NAVIGATION BAR
        LinearLayout bottomBar = new LinearLayout(this);
        bottomBar.setOrientation(LinearLayout.HORIZONTAL);
        bottomBar.setBackgroundColor(Color.parseColor("#03070B"));
        bottomBar.setPadding(10, 30, 10, 30);
        bottomBar.setGravity(Gravity.CENTER);

        // Naye wale custom buttons jaisa aapne bataya
        TextView btnHistory = createNavButton("🕒", "#40E0D0");
        TextView btnAddTab = createNavButton("＋", "#40E0D0");
        TextView btnHome = createNavButton("⌂", "#40E0D0");
        TextView btnTabs = createNavButton("🔲", "#40E0D0");
        TextView btnDX = createNavButton("DX", "#40E0D0");
        btnDX.setTypeface(null, android.graphics.Typeface.BOLD);

        bottomBar.addView(btnHistory);
        bottomBar.addView(btnAddTab);
        bottomBar.addView(btnHome);
        bottomBar.addView(btnTabs);
        bottomBar.addView(btnDX);

        mainLayout.addView(bottomBar, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // 6. BUTTONS KI WORKING (CLICK LOGIC)
        
        // Home Button Click (Wapas main screen par aane ke liye)
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myWebView.setVisibility(View.GONE);
                homeScreenLayout.setVisibility(View.VISIBLE);
            }
        });

        // Top Search Icon Par Click Karne Par Google Khule
        iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite("https://www.google.com");
            }
        });

        setContentView(mainLayout);
    }

    // Top Bar ke Icons styling ke liye helper function
    private TextView createTopIcon(String iconStr) {
        TextView tv = new TextView(this);
        tv.setText(iconStr);
        tv.setTextColor(Color.parseColor("#40E0D0"));
        tv.setTextSize(18);
        tv.setPadding(20, 10, 20, 10);
        return tv;
    }

    // Bottom Navigation Buttons styling ke liye helper function
    private TextView createNavButton(String text, String colorHex) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.parseColor(colorHex));
        tv.setTextSize(22);
        tv.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        tv.setLayoutParams(params);
        return tv;
    }

    // Grid ke andar modern rounded app icons design karne ke liye function
    private void addAppIcon(GridLayout grid, String emoji, final String name, final String url) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        box.setPadding(30, 30, 30, 30);

        // Icon Box (Sleek Dark Rounded Square)
        TextView iconView = new TextView(this);
        iconView.setText(emoji);
        iconView.setTextSize(28);
        iconView.setGravity(Gravity.CENTER);
        iconView.setWidth(150);
        iconView.setHeight(150);

        GradientDrawable shape = new GradientDrawable();
        shape.setColor(Color.parseColor("#0B131A")); // App box background
        shape.setCornerRadius(35f); // Soft edges
        iconView.setBackground(shape);

        // App Name Text
        TextView textView = new TextView(this);
        textView.setText(name);
        textView.setTextColor(Color.parseColor("#80FFFFFF")); // Clean fade-white text
        textView.setTextSize(12);
        textView.setPadding(0, 15, 0, 0);
        textView.setGravity(Gravity.CENTER);

        box.addView(iconView);
        box.addView(textView);

        // Icon Par Click Karne Par Website Load Ho
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite(url);
            }
        });

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = GridLayout.LayoutParams.WRAP_CONTENT;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        box.setLayoutParams(params);

        grid.addView(box);
    }

    // Website Open Karne Ka Logic
    private void openWebsite(String url) {
        homeScreenLayout.setVisibility(View.GONE);
        myWebView.setVisibility(View.VISIBLE);
        myWebView.loadUrl(url);
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
