package com.example.kr.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    private EditText etUsername, etAge;
    private TextView tvUsername, tvAge;
    private static String FIREBASE_URL = "https://fir-demo-31922.firebaseio.com/";
    private String username, age;


    // The is the user profile screen of the app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing firebase
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        // Set the fields and buttons
        etUsername = (EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvAge = (TextView) findViewById(R.id.tvAge);
        final Button bLogout = (Button) findViewById(R.id.bLogout);
        final Button bSave = (Button) findViewById(R.id.bSave);
        final Button bAddPicture = (Button) findViewById(R.id.bAddPicture);
        final Button bChatroom = (Button) findViewById(R.id.bChatroom);


        if (user == null){
            finish();
            startActivity(new Intent(this, Login.class));
        }
        // Get the database of the objects like name and age from current user
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid());
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("username")) {
                    username = dataSnapshot.getValue(String.class).trim();
                    tvUsername.setText(username);
                }
                if (dataSnapshot.getKey().equals("age")) {
                    age = dataSnapshot.getValue(String.class).trim();
                    tvAge.setText(age);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("username")) {
                    username = dataSnapshot.getValue(String.class).trim();
                    tvUsername.setText(username);
                }
                if (dataSnapshot.getKey().equals("age")) {
                    age = dataSnapshot.getValue(String.class).trim();
                    tvAge.setText(age);
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // Saves the data the user put in  the text fields
        bSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveUserInformation();
            }

        });

        // If the logout button is clicked on create an intent of the login screen
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent loginIntent = new Intent(MainActivity.this, Login.class);
                MainActivity.this.startActivity(loginIntent);
            }
        });


        // Add profile picture
        bAddPicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });

        // Launch the chatroom page
        bChatroom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent chatroomIntent = new Intent(MainActivity.this, ChatroomActivity.class);
                MainActivity.this.startActivity(chatroomIntent);
            }
        });

    }

    private void saveUserInformation() {
        String username = etUsername.getText().toString().trim();
        String age = etAge.getText().toString().trim();

        // Save the information of the user
        UserInformationData userInformationData = new UserInformationData(username, age);
        database.setValue(userInformationData);
        Toast.makeText(this, "Profile is updated", Toast.LENGTH_LONG).show();
    }

}
