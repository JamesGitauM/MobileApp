package com.example.labpro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button buttonnxt=findViewById(R.id.btn_next_page);
        buttonnxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent=new Intent(getApplicationContext(),MainActivity.class);
               HomeActivity.this.startActivity(nextIntent);
            }
        });

    }
}
