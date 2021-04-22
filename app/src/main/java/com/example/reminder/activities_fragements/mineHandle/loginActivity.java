package com.example.reminder.activities_fragements.mineHandle;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;
import com.example.reminder.entities.User;
import com.example.reminder.util.NetworkUtil;

import org.json.JSONException;

public class loginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_login);
        TextView account=findViewById(R.id.editTextAccount);
        TextView password=findViewById(R.id.editTextTextPassword);
        Button ok=findViewById(R.id.button_login);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(account.getText().toString(),password.getText().toString());
                Handler handler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 0:
                                Log.d("Login","handleMessage: 网络错误");
                                Toast.makeText(loginActivity.this,"网络服务请求失败",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                                login.edit().putString("token",(String)msg.obj).commit();
                                Log.d("Login", "handleMessage: 登录成功");

                                break;
                            case 4:
                                Log.d("Login","handleMessage: 登陆失败");
                                Toast.makeText(loginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                };
                try {
                    NetworkUtil.postLogin(handler,user);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("SharedPreferences","token->"+getSharedPreferences("login", Context.MODE_PRIVATE).getString("token","null"));
            }
        });
        }
}
