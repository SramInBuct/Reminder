package com.example.draft2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.fragment);
        MotionLayout mine = findViewById(R.id.motionLayoutMine);
//        MotionLayout plan = findViewById(R.id.motionLayoutPlan);
        Button plan=findViewById(R.id.button);
        MotionLayout study = findViewById(R.id.motionLayoutStudy);
        MotionLayout classtable = findViewById(R.id.motionLayoutClasstable);
        MotionLayout chart = findViewById(R.id.motionLayoutChart);
        mine.setOnClickListener(v -> navController.navigate(R.id.mineFragment));
        plan.setOnClickListener(v -> navController.navigate(R.id.planFragment));
        classtable.setOnClickListener(v -> navController.navigate(R.id.classtableFragment));
        study.setOnClickListener(v -> navController.navigate(R.id.studyFragment));
        chart.setOnClickListener(v -> navController.navigate(R.id.chartFragment));
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            controller.popBackStack();
            mine.setProgress(0f);
//            plan.setProgress(0f);
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
//            else if (destination.getId()==R.id.planFragment) plan.transitionToEnd();
            else if (destination.getId()==R.id.chartFragment) {
                chart.transitionToEnd();
            }
        });
    }
}