package com.example.e_commerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.e_commerce.R;
import com.example.e_commerce.entities.Category;

import java.util.List;

public class SelectCategoryAdapter  {
//    final  List<Category> categoryList ;
//    final Context context ;
//
//    public SelectCategoryAdapter(List<Category> categoryList, Context context) {
//        super();
//        this.categoryList = categoryList;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return categoryList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return categoryList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        convertView = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
//        TextView textView=convertView.findViewById(R.id.select_category_item);
//        textView.setText(categoryList.get(position).getName());
//
//
//        return convertView;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return null;
//    }
}
