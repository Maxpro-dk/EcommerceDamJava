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
import com.example.e_commerce.entities.Product;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.Viewholder> {
    ArrayList<Product> products;
    Context context;
    OnProductClickListener productListener;


    public AllProductAdapter(ArrayList<Product> products, Context context, OnProductClickListener productListener) {
        this.products = products;
        this.context = context;
        this.productListener = productListener;
    }

    @NonNull
    @Override
    public AllProductAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_image, parent, false);
        return new Viewholder(view, productListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.Viewholder holder, int position) {
        Product product = products.get(position);

//        Glide.with(context).asBitmap().load(product.getImg_id())
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                holder.imageProduct.setImageBitmap(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        });
//        int drawableReourceId = holder.itemView.getContext().getResources()
//                .getIdentifier(product.getImg_id(), "drawable",
//                        holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableReourceId)
//                .into(holder.imageProduct);
        Glide.with(context).load(product.getImg_id())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageProduct);
        holder.nameProduct.setText(product.getName());
        holder.priceProduct.setText(String.valueOf(product.getPrice()));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageProduct;
        private TextView nameProduct;
        private TextView priceProduct;
        OnProductClickListener productClickListener;

        public Viewholder(@NonNull View itemView, OnProductClickListener listener) {
            super(itemView);
            this.productClickListener = listener;
            imageProduct = itemView.findViewById(R.id.imageproduct);
            nameProduct = itemView.findViewById(R.id.productname);
            priceProduct = itemView.findViewById(R.id.productprice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            productClickListener.OnProductClick(getAdapterPosition(), v);
        }
    }

    public interface OnProductClickListener {
        void OnProductClick(int position, View view);
    }
}
