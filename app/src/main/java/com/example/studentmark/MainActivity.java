package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button signin,signup;
    private EditText email,pass;
    RegisterDatabase RD=new RegisterDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intwidigits();
        calllistners();


    }

    private void calllistners() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Registeration.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String password=pass.getText().toString();
                String h=RD.searchStudent(Email,password);
                //Toast.makeText(MainActivity.this,h,Toast.LENGTH_SHORT).show();
                SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);

                if(h=="yes"){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    String resID=RD.getID(Email,password);
                    String Name=RD.getName(Email,password);
                    editor.putString("UserID", resID);
                    editor.putString("UserEmail",Email);
                    editor.putString("UserName",Name);
                    editor.commit();
                    Intent i=new Intent(MainActivity.this,UserDahboard.class);
                    startActivity(i);
                }

            }
        });
    }

    private void intwidigits() {
        signin=findViewById(R.id.btnlogin);
        signup=findViewById(R.id.btnSignUp);
        email=findViewById(R.id.txtLmail);
        pass=findViewById(R.id.txtlpassword);
    }
}