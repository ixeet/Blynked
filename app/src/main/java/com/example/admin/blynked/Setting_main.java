package com.example.admin.blynked;

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
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Setting_main extends ActionBarActivity {

    EditText pName;
    private final int SELECT_PHOTO = 22;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap selectedImage;
    ImageView bRate, bFeedback, bHelp,bAbout;
 Button fb;
    CustemListAdapter2 genAdapter;
    ListView GlistView;
    String[] itemLink;
    String[] itemGen;
    ArrayList<DataModelsetting> linkList;
    ArrayList<DataModelsetting1> genList;

    private LoginManager manager;
    String textEmail;
    //CustemListAdapter cAdapter;
    //ListView bList;
    //List<String> listitem;
    Toolbar tool;
    String imagepath,str_id,fname;
    String p ="p";    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    ProgressDialog pDialog;
    private Marker customMarker;
    CustomAdapter cAdapter;
    ListView lv;
    EditText e1;
    Profile profile;
    String name,imagePreferance = null;
    DrawerLayout drawerLayout;
    private static final int CONTAINER = R.id.fragment_container;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    private int mTitle = R.string.app_name;
    CallbackManager callbackManager;
    public static final String STORAGE_IMAGE = "Image";
    Integer[] img_id = {
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_share_menu_drawer,
            R.mipmap.icn_request_menu_drawer,


            R.mipmap.icn_favourite_menu_drawer,
            R.mipmap.icn_calendar_menu_drawer,
            R.mipmap.icn_history_menu_drawer,
            R.mipmap.icn_settings_menu_drawer,



    };
    private static final String UPDATE_URL ="http://191.239.57.54:8080/Blynk/updateSettingController";
    EventAdapter1q ev;
    public static final String STORAGE_NAME = "MySharedPreferencess";
    public static final String STORAGE1 = "SenddataPreferences";
    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map","Satellite", "Terrain", "Hybrid"};
    TextView t111;
    ImageView img_main;
String phoneNo;
    private static int MIN_TIME_BW_UPDATES = 1; // 5 sec
    private static int MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    GoogleMap map;
    Marker lastOpenned = null;
    String lp, add,p11;
    String[] k = new String[2];
    String state, address, city, country, knownName;
    Geocoder geocoder;
    List<Address> addresses;
    Boolean isGPSEnabled;
    private int width, height;
    private Polyline newPolyline;
    LatLng dest, l;
    int flag;
    Globals g;
    ListView rlv;

    Intent data;
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar1;

    //String[] btnListArr=new String[] {"RateUs","Feedback","Help"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        g = (Globals) getApplication();
        //this.mHandler = new Handler();

        //  this.mHandler.postDelayed(m_Runnable,5000);
        tool = (Toolbar) findViewById(R.id.tool_bar);


        //   t111=(TextView)findViewById(R.id.textView1);
        final List<String> permissionNeeds = Arrays.asList("publish_actions");
        callbackManager = CallbackManager.Factory.create();
e1=(EditText)findViewById(R.id.e1);
        fb=(Button)findViewById(R.id.fb);
        if(g.getfb()==0)
        {
            fb.setText("Login");
        }
        else
        {
            fb.setText("Logout");
        }
        fb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (fb.getText().equals("Login")) {
                    manager = LoginManager.getInstance();
                    // manager.setLoginBehavior(LoginBehavior.SSO_WITH_FALLBACK);
                    manager.logInWithPublishPermissions(Setting_main.this, permissionNeeds);
                    manager.registerCallback(callbackManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {
                                    Log.d("accessyes", "" + loginResult.getAccessToken());
                                    // App code
                                    // Toast.makeText(getApplicationContext(), "yesss", Toast.LENGTH_SHORT).show();
                                    System.out.println("Success");

                                    //  loginbutton.setVisibility(View.INVISIBLE);
                                    GraphRequest.newMeRequest(
                                            loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                                @Override
                                                public void onCompleted(JSONObject json, GraphResponse response) {
                                                    if (response.getError() != null) {
                                                        // handle error
                                                        System.out.println("ERROR");
                                                    } else {
                                                        System.out.println("Success");
                                                        try {
                                                            g.setfb(1);
                                                            fb.setText("Logout");
                                                            String jsonresult = String.valueOf(json);
                                                            System.out.println("JSON Result" + jsonresult);

                                                            // String str_email = json.getString("email");
                                                            str_id = json.getString("id");
                                                            fname = json.getString("name");
                                                            Log.d("data fb", str_id + fname);
                                                            profile = Profile.getCurrentProfile();
                                                            if(profile!=null) {
                                                                if (isNetworkAvailable()) {
                                                                    new getc().execute();
                                                                } else {
                                                                    Toast.makeText(getApplicationContext(),
                                                                            "Please connect to Internet",
                                                                            Toast.LENGTH_LONG).show();
                                                                }
                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        //  String str_lastname = json.getString("last_name");
                                                        //   String link = json.getString("link");
                                                        //   String gender = json.getString("gender");
                                                        //Toast.makeText(getApplicationContext(), str_email, Toast.LENGTH_SHORT).show();
                                                        //textView1.setText(str_email);
                                                        //textView2.setText(str_id);
                                                        //textView3.setText(str_firstname);
                                                        //textView4.setText(link);
                                                        //button.setEnabled(true);
                                                        //verify.setText(str_email+""+name);


                                                        //textView5.setText(fields);

                                                    }
                                                }

                                            }).executeAsync();

                                }

                                //Intent i = new Intent(MainActivity.this, New.class);
                                // startActivity(i);
                                //        AccessToken accessToken = loginResult.getAccessToken();
                                //       Profile profile = Profile.getCurrentProfile();
                                //     displayMessage(profile);


                                @Override
                                public void onCancel() {
                                    // App code
                                    Log.d("fb cancel", "cancelled");
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    // App code
                                    Log.d("fb error", exception.toString());
                                }
                            });
                } else {
                    selectedImage=null;
                    e1.setText("");
                    Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.proff);
                    img_main.setImageBitmap(icon);
                    SharedPreferences s2= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = s2.edit();
                    editor.putString("imagePreferance","");
                    editor.putString("name","");
                    editor.apply();
                    editor.commit();
                    LoginManager.getInstance().logOut();
                    fb.setText("Login");
                    g.setfb(0);
                }
            }
            });
        e1.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
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
            RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1q);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    case 0:
                        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editorr = shared1.edit();

                        editorr.clear();
                        editorr.commit();
                        name = e1.getText().toString();

                        SharedPreferences s1= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s1.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s1.edit();
                         //   editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                    //    new Wait().execute();
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
                                Intent ii1 = new Intent(Setting_main.this, Share_main.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 1:

                        name = e1.getText().toString();

                        SharedPreferences s21= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s21.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s21.edit();
                            //   editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                        //    new Wait().execute();
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
                                Intent ii1 = new Intent(Setting_main.this, Share_main1.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:

                        name = e1.getText().toString();

                        SharedPreferences s11= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s11.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s11.edit();
                        //    editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                        //drawerLayout.closeDrawer(GravityCompat.START);
                        Intent i2 = new Intent(Setting_main.this, Request_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:
                        name = e1.getText().toString();

                        SharedPreferences s111= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s111.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s111.edit();
                        //    editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                        Intent i22 = new Intent(Setting_main.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        name = e1.getText().toString();

                        SharedPreferences s1111= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s1111.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s1111.edit();
                           // editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ia2 = new Intent(Setting_main.this, Calendar_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(ia2);
                        finish();
                        break;
                    case 5:
                        name = e1.getText().toString();

                        SharedPreferences s11111= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s11111.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s11111.edit();
                           // editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(Setting_main.this,HistoryActivity.class);
                        startActivity(ii);
                        finish();

                        break;
                    case 6:
                        name = e1.getText().toString();

                        SharedPreferences s2= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                        imagePreferance = s2.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                        if(selectedImage!=null ) {

                            SharedPreferences.Editor editor = s2.edit();
                          //  editor.clear();
                            editor.putString("imagePreferance", encodeTobase64(selectedImage));
                            editor.putString("name", name);

                            editor.apply();
                            editor.commit();
                        }
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ipi = new Intent(Setting_main.this,Setting_main.class);
                        startActivity(ipi);
                        finish();
                        break;

                }


            }
        });


        flag=g.getFlag();
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {

        if (android.os.Build.VERSION.SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }




        itemGen = getResources().getStringArray(R.array.general_list);
        itemLink=getResources().getStringArray(R.array.Link_List);
        //btnListArr=getResources().getStringArray(R.array.Litem);
        img_main = (ImageView) findViewById(R.id.imag_icon);

//        pName.getBackground().setColorFilter(getResources().getColor(R.color.orange), PorterDuff.Mode.SRC_ATOP);

        GlistView = (ListView) findViewById(R.id.listOfgeneral);
        //expListViewgen.setTranscriptMode(ExpandableListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        //bList=(ListView)findViewById(R.id.btnList);
        /*bRate = (ImageView) findViewById(R.id.rateus);
        bFeedback = (ImageView) findViewById(R.id.feedback);
        bHelp = (ImageView) findViewById(R.id.help);
        bAbout=(ImageView)findViewById(R.id.about);*/
        imagePreferance = sharedPreferences.getString("imagePreferance", "");
        name = sharedPreferences.getString("name", "");
        imagepath=sharedPreferences.getString("imagepath", "");
     //   Toast.makeText(Setting_main.this,imagepath,Toast.LENGTH_LONG).show();
        if(!name.equals(""))
        {
            e1.setText(name);
            img_main.setImageBitmap(decodeBase64(imagePreferance));
        }
        else
        {
           // Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_user_2_orange);
           // img_main.setImageBitmap(icon1);
        }




/*

        bRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Setting_main.this, "this feature coming soon", Toast.LENGTH_LONG).show();
                //bRate.setEnabled(false);
            }
        });

        bFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        bAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });*/

        img_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFrom();
            }
        });


        // First Expendable List Data
        LinkedListData();
        genrealListData();




        genAdapter = new CustemListAdapter2(Setting_main.this, this,genList);
        //cAdapter=new  CustemListAdapter(this,btnListArr);


        GlistView.setAdapter(genAdapter);
        Helper.getListViewSize(GlistView);
        /*bList.setAdapter(cAdapter);
        bList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(MainActivity.this,"this feture will be coming soon",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Uri uriF = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                        Intent intentF = new Intent(Intent.ACTION_VIEW, uriF);
                        startActivity(intentF);
                        break;
                    case 2:
                        Uri uriH = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
                        Intent intentH = new Intent(Intent.ACTION_VIEW, uriH);
                        startActivity(intentH);
                        break;


                }
            }
        });*/
    }

    class getc extends AsyncTask<String, String, Bitmap> {

        /**
         * Before starting background thread Show Progress Dialog
         */

        Profile profile = Profile.getCurrentProfile();
        URL url = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag



            try {

                url = new URL("https://graph.facebook.com/"+profile.getId()+"/picture?type=large");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpURLConnection.setFollowRedirects(true);
            conn.setInstanceFollowRedirects(true);
            //Bitmap fbpicture = null;
            try {
                selectedImage = BitmapFactory.decodeStream(conn.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return selectedImage;






        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(Bitmap photo) {

            img_main.setImageBitmap(photo);
            e1.setText(fname);
            SharedPreferences s2= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = s2.edit();
            editor.putString("imagePreferance", encodeTobase64(photo));
            editor.putString("name",fname);
            editor.apply();
            editor.commit();
        }
    }

   /* private void btnClickList() {
        listitem=new ArrayList<String>();
        for (int i=0;i<btnListArr.length;i++) {
            listitem.add("RateUs");
            listitem.add("Feedback");
            listitem.add("Help");
        }
    }*/

    public boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.e("Network Testing", "***Available***");
            return true;
        }
        Log.e("Network Testing", "***Not Available***");
        return false;
    }
    private void LinkedListData() {
        linkList=new ArrayList<DataModelsetting>();
        for (int i=0;i<itemLink.length;i++) {
            DataModelsetting country = new DataModelsetting(itemLink[i]);
            linkList.add(country);

        }
    }

    private void genrealListData() {

        genList = new ArrayList<DataModelsetting1>();



        for (int i = 0; i < itemGen.length; i++) {
            DataModelsetting1 country = new DataModelsetting1(itemGen[i]);
            genList.add(country);
        }


        // Adding child data




    }


    private void getImageFrom() {

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Setting_main.this);
        builder.setTitle("Select Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                } else if (items[item].equals("Choose from Library")) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;*/
        Bitmap bmp =BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
        return bmp;
    }
    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {               imgRatio = maxHeight / actualHeight;                actualWidth = (int) (imgRatio * actualWidth);               actualHeight = (int) maxHeight;             } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight,Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }
    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;      }       final float totalPixels = width * height;       final float totalReqPixelsCap = reqWidth * reqHeight * 2;       while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }
    public void setDisableshare() {
        bFeedback.setEnabled(false);


    }

    public void setEnableshare() {
        bFeedback.setEnabled(true);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        callbackManager.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // final Uri imageUri = imageReturnedIntent.getData();
                       /* final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                       // options.inSampleSize = 2;
                        options.inPreferredConfig = Bitmap.Config.RGB_565;

                        selectedImage = BitmapFactory.decodeStream(imageStream);

                        img_main.setImageBitmap(selectedImage);*/
                        String p1=imageReturnedIntent.getData().toString();
                         p=compressImage(p1);
                        SharedPreferences s1= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = s1.edit();
                            editor.putString("imagepath",p);
                            editor.apply();

                        // filee=p;
                        // Uri uri = Uri.parse(p);
                        selectedImage = BitmapFactory.decodeFile(p);
                        img_main.setImageBitmap(selectedImage);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    selectedImage = (Bitmap) extras.get("data");
                    Uri pp=getImageUri(getApplicationContext(),selectedImage);
                    String p1=pp.toString();
                    p = compressImage(p1);
                    SharedPreferences s1= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = s1.edit();
                    editor.putString("imagepath",p);
                    editor.apply();
                    editor.commit();
                    selectedImage = BitmapFactory.decodeFile(p);
                    img_main.setImageBitmap(selectedImage);


                }
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    /* profileTracker = new ProfileTracker() {
        @Override
        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
            updateProfile();
        }
    }*/




    private void storeRegIdinServer(final String username) {

        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //RelativeLayout rl=(RelativeLayout)findViewById(R.id.done);
        phoneNo = sharedPreferences.getString("phoneNo", "");
        DefaultHttpClient client = new DefaultHttpClient();
        //Toast.makeText(Setting_main.this, p, Toast.LENGTH_LONG).show();
       // Toast.makeText(Setting_main.this, phoneNo+username+p, Toast.LENGTH_LONG).show();
        SharedPreferences s1= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
        p = s1.getString("imagepath", "");
       // Toast.makeText(Setting_main.this, phoneNo+username+p, Toast.LENGTH_LONG).show();
        final File file1 = new File(p);

        final FileBody fileBody = new FileBody(file1);
        // Toast.makeText(Otp.this, name.getText().toString()+email.getText().toString()+""+phoneNo+""+p, Toast.LENGTH_LONG).show();
        // File file = new File(p, ContentType.DEFAULT_BINARY);
        HttpPost post = new HttpPost(UPDATE_URL);
        //  FileBody fileBody = new FileBody(file);
        StringBody stringBody1 = new StringBody(username, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody2 = new StringBody("+91"+phoneNo, ContentType.MULTIPART_FORM_DATA);



        // String boundary = "-------------" + System.currentTimeMillis();
        // post.setHeader("Content-type", "multipart/form-data; boundary="+boundary);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //  builder.setBoundary(boundary);
        builder.addPart("file", fileBody);
        builder.addPart("username", stringBody1);
        builder.addPart("mobileNum", stringBody2);


        HttpEntity entity = builder.build();
//
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);
            Log.d("Http Post Response:", response.toString());
            InputStream in = response.getEntity().getContent();
            StringBuilder stringbuilder = new StringBuilder();
            BufferedReader bfrd = new BufferedReader(new InputStreamReader(in),1024);
            String line;
            while((line = bfrd.readLine()) != null)
                stringbuilder.append(line);

            p11 = stringbuilder.toString();


            Toast.makeText(Setting_main.this, "Sent "+p11, Toast.LENGTH_LONG).show();

       } catch (IOException ee) {
            ee.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {

        name = e1.getText().toString();
        storeRegIdinServer(name);
        SharedPreferences s1= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
        imagePreferance = s1.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
       if(selectedImage!=null ) {

            SharedPreferences.Editor editor = s1.edit();
          //  editor.clear();
            editor.putString("imagePreferance", encodeTobase64(selectedImage));
            editor.putString("name", name);

            editor.apply();
            editor.commit();
        }
        else
       {
           SharedPreferences.Editor editor = s1.edit();
           //  editor.clear();
           editor.putString("imagePreferance", imagePreferance);
           editor.putString("name", name);

           editor.apply();
           editor.commit();
       }
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
                Intent ii1 = new Intent(Setting_main.this, MainActivity.class);
                //statusCheck();
                startActivity(ii1);
                finish();
                // close this activity

            }
        }, SPLASH_TIME_OUT);

    }

}





