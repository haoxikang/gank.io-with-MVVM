package com.fall.gank.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fall.gank.R;
import com.fall.gank.Utils.MDStatusBarCompat;
import com.fall.gank.Utils.Utils;
import com.fall.gank.core.BaseActivity;
import com.fall.gank.core.IPresenter;
import com.fall.gank.databinding.WebviewActivityBinding;
import com.fall.gank.presenter.WebViewActivityPresenter;
import com.fall.gank.viewmodel.WebViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qqq34 on 2016/12/8.
 */

public class WebViewActivity extends BaseActivity {

    private static final String EXTRA_URL = "WebViewActivity.url";

    private WebviewActivityBinding mWebviewActivityBinding;
    private WebViewModel mModel;
    private WebViewActivityPresenter mPresenter;


    public static void newIntent(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void initBinding() {
        mWebviewActivityBinding = DataBindingUtil.setContentView(WebViewActivity.this, R.layout.webview_activity);
    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        Toolbar toolbar = mWebviewActivityBinding.toolbar;
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MDStatusBarCompat.setOrdinaryToolBar(this);
        WebView webView = mWebviewActivityBinding.webview;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new ChromeClient());
        webView.setWebViewClient(new WebClient());
        mModel.webViewUrl.set(getIntent().getStringExtra(EXTRA_URL));
    }

    @Override
    public void initListeners() {
        mWebviewActivityBinding.toolbar.setNavigationOnClickListener(view -> {
                finish();
        });
        mWebviewActivityBinding.toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            switch (id) {
                case R.id.refresh:
                    mWebviewActivityBinding.webview.reload();
                    return true;
                case R.id.copy:
                    Utils.copyToClipBoard(this, mWebviewActivityBinding.webview.getUrl());
                    Snackbar.make(mWebviewActivityBinding.webview, "复制成功", Snackbar.LENGTH_SHORT).show();

                    return true;
                case R.id.open:
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(mWebviewActivityBinding.webview.getUrl());
                    intent.setData(uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Snackbar.make(mWebviewActivityBinding.webview, "打开失败：没有安装能打开此链接的app", Snackbar.LENGTH_SHORT).show();
                    }
                    return true;
            }
            return super.onOptionsItemSelected(item);
        });
    }

    @Override
    public void initOldData(@Nullable BaseObservable baseObservable) {
        mModel = (WebViewModel) baseObservable;

        mWebviewActivityBinding.setWebViewModel(mModel);
        mPresenter = new WebViewActivityPresenter(mModel);
        iPresenterList.add(mPresenter);
    }

    @Override
    public void initData() {
        mModel = new WebViewModel();
        mWebviewActivityBinding.setWebViewModel(mModel);
        mPresenter = new WebViewActivityPresenter(mModel);
        iPresenterList.add(mPresenter);
    }




    @Override
    public BaseObservable getViewModel() {
        return mModel;
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mModel.progress.set(newProgress);
        }


        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mWebviewActivityBinding.toolbar.setTitle(title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebviewActivityBinding.webview.canGoBack()) {
                        mWebviewActivityBinding.webview.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebClient extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebviewActivityBinding.webview.destroy();
    }


    @Override
    protected void onPause() {
        mWebviewActivityBinding.webview.onPause();
        super.onPause();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mWebviewActivityBinding.webview.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }
}
