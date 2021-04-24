package com.example.reminder.activities_fragments.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.reminder.R;
import com.example.reminder.activities_fragments.mineHandle.MineInfoActivity;
import com.example.reminder.activities_fragments.mineHandle.RegisterActivity;
import com.example.reminder.activities_fragments.mineHandle.LoginActivity;

public class mineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        Button login = view.findViewById(R.id.loginButton);
        TextView textView=view.findViewById(R.id.headText);
        String token = getArguments().getString("token");
        String name  = getArguments().getString("name");
        if(token != null && !token.isEmpty()){
            textView.setText(name);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(token == null || token.isEmpty()) {
                    Intent in = new Intent(getActivity(), LoginActivity.class);
                    startActivity(in);
                }else{
                    Intent in=new Intent(getActivity(), MineInfoActivity.class);
                    startActivity(in);
                }
            }
        });

        return view;
    }
}