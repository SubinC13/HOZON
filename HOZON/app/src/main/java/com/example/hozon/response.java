package com.example.hozon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class response extends AppCompatActivity {

    EditText edtFullName, edtMobile, edtLocation, edtNearLocation, edtQty, edtEmail, edtType;

    Button btnSignUp;

    String txtFullName, txtMobile, txtLocation, txtNearLocation, txtQty, txtEmail, txtType;

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    Button submit_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        edtFullName = findViewById(R.id.name);
        edtMobile = findViewById(R.id.number);
        edtLocation = findViewById(R.id.location);
        edtNearLocation = findViewById(R.id.near_location);
        edtQty = findViewById(R.id.qty);
        edtEmail = findViewById(R.id.email);
        edtType = findViewById(R.id.food_type);
        btnSignUp = findViewById(R.id.response_submit);


        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFullName = edtFullName.getText().toString();
                txtMobile = edtMobile.getText().toString().trim();
                txtLocation = edtLocation.getText().toString().trim();
                txtNearLocation = edtNearLocation.getText().toString().trim();
                txtQty = edtQty.getText().toString().trim();
                txtEmail = edtEmail.getText().toString().trim();
                txtType = edtType.getText().toString().trim();



                if (!TextUtils.isEmpty(txtEmail)) {

                    if (!TextUtils.isEmpty(txtType)) {

                        if (!TextUtils.isEmpty(txtFullName)) {
                            if (!TextUtils.isEmpty(txtLocation)) {
                                if (!TextUtils.isEmpty(txtMobile)) {
                                    if (txtMobile.length() == 10) {
                                        if (!TextUtils.isEmpty(txtNearLocation)) {
                                            if (!TextUtils.isEmpty(txtQty)) {
                                                if (txtMobile.isEmpty()) {
                                                    edtMobile.setError("Enter a valid Mobile");
                                                } else {
                                                    SignUpUser();
                                                }
                                            } else {
                                                edtQty.setError("Location Field can't be empty");
                                            }
                                        } else {
                                            edtNearLocation.setError("Near location can't be empty");
                                        }
                                    } else {
                                        edtMobile.setError("Enter a valid Mobile");
                                    }
                                } else {
                                    edtMobile.setError("Mobile Number Field can't be empty");
                                }
                            } else {
                                edtLocation.setError("Email Field can't be empty");
                            }
                        } else {
                            edtFullName.setError("Full Name Field can't be empty");
                        }
                    } else {
                        edtType.setError("Food type can't be empty");
                    }
                } else {
                    edtEmail.setError("E-mail field can't be empty");
                }
            }
        });

    }

    private void SignUpUser() {

        btnSignUp.setVisibility(View.INVISIBLE);

        mAuth.createUserWithEmailAndPassword(txtEmail, txtMobile).addOnSuccessListener(new OnSuccessListener < AuthResult > () {
            @Override
            public void onSuccess(AuthResult authResult) {
                Map < String, Object > user = new HashMap < > ();
                user.put("Name", txtFullName);
                user.put("Mobile Number", txtMobile);
                user.put("Location", txtLocation);
                user.put("Near Location", txtNearLocation);
                user.put("QTY", txtQty);
                user.put("Type", txtType);

                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener < DocumentReference > () {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(response.this, "Data Stored Successfully !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(response.this, thank_you.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(response.this, "Error - " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(response.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                btnSignUp.setVisibility(View.VISIBLE);
            }
        });


    }
}