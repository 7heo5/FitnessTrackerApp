package com.example.fitnesstrackerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class ExerciseDetailActivity extends AppCompatActivity {

    private TextView exerciseNameText, caloriesText, timeText, descText;
    private LottieAnimationView exerciseAnimation;
    private String exerciseName, desc, imgUrl;
    private int calories, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        exerciseNameText = findViewById(R.id.idTVExerciseName);
        caloriesText = findViewById(R.id.idTVCalories);
        timeText = findViewById(R.id.idTVTime);
        descText = findViewById(R.id.idTVDescription);
        exerciseAnimation = findViewById(R.id.idLAVExercise);

        exerciseName = getIntent().getStringExtra("exerciseName");
        desc = getIntent().getStringExtra("desc");
        imgUrl = getIntent().getStringExtra("imgUrl");
        calories = getIntent().getIntExtra("calories", 0);
        time = getIntent().getIntExtra("time", 0);

        exerciseNameText.setText(exerciseName);
        caloriesText.setText("Calories : " + calories);
        timeText.setText("Time : " + time + " Min");
        descText.setText(desc);
        exerciseAnimation.setAnimationFromUrl(imgUrl);
    }
}