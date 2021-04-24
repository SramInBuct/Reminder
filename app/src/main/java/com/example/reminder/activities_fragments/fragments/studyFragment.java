package com.example.reminder.activities_fragments.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.reminder.R;
import com.example.reminder.activities_fragments.studyHandle.ClockActivity;
import com.example.reminder.activities_fragments.studyHandle.StudyActivity;

public class studyFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_study, container, false);
        View clock = view.findViewById(R.id.neumorphCardViewClock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),ClockActivity.class);
                startActivity(in);
            }
        });
        View room = view.findViewById(R.id.neumorphCardViewStudy);
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), StudyActivity.class);
                in.putExtra("bundle",getArguments());
                startActivity(in);
            }
        });
        View more = view.findViewById(R.id.neumorphCardViewMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"敬请期待",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}