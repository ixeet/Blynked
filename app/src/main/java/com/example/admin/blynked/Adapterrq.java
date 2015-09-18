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

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Admin on 01-07-2015.
 */
public class Adapterrq extends BaseAdapter implements Filterable {

    private static final String TAG = Adapterr.class.getSimpleName();
    ArrayList<DataModel1> listArray=null;
    String userid;
    ImageView ii;
    String no;
    Context context;
    ProgressDialog pDialog;
    ArrayList<DataModel1> countrylist;
    ArrayList<DataModel1> mmStringFilterList = new ArrayList<DataModel1>();
    ArrayList<DataModel1> mStringFilterList;
    ValueFilter valueFilter;
    int flag;
    SparseBooleanArray mSparseBooleanArray;


    public Adapterrq(Context context, ArrayList<DataModel1> countrylist) {
        this.context = context;
        this.countrylist = countrylist;
        this.mStringFilterList = countrylist;
        mSparseBooleanArray = new SparseBooleanArray();
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


    public class ViewHolder{
        TextView textView;
        TextView textView1;
        ImageView ii;

    }
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View vi=convertView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        vi = inflater.inflate (R.layout.liststyleq, parent,false);
        ViewHolder item=new ViewHolder();
        final DataModel1 dataModel = countrylist.get(position);
        no=dataModel.getOtherData();
        item.textView = (TextView)vi.findViewById(R.id.tvNameMain);
        item.textView.setText(dataModel.getName());
        item.textView1 = (TextView)vi.findViewById(R.id.tvNumberMain);
        item.textView1.setText(no);
        item.ii= (ImageView) vi.findViewById(R.id.ii);

        vi.setTag(item);
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
                R.drawable.proff);

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
        item.ii.setImageBitmap(photo);
        //ii.setImageBitmap(dataModel.getphoto());
        // CheckBox mCheckBox = (CheckBox) view.findViewById(R.id.chkEnable);




        return vi;
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
                ArrayList<DataModel1> filterList = new ArrayList<DataModel1>();

                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ( (mStringFilterList.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        DataModel1 country = new DataModel1(mStringFilterList.get(i)
                                .getName() , mStringFilterList.get(i)
                                .getOtherData());

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
            countrylist = (ArrayList<DataModel1>) results.values;
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



