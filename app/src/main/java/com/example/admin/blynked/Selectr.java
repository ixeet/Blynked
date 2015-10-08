package com.example.admin.blynked;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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




public class Selectr extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    Toolbar tool;
    LinearLayout lll;
    ListView sapps;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    private Marker customMarker;
    CustomAdapter cAdapter;
    ListView lv;
    String name, imagePreferance = null;
    DrawerLayout drawerLayout;
    private static final int CONTAINER = R.id.fragment_container;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    private int mTitle = R.string.app_name;
    EditText atv;
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
    EventAdapter1 ev;
    public static final String STORAGE1 = "SenddataPreferences";
    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map", "Satellite", "Terrain", "Hybrid"};
    TextView t111;
    ImageView img_main;

    private static int MIN_TIME_BW_UPDATES = 1; // 5 sec
    private static int MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
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
    PackageManager packageManager;
    int flag;
    ApkAdapter apkadapter;
    Globals g;
  //  ListView rlv;
    private LatLngBounds latlngBounds;
    Location location, ll;
    ArrayList<Eventdata1> recentlist;
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar1;
    CircleProgressBar progressBar;
    RelativeLayout allc, apps,blynked;
    private static final String LOG_TAG = "ExampleApp";
    public static final String STR_ORDER = android.provider.CallLog.Calls.DATE + " DESC";
    public static final String[] STR_FIELDS = {
            android.provider.CallLog.Calls.NUMBER,
            android.provider.CallLog.Calls.TYPE,
            android.provider.CallLog.Calls.CACHED_NAME,
            android.provider.CallLog.Calls.CACHED_NUMBER_TYPE,
            android.provider.CallLog.Calls.DATE,

            android.provider.CallLog.Calls.DURATION, android.provider.CallLog.Calls.CACHED_NUMBER_LABEL, android.provider.CallLog.Calls.NUMBER
    };
    Button pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        statusCheck();

        setContentView(R.layout.activity_selectr);
        allc = (RelativeLayout) findViewById(R.id.allc);
        apps = (RelativeLayout) findViewById(R.id.apps);
        blynked = (RelativeLayout) findViewById(R.id.blynked);
        //this.mHandler = new Handler();

        //  this.mHandler.postDelayed(m_Runnable,5000);
        tool = (Toolbar) findViewById(R.id.tool_bar);
        atv = (EditText) findViewById(R.id.atv_places1);
        atv.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        atv.setOnFocusChangeListener( new View.OnFocusChangeListener(){

            public void onFocusChange( View view, boolean hasfocus){
                if(hasfocus){

                    view.setBackgroundResource( R.drawable.border_style);
                }
                else{
                    view.setBackgroundResource( R.drawable.lost_border_style);
                }
            }
        });
        sapps = (ListView) findViewById(R.id.sapps);
        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);

        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();
        for (PackageInfo info :packageList) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    if(info.packageName.contains("com.whatsapp")||info.packageName.contains("com.facebook.katana")||info.packageName.contains("com.twitter.android"))

                        packageList1.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
		/*To filter out System apps*/
      /*  for(PackageInfo pi : packageList) {
            boolean b = isSystemPackage(pi);
            if(!b) {
                packageList1.add(pi);
            }
        }*/

       apkadapter=new ApkAdapter(this, packageList1, packageManager);
       sapps.setAdapter(apkadapter);
        Helper.getListViewSize(sapps);
        sapps.setOnItemClickListener(this);
        //   t111=(TextView)findViewById(R.id.textView1);
        pass = (Button) findViewById(R.id.pass);
        pass.setOnClickListener(this);
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
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            RelativeLayout r1 = (RelativeLayout) findViewById(R.id.r1);

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
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
                                Intent ii1 = new Intent(Selectr.this, Share_main.class);
                                //statusCheck();
                                startActivity(ii1);
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
                                Intent ii1 = new Intent(Selectr.this, Share_main1.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent i2 = new Intent(Selectr.this, Request_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:


                        Intent i22 = new Intent(Selectr.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent i222 = new Intent(Selectr.this, Calendar_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i222);
                        finish();
                        break;
                    case 5:
                        Intent ih = new Intent(Selectr.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;


                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(Selectr.this, Setting_main.class);
                        startActivity(ii);
                        finish();
                        break;

                }


            }
        });
        try {
                /*crContacts = ContactHelper.getContactCursor(getContentResolver(), "");
                crContacts.moveToFirst();

                while (!crContacts.isAfterLast()) {
                    conNames.add(crContacts.getString(1));
                    conNumbers.add(crContacts.getString(2));
                    crContacts.moveToNext();
                }
                crContacts.close();
                name1 = conNames.toArray(new String[conNames.size()]);
                OtherName1 = conNumbers.toArray(new String[conNumbers.size()]);

                countrylist1 = new ArrayList<DataModel1>();
                for (int i = 0; i < name1.length; i++) {
                    DataModel1 country = new DataModel1(name1[i], OtherName1[i]);
                    countrylist1.add(country);
                }*/
            Cursor mCallCursor = getContentResolver().query(
                    android.provider.CallLog.Calls.CONTENT_URI,
                    STR_FIELDS,
                    null,
                    null,
                    STR_ORDER);
            if (mCallCursor.moveToFirst()) {
                int l_cnt = 0;

                String name;
                String number;
                //StringBuilder l_displayText = new StringBuilder();
                int nu = mCallCursor.getColumnIndex(STR_FIELDS[0]);
                int na = mCallCursor.getColumnIndex(STR_FIELDS[2]);
                recentlist = new ArrayList<Eventdata1>();


                // int l_colEnd = l_managedCursor.getColumnIndex(l_projection[1]);
                //  int eventLocation = l_managedCursor.getColumnIndex(l_projection[2]);
                do {
                    name = mCallCursor.getString(na);
                    //l_begin = getDateTimeStr(l_managedCursor.getString(l_colBegin));
                    number = mCallCursor.getString(nu);
                    // location = l_managedCursor.getString(eventLocation);
                    // iii = Long.valueOf(l_begin1).longValue();
               /* SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                Date date=new Date(iii);
                String d=sdf.format(date);
                Date date1=new Date(System.currentTimeMillis());
                String d1=sdf.format(date1);*/

                    //   l_end = getDateTimeStr(l_managedCursor.getString(l_colEnd));
                    // l_displayText.append(l_title + "\n" + l_begin + "\n" + l_end + "\n----------------\n");
                    ++l_cnt;
                    //myString = Long.toString(System.currentTimeMillis());

                    recentlist.add(new Eventdata1(name, number));
                    //  iii = (int) System.currentTimeMillis();
                    //  Toast.makeText(getActivity(), "" + d + "" + d1, Toast.LENGTH_LONG).show();

                } while ((mCallCursor.moveToNext()) && l_cnt < 3);
            }
            //   custom1 = new Adapterr(getApplicationContext(), countrylist1);

            ev = new EventAdapter1(getApplicationContext(), recentlist);


            //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);


        } catch (Exception e) {
            e.printStackTrace();
        }
        g = (Globals) getApplication();
        flag = g.getFlag();
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {
        name = sharedPreferences.getString("name", "");

        imagePreferance = sharedPreferences.getString("imagePreferance", "");
        //  if( imagePreferance!=null ) {
        if (!name.equals("")) {
            //  t11.setText(name);
            //     me.setText("Me "+ "("+name+")");
            //img_main.setImageBitmap(decodeBase64(imagePreferance));

            //  dp.setImageBitmap(decodeBase64(imagePreferance));
        }

        //  }
        else {
            // t11.setText("Please set your profile");
            //      Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            //    img_main.setImageBitmap(icon1);
        }
        allc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Selectr.this, Selectall.class);
                startActivity(i);
                finish();
            }
        });
        apps.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Selectr.this, Selectapp.class);
                startActivity(i);
                finish();
            }
        });
        blynked.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Selectr.this, Selectblynked.class);
                startActivity(i);
                finish();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long row) {
        PackageInfo packageInfo = (PackageInfo) parent
                .getItemAtPosition(position);
        Globals appData = (Globals) getApplicationContext();
        appData.setPackageInfo(packageInfo);

       /* Intent appInfo = new Intent(getApplicationContext(), Apkinfo.class);
        startActivity(appInfo);*/
    }





    public void loadFragment(Fragment fragment, boolean addToBackStack) {

        if (addToBackStack) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(CONTAINER, fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(CONTAINER, fragment).commit();
        }

    }


    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(mTitle);
    }

    /*   protected void onPostCreate(Bundle savedInstanceState) {

           super.onPostCreate(savedInstanceState);

           // Sync the toggle state after onRestoreInstanceState has occurred.

           mToggle.syncState();

       }*/
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showSettingsAlert("GPS");

        }


    }

    public void showSettingsAlert(String provider) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                Selectr.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        Selectr.this.startActivity(intent);
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
                Intent ii1 = new Intent(Selectr.this, Share_main.class);
                //statusCheck();
                startActivity(ii1);
                finish();
                // close this activity

            }
        }, SPLASH_TIME_OUT);
    }


    @Override
    public void onClick(View view) {
        if (ev != null) {

            ArrayList<Eventdata1> mArrayProducts = ev.getCheckedItems();
            //ArrayList<DataModel2> mArrayProducts1 = custom1.getCheckedItems();

            String allcc = mArrayProducts.toString();
            String g = atv.getText().toString();
            String regexStr = "^[+]?[0-9]{10,13}$";
            if (g.matches(regexStr) == true) {
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();
                editor.putString("rcp", g);
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
                        Intent ii1 = new Intent(Selectr.this, Share_main.class);
                        //statusCheck();
                        startActivity(ii1);
                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);

            } else if (!allcc.equals("[]")) {

                // Toast.makeText(getApplicationContext(),allcc,Toast.LENGTH_LONG).show();
                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();
                editor.putString("rcp", allcc);
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
                        Intent ii1 = new Intent(Selectr.this, Share_main.class);
                        //statusCheck();
                        startActivity(ii1);
                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);

            } else {

                Toast.makeText(Selectr.this, "Select valid recipient/number", Toast.LENGTH_LONG).show();
            }

        }


    }
}









