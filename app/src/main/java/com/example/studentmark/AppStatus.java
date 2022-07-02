package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AppStatus extends AppCompatActivity {
    private Button add;
    private EditText body,head;
    RegisterDatabase aPD=new RegisterDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_status);
        intwidget();
        calllistner();

    }

    private void calllistner() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head1=head.getText().toString();
                String b=body.getText().toString();
                if(aPD.addApllication("1",head1,b,0)){
                    Toast.makeText(AppStatus.this,"Send",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AppStatus.this,"Not Send",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void intwidget() {
        add=findViewById(R.id.btnsend);
        body=findViewById(R.id.txtbody);
        head=findViewById(R.id.txttopic);
    }
}