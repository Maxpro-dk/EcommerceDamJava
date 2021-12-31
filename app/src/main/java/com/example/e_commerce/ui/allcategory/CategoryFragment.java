package com.example.e_commerce.ui.allcategory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.activities.MainActivity;
import com.example.e_commerce.adapter.MyCategoryAdapter;
import com.example.e_commerce.databinding.AllProductFragmentBinding;
import com.example.e_commerce.databinding.MyProductsFragmentBinding;
import com.example.e_commerce.entities.Category;
import com.example.e_commerce.ui.allproduct.AllProductViewModel;
import com.example.e_commerce.ui.mycategory.MyCategoryViewModel;
import com.example.e_commerce.ui.myproducts.MyProductsViewModel;

import java.util.ArrayList;

public class CategoryFragment extends Fragment implements MyCategoryAdapter.OnCategoryListener {
    private CategoryViewModel mViewModel;
    private AllProductFragmentBinding binding;
    MyCategoryAdapter.OnCategoryListener  categoryListener= this;
    MyCategoryAdapter adapter;




    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        binding = AllProductFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getLiveData().observe(getViewLifecycleOwner(),categoryListObserve );
        binding.recyclerview.addOnScrollListener(onScrollListener);
        return root;
    }


    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
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
    };



    Observer<ArrayList<Category>> categoryListObserve = new Observer<ArrayList<Category>>() {
        @Override
        public void onChanged(ArrayList<Category> categories) {
            adapter = new MyCategoryAdapter(categories, getContext(),categoryListener );
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerview.setAdapter(adapter);
        }
    };

    @Override
    public void onStart() {
        MainActivity.showBottomBar();
        super.onStart();

    }

    @Override
    public void onCategoryClick(int position, View view) {
        Category category=mViewModel.getLiveData().getValue().get(position);
        if (category.getId()=="") AllProductViewModel.category=null;
        else AllProductViewModel.category=category;
        Navigation.findNavController(view).navigate(R.id.navigation_home);
    }

    @Override
    public void onNameClick(int position, View view) {

    }
}