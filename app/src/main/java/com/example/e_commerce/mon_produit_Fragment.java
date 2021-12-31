package com.example.e_commerce;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce.activities.MainActivity;
import com.example.e_commerce.databinding.FragmentMonProduitBinding;
import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Image;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.utility.ImgManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;


public class mon_produit_Fragment extends Fragment {
    private Product product;
    private FragmentMonProduitBinding binding;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private ProgressDialog  dialog;
    private Handler handler ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.hideBottomBar();
        if (getArguments() != null) {
            product  = (Product) getArguments().getSerializable("product");
        }
        handler = new Handler(Looper.getMainLooper());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMonProduitBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        dialog=new ProgressDialog(getContext());
        dialog.setTitle("Please waitting");
        db= FirebaseFirestore.getInstance();
        storage= FirebaseStorage.getInstance();
        initProduct();


        binding.toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        binding.modifyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify(product,v);
            }
        });

        binding.validateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePrivate(v);
            }
        });

        return view;
    }



    private void initProduct(){
        if (product.isStatus())  binding.validateProduct.setText("MASQUER");
        binding.name.setText(product.getName());
        binding.description.setText(product.getDescription());
        binding.price.setText(String.format("%,.0f",product.getPrice())+" Fcfa");
        binding.totalQuantity.setText( String.format("%,.0f",product.getQuantity()));
        binding.sellQuantity.setText(String.format("%,.0f",product.getQunatity_sell()));
        binding.availableQuantity.setText(String.format("%,.0f",product.getAvailable()));

        DocumentReference docCategrie = db.collection(Category.class.getSimpleName()).document(product.getCategory_id());
        docCategrie.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ImgManager.loadImage(product.getImg_id(), binding.imageproduct, requireActivity());
            }
        });



                   






    }

    public  void  delete(Product product){


    }
    public  void  modify(Product product,View view){

        Bundle bundle = new Bundle();
        bundle.putSerializable("product",product);
        Navigation.findNavController(view).navigate(R.id.action_mon_produit_Fragment_to_createProductFragment,bundle);


    }
    public  void  makePrivate(View v){
        ProgressDialog p = new ProgressDialog(getContext());
        p.create();
        p.show();
        product.setStatus(!product.isStatus());

        db.collection(Product.class.getSimpleName()).document(product.getId()).set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
               if (product.isStatus()){
                   Navigation.findNavController(v).navigate(R.id.action_mon_produit_Fragment_to_mes_produits_Fragment);
               }else binding.validateProduct.setText("PUBLIER");
               p.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              p.dismiss();
            }
        });

    }



}