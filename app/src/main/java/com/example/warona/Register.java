package com.example.warona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText email,username,password,rePassword,userType;
    Button registerBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.et_email);
        userType = (EditText) findViewById(R.id.et_type);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.et_password);
        rePassword = (EditText) findViewById(R.id.et_rePassword);
        registerBtn = (Button) findViewById(R.id.btn_register);
        DB = new DBHelper(Register.this);

        registerBtn.setOnClickListener(view -> {
            String emailValue = email.getText().toString();
            String usernameValue = username.getText().toString();
            String passwordValue = password.getText().toString();
            String rePasswordValue = rePassword.getText().toString();
            String userTypeValue = userType.getText().toString();

            //checking if the fields are empty
            if(emailValue.isEmpty()){
                Toast.makeText(
                        this,
                        "The email field is empty",
                        Toast.LENGTH_SHORT).show();
            }
            else if(userTypeValue.isEmpty()){
                Toast.makeText(
                        this,
                        "The user type field is empty",
                        Toast.LENGTH_SHORT).show();
            }
            else if(usernameValue.isEmpty()){
                Toast.makeText(this,
                        "The username is empty",
                        Toast.LENGTH_SHORT).show();
            }
            else if(passwordValue.isEmpty()){
                Toast.makeText(this,
                        "The password is empty",
                        Toast.LENGTH_SHORT).show();
            }
            else if(rePasswordValue.isEmpty()){
                Toast.makeText(this,
                        "The confirm password is empty",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                //checking if the two password are the same
                if(passwordValue.equals(rePasswordValue)){
                    //checking if user exist
                    boolean userExist = DB.checkUser(emailValue);

                    if(!userExist){
                        //registering user
                        boolean check =
                                DB.registerUser(emailValue,usernameValue,passwordValue);

                        if(check){
                            Toast.makeText(
                                    this,
                                    "The user was registered successfully",
                                    Toast.LENGTH_SHORT).show();

//                            Intent intent = new Intent(getApplicationContext(),Home.class);
//                            startActivity(intent);
                        }
                    }
                    else{
                        Toast.makeText(
                                this,
                                "The email is already in use",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this,
                            "The two password do not match",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}