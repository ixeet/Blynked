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
import android.net.Uri;
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
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Calendar_main extends ActionBarActivity {

    public static final String STORAGE1 = "SenddataPreferences";
    ListView clList;
    ArrayList<Eventdata> Lcontactstock;
    private View view;
    private MainActivity mAct;
    ToggleButton switch1;
    int cal;
    Globals g;
    TextView e;
    String dd;
    long current;
    long iii,jjj;
    String myString;
    private static final String DATE_TIME_FORMAT = "yyyy MMM dd, HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_FORMAT1 = "yyyy-MMM-dd";
    private static final String DATE_FORMAT11 = "MMM-dd";
    private static final String DATE_FORMAT2 = "HH:mm:ss";
    String desti,selmsg1,seltime1;
    LinearLayout lll;
    ProgressBar pBar;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    CustomAdapter cAdapter;
    ListView lv;
    String idd1;
    String idd;
    String d1;
    ProgressDialog pDialog;
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
    GridLayout glayout;
    String[] name1;
    String[] time;
    String name, imagePreferance = null;
    Toolbar tool;
    SqliteHelper1 sqliteHelper1;
ProgressBar progressBar1;
    int timer;
    String rcp;
    private static int SPLASH_TIME_OUT = 3000;
    CircleProgressBar progressBar;
    private static final int CONTAINER = R.id.fragment_container;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_main);
        g = (Globals) getApplication();
        timer = g.gettimer();
        tool = (Toolbar) findViewById(R.id.tool_bar);
        sqliteHelper1 = new SqliteHelper1(this);

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
       /* for (int i = 0; i < name1.length; i++) {
            // Toast.makeText(getApplicationContext(),""+name[i],Toast.LENGTH_LONG).show();
            HistoryModel historyModel = new HistoryModel(name1[i], time[i]);
            history_data.add(historyModel);
        }*/


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
                               //new Wait().execute();
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
                                Intent i = new Intent(Calendar_main.this, Share_main.class);
                                startActivity(i);

                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                               break;
                    case 1:
                        //new Wait().execute();

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
                                Intent i = new Intent(Calendar_main.this, Share_main1.class);
                                startActivity(i);

                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:
                        Intent i2 = new Intent(Calendar_main.this, Request_main.class);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:

                        Intent i22 = new Intent(Calendar_main.this, Favourite_main.class);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        Intent iih = new Intent(Calendar_main.this, Calendar_main.class);
                        startActivity(iih);
                        finish();
                        break;
                    case 5:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ih = new Intent(Calendar_main.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                       Intent ii = new Intent(Calendar_main.this, Setting_main.class);
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



        Lcontactstock = new ArrayList<Eventdata>();
        switch1 = (ToggleButton)findViewById(R.id.switch1);
        clList = (ListView)findViewById(R.id.eventlist);
        e=(TextView)findViewById(R.id.e);
        cal = g.getcal();
        //  c = g.getc();
        if (cal==0)
        {
            switch1.setChecked(false);

        }
        else
        {
            switch1.setChecked(true);

        }

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    // Toast.makeText(getActivity(), "Switch is ON", Toast.LENGTH_LONG).show();
                    clList.setVisibility(View.VISIBLE);
                    Lcontactstock.clear();
                    long time= System.currentTimeMillis();
                    Uri l_eventUri;
                    if (Build.VERSION.SDK_INT >= 8 ) {
                        l_eventUri = Uri.parse("content://com.android.calendar/events");
                    } else {
                        l_eventUri = Uri.parse("content://calendar/events");
                    }
                    String[] l_projection = new String[]{"title", "dtstart","eventLocation","dtend"};
                    Cursor l_managedCursor =Calendar_main.this.managedQuery(l_eventUri, l_projection, null, null, "dtstart DESC, dtend DESC");
                    //Cursor l_managedCursor = this.managedQuery(l_eventUri, l_projection, null, null, null);
                    if (l_managedCursor.moveToFirst()) {
                        int l_cnt = 0;
                        String l_title;
                        String l_begin;
                        String l_begin1;
                        String l_end;
                        String l_end1;
                        String location;
                        //StringBuilder l_displayText = new StringBuilder();
                        int l_colTitle = l_managedCursor.getColumnIndex(l_projection[0]);
                        int l_colBegin = l_managedCursor.getColumnIndex(l_projection[1]);
                        int l_colEnd = l_managedCursor.getColumnIndex(l_projection[3]);
                        int eventLocation = l_managedCursor.getColumnIndex(l_projection[2]);
                        do {
                            l_title = l_managedCursor.getString(l_colTitle);
                            l_begin = getDateTimeStr(l_managedCursor.getString(l_colBegin));
                        //    l_end = getDateTimeStr(l_managedCursor.getString(l_colEnd));
                            l_begin1 =l_managedCursor.getString(l_colBegin);
                     //       l_end1 =l_managedCursor.getString(l_colEnd);
                            location = l_managedCursor.getString(eventLocation);
                            iii = Long.valueOf(l_begin1).longValue();
                        //    jjj=Long.valueOf(l_end1).longValue();
                            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                            SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
                            SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
                            SimpleDateFormat sdf11 = new SimpleDateFormat(DATE_FORMAT11);
                         //   Date dateend=new Date(jjj);
                            Date date=new Date(iii);
                            String df=sdf1.format(date);
                            String d=sdf11.format(date);
                            String df2=sdf2.format(date);
                         //   String dend=sdf.format(dateend);
                            Date date1=new Date(System.currentTimeMillis());
                            String d1=sdf11.format(date1);
if(df2.equals("05:30:00"))
{
    df2="All Day";
}

                           /* try {
                                dd=getNextDate(d1);
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }*/

                            if(d.equals(d1)) {
                               Lcontactstock.add(new Eventdata(l_title,df,df2,location));

                            }
                        } while ((l_managedCursor.moveToNext()));
                        //   m_text_event.setText(l_displayText.toString());
                    }
                   // l_managedCursor.close();
                    g.setcal(1);
                   // e.setText("true");
                  clList.setAdapter(new Eventadapter(getApplicationContext(), Lcontactstock));

                clList.setOnItemClickListener(new DrawerItemClickListener());

                } else {
                  //  Toast.makeText(getApplicationContext(), "Switch is OFF", Toast.LENGTH_LONG).show();
                    g.setcal(0);
                   // e.setText("false");
                    clList.setVisibility(View.INVISIBLE);
                }

            }
        });
        if(switch1.isChecked()){
            Uri l_eventUri;
            if (Build.VERSION.SDK_INT >= 8 ) {
                l_eventUri = Uri.parse("content://com.android.calendar/events");
            } else {
                l_eventUri = Uri.parse("content://calendar/events");
            }
            clList.setVisibility(View.VISIBLE);
            Lcontactstock.clear();
            String[] l_projection = new String[]{"title", "dtstart","eventLocation"};
            long time= System.currentTimeMillis();
            Cursor l_managedCursor = getApplicationContext().getContentResolver().query(l_eventUri, l_projection, null, null, "dtstart DESC, dtend DESC");
            //Cursor l_managedCursor = this.managedQuery(l_eventUri, l_projection, null, null, null);
            if (l_managedCursor.moveToFirst()) {
                int l_cnt = 0;
                String l_title;
                String l_begin;
                String l_begin1;
                String l_end;
                String location;
                //StringBuilder l_displayText = new StringBuilder();
                int l_colTitle = l_managedCursor.getColumnIndex(l_projection[0]);
                int l_colBegin = l_managedCursor.getColumnIndex(l_projection[1]);
                int eventLocation = l_managedCursor.getColumnIndex(l_projection[2]);
                //  int l_colEnd = l_managedCursor.getColumnIndex(l_projection[1]);
                do {
                    l_title = l_managedCursor.getString(l_colTitle);
                    l_begin = getDateTimeStr(l_managedCursor.getString(l_colBegin));
                    location = l_managedCursor.getString(eventLocation);


                    l_begin1 =l_managedCursor.getString(l_colBegin);
                    iii = Long.valueOf(l_begin1).longValue() ;
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                    SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT1);
                    SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_FORMAT2);
                    SimpleDateFormat sdf11 = new SimpleDateFormat(DATE_FORMAT11);
                    //   Date dateend=new Date(jjj);
                    Date date=new Date(iii);
                    String df=sdf1.format(date);
                    String d=sdf11.format(date);
                    String df2=sdf2.format(date);
                    //   String dend=sdf.format(dateend);
                    Date date1=new Date(System.currentTimeMillis());
                    String d1=sdf11.format(date1);
                    if(df2.equals("05:30:00"))
                    {
                        df2="All Day";
                    }
                    /*try {
                        dd=getNextDate(d1);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }*/

                    if(d.equals(d1)) {
                       Lcontactstock.add(new Eventdata(l_title, df,df2,location));
                        //  iii = (int) System.currentTimeMillis();
                       // Toast.makeText(getApplicationContext(), ""+l_title + df + df2, Toast.LENGTH_LONG).show();
                    }

                } while((l_managedCursor.moveToNext()));
                //   m_text_event.setText(l_displayText.toString());
            }
           // l_managedCursor.close();
            g.setcal(1);
           // e.setText("true");
            clList.setAdapter(new Eventadapter(getApplicationContext(), Lcontactstock));
            clList.setOnItemClickListener(new DrawerItemClickListener());
        }
        else {
            g.setcal(0);
            // Toast.makeText(getActivity(), "OFF", Toast.LENGTH_LONG).show();
         //   e.setText("false");
            clList.setVisibility(View.INVISIBLE);
        }
    }


    public static String getDateTimeStr(String p_time_in_millis) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        Date l_time = new Date(Long.parseLong(p_time_in_millis));
        return sdf.format(l_time);
    }
    public static String getNextDate(String  curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(final AdapterView parent, final View view, int position, long id) {
            TextView t = (TextView) view.findViewById(R.id.contact_name_log);
            TextView ttt = (TextView) view.findViewById(R.id.contact_number_log);
            TextView ll = (TextView) view.findViewById(R.id.location);
            final String location = ll.getText().toString();
            final String name = t.getText().toString();
            final String number = ttt.getText().toString();
            if (!location.equals("")) {
                AlertDialog.Builder alert = new AlertDialog.Builder(Calendar_main.this);
                String s = "Do you want to use this meeting location as your destination?" + location;
                alert.setTitle("Set Destination?");
                alert.setMessage(s);


                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared1.edit();
                        editor.putString("destination", location);
                        editor.putString("Msg",name);
                        editor.apply();
                      //  new Set1().execute();
                        Intent i = new Intent(Calendar_main.this, Share_main.class);
                        i.putExtra("cactivity1", 33);
                        startActivity(i);
                        finish();

                        // Do something with value!
                        //textview.setText(input.getText().toString());
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared1.edit();

                        editor.putString("Msg",name);
                        editor.apply();
                        Intent i = new Intent(Calendar_main.this, Share_main.class);
                        i.putExtra("cactivity1", 33);


                        startActivity(i);
                        finish();
                    }
                });

                alert.show();
            } else {
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();

                editor.putString("Msg",name);
                editor.apply();
                Intent i = new Intent(Calendar_main.this, Share_main.class);
                i.putExtra("cactivity1", 33);


                startActivity(i);
                finish();

            }
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

    @Override
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
                Intent i = new Intent(Calendar_main.this, MainActivity.class);
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

