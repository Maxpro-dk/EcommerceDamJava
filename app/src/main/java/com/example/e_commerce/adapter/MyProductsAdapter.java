package com.example.e_commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.e_commerce.R;
import com.example.e_commerce.entities.Image;
import com.example.e_commerce.entities.Product;
import com.example.e_commerce.utility.ImgManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.ViewHolder> {
    ArrayList<Product> productArrayList;
    Context context;
    OnProductListener onProductListener;

    public MyProductsAdapter(ArrayList<Product> productArrayList, Context context, OnProductListener onProductListener) {
        this.productArrayList = productArrayList;
        this.context = context;
        this.onProductListener = onProductListener;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.tous_produits_xml, parent,false);
        return new ViewHolder(viewItem, onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(product.getName());
        String str1=String.format("%,.0f",product.getAvailable())+" / "+String.format("%,.0f",product.getQuantity());
        String str2= String.format("%,.0f",product.getQunatity_sell());

        viewHolder.quantity.setText(product.getAvailable()>1?str1 +" disponibles":str1 +" disponible");
        viewHolder.sell.setText(product.getQunatity_sell()>1?str2+" ventes":str2+" vente");
        viewHolder.price.setText(String.format("%,.0f",product.getPrice())+" Fcfa");
        Glide.with(context).load(product.getImg_id())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
//        FirebaseFirestore.getInstance().collection(Image.class.getSimpleName()).whereEqualTo("productId",product.getId()).get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        ImgManager.loadImage(product.getImg_id(),holder.imageView,context);
//                    }
//                });

    }

    @Override
    public int getItemCount() {

        return productArrayList == null ? 0 : productArrayList.size();
    }

    public void updateProductList(List<Product> products) {
        productArrayList.clear();
        productArrayList.addAll(products);
        this.notifyDataSetChanged();
    }


    public interface OnProductListener {
        void onProductClick(int position, View view);

        void onNameClick(int position, View view);

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView price;
        TextView quantity;
        TextView sell;
        TextView name;
        ImageView imageView;
        OnProductListener onProductListener;

        public ViewHolder(@NonNull View itemView, OnProductListener productListener) {

            super(itemView);
            this.onProductListener = productListener;
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            imageView =itemView.findViewById(R.id.pic);
            sell =itemView.findViewById(R.id.sell_quantity);
            quantity = itemView.findViewById(R.id.available_quantity);
            itemView.setOnClickListener(this);




        }


        @Override
        public void onClick(View v) {
            // tu verifie la methode à weiget cliquer et tu applique la fonction correspondante

                onProductListener.onProductClick(getAdapterPosition(), v);



        }

    }


}
