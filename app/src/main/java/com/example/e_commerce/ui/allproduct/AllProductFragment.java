package com.example.e_commerce.ui.allproduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.e_commerce.R;
import com.example.e_commerce.adapter.AllProductAdapter;
import com.example.e_commerce.databinding.AllProductFragmentBinding;
import com.example.e_commerce.entities.Product;

import java.util.ArrayList;

public class AllProductFragment extends Fragment implements AllProductAdapter.OnProductClickListener {

    private AllProductViewModel mViewModel;
    private AllProductFragmentBinding binding;
    private AllProductAdapter.OnProductClickListener listener = this::OnProductClick;

    public static AllProductFragment newInstance() {
        return new AllProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AllProductViewModel.class);
        binding = AllProductFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getLiveData().observe(getViewLifecycleOwner(), productListObserve);
        return root;
    }

    Observer<ArrayList<Product>> productListObserve = new Observer<ArrayList<Product>>() {
        @Override
        public void onChanged(ArrayList<Product> products) {
            AllProductAdapter adapter = new AllProductAdapter(products, getContext(), listener);
            binding.recyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
            binding.recyclerview.setAdapter(adapter);
        }
    };


    @Override
    public void OnProductClick(int position, View view) {
        Product product = mViewModel.getLiveData().getValue().get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        NavHostFragment.findNavController(this).navigate(R.id.detailProductFragment, bundle);
        Toast.makeText(getContext(), "Toi" + product + " |||" + position, Toast.LENGTH_SHORT).show();
    }
}