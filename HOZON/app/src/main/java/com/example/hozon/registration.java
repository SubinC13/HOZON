package com.example.hozon;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registration extends AppCompatActivity {

    TextInputEditText user,
            pass;
    Button veri;

    FirebaseAuth mAuth;
    ProgressBar progress;

    TextView textView;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(registration.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        veri = findViewById(R.id.button_registration);
        mAuth = FirebaseAuth.getInstance();
        progress = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.textView3);
        textView.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {
            Intent intent = new Intent(registration.this, login_page.class);
            startActivity(intent);
            finish();
        }
        });

        veri.setOnClickListener(new View.OnClickListener() {@Override
        public void onClick(View view) {

            progress.setVisibility(view.VISIBLE);
            String email,
                    security_key;

            email = String.valueOf(user.getText());
            security_key = String.valueOf(pass.getText());

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(registration.this, "Enter the email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(security_key)) {
                Toast.makeText(registration.this, "Enter the password ", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, security_key).addOnCompleteListener(new OnCompleteListener < AuthResult > () {@Override
            public void onComplete(@NonNull Task < AuthResult > task) {
                progress.setVisibility(View.GONE);
                if (task.isSuccessful()) {

                    Toast.makeText(registration.this, "User ID and Password generated.", Toast.LENGTH_SHORT).show();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(registration.this, "Please enter the valid details.", Toast.LENGTH_SHORT).show();
                }
            }
            });

        }
        });
    }
}