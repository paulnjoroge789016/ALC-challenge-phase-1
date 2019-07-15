package com.example.alc4phase1;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        aboutAlcWebView = (WebView) findViewById(R.id.aboutAlcWebView);
        aboutAlcWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }

            public void onProgressChanged(WebView view, int progress)
            {
                //Make the bar disappear after URL is loaded, and changes string to Loading...
                setTitle("Loading...");
                setProgress(progress * 100); //Make the bar disappear after URL is loaded

                // Return the app name after finish loading
                if(progress == 100)
                    setTitle(R.string.app_name);
            }
        });
        aboutAlcWebView.getSettings().setJavaScriptEnabled(true);
        aboutAlcWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        aboutAlcWebView.getSettings().setJavaScriptEnabled(true);
        aboutAlcWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
//        aboutAlcWebView.getSettings().setPluginsEnabled(true);
        aboutAlcWebView.getSettings().setSupportMultipleWindows(false);
        aboutAlcWebView.getSettings().setSupportZoom(false);
        aboutAlcWebView.setVerticalScrollBarEnabled(false);
        aboutAlcWebView.setHorizontalScrollBarEnabled(false);
        aboutAlcWebView.loadUrl("https://andela.com/alc/");
    }
}
