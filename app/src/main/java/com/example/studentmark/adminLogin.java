package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {
    private Button signin,signup;
    private EditText email,pass;
    adminDatabase RD=new adminDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        intwidget();
        calllistner();
    }

    private void calllistner() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(adminLogin.this,adminSignup.class);
                startActivity(i);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String password=pass.getText().toString();
                String h=RD.searchAdmin(Email,password);
                //Toast.makeText(adminLogin.this,h,Toast.LENGTH_SHORT).show();
                SharedPreferences sharedpreferences = getSharedPreferences("session2", Context.MODE_PRIVATE);

                if(h=="yes"){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    String resID=RD.getID(Email,password);
                    String Name=RD.getName(Email,password);
                    editor.putString("AdminID", resID);

                    editor.commit();
                    Intent i=new Intent(adminLogin.this,Admindash.class);
                    startActivity(i);
                }

            }
        });
    }

    private void intwidget() {
        signin=findViewById(R.id.btnlogin2);
        signup=findViewById(R.id.btnSignUp2);
        email=findViewById(R.id.txtLmail2);
        pass=findViewById(R.id.txtlpassword2);
    }
}