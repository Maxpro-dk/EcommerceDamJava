package com.example.e_commerce.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.e_commerce.ui.allcategory.CategoryFragment;
import com.example.e_commerce.ui.allproduct.AllProductFragment;
import com.example.e_commerce.ui.newproduct.NewProductFragment;


public class PageAdapter extends FragmentStateAdapter {


    public PageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new NewProductFragment();
            case 2:
                return new CategoryFragment();

        }
        return new AllProductFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
