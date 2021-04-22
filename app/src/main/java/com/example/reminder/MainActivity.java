package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.ImageView;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db = LitePal.getDatabase();
        setContentView(R.layout.activity_main);
        navShow();
    }

    private NavController navController;
    void navShow(){
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        navController = Navigation.findNavController(this,R.id.fragment);
        MotionLayout mine = findViewById(R.id.motionLayoutMine);
        ImageView plan=findViewById(R.id.planButton);
        MotionLayout study = findViewById(R.id.motionLayoutStudy);
        MotionLayout classtable = findViewById(R.id.motionLayoutClasstable);
        MotionLayout chart = findViewById(R.id.motionLayoutChart);
        mine.setOnClickListener(v -> navController.navigate(R.id.mineFragment));
        plan.setOnClickListener(v -> navController.navigate(R.id.planFragment));
        classtable.setOnClickListener(v -> navController.navigate(R.id.classtableFragment));
        study.setOnClickListener(v -> navController.navigate(R.id.studyFragment));
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
    }
}