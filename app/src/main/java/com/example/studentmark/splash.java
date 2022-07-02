package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splash extends AppCompatActivity {
    private Button admin,user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        intiwidget();
        calllistner();
    }

    private void calllistner() {
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(splash.this,adminLogin.class);
                startActivity(i);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(splash.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void intiwidget() {
        admin=findViewById(R.id.btngotoAdmin);
        user=findViewById(R.id.btngotoUser);
    }
}