package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserDahboard extends AppCompatActivity {
    private TextView t1,t2;
    private ImageButton present,absent,view,Edit,application;
    private Button logout;
    String ID;
    SharedPreferences sharedPreferences;
    RegisterDatabase Rd=new RegisterDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dahboard);

        sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        ID=sharedPreferences.getString("UserID", "defaultValue");
        Register r=Rd.getStudent(ID);

        intwidget();
        t1.setText(r.getName());
        t2.setText(r.getMail());
        clicklistner();



    }

    private void clicklistner() {
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserDahboard.this,Present.class);
                startActivity(i);
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserDahboard.this,ViewAttendence.class);
                startActivity(i);
            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserDahboard.this,UserEdit.class);
                startActivity(i);
            }
        });

        application.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserDahboard.this,AppStatus.class);
                startActivity(i);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().commit();
                Intent i=new Intent(UserDahboard.this,splash.class);
                startActivity(i);
            }
        });
    }

    private void intwidget() {
        t1=findViewById(R.id.txtUName);
        t2=findViewById(R.id.txtUEmail);
        present=findViewById(R.id.btnpresent);
        absent=findViewById(R.id.btnabsent);
        application=findViewById(R.id.btnview);
        Edit=findViewById(R.id.btnuseredit);
        logout=findViewById(R.id.btnlogout);
    }
}