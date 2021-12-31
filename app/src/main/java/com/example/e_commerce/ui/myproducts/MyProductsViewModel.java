package com.example.e_commerce.ui.myproducts;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce.entities.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MyProductsViewModel extends ViewModel {
    MutableLiveData<ArrayList<Product>> liveData;
    ArrayList<Product> productArrayList;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    public MyProductsViewModel() {
        liveData = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        generate();

    }

    public void generate() {
        productArrayList = new ArrayList<>();
        CollectionReference docPrduct = db.collection(Product.class.getSimpleName());
        docPrduct.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d: list){
                    productArrayList.add(d.toObject(Product.class));
                }

            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                liveData.setValue(productArrayList);
            }
        });

    }


    public MutableLiveData<ArrayList<Product>> getLiveData() {
        return liveData;
    }

}