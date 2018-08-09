package com.gracefield.finalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user =   (EditText)findViewById(R.id.userlogin);
        pass = (EditText)findViewById(R.id.pass);
    }
    public void login(View view){
        String userEntered = user.getText().toString();
        String passEntered = pass.getText().toString();
        if(userEntered.isEmpty() || passEntered.isEmpty()){
            Toast.makeText(this,"Username or Password is empty",Toast.LENGTH_LONG).show();
            return;
        }
        SharedPreferences preferences = getSharedPreferences("abdul",MODE_PRIVATE);
        String storedUsername = preferences.getString("Username","");
        String storedPass = preferences.getString("Pass","");
        if(userEntered.equalsIgnoreCase(storedUsername) && passEntered.equals(storedPass)){
            Intent intent = new Intent(this, UsersActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this,"Username or Password is Incorrect!",Toast.LENGTH_LONG).show();
        }
    }
}
