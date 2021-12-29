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
import com.example.e_commerce.Controller.ControllerPanier;
import com.example.e_commerce.R;
import com.example.e_commerce.entities.Basket_line;
import com.example.e_commerce.entities.Product;

import java.util.ArrayList;

public class PanierAdapter extends RecyclerView.Adapter<PanierAdapter.Viewholder> {
    ArrayList<Basket_line> basket_lines;
    ArrayList<Product> products;
    private ControllerPanier panier;
    ChangeNumberItemsListener changeNumberItemsListener;

    public PanierAdapter(ArrayList<Product> products,Context context ,ChangeNumberItemsListener changeNumberItemsListener) {
        this.products = products;
        panier=new ControllerPanier(context);
        this.changeNumberItemsListener = changeNumberItemsListener;

    }

    @NonNull
    @Override
    public PanierAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_panier, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanierAdapter.Viewholder holder, int position) {
        Product product=products.get(position);
         holder.textTitle.setText(product.getName());
         holder.textPrice.setText(product.getPrice()+"fcfa");
         holder.textValues.setText(String.valueOf(product.getNumberInCart()));
         if(product.getNumberInCart()>1){
             holder.textPrice.setText(Math.round(product.getNumberInCart()*product.getPrice())+"fcfa");
         }
//        Glide.with(context).asBitmap().load(product.getImg_id())
//                .diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                holder.imagePic.setImageBitmap(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        });
        int drawableReourceId = holder.itemView.getContext().getResources()
                .getIdentifier(product.getImg_id(), "drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableReourceId)
                .into(holder.imagePic);

        holder.textMoins.setOnClickListener(v->panier.minusNumberProduct(products,position,()->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
        holder.textPlus.setOnClickListener(v->panier.plusNumberProduct(products,position,()->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));
        holder.imagedelete.setOnClickListener(v -> panier.deleteproduct(products,position,()->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imagedelete;
        private ImageView imagePic;
        private TextView textTitle, textPrice, textPlus, textMoins, textValues;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imagedelete = itemView.findViewById(R.id.feeEachItem);
            imagePic = itemView.findViewById(R.id.pic);
            textTitle = itemView.findViewById(R.id.titleProduct);
            textPrice = itemView.findViewById(R.id.text_price);
            textPlus = itemView.findViewById(R.id.plusCardBtn);
            textMoins = itemView.findViewById(R.id.moinsCardBtn);
            textValues = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
