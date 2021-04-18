package com.example.reminder.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reminder.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class planFragment extends Fragment {
    private static List<planItem> planList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plan , container, false);
        showListView(view);
        showDialog(view);
        return view;
    }

    private void showListView(View view) {
        planItemAdapter adapter=new planItemAdapter(getActivity(), R.layout.plan_item_layout,planList);
        ListView listView=(ListView) view.findViewById(R.id.plan_list_view);
        listView.setAdapter(adapter);
    }


    private void showDialog(View view) {
        ImageButton inputButton=view.findViewById(R.id.inputButton);
//        Dialog dialog=new Dialog(getActivity());
//        dialog.setContentView(R.layout.information_input_layout);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(".fragment.action.informationInputActivity");
                startActivity(in);
            }
        });
    }

    public static void AddPlan (String value) {
        planList.add(new planItem(value,R.drawable.ic_add_stroke));
    }
}