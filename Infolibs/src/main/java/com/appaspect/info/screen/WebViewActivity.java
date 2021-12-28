package com.appaspect.info.screen;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener
{
	public static final String ARGUMENT_URL= "URL";
	public static final String ARGUMENT_TITLE= "Title";
	private WebView webView;
	private  androidx.core.widget.ContentLoadingProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_acticity);

		try
		{
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			{
				getWindow().setStatusBarColor(INL_Constant_Data.colorCode);
			}
			else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			{
				getWindow().setStatusBarColor(INL_Constant_Data.colorCode);
			}
		}
		catch (Exception e)
		{
			Log.e("Exception_web====>",e.toString());
		}

		manageHeader();
		try
		{
			webView = findViewById(R.id.webview);
			progressBar=findViewById(R.id.progressBar);
			progressBar.setIndeterminateTintList(ColorStateList.valueOf(INL_Constant_Data.colorCode));

			WebSettings webSettings = webView.getSettings();
			webSettings.setJavaScriptEnabled(true);
			webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			webSettings.setLoadWithOverviewMode(true);
			webSettings.setUseWideViewPort(true);
			webSettings.setSupportZoom(false);
			webSettings.setBuiltInZoomControls(false);
			webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//			webView.setWebChromeClient(new MyCustomChromeClient(this));
//			webView.setWebViewClient(new MyCustomWebViewClient(this));

			try
			{
				webView.clearCache(true);
				webView.clearHistory();
			}
			catch (RuntimeException ex)
			{
				Log.e("RuntimeException  ",ex.toString());
			}
			catch (Exception ex)
			{
				Log.e("webView.clearHistory ",ex.toString());
			}

			String url = getIntent().getStringExtra(ARGUMENT_URL);
			webView.setWebViewClient(new MyWebViewClient());

			if(!TextUtils.isEmpty(url))
			{
				webView.loadUrl(url);


			}

		}
		catch (RuntimeException ex)
		{
			Log.e("RuntimeException  ",ex.toString());
		}
		catch (Exception ex)
		{
			Log.e("Exception ",ex.toString());
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			Log.e("onPageStarted str_url :- ",""+url);
			progressBar.setVisibility(View.VISIBLE);
			// invalidateOptionsMenu();
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			webView.loadUrl(url);
			Log.e("shouldOverrideUrlLoading str_url :- ",""+url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			Log.e("onPageFinished str_url :- ",""+url);
			progressBar.setVisibility(View.GONE);
			//invalidateOptionsMenu();
		}

		@Override
		public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
			super.onReceivedError(view, request, error);
			Log.e("onPageFinished str_url :- ",""+error.toString());
			progressBar.setVisibility(View.GONE);
			// invalidateOptionsMenu();
		}
	}

	private void manageHeader()
	{
		FrameLayout frame_layout = findViewById(R.id.frame_layout);
		frame_layout.setBackgroundColor(INL_Constant_Data.colorCode);

		TextView txt_header_title =findViewById(R.id.txt_header_title);

		txt_header_title.setText(getIntent().getStringExtra(ARGUMENT_TITLE));
		ImageView imgBack = findViewById(R.id.img_back_arrow);
		imgBack.setVisibility(View.VISIBLE);
		imgBack.setOnClickListener(this);

		txt_header_title.setTextColor(INL_Constant_Data.colorCode_Text);
		imgBack.setColorFilter(INL_Constant_Data.colorCode_Text);

		//findViewById(R.id.imgInfo).setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.img_back_arrow) {
			finish();
		}
	}

	@Override
	public void onBackPressed() {
		if(webView!=null)
		{
			if (webView.canGoBack())
			{
				webView.goBack();
			}
            else
			{
				super.onBackPressed();
			}
		}
        else
		{
			super.onBackPressed();
		}
	}
}