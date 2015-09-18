package com.example.admin.blynked;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Admin on 10-08-2015.
 */
public class CustomListAdapter1 extends BaseAdapter {
    Context context;
    ArrayList<DataModelsetting> linkList;
    public CustomListAdapter1(Context context, ArrayList<DataModelsetting> linklist) {
        this.context=context;
        this.linkList=linklist;
    }

    @Override
    public int getCount() {
        return linkList.size();
    }

    @Override
    public Object getItem(int i) {
        return linkList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //LayoutInflater inflater=(LayoutInflater.from(viewGroup.getContext()));
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.list_grouplink,viewGroup,false);
        final DataModelsetting dataModel = linkList.get(i);

       final TextView title=(TextView)view.findViewById(R.id.titleText);
        title.setText(dataModel.getTitle());

        final Button loginButton = (Button) view.findViewById(R.id.logId);

        //lblListHeader.setTypeface(null, Typeface.BOLD);

        if (i == 0) {



            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AccessToken token = AccessToken.getCurrentAccessToken();
                    if (token == null) {
                        LoginManager.getInstance().logInWithReadPermissions((android.app.Activity) context, Arrays.asList("public_profile", "user_friends"));
                    } else {
                        LoginManager.getInstance().logOut();
                    }
                }
            });
        }



        return view;
    }
}
