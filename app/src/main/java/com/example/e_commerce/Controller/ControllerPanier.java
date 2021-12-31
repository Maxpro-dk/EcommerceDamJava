package com.example.e_commerce.Controller;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.e_commerce.adapter.ChangeNumberItemsListener;
import com.example.e_commerce.entities.Basket;
import com.example.e_commerce.entities.Basket_line;
import com.example.e_commerce.entities.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ControllerPanier {
    private Context context;
    private TinyDB tinyDB;
    public ControllerPanier(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(Product item) {

        ArrayList<Product> listProduct = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getName().equals(item.getName())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listProduct.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listProduct.add(item);
        }

        tinyDB.putListObject("productList", listProduct);
        Toast.makeText(context, "Add to your Cart" +listProduct, Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Product> getListCart() {
        return tinyDB.getListObject("productList");
    }

    public void minusNumberProduct(ArrayList<Product> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listProduct.get(position).getNumberInCart() == 1) {
            Toast.makeText(context, "Vous ne pouvez plus enlever le product", Toast.LENGTH_SHORT).show();
        } else {
            listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("productList", listProduct);
        changeNumberItemsListener.changed();
    }

    public void deleteproduct(ArrayList<Product> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listProduct.remove(position);
        tinyDB.putListObject("productList", listProduct);
        changeNumberItemsListener.changed();
    }
    public void plusNumberProduct(ArrayList<Product> listProduct, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listProduct.get(position).setNumberInCart(listProduct.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("productList", listProduct);
        changeNumberItemsListener.changed();
    }

    public Double getTotalPrice() {
        ArrayList<Product> productArrayList = getListCart();
        double pricetatol = 0;
        for (int i = 0; i < productArrayList.size(); i++) {
            pricetatol = pricetatol + (productArrayList.get(i).getPrice() * productArrayList.get(i).getNumberInCart());
        }
        return pricetatol;
    }
}
