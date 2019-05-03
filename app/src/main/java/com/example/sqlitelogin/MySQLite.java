package com.example.sqlitelogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MySQLite extends SQLiteOpenHelper {

    // for constructor .......
    public  static final String DATABASE_NAME = "Studentdetails.db";
    public  static  final String TABLE_NAME = "Student_Details";
    public  static final int VERSION_NUMBER = 1;

    // table Element .....

    public  static  final String ID = "_id";
    public static  final String NAME = "Name";
    public static  final  String EMAIL = "Email";
    public static  final String USERNAME = "UserName";
    public  static  final  String PASSWORD = "Password";

    // context ....
      private Context context;

      // onCreate Method .....
     public  static  final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR (255) NOT NULL,"+EMAIL+" TEXT NOT NULL, "+USERNAME+" TEXT NOT NULL, "+PASSWORD+" TEXT NOT NULL )";

     // onUpgrade Method .......
    public static  final  String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    //use for --->>> userLogin method
    public  static  final String SELECT_ALL_DATA = "SELECT * FROM "+TABLE_NAME;



      // # constructor ......
    public MySQLite(@Nullable Context context ) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    // onCreate Method

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Table successfully created ",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(context,"Table create failed "+e,Toast.LENGTH_LONG).show();
        }
    }

    // onUpgrade Method ...

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context,"Table successfully updated",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(context,"Table update failed "+e,Toast.LENGTH_LONG).show();
        }
    }

    // insert Data ....

    public long insertData(UserDetails userDetails){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(USERNAME,userDetails.getUserName());
        contentValues.put(PASSWORD,userDetails.getPassword());
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowId;
    }

    // userLogin method ..
    public Boolean userLogin (String userName, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery(SELECT_ALL_DATA,null);
        Boolean showresult = false;
        if(cursor.getCount()==0){
            Toast.makeText(context,"Wrong!\n Please try again later ",Toast.LENGTH_LONG).show();
        }else {
            while (cursor.moveToNext()){
                String usernameCheck = cursor.getString(3);
                String passwordCheck = cursor.getString(4);
                    if(usernameCheck.equals(userName) && passwordCheck.equals(password)){
                        showresult = true;
                        break;
                    }
            }
        }
        return showresult;
    }
}
