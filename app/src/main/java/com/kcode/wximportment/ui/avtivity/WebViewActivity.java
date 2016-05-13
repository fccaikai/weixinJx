package com.kcode.wximportment.ui.avtivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kcode.wximportment.R;
import com.kcode.wximportment.utils.L;

public class WebViewActivity extends BaseActivity {

    private static final String TAG = "WebViewActivity";
    private String url;

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        setToolBarTitle(R.string.home_page);
        setToolbarHomeAsUp();

        mProgressBar = find(R.id.progressBar);

        mWebView = find(R.id.webView);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.setWebViewClient(new MyWebViewClient());

        initWebViewSetting();

        mWebView.loadUrl(url);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_web_view);
    }



    private void initData(){
        if(getIntent() != null){
            url = getIntent().getStringExtra("url");
        }
    }

    private void initWebViewSetting(){
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    private class MyWebViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            L.i(TAG,"页面加载完成");
            mProgressBar.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }
    }

    private class MyWebChromeClient extends WebChromeClient{
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }
    }
}
