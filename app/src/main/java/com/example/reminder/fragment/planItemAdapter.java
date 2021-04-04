package com.example.reminder.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.reminder.R;

import java.util.List;

public class planItemAdapter extends ArrayAdapter<planItem> {
    private final int resourceId;

    public planItemAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<planItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        planItem planItem=getItem(position);
        @SuppressLint("ViewHolder") View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView planImage=(ImageView)view.findViewById(R.id.planImageView);
        TextView planText=view.findViewById(R.id.planText);
        return view;
    }
}
