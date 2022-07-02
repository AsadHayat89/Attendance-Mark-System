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

public class Admindash extends AppCompatActivity {
    private TextView t1,t2;
    String ID;
    private ImageButton view,report;
    adminDatabase ADd=new adminDatabase(this);
    private Button logout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindash);
        sharedPreferences = getSharedPreferences("session2", Context.MODE_PRIVATE);
        ID=sharedPreferences.getString("AdminID", "defaultValue");
        intwidget();
        admin r=ADd.getAdmin(ID);
        t1.setText(String.valueOf(r.getName()));
        t2.setText(String.valueOf(r.getMail()));

        calllistner();
    }

    private void calllistner() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admindash.this,adminmanageP.class);
                startActivity(i);

            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Admindash.this,adminReport.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().commit();
                Intent i=new Intent(Admindash.this,splash.class);
                startActivity(i);
            }
        });
    }

    private void intwidget() {
        t1=findViewById(R.id.txtUName2);
        t2=findViewById(R.id.txtUEmail2);
        view=findViewById(R.id.btnview2);
        report=findViewById(R.id.btnreport);
        logout=findViewById(R.id.btnlogout2);
    }
}