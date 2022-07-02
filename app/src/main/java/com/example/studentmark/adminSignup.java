package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminSignup extends AppCompatActivity {
    private Button register;
    private EditText Aname,Amail,Asection,Apassword,AconPassword;
    private adminDatabase RD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        intwigert();
        calllistner();
    }

    private void calllistner() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail=Amail.getText().toString();
                String Name=Aname.getText().toString();
                int contact=Integer.parseInt(Asection.getText().toString());
                String pass=Apassword.getText().toString();
                String ConPass=AconPassword.getText().toString();
                if(isValidEmailId(Mail.trim())){
                    if(isValidPassword(pass)){

                        if(checkfield()){
                            if(pass.equals(ConPass)){
                                if(RD.AddAdmin(Name,contact,Mail,pass,ConPass)){
                                    Toast.makeText(adminSignup.this, "Registered Succussfully .", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(adminSignup.this, "Registration Unsuccusful.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(adminSignup.this, "Passwords not matching .", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else{
                        Toast.makeText(adminSignup.this, "InValid Password .", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(adminSignup.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
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

        if(Aname.getText().toString().matches("")){
            Toast.makeText(adminSignup.this, "Please Enter User Name.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Asection.getText().toString().matches("")){
            Toast.makeText(adminSignup.this, "Please Enter User Section.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Amail.getText().toString().matches("")){
            Toast.makeText(adminSignup.this, "Please Enter User Mail.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Apassword.getText().toString().matches("")){
            Toast.makeText(adminSignup.this, "Please Enter User Password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(AconPassword.getText().toString().matches("")){
            Toast.makeText(adminSignup.this, "Please Enter User ConfirmPassword.", Toast.LENGTH_SHORT).show();
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
    private void intwigert() {
        register=findViewById(R.id.btnadminRegistration2);
        Aname=findViewById(R.id.txtuserName3);
        Amail=findViewById(R.id.txtUserEmail3);
        Asection=findViewById(R.id.txtUserSection3);
        Apassword=findViewById(R.id.txtUserPassword3);
        AconPassword=findViewById(R.id.txtUserConPassword3);
        RD=new adminDatabase(this);
    }
}