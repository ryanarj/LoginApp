package com.example.kr.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    // The is the user profile screen of the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the fields and buttons
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView etWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        final Button bLogout = (Button) findViewById(R.id.bLogout);

        // If the logout button is clicked on create an intent of the login screen
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        // Get the Intent retrieve the information from the Database
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        int age = intent.getIntExtra("age", -1);

        // Set the information ont he appropriated fields
        String message = name + " welcome to your profile";
        etWelcomeMsg.setText(message);
        etUsername.setText(username);
        etAge.setText(age + "");

    }
}
