package com.example.admin.blynked;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ScaleXSpan;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;





public class Share_main1 extends ActionBarActivity implements NumberPicker.OnValueChangeListener, OnMapReadyCallback {
    private static int MIN_TIME_BW_UPDATES = 5000; // 5 sec
    private static int MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;

    private static final String DATE_FORMAT = "MMM-dd-yyyy, HH:mm";
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String lp1;
    String[] name_list;
    int[] img_list;
    CustomAdapter cAdapter;
    ListView lv;
    String hrr,min,lim;
    String rcp,selmsg1;
    String crnt;
    int fs;
    String phoneNo1;
    private Marker customMarker;
    String destination;
    Location locationn;
    String name,imagePreferance;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    private int mTitle = R.string.app_name;
    String lp;
    String imagepath;
    int timers;
String phoneNo,sub,str,message;
    public static final String STORAGE_NAME1 = "MySharedPreferencess1";
    Integer[] img_id = {
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_request_menu_drawer,


            R.mipmap.icn_favourite_menu_drawer,
            R.mipmap.icn_calendar_menu_drawer,
            R.mipmap.icn_history_menu_drawer,
            R.mipmap.icn_settings_menu_drawer,



    };
    CountDownTimer resultTimer;
    GoogleMap map;
    Marker lastOpenned = null;
    String add;
    String t="time";
    String[] k = new String[2];
    String state, address, city, country, knownName;
    String state1, address1, city1, country1, knownName1;
    Geocoder geocoder;
    List<Address> addresses;
    List<Address> addresses1;
    Boolean isGPSEnabled;
    private int width, height;
    private Polyline newPolyline;
    LatLng dest, l;
    TextView t11;
   long timeee;

    String allcc,desti;

    String bestProvider;
    ImageView img_main;
    private LatLngBounds latlngBounds;
    Location location,ll;
    private static final String LOG_TAG = "ExampleApp";
    private static final String PLACES_API_BASE1 = "http://maps.googleapis.com/maps/api/directions/json";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String INITIATE_URL="http://191.239.57.54:8080/Blynk/InitiateTracker";
    private static final String DATAPOST_URL="http://191.239.57.54:8080/Blynk/TrackUser";
    private static final String OUT_JSON = "/json";
    TextView t1,t2;
    String uid;
    String p;
    int flag;
    SupportMapFragment supportMapFragment;
    // int d;
    public static final String STORAGE_IMAGE = "Image";
    public static final String STORAGE1 = "SenddataPreferences";

    TelephonyManager mngr;
    Spinner spinner1,spinner2;
    String seltime,selmsg;
    private static final int CONTAINER = R.id.fragment_container;
    Button share,fav;Toolbar tool;
    String clat,clong;
    int hid;
    String id;
    long remainingTime;
    LocationManager locationManager;
    JSONParser jsonParser = new JSONParser();
    private static final String API_KEY = "AIzaSyB9vcuLI-1dSeJ68Z9xvdm2j4TsLTQw7_A";
    SqliteHelper1 sqliteHelper1;
ProgressBar progressBar1;
    CircleProgressBar progressBar;
    TextView ddd,du,msgg,dut;
    RelativeLayout i2,i21,i22,i23,i24;
    private Button upButton,set;
    private Button downButton;
    private TextView editText;
    private int uprange = 4;
    private int downrange = 0;
    private int values = 0;
    String duration,sapps;
    TextView r,s;
    ProgressDialog pDialog;
    String Msg,seltime1,selint;
    int d;
    SqliteHelper sqliteHelper;
    long time1;
    int data,c;
    Globals g;
    int check;
    int progressValue = 0;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //show error dialog if GoolglePlayServices not available

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        statusCheck();

        setContentView(R.layout.activity_share_main1);
        //   statusCheck();
        //   statusCheck();
        g = (Globals) getApplication();
        data = g.getData();
        c = g.getc();
        check=g.getcheck();


        supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        //  new Wait().execute();
        supportMapFragment.getMapAsync(this);
        sqliteHelper1 = new SqliteHelper1(this);

        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uid = mngr.getDeviceId();
        tool = (Toolbar) findViewById(R.id.tool_bar);
        // ii0 = (RelativeLayout) findViewById(R.id.source);
        i2 = (RelativeLayout) findViewById(R.id.dest);
        i21 = (RelativeLayout) findViewById(R.id.rcp);
        i22 = (RelativeLayout) findViewById(R.id.dur);
        i23 = (RelativeLayout) findViewById(R.id.msg);
        i24=(RelativeLayout)findViewById(R.id.msgt);

        ddd = (TextView) findViewById(R.id.d);
        r = (TextView) findViewById(R.id.r);
        s = (TextView) findViewById(R.id.s);
        du = (TextView) findViewById(R.id.du);
        dut = (TextView) findViewById(R.id.dut);
        fav = (Button) findViewById(R.id.fav);
        msgg = (TextView) findViewById(R.id.msgg);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (tool != null) {
            setSupportActionBar(tool);
            tool.setNavigationIcon(R.mipmap.menu_converted);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mDrawerTitle = getTitle();
            name_list = getResources().getStringArray(R.array.item_name);
            //img_list=getResources().getIntArray(R.array.img_id);
            lv = (ListView) findViewById(R.id.nav_list);

            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);

            countrylist = new ArrayList<DataModel>();
            for (int i = 0; i < name_list.length; i++) {

                DataModel country = new DataModel(name_list[i], img_id[i]);
                countrylist.add(country);
            }

            cAdapter = new CustomAdapter(getApplicationContext(), countrylist);

            lv.setAdapter(cAdapter);
            // statusCheck();

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

                        SharedPreferences.Editor editor = shared1.edit();

                        editor.clear();
                        editor.commit();
                        progressBar1 = (ProgressBar)findViewById(R.id.pBar1);
                        progressBar1.setProgress(0);
                        progressBar1.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                            @Override
                            public void run() {
                                Intent i1 = new Intent(Share_main1.this, Share_main.class);
                                // i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);



                      //  new Ss().execute();

                        // drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:


                        progressBar1 = (ProgressBar)findViewById(R.id.pBar1);
                        progressBar1.setProgress(0);
                        progressBar1.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                            @Override
                            public void run() {
                                Intent i1 = new Intent(Share_main1.this, Share_main1.class);
                                // i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(i1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);



                        //  new Ss().execute();

                        // drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        Intent i2 = new Intent(Share_main1.this, Request_main.class);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:


                        Intent i22 = new Intent(Share_main1.this, Favourite_main.class);
                        startActivity(i22);
                        finish();
                        break;


                    case 4:
                        Intent ihe = new Intent(Share_main1.this, Calendar_main.class);
                        startActivity(ihe);
                        finish();
                        break;
                    case 5:
                        Intent ih = new Intent(Share_main1.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(Share_main1.this, Setting_main.class);
                        startActivity(ii);
                        finish();
                        break;
                }


            }
        });

        sqliteHelper = new SqliteHelper(this);
        // spinner1 = (Spinner) findViewById(R.id.spinner1);
        //  spinner2 = (Spinner) findViewById(R.id.spinner2);
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);

        name = sharedPreferences.getString("name", "");
        imagePreferance = sharedPreferences.getString("imagePreferance", "");
        imagepath=sharedPreferences.getString("imagepath","");
        // Toast.makeText(Share_main.this,imagepath,Toast.LENGTH_LONG).show();
        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {
        crnt = shared1.getString("Current", "");
        destination = shared1.getString("destination", "");
        rcp = shared1.getString("rcp", "");
        duration = shared1.getString("duration", "");
        hrr = shared1.getString("hrr", "");
        min = shared1.getString("min", "");
lim=shared1.getString("lim","");
        Msg = shared1.getString("Msg", "");
        locationn = new Location("");

       /* if (!crnt.equals("")) {
            s.setText(crnt);
            l = getLocationFromAddress(crnt);

            locationn.setLatitude(l.latitude);
            locationn.setLongitude(l.longitude);
            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);


            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);

            onLocationChanged(locationn);

        } else {
            s.setText("Define your source");
        }*/
        //  if( imagePreferance!=null ) {
        if (!destination.equals("")) {
            //  t11.setText(name);
            //     me.setText("Me "+ "("+name+")");
            //img_main.setImageBitmap(decodeBase64(imagePreferance));

            //  dp.setImageBitmap(decodeBase64(imagePreferance));
            ddd.setText(destination);


        }

        //  }
        else {
            // Toast.makeText(getApplicationContext(),"destin", Toast.LENGTH_LONG).show();
            // t11.setText("Please set your profile");
            //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            //    img_main.setImageBitmap(icon1);
            ddd.setText("Define your destination");

        }
        if (!rcp.equals("")) {
            r.setText(rcp);
        }


        if (!duration.equals("")) {
            du.setText(duration);
        } else {
            du.setText("Share for 15 minutes");
        }

        if (!lim.equals("")) {
            dut.setText(lim);
        } else {
            dut.setText("Set limit to send messages");
        }
        if (!Msg.equals("")) {
            msgg.setText(Msg);
        } else {
            msgg.setText("I am sharing my location");
        }


        // t1 = (TextView) findViewById(R.id.t11);
        //   t1.setText(s);
        // t2 = (TextView) findViewById(R.id.t2);
        share = (Button) findViewById(R.id.share);
        // fav = (Button) findViewById(R.id.fav);
        // fav.setVisibility(View.INVISIBLE);
        //   supportMapFragment =
        //  (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        // supportMapFragment.getMapAsync(this);
      /*  ii0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Share_main.this, Selects.class);
                startActivity(i);

            }
        });*/

        i2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
                        Intent i = new Intent(Share_main1.this, Selectd1.class);
                        startActivity(i);

                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);



            }
        });

        i21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Share_main1.this, Selectallauto.class);
                startActivity(i);

            }
        });
        i22.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                show();
              /*  Intent i=new Intent(Share_main.this,Selectdur.class);
                startActivity(i);*/

            }
        });
        i23.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Share_main1.this, Selectmsg1.class);
                startActivity(i);

            }
        });
        i24.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                show1();

            }
        });
        if (g.getfavflag() == 1) {
            share.setVisibility(View.INVISIBLE);
            fav.setVisibility(View.VISIBLE);
        } else {
            share.setVisibility(View.VISIBLE);
            fav.setVisibility(View.INVISIBLE);

        }

        fav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = shared1.edit();

                editor.clear();
                editor.commit();

                //Toast.makeText(getApplicationContext(),"PLease wait", Toast.LENGTH_LONG).show();
                d = c + 1;
                g.setc(d);
                //   desti = autoCompView.getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(Share_main1.this);

                alert.setTitle("Set Your favourite Name");
                //  alert.setMessage("Message");

// Set an EditText view to get user input
                final EditText input = new EditText(Share_main1.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        g.setfavflag(2);
                        selmsg1 = msgg.getText().toString();
                        seltime1 = du.getText().toString();
                        selint=dut.getText().toString();
                        desti = ddd.getText().toString();
                        rcp = r.getText().toString();
                        crnt = s.getText().toString();
                        int id1 = g.getc();
                        String msg = selmsg1;
                        String time = seltime1;
                        String name = input.getText().toString();
                        String id = Integer.toString(id1);
                        flag = 1;
                        boolean result = sqliteHelper.saveUser(id, desti, msg, time, flag, name, rcp, crnt);
                        int p1 = sqliteHelper.dbSyncCount();
                        //Toast.makeText(getBaseContext(),
                        //      ""+p1,
                        //    Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Share_main1.this, Favourite_main.class);
                        startActivity(intent);
                        finish();

                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    if (check == 1) {
                        String text;

                        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                        Date date1 = new Date(System.currentTimeMillis());
                        final String d1 = sdf.format(date1);
                        crnt = s.getText().toString();
                        selmsg1 = msgg.getText().toString();
                        seltime1 = du.getText().toString();
                        selint = dut.getText().toString();
                        if (seltime1.equals("Share for 15 minutes")) {
                            //timers=15;

                            timeee = 15 * 60 * 1000;

                        } else {
                            int hour = 0;
                            int minute = 0;
                            SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                            hrr = shared1.getString("hrr", "");
                            min = shared1.getString("min", "");

                            if (!hrr.equals("")) {
                                hour = Integer.parseInt(hrr);
                                //timeee=hrr*60*60*1000+

                            }
                            if (!min.equals("")) {
                                minute = Integer.parseInt(min);
                            }

                            timeee = hour * 60 * 60 * 1000 + minute * 60 * 1000;

                        }
                        desti = ddd.getText().toString();
                        rcp = r.getText().toString();

                        if (!rcp.equals("Select Contact")) {
                            if (rcp.equals("Facebook")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }
                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.facebook.katana");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("WhatsApp")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }
                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.whatsapp");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("Hangouts")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }

                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.google.android.talk");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("Messaging") || (rcp.equals("Messages"))) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }

                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.android.mms");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("Skype")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }

                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.skype.raider");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("Yahoo Mail")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }

                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.yahoo.mobile.client.android.mail");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("Twitter")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }
                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.twitter.android");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else if (rcp.equals("Messenger")) {
                                Intent waIntent = new Intent(Intent.ACTION_SEND);
                                waIntent.setType("text/plain");
                                if (selmsg1.equals("Send a message")) {
                                    text = "I am here: " + "http://191.239.57.54:8080/Blynk/locate/" + uid;
                                } else {
                                    text = "I am here: " + selmsg1 + " http://191.239.57.54:8080/Blynk/locate/" + uid;
                                }
                                // PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                //Check if package exists or not. If not then code
                                //in catch block will be called
                                waIntent.setPackage("com.facebook.orca");

                                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(waIntent, "Share with"));
                            } else {
                                phoneNo = r.getText().toString();
                                if(!selint.equals("Set limit to send messages")) {
                                    if (phoneNo.contains("[")) {
                                        sub = phoneNo.substring(1, phoneNo.length() - 1);
                                        str = sub.replaceAll("[^+,0-9]+", " ");

                                        int foo = Integer.parseInt(selint);
                                        long tt = timeee / (foo * 1000);
                                        String myString = Long.toString(tt);
                                        new MySmsTask().execute(str, selint, myString);
                                    } else {

                                        int foo = Integer.parseInt(selint);
                                        long tt = timeee / (foo * 1000);
                                        String myString = Long.toString(tt);
                                        new MySmsTask1().execute(phoneNo, selint, myString);
                                    }
                                }
                                else {
                                Toast.makeText(getApplicationContext(),"Plese select limit for sending message", Toast.LENGTH_LONG).show();
                                }
                            }
                          //  LatLng dest = getLocationFromAddress(desti);
                            hid = g.gethid();
                            id = Integer.toString(hid);


                            //  boolean result = sqliteHelper1.saveUser(id,time1);
                            hid = Integer.parseInt(id);
                            hid = hid + 1;
                            g.sethid(hid);

                            //  boolean result = sqliteHelper3.saveUser(id,timeee);
                       /* if(!crnt.equals("Not able to find current location")) {
                            desti=ddd.getText().toString();
                            // Toast.makeText(getApplicationContext(), desti, Toast.LENGTH_LONG).show();
                            if (!desti.equals("Define your destination")) {


                                new Initiate().execute();

                                //  Toast.makeText(getApplicationContext(), ""+d, Toast.LENGTH_LONG).show();
                                // new Initiate().execute();
                                //  Toast.makeText(getApplicationContext(), "Service calling ", Toast.LENGTH_LONG).show();
                            } else {

                                new Initiate1().execute();
                                //Toast.makeText(getApplicationContext(), "Service calling ", Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Current not available", Toast.LENGTH_SHORT).show();
                        }*/
                            CountDownTimer countDownTimer = new CountDownTimer(timeee, 1000) {

                                @Override
                                public void onTick(long millisUntilFinished) {

                                    g.setData(1000);
                          /*  if(isNetworkAvailable()) {
                                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                                Criteria criteria = new Criteria();
                                criteria.setAccuracy(Criteria.POWER_LOW);
                                criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
                                criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
                                criteria.setBearingAccuracy(Criteria.ACCURACY_LOW);
                                criteria.setSpeedAccuracy(Criteria.ACCURACY_MEDIUM);
                                bestProvider = locationManager.getBestProvider(criteria, true);
                                location = locationManager.getLastKnownLocation(bestProvider);
                                ll = location;
                                // if (location != null) {
                                //     onLocationChanged(location);
                                //   }
                                //   else {
                                Boolean isNetworkEnabled = locationManager
                                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                                Boolean isGPSEnabled = locationManager
                                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                                if (isNetworkEnabled) {

                                    locationManager.requestLocationUpdates(
                                            LocationManager.NETWORK_PROVIDER,
                                            MIN_TIME_BW_UPDATES,
                                            MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);
                                    location = locationManager
                                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                    onLocationChanged(location);
                                } else if (isGPSEnabled) {
                                    if (location == null) {
                                        locationManager.requestLocationUpdates(
                                                LocationManager.GPS_PROVIDER,
                                                MIN_TIME_BW_UPDATES,
                                                MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);
                                        location = locationManager
                                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                                        onLocationChanged(location);

                                    }
                                }

                               // Toast.makeText(getApplicationContext(), crnt, Toast.LENGTH_LONG).show();


                            }
                            else
                            {
                                locationManager.removeUpdates(Share_main.this);
                             //   Toast.makeText(getApplicationContext(), "You are not connected to internet right now.Services will be called after internet connectivity", Toast.LENGTH_SHORT).show();
                            }*/
                                    //txt.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                                    System.out.println(hms);
                                    g.setsstatus(1);
                                    // txt.setText(hms);
                                    time1 = millisUntilFinished;
                                    // time1=4;
                                    flag = 0;
                                    fs = 0;


                                    boolean result = sqliteHelper1.saveUser(id, time1, flag, d1, rcp, desti, selmsg1, seltime1, crnt, fs);

                                    //}
                                }

                                @Override
                                public void onFinish() {
                                    SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                                    SharedPreferences.Editor editor = shared1.edit();

                                    editor.clear();
                                    editor.commit();
                                    fs = 1;
                                    time1 = 0L;
                                    g.setData(300);
                                    g.setsstatus(0);
                                    //locationManager.removeUpdates(Share_main.this);
                                    //txt.setText("done");
                                    flag = 0;

                                    //    Toast.makeText(getApplicationContext(),""+id+"   "+time1+" "+flag, Toast.LENGTH_LONG).show();
                                    boolean result = sqliteHelper1.saveUser(id, time1, flag, d1, rcp, desti, selmsg1, seltime1, crnt, fs);
                                }
                            }.start();

//                if (phoneNo.length()>0 && message.length()>0)
//                    sendSMS(phoneNo, message);
//                else
//                    Toast.makeText(getBaseContext(),
//                        "Please enter both phone number and message.",
//                        Toast.LENGTH_SHORT).show();


                            // } catch (PackageManager.NameNotFoundException e) {
                            //   Toast.makeText(Share_main.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            //   .show();
                            //   }

                        } else {
                            Toast.makeText(getApplicationContext(), "Please select contact", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enable share functionality from Setting option", Toast.LENGTH_LONG).show();
                    }


            }
        });
     /*   share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //publishImage1();
                // Toast.makeText(getApplicationContext(),lp, Toast.LENGTH_LONG).show();

                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                Date date1=new Date(System.currentTimeMillis());
                final String d1=sdf.format(date1);
               // desti = autoCompView.getText().toString();
                String time = t2.getText().toString();
                String distance = t1.getText().toString();
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        "Sharing my location with you");
                String shareMessage = selmsg1;
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        "For" + seltime1 + selmsg1 + "http://191.239.57.54:8080/Blynk/locate/" + uid);
                // " Now I am here--"+lp+"  I am going---"+ shareMessage+" will cover "+ distance +" in " + time);
                startActivity(Intent.createChooser(shareIntent,
                        "Please select"));
                //if(!name.equals(""))
                //  t11.setText(name);
                SharedPreferences sharedPreferences1 = getSharedPreferences(STORAGE_NAME1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("seltime", seltime1);
                editor.apply();
                LatLng dest = getLocationFromAddress(desti);
                hid = g.gethid();
                id=Integer.toString(hid);


                //  boolean result = sqliteHelper1.saveUser(id,time1);
                hid=Integer.parseInt(id);
                hid=hid+1;
                g.sethid(hid);
                // String stime=seltime1.replaceAll("[^0-9]","");
                //Toast.makeText(getApplicationContext(),stime, Toast.LENGTH_LONG).show();

                CountDownTimer countDownTimer = new CountDownTimer(5*60 * 1000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        //txt.setText("seconds remaining: " + millisUntilFinished / 1000);
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                        System.out.println(hms);
                        // txt.setText(hms);
                        time1 = millisUntilFinished;
                        // time1=4;
                        flag=0;
                        // Toast.makeText(getApplicationContext(),""+id+"   "+time1+" "+flag, Toast.LENGTH_LONG).show();
                        // if(time1!=0L) {
                        boolean result = sqliteHelper1.saveUser(id,time1,flag,d1);
                        //}
                    }

                    @Override
                    public void onFinish() {
                        time1=0L;
                        g.setData(300);
                        //txt.setText("done");
                        flag=0 ;
                        //    Toast.makeText(getApplicationContext(),""+id+"   "+time1+" "+flag, Toast.LENGTH_LONG).show();
                        boolean result = sqliteHelper1.saveUser(id,time1,flag,d1);
                    }
                }.start();


                // Toast.makeText(getApplicationContext(),uid  + name, Toast.LENGTH_LONG).show();
            *//*    new Initiate().execute();
                g.setData(100);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 1 second
                                //locationManager.removeU
                                // pdates(locationListener);
                                g.setData(300);
                            }
                        }, 10*60*1000);
                    }
                });*//*
                //   lm.removeUpdates(locationListenerNetwork);
              *//*  HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(INITIATE_URL);

                try {
                    // Add your data
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("imeiNo", uid));
                    params.add(new BasicNameValuePair("startLoclat",Double.toString (location.getLatitude())));
                    params.add(new BasicNameValuePair("startLoclong",Double.toString (location.getLongitude())));
                    params.add(new BasicNameValuePair("endLoclat",Double.toString (dest.latitude)));
                    params.add(new BasicNameValuePair("endLoclong",Double.toString (dest.longitude)));
                    params.add(new BasicNameValuePair("userName",name));
                    params.add(new BasicNameValuePair("currentLocLat",Double.toString (location.getLatitude())));
                    params.add(new BasicNameValuePair("currentLocLong",Double.toString (location.getLatitude())));

                    httppost.setEntity(new UrlEncodedFormEntity(params));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);

                } catch (ClientProtocolException e) {
              e.printStackTrace();
                } catch (IOException e) {
                   e.printStackTrace();
                }*//*
            }
        });*/

        //
        int callingActivity = getIntent().getIntExtra("cactivity", 0);
        if (callingActivity == 1) {
            share.setVisibility(View.INVISIBLE);
            fav.setVisibility(View.VISIBLE);


        }
        int callingActivity1 = getIntent().getIntExtra("cactivity1", 0);
        if (callingActivity1 == 2) {
            share.setVisibility(View.VISIBLE);
            fav.setVisibility(View.INVISIBLE);
            SharedPreferences shared11 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
            //   boolean hasDrawable = (img_main.getDrawable() != null);
            //  if(hasDrawable) {
            crnt = shared11.getString("Current", "");
            destination = shared11.getString("destination", "");
            rcp = shared11.getString("rcp", "");
            duration = shared11.getString("duration", "");


            Msg = shared11.getString("Msg", "");


            locationn = new Location("");
           /* if (!crnt.equals("")) {
                s.setText(crnt);
                l = getLocationFromAddress(crnt);

                locationn.setLatitude(l.latitude);
                locationn.setLongitude(l.longitude);
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);


                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);

                onLocationChanged(locationn);

            } else {
                s.setText("Define your source");
            }*/
            //  if( imagePreferance!=null ) {
            if (!destination.equals("")) {
                //  t11.setText(name);
                //     me.setText("Me "+ "("+name+")");
                //img_main.setImageBitmap(decodeBase64(imagePreferance));

                //  dp.setImageBitmap(decodeBase64(imagePreferance));
                ddd.setText(destination);


            }

            //  }
            else {
                // Toast.makeText(getApplicationContext(),"destin", Toast.LENGTH_LONG).show();
                // t11.setText("Please set your profile");
                //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
                //    img_main.setImageBitmap(icon1);
                ddd.setText("Define your destination");

            }
            if (!rcp.equals("")) {
                r.setText(rcp);
            } else {
                r.setText("Select Contact");
            }


            if (!duration.equals("")) {
                du.setText(duration);
            } else {
                du.setText("Share for 15 minutes");
            }
            if (!Msg.equals("")) {
                msgg.setText(Msg);
            } else {
                msgg.setText("I am sharing my location");
            }


            //   spinner2.setSelection(adapter2.getPosition(dd));


        } else if (callingActivity1 == 33) {
            share.setVisibility(View.VISIBLE);
            fav.setVisibility(View.INVISIBLE);
            SharedPreferences shared11 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
            //   boolean hasDrawable = (img_main.getDrawable() != null);
            //  if(hasDrawable) {
            crnt = shared11.getString("Current", "");
            destination = shared11.getString("destination", "");
            rcp = shared11.getString("rcp", "");
            duration = shared11.getString("duration", "");


            Msg = shared11.getString("Msg", "");


            locationn = new Location("");
           /* if (!crnt.equals("")) {
                s.setText(crnt);
                l = getLocationFromAddress(crnt);

                locationn.setLatitude(l.latitude);
                locationn.setLongitude(l.longitude);
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,

                        MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);


                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);

                onLocationChanged(locationn);

            } else {
                s.setText("Define your source");
            }*/
            //  if( imagePreferance!=null ) {
            if (!destination.equals("")) {
                //  t11.setText(name);
                //     me.setText("Me "+ "("+name+")");
                //img_main.setImageBitmap(decodeBase64(imagePreferance));

                //  dp.setImageBitmap(decodeBase64(imagePreferance));
                ddd.setText(destination);


            }

            //  }
            else {
                // Toast.makeText(getApplicationContext(),"destin", Toast.LENGTH_LONG).show();
                // t11.setText("Please set your profile");
                //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
                //    img_main.setImageBitmap(icon1);
                ddd.setText("Define your destination");

            }
            if (!rcp.equals("")) {
                r.setText(rcp);
            } else {
                r.setText("Select Contact");
            }


            if (!duration.equals("")) {
                du.setText(duration);
            } else {
                du.setText("Share for 15 minutes");
            }
            if (!Msg.equals("")) {
                msgg.setText(Msg);
            } else {
                msgg.setText("I am sharing my location");
            }


            // spinner2.setSelection(adapter2.getPosition(dd));


        } else if (callingActivity1 == 3) {
            share.setVisibility(View.VISIBLE);
            fav.setVisibility(View.INVISIBLE);
            SharedPreferences shared11 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
            //   boolean hasDrawable = (img_main.getDrawable() != null);
            //  if(hasDrawable) {
            crnt = shared11.getString("Current", "");
            destination = shared11.getString("destination", "");
            rcp = shared11.getString("rcp", "");
            duration = shared11.getString("duration", "");


            Msg = shared11.getString("Msg", "");


            locationn = new Location("");
         /*   if (!crnt.equals("")) {
                s.setText(crnt);
                l = getLocationFromAddress(crnt);

                locationn.setLatitude(l.latitude);
                locationn.setLongitude(l.longitude);
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);


                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, Share_main.this);

                onLocationChanged(locationn);

            } else {
                s.setText("Define your source");
            }*/

            //  if( imagePreferance!=null ) {
            if (!destination.equals("")) {
                //  t11.setText(name);
                //     me.setText("Me "+ "("+name+")");
                //img_main.setImageBitmap(decodeBase64(imagePreferance));

                //  dp.setImageBitmap(decodeBase64(imagePreferance));
                ddd.setText(destination);


            }

            //  }
            else {
                // Toast.makeText(getApplicationContext(),"destin", Toast.LENGTH_LONG).show();
                // t11.setText("Please set your profile");
                //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
                //    img_main.setImageBitmap(icon1);
                ddd.setText("Define your destination");

            }
            if (!rcp.equals("")) {
                r.setText(rcp);
            } else {
                r.setText("Select Contact");
            }


            if (!duration.equals("")) {
                du.setText(duration);
            } else {
                du.setText("Share for 15 minutes");
            }
            if (!Msg.equals("")) {
                msgg.setText(Msg);
            } else {
                msgg.setText("I am sharing my location");
            }

        }
    }
    private class MySmsTask1 extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... messageData) {
            int sentCount = 0;
            int size = 1;
            int delay = Integer.parseInt(messageData[2]);
            int msgCount = Integer.parseInt(messageData[1])+1;



            for (sentCount = 0; sentCount < msgCount; sentCount++) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                geocoder = new Geocoder(Share_main1.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    state = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();

                } catch (Exception e) {

                }
                if(!address.equals("null")&& !city.equals("null")&& !country.equals("null")) {
                    message = "I am here-" + "Lat-" + location.getLongitude() + "Long-" + location.getLatitude() + " " + address + "," + city + "," + country;
                }
                else
                {
                    message = "I am here-" + "Lat-" + location.getLongitude() + "Long-" + location.getLatitude() + " Not able to find location without Internet";
                }
                for (int i = 0; i < size; i++) {
                    sendSMS(messageData[0], message);
                    try {
                        Thread.sleep(delay * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return sentCount;
        }

        @Override
        protected void onPostExecute(Integer result) {
            Toast.makeText(getBaseContext(), "Sent " + result + " messages", Toast.LENGTH_SHORT).show();
        }
    }

    public class MySmsTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... messageData) {
            int sentCount = 0;
            // int size = 1;

            int delay = Integer.parseInt(messageData[2]);
            int msgCount = Integer.parseInt(messageData[1])+1;
            for (sentCount = 0; sentCount < msgCount; sentCount++) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
   if(isNetworkAvailable()) {
    geocoder = new Geocoder(Share_main1.this, Locale.getDefault());
    try {
        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        city = addresses.get(0).getLocality();
        state = addresses.get(0).getAdminArea();
        country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        knownName = addresses.get(0).getFeatureName();
    } catch (Exception e) {
    }

   // if (!address.equals("null") && !city.equals("null") && !country.equals("null")) {
        message = "I am here-" + "Lat-" + location.getLongitude() + "Long-" + location.getLatitude() + " " + address + "," + city + "," + country;
  //  }
}
               else
                {
                    message = "I am here-" + "Lat-" + location.getLongitude() + "Long-" + location.getLatitude() + " [Not able to find location without Internet]";
                }
         // message=""+location.getLatitude()+ location.getLongitude();
              //  Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
                StringTokenizer st = new StringTokenizer(str, ",");
                while (st.hasMoreElements()) {
                    String tempMobileNumber = (String) st.nextElement();
                    tempMobileNumber = tempMobileNumber.substring(tempMobileNumber.length() - 10);
                    if (tempMobileNumber.length() > 0 && message.trim().length() > 0) {


                        sendSMS(tempMobileNumber, message);
                    }
                }
                try {
                    Thread.sleep(delay * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            }
            return sentCount;
        }

        @Override
        protected void onPostExecute(Integer result) {
            Toast.makeText(getBaseContext(), "Sent " + result + " messages", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListenerGps);
        locationManager.removeUpdates(locationListenerNetwork);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    private void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        },new IntentFilter(SENT));

        //---when the SMS has been delivered---
       /* registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));
*/
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);

    }



    public void show()
    {
        SpannableStringBuilder ssBuilser = new SpannableStringBuilder("Set Broadcast duration");
        StyleSpan span = new StyleSpan(Typeface.BOLD);
        ScaleXSpan span1 = new ScaleXSpan(1);
        //  ssBuilser.setSpan(span, 0, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //  ssBuilser.setSpan(span1, 0, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        final Dialog d = new Dialog(Share_main1.this);
        d.setTitle(ssBuilser);

        d.setContentView(R.layout.dialog);
        upButton = (Button)d. findViewById(R.id.upButton);
        // set = (Button) findViewById(R.id.set);
        downButton = (Button) d.findViewById(R.id.downButton);
        editText = (TextView) d.findViewById(R.id.numberEditText);
        editText.setText("0");
        Button b1 = (Button) d.findViewById(R.id.button1);
        // Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(59); // max value 100
        np.setMinValue(0);
        // min value 0

        np.setValue(5);
        np.setFocusable(true);
        np.setWrapSelectorWheel(false);
        np.setFocusableInTouchMode(true);
        np.setWrapSelectorWheel(false);
        setDividerColor(np, Color.TRANSPARENT);
        Boolean p=setNumberPickerTextColor(np,Color.parseColor("#ffff852b"));
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String rr=editText.getText().toString();
                String c=rr + "Hr  : " + String.valueOf(np.getValue()) + "Minutes";
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();
                editor.putString("hrr",rr);
                editor.putString("min",String.valueOf(np.getValue()));
                editor.putString("duration",c);
                editor.apply();
                du.setText(c); //set the value to textview

                d.dismiss();
            }
        });
      /*  b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });*/
        upButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // downButton.setBackgroundResource(R.drawable.timepicker_down_normal);
                //   upButton.setBackgroundResource(R.drawable.timepicker_up_pressed);
                if (values >= downrange && values <= uprange)
                    values++;
                if (values > uprange)
                    values = downrange;
                editText.setText("" + values);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //     downButton .setBackgroundResource(R.drawable.timepicker_down_pressed);
                //    upButton.setBackgroundResource(R.drawable.timepicker_up_normal);
                if (values >= downrange && values <= uprange)
                    values--;

                if (values < downrange)
                    values = uprange;

                editText.setText(values + "");
            }
        });
        d.show();


    }
    public void show1()
    {
        SpannableStringBuilder ssBuilser = new SpannableStringBuilder("Set limit to send message");
        StyleSpan span = new StyleSpan(Typeface.BOLD);
        ScaleXSpan span1 = new ScaleXSpan(1);
        //  ssBuilser.setSpan(span, 0, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        //  ssBuilser.setSpan(span1, 0, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        final Dialog d = new Dialog(Share_main1.this);
        d.setTitle(ssBuilser);

        d.setContentView(R.layout.dialog1);
        upButton = (Button)d. findViewById(R.id.upButton);
        // set = (Button) findViewById(R.id.set);
        downButton = (Button) d.findViewById(R.id.downButton);
        editText = (TextView) d.findViewById(R.id.numberEditText);
        editText.setText("0");
        Button b1 = (Button) d.findViewById(R.id.button1);
        // Button b2 = (Button) d.findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String rr=editText.getText().toString();
              //  String c=rr + "Hr  : " + String.valueOf(np.getValue()) + "Minutes";
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();
                editor.putString("lim",rr);
               // editor.putString("min",String.valueOf(np.getValue()));
                //editor.putString("duration",c);
                editor.apply();
                dut.setText(rr); //set the value to textview

                d.dismiss();
            }
        });
      /*  b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });*/
        upButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // downButton.setBackgroundResource(R.drawable.timepicker_down_normal);
                //   upButton.setBackgroundResource(R.drawable.timepicker_up_pressed);
                if (values >= downrange && values <= uprange)
                    values++;
                if (values > uprange)
                    values = downrange;
                editText.setText("" + values);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //     downButton .setBackgroundResource(R.drawable.timepicker_down_pressed);
                //    upButton.setBackgroundResource(R.drawable.timepicker_up_normal);
                if (values >= downrange && values <= uprange)
                    values--;

                if (values < downrange)
                    values = uprange;

                editText.setText(values + "");
            }
        });
        d.show();


    }
    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    @SuppressLint("LongLogTag")
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    ((EditText)child).setTextSize(15);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextColor", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextColor", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextColor", e);
                }
            }
        }
        return false;
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

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i2) {

    }



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
        //googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.POWER_LOW);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setBearingAccuracy(Criteria.ACCURACY_LOW);
        criteria.setSpeedAccuracy(Criteria.ACCURACY_MEDIUM);
        bestProvider = locationManager.getBestProvider(criteria, true);
        location = locationManager.getLastKnownLocation(bestProvider);
        ll = location;
        // if (location != null) {
        //     onLocationChanged(location);
        //   }
        //   else {
        if(isNetworkAvailable()) {
            Boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isNetworkEnabled) {

                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListenerNetwork);
                location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            } else if (isGPSEnabled) {

                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListenerGps);
                location = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);


            }
            map.clear();
            ll = location;
            if (location != null) {
                //  s.setText("Not able to find exact location because of no internet connectivity");
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                ivv.setImageBitmap(decodeBase64(imagePreferance));

                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                //  numTxt.setText("27");
                customMarker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
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
                        new LatLng(location.getLatitude(), location.getLongitude()), 15));
            }
        }
        else
        {
            locationManager.removeUpdates( locationListenerGps);
            // Toast.makeText(getApplicationContext(),"No Internet", Toast.LENGTH_LONG).show();
            //bestProvider = locationManager.getBestProvider(criteria, true);


            locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    MIN_TIME_BW_UPDATES,
                    MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListenerNetwork);
            location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            map.clear();
            ll = location;
            if(location!=null) {
              //  s.setText("Not able to find exact location because of no internet connectivity");
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                ivv.setImageBitmap(decodeBase64(imagePreferance));

                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                //  numTxt.setText("27");
                customMarker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker))));
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
        }


        //  }
    /*    Button spinner = (Button) findViewById(R.id.spinnerrr);
        spinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String fDialogTitle = "Select Map Type";
                AlertDialog.Builder builder = new AlertDialog.Builder(Share_main.this);
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
        });*/
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
    public void setUpMapIfNeeded(){
        supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);

    }
    class Initiate1 extends AsyncTask<String, String, String> {

        boolean failure = false;
        LatLng cl = getLocationFromAddress(crnt);
        //LatLng dest = getLocationFromAddress(desti);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /*
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(INITIATE_URL);

            try {
                // Add your data
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("imeiNo", uid));
                params.add(new BasicNameValuePair("startLoclat",Double.toString (cl.latitude)));
                params.add(new BasicNameValuePair("startLoclong",Double.toString (cl.longitude)));
                params.add(new BasicNameValuePair("endLoclat",Double.toString (cl.latitude)));
                params.add(new BasicNameValuePair("endLoclong",Double.toString (cl.longitude)));
                params.add(new BasicNameValuePair("userName",name));
                params.add(new BasicNameValuePair("currentLocLat",Double.toString (cl.latitude)));
                params.add(new BasicNameValuePair("currentLocLong",Double.toString (cl.longitude)));

                httppost.setEntity(new UrlEncodedFormEntity(params));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                Log.d("Http Post Response:", response.toString());
                InputStream in = response.getEntity().getContent();
                StringBuilder stringbuilder = new StringBuilder();
                BufferedReader bfrd = new BufferedReader(new InputStreamReader(in),1024);
                String line;
                while((line = bfrd.readLine()) != null)
                    stringbuilder.append(line);

                p = stringbuilder.toString();
                SharedPreferences sharedPreferences1 =getSharedPreferences(STORAGE_NAME1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("p",p);
                editor.apply();

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            //   pDialog.dismiss();
            if(file_url != null){

                Toast.makeText(Share_main1.this, "With out destination initiated" + file_url, Toast.LENGTH_LONG).show();

            }


        }

    }
    class Initiate extends AsyncTask<String, String, String> {

        boolean failure = false;
        LatLng cl = getLocationFromAddress(crnt);
        LatLng dest = getLocationFromAddress(desti);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /*
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(INITIATE_URL);

            try {
                // Add your data
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("imeiNo", uid));
                params.add(new BasicNameValuePair("startLoclat",Double.toString (cl.latitude)));
                params.add(new BasicNameValuePair("startLoclong",Double.toString (cl.longitude)));
                params.add(new BasicNameValuePair("endLoclat",Double.toString (dest.latitude)));
                params.add(new BasicNameValuePair("endLoclong",Double.toString (dest.longitude)));
                params.add(new BasicNameValuePair("userName",name));
                params.add(new BasicNameValuePair("currentLocLat",Double.toString (cl.latitude)));
                params.add(new BasicNameValuePair("currentLocLong",Double.toString (cl.longitude)));

                httppost.setEntity(new UrlEncodedFormEntity(params));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                Log.d("Http Post Response:", response.toString());
                InputStream in = response.getEntity().getContent();
                StringBuilder stringbuilder = new StringBuilder();
                BufferedReader bfrd = new BufferedReader(new InputStreamReader(in),1024);
                String line;
                while((line = bfrd.readLine()) != null)
                    stringbuilder.append(line);

                p = stringbuilder.toString();
                SharedPreferences sharedPreferences1 =getSharedPreferences(STORAGE_NAME1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("p",p);
                editor.apply();

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return p;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            //   pDialog.dismiss();
            if(file_url != null){

                Toast.makeText(Share_main1.this, "With destination initiated "+ file_url, Toast.LENGTH_LONG).show();

            }


        }

    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
   /*     BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;*/
        Bitmap bmp =BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
        return bmp;
    }
    /*   protected void onPostCreate(Bundle savedInstanceState) {

           super.onPostCreate(savedInstanceState);
           mToggle.syncState();

       }*/
    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Share_main1.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        Share_main1.this.startActivity(intent);
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




    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this, Locale.getDefault());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }



    class Datafetch extends AsyncTask<String, String, String> {

        boolean failure = false;
        String p11;
        ;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /*
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(DATAPOST_URL);

            try {
                // Add your data
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("imeiNo", uid));
                params.add(new BasicNameValuePair("currentLocLat",clat));
                params.add(new BasicNameValuePair("currentLocLong",clong));

                httppost.setEntity(new UrlEncodedFormEntity(params));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                InputStream in = response.getEntity().getContent();
                StringBuilder stringbuilder = new StringBuilder();
                BufferedReader bfrd = new BufferedReader(new InputStreamReader(in),1024);
                String line;
                while((line = bfrd.readLine()) != null)
                    stringbuilder.append(line);

                p11 = stringbuilder.toString();
                // p=response.toString();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return p11;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            //   pDialog.dismiss();
            if(file_url != null){

                Toast.makeText(Share_main1.this, "locationchanged"+ file_url, Toast.LENGTH_LONG).show();


            }


        }

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




    private class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {
        private ArrayList<String> resultList;

        public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }
            };
            return filter;
        }
    }

    private ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            // sb.append("&components=country:in");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));
            //String input = "input="+place;
            sb.append("&types=geocode");

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
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
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
    @Override
    public void onBackPressed() {
        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared1.edit();

        editor.clear();
        editor.commit();
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
                Intent i1 = new Intent(Share_main1.this, MainActivity.class);
                // i1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i1);
                finish();
                // close this activity

            }
        }, SPLASH_TIME_OUT);


    }


    public void statusCheck()
    {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            showSettingsAlert("GPS");

        }


    }
    LocationListener locationListenerGps = new LocationListener() {

        public void onLocationChanged(Location location) {


            if(location!=null) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //  spd.setText(""+location1.getSpeed()*3600/1000 +"km/h");
                //   Toast.makeText(getApplicationContext(),""+location.getSpeed(), Toast.LENGTH_LONG).show();
                LatLng latLng = new LatLng(latitude, longitude);
                map.clear();
                //  googleMap.addMarker(new MarkerOptions().position(latLng));
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                ivv.setImageBitmap(decodeBase64(imagePreferance));
                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                //  numTxt.setText("27");
                customMarker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getApplicationContext(), marker))));
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
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    state = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    knownName = addresses.get(0).getFeatureName();
                } catch (Exception e) {

                }
                lp = address + "," + city + "," + country;


                // Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();

                if(!lp.equals("null,null,null")) {
                    s.setText(lp);
                }
                else
                {
                    s.setText("Not able to find current location");
                }
                crnt=s.getText().toString();
                clat=Double.toString (latitude);
                clong=Double.toString (longitude);
                int p1 = sqliteHelper1.dbSyncCount();
                int x = p1;
                //Toast.makeText(getApplicationContext(),""+x, Toast.LENGTH_LONG).show();
           /* if(g.getData()==100)
            {
                while (p1 > 0) {
                    final int finalP = x - p1 + 1;
                    p = Integer.toString(finalP);
                    Cursor cursor = sqliteHelper1.getUser(p);
                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        //idd = cursor.getString(cursor.getColumnIndex("id"));
                        fs = cursor.getInt(cursor.getColumnIndex("fs"));

                    }
                    if (fs == 0) {
                        new Datafetch().execute();
                    } else {
                        //  Toast.makeText(getApplicationContext(),"There is no sharing active", Toast.LENGTH_LONG).show();
                    }
                    p1--;
                }
                // Toast.makeText(getApplicationContext(),"100 is here", Toast.LENGTH_LONG).show();
            }

            else{

            }
            //   Toast.makeText(Share_main.this,""+lp, Toast.LENGTH_LONG).show();
*/
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(location.getLatitude(), location.getLongitude()), 15));


            }
            else
            {
                Toast.makeText(getApplicationContext(), "Location is not available now.Please wait for some time.", Toast.LENGTH_LONG).show();
            }

        }

        public void onProviderDisabled(String provider) {}

        public void onProviderEnabled(String provider) {}

        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };


    LocationListener locationListenerNetwork = new LocationListener() {

        public void onLocationChanged(Location location) {

            if(location!=null) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //  spd.setText(""+location1.getSpeed()*3600/1000 +"km/h");
                //   Toast.makeText(getApplicationContext(),""+location.getSpeed(), Toast.LENGTH_LONG).show();
                LatLng latLng = new LatLng(latitude, longitude);
                map.clear();
                //  googleMap.addMarker(new MarkerOptions().position(latLng));
                View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                ImageView ivv = (ImageView) marker.findViewById(R.id.num_txt);
                ivv.setImageBitmap(decodeBase64(imagePreferance));
                //  TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                //  numTxt.setText("27");
                customMarker = map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(location.getLatitude(), location
                                        .getLongitude())).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(Share_main1.this, marker))));
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
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                    address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    city = addresses.get(0).getLocality();
                    state = addresses.get(0).getAdminArea();
                    country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    knownName = addresses.get(0).getFeatureName();
                } catch (Exception e) {

                }
                lp = address + "," + city + "," + country;


                // Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();

                if(!lp.equals("null,null,null")) {
                    s.setText(lp);
                }
                else
                {
                    s.setText("Not able to find current location");
                }
                crnt=s.getText().toString();
                clat=Double.toString (latitude);
                clong=Double.toString (longitude);
                int p1 = sqliteHelper1.dbSyncCount();
                int x = p1;
                //Toast.makeText(getApplicationContext(),""+x, Toast.LENGTH_LONG).show();
           /* if(g.getData()==100)
            {
                while (p1 > 0) {
                    final int finalP = x - p1 + 1;
                    p = Integer.toString(finalP);
                    Cursor cursor = sqliteHelper1.getUser(p);
                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        //idd = cursor.getString(cursor.getColumnIndex("id"));
                        fs = cursor.getInt(cursor.getColumnIndex("fs"));

                    }
                    if (fs == 0) {
                        new Datafetch().execute();
                    } else {
                        //  Toast.makeText(getApplicationContext(),"There is no sharing active", Toast.LENGTH_LONG).show();
                    }
                    p1--;
                }
                // Toast.makeText(getApplicationContext(),"100 is here", Toast.LENGTH_LONG).show();
            }

            else{

            }
            //   Toast.makeText(Share_main.this,""+lp, Toast.LENGTH_LONG).show();
*/
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(location.getLatitude(), location.getLongitude()), 15));


            }
            else
            {
                Toast.makeText(getApplicationContext(), "Location is not available now.Please wait for some time.", Toast.LENGTH_LONG).show();
            }

        }

        public void onProviderDisabled(String provider) {}

        public void onProviderEnabled(String provider) {}

        public void onStatusChanged(String provider, int status, Bundle extras) {}




    };
}



