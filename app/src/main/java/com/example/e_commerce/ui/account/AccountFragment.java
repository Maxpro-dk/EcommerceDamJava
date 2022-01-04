package com.example.e_commerce.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.e_commerce.R;
import com.example.e_commerce.activities.LoginActivity;
import com.example.e_commerce.activities.MainActivity;
import com.example.e_commerce.databinding.AccountFragmentBinding;
import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {

    private AccountViewModel mViewModel;

    private AccountFragmentBinding binding;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        binding = AccountFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.historyProduct.setOnClickListener(toHistory);
        binding.myProducts.setOnClickListener(toMyProducts);
        binding.textfavorite.setOnClickListener(toFavorite);
        binding.addproduct.setOnClickListener(toAddProduct);
        binding.disconnet.setOnClickListener(tologout);
        binding.wallet.setOnClickListener(towallet);
        return root;
    }

    View.OnClickListener towallet=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getContext(), "Clique now", Toast.LENGTH_SHORT).show();
        }
    };
    View.OnClickListener tologout=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));

        }
    };
    View.OnClickListener toHistory = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.hideBottomBar();
          Navigation.findNavController(v).navigate(R.id.action_navigation_account_to_historique_Fragment);
        }
    };

    View.OnClickListener toMyProducts = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.hideBottomBar();
            Navigation.findNavController(v).navigate(R.id.action_navigation_account_to_mes_produits_Fragment);
        }
    };

    View.OnClickListener toFavorite = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.hideBottomBar();
            Navigation.findNavController(v).navigate(R.id.action_navigation_account_to_mes_favories_Fragment);
        }
    };
    View.OnClickListener toAddProduct = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.hideBottomBar();
            Navigation.findNavController(v).navigate(R.id.action_navigation_account_to_createProductFragment);
        }
    };

    public void onStart() {
        MainActivity.showBottomBar();
        super.onStart();

    }
}