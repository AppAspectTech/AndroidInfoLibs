package com.appaspect.info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.appaspect.info.screen.INL_Constant_Data;
import com.appaspect.info.screen.Info_Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {

            String str_tag = "Info_Fragment";
            String str_addToBackStack = "Info_Fragment";;
            Bundle bundle_info=new Bundle();
            bundle_info.putString(INL_Constant_Data. Publisher_Name, INL_Constant_Data.AppAspect);
            bundle_info.putInt(INL_Constant_Data.InfoScreen_BG_Color,getResources().getColor(R.color.grey_bg));//80D5D9DF
            bundle_info.putInt(INL_Constant_Data.Header_BG_Color,getResources().getColor(R.color.white));
            bundle_info.putInt(INL_Constant_Data.Header_Text_Color,getResources().getColor(R.color.white)); // set Header Text Color
            bundle_info.putInt(INL_Constant_Data.Privacy_Policy_Text_Color,getResources().getColor(R.color.white)); // set privacy policy Text Color
            bundle_info.putString(INL_Constant_Data.Theme,INL_Constant_Data.Theme_Dark);
            bundle_info.putString(INL_Constant_Data.Privacy_URL,"");
            bundle_info.putString(INL_Constant_Data.Terms_And_Condition_URL,"");
            bundle_info.putString(INL_Constant_Data.App_Name,"");
            bundle_info.putString(INL_Constant_Data.App_Package_Name,getPackageName());
            bundle_info.putString(INL_Constant_Data.App_Version_Name,"2.0");

            bundle_info.putString(INL_Constant_Data.Device_Model,"");
            bundle_info.putString(INL_Constant_Data.Device_OS,"");

            bundle_info.putBoolean(INL_Constant_Data.Header_Show_Hide,false); // if fragment pass true otherwise false
            Info_Fragment info_fragment=new Info_Fragment();
            info_fragment.setArguments(bundle_info);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, info_fragment, str_tag).addToBackStack(str_addToBackStack).commit();

//            bundle_info.putBoolean(INL_Constant_Data.Header_Show_Hide,true); // if fragment pass true otherwise false
//            Intent intent = new Intent(MainActivity.this, Info_Activity_new.class);
//            intent.putExtras(bundle_info);
//            startActivity(intent);

        } catch (Exception e)
        {

        }


    }
}