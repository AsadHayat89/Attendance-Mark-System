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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserEdit extends AppCompatActivity {
    private EditText Tname,Tmail,Tcontact,Tpass,TConPass;
    private Button Edit,back;
    RegisterDatabase Rd=new RegisterDatabase(this);
    Register re;
    String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        SharedPreferences sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        ID=sharedPreferences.getString("UserID", "defaultValue");
        re=Rd.getStudent(ID);

        intwidgets();
        loadvalues();
        calllistner();

    }

    private void calllistner() {
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Mail=Tmail.getText().toString();
                String Name=Tname.getText().toString();
                int contact=Integer.parseInt(Tcontact.getText().toString());
                String pass=Tpass.getText().toString();
                String ConPass=TConPass.getText().toString();
                if(isValidEmailId(Mail.trim())){
                    if(isValidPassword(pass)){

                        if(checkfield()){
                            if(pass.equals(ConPass)){
                                if(Rd.editStudent(ID,Name,contact,Mail,pass,ConPass)){
                                    Toast.makeText(UserEdit.this, "Edit Succussfully .", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(UserEdit.this, "Updation Unsuccusful.", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(UserEdit.this, "Passwords not matching .", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    else{
                        Toast.makeText(UserEdit.this, "InValid Password .", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(UserEdit.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UserEdit.this,UserDahboard.class);
                startActivity(i);
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

        if(Tname.getText().toString().matches("")){
            Toast.makeText(UserEdit.this, "Please Enter User Name.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Tcontact.getText().toString().matches("")){
            Toast.makeText(UserEdit.this, "Please Enter User Section.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Tmail.getText().toString().matches("")){
            Toast.makeText(UserEdit.this, "Please Enter User Mail.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Tpass.getText().toString().matches("")){
            Toast.makeText(UserEdit.this, "Please Enter User Password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(TConPass.getText().toString().matches("")){
            Toast.makeText(UserEdit.this, "Please Enter User ConfirmPassword.", Toast.LENGTH_SHORT).show();
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
    private void loadvalues() {
        Tname.setText(re.getName());
        Tmail.setText(re.getMail());
        String contact=String.valueOf(re.getContact());
        Tcontact.setText(contact);
        Tpass.setText(re.getPassword());
        TConPass.setText(re.getConPassword());
    }

    private void intwidgets() {
        Tname=findViewById(R.id.txtuserName2);
        Tmail=findViewById(R.id.txtUserEmail2);
        Tcontact=findViewById(R.id.txtUserSection2);
        Tpass=findViewById(R.id.txtUserPassword2);
        TConPass=findViewById(R.id.txtUserConPassword2);
        Edit=findViewById(R.id.btnedit);
        back=findViewById(R.id.btnback);
    }
}