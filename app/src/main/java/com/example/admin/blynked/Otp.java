package com.example.admin.blynked;

import android.app.Activity;
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
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.gcm.GoogleCloudMessaging;

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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class Otp extends Activity {
    TextView OtpNumber1;
    String fileName;
    String r = "";
    String n,e;
    int serverResponseCode = 0;
    private final int SELECT_PHOTO = 22;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    String message,flag,phoneNo;
    public static final String STORAGE_IMAGE = "Image";
  ImageView button1;
    private static final String TAG_SUCCESS = "status";
    EditText name,email;
    String p="p";
    ProgressDialog prgDialog;

    String email1,name1;
    public static final String STORAGE_NAME = "MySharedPreferencess";
    public static final String VERIFY = "verify";
    private static final String VERIFY_URL ="http://191.239.57.54:8080/Blynk/uploadController";
    ProgressDialog pDialog;
    ImageView ii;
    String p11,device,version;
    Bitmap selectedImage;
    String imagePreferance;
    GoogleCloudMessaging gcmObj;
    JSONParser jsonParser = new JSONParser();
    int progressValue = 0;
    private static int SPLASH_TIME_OUT = 3000;
    CircleProgressBar progressBar;
    TelephonyManager mngr;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        OtpNumber1= (TextView) findViewById(R.id.txtName);
        button1=(ImageView)findViewById(R.id.button1);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        ii=(ImageView)findViewById(R.id.imag_icon);
        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        uid = mngr.getDeviceId();
      //  phoneNo="9998787999";
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        //RelativeLayout rl=(RelativeLayout)findViewById(R.id.done);
        phoneNo = sharedPreferences.getString("phoneNo", "");
        flag = sharedPreferences.getString("flag", "");
        device=Devices.getDeviceName();
        version = android.os.Build.VERSION.RELEASE;

    /* phoneNo="9068787995";   if(flag.equals("yes")) {
            OtpNumber1.setText("Verification Successful");
            button1.setText("Next");
            rl.setVisibility(View.VISIBLE);
        }
        else
        {
            OtpNumber1.setText("Failed,please try agin with valid phone Number");
            button1.setText("Previous");
            rl.setVisibility(View.INVISIBLE);
        }*/
        //Toast.makeText(Otp.this, message , Toast.LENGTH_SHORT).show();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Otp.this, MainActivity.class);
                //statusCheck();
                startActivity(i1);
                finish();
                n=name.getText().toString();
                e=email.getText().toString();
                progressBar = (CircleProgressBar) findViewById(R.id.progressWithoutBg);
               // Toast.makeText(Otp.this,p , Toast.LENGTH_SHORT).show();
               /* if(!n.equals("")&& (Utility.validate(e))&&(!p.equals("p"))) {
                    registerInBackground(n, e);
                    progressBar.setColorSchemeResources(android.R.color.holo_blue_light);
                    progressBar.setProgress(0);
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {



                        @Override
                        public void run() {

                            // close this activity






                        }
                    }, SPLASH_TIME_OUT);

                }
                else if(n.equals(""))
                {
                    Toast.makeText(Otp.this, "Please enter your name" , Toast.LENGTH_SHORT).show();
                }
                else if(e.equals(""))
                {
                    Toast.makeText(Otp.this, "Please enter your email" , Toast.LENGTH_SHORT).show();

                }
                else if(p.equals("p"))
                {
                    Toast.makeText(Otp.this, "Please upload image" , Toast.LENGTH_SHORT).show();

                }
                else
                    if(!Utility.validate(e))
                    {
                        Toast.makeText(Otp.this, "Please enter valid email ID" , Toast.LENGTH_SHORT).show();
                    }*/


//        progress1.setColorSchemeResources(android.R.color.holo_blue_bright);
                //progress2.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
                //progressWithArrow.setColorSchemeResources(android.R.color.holo_orange_light);





              // new Wait1().execute();
               /* if (button1.getText().toString().equals("Previous")) {
                    Intent i = new Intent(Otp.this, VerificationActivity.class);
                    startActivity(i);
                    finish();
                } else {
                 //  Toast.makeText(Otp.this, name.getText().toString()+email.getText().toString()+""+phoneNo+""+p, Toast.LENGTH_LONG).show();
                    //new AttemptLogin().execute();
                    new Wait().execute();


                }*/
            }
        });
        ii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFrom();
            }
        });
    }
    private void registerInBackground(final String emailid, final String username) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcmObj == null) {
                        gcmObj = GoogleCloudMessaging
                                .getInstance(getApplicationContext());
                    }
                    r = gcmObj
                            .register(ApplicationConstants.GOOGLE_PROJ_ID);
                    msg = "Registration ID :" + r;

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                if (!TextUtils.isEmpty(r)) {
                    //  mRegister = (Button) findViewById(R.id.register);
                    // mRegister.setEnabled(false);
                    storeRegIdinSharedPref(getApplicationContext(), r, e,n);
                   // Toast.makeText(Otp.this, r, Toast.LENGTH_LONG).show();
                    Log.d("GCM REg Id",r);

                    //Toast.makeText(
                    //      applicationContext,
                    //    "Registered with GCM Server successfully.\n\n"
                    //          + msg, Toast.LENGTH_SHORT).show();
                } else {
                    // Toast.makeText(
                    //       applicationContext,
                    //     "Reg ID Creation Failed.\n\nEither you haven't enabled Internet or GCM server is busy right now. Make sure you enabled Internet and try registering again after some time."
                    //           + msg, Toast.LENGTH_LONG).show();
                }
            }
        }.execute(null, null, null);
    }
    private void storeRegIdinSharedPref(Context context, String regid,
                                        String emailid,String username) {
        SharedPreferences prefs = getSharedPreferences("UserDetails",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("regid", regid);
        editor.putString("email", emailid);
        editor.commit();

            storeRegIdinServer(regid, emailid, username);



    }
    private void storeRegIdinServer(final String regid, String emailid,String username) {
        DefaultHttpClient client = new DefaultHttpClient();
        final File file1 = new File(p);

        final FileBody fileBody = new FileBody(file1);
        // Toast.makeText(Otp.this, name.getText().toString()+email.getText().toString()+""+phoneNo+""+p, Toast.LENGTH_LONG).show();
        // File file = new File(p, ContentType.DEFAULT_BINARY);
        HttpPost post = new HttpPost(VERIFY_URL);
        //  FileBody fileBody = new FileBody(file);
        StringBody stringBody1 = new StringBody(username, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody2 = new StringBody("+91"+phoneNo, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody3 = new StringBody(emailid, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody4 = new StringBody(device, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody6 = new StringBody(version, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody7 = new StringBody(uid, ContentType.MULTIPART_FORM_DATA);
        StringBody stringBody5 = new StringBody("Android", ContentType.MULTIPART_FORM_DATA);
       // String boundary = "-------------" + System.currentTimeMillis();
       // post.setHeader("Content-type", "multipart/form-data; boundary="+boundary);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
      //  builder.setBoundary(boundary);
        builder.addPart("file", fileBody);
        builder.addPart("username", stringBody1);
        builder.addPart("mobileNum", stringBody2);
        builder.addPart("emailId", stringBody3);
        builder.addPart("deviceName", stringBody4);
        builder.addPart("os", stringBody5);
        builder.addPart("softVersion", stringBody6);
        builder.addPart("imeiNo", stringBody7);
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
            if(!p11.equals("Failure")) {
                SharedPreferences s11= getSharedPreferences(STORAGE_IMAGE, Context.MODE_PRIVATE);
                // imagePreferance = s11.getString("imagePreferance", "");
//if(decodeBase64(imagePreferance)!=null)
                if(selectedImage!=null ) {

                    SharedPreferences.Editor editor = s11.edit();
                    //   editor.clear();
                    editor.putString("imagePreferance", encodeTobase64(selectedImage));
                    editor.putString("name", n);

                    editor.apply();
                    editor.commit();
                }
                // Toast.makeText(Otp.this,"name= "+n+"image= "+encodeTobase64(selectedImage) , Toast.LENGTH_SHORT).show();
                SharedPreferences s1= getSharedPreferences(VERIFY, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = s1.edit();
                //   editor.putString("message", messageReceived);
                editor.putBoolean("verified",true);
                editor.apply();
                Intent i1 = new Intent(Otp.this, MainActivity.class);
                //statusCheck();
                startActivity(i1);
                finish();
            }
            else
            {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Otp.this, "This number already exists in database", Toast.LENGTH_LONG).show();
            }

           Toast.makeText(Otp.this, "Sent "+p11, Toast.LENGTH_LONG).show();

        } catch (IOException ee) {
            ee.printStackTrace();
        }

                }
    class Wait1 extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            pDialog = new ProgressDialog(Otp.this);
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


                // Run your task here
               /* Intent i=new Intent(Tests.this,Otp.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();*/





            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted

            pDialog.dismiss();
            if (file_url != null) {

                Toast.makeText(Otp.this, file_url, Toast.LENGTH_LONG).show();

            }


        }
    }

  class Wait extends AsyncTask<String, String, String> {

      /**
       * Before starting background thread Show Progress Dialog
       */
      boolean failure = false;

      @Override
      protected void onPreExecute() {
          super.onPreExecute();


          pDialog = new ProgressDialog(Otp.this);
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


              // Run your task here
               /* Intent i=new Intent(Tests.this,Otp.class);
                // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();*/







          } catch (Exception e) {
              e.printStackTrace();
          }

          return null;

      }

      /**
       * After completing background task Dismiss the progress dialog
       * *
       */
      protected void onPostExecute(String file_url) {
          // dismiss the dialog once product deleted

          pDialog.dismiss();
          if (file_url != null) {

             // Toast.makeText(Otp.this, file_url, Toast.LENGTH_LONG).show();

          }


      }
  }
    private void getImageFrom() {

        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Otp.this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

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
                        ii.setImageBitmap(selectedImage);

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
                  //  Toast.makeText(Otp.this, p, Toast.LENGTH_LONG).show();
                    selectedImage = BitmapFactory.decodeFile(p);
                  ii.setImageBitmap(selectedImage);


                }
        }

    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
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

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

}
