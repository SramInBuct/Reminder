package com.example.reminder.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.reminder.R;
import com.example.reminder.entities.Event;
import com.example.reminder.planHandle.informActivity;
import com.example.reminder.planHandle.planItemAdapter;
import com.example.reminder.util.EventDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class planFragment extends Fragment {
    private static List<Event> planList=new ArrayList<>();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_plan , container, false);
        loadDate();
        showListView(view);
        showDialog(view);
        return view;
    }

    private void showListView(View view) {
        planItemAdapter adapter=new planItemAdapter(getActivity(), R.layout.item_plan,planList);
        ListView listView=(ListView) view.findViewById(R.id.plan_list_view);
        listView.setAdapter(adapter);
    }

    public void loadDate(){
        planList=EventDao.findAll();
    }

    private void showDialog(View view) {
        ImageButton inputButton=view.findViewById(R.id.inputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), informActivity.class);
                startActivity(intent);
            }
        });
    }


    public static void addList(Event event){
        planList.add(event);
    }

    @Override
    public void onResume() {
        super.onResume();
        showListView(view);
    }

}