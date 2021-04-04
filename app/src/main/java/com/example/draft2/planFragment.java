package com.example.draft2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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