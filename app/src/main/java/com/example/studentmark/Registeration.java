package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeration extends AppCompatActivity {
    private Button register;
    private EditText Uname,Umail,Usection,Upassword,UconPassword;
    private RegisterDatabase RD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        intwidget();
        calllistner();
    }

    private void calllistner() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail=Umail.getText().toString();
                String Name=Uname.getText().toString();
                int contact=Integer.parseInt(Usection.getText().toString());
                String pass=Upassword.getText().toString();
                String ConPass=UconPassword.getText().toString();
                if(isValidEmailId(Mail.trim())){
                    if(isValidPassword(pass)){

                        if(checkfield()){
                            if(pass.equals(ConPass)){
                                if(RD.AddStudent(Name,contact,Mail,pass,ConPass)){
                                    Toast.makeText(Registeration.this, "Registered Succussfully .", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(Registeration.this, "Registration Unsuccusful.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(Registeration.this, "Passwords not matching .", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else{
                        Toast.makeText(Registeration.this, "InValid Password .", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Registeration.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    private boolean checkfield(){

        if(Uname.getText().toString().matches("")){
            Toast.makeText(Registeration.this, "Please Enter User Name.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Usection.getText().toString().matches("")){
            Toast.makeText(Registeration.this, "Please Enter User Section.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Umail.getText().toString().matches("")){
            Toast.makeText(Registeration.this, "Please Enter User Mail.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Upassword.getText().toString().matches("")){
            Toast.makeText(Registeration.this, "Please Enter User Password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(UconPassword.getText().toString().matches("")){
            Toast.makeText(Registeration.this, "Please Enter User ConfirmPassword.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    private void intwidget() {
        register=findViewById(R.id.btnUserRegistration);
        Uname=findViewById(R.id.txtuserName);
        Umail=findViewById(R.id.txtUserEmail);
        Usection=findViewById(R.id.txtUserSection);
        Upassword=findViewById(R.id.txtUserPassword);
        UconPassword=findViewById(R.id.txtUserConPassword);
        RD=new RegisterDatabase(this);
    }
}