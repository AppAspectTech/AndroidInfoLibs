package com.appaspect.info.screen;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Info_Activity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_new);

        String str_tag = "Info_Fragment";
        String str_addToBackStack = "Info_Fragment";
        Bundle bundle_info = getIntent().getExtras();
        if(bundle_info!=null) // getting bundle data and set in fragment
        {
            try
            {
                INL_Constant_Data.colorCode = bundle_info.getInt(INL_Constant_Data.Header_BG_Color);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    getWindow().setStatusBarColor(INL_Constant_Data.colorCode);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    getWindow().setStatusBarColor(INL_Constant_Data.colorCode);
                }
            }
            catch (Exception e)
            {
               Log.e("Exception",e.toString());
            }


            Info_Fragment info_fragment=new Info_Fragment();
            info_fragment.setArguments(bundle_info);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, info_fragment, str_tag).addToBackStack(str_addToBackStack).commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
