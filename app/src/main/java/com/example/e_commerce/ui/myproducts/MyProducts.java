package com.example.e_commerce.ui.myproducts;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.adapter.AllProductAdapter;
import com.example.e_commerce.adapter.MyProductsAdapter;
import com.example.e_commerce.databinding.AllProductFragmentBinding;
import com.example.e_commerce.databinding.MyProductsFragmentBinding;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.ui.allproduct.AllProductViewModel;

import java.util.ArrayList;

public class MyProducts extends Fragment implements MyProductsAdapter.OnProductListener {

    private MyProductsViewModel mViewModel;
    private MyProductsFragmentBinding binding;
    MyProductsAdapter.OnProductListener productListener= this;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mViewModel = new ViewModelProvider(this).get(MyProductsViewModel.class);
        binding = MyProductsFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mViewModel.getLiveData().observe(getViewLifecycleOwner(), productListObserve);
        return root;

    }



    Observer<ArrayList<Product>> productListObserve = new Observer<ArrayList<Product>>() {
        @Override
        public void onChanged(ArrayList<Product> products) {
            MyProductsAdapter adapter = new MyProductsAdapter(products, getContext(), productListener);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerview.setAdapter(adapter);
        }
    };

    @Override
    public void onProductClick(int position, View view) {
        Bundle bundle =new Bundle();
        bundle.putSerializable("product",mViewModel.getLiveData().getValue().get(position));
        Navigation.findNavController(view).navigate(R.id.action_mes_produits_Fragment_to_mon_produit_Fragment,bundle);
    }

    @Override
    public void onNameClick(int position, View view) {

    }
}