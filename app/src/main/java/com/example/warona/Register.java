package com.example.warona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintStream;

public class Register extends AppCompatActivity {

    EditText email,username,password,rePassword,userType;
    Button registerBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailRegister);
        userType = findViewById(R.id.et_type);
        username = findViewById(R.id.username);
        password = findViewById(R.id.et_password);
        rePassword = findViewById(R.id.et_rePassword);
        registerBtn = findViewById(R.id.btn_register);
        DB = new DBHelper(this);

        registerBtn.setOnClickListener(
                view ->{
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

                            if(userExist){
                                Toast.makeText(
                                        this,
                                        "The email is already in use",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //registering user
                                boolean check =
                                        DB.registerUser(
                                                emailValue,
                                                userTypeValue,
                                                usernameValue,
                                                passwordValue);

                                if(check){
                                    Toast.makeText(
                                            this,
                                            "The user was registered successfully",
                                            Toast.LENGTH_SHORT).show();

                                    //navigating to the appropriate screen
                                    switch (userTypeValue){
                                        case "admin":
                                            startActivity(new
                                                    Intent(this,Admin.class));
                                            break;
                                        case "station":
                                            startActivity(new
                                                    Intent(this,FuelStations.class));
                                            break;
                                        case "user":
                                            startActivity(new
                                                    Intent(this,Home.class));
                                            break;
                                        default:
                                            Toast.makeText(
                                                    this,
                                                    "There no specified intent",
                                                    Toast.LENGTH_SHORT).show();
                                    }

                                    Intent intent = new Intent(this,Home.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(
                                            this,
                                            "Failed to save user to the database",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else{
                            Toast.makeText(this,
                                    "The two password do not match",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

    }
}