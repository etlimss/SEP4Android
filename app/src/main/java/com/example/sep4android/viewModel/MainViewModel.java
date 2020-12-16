package com.example.sep4android.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sep4android.data.model.Measurements;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Measurements> measurementsMutableLiveData= new MutableLiveData<>() ;

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<Measurements> getMeasurementsMutableLiveData() {
        return measurementsMutableLiveData;
    }
}
