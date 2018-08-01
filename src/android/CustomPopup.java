package com.sangs.plugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;

import com.sangs.bible25.R;

public class CustomPopup extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_custom_popup);

        Intent intent = getIntent();
        String linkUrl = intent.getStringExtra("linkUrl");
        Log.d("메시지", linkUrl);

        webView = (WebView) findViewById(R.id.custom_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(linkUrl);
    }

    public void onClick(View view) {
        this.finish();
    }
}
