package com.gracefield.finalapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    //This are the variables that hold the input text fields you created on the layout page
    private EditText username;
    private EditText password1;
    private EditText password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this setContentView sets the layout activity_signup to this java activity, therefore
        //once this activity comes up, the layout is displayed.
        //R stands for resource (res folder), then layout , then the name of the layout.
        //Therefore this indicates the location of the layout res/layout/activity_signup
        setContentView(R.layout.activity_signup);
        //The EditTexts are being initialized to point to the EditTexts created in the layout page
        //findViewById uses the ID assigned to the widgets in the layout
        username = (EditText) findViewById(R.id.username);
        password1 = (EditText)findViewById(R.id.pass1);
        password2 = (EditText)findViewById(R.id.pass2);

        //The below statement checks if a user has already signed up, therefore it goes to the LoginActivity
        //this method: alreadySignedUp() would be created below before you can write this code here to call it
        if(alreadySignedUp()){
            //creates an intent to go to Login Activity
            Intent intent = new Intent(this, LoginActivity.class);
            //starts the login activity
            startActivity(intent);
            //finish this current activity SignupActivity, so that this page is closed completely and not just moved down
            finish();
            //if you press back button while in the loginActivity, it would not come back to this page because of the finish()
            //by omitting the finish, whenever u press back button in the loginActivity, it comes back here
        }
    }

    //this method is created to be put on the onclick of SignupButton in the layout
    public void signupMethod(View view){
        //first get the string values of what user entered, I called it userN, pass1, pass2, u can call
        //it as u wish but it should be appropriate enough for you to remember it stores username
        String userN = username.getText().toString();
        String pass1 = password1.getText().toString();
        String pass2 = password2.getText().toString();
        //checks if the pass1 is not equal to pass2, then notify the user of the error
        if(!pass1.equals(pass2)){
            Toast.makeText(this, "Password is not the same", Toast.LENGTH_SHORT).show();
            return;//return means the method should leave at this point and not continue below
        }
        //checks if user entered something in the username or password side
        if(userN.isEmpty() || pass1.isEmpty()){
            //show error
            Toast.makeText(this, "Write a user name", Toast.LENGTH_LONG);
            return;//leave this method
        }

        /*
        In order to store username and password privately on the device for this app
        SharedPreferences gets a file on the phone which I have called 'abdul',
         if the file does not exist, creates one
        NB: you can call it as you wish, but remember that when you want to get the file in the future
        you must use the same name you created it with else it would not find it
        Since we wish
        SharedPreferences.Editor is created so as to use in editing the file.
        editor.putString puts the username entered by the user inside the file and gave it a name 'username'
        whenever I want to get it back, I look for 'username' and get what is stored there
        Same is done for pass
        the editing is then committed thus storing the file
         */
        SharedPreferences pref = getSharedPreferences("abdul",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Username",userN);
        editor.putString("Pass",pass1);
        editor.commit();

        //Once done, we move to the main page of our app, in this case, UserCreator,
        //Make sure to have created the activity UserCreator, before calling this intent and directing to it
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
        finish();
    }

    /*
    This method alreadySignedUp() is called inside the onCreate,
    it checks to see if a username is already stored in the private file of the app, which means, a user
    has already signed up, it returns true or false based on this.
    this method should be written here before being called in onCreate()
     */
    public boolean alreadySignedUp(){
        //first get the file 'abdul' if already exists
        SharedPreferences pref = getSharedPreferences("abdul",MODE_PRIVATE);
        //get the string stored as user, or if cannot find it just take an empty string ""
        String user = pref.getString("Username","");
        //so if it is an empty string, means could not find it
        if(user.isEmpty()){
            return false;
        }
        else {
            return true;
        }
    }
}
