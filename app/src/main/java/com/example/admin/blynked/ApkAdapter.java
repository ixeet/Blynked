package com.example.admin.blynked;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01-07-2015.
 */
public class ApkAdapter extends BaseAdapter  {

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

    public ApkAdapter(Activity context, List<PackageInfo> packageList,
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
        apkName.setText("Share via "+appName);
        apkName.setTextColor(Color.parseColor("#FF11A3F1"));




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView apkName=(TextView)view.findViewById(R.id.appname);
               // String appName=apkName.getText().toString();
                String appName1 = apkName.getText().toString();
                String[] split = appName1.split("Share via ");
                appName=split[1];

                SharedPreferences shared1 = context.getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();
                editor.putString("rcp",appName);
                editor.apply();
                Intent i=new Intent(context,Share_main.class);
                context.startActivity(i);
                context.finish();
            }
        });
        return view;
    }


}


