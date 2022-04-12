package com.example.warona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText email,username,password,rePassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.repassword);

        register.setOnClickListener(
                view -> {
                    String emailText = email.getText().toString();
                    String usernameText = username.getText().toString();
                    String passwordValue = password.getText().toString();
                    String rePasswordValue = rePassword.getText().toString();

                    if(emailText.isEmpty()){
                        Toast.makeText(RegisterActivity.this,
                                "The email field is empty",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(usernameText.isEmpty()){
                        Toast.makeText(this, 
                                "The username field is empty", 
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(passwordValue.isEmpty()){
                        Toast.makeText(this,
                                "The password field is empty",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if(rePasswordValue.isEmpty()){
                        Toast.makeText(this,
                                "Confirm password is empty",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //checking if the two password fits
                        if(passwordValue.equals(rePasswordValue)){
                            //register the user
                        }
                        else{
                            //when the two password do not match
                            Toast.makeText(this,
                                    "The two passwords do not match",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}