package com.example.admin.blynked;

/**
 * Created by Admin on 08-08-2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ApkAdapterq extends BaseAdapter {

    private static final String TAG = Adapterr.class.getSimpleName();
    ArrayList<DataModel1> listArray=null;
    String userid;
    ImageView ii;
    String no;
    public static final String STORAGE1 = "SenddataPreferences";
    ProgressDialog pDialog;
    List<PackageInfo> packageList;
    Activity context;
    PackageManager packageManager;
    int flag;
    SparseBooleanArray mSparseBooleanArray;
    String appName;

    public ApkAdapterq(Activity context, List<PackageInfo> packageList,
                      PackageManager packageManager){
        this.context = context;
        this.packageList = packageList;
        this.packageManager = packageManager;
        mSparseBooleanArray = new SparseBooleanArray();
    }


    @Override
    public int getCount() {
        return packageList.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return packageList.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(final int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.apklist_item, parent, false);

        }

        PackageInfo packageInfo = (PackageInfo) getItem(index);

        Drawable appIcon = packageManager
                .getApplicationIcon(packageInfo.applicationInfo);
        appName = packageManager.getApplicationLabel(
                packageInfo.applicationInfo).toString();
        appIcon.setBounds(0, 0, 50, 50);
        TextView apkName=(TextView)view.findViewById(R.id.appname);
        apkName.setCompoundDrawables(appIcon, null, null, null);
        apkName.setCompoundDrawablePadding(10);
        apkName.setText("Request via "+appName);
        apkName.setTextColor(Color.parseColor("#FF11A3F1"));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView apkName = (TextView) view.findViewById(R.id.appname);

                String appName1 = apkName.getText().toString();
                String[] split = appName1.split("Request via ");
                appName=split[1];

                if (appName.equals("Facebook")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";


                    waIntent.setPackage("com.facebook.katana");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("WhatsApp")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";

                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("Hangouts")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";


                    waIntent.setPackage("com.google.android.talk");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("Messaging")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";


                    waIntent.setPackage("com.android.mms");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("Skype")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";


                    waIntent.setPackage("com.skype.raider");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("Yahoo Mail")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";


                    waIntent.setPackage("com.yahoo.mobile.client.android.mail");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("Twitter")) {
                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";

                    waIntent.setPackage("com.twitter.android");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                } else if (appName.equals("Messenger")) {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "I am interested to know your location,Tap on this URL";

                    waIntent.setPackage("com.facebook.orca");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    context.startActivity(Intent.createChooser(waIntent, "Share with"));
                }


            }
        });
        return view;
    }

}



