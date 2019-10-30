package com.mohit.myassignment.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mohit.myassignment.service.repository.storge.model.Facts;

/**
 * Created by Mohit Issar on 10/25/2019.
 */

public class FactDetailsViewModel extends ViewModel {
    final private MutableLiveData factMutableLiveData;

    public FactDetailsViewModel() {
        factMutableLiveData = new MutableLiveData<Facts>();
    }

    public MutableLiveData<Facts> getFact() {
        return factMutableLiveData;
    }
}
