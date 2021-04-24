package com.example.reminder.activities_fragments.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.reminder.R;
import com.example.reminder.activities_fragments.mineHandle.RegisterActivity;
import com.example.reminder.activities_fragments.mineHandle.LoginActivity;

public class mineFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine, container, false);

        View login = view.findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
            }
        });

//        View register=view.findViewById(R.id.register);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in=new Intent(getActivity(), RegisterActivity.class);
//                startActivity(in);
//            }
//        });
        return view;
    }
}