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

public class login_page extends AppCompatActivity {


    TextInputEditText user,pass;
    Button veri_log;

    FirebaseAuth mAuth;
    ProgressBar progress;

    TextView textView;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(login_page.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        user = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        veri_log = findViewById(R.id.button_login);
        mAuth = FirebaseAuth.getInstance();
        progress = findViewById(R.id.progress_bar);
        textView=findViewById(R.id.textView4);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(login_page.this,registration.class);
                startActivity(intent);
                finish();
            }
        });

        veri_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress.setVisibility(view.VISIBLE);
                String email,security_key;

                email = String.valueOf(user.getText());
                security_key = String.valueOf(pass.getText());

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(login_page.this, "Enter the username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(security_key))
                {
                    Toast.makeText(login_page.this, "Enter a password ", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,security_key)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(login_page.this, "Verified Successfully. ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login_page.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(login_page.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}