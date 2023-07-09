package com.example.fitnesstrackerapp.ui.dashboard;

import static com.example.fitnesstrackerapp.MainActivity.dbHelper;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnesstrackerapp.ExerciseRVAdapter;
import com.example.fitnesstrackerapp.ExerciseRVModel;
import com.example.fitnesstrackerapp.R;
import com.example.fitnesstrackerapp.databinding.FragmentDashboardBinding;
import com.example.fitnesstrackerapp.ui.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements ExerciseRVAdapter.ExerciseClickInterface {

    private FragmentDashboardBinding binding;

    private RecyclerView exerciseRV;
    private ArrayList<ExerciseRVModel> exerciseRVModelArrayList;
    private ExerciseRVAdapter exerciseRVAdapter;

    Context context = getContext();

    /*GridView gridView;
    static final String[] MOBILE_OS = new String[]{
            "Push Ups", "Sit Ups", "Crunches", "Plank", "Pull Ups"
    };*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (context != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            exerciseRV.setLayoutManager(layoutManager);
        }

        exerciseRV = getView().findViewById(R.id.ExerciseInfo);
        exerciseRVModelArrayList = new ArrayList<>();
        exerciseRVAdapter = new ExerciseRVAdapter(exerciseRVModelArrayList, this,this::onExerciseClick);
        // This may throw up an issue, I replaced "this" with "context"
        LinearLayoutManager manager = new LinearLayoutManager(context);
        exerciseRV.setLayoutManager(manager);
        exerciseRV.setAdapter(exerciseRVAdapter);
        addData();

        /*//Needs to happen after 'inflate', or there is no item on page to be found
        gridView = (GridView) root.findViewById(R.id.gridView1);
        gridView.setAdapter(new ImageAdapter(getContext(), MOBILE_OS));

        // Listens for image click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(
                        getContext(),
                        ((TextView) v.findViewById(R.id.grid_item_label))
                                .getText(), Toast.LENGTH_SHORT).show();

            }
        });*/

        //Data Test
        //List results = dbHelper.Read("Dashboard");
        //if (results.size() > 0) {
        //    String result = results.get(0).toString();
        //    dashboardViewModel.SetText("Dashboard: " + result);
        //}

        return root;
    }

    private void addData(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onExerciseClick(int position) {

    }
}