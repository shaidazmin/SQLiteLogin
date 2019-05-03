package com.example.sqlitelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener {

    private EditText nameEditText,emailEditText,userNameSignUpEditText,passwordSingUpEditText;
    private Button signUpButton;
    UserDetails userDetails;
    MySQLite mySQLite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // getter and setter Class's Object create ..
            userDetails = new UserDetails();
            // MySQLite class
        mySQLite = new MySQLite(this);

        // find edit text ..
        nameEditText =(EditText) findViewById(R.id.nameEditText);
        emailEditText =(EditText) findViewById(R.id.emailEditText);
        userNameSignUpEditText =(EditText) findViewById(R.id.userNameSignUpEditText);
        passwordSingUpEditText =(EditText) findViewById(R.id.passwordSignUpEditText);

        // find button
        signUpButton = (Button) findViewById(R.id.signUpButton);

        // set listener ....

        signUpButton.setOnClickListener(this);


    }//close main method

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String userName = userNameSignUpEditText.toString();
        String password = passwordSingUpEditText.toString();
        //using setter .......
        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUserName(userName);
        userDetails.setPassword(password);
        // call insertData Method from MySQLite Class......
       long rowId =  mySQLite.insertData(userDetails);
       if(rowId>0){
           Toast.makeText(this,"Sign Up completed",Toast.LENGTH_LONG).show();
       }else {
           Toast.makeText(this,"Sign Up failed",Toast.LENGTH_LONG).show();
       }
    }
}
