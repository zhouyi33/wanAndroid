package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    private TextView title2;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        title2 = findViewById(R.id.title2);
        webView = findViewById(R.id.web);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");

        title2.setText(title);
        webView.loadUrl(url);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(url);

            }
        });

    }
}