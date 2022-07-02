package com.example.studentmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAttendence extends AppCompatActivity {
    private TextView Name,date,status;
    RegisterDatabase rd=new RegisterDatabase(this);
    ArrayList<attendance> RA=new ArrayList<>();
    String Id;
    private TableLayout tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendence);

        tb=findViewById(R.id.tablelayout);

       SharedPreferences sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        Id=sharedPreferences.getString("UserID", "defaultValue");
        RA=rd.getAllStudentAttendance(Id);
        for(int i=0;i<RA.size();i++){
            TableRow view=(TableRow)getLayoutInflater().inflate(R.layout.table_tems,tb, false);//here NPE
            String date1=RA.get(i).getDay()+"/"+RA.get(i).getMonth()+"/"+RA.get(i).getYear();
            Name=(TextView) view.findViewById(R.id.txttableName);
            date=(TextView) view.findViewById(R.id.txttabledate);
            status=(TextView) view.findViewById(R.id.txttablestatus);
            String ID=RA.get(i).getStudentId();
            String St="";
            if(RA.get(i).getStatus()==1){
                St="Present";
            }
            else if(RA.get(i).getStatus()==2){
                St="Absent";
            }

            Name.setText(ID);
            //Toast.makeText(ViewAttendence.this,date1,Toast.LENGTH_SHORT).show();
            date.setText(date1);
            status.setText(St);
            tb.addView(view);
        }

    }
}