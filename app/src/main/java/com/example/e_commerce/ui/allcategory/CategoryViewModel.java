package com.example.e_commerce.ui.allcategory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce.entities.Category;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {
    MutableLiveData<ArrayList<Category>> liveData;
    ArrayList<Category> categoryArrayList;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private Boolean isScrolled= false;
    DocumentSnapshot lastVisible ;
    CollectionReference docCategory;
    Query query ;

    public CategoryViewModel() {
        liveData = new MutableLiveData<>();
        db = FirebaseFirestore.getInstance();
        docCategory = db.collection(Category.class.getSimpleName());
        allQuery();
        generate();
    }

    public void generate() {
        categoryArrayList = new ArrayList<>();
        Category category = new Category("","Tous les produits");
        categoryArrayList.add(category);

                query
                .limit(11)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                lastVisible=queryDocumentSnapshots.getDocuments()
                        .get(queryDocumentSnapshots.size() -1);

                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d: list){
                    categoryArrayList.add(d.toObject(Category.class));
                }

            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                liveData.setValue(categoryArrayList);
                setScrolled(false);

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
                        categoryArrayList.add(d.toObject(Category.class));
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






    public MutableLiveData<ArrayList<Category>> getLiveData() {
        return liveData;
    }

    public void allQuery(){
        query = docCategory.orderBy("name");

    }

}