package com.example.reminder.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
        planItemAdapter adapter=new planItemAdapter(getActivity(), R.layout.plan_item_layout,planList);
        ListView listView=(ListView) view.findViewById(R.id.plan_list_view);
        listView.setAdapter(adapter);

        Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.information_input_layout);

        Button inputButton=view.findViewById(R.id.inputButton);
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        return view;
    }

    private void initPlan() {
        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

        planList.add(new planItem("apple",R.drawable.ic_add));

    }
}