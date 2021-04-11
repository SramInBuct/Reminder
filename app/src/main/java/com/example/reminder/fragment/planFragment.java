package com.example.reminder.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reminder.R;

import java.util.ArrayList;
import java.util.List;

public class planFragment extends Fragment {
    private List<planItem> planList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_plan , container, false);

        initPlan();

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
//        Dialog dialog=new Dialog(getActivity());
//        dialog.setContentView(R.layout.information_input_layout);
        Button inputButton=view.findViewById(R.id.inputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(".fragment.action.informationInputActivity");
                startActivity(in);
            }
        });
    }


    private void initPlan() {
        planList.add(new planItem("apple",R.drawable.ic_add));
    }
}