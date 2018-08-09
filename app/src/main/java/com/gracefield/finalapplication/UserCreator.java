package com.gracefield.finalapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class UserCreator extends AppCompatActivity {

    private EditText firstname;
    private EditText lastname;
    private Spinner dept;
    private Spinner gender;
    private Spinner state;

    private UserClassDb userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_creator);
        userDatabase = new UserClassDb(this);
        firstname = (EditText)findViewById(R.id.fname);
        lastname = (EditText)findViewById(R.id.sname);
        dept = (Spinner)findViewById(R.id.select_dept);
        gender = (Spinner)findViewById(R.id.select_gender);
        state = (Spinner)findViewById(R.id.select_state);
    }

    public void add(View view){
        String fnameString = firstname.getText().toString();
        String lnameString = lastname.getText().toString();
        String deptString = dept.getSelectedItem().toString();
        String genderString = gender.getSelectedItem().toString();
        String stateString = state.getSelectedItem().toString();
        //call the constructor to create new user and pass all the variables into it
        UserClass newUser = new UserClass(fnameString,lnameString,genderString,deptString,stateString);
        userDatabase.saveUser(newUser);
        finish();
    }
}
