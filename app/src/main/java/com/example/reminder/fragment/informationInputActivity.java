package com.example.reminder.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;


public class informationInputActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_input_layout);
        TextView inputTitle=findViewById(R.id.inputTitle);
        TextView inputDetails=findViewById(R.id.inputDetails);
        Button btn_ok=findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title= String.valueOf(inputTitle.getText());
                String details=String.valueOf(inputDetails.getText());
                planFragment.AddPlan(title);
                informationInputActivity.this.finish();
            }
        });
        Button btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informationInputActivity.this.finish();
            }
        });

    }
}
