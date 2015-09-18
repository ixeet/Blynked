package com.example.admin.blynked;

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
import android.telephony.TelephonyManager;
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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
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
import android.widget.RelativeLayout.LayoutParams;

public class Selects extends ActionBarActivity implements LocationListener, AdapterView.OnItemClickListener,OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapClickListener {


    Toolbar tool;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    private Marker customMarker;
    CustomAdapter cAdapter;
    ListView lv;
    int cd;
    String name,imagePreferance = null;
    DrawerLayout drawerLayout;
    private static final int CONTAINER = R.id.fragment_container;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    private int mTitle = R.string.app_name;
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
    TelephonyManager mngr;
    public static final String STORAGE1 = "SenddataPreferences";
    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map","Satellite", "Terrain", "Hybrid"};
    TextView t111;
    ImageView img_main;
    String lp1;
    private static int MIN_TIME_BW_UPDATES = 1; // 5 sec
    private static int MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    GoogleMap map;
    Marker lastOpenned = null;
    String lp, add;
    String[] k = new String[2];
    String state, address, city, country, knownName;
    Geocoder geocoder;
    List<Address> addresses;
    List<Address> addresses1;
    String state1, address1, city1, country1, knownName1;
    Boolean isGPSEnabled;
    private int width, height;
    private Polyline newPolyline;
    LatLng dest, l;
    int flag;
    Globals g;
    private LatLngBounds latlngBounds;
    Location location,ll;
    private static final String LOG_TAG = "ExampleApp";
    private static final String DATAPOST_URL="http://191.239.57.54:8080/Blynk/TrackUser";
    String clat,clong;
    private static final String PLACES_API_BASE1 = "http://maps.googleapis.com/maps/api/directions/json";
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";
    TextView t1,t2;
    Spinner spinner;
    LocationManager locationManager;
    String selState;
    String bestProvider;
    SupportMapFragment supportMapFragment;
    Button share;
    Handler mHandler;
    static final int LOCATION_SETTINGS_REQUEST = 1;
    private static final String API_KEY = "AIzaSyB9vcuLI-1dSeJ68Z9xvdm2j4TsLTQw7_A";
    TextView me,st,spd;
    ImageView dp;
    int key=0;
    String uid;
    BitmapDescriptor icon1;
    Transparent popup;
    private Animation slideRight,slideLeft;
    TextView t11;
    ImageButton setd;
    AutoCompleteTextView autoCompView;
    Button sharem,requestm,favm;
    Button pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        statusCheck();

        setContentView(R.layout.activity_selects);
        setd=(ImageButton)findViewById(R.id.setd);
        pass=(Button)findViewById(R.id.pass);
        autoCompView = (AutoCompleteTextView) findViewById(R.id.atv_places);
        autoCompView.setThreshold(1);
        autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.list_item));

        autoCompView.setOnItemClickListener(this);
        //this.mHandler = new Handler();

        //  this.mHandler.postDelayed(m_Runnable,5000);
        tool = (Toolbar) findViewById(R.id.tool_bar);
        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uid = mngr.getDeviceId();
        //   t111=(TextView)findViewById(R.id.textView1);

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
            RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    case 0:
                        Intent i1 = new Intent(Selects.this, Share_main.class);
                        //statusCheck();
                        startActivity(i1);
                        finish();
                        break;
                    case 1:
                        Intent i11 = new Intent(Selects.this, Share_main1.class);
                        //statusCheck();
                        startActivity(i11);
                        finish();
                        break;
                    case 2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent i2 = new Intent(Selects.this, Request_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:

                        Intent i22 = new Intent(Selects.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        Intent i222 = new Intent(Selects.this, Calendar_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i222);
                        finish();
                        break;
                    case 5:
                        Intent ih = new Intent(Selects.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;



                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                       Intent ii = new Intent(Selects.this,Setting_main.class);
                        startActivity(ii);
                        finish();
                        break;

                }


            }
        });
        setd.setOnClickListener(this);
        g = (Globals)getApplication();
        flag=g.getFlag();
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
        supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(this);

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add=autoCompView.getText().toString();
                if(!add.equals(""))
                {
                    SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared1.edit();
                    editor.putString("Current",add);

                    //editor.putString("destination", add);
                    editor.apply();
                    Intent i=new Intent(Selects.this,Share_main.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please select your source location to share .", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        //   add=e1.getText().toString();

        InputMethodManager imm = (InputMethodManager) getSystemService(Selects.this.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }
    @Override
    public void onClick(View view) {

        // String add = (String) adapterView.getItemAtPosition(position);
        String add=autoCompView.getText().toString();
        l = new LatLng(location.getLatitude(), location
                .getLongitude());
        LatLng dest = getLocationFromAddress(add);
        statusCheck();
        try {
            Marker marker1 = map.addMarker(new MarkerOptions()
                    .position(
                            new LatLng(dest.latitude, dest.longitude)).title(add));
            marker1.showInfoWindow();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        findDirections(l.latitude, l.longitude, dest.latitude, dest.longitude, GMapV2Direction.MODE_DRIVING);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(l);
        builder.include(dest);
        LatLngBounds bounds = builder.build();
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,20));
        k=Timed(lp,add);
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
    public void findDirections(double fromPositionDoubleLat, double fromPositionDoubleLong, double toPositionDoubleLat, double toPositionDoubleLong, String mode) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LAT, String.valueOf(fromPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.USER_CURRENT_LONG, String.valueOf(fromPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DESTINATION_LAT, String.valueOf(toPositionDoubleLat));
        map.put(GetDirectionsAsyncTask.DESTINATION_LONG, String.valueOf(toPositionDoubleLong));
        map.put(GetDirectionsAsyncTask.DIRECTIONS_MODE, mode);

        GetDirectionsAsyncTask2 asyncTask = new GetDirectionsAsyncTask2(this);
        asyncTask.execute(map);
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
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnMapClickListener(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.POWER_LOW);
      //  criteria.setAccuracy(Criteria.ACCURACY_HIGH);

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
        //  }
        Button spinner = (Button) findViewById(R.id.spinnerrr);
        spinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String fDialogTitle = "Select Map Type";
                AlertDialog.Builder builder = new AlertDialog.Builder(Selects.this);
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
                Selects.this);

        alertDialog.setTitle(provider + " SETTINGS");

        alertDialog
                .setMessage(provider + " is not enabled! Want to go to settings menu?");

        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        Selects.this.startActivity(intent);
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
        if(location1!=null) {
            double latitude = location1.getLatitude();
            double longitude = location1.getLongitude();
            //  spd.setText(""+location1.getSpeed()*3600/1000 +"km/h");
            //   Toast.makeText(getApplicationContext(),""+location.getSpeed(), Toast.LENGTH_LONG).show();
            LatLng latLng = new LatLng(latitude, longitude);
            //  googleMap.addMarker(new MarkerOptions().position(latLng));
        /*    *//**//*MarkerOptions mp = new MarkerOptions();

            mp.position(new LatLng(location.getLatitude(), location.getLongitude()));

            mp.title("priyanka");

            googleMap.addMarker(mp);*//**//**/
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
            autoCompView.setText(lp);
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
                    lastOpenned =customMarker;

                    // Event was handled by our code do not launch default behaviour.
                    return true;
                }
            });
            //clat=Double.toString (latitude);
            //clong=Double.toString (longitude);

            /*if(g.getData()==100)
            {
                new Datafetch().execute();
                // Toast.makeText(getApplicationContext(),"100 is here", Toast.LENGTH_LONG).show();
            }
            else if(g.getData()==300)
            {
                //Toast.makeText(getApplicationContext(),"Service stopped", Toast.LENGTH_LONG).show();
            }*/
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
    @Override
    public void onMapClick(LatLng point) {
        map.clear();
        MarkerOptions mp = new MarkerOptions();

        mp.position(new LatLng(point.latitude, point.longitude));


        //mp.title("priyanka");

        final Marker m1=map.addMarker(mp);
        m1.showInfoWindow();
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
                m1.showInfoWindow();
                // Re-assign the last openned such that we can close it later
                lastOpenned =m1;

                // Event was handled by our code do not launch default behaviour.
                return true;
            }
        });

        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses1 = geocoder.getFromLocation(point.latitude, point.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            address1 = addresses1.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            city1 = addresses1.get(0).getLocality();
            state1 = addresses1.get(0).getAdminArea();
            country1 = addresses1.get(0).getCountryName();
            //String postalCode = addresses.get(0).getPostalCode();
            knownName1 = addresses1.get(0).getFeatureName();
        } catch (Exception e) {

        }
        lp1 = address1 + "," + city1 + "," + country1;
        autoCompView.setText(lp1);

        map.animateCamera(CameraUpdateFactory.newLatLng(point));
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
            // place type to be searched
            //  String types = "types=geocode";
            // sb.append("&sensor=false");
            // Sensor enabled
            // String sensor = "sensor=false";

            // Building the parameters to the web service
            // String parameters = input+"&"+types+"&"+sensor+"&"+key;

            // Output format
            //String output = "json";

            // Building the url to the web service
            //String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;
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
            // Building the parameters to the web service
            // String parameters = input+"&"+types+"&"+sensor+"&"+key;

            // Output format
            //String output = "json";
            //Toast.makeText(getApplicationContext(),sb.toString(), Toast.LENGTH_LONG).show();
            // Building the url to the web service
            //    String url = "https://maps.googleapis.com/maps/api/place/autocomplete/"+output+"?"+parameters;
            URL url = new URL(sb.toString());
            //   conn1 = (HttpURLConnection) url.openConnection();
            //     InputStreamReader in = new InputStreamReader(conn1.getInputStream());
            // Load the results into a StringBuilder
            //    URL url = new URL(sb.toString());
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
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Share_main.class);
        startActivity(i);
        finish();
    }
}









