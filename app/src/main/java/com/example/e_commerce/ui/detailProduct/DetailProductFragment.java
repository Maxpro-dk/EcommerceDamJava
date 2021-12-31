package com.example.e_commerce.ui.detailProduct;

import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.e_commerce.Controller.ControllerPanier;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.e_commerce.R;
import com.example.e_commerce.databinding.DetailProductFragmentBinding;
import com.example.e_commerce.entities.Product;

public class DetailProductFragment extends Fragment {

    private DetailProductViewModel mViewModel;
    private DetailProductFragmentBinding binding;
    private Product product;
    private ControllerPanier panier;
    private int numberOrder = 1;
    private TextView textproductselect,textachat,textdisponible,textrestant;
    public static DetailProductFragment newInstance() {
        return new DetailProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel=new ViewModelProvider(this).get(DetailProductViewModel.class);
        binding=DetailProductFragmentBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        panier=new ControllerPanier(getContext());
        getData();
        return view;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(DetailProductViewModel.class);
//        // TODO: Use the ViewModel
//    }

    public void getData(){
        product =(Product)getArguments().getSerializable("product");
        Glide.with(getContext()).load(product.getImg_id())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageproduct);
//        int drawableResourceId = this.getResources().getIdentifier(product.getImg_id(), "drawable", getContext().getPackageName());
//        Glide.with(this)
//                .load(drawableResourceId)
//                .into(binding.imageproduct);
        Toast.makeText(getContext(), ""+product.getImg_id(), Toast.LENGTH_SHORT).show();
//        Glide.with(getContext()).asBitmap().load(product.getImg_id())
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                binding.imageproduct.setImageBitmap(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        });
        binding.title.setText(product.getName());
        binding.prix.setText(String.valueOf(product.getPrice()));
        binding.descriptionproduct.setText(product.getId());

        binding.addtopanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setNumberInCart(numberOrder);
                panier.insertFood(product);
                Toast.makeText(getContext(), "Product add", Toast.LENGTH_SHORT).show();
            }
        });
    }
}