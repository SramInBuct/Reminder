package com.example.reminder.planHandle;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;
import com.example.reminder.entities.Event;
import com.example.reminder.fragment.planFragment;
import com.example.reminder.util.EventDao;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;


public class informActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_layout);
        TextView inputTitle=findViewById(R.id.inputTitle);
        TextView inputDetails=findViewById(R.id.inputDetails);
        Button btn_ok=findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String title= String.valueOf(inputTitle.getText());
                String details=String.valueOf(inputDetails.getText());
                Date date=new Date();
                Event event=new Event();
                event.setName(title);
                event.setDescribe(details);
                event.setBeginDate(date);
                EventDao.insert(event);
                planFragment.addList(title,details,date,R.drawable.ic_mine);
                informActivity.this.finish();
            }
        });
        Button btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informActivity.this.finish();
            }
        });

    }
}
