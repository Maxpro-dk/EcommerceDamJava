package com.example.e_commerce.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.e_commerce.ui.allcategory.CategoryFragment;
import com.example.e_commerce.ui.allproduct.AllProductFragment;
import com.example.e_commerce.ui.mycategory.MyCategory;
import com.example.e_commerce.ui.mynewproduct.MyNewProduct;
import com.example.e_commerce.ui.myproducts.MyProducts;
import com.example.e_commerce.ui.newproduct.NewProductFragment;

public class MyPageAdapter extends FragmentStateAdapter {

    public MyPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new MyNewProduct();
            case 2:
                return new MyCategory();

        }
        return new MyProducts();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
