package com.example.reminder.activities_fragements.mineHandle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminder.R;
import com.example.reminder.entities.User;
import com.example.reminder.util.NetworkUtil;

import org.json.JSONException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView username=findViewById(R.id.registerUsername);
        TextView account=findViewById(R.id.registerAccount);
        TextView password=findViewById(R.id.registerPassword);
        TextView passwordConfirm=findViewById(R.id.registerPasswordConfirm);
        Button ok=findViewById(R.id.button_register);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().equals(passwordConfirm.getText().toString())){
                    Toast.makeText(RegisterActivity.this,"密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                User user=new User(username.getText().toString(),account.getText().toString(),"",password.getText().toString());
                Handler handler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 2:
                                SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                                login.edit().putString("token",(String)msg.obj).commit();
                                break;
                        }
                    }
                };
                try {
                    NetworkUtil.postRegister(handler,user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("SharedPreferences","token->"+getSharedPreferences("login", Context.MODE_PRIVATE).getString("token","null"));
            }
        });
    }
}