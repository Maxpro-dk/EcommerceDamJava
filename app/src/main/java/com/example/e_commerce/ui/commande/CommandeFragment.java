package com.example.e_commerce.ui.commande;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commerce.R;
import com.example.e_commerce.activities.MainActivity;

public class CommandeFragment extends Fragment {

    private CommandeViewModel mViewModel;

    public static CommandeFragment newInstance() {
        return new CommandeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.commande_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CommandeViewModel.class);
        // TODO: Use the ViewModel
    }



    @Override
    public void onResume() {
        MainActivity.showBottomBar();
        super.onResume();

    }


}