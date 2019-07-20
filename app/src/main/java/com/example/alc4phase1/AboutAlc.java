package com.example.alc4phase1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class AboutAlc extends AppCompatActivity {

    private WebView aboutAlcWebView;
    private ProgressBar progressBar;
    private ProgressBar myPogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        myPogressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        aboutAlcWebView = (WebView) findViewById(R.id.aboutAlcWebView);
        aboutAlcWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AboutAlc.this);
                builder.setTitle("SSl Error");
                builder.setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.proceed();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
                builder.setMessage("Ooops there was  an error loading the webpage this " +
                        "caused by unknown SSL certificate " +
                        "Would like to proceed ?");
                builder.create().show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                myPogressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                myPogressBar.setVisibility(View.GONE);
            }
        });
        aboutAlcWebView.getSettings().setJavaScriptEnabled(true);
        aboutAlcWebView.loadUrl("https://andela.com/alc/");
    }
}
