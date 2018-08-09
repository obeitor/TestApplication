package com.gracefield.finalapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Abdulgafar Obeitor on 8/6/2018.
 * This adapter adapts the User array to the list so as to show all the info as designed by your layout
 * it should hold the array and the context of the List
 */
public class UserAdapter extends BaseAdapter {

    private ArrayList<UserClass> users;
    private Context context;

    /**
     * This is the constructor for this adapter, the adapter needs a list of users and
     * application context for it to initialize
     * @param context
     * @param users
     */
    public UserAdapter(Context context, ArrayList<UserClass> users){
        this.context = context;
        this.users = users;
    }

    /**
     * This method is overriden from the parent BaseAdapter, it return the number of items in the list
     * make sure to change the return value from zero to the user list size
     * @return
     */
    @Override
    public int getCount() {
        return this.users.size();
    }

    /**
     * this returns an item from the array using the position i, yours might call it pos, it is same thing
     * make sure to change it from null to an object from the users list with position i
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return this.users.get(i);
    }

    /**
     * This returns the id of the item in the particular position indicated as i, yours might be called something else
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return this.users.get(i).getId();
    }

    /**
     * This method attaches the attributes of a single user object to the layout you designed for individual items on the list
     * Do not be afraid if yours is not called convertView, or i, just use what it is being called thruout the code
     * @param i
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            //the below inflates our layout user_list into the view convertView,
            convertView = inflater.inflate(R.layout.user_list,null);
        }
        TextView name = (TextView)convertView.findViewById(R.id.name);
        TextView dept = (TextView)convertView.findViewById(R.id.dept);
        TextView gender = (TextView)convertView.findViewById(R.id.gender);
        TextView state = (TextView)convertView.findViewById(R.id.state);

        //get the user in the indicated position i
        UserClass thisUser = this.users.get(i);
        //set the users attribute on all the text views accordingly
        name.setText(thisUser.getFirstname()+" "+thisUser.getLastname());
        dept.setText(thisUser.getDepartment());
        gender.setText(thisUser.getGender());
        state.setText(thisUser.getState()+" state");
        return convertView;
    }
}
