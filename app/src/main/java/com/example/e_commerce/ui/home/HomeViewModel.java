package com.example.e_commerce.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce.entities.Publicity;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Publicity>> mutableLiveData;
    ArrayList<Publicity> publicityArrayList;

    public HomeViewModel() {
        mutableLiveData = new MutableLiveData<>();
        slide();
    }

    public void slide() {
        publicityArrayList = new ArrayList<>();
        publicityArrayList.add(new Publicity("bhdj", "dhbdfh", "banner", null, null, 10120, 52412));
        publicityArrayList.add(new Publicity("bhdj", "dhbdfh", "banner2", null, null, 10120, 52412));
        publicityArrayList.add(new Publicity("bhdj", "dhbdfh", "banner3", null, null, 10120, 52412));
        publicityArrayList.add(new Publicity("bhdj", "dhbdfh", "banner4", null, null, 10120, 52412));
        publicityArrayList.add(new Publicity("bhdj", "dhbdfh", "banner5", null, null, 10120, 52412));

        mutableLiveData.postValue(publicityArrayList);
    }

    public MutableLiveData<ArrayList<Publicity>> getMutableLiveData() {
        return mutableLiveData;
    }
}