package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.ArrayList;

public class adminReport extends AppCompatActivity {
    private Spinner sp;
    private Button create;
    private DatePicker start,end;
    ArrayList<String> arrayList;
    RegisterDatabase Rd=new RegisterDatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_report);
        intwidget();
        arrayList=Rd.getIDs();
        ArrayAdapter adp=new ArrayAdapter(this,R.layout.spiinerlist,R.id.tvListItem,arrayList);
        //adp.setDropDownViewResource();
        sp.setAdapter(adp);
    }

    private void intwidget() {
        start=findViewById(R.id.etStrtDate);
        end=findViewById(R.id.etEndDate);
        create=findViewById(R.id.genPdf);
        sp=findViewById(R.id.spinner);

    }
}