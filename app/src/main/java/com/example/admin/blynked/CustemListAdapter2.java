package com.example.admin.blynked;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by Admin on 10-08-2015.
 */
public class CustemListAdapter2 extends BaseAdapter {
    Context context;
    ArrayList<DataModelsetting1> genList;
    ToggleButton swi;
    PowerManager pm;
    PowerManager.WakeLock wakeLock;
    int check;
    Setting_main s;
    Globals g;
    int b,power,exp;
    public CustemListAdapter2(Setting_main s,Context context, ArrayList<DataModelsetting1> genList) {
        this.context=context;
        this.genList=genList;
        this.s=s;
        g = (Globals)s.getApplication();

    }

    @Override
    public int getCount() {
        return genList.size();
    }

    @Override
    public Object getItem(int i) {
        return genList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater.from(viewGroup.getContext()));
        view=inflater.inflate(R.layout.list_groupgen,viewGroup,false);
        pm = (PowerManager) this.context.getSystemService(Context.POWER_SERVICE);
        final DataModelsetting1 dataModel1 = genList.get(i);

        TextView title=(TextView)view.findViewById(R.id.titleTextgen);

        swi=(ToggleButton)view.findViewById(R.id.switch1);

        title.setText(dataModel1.getTitle());

        if (i==0) {
            if(g.getcheck()==1) {
                boolean mBool = true;
                swi.setChecked(mBool);
            }
            else
            {
                swi.setChecked(false);
            }
            swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                    if (isChecked) {
                        swi.setGravity(Gravity.LEFT);
                        swi.setPadding(5,0,0,0);
                        g.setcheck(1);
                       // Toast.makeText(context, ""+g.getcheck(), Toast.LENGTH_LONG).show();
                    /*    if(context instanceof Share_main){
                            ((Share_main)context).setEnableshare();*/
                    //    }
                    } else {
                        swi.setGravity(Gravity.RIGHT);
                        swi.setPadding(0,0,5,0);
                        g.setcheck(0);
                      //  Toast.makeText(context, ""+g.getcheck(), Toast.LENGTH_LONG).show();
                        //Toast.makeText(context, "not share my location", Toast.LENGTH_LONG).show();
                        /*if(context instanceof Share_main){
                            ((Share_main)context).setDisableshare();
                        }*/
                    }


                }
            });
        }
        else if (i==1) {
            if(g.getb()==1) {
                boolean mBool = true;
                swi.setChecked(mBool);
            }
            else
            {
                swi.setChecked(false);
            }
            swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                       /* PackageManager pm  = context.getPackageManager();
                        ComponentName componentName = new ComponentName(context, name_of_your_receiver.class);
                        pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                                PackageManager.DONT_KILL_APP);
                        Toast.makeText(context, "activated", Toast.LENGTH_LONG).show();*/
                        Toast.makeText(context,"broadcast my speed",Toast.LENGTH_LONG).show();
                        g.setb(1);

                    }
                    else {
                        /*PackageManager pm  = Re_editActivity.this.getPackageManager();
                        ComponentName componentName = new ComponentName(currentActivity.this, name_of_your_receiver.class);
                        pm.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                                PackageManager.DONT_KILL_APP);
                        Toast.makeText(getApplicationContext(), "cancelled", Toast.LENGTH_LONG).show();*/
                        Toast.makeText(context,"not broadcast my speed",Toast.LENGTH_LONG).show();
                        g.setb(0);

                    }
                }
            });
        }
        else if (i==2) {
            if(g.getexp()==1) {
                boolean mBool = true;
                swi.setChecked(mBool);
            }
            else
            {
                swi.setChecked(false);
            }
            swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        Toast.makeText(context,"arrival expire",Toast.LENGTH_LONG).show();
                        g.setexp(1);
                    }
                    else {
                        Toast.makeText(context,"not arrival expire",Toast.LENGTH_LONG).show();
                        g.setexp(0);
                    }
                }
            });
        }
        /*else if (i==3) {
            swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        Toast.makeText(context,"arrival expire",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(context,"not arrival expire",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }*/
        else
        if(i == 3) {
            if(g.getpower()==1) {
                boolean mBool = true;
                swi.setChecked(mBool);
                wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
                        "My wakelook");
                wakeLock.acquire();
            }
            else
            {
                swi.setChecked(false);
            }

            swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        //Toast.makeText(context,"Low power mode",Toast.LENGTH_LONG).show();


                        wakeLock.acquire();
                        Toast acquire = Toast.makeText(context, "Wake Lock ON",
                                Toast.LENGTH_SHORT);
                        acquire.show();
                        g.setpower(1);
                    } else

                    {
                        wakeLock.release();
                        Toast release = Toast.makeText(context, "Wake Lock OFF", Toast.LENGTH_SHORT);
                        release.show();
                        g.setpower(0);
                    }
                }


            });
        }

        return view;
    }
}
