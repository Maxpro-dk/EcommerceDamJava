package com.example.e_commerce.ui.detailProduct;

import androidx.lifecycle.ViewModelProvider;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.e_commerce.entities.Basket;
import com.example.e_commerce.entities.Basket_line;
import com.example.e_commerce.entities.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailProductFragment extends Fragment {

    private DetailProductViewModel mViewModel;
    private DetailProductFragmentBinding binding;
    private Product product;
    private ControllerPanier panier;
    private int numberOrder = 1;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private Basket_line basket_line;
    private Basket basket;
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
        db=FirebaseFirestore.getInstance();
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
        Toast.makeText(getContext(), ""+product.getImg_id(), Toast.LENGTH_SHORT).show();
        binding.title.setText(product.getName());
        binding.prix.setText(String.valueOf(product.getPrice()));
        binding.descriptionproduct.setText(product.getDescription());

        binding.addtopanier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documbasket=db.collection(Basket.class.getSimpleName()).document();
                DocumentReference documentbasketLine=db.collection(Basket_line.class.getSimpleName()).document();
                basket=new Basket();
                basket_line=new Basket_line();

                basket.setUser_id("bHwzDTxZGxe5Tob8z1irDI65w7j1");
                basket.setId_basket(documbasket.getId());
                basket_line.setId(documentbasketLine.getId());
                basket_line.setProduct_id(product.getId());
                documbasket.set(basket).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        basket_line.setBask_id(basket.getId_basket());
                        cardProduct(documentbasketLine,basket_line);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
                product.setNumberInCart(numberOrder);
                panier.insertFood(product);
                Toast.makeText(getContext(), "Product add", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public Boolean  cardProduct(DocumentReference d,Object o){
        return d.set(o).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).isSuccessful();

    }
}