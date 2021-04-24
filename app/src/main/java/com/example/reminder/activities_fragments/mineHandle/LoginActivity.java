package com.example.reminder.activities_fragments.mineHandle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reminder.R;
import com.example.reminder.entities.User;
import com.example.reminder.util.NetworkUtil;

import org.json.JSONException;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_login);
        TextView title = findViewById(R.id.ActionBarTitle);
        title.setText("");
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(v -> onBackPressed());

        TextView account=findViewById(R.id.editTextAccount);
        TextView password=findViewById(R.id.editTextTextPassword);
        Button ok=findViewById(R.id.button_login);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountS = account.getText().toString();
                String passwordS = password.getText().toString();
                if (accountS.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"请输入账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passwordS.isEmpty()) {
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }


                User user=new User(accountS,passwordS);
                Handler handler = new Handler(Looper.myLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        switch (msg.what){
                            case 0:
                                Log.d("Login","handleMessage: 网络错误");
                                Toast.makeText(LoginActivity.this,"网络服务请求失败",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
                                ArrayList<String> inform = (ArrayList<String>) msg.obj;
                                login.edit().putString("token",inform.get(0)).apply();
                                login.edit().putString("name",inform.get(1)).apply();
                                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                                Log.d("Login", "handleMessage: 登录成功");
                                View btn = findViewById(R.id.loginButton);
                                LoginActivity.this.finish();

                                break;
                            case 4:
                                Log.d("Login","handleMessage: 登陆失败");
                                Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
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
                Log.d("SharedPreferences","name->"+getSharedPreferences("login", Context.MODE_PRIVATE).getString("name","null"));
            }
        });

        View register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });
        }
}
