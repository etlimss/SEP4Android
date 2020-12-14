package com.example.sep4android.client.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sep4android.client.model.Measurements;
import com.example.sep4android.client.repository.MeasurementRepository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Measurements> measurementsMutableLiveData= new MutableLiveData<>() ;
    private MeasurementRepository measurementRepository;




    public MainViewModel(@NonNull Application application) {
        super(application);
        measurementRepository= MeasurementRepository.getInstance(application);
    }


    public MutableLiveData<Measurements> getMeasurementsMutableLiveData() {
        return measurementsMutableLiveData;
    }
}
