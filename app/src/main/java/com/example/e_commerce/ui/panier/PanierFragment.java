package com.example.e_commerce.ui.panier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.Controller.ControllerPanier;
import com.example.e_commerce.adapter.ChangeNumberItemsListener;
import com.example.e_commerce.adapter.PanierAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce.R;
import com.example.e_commerce.databinding.PanierFragmentBinding;

public class PanierFragment extends Fragment {

    private PanierViewModel mViewModel;
    private PanierFragmentBinding binding;
    private PanierAdapter panierAdapter;
    private ControllerPanier panier;
    private TextView textproductselect,textachat,textdisponible,textrestant;
    public static PanierFragment newInstance() {
        return new PanierFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=PanierFragmentBinding.inflate(inflater,container,false);
        View root=binding.getRoot();
         panier=new ControllerPanier(getContext());
         binding.recyclerviewpanier.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
         Toast.makeText(getActivity(), ""+panier.getListCart(), Toast.LENGTH_SHORT).show();
         panierAdapter= new PanierAdapter(panier.getListCart(), getContext(), new ChangeNumberItemsListener() {
             @Override
             public void changed() {
                 calculate();
             }
         });
         binding.recyclerviewpanier.setAdapter(panierAdapter);
        return root;
    }



//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(PanierViewModel.class);
//        // TODO: Use the ViewModel
//    }

    public void calculate(){
        double achat=Math.round(panier.getTotalPrice());
        double montantdisponible=500000;
        double montanatrestant=montantdisponible-achat;
        binding.totatproduct.setText(String.valueOf(panier.getListCart().size()));
        binding.deliveryTxt.setText(String.valueOf(achat));
        binding.taxTxt.setText(String.valueOf(montantdisponible));
        binding.totalrestant.setText(String.valueOf(montanatrestant));
        Toast.makeText(getContext(), "Calcul effectué", Toast.LENGTH_SHORT).show();
    }
}