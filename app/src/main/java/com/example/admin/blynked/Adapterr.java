package com.example.admin.blynked;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.blynked.DataModel1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import static java.util.Locale.*;

/**
 * Created by Admin on 25-08-2015.
 */
public class
        Adapterr extends BaseAdapter {
    Context context;
    ArrayList<DataModel1> list;
    ArrayList<DataModel1> iList;
    ArrayList<DataModel1> mTempArry = new ArrayList<DataModel1>();
    public Adapterr(Context cxt, ArrayList<DataModel1> iList) {
        this.context=cxt;
        this.list=iList;
        this.iList=new ArrayList<DataModel1>();
        this.iList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder vHolder=null;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.liststyle, viewGroup, false);
            vHolder = new ViewHolder();

            final DataModel1 dm = list.get(i);
            vHolder.tv = (TextView) view.findViewById(R.id.tvNameMain);
            vHolder.tv1 = (TextView) view.findViewById(R.id.tvNumberMain);
            vHolder.cb = (CheckBox) view.findViewById(R.id.chkEnable);
            vHolder.im = (ImageView) view.findViewById(R.id.ii);
            view.setTag(vHolder);
            vHolder.tv.setText(dm.getName());
            vHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    int getPosition = (Integer) compoundButton.getTag();
                    list.get(getPosition).setSelected(compoundButton.isChecked());
                }

            });
            view.setTag(vHolder);
            view.setTag(R.id.tvNameMain, vHolder.tv);
            view.setTag(R.id.tvNumberMain, vHolder.tv1);
            view.setTag(R.id.chkEnable, vHolder.cb);
            view.setTag(R.id.ii, vHolder.im);
        } else {
            vHolder = (ViewHolder) view.getTag();
        }
        vHolder.cb.setTag(i); // This line is important.

        vHolder.tv.setText(list.get(i).getName());
        vHolder.tv1.setText(list.get(i).getOtherData());
        vHolder.cb.setChecked(list.get(i).isSelected());
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(list.get(i).getOtherData()));

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



        } catch (Exception e) {
            e.printStackTrace();
        }
        vHolder.im.setImageBitmap(photo);
       /* if (dm.getValue()==1) {
            cb.setChecked(true);
        } else  {
            cb.setChecked(false);
        }*/
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

            }
        });


        return view;

    }

    static class ViewHolder {
        protected TextView tv,tv1;
        protected CheckBox cb;
        ImageView im;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(iList);
        } else {
            for (DataModel1 wp : iList) {
                if (wp.getName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    public ArrayList<DataModel1> getCheckedItems() {


        ArrayList<DataModel1> mTempArry = new ArrayList<DataModel1>();




        for(int i=0;i<list.size();i++) {


            if(list.get(i).isSelected()) {


                mTempArry.add(list.get(i));


            }


        }




        return mTempArry;


    }
}
