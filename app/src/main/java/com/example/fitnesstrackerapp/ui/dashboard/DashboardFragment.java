package com.example.fitnesstrackerapp.ui.dashboard;

import static com.example.fitnesstrackerapp.MainActivity.dbHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstrackerapp.ExerciseDetailActivity;
import com.example.fitnesstrackerapp.ExerciseRVAdapter;
import com.example.fitnesstrackerapp.ExerciseRVModel;
import com.example.fitnesstrackerapp.R;
import com.example.fitnesstrackerapp.databinding.FragmentDashboardBinding;
import com.example.fitnesstrackerapp.ui.ImageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DashboardFragment extends Fragment implements ExerciseRVAdapter.ExerciseClickInterface {

    private FragmentDashboardBinding binding;
    private RecyclerView exerciseRV;
    private ArrayList<ExerciseRVModel> exerciseRVModelArrayList;
    private ExerciseRVAdapter exerciseRVAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        try {
        exerciseRV = root.findViewById(R.id.recyclerView);
        exerciseRVModelArrayList = new ArrayList<>();
        exerciseRVAdapter = new ExerciseRVAdapter(exerciseRVModelArrayList, this,this::onExerciseClick);
        // This may throw up an issue, I replaced "this" with "getContext()"
            if (exerciseRV != null){
                LinearLayoutManager manager = new LinearLayoutManager(requireContext());
                exerciseRV.setLayoutManager(manager);
                exerciseRV.setAdapter(exerciseRVAdapter);
                addData();
            }
        } catch (NullPointerException exception){
            Log.e("DashboardFragment", "NullPointerException: " + exception.getMessage());
            exception.printStackTrace();
        }

        return root;
    }

    private void addData(){
        exerciseRVModelArrayList.add(new ExerciseRVModel("Side Plank", getResources().getString(R.string.side_plank), "https://assets8.lottiefiles.com/packages/lf20_EOXjkv.json", 20, 10));
        exerciseRVModelArrayList.add(new ExerciseRVModel("Lunges", getResources().getString(R.string.lunges), "https://assets6.lottiefiles.com/packages/lf20_XbVCR4.json", 30, 10));
        exerciseRVModelArrayList.add(new ExerciseRVModel("Push Ups", getResources().getString(R.string.push_ups), "https://assets7.lottiefiles.com/packages/lf20_cswADz.json", 40, 10));
        exerciseRVModelArrayList.add(new ExerciseRVModel("Abdominal Crunches", getResources().getString(R.string.side_plank), "https://assets7.lottiefiles.com/packages/lf20_Ajcy3F.json", 30, 20));
        exerciseRVModelArrayList.add(new ExerciseRVModel("Tricep Dips", getResources().getString(R.string.tricep_dips), "https://assets1.lottiefiles.com/packages/lf20_VMnqvY.json", 10, 5));
        exerciseRVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onExerciseClick(int position) {
        //This could also throw up some context related issues, changed "DashboardFragment.this" to "getContext()"
        Intent i = new Intent(getContext(), ExerciseDetailActivity.class);
        i.putExtra("exerciseName", exerciseRVModelArrayList.get(position).getExerciseName());
        i.putExtra("imgUrl", exerciseRVModelArrayList.get(position).getImgURl());
        i.putExtra("time", exerciseRVModelArrayList.get(position).getTime());
        i.putExtra("calories", exerciseRVModelArrayList.get(position).getCalories());
        i.putExtra("desc", exerciseRVModelArrayList.get(position).getExerciseDescription());
        startActivity(i);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}