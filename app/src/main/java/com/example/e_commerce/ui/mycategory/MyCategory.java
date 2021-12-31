package com.example.e_commerce.ui.mycategory;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.adapter.MyCategoryAdapter;
import com.example.e_commerce.adapter.MyProductsAdapter;
import com.example.e_commerce.databinding.MyProductsFragmentBinding;
import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.ui.myproducts.MyProductsViewModel;

import java.util.ArrayList;

public class MyCategory extends Fragment implements MyCategoryAdapter.OnCategoryListener {

    private MyCategoryViewModel mViewModel;
    private MyProductsFragmentBinding binding;
    MyCategoryAdapter.OnCategoryListener  categoryListener= this;
    MyCategoryAdapter adapter;







    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(MyCategoryViewModel.class);
        binding = MyProductsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getLiveData().observe(getViewLifecycleOwner(), productListObserve);
        binding.recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (!mViewModel.getScrolled()) {
                    int n = mViewModel.getLiveData().getValue().size();
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == n-1) {
                        mViewModel.update();
                        mViewModel.setScrolled(true);



                    }
                }


            }
        });

        return root;


    }



    Observer<ArrayList<Category>> productListObserve = new Observer<ArrayList<Category>>() {
        @Override
        public void onChanged(ArrayList<Category> categories) {
            adapter = new MyCategoryAdapter(categories, getContext(),categoryListener );
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerview.setAdapter(adapter);
        }
    };



    @Override
    public void onCategoryClick(int position, View view) {
        Category category=mViewModel.getLiveData().getValue().get(position);
        if (category.getId()=="")MyProductsViewModel.category=null;
        else MyProductsViewModel.category=category;

        Navigation.findNavController(view).navigate(R.id.mes_produits_Fragment);
    }

    @Override
    public void onNameClick(int position, View view) {

    }

}