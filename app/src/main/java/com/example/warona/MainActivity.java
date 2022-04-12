package com.example.warona;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.btnSignIn);

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
                    }
                }
        );
    }
}