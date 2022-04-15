package com.example.warona;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button loginButton;
    DBHelper DB;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        DB = new DBHelper(this);

        //login button
        loginButton.setOnClickListener(
                view -> {
                    String emailText = email.getText().toString();
                    String passwordText = password.getText().toString();

                    //checking if the fields are empty
                    if(emailText.isEmpty() ){
                        Toast.makeText(MainActivity.this,
                                "The email field is Empty",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(passwordText.isEmpty()){
                        Toast.makeText(MainActivity.this,
                                "The password field is empty",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //check if the email exist in the database
                        boolean userExist = DB.checkUser(emailText);
                        if(userExist){
                            Toast.makeText(
                                    this,
                                    "A user with that email already exists",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(
                                    this,
                                    "The user does not exist",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        //register button
        imageButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),Register.class);
            startActivity(intent);
        });
    }
}