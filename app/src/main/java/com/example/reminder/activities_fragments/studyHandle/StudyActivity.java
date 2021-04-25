package com.example.reminder.activities_fragments.studyHandle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.reminder.R;
import com.example.reminder.util.AudioUtil;

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

    @SuppressLint({"WrongConstant", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_study);
        webView=findViewById(R.id.studyClass);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAllowContentAccess(true);
        WebView.setWebContentsDebuggingEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
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

        requestPermission();
        AudioUtil.choiceAudioModel(getApplicationContext());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onPermissionRequest(PermissionRequest request) {   //webview自带录音授权
                request.grant(request.getResources());
            }
        });

    }

    private void requestPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioUtil.changeToNormal(getApplicationContext());
    }
}