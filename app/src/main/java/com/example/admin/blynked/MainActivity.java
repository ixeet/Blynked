package com.example.admin.blynked;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;



import android.accounts.Account;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends ActionBarActivity implements LocationListener, OnMapReadyCallback {

    static int kk=0;
    Toolbar tool;
    TextView sharepop;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    private Marker customMarker,customMarker1,customMarker2;
    CustomAdapter cAdapter;
    ListView lv;
    String textName;
    private static final String SHARE_URL ="http://191.239.57.54:8080/Blynk/getLocationByImeiNo";
    String name,imagePreferance = null;
    DrawerLayout drawerLayout;
    private static final int CONTAINER = R.id.fragment_container;
    LinearLayout r1;
    LinearLayout r2;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    private int mTitle = R.string.app_name;
    Bitmap selectedImage;
    public static final String STORAGE_IMAGE = "Image";
    public static final String STORAGE_LOC = "location";
    Integer[] img_id = {
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_request_menu_drawer,


            R.mipmap.icn_favourite_menu_drawer,
            R.mipmap.icn_calendar_menu_drawer,
            R.mipmap.icn_history_menu_drawer,
            R.mipmap.icn_settings_menu_drawer,



    };
    String userName;
 String clat,clong;
    Double clat1,clong1;
    Sharesqlite sqliteHelper;
    float speed;
    final String[] items = {"Priyanka", "Ankur", "Ankit"};
    final boolean[] selected = {false, false, false};
    ProgressDialog pDialog;
    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map","Satellite", "Terrain", "Hybrid"};
    final CharSequence[] people={};
    double []lat={28,29,28};
    double []lon={77,75,77.5};
    TextView t111;
    ImageView img_main;

    private static int MIN_TIME_BW_UPDATES = 35000; // 5 sec
    private static int MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    GoogleMap map;
    Marker lastOpenned = null;
    String lp, add;
    String[] k = new String[2];
    String state, address, city, country, knownName;
    Geocoder geocoder;
    List<Address> addresses;
    Boolean isGPSEnabled;
    private int width, height;
    private Polyline newPolyline;
    LatLng dest, l;
    int flag;
    int sstatus;
    Globals g;
    String id1,item1;
    int flag1;
    Double lat1,lon1;
    String  textEmail;
    private LatLngBounds latlngBounds;
    Location location,ll;
    private static final String LOG_TAG = "ExampleApp";
    private static final String PLACES_API_BASE1 = "http://maps.googleapis.com/maps/api/directions/json";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    TextView t1,t2;
    Spinner spinner;
    int hid;
    String id;
    LocationManager locationManager;
    String selState;
    String bestProvider,imei;
    SupportMapFragment supportMapFragment;
    Button share;
    Handler mHandler;
    static final int LOCATION_SETTINGS_REQUEST = 1;
    private static final String API_KEY = "AIzaSyB9vcuLI-1dSeJ68Z9xvdm2j4TsLTQw7_A";
    TextView me,st,spd;
    ImageView dp,dp1;
    int key=0;
    String data1=null;
    RelativeLayout m11,s11;
    int key1=0;
    SlidingDrawer sDrawer;
    RelativeLayout sImg;
    BitmapDescriptor icon1;

    private Animation slideRight,slideLeft;
    TextView t11;
    String userImageUrl;
    Boolean state1;
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar1;
    CircleProgressBar progressBar;
    Button sharem,requestm,favm;
    SqliteHelper4 sqliteHelper4;
    public static final String STORAGE1 = "SenddataPreferences";
    public static final String STORAGE_IMEI = "SaveIMEI";
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g = (Globals)getApplication();
    if (!isGooglePlayServicesAvailable()) {
        finish();
    }
        final ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();
        statusCheck();
        sqliteHelper4 = new SqliteHelper4(this);
        sqliteHelper = new Sharesqlite(this);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        data1 = getIntent().getDataString();
        if(data1!=null) {

            //  String data11=data1.toString();
            imei = data1.substring(data1.length() - 15);
            g.setshare(2);
            SharedPreferences sharedPreferences1 =getSharedPreferences(STORAGE_IMEI, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("imei",imei);
            editor.apply();

        }

       /* state1=hasActiveInternetConnection();
        Toast.makeText(getApplicationContext(),""+state1,Toast.LENGTH_LONG).show();*/

        //this.mHandler = new Handler();
        sDrawer=(SlidingDrawer)findViewById(R.id.slide);
        sImg=(RelativeLayout)findViewById(R.id.footer);

        sDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                // sImg.setImageDrawable(getResources().getDrawable(R.drawable.img_right_arrowback));
            }
        });
        sDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                // sImg.setImageDrawable(getResources().getDrawable(R.drawable.img_right_arrow));
            }
        });
        //  this.mHandler.postDelayed(m_Runnable,5000);
        tool = (Toolbar) findViewById(R.id.tool_bar);
        requestm=(Button)findViewById(R.id.requestm);
        favm=(Button)findViewById(R.id.favm);
        sharem=(Button)findViewById(R.id.sharem);
        sharem.setText("Auto-Share");
        requestm.setText("Share");
        favm.setText("Favourite");
        dp=(ImageView)findViewById(R.id.dp);

        dp1=(ImageView)findViewById(R.id.dp1);
        me=(TextView)findViewById(R.id.me);
        st=(TextView)findViewById(R.id.st);
        spd=(TextView)findViewById(R.id.spd);

       /* data1 = getIntent().addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).getDataString();


      if(data1!=null)
        {

          //  String data11=data1.toString();
            String sdata = data1.substring(data1.length() - 15);
String idd=""+g.gets();
            sqliteHelper.saveUser(idd,sdata);
           // Toast.makeText(getApplicationContext(), ""+g.gets()+" imei"+sdata, Toast.LENGTH_LONG).show();
            int p=g.gets()+1;
            g.sets(p);

        }
        else
        {
          //  Toast.makeText(getApplicationContext(), "noo", Toast.LENGTH_LONG).show();

        }
        if(sqliteHelper.dbSyncCount() != 0) {

            JSONObject Parent = new JSONObject();
            try {
                Parent.put("IMEI", sqliteHelper.composeJSONfromSQLite());
            } catch (JSONException e) {
                e.printStackTrace();
            }
          //  Toast.makeText(getApplicationContext(), "" + result, Toast.LENGTH_LONG).show();
            JSONObject jobj= null;
            try {
                jobj = new JSONObject(Parent.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String c = null;
            try {
                c = jobj.getString("IMEI");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                JSONArray jArray = new JSONArray(c);
                Parent.put("IMEI", jArray);
                Toast.makeText(getApplicationContext(), "" + Parent, Toast.LENGTH_LONG).show();
                Log.d("araaa",Parent.toString());
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(SHARE_URL);
                StringEntity se = new StringEntity( Parent.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setHeader("Accept", "application/json");
                post.setHeader("Content-type", "application/json");
                post.setEntity(se);
                HttpResponse response = client.execute(post);
                //  Log.d("Http Post Response:", response.toString());
                //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);
                Log.d("Login attempt",Parent.toString());
                InputStream in = response.getEntity().getContent();
                StringBuilder stringbuilder = new StringBuilder();
                BufferedReader bfrd = new BufferedReader(new InputStreamReader(in),1024);
                String line;
                while((line = bfrd.readLine()) != null)
                    stringbuilder.append(line);

                pk = stringbuilder.toString();

                Log.d("Respopnse",pk);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
           // Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }*/
        //if(!data.equals("null")) {
          //
      //  }
      //  else {

      //  }
        //   t111=(TextView)findViewById(R.id.textView1);
        //  t111.setText("I am here,HAHAHA");

        // slideRight = AnimationUtils.loadAnimation(this, R.anim.slide_right);
        //  slideLeft = AnimationUtils.loadAnimation(this, R.anim.slide_left);
        //  slideRight.setAnimationListener(animationListener);
       // final Button btn=(Button)findViewById(R.id.handle);
       /* try {
            System.out.println("On Home Page***"
                    + AbstractGetNameTask.GOOGLE_USER_DATA);
            JSONObject profileData = new JSONObject(
                    AbstractGetNameTask.GOOGLE_USER_DATA);

            if (profileData.has("picture")) {
                userImageUrl = profileData.getString("picture");
                if(isNetworkAvailable()) {
                    new GetImageFromUrl().execute(userImageUrl);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No internet",Toast.LENGTH_LONG).show();
                }
            }
            if (profileData.has("name")) {
                textName = profileData.getString("name");
                // textViewName.setText(textName);
            }
          *//*  if (profileData.has("gender")) {
                textGender = profileData.getString("gender");
              //  textViewGender.setText(textGender);
            }
            if (profileData.has("birthday")) {
                textBirthday = profileData.getString("birthday");
               // textViewBirthday.setText(textBirthday);
            }
*//*
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        if (tool != null) {
            setSupportActionBar(tool);
            // tool.setNavigationIcon(R.drawable.navicon);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            tool.setNavigationIcon(R.mipmap.menu_converted);
            //getSupportActionBar().setHomeButtonEnabled(true);
            mDrawerTitle = getTitle();
            name_list = getResources().getStringArray(R.array.item_name);
            //img_list=getResources().getIntArray(R.array.img_id);
            lv = (ListView) findViewById(R.id.nav_list);
            //  t11 = (TextView) findViewById(R.id.t1);
            //  img_main = (ImageView) findViewById(R.id.img_icon);
            //  t11.setText("Please set your profile");
            //  Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            //  img_main.setImageBitmap(icon);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);

            // img_main.setOnClickListener(new View.OnClickListener() {
            //   @Override
            //    public void onClick(View view) {
            //          Intent i = new Intent(MainActivity.this, Image_main.class);
            //           startActivity(i);
            //        }
            //  });
            countrylist = new ArrayList<DataModel>();
            for (int i = 0; i < name_list.length; i++) {
                // Toast.makeText(getApplicationContext(),""+img_list[i],Toast.LENGTH_LONG).show();
                DataModel country = new DataModel(name_list[i], img_id[i]);
                countrylist.add(country);
            }

            cAdapter = new CustomAdapter(getApplicationContext(), countrylist);
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
       // Intent intent = getIntent();
       // textEmail = intent.getStringExtra("email_id");
       // System.out.println(textEmail);
      //  textViewEmail.setText(textEmail);

        /**
         * get user data from google account
         */


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          //  RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
                                Intent ii1 = new Intent(MainActivity.this, Share_main.class);
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
                                Intent ii1 = new Intent(MainActivity.this, Share_main1.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);

                        break;
                    case 2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent i2 = new Intent(MainActivity.this, Request_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:

                        Intent i22 = new Intent(MainActivity.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent i222 = new Intent(MainActivity.this, Calendar_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i222);
                        finish();
                        break;
                    case 5:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(MainActivity.this,HistoryActivity.class);
                        startActivity(ii);
                        finish();

                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ipi = new Intent(MainActivity.this,Setting_main.class);
                        startActivity(ipi);
                        finish();
                        break;

                }


            }
        });


     /*   if(g.getsstatus()==1)
        {
            st.setText("Active");
            r2.setVisibility(View.GONE);
            r1.setVisibility(View.VISIBLE);


        }
        else
        {
            st.setText("Not Sharing");
            r1.setVisibility(View.GONE);
            r2.setVisibility(View.VISIBLE);

        }
*/
        flag=g.getFlag();
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {
        name = sharedPreferences.getString("name", "");

        imagePreferance = sharedPreferences.getString("imagePreferance", "");
        if(!imagePreferance.equals("")) {
            dp.setImageBitmap(decodeBase64(imagePreferance));
        }
        //  if( imagePreferance!=null ) {
       /* if (!name.equals("")) {
            //  t11.setText(name);
            me.setText("Me "+ "("+name+")");
            //img_main.setImageBitmap(decodeBase64(imagePreferance));

            dp.setImageBitmap(decodeBase64(imagePreferance));
        }

        //  }
        else {
            // t11.setText("Please set your profile");
            //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            //    img_main.setImageBitmap(icon1);
        }*/
        supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);


     /*   sharepop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent ii1 = new Intent(MainActivity.this, Share_main.class);
                //statusCheck();
                startActivity(ii1);
                finish();



            }});*/
        sharem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                SharedPreferences.Editor editorr = shared1.edit();

                editorr.clear();
                editorr.commit();
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
                        Intent ii1 = new Intent(MainActivity.this, Share_main1.class);
                        //statusCheck();
                        startActivity(ii1);
                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);




            }});
      /*  m11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                progressBar = (ProgressBar) findViewById(R.id.pBar);
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

                    @Override
                    public void run() {
                        Intent ii1 = new Intent(MainActivity.this, Share_main.class);
                        //statusCheck();
                        startActivity(ii1);
                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);

            }});*/
      /*  s11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
               *//* Intent ii1 = new Intent(MainActivity.this, Share_main.class);
                //statusCheck();
                startActivity(ii1);
                finish();*//*
                Toast.makeText(getApplicationContext(), "Using services we can stop sharing location", Toast.LENGTH_LONG).show();

            }});*/
        requestm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
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
                        Intent ii1 = new Intent(MainActivity.this, Share_main.class);
                        //statusCheck();
                        startActivity(ii1);
                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);

            }
        });
        favm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i22 = new Intent(MainActivity.this, Favourite_main.class);
                // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i22);
                finish();
            }});
       // Toast.makeText(getApplicationContext(), ""+map, Toast.LENGTH_LONG).show();
        /*final String fDialogTitle = "View Selected";
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(fDialogTitle);

        // Find the current map type to pre-check the item representing the current state.
        int checkItem = 0;

        builder.setSingleChoiceItems(
                People,
                checkItem,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        // Locally create a finalised object.
*//*for(item=0;item<People.length;item++)
{

}*//*
                        // Perform an action depending on which item was selected.
                        if (item==0) {
                            g.setyes(1);
                            map.clear();
                            View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                           // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                           // ivv.setImageBitmap(decodeBase64(imagePreferance));
                            //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                            //  numTxt.setText("27");
                            customMarker = map.addMarker(new MarkerOptions()
                                    .position(
                                            new LatLng(lat[item], lon[item])).title(""+People[item]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                            //marker.setIcon(icon1);


                            customMarker.showInfoWindow();
                            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                public boolean onMarkerClick(Marker marker) {
                                    // Check if there is an open info window
                                    if (lastOpenned != null) {
                                        // Close the info window
                                        lastOpenned.hideInfoWindow();

                                        // Is the marker the same marker that was already open
                                        if (lastOpenned.equals(marker)) {
                                            // Nullify the lastOpenned object
                                            lastOpenned = null;
                                            // Return so that the info window isn't openned again
                                            return true;
                                        }
                                    }

                                    // Open the info window for the marker
                                    customMarker.showInfoWindow();
                                    // Re-assign the last openned such that we can close it later
                                    lastOpenned =customMarker;

                                    // Event was handled by our code do not launch default behaviour.
                                    return true;
                                }
                            });

                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lat[item], lon[item]), 15));
                            me.setText(""+People[item]);

                        } else if (item==1) {
                            {
                             g.setyes(2);
                                if(!(""+People[item]).equals("All")) {
                                    map.clear();
                                    View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                    // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                    // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                    // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                    //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                    //  numTxt.setText("27");
                                    customMarker = map.addMarker(new MarkerOptions()
                                            .position(
                                                    new LatLng(lat[item], lon[item])).title(""+People[item]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                    //marker.setIcon(icon1);


                                    customMarker.showInfoWindow();
                                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        public boolean onMarkerClick(Marker marker) {
                                            // Check if there is an open info window
                                            if (lastOpenned != null) {
                                                // Close the info window
                                                lastOpenned.hideInfoWindow();

                                                // Is the marker the same marker that was already open
                                                if (lastOpenned.equals(marker)) {
                                                    // Nullify the lastOpenned object
                                                    lastOpenned = null;
                                                    // Return so that the info window isn't openned again
                                                    return true;
                                                }
                                            }

                                            // Open the info window for the marker
                                            customMarker.showInfoWindow();
                                            // Re-assign the last openned such that we can close it later
                                            lastOpenned =customMarker;

                                            // Event was handled by our code do not launch default behaviour.
                                            return true;
                                        }
                                    });

                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                            new LatLng(lat[item], lon[item]), 15));
                                    me.setText(""+People[item]);
                                }

                            }
                        } else if (item==2) {
                         g.setyes(3);
                            if(!(""+People[item]).equals("All")) {
                                map.clear();
                                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                //  numTxt.setText("27");
                                customMarker = map.addMarker(new MarkerOptions()
                                        .position(
                                                new LatLng(lat[item], lon[item])).title(""+People[item]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                //marker.setIcon(icon1);


                                customMarker.showInfoWindow();
                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    public boolean onMarkerClick(Marker marker) {
                                        // Check if there is an open info window
                                        if (lastOpenned != null) {
                                            // Close the info window
                                            lastOpenned.hideInfoWindow();

                                            // Is the marker the same marker that was already open
                                            if (lastOpenned.equals(marker)) {
                                                // Nullify the lastOpenned object
                                                lastOpenned = null;
                                                // Return so that the info window isn't openned again
                                                return true;
                                            }
                                        }

                                        // Open the info window for the marker
                                        customMarker.showInfoWindow();
                                        // Re-assign the last openned such that we can close it later
                                        lastOpenned =customMarker;

                                        // Event was handled by our code do not launch default behaviour.
                                        return true;
                                    }
                                });

                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lat[item], lon[item]), 15));
                                me.setText(""+People[item]);
                            }
                            else {
                                    g.setyes(4);
                                    View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                    // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                    // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                    //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                    //  numTxt.setText("27");
                                    customMarker = map.addMarker(new MarkerOptions()
                                            .position(
                                                    new LatLng(lat[0], lon[0])).title(""+People[0]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                    //marker.setIcon(icon1);

                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    public boolean onMarkerClick(Marker customMarker) {
                                        // Check if there is an open info window
                                        if (lastOpenned != null) {
                                            // Close the info window
                                            lastOpenned.hideInfoWindow();

                                            // Is the marker the same marker that was already open
                                            if (lastOpenned.equals(customMarker)) {
                                                // Nullify the lastOpenned object
                                                lastOpenned = null;
                                                // Return so that the info window isn't openned again
                                                return true;
                                            }
                                        }

                                        // Open the info window for the marker
                                        customMarker.showInfoWindow();
                                        me.setText(""+People[0]);
                                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                new LatLng(lat[0], lon[0]), 15));
                                        // Re-assign the last openned such that we can close it later
                                        lastOpenned =customMarker;

                                        // Event was handled by our code do not launch default behaviour.
                                        return true;
                                    }
                                });

                                View marker1 = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                //  numTxt.setText("27");
                                customMarker1 = map.addMarker(new MarkerOptions()
                                        .position(
                                                new LatLng(lat[1], lon[1])).title(""+People[1]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker1))));

                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    public boolean onMarkerClick(Marker customMarker1) {
                                        // Check if there is an open info window
                                        if (lastOpenned != null) {
                                            // Close the info window
                                            lastOpenned.hideInfoWindow();

                                            // Is the marker the same marker that was already open
                                            if (lastOpenned.equals(customMarker1)) {
                                                // Nullify the lastOpenned object
                                                lastOpenned = null;
                                                // Return so that the info window isn't openned again
                                                return true;
                                            }
                                        }

                                        // Open the info window for the marker
                                        customMarker1.showInfoWindow();
                                        me.setText(""+People[1]);

                                        // Re-assign the last openned such that we can close it later
                                        lastOpenned =customMarker1;

                                        // Event was handled by our code do not launch default behaviour.
                                        return true;
                                    }
                                });


                                LatLng l = new LatLng(lat[0], lon[0]);
                                LatLng dest = new LatLng(lat[1], lon[1]);
                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                builder.include(l);
                                builder.include(dest);
                                LatLngBounds bounds = builder.build();
                                map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,10));

                            }
                            // invite Japanese

                        } else if (item==3) {
                            g.setyes(5);
                            if(!(""+People[item]).equals("All")) {
                                map.clear();
                                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                //  numTxt.setText("27");
                                customMarker = map.addMarker(new MarkerOptions()
                                        .position(
                                                new LatLng(lat[item], lon[item])).title(""+People[item]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                //marker.setIcon(icon1);


                                customMarker.showInfoWindow();
                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    public boolean onMarkerClick(Marker marker) {
                                        // Check if there is an open info window
                                        if (lastOpenned != null) {
                                            // Close the info window
                                            lastOpenned.hideInfoWindow();

                                            // Is the marker the same marker that was already open
                                            if (lastOpenned.equals(marker)) {
                                                // Nullify the lastOpenned object
                                                lastOpenned = null;

                                                // Return so that the info window isn't openned again
                                                return true;
                                            }
                                        }

                                        // Open the info window for the marker
                                        customMarker.showInfoWindow();
                                        // Re-assign the last openned such that we can close it later
                                        lastOpenned =customMarker;

                                        // Event was handled by our code do not launch default behaviour.
                                        return true;
                                    }
                                });

                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lat[item], lon[item]), 15));
                                me.setText(""+People[item]);
                            }
                            else
                            {
                                    g.setyes(6);
                                    View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                    // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                    // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                    //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                    //  numTxt.setText("27");
                                    customMarker = map.addMarker(new MarkerOptions()
                                            .position(
                                                    new LatLng(lat[0], lon[0])).title(""+People[0]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                    //marker.setIcon(icon1);

                                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        public boolean onMarkerClick(Marker customMarker) {
                                            // Check if there is an open info window
                                            if (lastOpenned != null) {
                                                // Close the info window
                                                lastOpenned.hideInfoWindow();

                                                // Is the marker the same marker that was already open
                                                if (lastOpenned.equals(customMarker)) {
                                                    // Nullify the lastOpenned object
                                                    lastOpenned = null;
                                                    // Return so that the info window isn't openned again
                                                    return true;
                                                }
                                            }

                                            // Open the info window for the marker
                                            customMarker.showInfoWindow();
                                            me.setText(customMarker.getTitle());
                                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                    new LatLng(customMarker.getPosition().latitude,customMarker.getPosition().longitude), 15));
                                            // Re-assign the last openned such that we can close it later
                                            lastOpenned =customMarker;

                                            // Event was handled by our code do not launch default behaviour.
                                            return true;
                                        }
                                    });

                                    View marker1 = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                    // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                    // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                    //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                    //  numTxt.setText("27");
                                    customMarker1 = map.addMarker(new MarkerOptions()
                                            .position(
                                                    new LatLng(lat[1], lon[1])).title(""+People[1]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker1))));

                                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        public boolean onMarkerClick(Marker customMarker1) {
                                            // Check if there is an open info window
                                            if (lastOpenned != null) {
                                                // Close the info window
                                                lastOpenned.hideInfoWindow();

                                                // Is the marker the same marker that was already open
                                                if (lastOpenned.equals(customMarker1)) {
                                                    // Nullify the lastOpenned object
                                                    lastOpenned = null;
                                                    // Return so that the info window isn't openned again
                                                    return true;
                                                }
                                            }

                                            // Open the info window for the marker
                                            customMarker1.showInfoWindow();
                                            me.setText(customMarker1.getTitle());
                                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                    new LatLng(customMarker1.getPosition().latitude,customMarker1.getPosition().longitude), 15));
                                            // Re-assign the last openned such that we can close it later
                                            lastOpenned =customMarker1;

                                            // Event was handled by our code do not launch default behaviour.
                                            return true;
                                        }
                                    });
                                    View marker2 = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                    // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                    // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                    //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                    //  numTxt.setText("27");
                                    customMarker2 = map.addMarker(new MarkerOptions()
                                            .position(
                                                    new LatLng(lat[2], lon[2])).title(""+People[2]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker2))));

                                    map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                        public boolean onMarkerClick(Marker customMarker2) {
                                            // Check if there is an open info window
                                            if (lastOpenned != null) {
                                                // Close the info window
                                                lastOpenned.hideInfoWindow();

                                                // Is the marker the same marker that was already open
                                                if (lastOpenned.equals(customMarker2)) {
                                                    // Nullify the lastOpenned object
                                                    lastOpenned = null;
                                                    // Return so that the info window isn't openned again
                                                    return true;
                                                }
                                            }

                                            // Open the info window for the marker
                                            customMarker2.showInfoWindow();
                                            me.setText(customMarker2.getTitle());
                                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                    new LatLng(customMarker2.getPosition().latitude,customMarker2.getPosition().longitude), 15));
                                            // Re-assign the last openned such that we can close it later
                                            lastOpenned =customMarker2;

                                            // Event was handled by our code do not launch default behaviour.
                                            return true;
                                        }
                                    });

                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                                builder.include(customMarker.getPosition());
                                builder.include(customMarker1.getPosition());
                                builder.include(customMarker2.getPosition());
                                LatLngBounds bounds = builder.build();
                                map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,10));
                                }

                            // invite Chinese

                        }
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.setCanceledOnTouchOutside(true);
        fMapTypeDialog.show();*/
     /*   if(g.getyes()==0) {


            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setTitle("View selected")
                    .setMultiChoiceItems(items, selected, new DialogInterface.OnMultiChoiceClickListener() {
                        public void onClick(DialogInterface dialogInterface, int item, boolean b) {
                         *//*   hid = g.gethid();
                            id = Integer.toString(hid);


                            //  boolean result = sqliteHelper1.saveUser(id,time1);
                            hid = Integer.parseInt(id);
                            hid = hid + 1;
                            g.sethid(hid);*//*
                            String ip=String.valueOf(item);
                            sqliteHelper4.saveUser(ip, 0, items[item], lat[item], lon[item]);
                            if (b ==true) {

                         *//*   people[kk++]=items[item];
                            Toast.makeText(getApplicationContext(),kk,Toast.LENGTH_LONG).show();*//*

                                g.setyes(1);
                               // Toast.makeText(getApplicationContext(),""+ip+"k liye 0",Toast.LENGTH_LONG).show();
                                sqliteHelper4.update(ip,0);
                                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                //  numTxt.setText("27");
                                customMarker = map.addMarker(new MarkerOptions()
                                        .position(
                                                new LatLng(lat[item], lon[item])).title("" + items[item]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                //marker.setIcon(icon1);

                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lat[item], lon[item]), 5));
                                customMarker.showInfoWindow();
                                me.setText("You have got Blynked of "+ items[item]+" for");
                                spd.setText("20");
                                st.setText("minutes.");
                                dp1.setVisibility(View.VISIBLE);

          *//*  LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(customMarker.getPosition());

                        LatLngBounds bounds = builder.build();
                        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,10));*//*
                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    public boolean onMarkerClick(Marker customMarker) {
                                        // Check if there is an open info window
                               *//*   if (lastOpenned != null) {
                                        // Close the info window
                                     //   lastOpenned.hideInfoWindow();

                                        // Is the marker the same marker that was already open
                                        if (lastOpenned.equals(customMarker)) {
                                            // Nullify the lastOpenned object
                                            lastOpenned = null;
                                            // Return so that the info window isn't openned again
                                            return true;
                                        }
                                    }*//*

                                        // Open the info window for the marker
                                        customMarker.showInfoWindow();
                                        me.setText("You have got Blynked of "+ customMarker.getTitle()+" for");
                                        spd.setText("20");
                                        st.setText("minutes.");
                                        dp1.setVisibility(View.VISIBLE);
                                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                                new LatLng(customMarker.getPosition().latitude, customMarker.getPosition().longitude), 6));
                                        // Re-assign the last openned such that we can close it later
                                        lastOpenned = customMarker;

                                        // Event was handled by our code do not launch default behaviour.
                                        return true;
                                    }
                                });


                            }
                            else
                            {
                                if(customMarker.getPosition().latitude==lat[item] && customMarker.getPosition().longitude==lon[item]) {
                                    customMarker.setVisible(false);
                                    customMarker.remove();
                                    sqliteHelper4.update(ip,1);
                                  //  Toast.makeText(getApplicationContext(),""+ip+"k liye 1",Toast.LENGTH_LONG).show();
                                    me.setText("You have no Blynked and are moving at");
                                    spd.setText(""+speed);
                                    st.setText("Km/hr");
                                    dp1.setVisibility(View.INVISIBLE);
                                }
                                else {
                                    //Toast.makeText(getApplicationContext(), "Check removed", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                    });

            builder1.create().show();
        }*/
        if(g.getshare()==2) {


            scheduler.scheduleAtFixedRate(new Runnable() {

                public void run() {
                    Log.i("hello", "world");
                    runOnUiThread(new Runnable() {
                        public void run() {
                            SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_IMEI, Context.MODE_PRIVATE);

                            imei = sharedPreferences.getString("imei", "");
                            g.setshare(1);
                            new GetLoc().execute();

                            SharedPreferences sharedPreferences1 = getSharedPreferences(STORAGE_LOC, Context.MODE_PRIVATE);

                            clat = sharedPreferences1.getString("clat", "");
                            clong= sharedPreferences1.getString("clong","");
                            userName = sharedPreferences1.getString("userName", "");
                            if(!clat.equals("")) {
                                 clat1 = Double.parseDouble(clat);
                                 clong1 = Double.parseDouble(clong);
                                map.clear();
                                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.proff);
                                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                                ivv.setImageBitmap(icon);
                                // ivv.setImageBitmap(decodeBase64(imagePreferance));
                                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                                //  numTxt.setText("27");
                                customMarker = map.addMarker(new MarkerOptions()
                                        .position(
                                                new LatLng(clat1, clong1)).title(userName).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                                //marker.setIcon(icon1);


                                customMarker.showInfoWindow();
                                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                    public boolean onMarkerClick(Marker marker) {
                                        // Check if there is an open info window
                                        if (lastOpenned != null) {
                                            // Close the info window
                                            lastOpenned.hideInfoWindow();

                                            // Is the marker the same marker that was already open
                                            if (lastOpenned.equals(marker)) {
                                                // Nullify the lastOpenned object
                                                lastOpenned = null;
                                                // Return so that the info window isn't openned again
                                                return true;
                                            }
                                        }

                                        // Open the info window for the marker
                                        customMarker.showInfoWindow();
                                        // Re-assign the last openned such that we can close it later
                                        lastOpenned = customMarker;

                                        // Event was handled by our code do not launch default behaviour.
                                        return true;
                                    }
                                });

                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(clat1, clong1), 15));
                                me.setText("You are viewing location of "+userName);
                                spd.setVisibility(View.GONE);
                                st.setVisibility(View.GONE);
                                 }
                           // Toast.makeText(getApplicationContext(), ""+clat+""+clong+""+userName, Toast.LENGTH_LONG).show();
                          //  Toast.makeText(getApplicationContext(), imei, Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }, 0, 10, TimeUnit.SECONDS);
            scheduler.schedule(new Runnable() {
                public void run() {
                    SharedPreferences shared1 = getSharedPreferences(STORAGE_LOC, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editorr = shared1.edit();

                    editorr.clear();
                    editorr.commit();
                    g.setshare(0);
                    Log.d("Stopped",""+g.getshare());
                    scheduler.shutdown();


                }
            }, 65, TimeUnit.SECONDS);
        }
    }


   /* public class GetImageFromUrl extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
            }
            return map;
        }

        // Sets the Bitmap returned by doInBackground
        @Override
        protected void onPostExecute(Bitmap result) {
            // imageProfile.setImageBitmap(result);
            SharedPreferences s1111 = getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
            imagePreferance = s1111.getString("imagePreferance", "");
            name = s1111.getString("name", "");
//if(decodeBase64(imagePreferance)!=null)
            if (imagePreferance.equals("") && name.equals("")) {

                SharedPreferences.Editor editor = s1111.edit();
                // editor.clear();
                editor.putString("imagePreferance", encodeTobase64(result));
                editor.putString("name", textName);

                editor.apply();

            }
            SharedPreferences s11111 = getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);

            //   boolean hasDrawable = (img_main.getDrawable() != null);
            //  if(hasDrawable) {
            name = s11111.getString("name", "");
            imagePreferance = s11111.getString("imagePreferance", "");
            //me.setText("Me "+ "(" +name+")");
            //img_main.setImageBitmap(decodeBase64(imagePreferance));

            dp.setImageBitmap(decodeBase64(imagePreferance));

        }
        public  String encodeTobase64(Bitmap image) {
            Bitmap immage = image;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

            Log.d("Image Log:", imageEncoded);
            return imageEncoded;
        }
        // Creates Bitmap from InputStream and returns it
        private Bitmap downloadImage(String url) {
            Bitmap bitmap = null;
            InputStream stream = null;
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inSampleSize = 1;

            try {
                stream = getHttpConnection(url);
                bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
                stream.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return bitmap;
        }

        // Makes HttpURLConnection and returns InputStream
        private InputStream getHttpConnection(String urlString)
                throws IOException {
            InputStream stream = null;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();

            try {
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    stream = httpConnection.getInputStream();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return stream;
        }
    }*/
    private Animation.AnimationListener animationListener = new Animation.AnimationListener(){

        @Override
        public void onAnimationEnd(Animation animation) {
            //  popup.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }};

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        map = googleMap;
        googleMap.setMyLocationEnabled(true);

        // googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        if(g.getFlag()==1)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        else if(g.getFlag()==2)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if(g.getFlag()==3)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if(g.getFlag()==4)
        {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        Button spinner = (Button) findViewById(R.id.spinnerrr);
        spinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String fDialogTitle = "Select Map Type";
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(fDialogTitle);

                // Find the current map type to pre-check the item representing the current state.
                int checkItem = googleMap.getMapType() - 1;

                builder.setSingleChoiceItems(
                        MAP_TYPE_ITEMS,
                        checkItem,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int item) {
                                // Locally create a finalised object.

                                // Perform an action depending on which item was selected.
                                switch (item) {
                                    case 1:
                                        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                        //p=GoogleMap.MAP_TYPE_SATELLITE;
                                        g.setFlag(2);
                                        break;
                                    case 2:
                                        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                        //p=GoogleMap.MAP_TYPE_SATELLITE;
                                        g.setFlag(3);
                                        break;
                                    case 3:
                                        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                        g.setFlag(4);
                                        break;
                                    default:
                                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                        g.setFlag(1);
                                }
                                dialog.dismiss();
                            }
                        }
                );
                AlertDialog fMapTypeDialog = builder.create();
                fMapTypeDialog.setCanceledOnTouchOutside(true);
                fMapTypeDialog.show();

            }
        });
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setPadding(0,0,0,300);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.POWER_LOW);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setBearingAccuracy(Criteria.ACCURACY_LOW);
        criteria.setSpeedAccuracy(Criteria.ACCURACY_MEDIUM);
       // state1=hasActiveInternetConnection();
       // Toast.makeText(getApplicationContext(),""+state1,Toast.LENGTH_LONG).show();
       // criteria.setAccuracy(Criteria.ACCURACY_HIGH);
        bestProvider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(bestProvider);
        ll = location;
        Toast.makeText(getApplicationContext(),"Sharedglob is"+g.getshare(),Toast.LENGTH_LONG).show();
        if(g.getshare()==1)
        {
            SharedPreferences sharedPreferences1 = getSharedPreferences(STORAGE_LOC, Context.MODE_PRIVATE);

            clat = sharedPreferences1.getString("clat", "");
            clong= sharedPreferences1.getString("clong","");
            userName = sharedPreferences1.getString("userName", "");
            Toast.makeText(getApplicationContext(),"onMap ready"+clat+clong,Toast.LENGTH_LONG).show();
            if(!clat.equals("")) {
                clat1 = Double.parseDouble(clat);
                clong1 = Double.parseDouble(clong);

                Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.proff);
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                ivv.setImageBitmap(icon);
                // ivv.setImageBitmap(decodeBase64(imagePreferance));
                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                //  numTxt.setText("27");
                customMarker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(clat1, clong1)).title(userName).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(MainActivity.this, marker))));
                //marker.setIcon(icon1);


                customMarker.showInfoWindow();
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    public boolean onMarkerClick(Marker marker) {
                        // Check if there is an open info window
                        if (lastOpenned != null) {
                            // Close the info window
                            lastOpenned.hideInfoWindow();

                            // Is the marker the same marker that was already open
                            if (lastOpenned.equals(marker)) {
                                // Nullify the lastOpenned object
                                lastOpenned = null;
                                // Return so that the info window isn't openned again
                                return true;
                            }
                        }

                        // Open the info window for the marker
                        customMarker.showInfoWindow();
                        // Re-assign the last openned such that we can close it later
                        lastOpenned = customMarker;

                        // Event was handled by our code do not launch default behaviour.
                        return true;
                    }
                });

                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(clat1, clong1), 15));
                me.setText("You are viewing location of "+userName);
                spd.setVisibility(View.GONE);
                st.setVisibility(View.GONE);
            }
        }
     /*   if(g.getyes()==1)
        {

            int p1 = sqliteHelper4.dbSyncCount();
            int x = p1;

         //   Toast.makeText(getApplicationContext(), p1, Toast.LENGTH_LONG).show();
            while (p1 > 0) {

                final int finalP = x - p1 + 1;
                String p = Integer.toString(finalP);
                Cursor cursor = sqliteHelper4.getUser(p);
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    id1 = cursor.getString(cursor.getColumnIndex("id"));
                    item1 = cursor.getString(cursor.getColumnIndex("item"));
                    // i = Integer.parseInt(idd);
                    flag1 = cursor.getInt(cursor.getColumnIndex("flag"));
                    lat1 = cursor.getDouble(cursor.getColumnIndex("lat"));
                    lon1 = cursor.getDouble(cursor.getColumnIndex("lon"));

                }
                if(flag1==0)
                {

                // Toast.makeText(getApplicationContext(), id1+item1+flag1+lat1+lon1, Toast.LENGTH_LONG).show();
                for (int i = 0; i < items.length; i++) {
                    if (items[i].equals(item1)) {
                        View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                        // ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                        // ivv.setImageBitmap(decodeBase64(imagePreferance));
                        //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                        //  numTxt.setText("27");

                        // Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_LONG).show();

                        customMarker = map.addMarker(new MarkerOptions()
                                .position(
                                        new LatLng(lat[i], lon[i])).title(items[i]).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
                        //marker.setIcon(icon1);

                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                new LatLng(lat[i], lon[i]), 5));
                        customMarker.showInfoWindow();
                        me.setText("You have got Blynked of "+ items[i]+" for");
                        spd.setText("20");
                        st.setText("minutes");
                        dp1.setVisibility(View.VISIBLE);
                     *//* *//**//**//**//*  LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(customMarker.getPosition());

                        LatLngBounds bounds = builder.build();
                        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,10));*//**//**//**//**//*
                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            public boolean onMarkerClick(Marker customMarker) {
                                // Check if there is an open info window
                                *//* if (lastOpenned != null) {
                                        // Close the info window
                                     //   lastOpenned.hideInfoWindow();

                                        // Is the marker the same marker that was already open
                                        if (lastOpenned.equals(customMarker)) {
                                            // Nullify the lastOpenned object
                                            lastOpenned = null;
                                            // Return so that the info window isn't openned again
                                            return true;
                                        }
                                    }*//*

                                // Open the info window for the marker
                                customMarker.showInfoWindow();
                                me.setText("You have got Blynked of "+ customMarker.getTitle()+" for");
                                spd.setText("20");
                                st.setText("minutes.");
                                dp1.setVisibility(View.VISIBLE);

                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(customMarker.getPosition().latitude, customMarker.getPosition().longitude), 6));
                                // Re-assign the last openned such that we can close it later
                                lastOpenned = customMarker;

                                // Event was handled by our code do not launch default behaviour.
                                return true;
                            }

                        });
                    }
                }

            }
                p1--;
            }

        }*/
        // if (location != null) {
        //     onLocationChanged(location);
        //   }
        //   else {
     //   if(hasActiveInternetConnection()) {
        if(isNetworkAvailable())
        {
            Boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isNetworkEnabled) {

                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                onLocationChanged(location);
            } else if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    location = locationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    onLocationChanged(location);

                }
            } else {
                // Toast.makeText(getApplicationContext(), "Neither network provider nor GPS provider is enabled", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            locationManager.removeUpdates(this);
            //Toast.makeText(getApplicationContext(),"No Internet", Toast.LENGTH_LONG).show();
            //bestProvider = locationManager.getBestProvider(criteria, true);
            location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            ll = location;
            if(location!=null) {
                map.clear();
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                ivv.setImageBitmap(decodeBase64(imagePreferance));
                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                //  numTxt.setText("27");
                customMarker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude())).title(name).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
                //marker.setIcon(icon1);


                customMarker.showInfoWindow();
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    public boolean onMarkerClick(Marker marker) {
                        // Check if there is an open info window
                        if (lastOpenned != null) {
                            // Close the info window
                            lastOpenned.hideInfoWindow();

                            // Is the marker the same marker that was already open
                            if (lastOpenned.equals(marker)) {
                                // Nullify the lastOpenned object
                                lastOpenned = null;
                                // Return so that the info window isn't openned again
                                return true;
                            }
                        }

                        // Open the info window for the marker
                        customMarker.showInfoWindow();
                        // Re-assign the last openned such that we can close it later
                        lastOpenned =customMarker;

                        // Event was handled by our code do not launch default behaviour.
                        return true;
                    }
                });

            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 15));
            }
            else
            {
                Toast.makeText(MainActivity.this,"Your phone network is not available!Sorry",Toast.LENGTH_SHORT).show();
            }

        }


        //  }

    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void setUpMapIfNeeded(){
        supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);

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

    /*  private final Runnable m_Runnable = new Runnable()
      {
          public void run()

          {
              //Toast.makeText(refresh.this,"in runnable",Toast.LENGTH_SHORT).show();

              MainActivity.this.mHandler.postDelayed(m_Runnable, 5000);
          }

      };*/
 /* @Override
  public void onDestroy() {
      super.onDestroy();
     // Log.d(msg, "The onDestroy() event");
      locationManager.removeUpdates(this);
  }*/
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(mTitle);
    }

    /*   protected void onPostCreate(Bundle savedInstanceState) {

           super.onPostCreate(savedInstanceState);

           // Sync the toggle state after onRestoreInstanceState has occurred.

           mToggle.syncState();

       }*/
    public void statusCheck()
    {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            showSettingsAlert("GPS");

        }


    }

    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        MainActivity.this.startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
  /*  protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(bestProvider, 0, 0, this);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
 /*   @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // This is also called by the Android framework in onResume(). The map may not be created at
        // this stage yet.
        if (googleMap != null) {
            setLayer((String) parent.getItemAtPosition(position));
        }
    }*/

  /*  @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    private void setLayer(String layerName) {
        if (layerName.equals("normal")) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        } else if (layerName.equals("hybrid")) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);




        } else if (layerName.equals("satellite")) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else if (layerName.equals("terrain")) {
           googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        } else if (layerName.equals("none_map")) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        } else {
            Log.i("LDA", "Error setting layer with name " + layerName);
        }
    }*/

    /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (mToggle.onOptionsItemSelected(item)) {

        }
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }*/
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;*/
        Bitmap bmp = BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
        return bmp;
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {

            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }




    @Override
    public void onLocationChanged(Location location1) {
        //  TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
         speed=location1.getSpeed()*3600/1000;
        if(g.getshare()!=1)

      if(location1!=null) {
          double latitude = location1.getLatitude();
          double longitude = location1.getLongitude();
          //spd.setText(""+location1.getSpeed()*3600/1000 +"km/h");
          //   Toast.makeText(getApplicationContext(),""+location.getSpeed(), Toast.LENGTH_LONG).show();

          //  googleMap.addMarker(new MarkerOptions().position(latLng));
    /*   MarkerOptions mp = new MarkerOptions();

            mp.position(new LatLng(location.getLatitude(), location.getLongitude()));

            mp.title("priyanka");

            googleMap.addMarker(mp);*/
          geocoder = new Geocoder(this, Locale.getDefault());
          try {
              addresses = geocoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

              address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
              city = addresses.get(0).getLocality();
              state = addresses.get(0).getAdminArea();
              country = addresses.get(0).getCountryName();
              String postalCode = addresses.get(0).getPostalCode();
              knownName = addresses.get(0).getFeatureName();
          } catch (Exception e) {

          }

          // Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();
          lp = address + "," + city + "," + country;

              map.clear();

              View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
              ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
              ivv.setImageBitmap(decodeBase64(imagePreferance));
              //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
              //  numTxt.setText("27");
              customMarker = map.addMarker(new MarkerOptions()
                      .position(
                              new LatLng(location1.getLatitude(), location1
                                      .getLongitude())).title(name).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
              //marker.setIcon(icon1);


              customMarker.showInfoWindow();
              map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                  public boolean onMarkerClick(Marker marker) {
                      // Check if there is an open info window
                      if (lastOpenned != null) {
                          // Close the info window
                          lastOpenned.hideInfoWindow();

                          // Is the marker the same marker that was already open
                          if (lastOpenned.equals(marker)) {
                              // Nullify the lastOpenned object
                              lastOpenned = null;
                              // Return so that the info window isn't openned again
                              return true;
                          }
                      }

                      // Open the info window for the marker
                      customMarker.showInfoWindow();
                      // Re-assign the last openned such that we can close it later
                      lastOpenned = customMarker;

                      // Event was handled by our code do not launch default behaviour.
                      return true;
                  }
              });
              map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                      new LatLng(location1.getLatitude(), location1.getLongitude()), 15));
          }

        else
        {
            Toast.makeText(getApplicationContext(), "Location is not available now.Please wait for some time.", Toast.LENGTH_LONG).show();
        }
        // googleMap.animateCamera(CameraUpdateFactory.zoomTo(6));
        //googleMap.addMarker(new MarkerOptions().position(latLng)
        //      .icon(BitmapDescriptorFactory
        //            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        //  .draggable(true));;
        //   findDirections(ll.getLatitude(), ll.getLongitude(), latitude, longitude, GMapV2Direction.MODE_DRIVING);
        // locationTv.setText("Latitude:" + latitude + ", Longitude:" + longitude);
    }
    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

  /*  @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }*/


    /*    public String getMobileNumber(){
            TelephonyManager telephonyManager =(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
            String strMobileNumber = telephonyManager.getSimSerialNumber();
            Toast.makeText(getApplicationContext(), strMobileNumber, Toast.LENGTH_LONG).show();
            return strMobileNumber;

        }
*/
    public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints) {
        PolylineOptions rectLine = new PolylineOptions().width(2).color(Color.RED);

        for (int i = 0; i < directionPoints.size(); i++) {
            rectLine.add(directionPoints.get(i));
        }
        if (newPolyline != null) {
            newPolyline.remove();
        }
        newPolyline = map.addPolyline(rectLine);

        latlngBounds = createLatLngBoundsObject(l, dest);
        //  googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        // googleMap.animateCamera(CameraUpdateFactory.zoomTo(6));

    }

    private LatLngBounds createLatLngBoundsObject(LatLng firstLocation, LatLng secondLocation) {
        if (firstLocation != null && secondLocation != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(firstLocation).include(secondLocation);

            return builder.build();
        }
        return null;
    }



    String[] Timed(String start, String end) {
        // ArrayList<String> resultList = null;
        String[] arr = new String[2];
        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE1);
            sb.append("?origin=" + URLEncoder.encode(start, "utf8"));
            //sb.append("?origin="+start);
            sb.append("&destination=" + URLEncoder.encode(end, "utf8"));

            // sb.append("&destination="+end);
            sb.append("&sensor=false");

            URL url = new URL(sb.toString());

            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            // return p;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            //  return p;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {

            // Create a JSON object hierarchy from the results
            final JSONObject json = new JSONObject(jsonResults.toString());
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);

            JSONArray newTempARr = routes.getJSONArray("legs");
            JSONObject newDisTimeOb = newTempARr.getJSONObject(0);

            JSONObject distOb = newDisTimeOb.getJSONObject("distance");
            JSONObject timeOb = newDisTimeOb.getJSONObject("duration");

            //p=distOb.getString("text").toString()+timeOb.getString("text").toString();
            arr[0]=distOb.getString("text").toString();
            arr[1]=timeOb.getString("text").toString();
            Log.i("Diatance :", distOb.getString("text"));
            Log.i("Time :", timeOb.getString("text"));
            // return resultList;
            // Toast.makeText(getApplicationContext(), p, Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //   Toast.makeText(getApplicationContext(),p, Toast.LENGTH_LONG).show();
        return arr;

    }
    public String getUsername() {
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
            // TODO: Check possibleEmail against an email regex or treat
            // account.name as an email address only for certain account.type values.
            possibleEmails.add(account.name);
        }

        if (!possibleEmails.isEmpty() && possibleEmails.get(0) != null) {
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");

            if (parts.length > 1)
                return parts[0];
        }
        return null;
    }
    public void onBackPressed() {

        // finish();
        moveTaskToBack(true);
    }
    public  boolean hasActiveInternetConnection() {
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error checking internet connection", e);
            }
        } else {
            Log.d(LOG_TAG, "No network available!");
        }
        return false;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected())

            return true;
        else
            return false;

    }
    class GetLoc extends AsyncTask<String, String, String> {

        boolean failure = false;
        String gender;
        int mflag, eflag;
        int success;
        int bg;
        String c = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = (CircleProgressBar) findViewById(R.id.pBar);
            progressBar.setColorSchemeResources(android.R.color.holo_blue_light);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag

            String REGISTER_URL = "http://191.239.57.54:8080/Blynk/getShareLocation?imeiNo="+imei;

            GetJsonObject json=new GetJsonObject();
            String response =json.getWebServceObj(REGISTER_URL);
            Log.d("Shareee",response);
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if(jsonResponse.getString("status").equals("success")){
                    c="success";
                    JSONArray jsonDistArr = jsonResponse.getJSONArray("userDetailList");
                    //districIdArr = new int[jsonDistArr.length()];
                    //dname = new String[jsonDistArr.length()];
                    for(int i=0;i<jsonDistArr.length();i++){
                        JSONObject jsonDisObj = jsonDistArr.getJSONObject(i);
                       // String areaList = jsonDisObj.getJSONArray("areaList").toString();
                        clat = jsonDisObj.getString("currentLocationLat");
                        clong=jsonDisObj.getString("currentLocationLong");
                        userName=jsonDisObj.getString("userName");

      Log.d("Shared loc data",clat+""+clong+" "+userName);
                    }
                    SharedPreferences sharedPreferences1 =getSharedPreferences(STORAGE_LOC, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString("clat",clat);
                    editor.putString("clong",clong);
                    editor.putString("userName",userName);
                    editor.apply();
                    editor.commit();
                    //  dname = list.toArray(new String[list.size()]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),clat+clong+userName,Toast.LENGTH_LONG).show();

         //
        }
    }
}
