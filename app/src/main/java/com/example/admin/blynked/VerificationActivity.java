package com.example.admin.blynked;



import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class VerificationActivity extends Activity {
   ImageView btnSendSMS;
    EditText OtpNumber;
    ImageView v1;
    private ProgressDialog pDialog;
    CountDownTimer resultTimer;
    public static final String STORAGE_NAME = "MySharedPreferencess";
    BroadcastReceiver sendBroadcastReceiver;
    BroadcastReceiver deliveryBroadcastReceiver;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    String flag;
    CircleProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        OtpNumber= (EditText) findViewById(R.id.txtName);
        //    m=getIntent().getStringExtra("message");
        btnSendSMS = (ImageView) findViewById(R.id.btnSendSMS);
        //  OtpNumber.setText(message);
       v1=(ImageView)findViewById(R.id.v);
         SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
         flag = sharedPreferences.getString("flag", "");

        btnSendSMS.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                Intent i=new Intent(VerificationActivity.this,Otp.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

              /* String phoneNo = OtpNumber.getText().toString();


                if (v1.getTag() == null) {
                   // Toast.makeText(getApplicationContext(),
                          //  "" + v1.getTag(),
                         //   Toast.LENGTH_LONG).show();
                    String message = "Verification done successfully";
                    // String phoneNo = txtPhoneNo.getText().toString();
                    // String message = txtMessage.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    // editor.putString("message",messageReceived);
                    editor.putString("flag", "");
                    editor.putString("phoneNo", phoneNo);
                    editor.apply();
                    String regexStr = "[0-9]+";
                    if ((phoneNo.matches(regexStr))&& (phoneNo.length()==10) )
                      //  if(phoneNo.length()==10) {
                            sendSMS(phoneNo, message);
                     //   }

                    else
                        Toast.makeText(getBaseContext(),
                                "Please enter 10 digit valid phone number for verification",
                                Toast.LENGTH_LONG).show();


                }
                else if((int)v1.getTag()== R.drawable.yes_converted)
                {
                    Toast.makeText(getBaseContext(),
                            "Your Number is verified Successfully",
                            Toast.LENGTH_LONG).show();
                    Intent i=new Intent(VerificationActivity.this,Otp.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                }
                else if((int)v1.getTag()== R.drawable.no_converted)
                {
                    Toast.makeText(getBaseContext(),
                            "Your Number is not verified.Please try again",
                            Toast.LENGTH_LONG).show();
                    v1.setTag(null);
                    v1.setVisibility(View.INVISIBLE);
                }*/
            }
        });
        /*sendBroadcastReceiver = new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        //Toast.makeText(getBaseContext(), "SMS sent",
                        //     Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        // Toast.makeText(getBaseContext(), "Generic failure",
                        //       Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        //Toast.makeText(getBaseContext(), "No service",
                        //       Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        //  Toast.makeText(getBaseContext(), "Null PDU",
                        //        Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // Toast.makeText(getBaseContext(), "Radio off",
                        //        Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        registerReceiver(sendBroadcastReceiver , new IntentFilter(SENT));*/

    }
    private void sendSMS(String phoneNumber, String message)
    {

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---


        //---when the SMS has been delivered---
        //   registerReceiver(new BroadcastReceiver(){
        //     @Override
        //   public void onReceive(Context arg0, Intent arg1) {
        //     switch (getResultCode())
        //   {
        //     case Activity.RESULT_OK:
        //       Toast.makeText(getBaseContext(), "SMS delivered",
        //             Toast.LENGTH_SHORT).show();
        //    break;
        //case Activity.RESULT_CANCELED:
        //  Toast.makeText(getBaseContext(), "SMS not delivered",
        //        Toast.LENGTH_SHORT).show();
        // break;
        // }
        //  }
        // }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
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
                //  registerInBackground(n,e);
                // close this activity
                progressBar.setVisibility(View.INVISIBLE);
                SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
                // RelativeLayout rl=(RelativeLayout)findViewById(R.id.done);
                //  phoneNo = sharedPreferences.getString("phoneNo", "");
                String flag = sharedPreferences.getString("flag", "");
                if(flag.equals("yes")) {
                    v1.setVisibility(View.VISIBLE);
                    v1.setImageResource(R.drawable.yes_converted);
                    v1.setTag(R.drawable.yes_converted);
                }
                else
                {
                    v1.setVisibility(View.VISIBLE);
                    v1.setImageResource(R.drawable.no_converted);
                    v1.setTag(R.drawable.no_converted);
                }

            }
        }, 20000);




    }

    /*class AttemptVerify extends AsyncTask<String, String, String> {

        *//**
         * Before starting background thread Show Progress Dialog
         *//*
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            pDialog = new ProgressDialog(VerificationActivity.this);
            pDialog.setMessage("Number Verifying...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }
        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag



                // Building Parameters



            return null;

        }

        *//**
         * After completing background task Dismiss the progress dialog
         * *
         *//*
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            pDialog.dismiss();


                SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
               // RelativeLayout rl=(RelativeLayout)findViewById(R.id.done);
              //  phoneNo = sharedPreferences.getString("phoneNo", "");
                String flag = sharedPreferences.getString("flag", "");
                if(flag.equals("yes")) {
                v1.setVisibility(View.VISIBLE);
                v1.setImageResource(R.drawable.yes_converted);
                    v1.setTag(R.drawable.yes_converted);
                }
                else
                {
                v1.setVisibility(View.VISIBLE);
                v1.setImageResource(R.drawable.no_converted);
                    v1.setTag(R.drawable.no_converted);
                }



        }
    }
*/
}
