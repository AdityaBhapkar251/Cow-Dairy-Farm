package com.bhapkar.dairyfarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.userDetails);
        currentUser = auth.getCurrentUser();

        updateUI(currentUser);
    }
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser == null) {
            // No user is signed in
            Intent loginIntent = new Intent(this, Login.class);
            startActivity(loginIntent);
            finish();
        } else {
            // User is signed in
            Intent homeIntent = new Intent(this, HomePage.class);
            startActivity(homeIntent);
            finish();
        }
    }


//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
