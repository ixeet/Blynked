package com.example.admin.blynked;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.blynked.Eventdata;
import com.example.admin.blynked.Eventdata1;
import com.example.admin.blynked.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01-07-2015.
 */
public class EventAdapter1 extends BaseAdapter implements Filterable {

    //private static final String TAG = Adapterr.class.getSimpleName();
    ArrayList<Eventdata1> listArray=null;
    String userid;
    ImageView ii;
    String no;
    Context context;
    ProgressDialog pDialog;
    ArrayList<Eventdata1> countrylist;
    ArrayList<Eventdata1> mmStringFilterList = new ArrayList<Eventdata1>();
    ArrayList<Eventdata1> mStringFilterList;
    ValueFilter valueFilter;
    int flag;
    SparseBooleanArray mSparseBooleanArray;


    public EventAdapter1(Context context, ArrayList<Eventdata1> countrylist) {
        this.context = context;
        this.countrylist = countrylist;
        this.mStringFilterList = countrylist;
        mSparseBooleanArray = new SparseBooleanArray(countrylist.size());
    }


    @Override
    public int getCount() {
        return countrylist.size();    // total number of elements in the list
    }

    @Override
    public Object getItem(int i) {
        return countrylist.get(i);    // single item in the list
    }

    @Override
    public long getItemId(int i) {
        return i;                   // index number
    }

    @Override
    public View getView(int index, View view, final ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.listtt, parent, false);

        }

        final Eventdata1 dataModel = countrylist.get(index);

        final TextView textView = (TextView) view.findViewById(R.id.contact_name_log);
        textView.setText(dataModel.getName());
        final TextView textView1 = (TextView) view.findViewById(R.id.contact_number_log);
        no=dataModel.getNumber();
        textView1.setText(no);
        ii= (ImageView) view.findViewById(R.id.imageView11);
        new getc().execute();
        //  textView1.setText(dataModel.getOtherData());
        // Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.images);
        // i1 = (ImageView) view.findViewById(R.id.iv1);
        //ii.setImageBitmap(icon);
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(no));

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


            //adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, products);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ii.setImageBitmap(photo);
        //ii.setImageBitmap(dataModel.getphoto());
        CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.chkEnable);


        mCheckBox.setTag(index);


      //


        mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);
        mCheckBox.setChecked(mSparseBooleanArray.get(index));

        return view;
    }
    class getc extends AsyncTask<String, String, Bitmap> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        String p = "yess";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag






            return null;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * *
         */
        protected void onPostExecute(Bitmap photo) {



        }
    }
    CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {




        @Override


        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            // TODO Auto-generated method stub


            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);


        }


    };
    public ArrayList<Eventdata1> getCheckedItems() {


        ArrayList<Eventdata1> mTempArry = new ArrayList<Eventdata1>();




        for(int i=0;i<countrylist.size();i++) {


            if(mSparseBooleanArray.get(i)) {


                mTempArry.add(countrylist.get(i));


            }


        }




        return mTempArry;


    }
    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Eventdata1> filterList = new ArrayList<Eventdata1>();

                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ( (mStringFilterList.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        Eventdata1 country = new Eventdata1(mStringFilterList.get(i)
                                .getName() ,  mStringFilterList.get(i)
                                .getNumber());

                        filterList.add(country);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            countrylist = (ArrayList<Eventdata1>) results.values;
            notifyDataSetChanged();
        }

    }
    private void sendSMS(String phoneNumber, String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
                new Intent(DELIVERED), 0);



        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }
}


