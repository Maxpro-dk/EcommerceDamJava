package com.example.e_commerce.ui.allproduct;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class AllProductViewModel extends ViewModel {
    MutableLiveData<ArrayList<Product>> liveData;
    ArrayList<Product> productArrayList;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    CollectionReference referenceProduct;
    private String tab="tous";
    public static Category category=null;
    Query query;

    public AllProductViewModel() {
        liveData = new MutableLiveData<>();
        db=FirebaseFirestore.getInstance();
        referenceProduct= db.collection(Product.class.getSimpleName());
    }

    public void generate() {
        productArrayList = new ArrayList<>();
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
              if (task.isSuccessful()){
                  for (QueryDocumentSnapshot snapshot:task.getResult()){
                      Product product=snapshot.toObject(Product.class);
                      productArrayList.add(product);
                  }
                  liveData.setValue(productArrayList);
              }
            }
        });

    }

    public MutableLiveData<ArrayList<Product>> getLiveData() {
        return liveData;
    }

    public void allQuery(){
        if(category==null)
            query = referenceProduct.orderBy("id");
        else query = referenceProduct.whereEqualTo("category_id",category.getId()).orderBy("id");

    }

    public void newQuery(){
        if(category==null)
            query = referenceProduct.orderBy("timestamp", Query.Direction.DESCENDING);
        else query = referenceProduct.whereEqualTo("category_id",category.getId()).orderBy("timestamp", Query.Direction.DESCENDING);


    }

}