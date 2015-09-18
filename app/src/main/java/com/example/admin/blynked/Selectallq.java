package com.example.admin.blynked;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.net.Uri;


import java.io.InputStream;
import java.util.ArrayList;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class Selectallq extends ActionBarActivity implements SearchView.OnCloseListener, SearchView.OnQueryTextListener {

    private ArrayList<String> conNames;
    private ArrayList<String> conNumbers;
    private Cursor crContacts;

    ListView lv1;
    int p=0;
    int p1=0;
    Adapterrq custom1;
    ArrayList<DataModel1> countrylist1;
    String[] OtherName1, name2;
    String [] name1;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
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
    //Button pass;
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
    SearchView searchView;
    private static int SPLASH_TIME_OUT = 3000;
    ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectallq);
        searchView = (SearchView) findViewById(R.id.search);

        lv1 = (ListView) findViewById(R.id.list);
        // rlv = (ListView) findViewById(R.id.list);

        conNames = new ArrayList<String>();
        conNumbers = new ArrayList<String>();
        //  new Wait().execute();

        //bb = (Button) findViewById(R.id.bb);


        //   m_text_event.setText(l_displayText.toString());


        // setListAdapter(new MyAdapter(this, android.R.layout.simple_list_item_1,
        // R.id.tvNameMain, conNames));

        //  lv1.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        tool = (Toolbar) findViewById(R.id.tool_bar);
        if (tool != null) {
            setSupportActionBar(tool);
            // tool.setNavigationIcon(R.drawable.navicon);
            // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                    //    new Wait().execute();
                        SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);

                        SharedPreferences.Editor editorr = shared1.edit();

                        editorr.clear();
                        editorr.commit();
                        progressBar = (ProgressBar) findViewById(R.id.pBar1);
                        progressBar.setProgress(0);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                            @Override
                            public void run() {
                                Intent ii1 = new Intent(Selectallq.this, Share_main.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);

                        break;
                    case 1:
                        //    new Wait().execute();

                        progressBar = (ProgressBar) findViewById(R.id.pBar1);
                        progressBar.setProgress(0);
                        progressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                            @Override
                            public void run() {
                                Intent ii1 = new Intent(Selectallq.this, Share_main1.class);
                                //statusCheck();
                                startActivity(ii1);
                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);

                        break;
                    case 2:
                        Intent iw1 = new Intent(Selectallq.this, Request_main.class);
                        startActivity(iw1);
                        finish();
                        break;

                    case 3:


                        Intent i22 = new Intent(Selectallq.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;


                    case 4:
                        Intent i222 = new Intent(Selectallq.this, Calendar_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i222);
                        finish();
                        break;
                    case 5:
                        Intent ih = new Intent(Selectallq.this, HistoryActivity.class);
                        startActivity(ih);
                        finish();
                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                       Intent ii = new Intent(Selectallq.this, Setting_main.class);
                        startActivity(ii);
                        finish();
                        break;
                }


            }
        });
        searchView.setIconifiedByDefault(false);
        searchView.setOnCloseListener(this);
        searchView.setOnQueryTextListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //   boolean hasDrawable = (img_main.getDrawable() != null);
        //  if(hasDrawable) {
        name = sharedPreferences.getString("name", "");
        imagePreferance = sharedPreferences.getString("imagePreferance", "");

        try {
            crContacts = ContactHelper.getContactCursor(getContentResolver(), "");
            crContacts.moveToFirst();

            while (crContacts.moveToNext()) {
                conNames.add(crContacts.getString(1));
                conNumbers.add(crContacts.getString(2));
                crContacts.moveToNext();
            }
            crContacts.close();

           // name1 = conNames.toArray(new String[conNames.size()]);
            OtherName1 = conNumbers.toArray(new String[conNumbers.size()]);
            name1=new String[OtherName1.length];
            countrylist1 = new ArrayList<DataModel1>();
            for (int i = 0; i < OtherName1.length; i++) {
                name1[i]=getname(getApplicationContext(),OtherName1[i]);
                DataModel1 country = new DataModel1(name1[i], OtherName1[i]);
                countrylist1.add(country);
            }
            custom1 = new Adapterrq(getApplicationContext(), countrylist1);







            //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);


        } catch (Exception e) {
            e.printStackTrace();
        }
        lv1.setAdapter(custom1);
        lv1.setOnItemClickListener(new DrawerItemClickListener());

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

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(final AdapterView parent, View view, int position, long id) {
            TextView t=(TextView)view.findViewById(R.id.tvNameMain);
            TextView ttt=(TextView)view.findViewById(R.id.tvNumberMain);
            final String name=t.getText().toString();
            final String number=ttt.getText().toString();
            AlertDialog.Builder alert = new AlertDialog.Builder(Selectallq.this);
            String s="Do you want to request to "+ name +" know his/her location";
            alert.setTitle("Confirm?");
            alert.setMessage(s);

// Set an EditText view to get user input
            // final EditText input = new EditText(context);
            //  input.setText("hiii");
            // alert.setView(input);

            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    String message = "I request you to share your location,Tap on this URL";
                    sendSMS(number, message);
                    Toast.makeText(Selectallq.this, "Your request sent to " + name, Toast.LENGTH_SHORT).show();

                    // Do something with value!
                    //textview.setText(input.getText().toString());
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();
        }
        // if(((TextView)view).getText().equals("Cosmetics"))
        // {
        // Toast.makeText(Request_main.this, tt, Toast.LENGTH_LONG).show();
        //  }
        //Toast.makeText(MainActivity.this, ((TextView) view).getText(), Toast.LENGTH_LONG).show();
        //  drawerLayout.closeDrawer(drawerListView);

    }

    private void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(getApplicationContext(), 0,
                new Intent(DELIVERED), 0);



        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }

 /*   protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.

        mToggle.syncState();

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
    public boolean onQueryTextChange(String newText) {
        lv1.setVisibility(View.VISIBLE);

        if (TextUtils.isEmpty(newText)) {
            lv1.clearTextFilter();
            custom1.getFilter().filter("");

        } else {
            // lv.setFilterText(newText.toString());
            custom1.getFilter().filter(newText);
            //      getFilterableArrayAdapter().getFilter().filter(prefix);

        }
        return true;
        //adapter.getFilter().filter(newText);
        //showResults(newText + "*");
        //return true;
    }

    public boolean onQueryTextSubmit(String query) {
        //showResults(query + "*");
        return true;
    }

    public boolean onClose() {
        //showResults("");

        // lv.setAdapter(customAdapter);
        //  searchView.setQuery(query, true);
        return true;
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
        Intent i = new Intent(this, Request_main.class);
        startActivity(i);
        finish();
    }



}