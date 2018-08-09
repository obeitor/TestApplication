package com.gracefield.finalapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Make sure to create the UserAdapter, and user_list before working on this place
 */
public class UsersActivity extends AppCompatActivity {

    /**
     * This is the arraylist of all the users
     */
    private ArrayList<UserClass> users;
    /**
     * This is the adapter to adapt the above list to the list view individual items accordingly
     */
    private UserAdapter userAdapter;
    /**
     * This is the list view created in your activity_users
     */
    private ListView userList;
    /**
     * This is an object of the database for users u created
     */
    private UserClassDb userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        //initialize the database using its constructor with this UsersActivity as the context 'this'
        userDatabase = new UserClassDb(this);
        //initialize the users array
        users = new ArrayList<>();
        //initialize the adapter with the array and this activity as the context
        userAdapter = new UserAdapter(this,users);
        //attach the listview to the listview created in the activity_users using the id
        userList = (ListView)findViewById(R.id.userlist);
        //set the userAdapter as its adapter
        userList.setAdapter(userAdapter);
    }

    public void createUser(View view){
        Intent intent = new Intent(this, UserCreator.class);
        startActivity(intent);
    }

    /**
     * This method onResume is used to perform certain actions after an activity was pushed aside to allow
     * another activity and then the activity comes back on (resumed)
     * In this case, when we leave this activity to UserCreator, we did not call finish(), so this activity
     * remains in memory, is not closed entirely, when we come back to it we want it to re fetch all users
     * from the database
     *
     * make sure it has super.onResume() before any other code you want to add,
     */
    @Override
    protected void onResume() {
        super.onResume();
        //first clear the list
        users.clear();
        //then add all from db again
        users.addAll(userDatabase.getUsers());
        //notify the adapter to update the list view with the new update
        userAdapter.notifyDataSetChanged();
    }
}
