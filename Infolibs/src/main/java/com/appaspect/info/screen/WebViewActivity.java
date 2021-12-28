package com.appaspect.info.screen;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener
{
	public static final String ARGUMENT_URL= "URL";
	public static final String ARGUMENT_TITLE= "Title";
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_acticity);

		manageHeader();
		try
		{
			webView = findViewById(R.id.webview);

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


			webView.getSettings().setJavaScriptEnabled(true);
			webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
			String url = getIntent().getStringExtra(ARGUMENT_URL);

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

	private void manageHeader()
	{
		((TextView)findViewById(R.id.txt_header_title)).setText(getIntent().getStringExtra(ARGUMENT_TITLE));
		ImageView imgBack = findViewById(R.id.img_back_arrow);
		imgBack.setVisibility(View.VISIBLE);
		imgBack.setOnClickListener(this);

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