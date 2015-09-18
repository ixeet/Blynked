package com.example.admin.blynked;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Filter;
import android.widget.Filterable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27-07-2015.
 */
public class EventAdapter1q extends ArrayAdapter<Eventdata1q> {

    Context context;
    List<Eventdata1q> stock;
    ValueFilter valueFilter;
    public EventAdapter1q(Context cxt, List<Eventdata1q> contactstock) {
        super(cxt,R.layout.listttq,contactstock);
        this.context=cxt;
        this.stock=contactstock;

    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        View rowView = convertView;
        ContactStockView sv = null;
        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.listttq, parent, false);
            rowView = inflater.inflate(
                    R.layout.listttq, null);

            // Hold the view objects in an object,
            // so they don't need to be re-fetched
            sv = new ContactStockView();
            sv.name = (TextView) rowView.findViewById(R.id.contact_name_log);
            sv.number = (TextView) rowView.findViewById(R.id.contact_number_log);
            sv.ii= (ImageView) rowView.findViewById(R.id.imageView11);

            rowView.setTag(sv);
            Eventdata1q currentStock = (Eventdata1q) stock.get(position);
            sv.name.setText(currentStock.getName());
            sv.number.setText(currentStock.getNumber());
        } else {
            sv = (ContactStockView) rowView.getTag();
        }
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(sv.number.getText().toString()));

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

        sv.ii.setImageBitmap(photo);
        // Transfer the stock data from the data object
        // to the view objects





        // TODO Auto-generated method stub
        final ContactStockView finalSv = sv;
        final ContactStockView finalSv1 = sv;

        return rowView;
    }

    protected static class ContactStockView {
        protected TextView name;
        protected TextView number;

        protected ImageView ii;
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
                ArrayList<Eventdata1q> filterList = new ArrayList<Eventdata1q>();

                for (int i = 0; i < stock.size(); i++) {
                    if ( (stock.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        Eventdata1q country = new Eventdata1q(stock.get(i)
                                .getName() ,  stock.get(i)
                                .getNumber());

                        filterList.add(country);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = stock.size();
                results.values = stock;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            ArrayList<Eventdata1q> countrylist = (ArrayList<Eventdata1q>) results.values;
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


