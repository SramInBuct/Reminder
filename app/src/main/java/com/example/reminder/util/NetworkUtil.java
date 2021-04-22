package com.example.reminder.util;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.SharedMemory;
import android.util.Log;

import com.example.reminder.entities.User;
import com.example.reminder.entities.WeatherDays;
import com.example.reminder.entities.WeatherIndices;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetworkUtil {

    public static final String BASE_URL="http://47.117.116.187";
    public static final String WEATHER=":8777";
    private static final String TAG ="OkHttpClient" ;
    private static String date="2021-04-18";
    private static String local ="101010100";
    private static String type="1";

    /**
     * GET方法
     */
    public static void GetWeatherDays(Handler handler){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url(BASE_URL+WEATHER+"/weather/days?loc="+local+"&date="+date)
                .build();
        //client创建任务
        Call call = client.newCall(request);
        //异步

        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"onFailure-->"+e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.d(TAG,"code-->"+code);
                if (code==200) {
                    ResponseBody responseBody = response.body();
                    String result = responseBody.string();
                    Gson gson = new Gson();
                    try {
                        JSONObject object = new JSONObject(result);
                        JSONObject o = object.getJSONArray("msg").getJSONObject(0);
                        String s = o.toString();
                        WeatherDays weatherDays = gson.fromJson(s, WeatherDays.class);
                        Message message = new Message();
                        message.what=2;
                        message.obj=weatherDays;
                        Log.d(TAG, "responseBody-->" + weatherDays.toString());
                        handler.sendMessage(message);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Message message = new Message();
                    message.what=4;
                    message.obj=0;
                    handler.sendMessage(message);
                }
            }
        });

    }

    public static void GetWeatherIndices(Handler handler){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .get()
                .url(BASE_URL+WEATHER+"/weather/indices?loc="+local+"&date="+date+"&type="+type)
                .build();
        //client创建任务
        Call call = client.newCall(request);
        //异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG,"onFailure-->"+e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.d(TAG,"code-->"+code);
                if (code==200) {
                    ResponseBody responseBody = response.body();
                    String result = responseBody.string();
                    Gson gson = new Gson();
                    try {
                        JSONObject object = new JSONObject(result);
                        JSONObject o = object.getJSONArray("msg").getJSONObject(0);
                        String s = o.toString();
                        WeatherIndices weatherIndices = gson.fromJson(s, WeatherIndices.class);
                        Message message = new Message();
                        message.what=2;
                        message.obj=weatherIndices;
                        handler.sendMessage(message);
                        Log.d(TAG, "responseBody-->" + weatherIndices.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Message message = new Message();
                    message.what=4;
                    message.obj=0;
                    handler.sendMessage(message);
                    Log.d(TAG, "onResponse--> Error");
                }
            }
        });
    }


    /**
     * POST方法
     */
    public static void postLogin(Handler handler,User user) throws JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        JSONObject obj = new JSONObject();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        obj.put("mail",user.getMail());
        obj.put("uuid",user.getUuid());
        obj.put("password",user.getPassword());
        RequestBody body =  RequestBody.create(JSON,String.valueOf(obj));

        Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.58.209:8668/api/user/login")
                .build();
        Boolean tag;
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,"onFailure-->"+e.toString());
                Message message = Message.obtain();
                message.what = 0;
                message.obj = e.getMessage();
                handler.sendMessage(message);
                Log.d(TAG, "onResponse: " + message.obj.toString());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Message message = Message.obtain();
                message.what = 2;

                String result = response.body().string();//string不能调用两次 被调用一次就关闭了，这里调用两次会报异常
                String token = null;
                String status = null;
                Object msg;
                JSONObject js;
                try {
                    js = new JSONObject(result);
                    status = js.getString("status");
                    Log.d(TAG,"status->"+status);
                    if(status.equals("FAIL")) {
                        message.what=4;
                        msg = js.getString("msg");
                        message.obj = (String)msg;
                        Log.d(TAG,"onResponse->msg: "+message.obj.toString());
                    }
                    else{
                        msg = js.getJSONObject("msg");
                        token =((JSONObject)msg).getString("token");
                        message.obj = token;
                        Log.d(TAG, "onResponse->token: " + message.obj.toString());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(message);

            }
        });

    }
    public static void postRegister(Handler handler,User user) throws JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        JSONObject obj = new JSONObject();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        obj.put("name",user.getName());
        obj.put("mail",user.getMail());
        obj.put("uuid",user.getUuid());
        obj.put("password",user.getPassword());
        RequestBody body =  RequestBody.create(JSON,String.valueOf(obj));

        Request request = new Request.Builder()
                .post(body)
                .url("http://192.168.58.209:8668/api/user/register")
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,"onFailure-->"+e.toString());
                Message message = Message.obtain();
                message.what = 0;
                message.obj = e.getMessage();
                handler.sendMessage(message);
                Log.d(TAG, "onResponse: " + message.obj.toString());
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                Message message = Message.obtain();
                message.what = 2;
                String result = response.body().string();//string不能调用两次 被调用一次就关闭了，这里调用两次会报异常
                String token = null;
                String status = null;
                Object msg;
                JSONObject js;
                try {
                    js = new JSONObject(result);
                    status = js.getString("status");
                    Log.d(TAG,"status->"+status);
                    if(status.equals("FAIL")) {
                        message.what=4;
                    }
                    msg = js.getString("msg");
                    message.obj = (String)msg;
                    Log.d(TAG,"onResponse->msg: "+message.obj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(message);

            }
        });
    }

}
