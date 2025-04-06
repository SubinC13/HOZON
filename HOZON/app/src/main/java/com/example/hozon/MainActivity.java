package com.example.hozon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView know_more_mo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button mButton=findViewById( R.id.response_move);
        CheckBox mCheckBox=findViewById( R.id.checkBox1);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    mButton.setEnabled(true);

                    mButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent next = new Intent(MainActivity.this,response.class);
                            startActivity(next);
                        }
                    });

                }else{
                    mButton.setEnabled(false);
                }

            }
        });

        know_more_mo=findViewById(R.id.more_about_us_txt);
        know_more_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,know.class);
                startActivity(intent);
            }
        });

    }
}