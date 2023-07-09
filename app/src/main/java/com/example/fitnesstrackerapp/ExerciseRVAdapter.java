package com.example.fitnesstrackerapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fitnesstrackerapp.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

public class ExerciseRVAdapter extends RecyclerView.Adapter<ExerciseRVAdapter.ExerciseViewHolder> {

    private ArrayList<ExerciseRVModel> exerciseRVModelArrayList;
    private DashboardFragment context;
    private ExerciseClickInterface exerciseClickInterface;

    public ExerciseRVAdapter(ArrayList<ExerciseRVModel> exerciseRVModelArrayList, DashboardFragment context, ExerciseClickInterface exerciseClickInterface) {
        this.exerciseRVModelArrayList = exerciseRVModelArrayList;
        this.context = context;
        this.exerciseClickInterface = exerciseClickInterface;
    }

    @NonNull
    @Override
    public ExerciseRVAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_rv_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRVAdapter.ExerciseViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.exerciseTV.setText(exerciseRVModelArrayList.get(position).getExerciseName());
        holder.exerciseLAV.setAnimationFromUrl(exerciseRVModelArrayList.get(position).getImgURl());
        String time = String.valueOf(exerciseRVModelArrayList.get(position).getTime()) + " MIN";
        holder.timeTV.setText(time);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseClickInterface.onExerciseClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exerciseRVModelArrayList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{
        private TextView exerciseTV, timeTV;
        private LottieAnimationView exerciseLAV;
        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseTV = itemView.findViewById(R.id.idExerciseNameTV);
            timeTV = itemView.findViewById(R.id.idExerciseTimeTV);
            exerciseLAV = itemView.findViewById(R.id.idExerciseLAV);
        }
    }

    public interface ExerciseClickInterface{
        void onExerciseClick(int position);
    }
}
