package com.example.reminder.activities_fragments.planHandle;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;
import com.example.reminder.activities_fragments.fragments.planFragment;
import com.example.reminder.entities.Event;
import com.example.reminder.util.AlertUtil;
import com.example.reminder.util.EventDao;

import java.util.Calendar;
import java.util.Date;


public class InformActivity extends AppCompatActivity {
    Date beginDateInput=new Date();
    Date endDateInput=new Date();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_input_plan);
        inputInform();
        inputDate();
    }

    private void inputDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final StringBuffer date_default = new StringBuffer();
        final StringBuffer time_default = new StringBuffer();
        date_default.append(year + "年" + (month+1) + "月" + day +"日");
        time_default.append(hour).append("点").append(minute).append("分");

        TextView dateTextBegin=(TextView) findViewById(R.id.editTextBeginDate);
        dateTextBegin.setText(date_default);
        dateTextBegin.setOnClickListener(v -> showDatePick((TextView) v));

        TextView dateTextEnd=(TextView) findViewById(R.id.editTextEndDate);
        dateTextEnd.setText(date_default);
        dateTextEnd.setOnClickListener(v -> showDatePick((TextView) v));

        TextView timeTextBegin=(TextView) findViewById(R.id.editTextBeginTime);
        timeTextBegin.setText(time_default);
        timeTextBegin.setOnClickListener(v -> showTimePick((TextView) v,1));

        TextView timeTextEnd=(TextView) findViewById(R.id.editTextEndTime);
        timeTextEnd.setText(time_default);
        timeTextEnd.setOnClickListener(v -> showTimePick((TextView) v,2));
    }

    private void showDatePick(final TextView dateText){
        final StringBuffer date = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //实例化DatePickerDialog对象
        DatePickerDialog datePickerDialog = new DatePickerDialog(InformActivity.this, new DatePickerDialog.OnDateSetListener() {
            //选择完日期后会调用该回调函数
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if(dateText.getId()==R.id.editTextBeginDate){
                    beginDateInput.setYear(year);
                    beginDateInput.setMonth(monthOfYear);
                    beginDateInput.setDate(dayOfMonth+1);
                }else if(dateText.getId()==R.id.editTextEndDate) {
                    endDateInput.setYear(year);
                    endDateInput.setMonth(monthOfYear);
                    endDateInput.setDate(dayOfMonth+1);
                }
                //因为monthOfYear会比实际月份少一月所以这边要加1
                date.append(year + "年" + (monthOfYear+1) + "月" + dayOfMonth +"日");
                dateText.setText(date);
            }
        }, year, month, day);
        //弹出选择日期对话框
        datePickerDialog.show();
    }
    @SuppressLint("NonConstantResourceId")
    private Integer getIcon(){
        RadioGroup rdp=findViewById(R.id.radioGroup);

        int id = rdp.getCheckedRadioButtonId();
        switch (id){
            case R.id.radioButton: return R.drawable.ic_exercise;
            case R.id.radioButton2: return R.drawable.ic_rest;
            case R.id.radioButton3: return R.drawable.ic_studying;
            case R.id.radioButton4: return R.drawable.ic_work;
        }
        return R.drawable.ic_mine;
    }
    private void showTimePick(final TextView timeText,int id) {
        final StringBuffer time = new StringBuffer();
        //获取Calendar对象，用于获取当前时间
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        //实例化TimePickerDialog对象
        final TimePickerDialog timePickerDialog = new TimePickerDialog(InformActivity.this, new TimePickerDialog.OnTimeSetListener() {
            //选择完时间后会调用该回调函数
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if(id==1){
                    beginDateInput.setHours(hourOfDay);
                    beginDateInput.setMinutes(minute);
                }else if(id==2){
                    endDateInput.setHours(hourOfDay);
                    endDateInput.setMinutes(minute);
                }

                time.append(hourOfDay + "点" + minute + "分");
                //设置TextView显示最终选择的时间
                timeText.setText(time);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }
    private void inputInform() {
        TextView inputTitle=findViewById(R.id.inputTitle);
        TextView inputDetails=findViewById(R.id.inputDetails);
        Button btn_ok=findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(v -> {
            String title= String.valueOf(inputTitle.getText());
            if(title.length()==0){
                Toast.makeText(InformActivity.this,"请输入计划名称",Toast.LENGTH_SHORT).show();
                return;
            }
            String details=String.valueOf(inputDetails.getText());
            Event event=new Event();
            event.setBeginDate(beginDateInput);
            event.setBeginTime(beginDateInput);
            event.setEndDate(endDateInput);
            event.setEndTime(endDateInput);
            event.setName(title);
            event.setDescribe(details);
            event.setIcon_id(getIcon());
            EventDao.insert(event);
            planFragment.addList(event);
            //添加事件
            AlertUtil.Build(this).setAlarm(event);
            InformActivity.this.finish();
        });
        Button btn_cancel=findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(v -> InformActivity.this.finish());
    }
}
