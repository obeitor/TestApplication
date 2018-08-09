package com.gracefield.finalapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Abdulgafar Obeitor on 8/6/2018.
 *
 * THis class is extending another class, SQLiteOpenHelper
 * Extending means to become a subclass of another higher class, thus having all the features of your parent
 * in addition to the features of your parent, you have your own personal features which are not available to the parent
 * methods of your parent can be used exactly as they are or overridden with your own code
 * therefore any method you see @Override on is actually a method of the parent SQLiteOpenHelper
 * but the child UserClassDb is rewriting it for its own use.
 */
public class UserClassDb extends SQLiteOpenHelper{

    /**
     * This is the constructor for UserClassDb, it has to call the constructor of its parent,
     * which is super();
     * the constructor of its parent takes context, which is basically the activity that is in use of this
     * the context makes the resources of the activity available for this class.
     * database.db is the name of the database to be created.
     * null is actually suppose to be the cursor for the database, but we are not passing any default cursor thus null
     * 1 represents the version of the database, version 1
     * @param context
     */
    public UserClassDb(Context context){
        super(context,"database.db",null,1);
    }

    /**
     * this is overriden from the parent,
     * it defines what happens when database is being created,
     * for us we run an SQL that creates the table we need
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        //NB: The id is named _id so that sqlitedatabase can recognize it as id and return it when saving a
        //new user to the table
        db.execSQL("CREATE TABLE users (_id integer primary key autoincrement, fname varchar[40], sname varchar[40], gender varchar[7]" +
                ", department varchar[40], state_of_origin varchar[20])");
    }

    /**
     * This is overriden from the parent
     * it defines what happens when you want to upgrad your database
     * for us we run an sql that deletes the previous table
     * then we re create the table
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    /**
     * This method takes a user,
     * saves him on the database
     * then returns the id of the new object added to the person that called it
     * @param user
     * @return
     */
    public long saveUser(UserClass user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //remember the column name is to be called below when putting the variables, therefore make sure they
        //are same as you used in creating your table 'fname', 'sname', 'gender', 'department', 'state_of_origin'
        cv.put("fname",user.getFirstname());
        cv.put("sname",user.getLastname());
        cv.put("gender",user.getGender());
        cv.put("department",user.getDepartment());
        cv.put("state_of_origin",user.getState());
        return db.insert("users",null,cv);
    }

    /**
     * We want to get a list of all users stored on database
     * first we get a readable version of the db
     * we create a curson to use to loop through the rows and run a query into it, to select from user
     * no search arguments involved since looking for all thus the null
     * we move the cursor to the first item
     * create the array list that would hold it
     * while cursor is not after last item, we create a user object, set all the attributes with what is
     * got from the database as fields, add the user into the array list users.
     * when done, return the list
     * @return
     */
    public ArrayList<UserClass> getUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM users",null);
        rs.moveToFirst();
        ArrayList<UserClass> users = new ArrayList<>();
        while(!rs.isAfterLast()) {
            UserClass user = new UserClass();
            user.setId(rs.getInt(rs.getColumnIndex("_id")));
            user.setFirstname(rs.getString(rs.getColumnIndex("fname")));
            user.setLastname(rs.getString(rs.getColumnIndex("sname")));
            user.setGender(rs.getString(rs.getColumnIndex("gender")));
            user.setDepartment(rs.getString(rs.getColumnIndex("department")));
            user.setState(rs.getString(rs.getColumnIndex("state_of_origin")));
            users.add(user);
            rs.moveToNext();
        }
        return users;
    }

    /**
     * Searching by firstname or lastname
     * same as getting all, but this time there is an argument
     * the question marks in the query would be replaced with the strings inside the array 'argument'
     * fname would replace the first ? while lname replaces the second.
     * Remember to arrange your arguments accordingly with the ?s
     * @param fname
     * @param lname
     * @return
     */
    public ArrayList<UserClass> searchByFirstnameOrLastname(String fname, String lname){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] argument = {fname, lname};
        Cursor rs = db.rawQuery("SELECT * FROM users WHERE fname LIKE ? OR sname LIKE ?",argument);
        rs.moveToFirst();
        ArrayList<UserClass> users = new ArrayList<>();
        while(!rs.isAfterLast()) {
            UserClass user = new UserClass();
            user.setId(rs.getInt(rs.getColumnIndex("_id")));
            user.setFirstname(rs.getString(rs.getColumnIndex("fname")));
            user.setLastname(rs.getString(rs.getColumnIndex("sname")));
            user.setGender(rs.getString(rs.getColumnIndex("gender")));
            user.setDepartment(rs.getString(rs.getColumnIndex("department")));
            user.setState(rs.getString(rs.getColumnIndex("state_of_origin")));
            users.add(user);
            rs.moveToNext();
        }
        return users;
    }

    /**
     * all the parameters are set according same as in saving a new user
     * db.update first pass the table name 'users', then the content to be put 'ContentValues cv'
     * then the where clause, 'where _id = ?' then the argument is being passed inside a string array
     * the only argument in this case is the id, the id is an integer therefore an empty string
     * is added to it to change it to a string
     * @param user
     */
    public void updateUser(UserClass user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fname",user.getFirstname());
        cv.put("sname",user.getLastname());
        cv.put("gender",user.getGender());
        cv.put("department",user.getDepartment());
        cv.put("state_of_origin",user.getState());
        db.update("users",cv,"_id = ?",new String[]{user.getId()+""});
    }
}
