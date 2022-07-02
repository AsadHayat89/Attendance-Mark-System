package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Present extends AppCompatActivity {
    private Button pres,Can;
    int day,month,year;
    String Email,Name,ID;
    Date date;
    public RegisterDatabase ATD=new RegisterDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present);


        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);



        SharedPreferences sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);

        Email=sharedPreferences.getString("UserEmail", "defaultValue");
        Name=sharedPreferences.getString("UserName", "defaultValue");
        ID=sharedPreferences.getString("UserID", "defaultValue");

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int w=dm.widthPixels;
        int he=dm.heightPixels;
        getWindow().setLayout((int)(w*.8),(int)(w*.6));
        intwidget();
        calllistner();


    }

    private void calllistner() {
        pres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Present.this,UserDahboard.class);
                String checl=ATD.checkdate(day,month,year,ID);
                if(checl=="false"){
                    if(ATD.AddAttendenca(ID,day,month,year,1)){
                       Toast.makeText(Present.this,"Marked ",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Present.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Present.this,"Your Attendence already marked",Toast.LENGTH_SHORT).show();
                }



              //  Toast.makeText(Present.this,checl,Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        Can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Present.this,UserDahboard.class);
                String checl=ATD.checkdate(day,month,year,ID);
                if(checl=="false"){
                    if(ATD.AddAttendenca(ID,day,month,year,2)){
                        Toast.makeText(Present.this,"Marked ",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Present.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Present.this,"Your Attendence already marked",Toast.LENGTH_SHORT).show();
                }



                //  Toast.makeText(Present.this,checl,Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

    }

    private void intwidget() {
        pres=findViewById(R.id.btnp);
        Can=findViewById(R.id.btnC);
    }
}