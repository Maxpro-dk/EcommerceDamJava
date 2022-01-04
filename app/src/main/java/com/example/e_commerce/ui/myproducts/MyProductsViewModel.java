package com.example.e_commerce.ui.myproducts;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MyProductsViewModel extends ViewModel {
    MutableLiveData<ArrayList<Product>> liveData;
    ArrayList<Product> productArrayList;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private FirebaseAuth auth;
    private Boolean isScrolled= false;
    DocumentSnapshot lastVisible ;
    CollectionReference docPrduct;
    Query query ;
    public static Category category=null;

    public MyProductsViewModel() {
        liveData = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        docPrduct = db.collection(Product.class.getSimpleName());


    }

    public void generate() {
        productArrayList = new ArrayList<>();

                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                lastVisible=queryDocumentSnapshots.getDocuments()
                        .get(queryDocumentSnapshots.size() -1);

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

    public Boolean getScrolled() {
        return isScrolled;
    }

    public void setScrolled(Boolean scrolled) {
        isScrolled = scrolled;
    }

    public void update() {
     if (lastVisible!=null) {

                    query
                    .limit(15)
                 .startAfter(lastVisible)
                 .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
             @Override
             public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                 int n=queryDocumentSnapshots.size() -1;
                 if (n>0) {
                     lastVisible = queryDocumentSnapshots.getDocuments()
                             .get(n);
                 }

                 List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                 for (DocumentSnapshot d : list) {
                     productArrayList.add(d.toObject(Product.class));
                 }

             }
         }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                 setScrolled(false);
             }
         });
     }




    }






    public MutableLiveData<ArrayList<Product>> getLiveData() {
        return liveData;
    }

    public void allQuery(){

        if(category==null)
        query = docPrduct.whereEqualTo("user_id",FirebaseAuth.getInstance().getCurrentUser().getUid()).orderBy("id");
        else query = docPrduct.whereEqualTo("user_id",FirebaseAuth.getInstance().getCurrentUser().getUid()).whereEqualTo("category_id",category.getId()).orderBy("id");

    }

    public void newQuery(){
        if(category==null)
            query = docPrduct.whereEqualTo("user_id",FirebaseAuth.getInstance().getCurrentUser().getUid()).orderBy("timestamp", Query.Direction.DESCENDING);
        else query = docPrduct.whereEqualTo("user_id",FirebaseAuth.getInstance().getCurrentUser().getUid()).whereEqualTo("category_id",category.getId()).orderBy("timestamp", Query.Direction.DESCENDING);


    }



}