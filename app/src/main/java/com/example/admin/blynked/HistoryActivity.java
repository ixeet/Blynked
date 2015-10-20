package com.example.admin.blynked;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class HistoryActivity extends ActionBarActivity {
    public static final String STORAGE1 = "SenddataPreferences";
    Globals g;
    String desti,selmsg1,seltime1;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    CustomAdapter cAdapter;
    ListView lv;
    String idd1;
    String idd;
    String d1;
    //String name, imagePreferance;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    private int mTitle = R.string.app_name;
    public static final String STORAGE_NAME = "MySharedPreferencess";
    TextView t11;
    String p;
    long time1;
    String hidd;
    ProgressDialog pDialog;
    ImageView img_main;
    Integer[] img_id = {
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_request_menu_drawer,


            R.mipmap.icn_favourite_menu_drawer,
            R.mipmap.icn_calendar_menu_drawer,
            R.mipmap.icn_history_menu_drawer,
            R.mipmap.icn_settings_menu_drawer,


    };
    ListView list_his;
    int flaggg;
    int flag;
    TelephonyManager mngr;
    String uid;
    GridLayout glayout;
    String[] name1;
    String[] time;
    String name, imagePreferance = null;
    Toolbar tool;
    SqliteHelper1 sqliteHelper1;
    SqliteHelper2 sqliteHelper2;
    int timer;
    String rcp,crnt;
    private static final int CONTAINER = R.id.fragment_container;
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar1;
    CircleProgressBar progressBar;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.admin.blynked.R.layout.activity_history);
        g = (Globals) this.getApplication();
        timer = g.gettimer();
        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uid = mngr.getDeviceId();
        tool = (Toolbar) findViewById(R.id.tool_bar);
        sqliteHelper1 = new SqliteHelper1(this);
        sqliteHelper2 = new SqliteHelper2(this);
        if (tool != null) {
            setSupportActionBar(tool);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tool.setNavigationIcon(R.mipmap.menu_converted);
            // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        mDrawerTitle = getTitle();
        name_list = getResources().getStringArray(R.array.item_name);
        // name1 = getResources().getStringArray(R.array.history_name);
        //time = getResources().getStringArray(R.array.history_time);
        // list_his = (ListView) findViewById(R.id.history_list);
        //img_list=getResources().getIntArray(R.array.img_id);
        lv = (ListView) findViewById(R.id.nav_list);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        //    lll=(LinearLayout)findViewById(R.id.drawer);

        countrylist = new ArrayList<DataModel>();
        for (int i = 0; i < name_list.length; i++) {

            DataModel country = new DataModel(name_list[i], img_id[i]);
            countrylist.add(country);
        }

        cAdapter = new CustomAdapter(getApplicationContext(), countrylist);

        lv.setAdapter(cAdapter);

        glayout = (GridLayout) findViewById(R.id.grid_view);



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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RelativeLayout r1 = (RelativeLayout) findViewById(R.id.r1);
                switch (i) {
                    case 0:
                      //  new Wait().execute();
                        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = shared1.edit();

                        editor.clear();
                        editor.commit();
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
                                Intent ii1 = new Intent(HistoryActivity.this, Share_main.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 1:
                        //  new Wait().execute();

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
                                Intent ii1 = new Intent(HistoryActivity.this, Share_main1.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:
                        Intent i2 = new Intent(HistoryActivity.this, Request_main.class);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:

                        Intent i22 = new Intent(HistoryActivity.this, Favourite_main.class);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        Intent ihf = new Intent(HistoryActivity.this, Calendar_main.class);
                        startActivity(ihf);
                        finish();
                        break;
                    case 5:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ih = new Intent(HistoryActivity.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(HistoryActivity.this, Setting_main.class);
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
        //  if( imagePreferance!=null ) {
        if (!name.equals("")) {
            //      t11.setText(name);
            //     img_main.setImageBitmap(decodeBase64(imagePreferance));
        }

        //  }
        else {
            //     t11.setText("Please set your profile");
            //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            //    img_main.setImageBitmap(icon1);
        }


        sqliteHelper1 = new SqliteHelper1(getApplicationContext());

        int p1 = sqliteHelper1.dbSyncCount();
        int x = p1;
        //  Toast.makeText(getApplicationContext(), "" + p1, Toast.LENGTH_LONG).show();
        // sqliteHelper.deleteAllData();
        // data = g.getData();
        //   c=g.getc();
        while (p1 > 0) {
            final int finalP = x - p1 + 1;
            p = Integer.toString(finalP);
            Cursor cursor = sqliteHelper1.getUser(p);
            if (cursor.getCount() != 0) {
                cursor.moveToFirst();
                idd = cursor.getString(cursor.getColumnIndex("id"));
                // i = Integer.parseInt(idd);
                flag = cursor.getInt(cursor.getColumnIndex("flag"));
                time1 = cursor.getLong(cursor.getColumnIndex("time1"));
                d1 = cursor.getString(cursor.getColumnIndex("d1"));
                rcp = cursor.getString(cursor.getColumnIndex("rcp"));
                crnt = cursor.getString(cursor.getColumnIndex("crnt"));
                desti = cursor.getString(cursor.getColumnIndex("desti"));
            }
            cursor.close();
            Cursor cursor1 = sqliteHelper2.getUser(p);
            if (cursor1.getCount() != 0) {
                cursor1.moveToFirst();
                idd1 = cursor1.getString(cursor1.getColumnIndex("id"));
                // i = Integer.parseInt(idd);
                flaggg = cursor1.getInt(cursor1.getColumnIndex("flag"));
                //  Toast.makeText(getApplicationContext(),"idd="+idd+""+"flaggg="+flaggg, Toast.LENGTH_LONG).show();

            }
            cursor1.close();
            if (flaggg == 1 && idd1.equals(idd) ) {
                final RelativeLayout relativeLayout = new RelativeLayout(HistoryActivity.this);
                // relativeLayout.setPadding(5, 10, 5, 10);

                relativeLayout.setId(x - p1 + 1);

                relativeLayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                final TextView textview111 = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lpTextView111 = new RelativeLayout.LayoutParams(
                        250,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                textview111.setId(View.generateViewId());
                textview111.setLayoutParams(lpTextView111);
                if(!desti.equals("Define your destination")) {
                    textview111.setText(crnt + "--->" + desti);
                    textview111.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else
                {
                    textview111.setText(crnt);
                    textview111.setTypeface(Typeface.DEFAULT_BOLD);
                }
                final TextView textview = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lpTextView = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lpTextView.addRule(RelativeLayout.BELOW, textview111.getId());
                textview.setId(View.generateViewId());
                textview.setLayoutParams(lpTextView);
                textview.setText(d1);
                textview111.setTextColor(Color.parseColor("#ff11a3f1"));
                final TextView timer = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams timer1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                timer.setId(View.generateViewId());
                //     timer1.addRule(RelativeLayout.ALIGN_RIGHT);
                timer1.addRule(RelativeLayout.BELOW, textview.getId());
                timer.setLayoutParams(timer1);
                timer.setText("Expired");

                timer.setTextColor(Color.parseColor("#ff11a3f1"));

                final TextView rcpt = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lprcp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lprcp.addRule(RelativeLayout.BELOW,timer.getId());
                rcpt.setId(View.generateViewId());
                rcpt.setLayoutParams(lprcp);
                rcpt.setText("Shared with: ");

                final Button button1 = new Button(HistoryActivity.this);
                //  button.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
                button1.setText("Delete");

                button1.setTextColor(Color.parseColor("#FF525252"));
                button1.setBackgroundColor(Color.TRANSPARENT);
                button1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.delete), null, null, null);
                button1.setTextSize(15);
                final RelativeLayout.LayoutParams b11 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        70);
                b11.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                b11.setMargins(0, 5, 0, 0);
               // b11.addRule(RelativeLayout.BELOW, textview111.getId());
                //  b11.addRule(RelativeLayout.BELOW,rcptt.getId());
                button1.setId(View.generateViewId());
                button1.setVisibility(View.VISIBLE);
                button1.setLayoutParams(b11);
                final Button rs = new Button(HistoryActivity.this);
                //  button.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
                rs.setText("Resend");
                rs.setTextSize(15);
                rs.setTextColor(Color.parseColor("#FF525252"));
                rs.setBackgroundColor(Color.TRANSPARENT);
               // rs.setCompoundDrawables();
                rs.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.resend), null, null, null);
                final RelativeLayout.LayoutParams rss = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        70);
                //  rss.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                rss.addRule(RelativeLayout.BELOW, button1.getId());
                //rss.addRule(RelativeLayout.BELOW, timer.getId());
                rss.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                rss.setMargins(0, 10, 0, 0);
                rs.setId(View.generateViewId());
                rs.setLayoutParams(rss);
                final TextView rcptt = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lprcpt = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                //lprcpt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lprcpt.addRule(RelativeLayout.BELOW, rcpt.getId());
                //lprcpt.setMargins(10,0,0,0);
                lprcpt.addRule(RelativeLayout.LEFT_OF, rs.getId());
                lprcpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                rcptt.setId(View.generateViewId());
                rcptt.setLayoutParams(lprcpt);
                rcptt.setTypeface(null, Typeface.BOLD);
                rcptt.setText(rcp);
                /*final TextView textview1 = new TextView(HistoryActivity.this);
                //Setting the parameters on the TextView
                textview1.setLayoutParams(lpTextView1);
                textview1.setId(View.generateViewId());
                textview1.setText(idd);*/

                // textview1.setLayoutParams(b1);

                //  button.setId(View.generateViewId());



                View v = new View(this);
                final RelativeLayout.LayoutParams v11= new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        1);
                v11.addRule(RelativeLayout.BELOW, rcptt.getId());
              //  v11.addRule(RelativeLayout.BELOW, rs.getId());
                v11.setMargins(0, 20, 0, 0);
                v.setLayoutParams(v11);
                v.setBackgroundColor(Color.parseColor("#ff11a3f1"));


                // relativeLayout.addView(image);
                relativeLayout.addView(button1);
                relativeLayout.addView(rcpt);
                relativeLayout.addView(textview);
                relativeLayout.addView(rcptt);
                relativeLayout.addView(timer);
                relativeLayout.addView(rs);
                relativeLayout.addView(v);
                relativeLayout.addView(textview111);
                //mViews.add(relativeLayout);
                glayout.addView(relativeLayout);


                        rs.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if (relativeLayout.getId() == finalP) {
                            p = Integer.toString(finalP);
                            Cursor cursor = sqliteHelper1.getUser(p);
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();
                                idd = cursor.getString(cursor.getColumnIndex("id"));
                                flag= cursor.getInt(cursor.getColumnIndex("flag"));
                                time1 = cursor.getLong(cursor.getColumnIndex("time1"));
                                d1 = cursor.getString(cursor.getColumnIndex("d1"));
                                rcp = cursor.getString(cursor.getColumnIndex("rcp"));
                                desti = cursor.getString(cursor.getColumnIndex("desti"));
                                selmsg1 = cursor.getString(cursor.getColumnIndex("selmsg1"));
                                seltime1 = cursor.getString(cursor.getColumnIndex("seltime1"));
                                crnt = cursor.getString(cursor.getColumnIndex("crnt"));
                                //     timer.setText("Expired");
                                //countDownTimer.cancel();
                                //countDownTimer=null;
                                // flag=1;
                                // sqliteHelper2.update(idd,1);
                            }
                            cursor.close();
                            SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared1.edit();
                            editor.putString("destination", desti);
                            editor.putString("Msg",selmsg1);
                            editor.putString("duration",seltime1);
                            editor.putString("rcp",rcp);
                            editor.putString("Current",crnt);
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
                                    Intent i11 = new Intent(HistoryActivity.this, Share_main.class);
                                    i11.putExtra("cactivity1",3);

                                    startActivity(i11);
                                    // close this activity

                                }
                            }, SPLASH_TIME_OUT);





                        }


                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if (relativeLayout.getId() == finalP) {
                            p = Integer.toString(finalP);
                            Cursor cursor = sqliteHelper1.getUser(p);
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();
                                idd = cursor.getString(cursor.getColumnIndex("id"));
                                // i = Integer.parseInt(idd);


                                flag = cursor.getInt(cursor.getColumnIndex("flag"));

                                sqliteHelper2.saveUser(idd, 2);
                            }
                            // zoomImageFromThumb(image, selectedImage);
cursor.close();
                            glayout.removeView(relativeLayout);
                            //    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_LONG).show();


                        }


                    }
                });
            } else if (flaggg == 2 && idd1.equals(idd)) {
                //   Toast.makeText(getApplicationContext(), idd + flag, Toast.LENGTH_LONG).show();
            } else {
                final RelativeLayout relativeLayout = new RelativeLayout(HistoryActivity.this);

                relativeLayout.setId(x - p1 + 1);

                relativeLayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                final TextView textview111 = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lpTextView111 = new RelativeLayout.LayoutParams(
                        250,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                textview111.setId(View.generateViewId());
                textview111.setLayoutParams(lpTextView111);
                if(!desti.equals("Define your destination")) {
                    textview111.setText(crnt + "->" + desti);
                }
                else
                {
                    textview111.setText(crnt);
                }
                textview111.setTextColor(Color.parseColor("#ff11a3f1"));
                final TextView textview = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lpTextView = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                lpTextView.addRule(RelativeLayout.BELOW, textview111.getId());
                textview.setId(View.generateViewId());
                textview.setLayoutParams(lpTextView);
                textview.setText(d1);
                final TextView timer = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams timer1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                timer.setId(View.generateViewId());
                timer1.addRule(RelativeLayout.BELOW, textview.getId());
                //     timer1.addRule(RelativeLayout.ALIGN_RIGHT);
                //timer1.addRule(RelativeLayout.BELOW, textview.getId());
                timer.setTextColor(Color.parseColor("#ff11a3f1"));
                timer.setLayoutParams(timer1);
                final TextView rcpt = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lprcp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

                lprcp.addRule(RelativeLayout.BELOW, timer.getId());
                rcpt.setId(View.generateViewId());
                rcpt.setLayoutParams(lprcp);
                rcpt.setText("Shared with: ");
                final Button button1 = new Button(HistoryActivity.this);
                //  button.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
                button1.setText("Delete");
                final RelativeLayout.LayoutParams b11 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        70);
                b11.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                b11.setMargins(0, 5, 0, 0);
             //   b11.addRule(RelativeLayout.BELOW, textview111.getId());
                // b11.addRule(RelativeLayout.BELOW, rcptt.getId());
                button1.setId(View.generateViewId());
                button1.setTextSize(15);
                button1.setVisibility(View.INVISIBLE);
                button1.setTextColor(Color.parseColor("#FF525252"));
                button1.setBackgroundColor(Color.TRANSPARENT);
                button1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.delete), null, null, null);
                button1.setLayoutParams(b11);
                final Button rs = new Button(HistoryActivity.this);
                //  button.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
                rs.setText("Modify");
                rs.setTextSize(15);
                rs.setTextColor(Color.parseColor("#FF525252"));
                rs.setBackgroundColor(Color.TRANSPARENT);
               rs.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.icn_modify), null, null, null);
                final RelativeLayout.LayoutParams rss = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        70);
                //  rss.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                rss.addRule(RelativeLayout.BELOW, button1.getId());
                // rss.addRule(RelativeLayout.BELOW, timer.getId());
                rss.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                rss.setMargins(0, 10, 0, 0);
                rs.setId(View.generateViewId());
                rs.setLayoutParams(rss);
                final TextView rcptt = new TextView(HistoryActivity.this);
                final RelativeLayout.LayoutParams lprcpt = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
               // lprcpt.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                lprcpt.addRule(RelativeLayout.BELOW, rcpt.getId());
                lprcpt.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                //lprcpt.setMargins(10,0,0,0);
                lprcpt.addRule(RelativeLayout.LEFT_OF, rs.getId());
               // lprcpt.setMargins(10,0,0,0);
                rcptt.setId(View.generateViewId());
                rcptt.setLayoutParams(lprcpt);
                rcptt.setText(rcp);
                rcptt.setTypeface(null, Typeface.BOLD);

                final Button button = new Button(HistoryActivity.this);
                //  button.setLayoutParams(new android.view.ViewGroup.LayoutParams(60, 60));
                button.setText("Expire");
                button.setTextSize(15);
               button.setTextColor(Color.parseColor("#FF525252"));
                button.setBackgroundColor(Color.TRANSPARENT);
                button.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.icn_stop), null, null, null);
                final RelativeLayout.LayoutParams b1 = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        70);
                b1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                b1.setMargins(0, 5, 0, 0);
             //   b1.addRule(RelativeLayout.BELOW, textview111.getId());
                //b1.addRule(RelativeLayout.BELOW, timer.getId());
                button.setLayoutParams(b1);
                button.setId(View.generateViewId());



                // textview1.setLayoutParams(b1);i


                View v = new View(this);
                final RelativeLayout.LayoutParams v11= new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        1);
                v11.addRule(RelativeLayout.BELOW, rcptt.getId());
             //   v11.addRule(RelativeLayout.BELOW, rs.getId());
                v11.setMargins(0, 20, 0, 0);
                v.setLayoutParams(v11);

                v.setBackgroundColor(Color.parseColor("#ff11a3f1"));
                //    button.Gravity = GravityFlags.Center;
                //timer.setText("hiii");
                final CountDownTimer countDownTimer = new CountDownTimer(time1, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        //txt.setText("seconds remaining: " + millisUntilFinished / 1000);
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                        System.out.println(hms);
                        long min=millisUntilFinished/(1000*60);
                        if(min<60)
                        {
                            timer.setText(min+" Min Remaining");
                        }
                        else
                        {
                            int hr=(int)min/60;
                            timer.setText(hr+" hr Remaining");
                        }


                    }

                    @Override
                    public void onFinish() {
                        timer.setText("Expired");
                        rs.setText("Resend");
                        rs.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.resend), null, null, null);
                        button.setVisibility(View.INVISIBLE);
                        button1.setVisibility(View.VISIBLE);
                        sqliteHelper2.saveUser(idd, 1);

                    }
                }.start();

                // relativeLayout.addView(image);
                relativeLayout.addView(button1);
                relativeLayout.addView(button);
                relativeLayout.addView(textview);
                relativeLayout.addView(rcpt);
                relativeLayout.addView(rcptt);
                relativeLayout.addView(timer);
                relativeLayout.addView(textview111);
                relativeLayout.addView(rs);
                relativeLayout.addView(v);
                //mViews.add(relativeLayout);
                glayout.addView(relativeLayout);

                // final int finalP = x-p1+1;
                //  final String c = Integer.toString(finalP);

                rs.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if (relativeLayout.getId() == finalP) {
                            p = Integer.toString(finalP);
                            Cursor cursor = sqliteHelper1.getUser(p);
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();
                                idd = cursor.getString(cursor.getColumnIndex("id"));
                                flag = cursor.getInt(cursor.getColumnIndex("flag"));
                                time1 = cursor.getLong(cursor.getColumnIndex("time1"));
                                d1 = cursor.getString(cursor.getColumnIndex("d1"));
                                rcp = cursor.getString(cursor.getColumnIndex("rcp"));
                                desti = cursor.getString(cursor.getColumnIndex("desti"));
                                selmsg1 = cursor.getString(cursor.getColumnIndex("selmsg1"));
                                seltime1 = cursor.getString(cursor.getColumnIndex("seltime1"));
                                crnt = cursor.getString(cursor.getColumnIndex("crnt"));
                                //     timer.setText("Expired");
                                //countDownTimer.cancel();
                                //countDownTimer=null;
                                // flag=1;
                                // sqliteHelper2.update(idd,1);
                            }
                            cursor.close();
                            SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared1.edit();
                            editor.putString("destination", desti);
                            editor.putString("Msg",selmsg1);
                            editor.putString("duration",seltime1);
                            editor.putString("rcp",rcp);
                            editor.putString("Current",crnt);
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
                                    Intent i11 = new Intent(HistoryActivity.this, Share_main.class);
                                    i11.putExtra("cactivity1",3);

                                    startActivity(i11);
                                    // close this activity

                                }
                            }, SPLASH_TIME_OUT);


                        }


                    }
                });
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if (relativeLayout.getId() == finalP) {
                            p = Integer.toString(finalP);
                            Cursor cursor = sqliteHelper1.getUser(p);
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();
                                idd = cursor.getString(cursor.getColumnIndex("id"));
                                flag = cursor.getInt(cursor.getColumnIndex("flag"));
                                time1 = cursor.getLong(cursor.getColumnIndex("time1"));
                                d1 = cursor.getString(cursor.getColumnIndex("d1"));
                                timer.setText("Expired");
                                rs.setText("Resend");
                                rs.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.resend), null, null, null);
                                countDownTimer.cancel();
                                //countDownTimer=null;
                                // flag=1;
                                sqliteHelper2.saveUser(idd, 1);
                            }
                            cursor.close();
                            new Expire().execute();
                            // zoomImageFromThumb(image, selectedImage);
                            button.setVisibility(View.INVISIBLE);
                            button1.setVisibility(View.VISIBLE);



                        }


                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {


                        if (relativeLayout.getId() == finalP) {
                            p = Integer.toString(finalP);
                            Cursor cursor = sqliteHelper1.getUser(p);
                            if (cursor.getCount() != 0) {
                                cursor.moveToFirst();
                                idd = cursor.getString(cursor.getColumnIndex("id"));
                                // i = Integer.parseInt(idd);


                                flag = cursor.getInt(cursor.getColumnIndex("flag"));

                                sqliteHelper2.saveUser(idd, 2);
                            }
                            cursor.close();
                            // zoomImageFromThumb(image, selectedImage);

                            glayout.removeView(relativeLayout);
                            // Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_LONG).show();


                        }


                    }
                });
            }




            p1--;
        }
    }
    class Expire extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */

        String response;
        boolean failure = false;
        String gender;
        int mflag,eflag;
        int success;
        String bg;
        String c = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar =(CircleProgressBar)findViewById(R.id.pBar);
            progressBar.setColorSchemeResources(android.R.color.holo_blue_light);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag

            try {



                String EXPIRE_URL ="http://191.239.57.54:8080/Blynk/StopShare?imeiNo="+uid;
                GetJsonObject json=new GetJsonObject();
                response =json.getWebServceObj(EXPIRE_URL);
                Log.d("Stop response",response);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;

        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(HistoryActivity.this,file_url, Toast.LENGTH_LONG).show();

        }

    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bmp =BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length,options);
        return bmp;
    }

    public void onBackPressed() {
        progressBar = (CircleProgressBar) findViewById(R.id.pBar);


//        progress1.setColorSchemeResources(android.R.color.holo_blue_bright);
        //progress2.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //progressWithArrow.setColorSchemeResources(android.R.color.holo_orange_light);
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
                Intent i = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(i);

                finish();
                // close this activity

            }
        }, SPLASH_TIME_OUT);

    }
    public void loadFragment(Fragment fragment, boolean addToBackStack) {

        if(addToBackStack){
            getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(CONTAINER, fragment).commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(CONTAINER, fragment).commit();
        }

    }


}

