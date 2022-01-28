package com.appaspect.info.screen;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

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
                INL_Constant_Data.colorCode_Header = bundle_info.getInt(INL_Constant_Data.Header_BG_Color);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    getWindow().setStatusBarColor(INL_Constant_Data.colorCode_Header);
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    getWindow().setStatusBarColor(INL_Constant_Data.colorCode_Header);
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
