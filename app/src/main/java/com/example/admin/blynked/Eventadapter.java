package com.example.admin.blynked;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 27-07-2015.
 */
public class Eventadapter extends ArrayAdapter<Eventdata> {

    Context context;
    List<Eventdata> stock;
    public Eventadapter(Context cxt, List<Eventdata> contactstock) {
        super(cxt,R.layout.listt,contactstock);
        this.context=cxt;
        this.stock=contactstock;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ContactStockView sv = null;
        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.listt, parent, false);
            rowView = inflater.inflate(
                    R.layout.listt, null);

            // Hold the view objects in an object,
            // so they don't need to be re-fetched
            sv = new ContactStockView();
            sv.name = (TextView) rowView.findViewById(R.id.contact_name_log);
            sv.number = (TextView) rowView.findViewById(R.id.contact_number_log);
            sv.time=(TextView)rowView.findViewById(R.id.time);
            sv.location= (TextView) rowView.findViewById(R.id.location);
            sv.image=(ImageView) rowView.findViewById(R.id.imageView11);

            // Cache the view objects in the tag,
            // so they can be re-accessed later
            rowView.setTag(sv);
        } else {
            sv = (ContactStockView) rowView.getTag();
        }
        // Transfer the stock data from the data object
        // to the view objects
        Eventdata currentStock = (Eventdata) stock.get(position);
        sv.name.setText(currentStock.getName());
        sv.number.setText(currentStock.getNumber());
        sv.time.setText(currentStock.getTime());
        sv.location.setText(currentStock.getlocation());
        sv.image.setBackgroundResource(R.mipmap.eventicon);

        // TODO Auto-generated method stub
        final ContactStockView finalSv = sv;
        final ContactStockView finalSv1 = sv;

        return rowView;
    }

    protected static class ContactStockView {
        protected TextView name;
        protected TextView number;
        protected TextView location;
        protected ImageView image;
        protected TextView time;
    }
}


