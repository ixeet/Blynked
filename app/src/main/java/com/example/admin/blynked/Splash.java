package com.example.admin.blynked;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Splash extends Activity {
    Context mContext = Splash.this;
    AccountManager mAccountManager;
    String token;
    int serverCode;
    String textName;
    Boolean verified;
    String name,imagePreferance;
    private static final String SCOPE = "oauth2:https://www.googleapis.com/auth/userinfo.profile";
    public static final String STORAGE_IMAGE = "Image";
    public static final String VERIFY = "verify";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences s1= getSharedPreferences(VERIFY, Context.MODE_PRIVATE);
        verified = s1.getBoolean("verified", false);
      //  syncGoogleAccount();
       new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                if(verified==true) {
    Intent i = new Intent(Splash.this, MainActivity.class);
    startActivity(i);
    finish();
}

                else
{
    Intent i = new Intent(Splash.this, VerificationActivity.class);
    startActivity(i);
    finish();
}
             /*   Intent i = new Intent(Splash.this, VerificationActivity.class);
                startActivity(i);
                finish();*/
            }
        },1000);

       // textEmail = intent.getStringExtra("email_id");



    }


    public boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.e("Network Testing", "***Available***");
            return true;
        }
        Log.e("Network Testing", "***Not Available***");
        return false;
    }




}