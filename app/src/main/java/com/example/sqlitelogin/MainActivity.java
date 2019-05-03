package com.example.sqlitelogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MySQLite mySQLite;
  private EditText logInnameEditText,logInpasswordEditText;
  private Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // create DataBase .....
       mySQLite = new MySQLite(this);
       SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();

       // find Edit Text ...
        logInnameEditText =(EditText) findViewById(R.id.name);
        logInpasswordEditText =(EditText) findViewById(R.id.password);
        // find button
        signInButton = (Button) findViewById(R.id.signInButton);
        signUpButton = (Button) findViewById(R.id.signUpMainButton);

        // set listener ....
        signInButton.setOnClickListener(this);
         signUpButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        String userName = logInnameEditText.getText().toString();
        String password = logInpasswordEditText.getText().toString();

        // sign In

        if(view.getId() == R.id.signInButton){
            Boolean result = mySQLite.userLogin(userName,password);
            if(result == true){
                Intent intent = new Intent(MainActivity.this,WellcomeActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this,"User Name & password doesn't match",Toast.LENGTH_LONG).show();
            }

        }

        // sign Up ...

        else if(view.getId() == R.id.signUpMainButton){
            Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(intent);
        }

    }
}
