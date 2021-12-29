package com.example.e_commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.e_commerce.R;
import com.example.e_commerce.entities.Publicity;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {
    ArrayList<Publicity> publicities;
    Context context;

    public SliderAdapter(ArrayList<Publicity> publicities, Context context) {
        this.publicities = publicities;
        this.context = context;
    }

    @Override
    public SliderAdapter.Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_items, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.Holder viewHolder, int position) {
        Publicity publicity = publicities.get(position);
//        Glide.with(context).asBitmap().load(publicity.getImages()).diskCacheStrategy(DiskCacheStrategy.ALL).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                viewHolder.getImageView().setImageBitmap(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//        });
        int drawableReourceId = viewHolder.itemView.getContext().getResources()
                .getIdentifier(publicity.getImages(), "drawable",
                        viewHolder.itemView.getContext().getPackageName());

        Glide.with(viewHolder.itemView.getContext())
                .load(drawableReourceId)
                .into(viewHolder.imageView);

    }

    @Override
    public int getCount() {
        return publicities.size();
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
        private ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_View);
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}
