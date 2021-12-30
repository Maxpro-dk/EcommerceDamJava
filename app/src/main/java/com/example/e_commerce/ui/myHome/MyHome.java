package com.example.e_commerce.ui.myHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.e_commerce.R;
import com.example.e_commerce.adapter.MyPageAdapter;
import com.example.e_commerce.adapter.PageAdapter;
import com.example.e_commerce.databinding.MyHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class MyHome extends Fragment implements TabLayout.OnTabSelectedListener {
    MyHomeBinding binding ;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= MyHomeBinding.inflate(inflater,container,false);
        View view =binding.getRoot();
        binding.viewPage.setSaveEnabled(false);
        FragmentManager manager = getChildFragmentManager();
        MyPageAdapter adapter = new MyPageAdapter(manager, getLifecycle());
        binding.viewPage.setAdapter(adapter);

        binding.viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tablayout.selectTab(binding.tablayout.getTabAt(position));
            }
        });

        binding.tablayout.setSelectedTabIndicator(null);
        binding.tablayout.addOnTabSelectedListener(this);


        return view;
    }


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