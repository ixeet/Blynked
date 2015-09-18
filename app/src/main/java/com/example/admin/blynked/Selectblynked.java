package com.example.admin.blynked;

import android.net.Uri;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Selectblynked extends ActionBarActivity implements  View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrayList<String> conNames;
    private ArrayList<String> conNumbers;
    private Cursor crContacts;
    SearchView searchView;
    ListView lv1;
    int p=0;
    int p1=0;
    Adapterr custom1;
    ArrayList<DataModel1> countrylist1;
    String[] name1, OtherName1, name2;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    CustomAdapter cAdapter;
    ListView lv;
    JSONParser jsonParser = new JSONParser();
    Toolbar tool;
    String pK;
    TextView t11;
    ProgressDialog pDialog;
    ImageView img_main;
    String name, imagePreferance;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mToggle;
    CharSequence mDrawerTitle;
    Button pass;
    Boolean [] pp;
    String [] name11;
    private static final String VALID_URL ="http://191.239.57.54:8080/Blynk/getValidNumber";
    private int mTitle = R.string.app_name;
    private static final int CONTAINER = R.id.fragment_container;
    public static final String STORAGE1 = "SenddataPrefer`ences";
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
    String phoneNo;
    String[] number;
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar1;
    CircleProgressBar progressBar;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectblynked);
        /*SharedPreferences sharedPreferences111 = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        phoneNo = sharedPreferences111.getString("phoneNo", "");*/
        //  searchView = (SearchView) findViewById(R.id.search);
        lv1 = (ListView) findViewById(R.id.list);
        lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

                //Toast.makeText(Selectall.this, "hiiiikok", Toast.LENGTH_LONG).show();
            }
        });
        // rlv = (ListView) findViewById(R.id.list);

        conNames = new ArrayList<String>();
        conNumbers = new ArrayList<String>();
        //  new Wait().execute();

        pass=(Button)findViewById(R.id.pass);
        pass.setOnClickListener(this);
        tool = (Toolbar) findViewById(R.id.tool_bar);
        if (tool != null) {
            setSupportActionBar(tool);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            tool.setNavigationIcon(R.mipmap.menu_converted);
            //getSupportActionBar().setHomeButtonEnabled(true);
            mDrawerTitle = getTitle();
            name_list = getResources().getStringArray(R.array.item_name);
            //img_list=getResources().getIntArray(R.array.img_id);
            lv = (ListView) findViewById(R.id.nav_list);
            //  t11 = (TextView) findViewById(R.id.t1);
            //img_main = (ImageView) findViewById(R.id.img_icon);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);

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
                                Intent ii1 = new Intent(Selectblynked.this, Share_main.class);
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
                                Intent ii1 = new Intent(Selectblynked.this, Share_main1.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:
                        FrameLayout r11 = (FrameLayout) findViewById(CONTAINER);
                        r11.setVisibility(View.INVISIBLE);
                        Intent i2 = new Intent(Selectblynked.this, Request_main.class);
                        //i2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i2);
                        finish();
                        //   drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case 3:


                        Intent i22 = new Intent(Selectblynked.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        Intent iih = new Intent(Selectblynked.this, Calendar_main.class);
                        startActivity(iih);
                        finish();
                        break;
                    case 5:
                        Intent ih = new Intent(Selectblynked.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(Selectblynked.this, Setting_main.class);
                        startActivity(ii);
                        finish();
                        break;
                }


            }
        });
/*
        searchView.setIconifiedByDefault(false);
        searchView.setOnCloseListener(this);
        searchView.setOnQueryTextListener(this);*/
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {
        name = sharedPreferences.getString("name", "");
        imagePreferance = sharedPreferences.getString("imagePreferance", "");
        if (!name.equals("")) {
            //   t11.setText(name);
            //  img_main.setImageBitmap(decodeBase64(imagePreferance));
        } else {

            // t11.setText("Please set your profile");
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.icn_user_2_orange);
            //   img_main.setImageBitmap(icon);
        }
        try {
            crContacts = ContactHelper.getContactCursor(getContentResolver(), "");
            crContacts.moveToFirst();

            while (!crContacts.isAfterLast()) {
                conNames.add(crContacts.getString(1));
                conNumbers.add(crContacts.getString(2));
                crContacts.moveToNext();
            }
            crContacts.close();
           // name1 = conNames.toArray(new String[conNames.size()]);
            OtherName1 = conNumbers.toArray(new String[conNumbers.size()]);


           /* countrylist1 = new ArrayList<DataModel1>();
            for (int i = 0; i < conNames.size(); i++) {
                DataModel1 country = new DataModel1(name1[i], OtherName1[i]);
                countrylist1.add(country);
            }*/
           // custom1 = new Adapterr(getApplicationContext(), countrylist1);

            JSONObject Parent = new JSONObject();
            JSONArray array = new JSONArray();
            JSONArray data;

            for (int i = 0 ; i < OtherName1.length ; i++)
            {

                   // OtherName1[i] = OtherName1[i].replaceAll("\\s+", "");
                OtherName1[i] = OtherName1[i].replaceAll("\\D", "");
                if(OtherName1[i].length() >= 10) {
                    OtherName1[i] = OtherName1[i].substring(OtherName1[i].length() - 10);
                    JSONObject jsonObj = new JSONObject();
                    //  jsonObj.put("Name", name1[i]);
                    jsonObj.put("Number", "+91" + OtherName1[i]);

                    array.put(jsonObj);
                }
            }
            Parent.put("mobNumber", array);
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(VALID_URL);
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

            pK = stringbuilder.toString();
            //Log.d("Returned",pK);

            try {
                JSONObject jsonObj = new JSONObject(pK);

                // Getting JSON Array node
                data = jsonObj.getJSONArray("responseList");
                Log.d("Returned",""+data.length());
                Log.d("Returned",""+data);

                //data = new JSONArray(p);
                //  Toast.makeText(Selectblynked.this,""+data.length(), Toast.LENGTH_LONG).show();

                for (int i = 0; i < data.length(); i++) {
                    //  JSONObject c = data.getJSONObject(i);
                    //  Log.d("Yess",data.getJSONObject(i).getString(""+i));
                    list.add(data.get(i).toString());
                  //  list.add(data.get(i).getString(""+i));
                    //list1.add(data.getJSONObject(i).getString("exam_id"));
                // Log.d("got it", data[i]);
                }
                //  t.setText("There are "+data.length()+" tests available for you.");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            number = list.toArray(new String[list.size()]);

            //   OtherName = list1.toArray(new String[list1.size()]);

          //  Log.d("got it", ""+number);
            /*for (int i = 0; i < number.length; i++) {
                Log.d("got it", number[i]);
            }*/
            name11=new String[number.length];
            countrylist1 = new ArrayList<DataModel1>();
            for (int i = 0; i < number.length; i++) {

                name11[i]=getname(getApplicationContext(),number[i]);
                DataModel1 country = new DataModel1(name11[i],number[i]);
                countrylist1.add(country);
            }

            custom1 = new Adapterr(getApplicationContext(), countrylist1);
            //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);

        } catch (Exception e) {
            e.printStackTrace();
        }


        lv1.setAdapter(custom1);

    }
    public String getname(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactName = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
            }
            cursor.close();
        }
        return contactName;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //  Toast.makeText(Selectall.this, "hiiiikok", Toast.LENGTH_LONG).show();
    }





    public String getJSONUrl(String url) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Download OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download file..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }
    public static Bitmap retrieveContactPhoto(Context context, String number) {
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.icn_user_2_orange);

        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

            if (inputStream != null) {
                photo = BitmapFactory.decodeStream(inputStream);
            }

            //  assert inputStream != null;
            // inputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return photo;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater imf = getMenuInflater();
        imf.inflate(R.menu.menu_main, menu);
        return true;
    }


    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
       /* BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;*/
        Bitmap bmp = BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
        return bmp;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, Selectr.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onClick(View view) {
        if (custom1 != null) {
            ArrayList<DataModel1> mArrayProducts = custom1.getCheckedItems();
            //ArrayList<DataModel2> mArrayProducts1 = custom1.getCheckedItems();

            String allcc = mArrayProducts.toString();


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
                    Intent ii1 = new Intent(Selectblynked.this, Share_main.class);
                    //statusCheck();
                    startActivity(ii1);
                    finish();
                    // close this activity

                }
            }, SPLASH_TIME_OUT);


        }

    }
}