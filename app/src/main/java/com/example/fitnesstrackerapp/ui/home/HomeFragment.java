package com.example.fitnesstrackerapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitnesstrackerapp.R;
import com.example.fitnesstrackerapp.databinding.FragmentHomeBinding;
import com.example.fitnesstrackerapp.ui.ImageAdapter;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    GridView gridView;
    static final String[] MOBILE_OS = new String[]{
            "Hitting", "Hungry", "Bullying", "Shouting", "Torture"
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
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

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}