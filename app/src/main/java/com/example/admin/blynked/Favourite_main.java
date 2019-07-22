package com.example.admin.blynked;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;


public class Favourite_main extends ActionBarActivity {
    Toolbar tool;
    LinearLayout lll;
    ArrayList<DataModel> countrylist;
    String[] name_list;
    int[] img_list;
    public static final String STORAGE1 = "SenddataPreferences";
    private Marker customMarker;
    CustomAdapter cAdapter;
    ListView lv;
    String imagePreferance = null;
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
    GridLayout glayout;
    private List<View> mViews;
    int id1;
    String name,rcp,timee;
    int i;
    String p,idd,destt,msgg,crnt;
    int data,c;
    Globals g;
    ProgressDialog pDialog;
    int flag;
    int favflag;
    Button add;
    SqliteHelper sqliteHelper;
    private static int SPLASH_TIME_OUT = 3000;
    CircleProgressBar progressBar;
    ProgressBar progressBar1;
    private static final CharSequence[] setting_options =
            {"Delete","Rename", "Create Shortcut"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_main);
        tool = (Toolbar) findViewById(R.id.tool_bar);
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
//            RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    case 0:
                       // new Wait().execute();
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
                                Intent i = new Intent(Favourite_main.this, Share_main.class);
                                startActivity(i);

                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 1:
                        // new Wait().execute();

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
                                Intent i = new Intent(Favourite_main.this, Share_main1.class);
                                startActivity(i);

                                finish();
                                // close this activity

                            }
                        }, SPLASH_TIME_OUT);
                        break;
                    case 2:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        Intent i2 = new Intent(Favourite_main.this, Request_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i2);
                        finish();
                        break;

                    case 3:

                        Intent i22 = new Intent(Favourite_main.this, Favourite_main.class);
                        // i2.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        //  i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i22);
                        finish();
                        break;
                    case 4:
                        Intent iii = new Intent(Favourite_main.this,Calendar_main.class);
                        startActivity(iii);
                        finish();
                        break;
                    case 5:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                        Intent ii = new Intent(Favourite_main.this,HistoryActivity.class);
                        startActivity(ii);
                        finish();

                        break;
                    case 6:
                        //   RelativeLayout r1=(RelativeLayout)findViewById(R.id.r1);
                       Intent ipi = new Intent(Favourite_main.this,Setting_main.class);
                        startActivity(ipi);
                        finish();
                        break;

                }


            }
        });

        glayout = (GridLayout)findViewById(R.id.grid_view);
        //   g = (Globals) getActivity().getApplication();
        sqliteHelper = new SqliteHelper(Favourite_main.this);
        g = (Globals) getApplication();
        favflag = g.getfavflag();
        int p1=sqliteHelper.dbSyncCount();
        int x=p1;
        add=(Button)findViewById(R.id.add);


            while (p1 > 0) {
                final int finalP = x - p1 + 1;
                p = Integer.toString(finalP);
                Cursor cursor = sqliteHelper.getUser(p);
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    idd = cursor.getString(cursor.getColumnIndex("id"));
                    // i = Integer.parseInt(idd);
                    destt = cursor.getString(cursor.getColumnIndex("desti"));
                    msgg = cursor.getString(cursor.getColumnIndex("msg"));
                    flag = cursor.getInt(cursor.getColumnIndex("flag"));
                    name = cursor.getString(cursor.getColumnIndex("name"));
                    rcp = cursor.getString(cursor.getColumnIndex("rcp"));
                    timee = cursor.getString(cursor.getColumnIndex("time"));
                    //Toast.makeText(Favourite_main.this, "flag=="+flag, Toast.LENGTH_LONG).show();
                }
                // p=Integer.toString(p1);

                // RelativeLayout r2=(RelativeLayout)view.findViewById(R.id.rr);
                //r2.setVisibility(View.VISIBLE);
                if (flag == 1) {
                    // p=Integer.toString(p1);

                    // RelativeLayout r2=(RelativeLayout)view.findViewById(R.id.rr);
                    //r2.setVisibility(View.VISIBLE);
                    final RelativeLayout relativeLayout = new RelativeLayout(Favourite_main.this);
                    relativeLayout.setPadding(5, 10, 5, 10);
                    // relativeLayout.setId(c1);
                    final ImageView image = new ImageView(Favourite_main.this);

                    final Button button = new Button(Favourite_main.this);
                    image.setImageResource(R.drawable.icn_user_1_white);
                    image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    image.setPadding(10, 10, 10, 10);
                    image.setId(View.generateViewId());
                    relativeLayout.setId(x - p1 + 1);
                    image.setLayoutParams(new GridView.LayoutParams(200, 200));
                    button.setBackgroundResource(R.drawable.icn_settings_orange);
                    // image.setMaxHeight(10);
                    //image.setMaxWidth(10);
                    // image.setLayoutParams(new android.view.ViewGroup.LayoutParams(150,200));
                    button.setLayoutParams(new ViewGroup.LayoutParams(60, 60));
                    final TextView textview = new TextView(Favourite_main.this);
                    final RelativeLayout.LayoutParams lpTextView = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);
                    lpTextView.addRule(RelativeLayout.BELOW, image.getId());
                    lpTextView.addRule(RelativeLayout.CENTER_IN_PARENT);
                    //Setting the parameters on the TextView
                    textview.setLayoutParams(lpTextView);
                    textview.setText(name);
                    relativeLayout.addView(image);
                    relativeLayout.addView(button);
                    relativeLayout.addView(textview);
                    //mViews.add(relativeLayout);
                    glayout.addView(relativeLayout);

                    // final int finalP = x-p1+1;
                    final String c = Integer.toString(finalP);

                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (relativeLayout.getId() == finalP) {
                                p = Integer.toString(finalP);
                                Cursor cursor = sqliteHelper.getUser(p);
                                if (cursor.getCount() != 0) {
                                    cursor.moveToFirst();
                                    idd = cursor.getString(cursor.getColumnIndex("id"));
                                    // i = Integer.parseInt(idd);
                                    destt = cursor.getString(cursor.getColumnIndex("desti"));
                                    msgg = cursor.getString(cursor.getColumnIndex("msg"));
                                    flag = cursor.getInt(cursor.getColumnIndex("flag"));
                                    timee = cursor.getString(cursor.getColumnIndex("time"));
                                    rcp = cursor.getString(cursor.getColumnIndex("rcp"));
                                    crnt = cursor.getString(cursor.getColumnIndex("crnt"));
                                }
                                // zoomImageFromThumb(image, selectedImage);
                                // Toast.makeText(getActivity(), "" + idd + destt + msgg + flag, Toast.LENGTH_LONG).show();
                                SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared1.edit();
                                editor.putString("destination", destt);
                                editor.putString("Msg",msgg);
                                editor.putString("duration",timee);
                                editor.putString("rcp",rcp);
                                editor.putString("Current",crnt);
                                editor.apply();
                                Intent i11 = new Intent(Favourite_main.this, Share_main.class);
                                i11.putExtra("cactivity1", 2);

                                //i11.putExtra("name",name);
                                //statusCheck();
                                startActivity(i11);
                            }
                        }
                    });
                    button.setOnClickListener(new View.OnClickListener() {

                                                  @Override
                                                  public void onClick(View view) {
                                                      final String fDialogTitle = "Setting options";
                                                      AlertDialog.Builder builder = new AlertDialog.Builder(Favourite_main.this);
                                                      builder.setTitle(fDialogTitle);

                                                      // Find the current map type to pre-check the item representing the current state.
                                                      // int checkItem = googleMap.getMapType() - 1;

                                                      builder.setItems(setting_options, new DialogInterface.OnClickListener() {
                                                          public void onClick(DialogInterface dialog, int item) {
                                                              // Do something with the selection
                                                              switch (item) {
                                                                  case 0:
                                                                      if (relativeLayout.getId() == finalP) {
                                                                          p = Integer.toString(finalP);
                                                                          Cursor cursor = sqliteHelper.getUser(p);
                                                                          if (cursor.getCount() != 0) {
                                                                              cursor.moveToFirst();
                                                                              idd = cursor.getString(cursor.getColumnIndex("id"));
                                                                              // i = Integer.parseInt(idd);
                                                                              destt = cursor.getString(cursor.getColumnIndex("desti"));
                                                                              msgg = cursor.getString(cursor.getColumnIndex("msg"));
                                                                              flag = cursor.getInt(cursor.getColumnIndex("flag"));

                                                                              sqliteHelper.update(idd, 0);
                                                                          }
                                                                          // zoomImageFromThumb(image, selectedImage);

                                                                          glayout.removeView(relativeLayout);
                                                                          Toast.makeText(Favourite_main.this, "deleted", Toast.LENGTH_LONG).show();
                                                                      }
                                                                      break;
                                                                  case 1:
                                                                      if (relativeLayout.getId() == finalP) {
                                                                          p = Integer.toString(finalP);
                                                                          Cursor cursor = sqliteHelper.getUser(p);
                                                                          if (cursor.getCount() != 0) {
                                                                              cursor.moveToFirst();
                                                                              idd = cursor.getString(cursor.getColumnIndex("id"));
                                                                              // i = Integer.parseInt(idd);
                                                                              destt = cursor.getString(cursor.getColumnIndex("desti"));
                                                                              msgg = cursor.getString(cursor.getColumnIndex("msg"));
                                                                              flag = cursor.getInt(cursor.getColumnIndex("flag"));
                                                                              name = cursor.getString(cursor.getColumnIndex("name"));
                                                                              //  sqliteHelper.update(idd, 0);
                                                                          }


                                                                          AlertDialog.Builder alert = new AlertDialog.Builder(Favourite_main.this);

                                                                          alert.setTitle("Set Your favourite Name");
                                                                          //  alert.setMessage("Message");

// Set an EditText view to get user input
                                                                          final EditText input = new EditText(Favourite_main.this);
                                                                          input.setText(name);
                                                                          alert.setView(input);

                                                                          alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                              public void onClick(DialogInterface dialog, int whichButton) {
                                              /*  Intent intent = new Intent();
                                                intent.putExtra("dest", desti);
                                                intent.putExtra("msg", selmsg1);
                                                intent.putExtra("time", seltime1);
                                                intent.putExtra("c", d);
                                                intent.putExtra("name",input.getText().toString());
                                                setResult(2, intent);

                                                finish();*/
                                                                                  // Do something with value!
                                                                                  textview.setText(input.getText().toString());
                                                                                  sqliteHelper.update1(idd, input.getText().toString());
                                                                              }
                                                                          });

                                                                          alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                                              public void onClick(DialogInterface dialog, int whichButton) {
                                                                                  // Canceled.
                                                                              }
                                                                          });

                                                                          alert.show();
                                                                      }
                                                                      break;
                                                                  case 2:
                                                                      //   googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                                                      //  g.setFlag(4);\


                                                                      if (relativeLayout.getId() == finalP) {
                                                                          p = Integer.toString(finalP);
                                                                          Cursor cursor = sqliteHelper.getUser(p);
                                                                          if (cursor.getCount() != 0) {
                                                                              cursor.moveToFirst();
                                                                              idd = cursor.getString(cursor.getColumnIndex("id"));
                                                                              // i = Integer.parseInt(idd);
                                                                              destt = cursor.getString(cursor.getColumnIndex("desti"));
                                                                              msgg = cursor.getString(cursor.getColumnIndex("msg"));
                                                                              flag = cursor.getInt(cursor.getColumnIndex("flag"));
                                                                              name = cursor.getString(cursor.getColumnIndex("name"));
                                                                            timee = cursor.getString(cursor.getColumnIndex("time"));
                                                                             rcp = cursor.getString(cursor.getColumnIndex("rcp"));
                                                                              crnt = cursor.getString(cursor.getColumnIndex("crnt"));
                                                                              //  sqliteHelper.update(idd, 0);
                                                                          }
                                                                          AlertDialog.Builder alert = new AlertDialog.Builder(Favourite_main.this);

                                                                          alert.setTitle("Set Your shortcut name");
                                                                          //  alert.setMessage("Message");

// Set an EditText view to get user input
                                                                          final EditText input = new EditText(Favourite_main.this);
                                                                          alert.setView(input);


                                                                          alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                              public void onClick(DialogInterface dialog, int whichButton) {

                                                                                  // Do something with value!
                                                                                  name = input.getText().toString();
                                                                                  Intent shortcutIntent = new Intent(Favourite_main.this,
                                                                                          Share_main.class);
                                                                                  shortcutIntent.putExtra("cactivity1", 2);
                                                                                  SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                                                                                  SharedPreferences.Editor editor = shared1.edit();
                                                                                  editor.putString("destination", destt);
                                                                                  editor.putString("Msg",msgg);
                                                                                  editor.putString("duration",timee);
                                                                                  editor.putString("rcp",rcp);
                                                                                  editor.putString("Current",crnt);
                                                                                  editor.apply();

                                                                                  shortcutIntent.setAction(Intent.ACTION_MAIN);

                                                                                  Intent addIntent = new Intent();
                                                                                  addIntent
                                                                                          .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                                                                                  addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
                                                                                  addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                                                                                          Intent.ShortcutIconResource.fromContext(Favourite_main.this,
                                                                                                  R.mipmap.bg_logo));

                                                                                  addIntent
                                                                                          .setAction("com.android.launcher.action.INSTALL_SHORTCUT")                                                                                  ;

                                                                                  Favourite_main.this.sendBroadcast(addIntent);
                                                                              }
                                                                          });

                                                                          alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                                              public void onClick(DialogInterface dialog, int whichButton) {
                                                                                  // Canceled.
                                                                                  Intent shortcutIntent = new Intent(Favourite_main.this,
                                                                                          Share_main.class);
                                                                                  shortcutIntent.putExtra("cactivity1", 2);
                                                                                  SharedPreferences shared1 = getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                                                                                  SharedPreferences.Editor editor = shared1.edit();
                                                                                  editor.putString("destination", destt);
                                                                                  editor.putString("Msg",msgg);
                                                                                  editor.putString("duration",timee);
                                                                                  editor.putString("rcp",rcp);
                                                                                  editor.putString("Current",crnt);
                                                                                  editor.apply();
                                                                                  shortcutIntent.setAction(Intent.ACTION_MAIN);

                                                                                  Intent addIntent = new Intent();
                                                                                  addIntent
                                                                                          .putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                                                                                  addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
                                                                                  addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                                                                                          Intent.ShortcutIconResource.fromContext(Favourite_main.this,
                                                                                                  R.mipmap.bg_logo));

                                                                                  addIntent
                                                                                          .setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");

                                                                                  Favourite_main.this.sendBroadcast(addIntent);
                                                                              }
                                                                          });

                                                                          alert.show();

                                                                      }
                                                                      break;
                                                                  default:
                                                                      //  googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                                                      //  g.setFlag(1);
                                                              }
                                                              dialog.dismiss();
                                                          }
                                                      });
                                                      AlertDialog alert = builder.create();
                                                      alert.setCanceledOnTouchOutside(true);
                                                      alert.show();

                                                  }
                                              }
                    );




                } else {
                   // Toast.makeText(Favourite_main.this, "" + idd + flag, Toast.LENGTH_LONG).show();
                }
                p1--;
            }


        g.setfavflag(0);


        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                g.setfavflag(1);
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
                        Intent i = new Intent(Favourite_main.this, Share_main.class);

                        startActivity(i);

                        finish();
                        // close this activity

                    }
                }, SPLASH_TIME_OUT);
                // getActivity().finish();


            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favourite_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                Intent i = new Intent(Favourite_main.this, MainActivity.class);
                startActivity(i);

                finish();
                // close this activity

            }
        }, SPLASH_TIME_OUT);

    }

}
