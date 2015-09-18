package com.example.admin.blynked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 01-07-2015.
 */
public class CustomAdapter extends BaseAdapter{


    private static final String TAG = CustomAdapter.class.getSimpleName();
    ArrayList<DataModel> listArray = null;
    String userid;
    Context context;
    ArrayList<DataModel> countrylist;
    ArrayList<DataModel> mmStringFilterList = new ArrayList<DataModel>();
    ArrayList<DataModel> mStringFilterList;
    int flag;


    public CustomAdapter(Context context, ArrayList<DataModel> countrylist) {
        this.context = context;
        this.countrylist = countrylist;
        this.mStringFilterList = countrylist;
        this.userid = userid;


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
                view = inflater.inflate(R.layout.custom_item_layout, parent, false);
                // Toast.makeText(parent.getContext(), "There is no data", Toast.LENGTH_SHORT).show();
            }

            final DataModel dataModel = countrylist.get(index);

            final TextView textView = (TextView) view.findViewById(R.id.text_row);
            final ImageView imageView=(ImageView)view.findViewById(R.id.img_row);
            textView.setText(dataModel.getName());
            imageView.setImageResource(dataModel.getOtherData());



            return view;
        }


}

