package com.example.e_commerce.ui.allproduct;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

    public AllProductViewModel() {
        liveData = new MutableLiveData<>();
        generate();
    }

    public void generate() {
        productArrayList = new ArrayList<>();
//        db=FirebaseFirestore.getInstance();
//        CollectionReference reference= db.collection(Product.class.getSimpleName());
//        Query query=reference.whereEqualTo("id",reference.getId());
//        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//              if (task.isSuccessful()){
//                  for (QueryDocumentSnapshot snapshot: task.getResult()){
//                     Product product= snapshot.toObject(Product.class);
//                     productArrayList.add(product);
//                  }
//              }
//            }
//        });
        productArrayList.add(new Product("hgd", "alber", "hello", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "Albert", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "Moufid", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "Ange", "je ne sais quoi", 2000, 548782, null, "ordinateur", "banner4"));
        productArrayList.add(new Product("hgd", "alber", "pc Hp-max pro", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "pc Hp-max pro", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "pc Hp-max pro", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "pc Hp-max pro", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "pc Hp-max pro", "je ne sais quoi", 2000, 548782, null, "ordinateur", "chemh2"));
        productArrayList.add(new Product("hgd", "alber", "hp note book", "vraiment hein la vie qu'on mène", 4000, 58572, null, "ordinateur probook", "banner4"));
        productArrayList.add(new Product("hgd", "alber", "food qu'on mange", "vraiment hein la vie qu'on mène", 4000, 58572, null, "ordinateur probook", "banner5"));
        productArrayList.add(new Product("hgd", "alber", "habits", "vraiment hein la vie qu'on mène", 4000, 58572, null, "ordinateur probook", "fem3"));
        liveData.setValue(productArrayList);
    }


    public MutableLiveData<ArrayList<Product>> getLiveData() {
        return liveData;
    }
}