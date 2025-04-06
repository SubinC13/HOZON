package com.example.hozon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class know extends AppCompatActivity {

    ImageView main_move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        main_move=findViewById(R.id.imageView5);
        main_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(know.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}