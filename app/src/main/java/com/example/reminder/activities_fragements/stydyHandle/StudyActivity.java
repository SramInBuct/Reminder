package com.example.reminder.activities_fragements.stydyHandle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.reminder.R;

public class StudyActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    public void onBackPressed() {
        if (!webView.canGoBack()) {
            super.onBackPressed();
        } else {
            webView.goBack();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        webView=findViewById(R.id.studyClass);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAllowContentAccess(true);
        WebView.setWebContentsDebuggingEnabled(true);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String token = bundle.getString("token");
//        String Url="https://reminder.yihuang728.club/login";
        String Url="https://reminder.yihuang728.club/search?token="+(token==null?"":token);
        webView.loadUrl(Url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
//

//
//        return super.onCreateView(name, context, attrs);
//    }
}