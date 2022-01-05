package com.appaspect.info.screen;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



public class Info_Fragment extends Fragment implements View.OnClickListener{

	private ScrollView sv_main;
	private LinearLayout ll_seprator,ll_contact_us,ll_share_app,ll_rate_this_app,ll_more_app;
	private LinearLayout ll_visit_our_website,ll_like_us_on_facebook,ll_follow_us_on_instagram,ll_follow_us_on_twitter;
	private String APP_LINK,strPackageName="";
	private TextView txt_app_version,txt_privacy,txt_terms_condition;
	private String publisherName = "",Theme="",str_privacy_url="",str_terms_and_condition_url="";
	private boolean header_show;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		try
		{
			Bundle bundle= getArguments();

			if(bundle!=null) //getting bundle data and set value
			{
				Theme = bundle.getString(INL_Constant_Data.Theme);
				publisherName = bundle.getString(INL_Constant_Data.Publisher_Name);
				INL_Constant_Data.colorCode = bundle.getInt(INL_Constant_Data.Header_BG_Color);
				INL_Constant_Data.BG_ColorCode_Info_Screen = bundle.getInt(INL_Constant_Data.InfoScreen_BG_Color);
				INL_Constant_Data.colorCode_Text = bundle.getInt(INL_Constant_Data.Header_Text_Color);
				INL_Constant_Data.colorCode_Privacy_Policy = bundle.getInt(INL_Constant_Data.Privacy_Policy_Text_Color);
				header_show = bundle.getBoolean(INL_Constant_Data.Header_Show_Hide);
				str_privacy_url = bundle.getString(INL_Constant_Data.Privacy_URL);
				str_terms_and_condition_url = bundle.getString(INL_Constant_Data.Terms_And_Condition_URL);
				strPackageName = bundle.getString(INL_Constant_Data.App_Package_Name);
				INL_Constant_Data.str_modelName = bundle.getString(INL_Constant_Data.Device_Model);
				INL_Constant_Data.str_device_os = bundle.getString(INL_Constant_Data.Device_OS);
				INL_Constant_Data.str_app_name = bundle.getString(INL_Constant_Data.App_Name);
				INL_Constant_Data.str_app_version = bundle.getString(INL_Constant_Data.App_Version_Name);


				if(Theme.equals(INL_Constant_Data.Theme_Dark))
				{
					INL_Constant_Data.colorCode_Text_Dark_Theme = getActivity().getResources().getColor(R.color.white);
				}
				else
				{
					INL_Constant_Data.colorCode_Text_Dark_Theme = getActivity().getResources().getColor(R.color.black);;
				}

				getInfoDetails();

				APP_LINK="https://play.google.com/store/apps/details?id=" + strPackageName;

			}
		}
		catch (Exception e)
		{
			Log.e("str_hl:- ",e.getMessage());
		}

	}
	private void getInfoDetails()
	{
		if(publisherName.equals(INL_Constant_Data.AppAspect))
		{
			INL_Constant_Data.str_visit_our_website = getString(R.string.appaspect_website);
			INL_Constant_Data.str_more_apps = getString(R.string.appaspect_more_apps);
			INL_Constant_Data.str_contact_us_email = getString(R.string.appaspect_info_mail);
			INL_Constant_Data.str_follow_us_on_twitter = getString(R.string.appaspect_twitter_url);
			INL_Constant_Data.str_follow_us_on_instagram = getString(R.string.appaspect_instagram_url);
			INL_Constant_Data.str_follow_us_on_instagram_app_url = getString(R.string.appaspect_instagram_app_url);
			INL_Constant_Data.str_like_us_on_facebook = getString(R.string.appaspect_facebook_url);
			INL_Constant_Data.Str_Share_App_Body_middle = getString(R.string.share_app_body_middle_appaspect);
		}
		else
		{
			INL_Constant_Data.str_visit_our_website = getString(R.string.guruinfomedia_website);
			INL_Constant_Data.str_more_apps = getString(R.string.guruinfomedia_more_apps);
			INL_Constant_Data.str_contact_us_email = getString(R.string.guruinfomedia_info_mail);
			INL_Constant_Data.str_follow_us_on_twitter = getString(R.string.guruinfomedia_twitter_url);
			INL_Constant_Data.str_follow_us_on_instagram = getString(R.string.guruinfomedia_instagram_url);
			INL_Constant_Data.str_follow_us_on_instagram_app_url = getString(R.string.guruinfomedia_instagram_app_url);
			INL_Constant_Data.str_like_us_on_facebook = getString(R.string.guruinfomedia_facebook_url);
			INL_Constant_Data.Str_Share_App_Body_middle = getString(R.string.share_app_body_middle_guruInfoMedia);
		}
	}
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view;
		view=(View) inflater.inflate(R.layout.activity_info, container, false);

		manageHeader(view);
		init(view);
		manageOnClick();

		sv_main.setBackgroundColor(INL_Constant_Data.BG_ColorCode_Info_Screen);
		ll_seprator.setBackgroundColor(INL_Constant_Data.colorCode_Privacy_Policy);
		txt_privacy.setTextColor(INL_Constant_Data.colorCode_Privacy_Policy);
		txt_terms_condition.setTextColor(INL_Constant_Data.colorCode_Privacy_Policy);

		return view;
	}


	@Override
	public void onResume()
	{
		super.onResume();

	}

	private void manageHeader(View view) // set Header value
	{
		if(!header_show)
		{
			((LinearLayout)view.findViewById(R.id.ll_main)).setVisibility(View.GONE);
		}
		else
		{
			((LinearLayout)view.findViewById(R.id.ll_main)).setVisibility(View.VISIBLE);
		}

		FrameLayout frame_layout = view.findViewById(R.id.frame_layout);
		frame_layout.setBackgroundColor(INL_Constant_Data.colorCode);

		TextView txt_header_title = view.findViewById(R.id.txt_header_title);
		txt_header_title.setText(getString(R.string.title_about));


		ImageView imgBack = view.findViewById(R.id.img_back_arrow);
		imgBack.setVisibility(View.VISIBLE);
		imgBack.setOnClickListener(this);
		txt_header_title.setTextColor(INL_Constant_Data.colorCode_Text);
		imgBack.setColorFilter(INL_Constant_Data.colorCode_Text);
		//findViewById(R.id.imgInfo).setVisibility(View.GONE);
	}

	private void init(View view) // initialization of widgets
	{
		sv_main=view.findViewById(R.id.sv_main);
		ll_seprator=view.findViewById(R.id.ll_seprator);
		ll_contact_us=view.findViewById(R.id.ll_contact_us);
		ll_share_app=view.findViewById(R.id.ll_share_app);
		ll_rate_this_app=view.findViewById(R.id.ll_rate_this_app);
		ll_more_app=view.findViewById(R.id.ll_more_app);

		txt_app_version=view.findViewById(R.id.txt_app_version);
		txt_privacy=view.findViewById(R.id.txt_privacy);
		txt_terms_condition=view.findViewById(R.id.txt_terms_condition);

		ll_visit_our_website=view.findViewById(R.id.ll_visit_our_website);
		ll_like_us_on_facebook=view.findViewById(R.id.ll_like_us_on_facebook);
		ll_follow_us_on_instagram=view.findViewById(R.id.ll_follow_us_on_instagram);
		ll_follow_us_on_twitter=view.findViewById(R.id.ll_follow_us_on_twitter);


		if(TextUtils.isEmpty(str_terms_and_condition_url))
		{
			ll_seprator.setVisibility(View.GONE);
			txt_terms_condition.setVisibility(View.GONE);
		}
		else
		{
			ll_seprator.setVisibility(View.VISIBLE);
			txt_terms_condition.setVisibility(View.VISIBLE);
		}
	}

	private void manageOnClick() //set ClickListener
	{
		ll_contact_us.setOnClickListener(this);
		ll_share_app.setOnClickListener(this);
		ll_rate_this_app.setOnClickListener(this);
		ll_more_app.setOnClickListener(this);
		ll_visit_our_website.setOnClickListener(this);
		ll_like_us_on_facebook.setOnClickListener(this);
		ll_follow_us_on_instagram.setOnClickListener(this);
		ll_follow_us_on_twitter.setOnClickListener(this);
		txt_privacy.setOnClickListener(this);
		txt_terms_condition.setOnClickListener(this);

		txt_app_version.setText(getString(R.string.version)+"  "+ INL_Constant_Data.str_app_version);
	}



	@Override
	public void onClick(View v)
	{
		if(v.getId()== R.id.img_back_arrow)
		{
			getActivity().finish();
		}
		else if(v.getId()== R.id.ll_visit_our_website)
		{
			try
			{
//				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse(INL_Constant_Data.str_visit_our_website));
//				startActivity(intent);

				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra(WebViewActivity.ARGUMENT_TITLE,getString(R.string.about_us));
				intent.putExtra(WebViewActivity.ARGUMENT_URL,INL_Constant_Data.str_visit_our_website);
				startActivity(intent);
			}
			catch (Exception ex)
			{
				Log.e("Error ",ex.toString());

                    Intent intent = new Intent(getActivity(),WebViewActivity.class);
					intent.putExtra(WebViewActivity.ARGUMENT_TITLE,getString(R.string.about_us));
                    intent.putExtra(WebViewActivity.ARGUMENT_URL,INL_Constant_Data.str_visit_our_website);
                    startActivity(intent);
			}
		}
		else if(v.getId()== R.id.ll_contact_us)
		{
			try
			{

				String subject = getString(R.string.feedback_for) + " " + INL_Constant_Data.str_app_name;
				String body = getString(R.string.title_app_name) + " " + INL_Constant_Data.str_app_name + " \n" +
						getString(R.string.title_app_version) + " " + INL_Constant_Data.str_app_version + " \n" +
						getString(R.string.title_os_version) + " " + INL_Constant_Data.str_device_os + " \n" +
						getString(R.string.title_device_model) + " " + INL_Constant_Data.str_modelName  + " \n";


				Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
				selectorIntent.setData(Uri.parse("mailto:"));

				final Intent emailIntent = new Intent(Intent.ACTION_SEND);
				//emailIntent.setType("message/rfc822");
				emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { INL_Constant_Data.str_contact_us_email });
				emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
				emailIntent.putExtra(Intent.EXTRA_TEXT, body);
				emailIntent.setSelector( selectorIntent );

				startActivity(Intent.createChooser(emailIntent, getString(R.string.str_mail_dialog_title)));
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		else if(v.getId()== R.id.ll_follow_us_on_twitter)
		{
			try
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(INL_Constant_Data.str_follow_us_on_twitter));
				startActivity(intent);
			}
			catch (ActivityNotFoundException ex)
			{
				Log.e("Error ",ex.toString());

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(INL_Constant_Data.str_follow_us_on_twitter));
				startActivity(intent);
			}
		}
		else if(v.getId()== R.id.ll_follow_us_on_instagram)
		{
			Uri uri = Uri.parse(INL_Constant_Data.str_follow_us_on_instagram_app_url);
			Intent intent = new Intent(Intent.ACTION_VIEW,uri);
			intent.setPackage("com.instagram.android");

			try {
				startActivity(intent);
			}
			catch (ActivityNotFoundException ex) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(INL_Constant_Data.str_follow_us_on_instagram)));
			}
		}
		else if(v.getId()== R.id.ll_like_us_on_facebook)
		{
			Uri uri = Uri.parse(INL_Constant_Data.str_like_us_on_facebook);
			Intent intent = new Intent(Intent.ACTION_VIEW,uri);

			try {
				startActivity(intent);
			}
			catch (ActivityNotFoundException ex) {
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(INL_Constant_Data.str_like_us_on_facebook)));
			}
		}
		else if(v.getId()== R.id.ll_share_app)
		{
			onshareapps();
		}
		else if(v.getId()== R.id.ll_rate_this_app)
		{
			try
			{
				Uri marketUri = Uri.parse(String.format("market://details?id=%s", strPackageName));
				Intent marketIntent = new Intent(Intent.ACTION_VIEW).setData(marketUri);
				startActivity(marketIntent);

			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		else if(v.getId()== R.id.ll_more_app)
		{
			try
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);

				intent.setData(Uri.parse(INL_Constant_Data.str_more_apps));
				startActivity(intent);
			}
			catch (Exception ex)
			{
				Log.e("Error ",ex.toString());
			}
		}

		else if(v.getId()== R.id.txt_privacy)
		{
			try
			{
//				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse(str_privacy_url));
//				startActivity(intent);

				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra(WebViewActivity.ARGUMENT_TITLE,getString(R.string.privacy_policy));
				intent.putExtra(WebViewActivity.ARGUMENT_URL,str_privacy_url);
				startActivity(intent);
			}
			catch (Exception ex)
			{
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra(WebViewActivity.ARGUMENT_TITLE,getString(R.string.privacy_policy));
				intent.putExtra(WebViewActivity.ARGUMENT_URL,str_privacy_url);
				startActivity(intent);

				Log.e("Error ",ex.toString());
			}
		}
		else if(v.getId()== R.id.txt_terms_condition)
		{
			try
			{
//				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse(str_privacy_url));
//				startActivity(intent);

				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra(WebViewActivity.ARGUMENT_TITLE,getString(R.string.terms_condition));
				intent.putExtra(WebViewActivity.ARGUMENT_URL,str_terms_and_condition_url);
				startActivity(intent);
			}
			catch (Exception ex)
			{
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra(WebViewActivity.ARGUMENT_TITLE,getString(R.string.terms_condition));
				intent.putExtra(WebViewActivity.ARGUMENT_URL,str_terms_and_condition_url);
				startActivity(intent);

				Log.e("Error ",ex.toString());
			}

//			Intent intent = new Intent(Intent.ACTION_VIEW);
//			intent.setData(Uri.parse(str_terms_and_condition_url));
//			startActivity(intent);
		}

	}

	public void onshareapps()
	{
		String body = getString(R.string.share_app_body_top) + " " + INL_Constant_Data.str_app_name + "\n" +
				INL_Constant_Data.Str_Share_App_Body_middle + "\n" + APP_LINK + "\n" +
				getString(R.string.share_app_body_bottom);

		LayoutInflater li = LayoutInflater.from(getActivity());
		View promptsView = li.inflate(R.layout.d_i_s_c_edit, null);

		ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.BLACK);
		SpannableStringBuilder ssBuilder = new SpannableStringBuilder(INL_Constant_Data.str_app_name);
		ssBuilder.setSpan(
				foregroundColorSpan,
				0,
				INL_Constant_Data.str_app_name.length(),
				Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
		);

		final EditText edtText = (EditText) promptsView.findViewById(R.id.edtName);
		edtText.setRawInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);

		edtText.setText(body);
		int position = edtText.length();
		Editable etext = edtText.getText();
		Selection.setSelection(etext, position);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
		alertDialogBuilder.setTitle(ssBuilder);
		alertDialogBuilder.setView(promptsView);

		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton(getString(R.string.str_send),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								String finalString = (edtText.getText().toString());
								Intent email = new Intent(Intent.ACTION_SEND);
								email.setType("text/plain");
								email.putExtra(Intent.EXTRA_EMAIL, "");
								email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								email.putExtra(Intent.EXTRA_SUBJECT, " " + INL_Constant_Data.str_app_name + " " + getString(R.string.share_andoird) + "" + getString(R.string.share_application));
								email.putExtra(Intent.EXTRA_TEXT, finalString);

								try {
									startActivity(Intent.createChooser(email, getString(R.string.str_mail_msg_title)));
								} catch (ActivityNotFoundException ex) {

								}
							}
						})
				.setNegativeButton(getString(R.string.str_cancel),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		final AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
			@Override
			public void onShow(DialogInterface arg0) {
				alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
				alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
			}
		});

		alertDialog.show();
	}
}