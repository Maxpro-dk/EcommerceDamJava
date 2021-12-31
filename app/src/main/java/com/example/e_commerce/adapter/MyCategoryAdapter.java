package com.example.e_commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce.R;
import com.example.e_commerce.entities.Category;
import com.example.e_commerce.entities.Image;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.utility.ImgManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyCategoryAdapter extends RecyclerView.Adapter<MyCategoryAdapter.ViewHolder> {

    ArrayList<Category> categoryArrayList;
    Context context;
    MyCategoryAdapter.OnCategoryListener onCategoryListener;

    public MyCategoryAdapter(ArrayList<Category> categoryArrayList, Context context, MyCategoryAdapter.OnCategoryListener categoryListener) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
        this.onCategoryListener = categoryListener;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_category_items, parent, false);
        return new ViewHolder(viewItem, onCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryArrayList.get(position);
        MyCategoryAdapter.ViewHolder viewHolder = (MyCategoryAdapter.ViewHolder) holder;
        viewHolder.name.setText(category.getName());

    }


    @Override
    public int getItemCount() {

        return categoryArrayList == null ? 0 : categoryArrayList.size();
    }

    public void updateProductList(List<Category> categories) {
        categoryArrayList.clear();
        categoryArrayList.addAll(categories);
        this.notifyDataSetChanged();
    }


    public interface OnCategoryListener {
        void onCategoryClick(int position, View view);

        void onNameClick(int position, View view);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        MyCategoryAdapter.OnCategoryListener onCategoryListener;

        public ViewHolder(@NonNull View itemView, MyCategoryAdapter.OnCategoryListener listener) {

            super(itemView);
            this.onCategoryListener = listener;
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {
            // tu verifie la methode à weiget cliquer et tu applique la fonction correspondante

            onCategoryListener.onCategoryClick(getAdapterPosition(), v);


        }

    }

}
