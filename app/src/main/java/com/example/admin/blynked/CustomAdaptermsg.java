package com.example.admin.blynked;

/**
 * Created by Admin on 07-08-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



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
public class CustomAdaptermsg extends BaseAdapter implements Filterable {


    private static final String TAG = CustomAdapter.class.getSimpleName();
    ArrayList<DataModelmsg> listArray = null;
    String userid;
    Context context;
    ArrayList<DataModelmsg> countrylist;
    ArrayList<DataModelmsg> mmStringFilterList = new ArrayList<DataModelmsg>();
    ArrayList<DataModelmsg> mStringFilterList;
    int flag;
    public static final String STORAGE1 = "SenddataPreferences";
ValueFilter valueFilter;

    public CustomAdaptermsg(Context context, ArrayList<DataModelmsg> countrylist) {
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
            view = inflater.inflate(R.layout.custom_item_layout_msg, parent, false);
            // Toast.makeText(parent.getContext(), "There is no data", Toast.LENGTH_SHORT).show();
        }

        final DataModelmsg dataModel = countrylist.get(index);

        final TextView textView = (TextView) view.findViewById(R.id.text_row);
      //  final ImageView imageView=(ImageView)view.findViewById(R.id.img_row);
        textView.setText(dataModel.getName());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = (TextView) view.findViewById(R.id.text_row);
                String msg=textView.getText().toString();
                SharedPreferences shared1 = context.getSharedPreferences(STORAGE1, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared1.edit();
                editor.putString("Msg",msg);
                editor.apply();
                Intent i=new Intent(context,Share_main.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

        return view;
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
                ArrayList<DataModelmsg> filterList = new ArrayList<DataModelmsg>();

                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ( (mStringFilterList.get(i).getName().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        DataModelmsg country = new DataModelmsg(mStringFilterList.get(i)
                                .getName() ,  mStringFilterList.get(i)
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
            countrylist = (ArrayList<DataModelmsg>) results.values;
            notifyDataSetChanged();
        }

    }
}


