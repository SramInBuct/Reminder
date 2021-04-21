package com.example.reminder.activities_fragements.planHandle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.reminder.R;
import com.example.reminder.activities_fragements.fragements.planFragment;
import com.example.reminder.entities.Event;
import com.example.reminder.util.EventDao;

import java.util.List;

public class planItemAdapter extends ArrayAdapter<Event> {
    private final int resourceId;

    public planItemAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Event> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }



    private void showCoverDialog(Event planItem){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("确定要删除这项日程吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EventDao.delete(planItem.getId());
                planFragment.remove(planItem);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
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

        ImageView del=(ImageView)view.findViewById(R.id.deleteButton);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCoverDialog(planItem);
            }
        });

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
