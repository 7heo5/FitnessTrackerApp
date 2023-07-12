package com.example.fitnesstrackerapp.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.fitnesstrackerapp.R;
import com.example.fitnesstrackerapp.SliderAdapter;
import com.example.fitnesstrackerapp.databinding.FragmentHomeBinding;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private String[] slides = {
            "The body achieves what the mind believes. \n– Napoleon Hill",
            "If you don’t find the time, if you don’t do the work, you don’t get the results. \n– Arnold Schwarzenegger",
            "The real workout starts when you want to stop. \n– Ronnie Coleman",
            "Take care of your body. It’s the only place you have to live. \n— Jim Rohn",
            "Once you learn to quit, it becomes a habit. \n– Vince Lombardi"};
    private ViewPager viewPager;
    private SliderAdapter sliderAdapter;
    private Timer timer;
    private int currentPage = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager = root.findViewById(R.id.viewPager);
        sliderAdapter = new SliderAdapter(getContext(), slides);
        viewPager.setAdapter(sliderAdapter);

        // Automatic sliding
        final Handler handler = new Handler(Looper.myLooper());
        final Runnable runnable = new Runnable() {
            public void run() {
                if (currentPage == slides.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 3000, 3000); // Delay in milliseconds before the first slide transition and interval between slides

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}