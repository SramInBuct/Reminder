package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.reminder.activities_fragments.loadActivity.LoadActivity;
import com.example.reminder.activities_fragments.mineHandle.LoginActivity;

import org.litepal.LitePal;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Vibrator vibrator;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db = LitePal.getDatabase();
        setContentView(R.layout.activity_main);
        navShow(savedInstanceState);
        if (savedInstanceState!=null) {
            savedInstanceState.putBundle("bundle",bundle);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (bundle.getString("token") == null) {
            bundle.putString("token",getSharedPreferences("login",Context.MODE_PRIVATE).getString("token",null));
            bundle.putString("name",getSharedPreferences("login",Context.MODE_PRIVATE).getString("name",null));
        }
    }

    private NavController navController;
    void navShow(Bundle savedInstanceState){
        bundle = savedInstanceState==null?null:savedInstanceState.getBundle("bundle");
        if (bundle == null)
            bundle = new Bundle();

        String token = getSharedPreferences("login",Context.MODE_PRIVATE).getString("token",null);
        bundle.putString("token",token);

        bundle.putString("name",getSharedPreferences("login",Context.MODE_PRIVATE).getString("name",null));

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        navController = Navigation.findNavController(this,R.id.fragment);
        MotionLayout mine = findViewById(R.id.motionLayoutMine);
        ImageView plan=findViewById(R.id.planButton);
        MotionLayout study = findViewById(R.id.motionLayoutStudy);
        MotionLayout classtable = findViewById(R.id.motionLayoutClasstable);
        MotionLayout chart = findViewById(R.id.motionLayoutChart);
        mine.setOnClickListener(v -> navController.navigate(R.id.mineFragment,bundle));
        plan.setOnClickListener(v -> navController.navigate(R.id.planFragment));
        classtable.setOnClickListener(v -> navController.navigate(R.id.classtableFragment));
        study.setOnClickListener(v -> {
            navController.navigate(R.id.studyFragment, bundle);
        });
        chart.setOnClickListener(v -> navController.navigate(R.id.chartFragment));
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            vibrator.vibrate(60);
            controller.popBackStack();
            mine.setProgress(0f);
            study.setProgress(0f);
            classtable.setProgress(0f);
            chart.setProgress(0f);

            if (destination.getId()==R.id.mineFragment) {
                mine.transitionToEnd();
            }
            else if (destination.getId()==R.id.studyFragment) {
                study.transitionToEnd();
            }
            else if (destination.getId()==R.id.classtableFragment) {
                classtable.transitionToEnd();
            }
            else if (destination.getId()==R.id.chartFragment) {
                chart.transitionToEnd();
            }
        });
        if (token == null) {
            Intent in = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(in);
        }
    }
}