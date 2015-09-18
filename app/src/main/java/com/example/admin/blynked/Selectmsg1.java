package com.example.admin.blynked;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class Selectmsg1 extends ActionBarActivity {

    private ArrayList<String> conNames;
    private ArrayList<String> conNumbers;
    private Cursor crContacts;
    private SearchView searchView;
    ListView lv1;
    int p=0;
    int p1=0;
    Adapterr custom1;
    ArrayList<DataModel1> countrylist1;
    String[] name1, OtherName1, name2;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    ArrayList<DataModelmsg> countrylistmsg;
    String[] name_list,msg_list;
    int[] img_list;
    CustomAdapter cAdapter;
    ListView lv;
    Toolbar tool;
    TextView t11;
    ProgressDialog pDialog;
    ImageView img_main;
    String name, imagePreferance;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;

    private int mTitle = R.string.app_name;
    private static final int CONTAINER = R.id.fragment_container;
    public static final String STORAGE1 = "SenddataPreferences";
    public static final String STORAGE_NAME = "MySharedPreferencess";
    Integer[] img_id = {
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_request_menu_drawer,


            R.mipmap.icn_favourite_menu_drawer,
            R.mipmap.icn_calendar_menu_drawer,
            R.mipmap.icn_history_menu_drawer,
            R.mipmap.icn_settings_menu_drawer,



    };
    ListView rlv;
    Button tt,ttt;
    PackageManager packageManager;
    ListView apkList;
    ApkAdapter apkadapter;
    CustomAdaptermsg cAdaptermsg;
    Button pass;
    AutoCompleteTextView autoCompView;

    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar1;
    CircleProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectmsg1);
        pass=(Button)findViewById(R.id.pass);


        //  lv1 = (ListView) findViewById(R.id.list);
        msg_list = getResources().getStringArray(R.array.msg_list1);
        apkList = (ListView) findViewById(R.id.msgs);
        countrylistmsg = new ArrayList<DataModelmsg>();
        for (int i = 0; i < msg_list.length; i++) {
            // Toast.makeText(getApplicationContext(),""+img_list[i],Toast.LENGTH_LONG).show();
            DataModelmsg country = new DataModelmsg(msg_list[i]);
            countrylistmsg.add(country);
        }

        cAdaptermsg = new CustomAdaptermsg(getApplicationContext(), countrylistmsg);
        // lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
        apkList.setAdapter(cAdaptermsg);
        autoCompView = (AutoCompleteTextView) findViewById(R.id.atv_places1);
        autoCompView.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        autoCompView.setThreshold(1);
        autoCompView.setAdapter(cAdaptermsg);
        autoCompView.setDropDownHeight(0);
        autoCompView.setDropDownWidth(0);

        // autoCompView.setOnItemClickListener(this);
        //   apkList.setOnItemClickListener(this);
        tool = (Toolbar) findViewById(R.id.tool_bar);
        if (tool != null) {
            setSupportActionBar(tool);
            // tool.setNavigationIcon(R.drawable.navicon);
            // tool.setNavigationIcon(R.drawable.navicon);
            // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            tool.setNavigationIcon(R.mipmap.menu_converted);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //getSupportActionBar().setHomeButtonEnabled(true);
            mDrawerTitle = getTitle();
            name_list = getResources().getStringArray(R.array.item_name);
            //img_list=getResources().getIntArray(R.array.img_id);
            lv = (ListView) findViewById(R.id.nav_list);
            //  t11 = (TextView) findViewById(R.id.t1);
            //img_main = (ImageView) findViewById(R.id.img_icon);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
            //    lll=(LinearLayout)findViewById(R.id.drawer);
            //  img_main.setOnClickListener(new View.OnClickListener() {
            //        @Override
            //     public void onClick(View view) {
            //          Intent i = new Intent(Request_main.this, Image_main.class);
            //           startActivity(i);
            //      }
            //   });
            countrylist = new ArrayList<DataModel>();
            for (int i = 0; i < name_list.length; i++) {
                // Toast.makeText(getApplicationContext(),""+img_list[i],Toast.LENGTH_LONG).show();
                DataModel country = new DataModel(name_list[i], img_id[i]);
                countrylist.add(country);
            }

            cAdapter = new CustomAdapter(getApplicationContext(), countrylist);
            // lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
            lv.setAdapter(cAdapter);


            mToggle = new ActionBarDrawerToggle(this, drawerLayout, tool, R.string.drawer_open, R.string.drawer_close) {

                public void onDrawerClosed(View v) {
                    getSupportActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu();
                }

                public void onDrawerOpen(View v) {
                    getSupportActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu();

                }
            };
            drawerLayout.setDrawerListener(mToggle);
            //mToggle.setDrawerIndicatorEnabled(false);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RelativeLayout r1 = (RelativeLayout) findViewById(R.id.r1);
                switch (i) {
                    case 0:
                        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editorr = shared1.edit();

                        editorr.clear();
                        editorr.commit();
                        progressBar1 = (ProgressBar) findViewById(R.id.pBar1);
                        progressBar1.setProgress(0);
                        progressBar1.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                            @Override
                            public void run() {
                                Intent i1 = new Intent(Selectmsg1.this, Share_main.class);
                                // i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 1:

                        progressBar1 = (ProgressBar) findViewById(R.id.pBar1);
                        progressBar1.setProgress(0);
                        progressBar1.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                            @Override
                            public void run() {
                                Intent i1 = new Intent(Selectmsg1.this, Share_main1.class);
                                // i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:
                        //  FrameLayout r11 = (FrameLayout) findViewById(CONTAINER);
                        //  r11.setVisibility(View.INVISIBLE);
                        Intent i2 = new Intent(Selectmsg1.this, Request_main.class);
                        //i2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i2);
                        finish();
                        //   drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 3:


                        Intent i22 = new Intent(Selectmsg1.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent i222 = new Intent(Selectmsg1.this, Calendar_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i222);
                        finish();
                        break;
                    case 5:
                        Intent ih = new Intent(Selectmsg1.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(Selectmsg1.this, Setting_main.class);
                        startActivity(ii);
                        finish();
                        break;
                }


            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {
        name = sharedPreferences.getString("name", "");
        imagePreferance = sharedPreferences.getString("imagePreferance", "");

        //new getc().execute();

       /* ttt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RelativeLayout r=(RelativeLayout)findViewById(R.id.need);
                if(p==0) {
                    rlv.setVisibility(View.VISIBLE);
                  //  r.removeView(lv1);
                    lv1.setVisibility(View.GONE);
                    Helper1.getListViewSize(rlv);
                    p=1;
                }
                else if(p==1)
                {
                 //  r.removeView(rlv);
                   rlv.setVisibility(View.GONE);
                    p=0;
                 //   RelativeLayout need=(RelativeLayout)findViewById(R.id.need);
                //    need.setVisibility(View.VISIBLE);
                }
            }
            });
        tt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RelativeLayout r=(RelativeLayout)findViewById(R.id.need);
                if(p1==0) {
                   lv1.setVisibility(View.VISIBLE);
                   rlv.setVisibility(View.GONE);
                   // r.removeView(rlv);
                    Helper.getListViewSize(lv1);
                    p1=1;
                }
                else if(p1==1)
                {
                    lv1.setVisibility(View.GONE);
                 //   r.removeView(lv1);

                    p1=0;
                 //   RelativeLayout need=(RelativeLayout)findViewById(R.id.need);
                 //   need.setVisibility(View.VISIBLE);

                  //  Helper.getListViewSize(lv1);
                }
            }
        });*/
        pass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String msg=autoCompView.getText().toString();
                if(!msg.equals(""))
                {
                    SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared1.edit();
                    editor.putString("Msg", msg);
                    editor.apply();
                    progressBar = (CircleProgressBar) findViewById(R.id.pBar);
                    progressBar.setColorSchemeResources(android.R.color.holo_blue_light);
                    progressBar.setProgress(0);
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                        @Override
                        public void run() {
                            Intent ii1 = new Intent(Selectmsg1.this, Share_main1.class);
                            //statusCheck();
                            startActivity(ii1);
                            finish();
                            // close this activity

                        }
                    }, SPLASH_TIME_OUT);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please select any message", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

/*    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //   add=e1.getText().toString();

        InputMethodManager imm = (InputMethodManager) getSystemService(Selectmsg.this.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }*/

/* class Wait extends AsyncTask<String, String, String> {

     *//**
     * Before starting background thread Show Progress Dialog
     *//*
     boolean failure = false;

     @Override
     protected void onPreExecute() {
         super.onPreExecute();


         pDialog = new ProgressDialog(Request_main.this);
         pDialog.setMessage("Loading...");
         pDialog.setIndeterminate(false);
         pDialog.setCancelable(true);
         pDialog.show();

     }

     @Override
     protected String doInBackground(String... args) {
         // TODO Auto-generated method stub
         // Check for success tag


         try {
             // Building Parameters

             try {
                 //Do something...
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             // Run your task here
               *//* Intent i=new Intent(Tests.this,Otp.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();*//*


         } catch (Exception e) {
             e.printStackTrace();
         }

         return null;

     }

     *//**
     * After completing background task Dismiss the progress dialog
     * *
     *//*
     protected void onPostExecute(String file_url) {
         // dismiss the dialog once product deleted

         pDialog.dismiss();
         if (file_url != null) {



         }


     }*/
    //}



    public void loadFragment(Fragment fragment, boolean addToBackStack) {

        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(CONTAINER, fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(CONTAINER, fragment).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater imf = getMenuInflater();
        imf.inflate(R.menu.menu_main, menu);
        return true;
    }

/*
    public void sendSMS(final String p1, final String p2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Request_main.this);
        builder.setCancelable(false);
        // builder.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        builder.setMessage("Do you want to send request ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application

                // String p1 = textView.getText().toString();
                //String p2 = textView1.getText().toString();
                String message = "I request you to share your location,Tap on this URL";
                sendSMS(p2, message);
                Toast.makeText(getApplicationContext(), "Your request sent to" + p1, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }*/



    @Override
    public void onBackPressed() {
        progressBar = (CircleProgressBar) findViewById(R.id.pBar);
        progressBar.setColorSchemeResources(android.R.color.holo_blue_light);
        progressBar.setProgress(0);
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent ii1 = new Intent(Selectmsg1.this, Share_main1.class);
                //statusCheck();
                startActivity(ii1);
                finish();
                // close this activity

            }
        }, SPLASH_TIME_OUT);
    }


/*    @Override
    public void onClick(View view) {
        if (apkadapter != null) {


          *//*  ArrayList<String> mArrayProducts = apkadapter.getCheckedItems();
           // Toast.makeText(getApplicationContext(), "Selected Items: " + mArrayProducts.toString(), Toast.LENGTH_LONG).show();
            String apps=mArrayProducts.toString();
            SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shared1.edit();
            editor.putString("sapps", apps);
            editor.apply();
            Intent i=new Intent(Selectapp.this,Share_main.class);
            startActivity(i);
            finish();*//*

        }


    }*/
}