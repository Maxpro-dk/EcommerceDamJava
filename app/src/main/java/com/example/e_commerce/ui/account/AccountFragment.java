package com.example.e_commerce.ui.account;

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
import com.example.e_commerce.databinding.AccountFragmentBinding;

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
        binding.addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "j'ai cliqué ", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.createProductFragment);
            }
        });
        return root;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
//        // TODO: Use the ViewModel
//    }

}