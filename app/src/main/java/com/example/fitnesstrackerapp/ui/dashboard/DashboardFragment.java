package com.example.fitnesstrackerapp.ui.dashboard;

import static com.example.fitnesstrackerapp.MainActivity.dbHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnesstrackerapp.R;
import com.example.fitnesstrackerapp.databinding.FragmentDashboardBinding;
import com.example.fitnesstrackerapp.ui.ImageAdapter;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    GridView gridView;
    static final String[] MOBILE_OS = new String[]{
            "Push Ups", "Sit Ups", "Crunches", "Plank", "Pull Ups"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Needs to happen after 'inflate', or there is no item on page to be found
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
        });

        //Data Test
        //List results = dbHelper.Read("Dashboard");
        //if (results.size() > 0) {
        //    String result = results.get(0).toString();
        //    dashboardViewModel.SetText("Dashboard: " + result);
        //}

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}