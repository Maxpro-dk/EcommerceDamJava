package com.example.e_commerce.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.e_commerce.adapter.PageAdapter;
import com.example.e_commerce.adapter.SliderAdapter;
import com.example.e_commerce.databinding.FragmentHomeBinding;
import com.example.e_commerce.entities.Publicity;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), publicitiesObserve);
        binding.viewPage.setSaveEnabled(false);
        FragmentManager manager = getChildFragmentManager();
        PageAdapter adapter = new PageAdapter(manager, getLifecycle());
        binding.viewPage.setAdapter(adapter);

        binding.viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tablayout.selectTab(binding.tablayout.getTabAt(position));
            }
        });

        binding.tablayout.setSelectedTabIndicator(null);
        binding.tablayout.addOnTabSelectedListener(this);
        return root;
    }

    Observer<ArrayList<Publicity>> publicitiesObserve = new Observer<ArrayList<Publicity>>() {
        @Override
        public void onChanged(ArrayList<Publicity> publicities) {
            SliderAdapter sliderAdapter = new SliderAdapter(publicities, getContext());
            binding.imageSlide.setSliderAdapter(sliderAdapter);
            binding.imageSlide.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
            binding.imageSlide.setSliderTransformAnimation(SliderAnimations.GATETRANSFORMATION);
            binding.imageSlide.startAutoCycle();
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        binding.viewPage.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}