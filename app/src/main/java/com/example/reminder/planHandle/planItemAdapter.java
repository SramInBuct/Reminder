package com.example.reminder.planHandle;

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
import com.example.reminder.entities.Event;

import java.util.List;

public class planItemAdapter extends ArrayAdapter<Event> {
    private final int resourceId;

    public planItemAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Event> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event planItem=getItem(position);
        @SuppressLint("ViewHolder") View view;
        if(convertView==null){
            view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view=convertView;
        }
        ImageView planImage=(ImageView)view.findViewById(R.id.planImageView);
        planImage.setImageResource(R.drawable.ic_mine);
        TextView planText=view.findViewById(R.id.planText);
        planText.setText(planItem.getName());
        TextView planDetails=view.findViewById(R.id.detailText);
        planDetails.setText(planItem.getDescribe());
        TextView dateText=view.findViewById(R.id.timeText);
        dateText.setText(planItem.getBeginDate().toString());
        return view;
    }
}
